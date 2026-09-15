package com.kontron.qdw.boundary.service.tracebomimport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.tracebom.neu.NewTraceBoMHeaderType;
import com.kontron.qdw.boundary.service.mapping.tracebom.neu.NewTraceBoMItemType;
import com.kontron.qdw.boundary.service.mapping.tracebom.neu.NewTraceBoMRootType;
import com.kontron.qdw.boundary.service.mapping.tracebom.neu.NewTraceBoMType;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.domain.serial.TraceBoM;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository.MatRevKey;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoMatNrKey;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

/**
 * Import der Trace-BoM-Dateien im neuen Format, die die Fertiger in verschiedenen Verzeichnissen auf dem sftp bereitstellen.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class TBNewImportServiceBean extends AbstractTBImportServiceBean<NewTraceBoMType, NewTraceBoMHeaderType, NewTraceBoMItemType> {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Charset ENCODING = Constants.CHARSET_UTF_8;

    private static final String SCHEMA_PATH = "/schema/";
    private static final String SCHEMA_NAME = "TraceBoM.xsd";


    @EJB
    private SupplierRepository supplierManager;
    @EJB
    private PlantRepository plantManager;
    @EJB
    private MaterialRepository materialManager;
    @EJB
    private MaterialRevisionRepository matRevManager;
    @EJB
    private SerialObjectRepository serObjManager;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext ctx;


    /** @return success */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ImportResult processFile(TaskNodeLog folderTask, File localFolder, File sourceFile, FolderConfig folderConfig) {
        NewTraceBoMRootType trBoMRootImported = null;
        try {
            trBoMRootImported = createLogisticXMLFile(folderTask, localFolder, sourceFile, folderConfig);
        }
        catch (ImportAbortedException e) {
            return ImportResult.fail(e.getTaskLog().toString());
        }
        return saveTraceBoM(sourceFile, trBoMRootImported);
    }


    /** Create file for logistic, based on new xml structure for trace bom xmls */
    private NewTraceBoMRootType createLogisticXMLFile(TaskNodeLog folderTask, File localFolder, File sourceFile, FolderConfig folderConfig)
            throws ImportAbortedException {
        String correctedContent;
        try (BufferedReader input = new BufferedReader(new FileReader(sourceFile, ENCODING))) {
            // Datei vollständig in StringBuilder einlesen, damit der Inhalt vorab korrigiert werden kann
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                content.append(line);
            }

            // Some KDMS files have obscure characters at the beginning!
            correctedContent = content.substring(content.indexOf("<"))
                    .replace("revision_no=\"\"", "revision_no=\"?\"")
                    .replace("part_no=\"\"", "part_no=\"0000-0000\"")
                    .replace("order_no=\"\"", "order_no=\"?\"")
                    .replace("&", "&amp;").replace("&amp;amp;", "&amp;");
        }
        catch (Exception e) { // FileNotFoundException, IOException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("File cannot be opened to correct the content").withTaskDesc("creating logistic xml"));
        }


        SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL fileURL = getClass().getResource(SCHEMA_PATH + SCHEMA_NAME);
        Unmarshaller unmarshaller;
        try {
            Schema schema = sf.newSchema(fileURL);
            unmarshaller = JAXBContext.newInstance(NewTraceBoMRootType.class).createUnmarshaller();
            unmarshaller.setSchema(schema);
        }
        catch (Exception e) { // SAXException, JAXBException, NullPointerException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("Error initializing unmarshaller").withException(e).withTaskDesc("creating logistic xml"));
        }


        NewTraceBoMRootType rootMappingObject;
        try (StringReader inputReader = new StringReader(correctedContent)) {
            rootMappingObject = (NewTraceBoMRootType) unmarshaller.unmarshal(inputReader);
        }
        catch (Exception e) { // JAXBException, UnmarshalException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("Error unmarshalling file").withException(e).withTaskDesc("creating logistic xml"));
        }


        List<NewTraceBoMType> traceBoMs = rootMappingObject.getSerialObjects();
        NewTraceBoMHeaderType header = rootMappingObject.getHeader();

        if (traceBoMs.isEmpty()) {
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("No trace BoMs").withTaskDesc("creating logistic xml"));
        }

        String outputFileName = sourceFile.getName().substring(0, (sourceFile.getName().length() - 4));
        if (!outputFileName.contains(header.getDeliveryNoteNumber())) {
            outputFileName = outputFileName + "_" + header.getDeliveryNoteNumber();
        }
        outputFileName = localFolder.getName() + "_" + outputFileName + ".xml";


        // read first object, in order to get revision number
        String revNo = traceBoMs.getFirst().getRevisionNumber();

        StringBuilder output = new StringBuilder(1024);
        output.append("<?xml version=\"1.0\" standalone=\"yes\" ?>\n");

        // open list
        output.append("<SHIPPING_LISTS>\n<SHIP_LIST>\n");

        // Create header data
        output.append("<HEADER>\n");
        output.append("<LSNR>").append(header.getDeliveryNoteNumber()).append("</LSNR>\n");
        output.append("<LotNr>").append(header.getLotNumber()).append("</LotNr>\n");
        output.append("<CE_Nr>").append(revNo).append("</CE_Nr>\n");
        output.append("<BELEG_Nr>").append(header.getOrderNumber()).append("</BELEG_Nr>\n");
        output.append("</HEADER>\n");

        // Create serObj. data
        output.append("<DATA>\n");
        // Iterate over all serial numbers
        for (NewTraceBoMType traceBoM : traceBoMs) {
            // check serial number field, as Plexus sometimes only fills customer serial number
            if (traceBoM.getSerialNumber().isEmpty() && !traceBoM.getCustomerSerialNumber().isEmpty()) {
                traceBoM.setSerialNumber(traceBoM.getCustomerSerialNumber());
            }

            output.append("<SNRSET>\n");

            output.append("<SNR>").append(traceBoM.getSerialNumber()).append("</SNR>\n");
            if (StringUtils.isEmpty(traceBoM.getCustomerSerialNumber())) {
                output.append("<SNR_CUST/>\n");
            }
            else {
                output.append("<SNR_CUST>").append(traceBoM.getCustomerSerialNumber()).append("</SNR_CUST>\n");
            }
            output.append("<SNR_SYS1/>\n");
            output.append("<SNR_SYS2/>\n");
            output.append("<SNR_SYS3/>\n");
            output.append("<SNR_SYS4/>\n");
            output.append("<SNR_SYS5/>\n");

            output.append("</SNRSET>\n");
        }
        output.append("</DATA>\n");

        // close list
        output.append("</SHIP_LIST>\n</SHIPPING_LISTS>\n");


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderConfig.logisticTraceBoMFolder + File.separator + outputFileName))) {
            writer.write(output.toString());
        }
        catch (Exception e) { // IOException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("Error writing logistic XML file by Trace BoM file (new structure)").withTaskDesc("creating logistic xml"));
        }


        // Logistic file has been created successfully!
        folderTask.addSubTask(new FileImportSuccessfulLog(localFolder.getName() + File.separator + sourceFile.getName(),
                "creating logistic XML", traceBoMs.size()));
        return rootMappingObject;
    }


    /** @return success */
    private ImportResult saveTraceBoM(File sourceFile, NewTraceBoMRootType importedTrBoMRoot) {
        // Import trace BoM
        NewTraceBoMHeaderType trBoMHeaderImported = importedTrBoMRoot.getHeader();
        List<NewTraceBoMType> importedTraceBoMs = importedTrBoMRoot.getSerialObjects();
        batchNormalisieren(importedTraceBoMs);
        List<String> illegalRatioMsgs = new ArrayList<>();

        try {
            Supplier supplier = supplierManager.findById(trBoMHeaderImported.getSupplierCode());
            Plant defaultPlant = plantManager.getReference(DEFAULT_PLANT_CODE);

            LocalDate parsedProdDate = parseToLocalDate(trBoMHeaderImported.getProductionDate());


            // vorab im bulk Revisionen holen
            List<MatRevKey> requestedMatRevs = importedTraceBoMs.stream()
                    .map(so -> new MatRevKey(so.getMaterialNumber(), DEFAULT_PLANT_CODE, so.getRevisionNumber()))
                    .collect(Collectors.toList());
            Map<MatRevKey, MaterialRevision> lastMatRevPerKey = matRevManager.getLastMaterialRevisionByMatNr(requestedMatRevs);
            logger.info("{} von {} Revisionen im bulk geholt", lastMatRevPerKey.size(), requestedMatRevs.size());

            // vorab im bulk SerialObjects holen
            List<SerNoMatNrKey> requestedSerObjs = importedTraceBoMs.stream()
                    .map(so -> new SerNoMatNrKey(so.getSerialNumber(), so.getMaterialNumber()))
                    .collect(Collectors.toList());
            Map<SerNoMatNrKey, SerialObject> serObjPerKey = serObjManager.findBySerialNumberAndMaterialNrBulk(requestedSerObjs);
            logger.info("{} von {} SerObj im bulk geholt", serObjPerKey.size(), requestedSerObjs.size());

            // vorab im bulk Material der BoMItems holen
            List<String> requestedSapNr = importedTraceBoMs.stream()
                    .map(NewTraceBoMType::getTraceBoMItems)
                    .flatMap(Collection::stream)
                    .map(NewTraceBoMItemType::getMaterialSapNumber)
                    .distinct()
                    .toList();
            Map<String, Material> materialPerSAPNr = materialManager.findBySAPNumbers(requestedSapNr, false);
            logger.info("{} von {} Materialien nach SAP-Nr. im bulk geholt", materialPerSAPNr.size(), requestedSapNr.size());


            // Map an bereits persistierten TraceBoM per NewTraceBoMType
            Map<NewTraceBoMType, TraceBoM> persistedBoMPerImportedBoM = new HashMap<>();

            for (NewTraceBoMType importedTraceBoM : importedTraceBoMs) {
                MaterialRevision materialRevision = findMaterialRevision(importedTraceBoM.getMaterialNumber(), importedTraceBoM.getRevisionNumber(),
                        lastMatRevPerKey, defaultPlant);
                Material material = materialRevision.getMaterial();


                SerialObject serialObject = findSerialObject(importedTraceBoM.getSerialNumber(), material, serObjPerKey,
                        importedTraceBoM.getCustomerSerialNumber(), trBoMHeaderImported.getOrderNumber(), parsedProdDate);

                // First we check if the current BoM has been already persisted!
                TraceBoM persistedBoM = persistedBoMPerImportedBoM.get(importedTraceBoM);
                if (persistedBoM != null) {
                    serialObject.setTraceBom(persistedBoM);
                }

                if (persistedBoM == null) {
                    persistedBoM = createTraceBoM(importedTraceBoM, trBoMHeaderImported, supplier, parsedProdDate, materialRevision,
                            illegalRatioMsgs, materialPerSAPNr);
                    serialObject.setTraceBom(persistedBoM);
                    persistedBoMPerImportedBoM.put(importedTraceBoM, persistedBoM);
                }
            }

            em.flush();
            sendIllegalRatioMail(trBoMHeaderImported, illegalRatioMsgs);

            return ImportResult.ok(importedTraceBoMs.size());
        }
        catch (Exception e) {
            ctx.setRollbackOnly();
            String errorMsg = "Error while processing trace file " + sourceFile.getName() + ": " + e.getMessage();
            logger.error(errorMsg);

            return ImportResult.fail(errorMsg);
        }
    }



    private void batchNormalisieren(List<NewTraceBoMType> importedTraceBoMs) {
        importedTraceBoMs.forEach(importedTraceBoM -> {
            // Some CMs only deliver the Rev6 field. In order to find a proper revision the alternative number must be added!
            importedTraceBoM.setRevisionNumber(correctRevNr(importedTraceBoM.getRevisionNumber()));

            importedTraceBoM.setSerialNumber(StringUtil.removeLeadingZeroIfNumber(importedTraceBoM.getSerialNumber()));
            importedTraceBoM.setCustomerSerialNumber(StringUtil.removeLeadingZeroIfNumber(importedTraceBoM.getCustomerSerialNumber()));

            // check serial number field, as Plexus sometimes only fills customer serial number
            if (importedTraceBoM.getSerialNumber().isEmpty() && !importedTraceBoM.getCustomerSerialNumber().isEmpty()) {
                importedTraceBoM.setSerialNumber(importedTraceBoM.getCustomerSerialNumber());
            }

            importedTraceBoM.getTraceBoMItems().forEach(importedTBItem -> {
                importedTBItem.setMaterialSapNumber(importedTBItem.getMaterialSapNumber().replace("-", ""));
            });
        });
    }

}
