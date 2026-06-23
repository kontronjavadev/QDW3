package com.kontron.qdw.boundary.service.xmlimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.kontron.constants.file.FileType;
import com.kontron.qdw.boundary.material.MaterialRevisionBoundaryService;
import com.kontron.qdw.boundary.service.XMLDataImportUtils;
import com.kontron.qdw.boundary.service.mapping.arrival.ArrivalMappingType;
import com.kontron.qdw.boundary.service.mapping.arrival.ArrivalRootMappingType;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Country;
import com.kontron.qdw.domain.base.MovementType;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.domain.material.BoMItem;
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
import com.kontron.util.datetime.DateUtil;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportProcessedWithErrors;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.ExceptionUtil;
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


        Map<String, Plant> plants = Plant.asMap(plantManager.findAll());
        Map<String, MovementType> movementTypes = MovementType.asMap(movementTypeManager.findAll());

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

            // Map<String, Material> existingMaterialMap = cacheMaterial(curBatch, ArrivalMappingType::getMaterialNumber, true);


            for (ArrivalMappingType arrival : curBatch) {
                if (cnt / listSize * 100 > progress) {
                    progress = ((int) (cnt / listSize * 100) / progressStep) * progressStep;
                    logger.info(progress + "% done");
                    progress += progressStep;
                }

                try {
                    importEntry(arrival, importFileName, revisionChangeJournal, errorList, existingMaterialMap, plants, movementTypes);
                }
                catch (Exception e) {
                    logger.error("failed", e);
                    String sapNumber = StringUtil.removeLeadingZero(arrival.getMaterialNumber());
                    tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, sapNumber, e));
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

        XMLDataImportUtils.moveFileToArchive(FOLDER_SUB_PATH, importFileName);
    }



    private void importEntry(ArrivalMappingType mappingObject, String importFileName, StringBuilder revisionChangeJournal, List<String> errorList,
            Map<String, Material> existingMaterialMap, Map<String, Plant> plants, Map<String, MovementType> movementTypes) {

        // vor Weiterverarbeitung um führende Nullen kürzen
        // TODO: vorab für alle des batch anwenden und ungültige überspringen, bzw. aus Liste entfernen
        mappingObject.setMaterialSapNumber(StringUtil.removeLeadingZero(mappingObject.getMaterialSapNumber()));
        mappingObject.setOrderNumber(StringUtil.removeLeadingZero(mappingObject.getOrderNumber()));
        mappingObject.setSerialNumber(StringUtil.removeLeadingZeroIfNumber(mappingObject.getSerialNumber()));
        mappingObject.setSerialObjectMaterialNumber(mappingObject.getMaterialSapNumber());
        mappingObject.setSupplierCode(StringUtil.removeLeadingZero(mappingObject.getSupplierCode()));
        if (mappingObject.getSupplierCode().isEmpty()) {
            mappingObject.setSupplierCode(UNKNOWN_SUPPLIER);
        }

        if (StringUtils.isEmpty(mappingObject.getMaterialSapNumber())
                || StringUtils.isEmpty(mappingObject.getSerialNumber())
                || StringUtils.isEmpty(mappingObject.getId())) {
            // continue;
            return;
        }

        String transactionIdStr = mappingObject.getId().replaceAll("[A-Z]", "");
        if (StringUtils.isEmpty(transactionIdStr)) {
            return;
        }

        Long transactionId = Long.parseLong(transactionIdStr);
        if (arrivalManager.findById(transactionId) != null) {
            // Arrival wurde bereits angelegt
            return;
        }


        // TODO: vorab Material des batch holen
        Material material = materialManager.findBySapNumber(mappingObject.getMaterialSapNumber());
        if (material == null) {
            String sapNumber = mappingObject.getMaterialSapNumber();
            // String materialNumber = sapNumber.substring(0, 4) + "-" + sapNumber.substring(4);
            String errorMsg = String.format(
                    "Fehler Import Datei '%s': kein Material zu SAP-Nummer '%s'"
                            // + " oder Materialnummer '%s'"
                            + " vorhanden.",
                    importFileName, sapNumber
            // , materialNumber
            );
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }


        // TODO: vorab Supplier des batch holen
        // nicht existierende Supplier werden angelegt und TODO: in die geholte Map gepackt!
        HashMap<String, Supplier> newSupplierMap = new HashMap<>();
        Supplier supplier;
        if (newSupplierMap.containsKey(mappingObject.getSupplierCode())) {
            supplier = newSupplierMap.get(mappingObject.getSupplierCode());
        }
        else {
            supplier = supplierManager.findById(mappingObject.getSupplierCode());
        }

        if (supplier == null) {
            supplier = new Supplier(mappingObject.getSupplierCode());
            supplier.setName(Constants.DUMMY_NAME_BY_IMPORT + " (" + supplier.getCode() + ")");
            supplier.setComment("Automatically created at arrival import");
            supplier.setCountry(new Country("DE"));

            supplier = supplierManager.persist(supplier, true, true);
            newSupplierMap.put(supplier.getCode(), supplier);
        }


        Plant plant = plants.get(mappingObject.getPlantCode());
        if (plant == null) {
            String errorMsg = String.format("Fehler Import Datei '%s': unbekannte plant '%s'.", importFileName, mappingObject.getPlantCode());
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }


        MovementType movementType = movementTypes.get(mappingObject.getMovementTypeCode());
        if (movementType == null) {
            movementType = new MovementType(mappingObject.getMovementTypeCode());
            movementType.setActive(true);
            movementType = movementTypeManager.persist(movementType, true, true);
            movementTypes.put(movementType.getCode(), movementType);
        }

        // TODO: Konzept überlegen. Wie wurde das bislang gemacht? Einzeln? Bulk? Revisionen mit Material holen?
        HashMap<String, MaterialRevision> revMap = new HashMap<>();
        String revNo = RevisionUtil.calculateRevNumberBySapRevNumber(mappingObject.getRevisionNumber());
        MaterialRevision revision = revMap.get(material.getId() + revNo);
        if (revision == null) {
            // nicht in Map, also aus DB holen oder neu erzeugen, wenn nicht vorhanden
            revision = getOrCreateMatRev(material, plant, mappingObject.getRevisionNumber());
            revMap.put(material.getId() + revNo, revision);
        }

        SerialObject serialObject = serialObjectManager.findBySerialNumberAndMaterialId(mappingObject.getSerialNumber(), material);
        if (serialObject == null) {
            serialObject = new SerialObject();
            serialObject.setSerialNumber(mappingObject.getSerialNumber());
            serialObject.setMaterial(material);
            serialObject = serialObjectManager.persist(serialObject, false, false, false);
        }


        Arrival arrival = new Arrival();
        arrival.setId(transactionId);
        arrival.setPlant(plant);
        arrival.setArrivalDate(LocalDate.parse(mappingObject.getArrivalDate())); // Datum im ISO-8601-Format
        arrival.setSerialObject(serialObject);
        arrival.setMaterialRevision(revision);
        arrival.setMovementType(movementType);
        arrival.setSupplier(supplier);
        arrival.setOrderNumber(mappingObject.getOrderNumber());
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



    protected MaterialRevision getOrCreateMatRev(Material material, Plant plant, String sapRevNumber) {
        String revNo = RevisionUtil.calculateRevNumberBySapRevNumber(sapRevNumber);

        MaterialRevision revision;
        revision = materialRevisionManager.getLastMaterialRevisionByMatNr(material.getMaterialNumber(), plant.getCode(), revNo);
        if (revision == null) {
            String[] revAltRev2Rev6 = RevisionUtil.extractRevAltRev2Rev6FromSapRevNumber(sapRevNumber);

            revision = new MaterialRevision();
            revision.setMaterial(material);
            revision.setPlant(plant);
            revision.setRevisionNumber(revNo);
            revision.setRev2(revAltRev2Rev6[1]);
            revision.setRev6(revAltRev2Rev6[2]);

            revision = materialRevisionManager.persist(revision, true, false);
        }
        return revision;
    }

}
