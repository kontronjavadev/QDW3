package com.kontron.qdw.boundary.service.tracebomimport;

import static com.kontron.qdw.boundary.service.process.FileUtils.XML_FILE_FILTER;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.common.filetransfer.FtException;
import com.kontron.common.filetransfer.SftpAccess;
import com.kontron.qdw.boundary.service.SchedulerServiceBean;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMHeaderType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMRootMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebomneu.NewTraceBoMHeaderType;
import com.kontron.qdw.boundary.service.mapping.tracebomneu.NewTraceBoMRootType;
import com.kontron.qdw.boundary.service.mapping.tracebomneu.NewTraceBoMType;
import com.kontron.qdw.boundary.service.process.FileUtils;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.util.datetime.TimeUtil;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import net.sourceforge.jbizmo.commons.server.logging.LoggingDTO;
import net.sourceforge.jbizmo.commons.server.mail.MailServiceException;

/**
 * Import der Trace-BoM-Dateien, die die Fertiger in verschiedenen Verzeichnissen auf dem sftp bereitstellen.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class TraceBoMImportServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Charset ENCODING = Constants.CHARSET_UTF_8;

    // private static final String TASKNAME_IMPORT_REBUILD = "Import and rebuild";
    private static final String TASKNAME_IMPORT = "Trace-BoM import";
    // private static final String TASKNAME_REBUILD = "rebuild materialized tables";

    // private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";
    // private static final String PROP_XML_ARCHIVE_FOLDER = "sap_archive_folder";

    private static final String ROOT_ELEMENT_STOCK_RECEIPT = "STOCK_RECEIPT";
    private static final String ROOT_ELEMENT_TRACE_BOMS = "trace_boms";

    private static final String SCHEMA_PATH = "/schema/";
    private static final String SCHEMA_NAME = "TraceBoM.xsd";



    @EJB
    private SchedulerServiceBean schedulerService;

    // @EJB
    // private RmaImportServiceBean rmaImportServiceBean;
    // @EJB
    // private SvcMsgImportServiceBean svcMsgImportServiceBean;
    // @EJB
    // private SvcMsgRebuildMaterializedDeltaServiceBean svcMsgRebuildServiceBean;


    // private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    // private String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runImport() {
        // Original: "splitNewFiles()"
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImport();

        // ---> == execTask(), nur dass beim normalen Import noch eine Klammer darüber ist
        SftpAccess ftpAccess;
        Folder folder;
        List<String> rootFolders = null;
        try {
            ftpAccess = createSFTPClient(mainTask);
            folder = setupFolders();
            rootFolders = getRootFolders(ftpAccess);
        }
        catch (Exception e) {
            TaskLeafLog tskInit = mainTask.createNewSubTaskLeaf("initializing sftp access for import");
            tskInit.finishTaskWithError(e);
            mainTask.abortTask();
            return;
        }


        // Iterate over all sub-folders of input folder as every contract manufacturer has its own sub-folder!
        try {
            // Eine geworfene Exception führt zu Komplettabbruch
            // Soll nur mit der nächsten Datei oder dem nächsten Hersteller fortgefahren werden,
            // so muss ein Fehler-Task erstellt und zurück gekehrt werden.
            for (String ftpManufacturerFolder : rootFolders) {
                splitNewFilesForFolder(mainTask, ftpAccess, ftpManufacturerFolder, folder);
            }
        }
        catch (Exception e) {
            TaskLeafLog tskProcess = mainTask.createNewSubTaskLeaf("downloading and processing files");
            tskProcess.finishTaskWithError(e);
            mainTask.abortTask();
            return;
        }

        // <--- execTask()
        finishImport(mainTask);
    }



    private void splitNewFilesForFolder(TaskNodeLog mainTask, SftpAccess ftpAccess, String ftpManufacturerFolder, Folder folder)
    // throws FtException, IOException
    {
        if (Constants.IS_PROD_ENVIRONMENT && ftpManufacturerFolder.equalsIgnoreCase("test")) {
            // Ein Test-Ordner für die Testumgebung
            return;
        }


        try {
            // Map, in der die Dateien einer heruntergeladenen zip-Datei aufgelöst sind.
            // Ist die heruntergeladene Datei keine zip-Datei, ist hier auch nichts gelistet.
            Map<File, List<File>> zipToExtractedFilesMapping = downloadAndUnzipFilesForFolder(mainTask, ftpAccess, ftpManufacturerFolder, folder);
            splitFilesInFolder(mainTask, ftpAccess, ftpManufacturerFolder, zipToExtractedFilesMapping, folder);
        }
        catch (Exception e) { // FtException, SecurityException, IOException
            mainTask.addSubTask(new FileImportAbortedWithErrorsLog(ftpManufacturerFolder, e));
            return;
        }
    }


    private Map<File, List<File>> downloadAndUnzipFilesForFolder(TaskNodeLog mainTask, SftpAccess ftpAccess, String ftpManufacturerFolder,
            Folder folder)
            throws FtException, SecurityException, IOException {
        Map<File, List<File>> zipToExtractedFilesMapping = new HashMap<>();

        // Liste an Dateien in Verzeichnis holen (kann Exception werfen)
        List<String> ftpFiles = ftpAccess.getReadableFileList(ftpManufacturerFolder);

        File curLocalFolder = new File(folder.localTraceBoMFolder.getAbsolutePath() + File.separator + ftpManufacturerFolder);
        curLocalFolder.mkdirs();

        // Download files and check if there are new files that are packed and unzip them if necessary!
        for (String ftpFile : ftpFiles) {
            // Dateiinhalt von sftp lesen
            byte[] fileBytes = ftpAccess.getFileContent(ftpManufacturerFolder, ftpFile);

            // Dateiinhalt in lokale Datei schreiben
            File localTraceFile = new File(curLocalFolder.getAbsolutePath() + File.separator + ftpFile);
            try (FileOutputStream fos = new FileOutputStream(localTraceFile)) {
                fos.write(fileBytes);
                fos.flush();
            }

            // wenn es sich um eine zip-Datei handelt, ...
            if (localTraceFile.isFile() && FileUtils.isZipFile(localTraceFile)) {
                // ... vorherige Dateiliste in lokalem Verzeichnis merken, ...
                List<File> filesBefore = Arrays.asList(curLocalFolder.listFiles()).stream()
                        .filter(f -> f.isFile())
                        .collect(Collectors.toList());
                // ... zip-Datei entpacken, ...
                FileUtils.unzipFile(localTraceFile, curLocalFolder.getAbsolutePath());
                // ... neu hinzu gekommene Dateien ermitteln ...
                List<File> extractedFiles = Arrays.asList(curLocalFolder.listFiles()).stream()
                        .filter(f -> f.isFile() && !filesBefore.contains(f))
                        .collect(Collectors.toList());
                // ... und in einer Map merken
                zipToExtractedFilesMapping.put(localTraceFile, extractedFiles);
            }
        }
        return zipToExtractedFilesMapping;
    }

    private void splitFilesInFolder(TaskNodeLog mainTask, SftpAccess ftpAccess, String localManufacturerFolder,
            Map<File, List<File>> zipToExtractedFilesMapping, Folder folder) {
        if (Constants.IS_PROD_ENVIRONMENT && localManufacturerFolder.equalsIgnoreCase("test")) {
            return;
        }

        File curLocalFolder = new File(folder.localTraceBoMFolder.getAbsolutePath() + File.separator + localManufacturerFolder);
        curLocalFolder.mkdirs();


        // erstelle Map aller xml-Dateien in lokalem Verzeichnis
        File[] fileMap = curLocalFolder.listFiles(XML_FILE_FILTER);

        // Iterate over all new incoming files and try to split them
        for (File inputFile : fileMap) {

            String xmlSignatureLine = null;
            String rootElementLine = null;

            // Datei nur für eine erste Analyse öffnen
            try (BufferedReader input = new BufferedReader(new FileReader(inputFile))) {
                xmlSignatureLine = input.readLine();
                rootElementLine = input.readLine();

                if (StringUtils.isEmpty(rootElementLine)) {
                    // für den Fall, dass die Datei keinen Zeilenumbruch hat und somit alles in einer einzigen Zeile steht
                    rootElementLine = xmlSignatureLine;
                }
            }
            catch (Exception e) { // FileNotFoundException, IOException
                mainTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                        "File cannot be opened"));
                continue;
            }


            try {
                // TODO: warum 20 Sek. warten und dann doch nicht verarbeiten?
                // Vor allem ohne try-catch! Im äußeren catch wird einfach abgebrochen.
                if (FileUtils.isBusy(inputFile)) {
                    Thread.sleep(20);
                    continue;
                }


                boolean success = false;

                // ist es überhaupt eine XML-Datei?
                if (!StringUtils.trimToEmpty(xmlSignatureLine).startsWith("<?xml")) {
                    mainTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            "File is not a valid xml file"));
                    continue;
                }

                // Unterscheidung, ob es sich um eine alte oder neue XML-Struktur handelt
                if (rootElementLine.contains(ROOT_ELEMENT_TRACE_BOMS)) { // neu
                    NewTraceBoMRootType newRootMappingObject = createLogisticXMLFileFromNewStructure(mainTask, curLocalFolder, inputFile, folder);
                    success = saveTraceBoMFromNewStructure(inputFile, newRootMappingObject);
                    System.out.println("importiert: " + curLocalFolder + File.separator + inputFile.getName());
                }
                else if (rootElementLine.contains(ROOT_ELEMENT_STOCK_RECEIPT)) { // altS
                    TraceBoMRootMappingType rootMappingObject = createLogisticXMLFileFromOldStructure(mainTask, curLocalFolder, inputFile, folder);
                    success = saveTraceBoMFromNewStructure(inputFile, rootMappingObject);
                    System.out.println("importiert: " + curLocalFolder + File.separator + inputFile.getName());
                }
                // ist XML-Datei, aber weder alte, noch neue Trae-BoM-XML-Struktur
                else {
                    String errorMsg = String.format("Accepted xml root elements are '%s' and '%s' but root element was '%s'.",
                            ROOT_ELEMENT_TRACE_BOMS, ROOT_ELEMENT_STOCK_RECEIPT, StringUtils.strip(rootElementLine, "<>"));
                    mainTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            errorMsg));
                    continue;
                }


                // check if the file originates from a zip file
                Optional<Entry<File, List<File>>> zipFileEntrySet = zipToExtractedFilesMapping.entrySet().stream()
                        .filter(e -> e.getValue().stream()
                                .anyMatch(f -> f.equals(inputFile)))
                        .findFirst();

                if (zipFileEntrySet.isPresent()) {
                    File zipFile = zipFileEntrySet.get().getKey();
                    List<File> filesOfZipFile = zipFileEntrySet.get().getValue();

                    filesOfZipFile.remove(inputFile);
                    inputFile.delete();

                    // the zip file might have been moved by a previous error, so we have to check if still exists
                    if (zipFile.exists() && !success) {
                        moveFile(zipFile, new File(folder.errorTraceBoMFolder.getAbsolutePath() + File.separator + localManufacturerFolder
                                + File.separator + zipFile.getName()));
                    }

                    if (filesOfZipFile.isEmpty()) {
                        if (zipFile.exists()) {
                            moveFile(zipFile, new File(folder.backupTraceBoMFolder.getAbsolutePath() + File.separator + localManufacturerFolder
                                    + File.separator + zipFile.getName()));
                        }

                        deleteFtpFile(ftpAccess, localManufacturerFolder, zipFile.getName());
                    }
                }
                else {
                    if (success) {
                        moveFile(inputFile, new File(folder.backupTraceBoMFolder.getAbsolutePath() + File.separator + localManufacturerFolder
                                + File.separator + inputFile.getName()));
                    }
                    else {
                        moveFile(inputFile, new File(folder.errorTraceBoMFolder.getAbsolutePath() + File.separator + localManufacturerFolder
                                + File.separator + inputFile.getName()));
                    }

                    deleteFtpFile(ftpAccess, localManufacturerFolder, inputFile.getName());
                }

            }
            catch (FtException e) {
                StringWriter stackTraceWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stackTraceWriter));
                String message = "Error while splitting trace files: deleting file on sftp failed!";

                QDWHelper.sendErrorMail(e, message + inputFile != null ? inputFile.getAbsolutePath() : "null");
                return;
            }
            catch (ImportAbortedException e) {
                mainTask.addSubTask(e.getLog());
                continue;
            }
            catch (Exception e) {
                StringWriter stackTraceWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stackTraceWriter));
                String message = "Error while splitting trace files: importing file failed!";

                QDWHelper.sendErrorMail(e, message + inputFile != null ? inputFile.getAbsolutePath() : "null");
                return;
            }
        } // end for(fileMap)
    }


    private SftpAccess createSFTPClient(TaskNodeLog ownTask) throws FtException {
        return new SftpAccess(Constants.getTraceBoMSftpHost(),
                Constants.getTraceBoMSftpAuthUser(),
                Constants.getTraceBoMSftpAuthPassword());
    }

    private Folder setupFolders() throws IllegalAccessError, IllegalArgumentException {
        try {
            Folder folder = new Folder();
            folder.localTraceBoMFolder = new File(Constants.getTraceBoMLocalFolder());
            folder.backupTraceBoMFolder = new File(Constants.getTraceBoMBackupFolder());
            folder.logisticTraceBoMFolder = new File(Constants.getTraceBoMLogisticFolder());
            folder.errorTraceBoMFolder = new File(Constants.getTraceBoMErrorFolder());

            ensureDirectoryExists(folder.localTraceBoMFolder);
            ensureDirectoryExists(folder.backupTraceBoMFolder);
            ensureDirectoryExists(folder.logisticTraceBoMFolder);
            ensureDirectoryExists(folder.errorTraceBoMFolder);
            return folder;
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void ensureDirectoryExists(File dir) throws IllegalAccessError, IllegalArgumentException {
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.canRead()) {
            throw new IllegalAccessError("Unable to access folder: " + dir.getAbsolutePath());
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Is not a folder: " + dir.getAbsolutePath());
        }
    }

    private List<String> getRootFolders(SftpAccess ftpAccess) throws IllegalAccessError, IllegalArgumentException {
        List<String> rootFolders = null;
        try {
            rootFolders = ftpAccess.getReadableDirList(".");
        }
        catch (SecurityException | FtException se) {
            throw new IllegalAccessError("Unable to access root folders");
        }

        if (rootFolders == null || rootFolders.isEmpty()) {
            throw new IllegalArgumentException("No root folders found!");
        }
        return rootFolders;
    }



    /** Create file for logistic, based on new xml structure for trace bom xmls */
    private NewTraceBoMRootType createLogisticXMLFileFromNewStructure(TaskNodeLog mainTask, File localFolder, File sourceFile, Folder folder)
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
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "File cannot be opened to correct the content"));
        }


        SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL fileURL = getClass().getResource(SCHEMA_PATH + getSchemaName());
        Unmarshaller unmarshaller;
        try {
            Schema schema = sf.newSchema(fileURL);
            unmarshaller = JAXBContext.newInstance(NewTraceBoMRootType.class).createUnmarshaller();
            unmarshaller.setSchema(schema);
        }
        catch (Exception e) { // SAXException, JAXBException, NullPointerException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error initializing unmarshaller"));
        }


        NewTraceBoMRootType rootMappingObject;
        try (StringReader inputReader = new StringReader(correctedContent)) {
            rootMappingObject = (NewTraceBoMRootType) unmarshaller.unmarshal(inputReader);
        }
        catch (Exception e) { // JAXBException, UnmarshalException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error unmarshalling file"));
        }


        List<NewTraceBoMType> traceBoMs = rootMappingObject.getSerialObjects();
        NewTraceBoMHeaderType header = rootMappingObject.getHeader();

        if (traceBoMs.isEmpty()) {
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "No trace BoMs"));
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


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(folder.logisticTraceBoMFolder + File.separator + outputFileName))) {
            writer.write(output.toString());
        }
        catch (Exception e) { // IOException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error creating logistic XML file by Trace BoM file (new structure)"));
        }


        // Logistic file has been created successfully!
        mainTask.addSubTask(new FileImportSuccessfulLog(localFolder.getName() + File.separator + sourceFile.getName(),
                traceBoMs.size()));
        return rootMappingObject;
    }


    /** Create file for logistic */
    private TraceBoMRootMappingType createLogisticXMLFileFromOldStructure(TaskNodeLog mainTask, File localFolder, File sourceFile, Folder folder)
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


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(folder.logisticTraceBoMFolder + File.separator + outputFileName))) {
            writer.write(output.toString());
        }
        catch (Exception e) { // IOException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error creating logistic XML file by Trace BoM file (old structure)"));
        }


        // Logistic file has been created successfully!
        mainTask.addSubTask(new FileImportSuccessfulLog(localFolder.getName() + File.separator + sourceFile.getName(),
                traceBoMs.size()));
        return rootMappingObject;
    }


    /** @return success */
    private boolean saveTraceBoMFromNewStructure(File sourceFile, NewTraceBoMRootType rootMappingObject) {
        long start = System.currentTimeMillis();
        try {
            // Import trace BoM
            traceBoMImportService.performTraceBoMXMLImportFromNewStructure(rootMappingObject);
            return true;
        }
        catch (Exception ex) {
            String subject = "Error while processing content of trace file " + sourceFile.getName() + "!";
            LoggingDTO logEntry = new LoggingDTO(subject, System.currentTimeMillis() - start, ex);
            logger.error(logEntry);

            // Send mail that content was not readable
            QDWHelper.sendErrorMail(subject, sourceFile.getPath(), ex);
            return false;
        }
    }

    /** @return success */
    private boolean saveTraceBoMFromNewStructure(File sourceFile, TraceBoMRootMappingType rootMappingObject) {
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



    private TaskNodeLog initImport() {
        logger.info("Importing Trace-BoM files");

        return new TaskNodeLog(TASKNAME_IMPORT);
    }

    private void finishImport(TaskNodeLog tsk) {
        tsk.finishTask();
        // keine Mail schicken, wenn es nichts zu importieren gab oder alles glatt gelaufen ist
        if (!tsk.wasAtLeastOneConcreteTaskPerformed()) {
            logger.info("Finished importing Trace-BoM files — no import files");
            return;
        }
        if (tsk.isSuccess()) {
            logger.info("Finished importing Trace-BoM files successfully");
            return;
        }

        // ist beendet
        long duration = tsk.getEndTime() - tsk.getStartTime();
        logger.info("Finished importing Trace-BoM files");

        String subjectText = Constants.APP_ENV + ": Trace-BoM import finished " + (tsk.isSuccess() ? "successfully" : "with errors");
        StringBuilder importLog = new StringBuilder();
        importLog.append(subjectText);
        importLog.append(" in ").append(TimeUtil.toBestPracticeStringShort(duration)).append(".\n\n");
        importLog.append("Overview (Details below):\n");
        importLog.append(tsk.getTaskOverviewInformation()).append("\n\n");
        importLog.append("Details:\n");
        importLog.append(tsk.getTaskHierarchicalDetailInformation()).append("\n\n");

        // schicke Informationsmail
        try {
            // MailServiceFacade.sendMail(Constants.getMailRecipient(), subjectText, importLog.toString());
            // TODO: erfolgreich importierte Datenen gehen an getMailRecipientLogistic(). Informationsmail jedoch an getMailRecipient().
            MailServiceFacade.sendMail(Constants.getMailRecipientLogistic(), subjectText, importLog.toString());
        }
        catch (Exception mailException) {
            logger.error("Sending mail after importing Trace-BoM files failed!", mailException);
        }
    }

    protected String getSchemaName() {
        return SCHEMA_NAME;
    }



    class Folder {
        File localTraceBoMFolder;
        File backupTraceBoMFolder;
        File errorTraceBoMFolder;
        File logisticTraceBoMFolder;
    }

    class ImportAbortedException extends Exception {

        private static final long serialVersionUID = 2515003477041142545L;
        private final FileImportAbortedWithErrorsLog log;

        ImportAbortedException(FileImportAbortedWithErrorsLog log) {
            this.log = log;
        }

        public FileImportAbortedWithErrorsLog getLog() {
            return log;
        }

    }


    /*
    private void sendErrorMail(String additionalMsgBody) {
        String msgHeader = "Error while splitting trace files";
        logger.info(msgHeader + ": " + additionalMsgBody);
    
        String subjectText = Constants.APP_ENV + ": Trace-BoM import: " + msgHeader;
        StringBuilder importLog = new StringBuilder();
        importLog.append(msgHeader + ": " + additionalMsgBody);
    
        // schicke Informationsmail
        try {
            MailServiceFacade.sendMail(Constants.getMailRecipient(), subjectText, importLog.toString());
        }
        catch (Exception mailException) {
            logger.error("Sending mail after importing Trace-BoM files failed!", mailException);
        }
    }
     */



    /*
    
    private TaskNodeLog initImportAndRebuild() {
        logger.info("Importing Trace-BoM files and rebuilding materialized tables");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));
        
        return new TaskNodeLog(TASKNAME_IMPORT_REBUILD);
    }
    
    @SuppressWarnings("unused")
    
    private TaskNodeLog initRebuild() {
        logger.info("Rebuilding materialized tables");
    
        return new TaskNodeLog(TASKNAME_REBUILD);
    }
    
    
    @SuppressWarnings("unused")
    private ITaskNodeLog executeTask(TaskNodeLog parentTask, TaskCall execInstance) {
        TaskNodeLog taskNodeLog = execInstance.initTask();
        parentTask.addSubTask(taskNodeLog);
        execInstance.execTask(taskNodeLog);
        return taskNodeLog;
    }
    
    
    
    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runRmaImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }
    
        TaskNodeLog mainTask = initImportAndRebuild();
    
        TaskNodeLog taskImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
        // ITaskNodeLog rmaImportTask = executeTask(taskImport, rmaImportServiceBean);
        taskImport.finishTask();
    
        TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
        // if (rmaImportTask.wasAtLeastOneConcreteTaskPerformed() && rmaImportTask.isSuccess()) {
        // executeTask(taskRebuild, svcMsgRebuildServiceBean);
        // }
        taskRebuild.finishTask();
    
        finishImport(mainTask);
    }
    
    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runSvcMsgRebuild() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }
    
        TaskNodeLog taskRebuild = initRebuild();
        // executeTask(taskRebuild, svcMsgRebuildServiceBean);
        finishImport(taskRebuild);
    }
    */

}
