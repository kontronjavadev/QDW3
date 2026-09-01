package com.kontron.qdw.boundary.service.tracebomimport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMHeaderType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMRootMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebomneu.NewTraceBoMRootType;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.ExceptionUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import net.sourceforge.jbizmo.commons.server.logging.LoggingDTO;

/**
 * Import der Trace-BoM-Dateien, die die Fertiger in verschiedenen Verzeichnissen auf dem sftp bereitstellen.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class TBOldImportServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Charset ENCODING = Constants.CHARSET_UTF_8;



    // @EJB
    // private SchedulerServiceBean schedulerService;
    // @EJB
    // private RmaImportServiceBean rmaImportServiceBean;
    // @EJB
    // private SvcMsgImportServiceBean svcMsgImportServiceBean;
    // @EJB
    // private SvcMsgRebuildMaterializedDeltaServiceBean svcMsgRebuildServiceBean;


    // private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    // private String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);



    /** Create file for logistic */
    TraceBoMRootMappingType createLogisticXMLFileFromOldStructure(TaskNodeLog folderTask, File localFolder, File sourceFile, FolderConfig folderConfig)
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
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "File cannot be opened to correct the content"));
        }


        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance(TraceBoMRootMappingType.class).createUnmarshaller();
        }
        catch (Exception e) { // JAXBException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error initializing unmarshaller"));
        }


        TraceBoMRootMappingType rootMappingObject;
        try (StringReader inputReader = new StringReader(correctedContent)) {
            rootMappingObject = (TraceBoMRootMappingType) unmarshaller.unmarshal(inputReader);
        }
        catch (Exception e) { // JAXBException, UnmarshalException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error unmarshalling file"));
        }


        List<TraceBoMMappingType> traceBoMs = rootMappingObject.getSerialObjects();
        TraceBoMHeaderType header = rootMappingObject.getHeader();

        if (traceBoMs.isEmpty()) {
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "No trace BoMs"));
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
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error creating logistic XML file by Trace BoM file (old structure)"));
        }


        // Logistic file has been created successfully!
        folderTask.addSubTask(new FileImportSuccessfulLog(localFolder.getName() + File.separator + sourceFile.getName(),
                traceBoMs.size()));
        return rootMappingObject;
    }



    /** @return success */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean saveTraceBoMFromOldStructure(File sourceFile, TraceBoMRootMappingType rootMappingObject) {
        long start = System.currentTimeMillis();
        try {
            // Import trace BoM
            traceBoMImportService.performTraceBoMXMLImport(rootMappingObject);
            return true;
        }
        catch (Exception ex) {
            String messageSubject = "Error while processing content of trace file " + sourceFile.getName() + "!";
            LoggingDTO logEntry = new LoggingDTO(messageSubject, System.currentTimeMillis() - start, ex);
            logger.error(logEntry);

            double size = sourceFile.length() / 1024;

            // Send mail that content was not readable
            String stack = ExceptionUtil.stacktraceToString(ex);
            String messageBody = sourceFile.getPath() + "\nFile size: " + size + "kB\n\n" + ExceptionUtil.getMoreUsefulExceptionMessage(ex)
                    + "\n\nstack trace:\n" + stack;
            QDWHelper.sendErrorMail(messageSubject, messageBody); // send Mail to Admins
            QDWHelper.sendProductOwnerMail(messageSubject, messageBody); // send Mail to Product Owners
            return false;
        }
    }


}
