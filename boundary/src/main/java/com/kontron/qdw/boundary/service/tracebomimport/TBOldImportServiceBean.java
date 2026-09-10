package com.kontron.qdw.boundary.service.tracebomimport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.tracebom.alt.TraceBoMHeaderType;
import com.kontron.qdw.boundary.service.mapping.tracebom.alt.TraceBoMItemMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebom.alt.TraceBoMMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebom.alt.TraceBoMRevisionMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebom.alt.TraceBoMRootMappingType;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.domain.serial.TraceBoM;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.TaskNodeLog;

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
 * Import der Trace-BoM-Dateien im alten Format, die die Fertiger in verschiedenen Verzeichnissen auf dem sftp bereitstellen.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class TBOldImportServiceBean extends AbstractTBImportServiceBean<TraceBoMMappingType, TraceBoMHeaderType, TraceBoMItemMappingType> {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Charset ENCODING = Constants.CHARSET_UTF_8;


    @EJB
    private SupplierRepository supplierManager;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext ctx;


    /** @return success */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ImportResult processFileInFolder(TaskNodeLog folderTask, File localFolder, File sourceFile, FolderConfig folderConfig) {
        TraceBoMRootMappingType trBoMRootImported = null;
        try {
            trBoMRootImported = createLogisticXMLFile(folderTask, localFolder, sourceFile, folderConfig);
        }
        catch (ImportAbortedException e) {
            return ImportResult.fail(e.getTaskLog().toString());
        }
        return saveTraceBoM(sourceFile, trBoMRootImported);
    }



    /** Create file for logistic */
    private TraceBoMRootMappingType createLogisticXMLFile(TaskNodeLog folderTask, File localFolder, File sourceFile,
            FolderConfig folderConfig)
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
            correctedContent = content.substring(content.indexOf("<"));
        }
        catch (Exception e) { // FileNotFoundException, IOException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("File cannot be opened to correct the content").withTaskDesc("creating logistic xml"));
        }


        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance(TraceBoMRootMappingType.class).createUnmarshaller();
        }
        catch (Exception e) { // JAXBException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("Error initializing unmarshaller").withException(e).withTaskDesc("creating logistic xml"));
        }


        TraceBoMRootMappingType rootMappingObject;
        try (StringReader inputReader = new StringReader(correctedContent)) {
            rootMappingObject = (TraceBoMRootMappingType) unmarshaller.unmarshal(inputReader);
        }
        catch (Exception e) { // JAXBException, UnmarshalException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("Error unmarshalling file").withException(e).withTaskDesc("creating logistic xml"));
        }


        List<TraceBoMMappingType> traceBoMs = rootMappingObject.getSerialObjects();
        TraceBoMHeaderType header = rootMappingObject.getHeader();

        if (traceBoMs.isEmpty()) {
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName())
                    .withErrorMsg("No trace BoMs").withTaskDesc("creating logistic xml"));
        }

        String outputFileName = sourceFile.getName().substring(0, (sourceFile.getName().length() - 4));
        if (!outputFileName.contains(header.getDeliveryNoteNumber())) {
            outputFileName = outputFileName + "_" + header.getDeliveryNoteNumber();
        }
        outputFileName = localFolder.getName() + "_" + outputFileName + ".xml";


        StringBuilder output = new StringBuilder(1024);
        output.append("<?xml version=\"1.0\" standalone=\"yes\" ?>\n");

        // open list
        output.append("<SHIPPING_LISTS>\n<SHIP_LIST>\n");

        // Create header data
        output.append("<HEADER>\n");
        output.append("<LSNR>").append(header.getDeliveryNoteNumber()).append("</LSNR>\n");
        output.append("<LotNr>").append(header.getLotNumber()).append("</LotNr>\n");
        output.append("<CE_Nr>").append(header.getMaterialRevision().getRevisionNumber()).append("</CE_Nr>\n");
        output.append("<BELEG_Nr>").append(header.getOrderNumber()).append("</BELEG_Nr>\n");
        output.append("</HEADER>\n");

        // Create serObj. data
        output.append("<DATA>\n");
        // Iterate over all serial numbers
        for (TraceBoMMappingType traceBoM : traceBoMs) {
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
                    .withErrorMsg("Error writing logistic XML file by Trace BoM file (old structure)").withTaskDesc("creating logistic xml"));
        }


        // Logistic file has been created successfully!
        folderTask.addSubTask(new FileImportSuccessfulLog(localFolder.getName() + File.separator + sourceFile.getName(),
                "creating logistic XML", traceBoMs.size()));
        return rootMappingObject;
    }


    /** @return success */
    private ImportResult saveTraceBoM(File sourceFile, TraceBoMRootMappingType trBoMRootImported) {
        // Import trace BoM
        TraceBoMHeaderType trBoMHeaderImported = trBoMRootImported.getHeader();
        TraceBoMRevisionMappingType trBoMRevisionImported = trBoMHeaderImported.getMaterialRevision();
        List<String> illegalRatioMsgs = new ArrayList<>();

        try {
            Supplier supplier = supplierManager.findById(trBoMHeaderImported.getSupplierCode());

            // Some CMs only deliver the Rev6 field. In order to find a proper revision the alternative number must be added!
            String revisionNo = correctRevNr(trBoMRevisionImported.getRevisionNumber());

            MaterialRevision materialRevision = findMaterialRevision(trBoMRevisionImported.getMaterialNumber(), revisionNo);
            Material material = materialRevision.getMaterial();

            LocalDate parsedProdDate = parseToLocalDate(trBoMHeaderImported.getProductionDate());

            Map<TraceBoMMappingType, TraceBoM> persistedBoMPerImportedBoM = new HashMap<>();

            for (TraceBoMMappingType trBoMImported : trBoMRootImported.getSerialObjects()) {
                SerialObject serialObject = findSerialObject(trBoMImported.getSerialNumber(), trBoMImported.getCustomerSerialNumber(),
                        material, trBoMHeaderImported.getOrderNumber(), parsedProdDate);

                // First we check if the current BoM has been already persisted!
                TraceBoM persistedBoM = persistedBoMPerImportedBoM.get(trBoMImported);
                if (persistedBoM != null) {
                    serialObject.setTraceBom(persistedBoM);
                }

                if (persistedBoM == null) {
                    persistedBoM = createTraceBoM(trBoMImported, trBoMHeaderImported, supplier, parsedProdDate, materialRevision, illegalRatioMsgs);
                    serialObject.setTraceBom(persistedBoM);
                    persistedBoMPerImportedBoM.put(trBoMImported, persistedBoM);
                }
            }

            em.flush();
            sendIllegalRatioMail(trBoMHeaderImported, illegalRatioMsgs);

            return ImportResult.ok(trBoMRootImported.getSerialObjects().size());
        }
        catch (Exception e) {
            ctx.setRollbackOnly();
            String errorMsg = "Error while processing trace file " + sourceFile.getName() + ": " + e.getMessage();
            logger.error(errorMsg);

            return ImportResult.fail(errorMsg);
        }
    }

}
