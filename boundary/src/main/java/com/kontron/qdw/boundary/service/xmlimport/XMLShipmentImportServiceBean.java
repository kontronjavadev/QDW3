package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.shipment.ShipmentMappingType;
import com.kontron.qdw.boundary.service.mapping.shipment.ShipmentRootMappingType;
import com.kontron.qdw.domain.base.Customer;
import com.kontron.qdw.domain.base.MovementType;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.domain.serial.Shipment;
import com.kontron.qdw.repository.base.CustomerRepository;
import com.kontron.qdw.repository.base.MovementTypeRepository;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoJeMatIdFilter;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoMatIdResult;
import com.kontron.qdw.repository.serial.ShipmentRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;
import com.kontron.util.version.RevisionUtil;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Import der Shipment-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Superklasse Interface implementiert und sonst keine No-Interface-View bereit gestellt wird
public class XMLShipmentImportServiceBean extends AbstractXMLImportServiceBean<ShipmentRootMappingType, ShipmentMappingType> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "shipment";
    private static final String FOLDER_SUB_PATH = "shipment";
    private static final String SCHEMA_NAME = "Shipment.xsd";

    private static final String UNKNOWN_CUSTOMER = "0000";
    private static final String CANCELED_SHIPMENT_MOVEMENT_TYPE_1 = "602";


    @EJB
    private CustomerRepository customerManager;
    @EJB
    private ShipmentRepository shipmentManager;
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


    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    protected String getFolderSubPath() {
        return FOLDER_SUB_PATH;
    }

    @Override
    protected String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override
    protected ImportType getImportType() {
        return ImportType.QDW_SHIPMENT;
    }

    @Override
    protected Class<ShipmentRootMappingType> getXmlRootClazz() {
        return ShipmentRootMappingType.class;
    }

    @Override
    protected Function<ShipmentRootMappingType, List<ShipmentMappingType>> getGetElementsFunction() {
        return ShipmentRootMappingType::getShipments;
    }



    @Override
    protected void importBulk(String importFileName, TaskNodeLog tsk, List<ShipmentMappingType> importedShipments, List<String> errorList,
            BulkProcess bulkProcess) throws Exception {
        // aktuell verarbeiteter Batch
        List<ShipmentMappingType> curBatch = importedShipments.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());
        batchNormalisieren(curBatch);
        curBatch = batchFiltern(curBatch);


        Map<String, MovementType> mvtTypeMap = MovementType.asMap(movementTypeManager.findAll());
        // Movementtypes erzeugen, die es noch nicht gibt
        Map<String, MovementType> newCreatedMvtTypeMap = createMissingMvtTypes(mvtTypeMap, curBatch);
        // die neu erzeugten Movementtypes hinzufügen
        mvtTypeMap.putAll(newCreatedMvtTypeMap);


        Map<String, Plant> plantMap = Plant.asMap(plantManager.findAll());

        // Es wird eine Map zurück gegeben, in der sämtliche angeforderten SAP-Nummern als key vorhanden sind!
        Map<String, Material> existingMaterialMap = cacheMaterial(curBatch, true);

        Map<SerNoMatIdResult, SerialObject> existingSerObjMap = getSerialObjectsOfBatch(curBatch, existingMaterialMap);


        for (ShipmentMappingType shipment : curBatch) {
            bulkProcess.logProcess(logger);

            try {
                importEntry(shipment, importFileName, errorList, existingMaterialMap, existingSerObjMap, plantMap, mvtTypeMap);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, shipment.getMaterialSapNumber(), e));
                tsk.abortTask();
                throw e;
            }

            bulkProcess.nextCnt();
        }
    }

    private void importEntry(ShipmentMappingType importedShipment, String importFileName, List<String> errorList,
            Map<String, Material> existingMaterialMap, Map<SerNoMatIdResult, SerialObject> existingSerObjMap, Map<String, Plant> plantMap,
            Map<String, MovementType> mvtTypeMap) {
        Long transactionId = Long.parseLong(importedShipment.getId());
        if (shipmentManager.findById(transactionId) != null) {
            // Shipment wurde bereits angelegt
            return;
        }


        Material material = existingMaterialMap.get(importedShipment.getMaterialSapNumber());
        if (material == null) {
            String sapNumber = importedShipment.getMaterialSapNumber();
            String errorMsg = String.format("Fehler Import Datei '%s': kein Material zu SAP-Nummer '%s' vorhanden.", importFileName, sapNumber);
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }

        Plant plant = plantMap.get(importedShipment.getPlantCode());
        if (plant == null) {
            String errorMsg = String.format("Fehler Import Datei '%s': unbekanntes Werk '%s'.", importFileName, importedShipment.getPlantCode());
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }

        Customer customer = customerManager.findById(importedShipment.getCustomerCode());
        if (customer == null) {
            // leerer Customer wurde ersetzt durch UNKNOWN, aber der angegebene Customer kann natürlich ebenfalls unbekannt sein
            customer = customerManager.findById(UNKNOWN_CUSTOMER);
        }

        MovementType movementType = mvtTypeMap.get(importedShipment.getMovementTypeCode());
        MaterialRevision revision = getOrCreateMatRev(material, plant, importedShipment.getRevisionNumber());

        SerialObject serialObject = existingSerObjMap.get(new SerNoMatIdResult(material.getId(), importedShipment.getSerialNumber()));
        if (serialObject == null) {
            serialObject = new SerialObject();
            serialObject.setSerialNumber(importedShipment.getSerialNumber());
            serialObject.setMaterial(material);
            serialObject = serialObjectManager.persist(serialObject, false, false);
            existingSerObjMap.put(new SerNoMatIdResult(material.getId(), importedShipment.getSerialNumber()), serialObject);
            // logger.trace("Neues SerObj erstellt: " + importedArrival.getSerialNumber() + " für " + material.getMaterialNumber());
        }

        Shipment shipment = new Shipment();
        shipment.setId(transactionId);
        shipment.setPlant(plant);
        shipment.setCustomer(customer);
        shipment.setShipmentDate(LocalDate.parse(importedShipment.getShipmentDate())); // Datum im ISO-8601-Format
        shipment.setOrderNumber(importedShipment.getOrderNumber());
        shipment.setMaterialRevision(revision);
        shipment.setSerialObject(serialObject);
        shipment.setMovementType(movementType);
        shipment.setRebuildFlag(true);
    }



    private void batchNormalisieren(List<ShipmentMappingType> curBatch) {
        curBatch.forEach(importedShipment -> {
            importedShipment.setMaterialSapNumber(StringUtil.removeLeadingZero(importedShipment.getMaterialSapNumber()));
            importedShipment.setOrderNumber(StringUtil.removeLeadingZero(importedShipment.getOrderNumber()));
            importedShipment.setSerialNumber(StringUtil.removeLeadingZeroIfNumber(importedShipment.getSerialNumber()));
            importedShipment.setSerialObjectSapNumber(importedShipment.getMaterialSapNumber());
            importedShipment.setId(RegExUtils.replaceAll(importedShipment.getId(), "[A-Z]", ""));

            String customerCode = StringUtil.removeLeadingZero(importedShipment.getCustomerCode());
            customerCode = StringUtils.defaultIfEmpty(customerCode, UNKNOWN_CUSTOMER);
            importedShipment.setCustomerCode(customerCode);
        });
    }

    private List<ShipmentMappingType> batchFiltern(List<ShipmentMappingType> curBatch) {
        curBatch = curBatch.stream()
                .filter(importedShipment -> StringUtils.isNotEmpty(importedShipment.getSerialNumber()))
                .filter(importedShipment -> StringUtils.isNotEmpty(importedShipment.getId()))
                // CANCELED_SHIPMENT_MOVEMENT_TYPE_1 must be taken over without order number because
                // SAP is not able to transfer this number in any circumstance
                .filter(importedShipment -> StringUtils.isNotEmpty(importedShipment.getOrderNumber())
                        || Objects.equals(importedShipment.getMovementTypeCode(), CANCELED_SHIPMENT_MOVEMENT_TYPE_1))
                .collect(Collectors.toList());
        return curBatch;
    }


    private Map<String, Material> cacheMaterial(List<ShipmentMappingType> xmlItems, boolean fetchRevisions) {
        // Das angegebene Material in der Datenbank suchen.
        // -> Map zu allen gesuchten Materialien erstellen;
        // key ist SAP-Nummer; Suche nach SAP-Nummer oder ersatzweise nach Materialnummer
        // Die Map wird mit den so gefundenen Materialien befüllt.

        Map<String, String> matNrZuSapNr = xmlItems.stream()
                .collect(Collectors.toMap(
                        ShipmentMappingType::getMaterialSapNumber,
                        ShipmentMappingType::getMaterialNumber,
                        (existingValue, newValue) -> existingValue));

        Set<String> sapNummern = matNrZuSapNr.keySet();

        // Es wird eine Map zurück gegeben, in der sämtliche angeforderten SAP-Nummern als key vorhanden sind!
        Map<String, Material> existingMaterialMap = materialManager.findBySAPNumbers(sapNummern, fetchRevisions);

        // Nicht gefundene Materialien zusätzlich nach Materialnummer suchen. Die Rückgabe-Map ist ebenfalls nach SAP-Nummer!
        Set<String> missingMatNr = existingMaterialMap.entrySet().stream()
                // filtern nach Einträgen, zu denen nichts gefunden wurde
                .filter(entry -> entry.getValue() == null)
                .map(Entry::getKey)
                // Materialnummer zu SAP-Nummer holen
                .map(matNrZuSapNr::get)
                .collect(Collectors.toSet());

        // nach MatNr suchen; zurückgegebene Map hat key sap-Nr!
        existingMaterialMap.putAll(materialManager.alternativelyFindByMaterialNumbers(missingMatNr, fetchRevisions));
        return existingMaterialMap;
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

    private Map<String, MovementType> createMissingMvtTypes(Map<String, MovementType> mvtTypeMap, List<ShipmentMappingType> curBatch) {
        return CollectionUtils.subtract(curBatch.stream()
                .map(ShipmentMappingType::getMovementTypeCode)
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

    private Map<SerNoMatIdResult, SerialObject> getSerialObjectsOfBatch(List<ShipmentMappingType> curBatch,
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
