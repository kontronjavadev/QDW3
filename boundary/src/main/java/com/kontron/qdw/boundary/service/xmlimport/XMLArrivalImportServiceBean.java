package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.arrival.ArrivalMappingType;
import com.kontron.qdw.boundary.service.mapping.arrival.ArrivalRootMappingType;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Country;
import com.kontron.qdw.domain.base.MovementType;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.serial.Arrival;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.repository.base.MovementTypeRepository;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.ArrivalRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoJeMatIdFilter;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoMatIdResult;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;
import com.kontron.util.version.RevisionUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Import der Arrival-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLArrivalImportServiceBean extends AbstractXMLImportServiceBean<ArrivalRootMappingType, ArrivalMappingType> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "arrival";
    private static final String FOLDER_SUB_PATH = "arrival";
    private static final String SCHEMA_NAME = "Arrival.xsd";

    private static final String UNKNOWN_SUPPLIER = "XXXX";

    @EJB
    private ArrivalRepository arrivalManager;
    @EJB
    private SupplierRepository supplierManager;
    @EJB
    private MovementTypeRepository movementTypeManager;
    @EJB
    private SerialObjectRepository serialObjectManager;

    @EJB
    private MaterialRevisionRepository materialRevisionManager;
    @EJB
    private MaterialRepository materialManager;
    @EJB
    private PlantRepository plantManager;

    @PersistenceContext
    private EntityManager em;



    /** Perform import */
    @PermitAll
    public ITaskNodeLog runImport() {
        return super.runImport(ENTITY_NAME, FOLDER_SUB_PATH, SCHEMA_NAME, ImportType.QDW_ARRIVAL,
                ArrivalRootMappingType.class, ArrivalRootMappingType::getArrivals);
    }



    @Override
    protected void importBulk(String importFileName, TaskNodeLog tsk, List<ArrivalMappingType> importedArrivals, List<String> errorList,
            BulkProcess bulkProcess) throws Exception {
        // aktuell verarbeiteter Batch
        List<ArrivalMappingType> curBatch = importedArrivals.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());
        batchNormalisieren(curBatch);
        curBatch = batchFiltern(curBatch);

        Map<String, Plant> plantMap = Plant.asMap(plantManager.findAll());


        // alle Supplier dieses Batches
        Map<String, Supplier> existingSupplierMap = getSupplier(curBatch);
        // Supplier erzeugen, die es noch nicht gibt
        Map<String, Supplier> newCreatedSupplierMap = createMissingSupplier(curBatch, existingSupplierMap);
        // die neu erzeugten Supplier hinzufügen
        existingSupplierMap.putAll(newCreatedSupplierMap);


        Map<String, MovementType> mvtTypeMap = MovementType.asMap(movementTypeManager.findAll());
        // Movementtypes erzeugen, die es noch nicht gibt
        Map<String, MovementType> newCreatedMvtTypeMap = createMissingMvtTypes(mvtTypeMap, curBatch);
        // die neu erzeugten Movementtypes hinzufügen
        mvtTypeMap.putAll(newCreatedMvtTypeMap);


        // Es wird eine Map zurück gegeben, in der sämtliche angeforderten SAP-Nummern als key vorhanden sind!
        Map<String, Material> existingMaterialMap = materialManager.findBySAPNumbers(
                curBatch.stream()
                        .map(ArrivalMappingType::getMaterialSapNumber)
                        .collect(Collectors.toSet()),
                true);

        Map<SerNoMatIdResult, SerialObject> existingSerObjMap = getSerialObjectsOfBatch(curBatch, existingMaterialMap);


        for (ArrivalMappingType arrival : curBatch) {
            bulkProcess.logProcess(logger);

            try {
                importEntry(arrival, importFileName, errorList,
                        existingMaterialMap, existingSupplierMap,
                        existingSerObjMap, plantMap, mvtTypeMap);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, arrival.getMaterialSapNumber(), e));
                tsk.abortTask();
                throw e;
            }

            bulkProcess.nextCnt();
        }
    }



    private void importEntry(ArrivalMappingType importedArrival, String importFileName, List<String> errorList,
            Map<String, Material> existingMaterialMap, Map<String, Supplier> existingSupplierMap,
            Map<SerNoMatIdResult, SerialObject> existingSerObjMap, Map<String, Plant> plantMap, Map<String, MovementType> mvtTypeMap) {

        Long transactionId = Long.parseLong(importedArrival.getId());
        if (arrivalManager.findById(transactionId) != null) {
            // Arrival wurde bereits angelegt
            return;
        }

        Material material = existingMaterialMap.get(importedArrival.getMaterialSapNumber());
        if (material == null) {
            String sapNumber = importedArrival.getMaterialSapNumber();
            String errorMsg = String.format("Fehler Import Datei '%s': kein Material zu SAP-Nummer '%s' vorhanden.", importFileName, sapNumber);
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }

        Plant plant = plantMap.get(importedArrival.getPlantCode());
        if (plant == null) {
            String errorMsg = String.format("Fehler Import Datei '%s': unbekanntes Werk '%s'.", importFileName, importedArrival.getPlantCode());
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }

        Supplier supplier = existingSupplierMap.get(importedArrival.getSupplierCode());
        MovementType movementType = mvtTypeMap.get(importedArrival.getMovementTypeCode());


        MaterialRevision revision = getOrCreateMatRev(material, plant, importedArrival.getRevisionNumber());

        SerialObject serialObject = existingSerObjMap.get(new SerNoMatIdResult(material.getId(), importedArrival.getSerialNumber()));
        if (serialObject == null) {
            serialObject = new SerialObject();
            serialObject.setSerialNumber(importedArrival.getSerialNumber());
            serialObject.setMaterial(material);
            serialObject = serialObjectManager.persist(serialObject, false, false);
            existingSerObjMap.put(new SerNoMatIdResult(material.getId(), importedArrival.getSerialNumber()), serialObject);
            // logger.trace("Neues SerObj erstellt: " + importedArrival.getSerialNumber() + " für " + material.getMaterialNumber());
        }


        Arrival arrival = new Arrival();
        arrival.setId(transactionId);
        arrival.setPlant(plant);
        arrival.setArrivalDate(LocalDate.parse(importedArrival.getArrivalDate())); // Datum im ISO-8601-Format
        arrival.setSerialObject(serialObject);
        arrival.setMaterialRevision(revision);
        arrival.setMovementType(movementType);
        arrival.setSupplier(supplier);
        arrival.setOrderNumber(importedArrival.getOrderNumber());
        arrival.setRebuildFlag(true);

        arrival = arrivalManager.persist(arrival, false, false);


        //
        //
        //

        // send mail with newly created suppliers
        // if (!newSupplierMap.isEmpty()) {
        // StringBuilder s = new StringBuilder("Some new suplliers have automatically been created at arrival xml import!\nFile: "
        // + importFile.getName() + "\n\nCode(s):\n");
        //
        // for (String n : newSupplierMap.keySet()) {
        // s.append(n + "\n");
        // }
        //
        // s.append("\n\nPlease check ERP system for further details!");
        // QDWHelper.sendInfoMail("Newly created suppliers at arrival import", s.toString());
        // }
    }



    private void batchNormalisieren(List<ArrivalMappingType> curBatch) {
        curBatch.forEach(importedArrival -> {
            importedArrival.setMaterialSapNumber(StringUtil.removeLeadingZero(importedArrival.getMaterialSapNumber()));
            importedArrival.setOrderNumber(StringUtil.removeLeadingZero(importedArrival.getOrderNumber()));
            importedArrival.setSerialNumber(StringUtil.removeLeadingZeroIfNumber(importedArrival.getSerialNumber()));
            importedArrival.setSerialObjectMaterialNumber(importedArrival.getMaterialSapNumber());
            importedArrival.setId(RegExUtils.replaceAll(importedArrival.getId(), "[A-Z]", ""));
            importedArrival.setSupplierCode(StringUtil.removeLeadingZero(importedArrival.getSupplierCode()));
            if (importedArrival.getSupplierCode().isEmpty()) {
                importedArrival.setSupplierCode(UNKNOWN_SUPPLIER);
            }
        });
    }

    private List<ArrivalMappingType> batchFiltern(List<ArrivalMappingType> curBatch) {
        curBatch = curBatch.stream()
                .filter(importedArrival -> StringUtils.isNotEmpty(importedArrival.getMaterialSapNumber()))
                .filter(importedArrival -> StringUtils.isNotEmpty(importedArrival.getSerialNumber()))
                .filter(importedArrival -> StringUtils.isNotEmpty(importedArrival.getId()))
                .collect(Collectors.toList());
        return curBatch;
    }



    private MaterialRevision getOrCreateMatRev(Material material, Plant plant, String sapRevNumber) {
        String revNo = RevisionUtil.calculateRevNumberBySapRevNumber(sapRevNumber);

        Optional<MaterialRevision> revisionOpt = material.getRevisions().stream()
                .filter(rev -> rev.getPlant().getCode().equals(plant.getCode()))
                .filter(rev -> rev.getRevisionNumber().equals(revNo))
                .findFirst();

        // Revision gefunden
        if (revisionOpt.isPresent()) {
            return revisionOpt.get();
        }

        // nicht gefunden, neu erstellen
        String[] revAltRev2Rev6 = RevisionUtil.extractRevAltRev2Rev6FromSapRevNumber(sapRevNumber);

        MaterialRevision revision = new MaterialRevision();
        revision.setMaterial(material);
        revision.setPlant(plant);
        revision.setRevisionNumber(revNo);
        revision.setRev2(revAltRev2Rev6[1]);
        revision.setRev6(revAltRev2Rev6[2]);

        revision = materialRevisionManager.persist(revision, false, false);
        material.getRevisions().add(revision);
        logger.info("neue Revision erstellt: " + revision);

        return revision;
    }

    private Map<String, Supplier> getSupplier(List<ArrivalMappingType> curBatch) {
        return supplierManager.findByIds(curBatch.stream()
                .map(ArrivalMappingType::getSupplierCode)
                .collect(Collectors.toSet()));
    }

    private Map<String, Supplier> createMissingSupplier(List<ArrivalMappingType> curBatch, Map<String, Supplier> existingSupplierMap) {
        return existingSupplierMap.entrySet().stream()
                .filter(suppMapEntry -> suppMapEntry.getValue() == null)
                .map(Map.Entry::getKey)
                .map(missingCode -> {
                    Supplier supplier = new Supplier(missingCode);
                    supplier.setName(Constants.DUMMY_NAME_BY_IMPORT + " (" + missingCode + ")");
                    supplier.setComment("Automatically created at arrival import");
                    supplier.setCountry(new Country("DE"));
                    supplier = supplierManager.persist(supplier, false, false);
                    logger.info("neuer Supplier erstellt: " + supplier);
                    return supplier;
                })
                .collect(Collectors.toMap(Supplier::getCode, Function.identity()));
    }

    private Map<String, MovementType> createMissingMvtTypes(Map<String, MovementType> mvtTypeMap, List<ArrivalMappingType> curBatch) {
        return CollectionUtils.subtract(curBatch.stream()
                .map(ArrivalMappingType::getMovementTypeCode)
                .collect(Collectors.toSet()),
                mvtTypeMap.keySet())
                // -> missing movementTypes
                .stream()
                .map(missingMovementType -> {
                    MovementType mvtType = new MovementType(missingMovementType);
                    mvtType.setActive(true);
                    mvtType = movementTypeManager.persist(mvtType, false, false);
                    logger.info("neuer MovementType erstellt: " + mvtType);
                    return mvtType;
                })
                .collect(Collectors.toMap(MovementType::getCode, Function.identity()));
    }

    private Map<SerNoMatIdResult, SerialObject> getSerialObjectsOfBatch(List<ArrivalMappingType> curBatch,
            Map<String, Material> existingMaterialMap) {
        List<SerNoJeMatIdFilter> serNoJeMatIdFilter = curBatch.stream()
                // Arrival mit Material aus Map zu einem Tupel verknüpfen
                .map(arrival -> ImmutablePair.of(arrival, existingMaterialMap.get(arrival.getMaterialSapNumber())))
                // Arrival ohne Material rausfiltern
                .filter(pair -> pair.getRight() != null)
                // zu Map mit Set an SerialNumber je Material id
                .collect(Collectors.groupingBy(
                        pair -> pair.getRight().getId(),
                        Collectors.mapping(pair -> pair.getLeft().getSerialNumber(), Collectors.toSet())))
                // Map in eine Liste an record selben Inhalts wandeln
                .entrySet().stream()
                .map(entry -> new SerNoJeMatIdFilter(entry.getKey(), entry.getValue()))
                .toList();

        // Wir übergeben eine Liste an Record, die ein Set an Seriennummern zu einer Material-Id haben und erhalten die SerialObject dazu wieder.
        // Der erhaltene Record des Map-keys ist jedoch flachgeklopft, hat also nur die Material-Id und eine Seriennummer.
        Map<SerNoMatIdResult, SerialObject> existingSerObjMap = serialObjectManager.findBySerialNumberAndMaterialIds(serNoJeMatIdFilter);

        // Da 95% der SerialObject nicht existieren, sollte die Anlage geimeinsam mit der Anlage des Arrival in der Hauptschleife durchgeführt werden
        return existingSerObjMap;
    }

}
