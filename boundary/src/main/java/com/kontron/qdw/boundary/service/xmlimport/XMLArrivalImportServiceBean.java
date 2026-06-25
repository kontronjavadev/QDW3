package com.kontron.qdw.boundary.service.xmlimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.kontron.constants.file.FileType;
import com.kontron.qdw.boundary.material.MaterialRevisionBoundaryService;
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
import com.kontron.qdw.repository.material.BoMItemRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.ArrivalRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoJeMatIdFilter;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoMatIdResult;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportProcessedWithErrors;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;
import com.kontron.util.version.RevisionUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Import der Arrival-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLArrivalImportServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String SCHEMA_PATH = "/schema/Arrival.xsd";
    private static final String FOLDER_SUB_PATH = "arrival";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";

    private static final String ENCODING = Constants.UTF_8;

    // Definition of simple filter to get only *.xml files
    private static final FilenameFilter SIMPLE_XML_FILTER = FileType.XML.getFilenameFilterAllWithExtension();

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
    private BoMItemRepository bomManager;
    @EJB
    private MaterialRevisionRepository materialRevisionManager;
    @EJB
    private MaterialRevisionBoundaryService materialRevisionService;
    @EJB
    private MaterialRepository materialManager;
    @EJB
    private PlantRepository plantManager;

    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);



    /** Perform BoM import */
    @PermitAll
    public ITaskNodeLog runImport() {
        String importDir = exchangePath + FOLDER_SUB_PATH;

        TaskNodeLog tsk = new TaskNodeLog("import Arrival", "import Arrival in folder " + importDir);

        String[] importFileNames = new File(importDir).list(SIMPLE_XML_FILTER);
        if (importFileNames.length == 0) {
            tsk.finishTask();
            return tsk;
        }


        Unmarshaller unmarshaller;
        try {
            URL fileURL = getClass().getResource(SCHEMA_PATH);
            unmarshaller = JAXBContext.newInstance(ArrivalRootMappingType.class).createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(fileURL);
            unmarshaller.setSchema(schema);
        }
        catch (Exception e) {
            TaskLeafLog tskUnmarshall = tsk.createNewSubTaskLeaf("initializing unmarshaller");
            tskUnmarshall.finishTaskWithError(e);
            tsk.abortTask();
            return tsk;
        }


        List<String> orderedImportFileNames = com.kontron.util.file.FileUtil.getOrderedSAPImportFileNames(importFileNames, ImportType.QDW_ARRIVAL);
        StringBuilder revisionChangeJournal = new StringBuilder();

        // Read all xml files from given path
        for (String importFileName : orderedImportFileNames) {
            importFile(importFileName, tsk, revisionChangeJournal, importDir, unmarshaller);
        }


        tsk.finishTask();
        return tsk;
    }



    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void importFile(String importFileName, TaskNodeLog tsk, StringBuilder revisionChangeJournal, String bomDir, Unmarshaller unmarshaller) {
        logger.info("Lese BoM-Import Datei '{}'", importFileName);

        List<ArrivalMappingType> importedArrivals;
        // parse xml file into list of entities
        try (InputStream is = new FileInputStream(new File(bomDir, importFileName));
                InputStreamReader isr = new InputStreamReader(is, ENCODING)) {
            InputSource isrc = new InputSource(isr);
            isrc.setEncoding(ENCODING);
            ArrivalRootMappingType xmlRoot = (ArrivalRootMappingType) unmarshaller.unmarshal(isrc);
            importedArrivals = xmlRoot.getArrivals();
        }
        catch (Exception e) {
            // add error to response and continue with next file
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
            return;
        }


        Map<String, Plant> plantMap = Plant.asMap(plantManager.findAll());
        Map<String, MovementType> mvtTypeMap = MovementType.asMap(movementTypeManager.findAll());

        List<String> errorList = new ArrayList<>();
        float cnt = 0;
        int progressStep = 5;
        int progress = progressStep;

        int listSize = importedArrivals.size();
        int bulkSize = 2000;
        int bulkFromIdx = 0;
        int bulkToIdx = Math.min(listSize, bulkSize);


        while (bulkToIdx - bulkFromIdx > 0) {
            // aktuell verarbeiteter Batch
            List<ArrivalMappingType> curBatch = importedArrivals.subList(bulkFromIdx, bulkToIdx);
            batchNormalisieren(curBatch);
            curBatch = batchFiltern(curBatch);

            // Es wird eine Map zurück gegeben, in der sämtliche angeforderten SAP-Nummern als key vorhanden sind!
            Map<String, Material> existingMaterialMap = materialManager.findBySAPNumbers(
                    curBatch.stream()
                            .map(ArrivalMappingType::getMaterialSapNumber)
                            .collect(Collectors.toSet()),
                    true);

            Map<String, Supplier> existingSupplierMap = getOrCreateSupplier(curBatch);

            createMissingMvtTypes(mvtTypeMap, curBatch);

            Map<SerNoMatIdResult, SerialObject> existingSerObj = getOrCreateSerObj(curBatch, existingMaterialMap);


            for (ArrivalMappingType arrival : curBatch) {
                if (cnt / listSize * 100 > progress) {
                    progress = ((int) (cnt / listSize * 100) / progressStep) * progressStep;
                    logger.info(progress + "% done");
                    progress += progressStep;
                }

                try {
                    importEntry(arrival, importFileName, revisionChangeJournal, errorList,
                            existingMaterialMap, existingSupplierMap, existingSerObj, plantMap, mvtTypeMap);
                }
                catch (Exception e) {
                    logger.error("failed", e);
                    tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, arrival.getMaterialSapNumber(), e));
                    tsk.abortTask();
                    return;
                }

                cnt++;
            }

            bulkFromIdx = bulkToIdx;
            bulkToIdx = Math.min(listSize, bulkFromIdx + bulkSize);
        } // end for BoMs in Datei
        logger.info("100% done");

        if (errorList.isEmpty()) {
            tsk.addSubTask(new FileImportSuccessfulLog(importFileName, importedArrivals.size()));
        }
        else {
            tsk.addSubTask(new FileImportProcessedWithErrors(importFileName, errorList, importedArrivals.size()));
        }

        // XMLDataImportUtils.moveFileToArchive(FOLDER_SUB_PATH, importFileName);
    }



    private void importEntry(ArrivalMappingType importedArrival, String importFileName, StringBuilder revisionChangeJournal, List<String> errorList,
            Map<String, Material> existingMaterialMap, Map<String, Supplier> existingSupplierMap, Map<SerNoMatIdResult, SerialObject> existingSerObj,
            Map<String, Plant> plantMap, Map<String, MovementType> mvtTypeMap) {

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
            String errorMsg = String.format("Fehler Import Datei '%s': unbekannte plant '%s'.", importFileName, importedArrival.getPlantCode());
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }

        Supplier supplier = existingSupplierMap.get(importedArrival.getSupplierCode());
        MovementType movementType = mvtTypeMap.get(importedArrival.getMovementTypeCode());


        MaterialRevision revision = getOrCreateMatRev(material, plant, importedArrival.getRevisionNumber());
        // HashMap<String, MaterialRevision> revMap = new HashMap<>();
        // String revNo = RevisionUtil.calculateRevNumberBySapRevNumber(importedArrival.getRevisionNumber());
        // MaterialRevision revision = revMap.get(material.getId() + revNo);
        // if (revision == null) {
        // // nicht in Map, also aus DB holen oder neu erzeugen, wenn nicht vorhanden
        // revision = getOrCreateMatRev(material, plant, importedArrival.getRevisionNumber());
        // revMap.put(material.getId() + revNo, revision);
        // }

        SerialObject serialObject = existingSerObj.get(new SerNoMatIdResult(material.getId(), importedArrival.getSerialNumber()));


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



    protected void batchNormalisieren(List<ArrivalMappingType> curBatch) {
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

    protected List<ArrivalMappingType> batchFiltern(List<ArrivalMappingType> curBatch) {
        curBatch = curBatch.stream()
                .filter(importedArrival -> StringUtils.isNotEmpty(importedArrival.getMaterialSapNumber()))
                .filter(importedArrival -> StringUtils.isNotEmpty(importedArrival.getSerialNumber()))
                .filter(importedArrival -> StringUtils.isNotEmpty(importedArrival.getId()))
                .collect(Collectors.toList());
        return curBatch;
    }



    protected MaterialRevision getOrCreateMatRev(Material material, Plant plant, String sapRevNumber) {
        String revNo = RevisionUtil.calculateRevNumberBySapRevNumber(sapRevNumber);

        // MaterialRevision revision;
        // revision = materialRevisionManager.getLastMaterialRevisionByMatNr(material.getMaterialNumber(), plant.getCode(), revNo);
        // if (revision == null) {
        // String[] revAltRev2Rev6 = RevisionUtil.extractRevAltRev2Rev6FromSapRevNumber(sapRevNumber);
        //
        // revision = new MaterialRevision();
        // revision.setMaterial(material);
        // revision.setPlant(plant);
        // revision.setRevisionNumber(revNo);
        // revision.setRev2(revAltRev2Rev6[1]);
        // revision.setRev6(revAltRev2Rev6[2]);
        //
        // revision = materialRevisionManager.persist(revision, true, false);
        // }

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

        revision = materialRevisionManager.persist(revision, true, false);
        material.getRevisions().add(revision);

        return revision;
    }

    protected Map<String, Supplier> getOrCreateSupplier(List<ArrivalMappingType> curBatch) {
        Map<String, Supplier> existingSupplierMap = supplierManager.findByIds(curBatch.stream()
                .map(ArrivalMappingType::getSupplierCode)
                .collect(Collectors.toSet()));

        Map<String, Supplier> missingSupplierMap = existingSupplierMap.entrySet().stream()
                .filter(suppMapEntry -> suppMapEntry.getValue() == null)
                .map(Map.Entry::getKey)
                .map(missingCode -> {
                    Supplier supplier = new Supplier(missingCode);
                    supplier.setName(Constants.DUMMY_NAME_BY_IMPORT + " (" + missingCode + ")");
                    supplier.setComment("Automatically created at arrival import");
                    supplier.setCountry(new Country("DE"));
                    supplier = supplierManager.persist(supplier, true, true);
                    return supplier;
                })
                .collect(Collectors.toMap(Supplier::getCode, Function.identity()));

        existingSupplierMap.putAll(missingSupplierMap);
        return existingSupplierMap;
    }

    protected void createMissingMvtTypes(Map<String, MovementType> mvtTypeMap, List<ArrivalMappingType> curBatch) {
        Map<String, MovementType> missingMvtTypeMap = CollectionUtils.subtract(curBatch.stream()
                .map(ArrivalMappingType::getMovementTypeCode)
                .collect(Collectors.toSet()),
                mvtTypeMap.keySet())
                // -> missing movementTypes
                .stream()
                .map(missingMovementType -> {
                    MovementType mvtType = new MovementType(missingMovementType);
                    mvtType.setActive(true);
                    mvtType = movementTypeManager.persist(mvtType, true, true);
                    return mvtType;
                })
                .collect(Collectors.toMap(MovementType::getCode, Function.identity()));
        mvtTypeMap.putAll(missingMvtTypeMap);
    }

    protected Map<SerNoMatIdResult, SerialObject> getOrCreateSerObj(List<ArrivalMappingType> curBatch, Map<String, Material> existingMaterialMap) {
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

        // TODO Raymund: da 95% der SerialObject nicht existieren, sollte die Anlage geimeinsam mit der Anlage des Arrival in der Hauptschleife durchgeführt werden
        // TODO Dan wiederum ist es nicht nötig, die Map mit angefragten keys und leeren values zurückzugeben
        // Die Map umfasst alle angefragten keys. Werte, die nicht in der Datenbank waren, sind null.
        // Zu diesen Werten ein neues Objekt erstellen und das Ergebnis in die vorhandene Map eintragen.
        Map<SerNoMatIdResult, SerialObject> missingSerObjMap = existingSerObjMap.entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .map(Entry::getKey)
                .map(missingSerObj -> {
                    SerialObject serialObject = new SerialObject();
                    serialObject.setSerialNumber(missingSerObj.serialNumber());
                    serialObject.setMaterial(new Material(missingSerObj.materialId()));
                    serialObject = serialObjectManager.persist(serialObject, true, true);
                    return serialObject;
                })
                .collect(Collectors.toMap(serObj -> new SerNoMatIdResult(serObj.getMaterial().getId(), serObj.getSerialNumber()),
                        Function.identity()));

        existingSerObjMap.putAll(missingSerObjMap);
        return existingSerObjMap;
    }

}
