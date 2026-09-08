package com.kontron.qdw.boundary.service.tracebomimport;

import static com.kontron.qdw.boundary.service.process.FileUtils.XML_FILE_FILTER;
import static com.kontron.qdw.boundary.service.process.FileUtils.ZIP_FILE_FILTER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.common.filetransfer.FtException;
import com.kontron.common.filetransfer.SftpAccess;
import com.kontron.qdw.boundary.service.SchedulerServiceBean;
import com.kontron.qdw.boundary.service.mapping.tracebom.alt.TraceBoMRootMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebom.neu.NewTraceBoMRootType;
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

    private static final String TASKNAME_IMPORT = "Trace-BoM import";
    private static final String TASKNAME_DOWNLOAD = "Trace-BoM download";
    private static final String TASKNAME_PROCESS = "Trace-BoM process";

    private static final String ROOT_ELEMENT_STOCK_RECEIPT = "STOCK_RECEIPT";
    private static final String ROOT_ELEMENT_TRACE_BOMS = "trace_boms";



    @EJB
    private SchedulerServiceBean schedulerService;
    @EJB
    private TBNewImportServiceBean tbNewService;
    @EJB
    private TBOldImportServiceBean tbOldService;



    /**
     * Holt die Ordner-Liste vom SFTP. Aktuell wird eine fest kodierte Liste verwendet,
     * da der Zugriff nur für die Anzeige der Ordner-Liste ist und dafür zu lange braucht.
     * 
     * @return Ordner-Liste im Grundverzeichnis des SFTP
     * 
     * @throws FtException wenn keine Verbindung zum SFTP aufgebaut werden kann
     * @throws IllegalAccessError bei fehlender Berechtigung
     * @throws IllegalArgumentException wenn der Basisordner leer ist
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> getRootFolders() throws IllegalAccessError, IllegalArgumentException, FtException {
        return getRootFolders(null);
    }


    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runImport() {
        runImport(null);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runImport(List<String> selectedFolders) {
        // Original: "splitNewFiles()"
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImport();

        // ---> == execTask(), nur dass beim normalen Import noch eine Klammer darüber ist
        SftpAccess ftpAccess;
        FolderConfig folderConfig;
        List<String> rootFolders = null;
        try {
            ftpAccess = createSFTPClient();
            folderConfig = setupFolders();
            rootFolders = CollectionUtils.isEmpty(selectedFolders)
                    ? getRootFolders(ftpAccess)
                    : new ArrayList<>(selectedFolders);
        }
        catch (Exception e) {
            TaskLeafLog tskInit = mainTask.createNewSubTaskLeaf("Run import", "initializing sftp access for import");
            tskInit.finishTaskWithError(e);
            mainTask.abortTask();
            return;
        }


        // Beachte: jeder Vertrags-Fertiger hat seinen eigenen Unterordner
        // Alle Dateien runter laden
        TaskNodeLog downloadTask = mainTask.createNewSubTaskNode(TASKNAME_DOWNLOAD);
        for (String ftpManufacturerFolder : rootFolders) {
            try {
                downloadFilesFromFolder(downloadTask, ftpAccess, ftpManufacturerFolder, folderConfig);
            }
            catch (Exception e) {
                downloadTask.addSubTask(new FileImportAbortedWithErrorsLog("folder " + ftpManufacturerFolder, "Error when downloading files"));
                break;
            }
        }


        // alle heruntergeladenen Dateien ggf. entpacken und verarbeiten
        // (Es können auch bereits Dateien im Verzeichnis liegen, die nicht gerade erst runtergeladen wurden.)
        TaskNodeLog processTask = mainTask.createNewSubTaskNode(TASKNAME_PROCESS);
        for (String ftpManufacturerFolder : rootFolders) {
            TaskNodeLog folderTask = processTask.createNewSubTaskNode("folder " + ftpManufacturerFolder);
            try {
                processFilesInFolder(folderTask, ftpAccess, ftpManufacturerFolder, folderConfig);
                folderTask.finishTask();
            }
            catch (Exception e) {
                folderTask.addSubTask(new FileImportAbortedWithErrorsLog("folder " + ftpManufacturerFolder, "Error when processing files"));
                folderTask.abortTask();
                break;
            }
        }


        // <--- execTask()
        finishImport(mainTask);
    }


    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runDownload(List<String> selectedFolders) {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog downloadTask = initDownload();

        // ---> == execTask(), nur dass beim normalen Import noch eine Klammer darüber ist
        SftpAccess ftpAccess;
        FolderConfig folderConfig;
        List<String> rootFolders = null;
        try {
            ftpAccess = createSFTPClient();
            folderConfig = setupFolders();
            rootFolders = CollectionUtils.isEmpty(selectedFolders)
                    ? getRootFolders(ftpAccess)
                    : new ArrayList<>(selectedFolders);
        }
        catch (Exception e) {
            TaskLeafLog tskInit = downloadTask.createNewSubTaskLeaf("initializing sftp access for download");
            tskInit.finishTaskWithError(e);
            downloadTask.abortTask();
            return;
        }


        // Beachte: jeder Vertrags-Fertiger hat seinen eigenen Unterordner
        for (String ftpManufacturerFolder : rootFolders) {
            try {
                downloadFilesFromFolder(downloadTask, ftpAccess, ftpManufacturerFolder, folderConfig);
            }
            catch (Exception e) {
                downloadTask.addSubTask(new FileImportAbortedWithErrorsLog("folder " + ftpManufacturerFolder, "Error when downloading files"));
                break;
            }
        }

        // <--- execTask()
        finishImport(downloadTask);
    }


    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runProcess(List<String> selectedFolders) {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog processTask = initProcess();

        // ---> == execTask(), nur dass beim normalen Import noch eine Klammer darüber ist
        SftpAccess ftpAccess = null;
        FolderConfig folderConfig;
        List<String> rootFolders = null;
        try {
            if (Constants.IS_PROD_ENVIRONMENT) {
                // Zugang zum SFTP wird beim Aufruf von processFilesInFolder nur benötigt,
                // um die Dateien auf dem SFTP zu löschen und das macht nur die Produktivumgebung,
                // oder um ggf. die vollständige Ordnerliste zu holen (wird dort aufgebaut, falls nötig).
                ftpAccess = createSFTPClient();
            }
            folderConfig = setupFolders();
            rootFolders = CollectionUtils.isEmpty(selectedFolders)
                    ? getRootFolders(ftpAccess)
                    : new ArrayList<>(selectedFolders);
        }
        catch (Exception e) {
            TaskLeafLog tskInit = processTask.createNewSubTaskLeaf("initializing sftp access for deleting files after processed");
            tskInit.finishTaskWithError(e);
            processTask.abortTask();
            return;
        }


        // Beachte: jeder Vertrags-Fertiger hat seinen eigenen Unterordner
        for (String ftpManufacturerFolder : rootFolders) {
            TaskNodeLog folderTask = processTask.createNewSubTaskNode("folder " + ftpManufacturerFolder);
            try {
                processFilesInFolder(folderTask, ftpAccess, ftpManufacturerFolder, folderConfig);
                folderTask.finishTask();
            }
            catch (Exception e) {
                folderTask.addSubTask(new FileImportAbortedWithErrorsLog("folder " + ftpManufacturerFolder, "Error when processing files"));
                folderTask.abortTask();
                break;
            }
        }

        // <--- execTask()
        finishImport(processTask);
    }



    private void downloadFilesFromFolder(TaskNodeLog folderTask, SftpAccess ftpAccess, String ftpManufacturerFolder,
            FolderConfig folderConfig)
            throws FtException, SecurityException, IOException {
        if (Constants.IS_PROD_ENVIRONMENT && ftpManufacturerFolder.equalsIgnoreCase("test")) {
            // Ein Test-Ordner für die Testumgebung
            return;
        }

        long startTime = System.currentTimeMillis();

        // Liste an Dateien in Verzeichnis holen (kann Exception werfen)
        List<String> ftpFiles = ftpAccess.getReadableFileList(ftpManufacturerFolder);

        File curLocalFolder = new File(folderConfig.localTraceBoMFolder.getAbsolutePath() + File.separator + ftpManufacturerFolder);
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
        }

        folderTask.addSubTask(new FileImportSuccessfulLog(ftpManufacturerFolder, "downloading", ftpFiles.size(), startTime));
    }

    private void processFilesInFolder(TaskNodeLog folderTask, SftpAccess ftpAccess, String localManufacturerFolder, FolderConfig folderConfig) {
        if (Constants.IS_PROD_ENVIRONMENT && localManufacturerFolder.equalsIgnoreCase("test")) {
            return;
        }

        File curLocalFolder = new File(folderConfig.localTraceBoMFolder.getAbsolutePath() + File.separator + localManufacturerFolder);
        curLocalFolder.mkdirs();


        // erstelle Map aller xml-Dateien in lokalem Verzeichnis
        File[] zipFiles = curLocalFolder.listFiles(ZIP_FILE_FILTER);
        if (zipFiles == null) {
            folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder, "I/O error reading files from directory"));
            folderTask.abortTask();
            return;
        }

        Map<File, List<File>> zipToExtractedFilesMapping = new HashMap<>();
        for (File zipFile : zipFiles) {
            try {
                // zip-Datei entpacken, ...
                List<File> extractedFiles = FileUtils.unzipFile(zipFile, curLocalFolder.getAbsolutePath(),
                        Optional.of(f -> f.getName().toLowerCase().endsWith(".xml")));
                // ... und in einer Map merken
                zipToExtractedFilesMapping.put(zipFile, extractedFiles);
            }
            catch (IOException | SecurityException e) {
                folderTask.addSubTask(new FileImportAbortedWithErrorsLog(zipFile.getAbsolutePath(), "Error when unzipping"));
                folderTask.abortTask();
                return;
            }
        }


        // erstelle Map aller xml-Dateien in lokalem Verzeichnis
        File[] xmlFiles = curLocalFolder.listFiles(XML_FILE_FILTER);
        if (xmlFiles == null) {
            folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder, "I/O error reading files from directory"));
            folderTask.abortTask();
            return;
        }

        // Iterate over all new incoming files and try to split them
        for (File inputFile : xmlFiles) {
            long startTime = System.currentTimeMillis();
            if (FileUtils.isBusy(inputFile)) {
                folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                        "File is currently in usage"));
                continue;
            }

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
                folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                        "File cannot be opened"));
                continue;
            }


            try {
                // ist es überhaupt eine XML-Datei?
                if (!StringUtils.trimToEmpty(xmlSignatureLine).startsWith("<?xml")) {
                    folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            "File is not a valid xml file"));
                    continue;
                }
                // ist zwar eine XML-Datei, aber weder alte, noch neue Trae-BoM-XML-Struktur
                if (!rootElementLine.contains(ROOT_ELEMENT_TRACE_BOMS) && !rootElementLine.contains(ROOT_ELEMENT_STOCK_RECEIPT)) {
                    String errorMsg = String.format("Accepted xml root elements are '%s' and '%s' but root element was '%s'.",
                            ROOT_ELEMENT_TRACE_BOMS, ROOT_ELEMENT_STOCK_RECEIPT, StringUtils.strip(rootElementLine, "<>"));
                    folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            errorMsg));
                    continue;
                }


                ImportResult result;
                // Unterscheidung, ob es sich um eine alte oder neue XML-Struktur handelt
                if (rootElementLine.contains(ROOT_ELEMENT_TRACE_BOMS)) { // neu
                    NewTraceBoMRootType trBoMRootImported = tbNewService.createLogisticXMLFile(
                            folderTask, curLocalFolder, inputFile, folderConfig);
                    result = tbNewService.saveTraceBoM(inputFile, trBoMRootImported);
                }
                else { // ROOT_ELEMENT_STOCK_RECEIPT (alt)
                    TraceBoMRootMappingType trBoMRootImported = tbOldService.createLogisticXMLFile(
                            folderTask, curLocalFolder, inputFile, folderConfig);
                    result = tbOldService.saveTraceBoM(inputFile, trBoMRootImported);
                }
                logger.info("importiert: {}{}{}", curLocalFolder, File.separator, inputFile.getName());


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
                    if (zipFile.exists() && !result.success()) {
                        moveFile(zipFile, new File(folderConfig.errorTraceBoMFolder.getAbsolutePath()
                                + File.separator + localManufacturerFolder + File.separator + zipFile.getName()));
                    }

                    if (filesOfZipFile.isEmpty()) {
                        if (zipFile.exists()) {
                            moveFile(zipFile, new File(folderConfig.backupTraceBoMFolder.getAbsolutePath()
                                    + File.separator + localManufacturerFolder + File.separator + zipFile.getName()));
                        }

                        deleteFtpFile(ftpAccess, localManufacturerFolder, zipFile.getName());
                    }
                }
                else {
                    if (result.success()) {
                        moveFile(inputFile, new File(folderConfig.backupTraceBoMFolder.getAbsolutePath()
                                + File.separator + localManufacturerFolder + File.separator + inputFile.getName()));
                    }
                    else {
                        moveFile(inputFile, new File(folderConfig.errorTraceBoMFolder.getAbsolutePath()
                                + File.separator + localManufacturerFolder + File.separator + inputFile.getName()));
                    }

                    deleteFtpFile(ftpAccess, localManufacturerFolder, inputFile.getName());
                }

                // Information für Mail erstellen
                if (result.success()) {
                    folderTask.addSubTask(new FileImportSuccessfulLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            result.numberEntries(), startTime));
                }
                else {
                    folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            result.errorMessage()));
                }
            }
            catch (ImportAbortedException e) {
                folderTask.addSubTask(e.getTaskLog());
                folderTask.abortTask();
                continue;
            }
        } // end for(fileMap)
    }



    private List<String> getRootFolders(SftpAccess ftpAccess) throws IllegalAccessError, IllegalArgumentException, FtException {
        SftpAccess _ftpAccess = ftpAccess == null
                ? createSFTPClient()
                : ftpAccess;
        List<String> rootFolders = null;
        try {
            rootFolders = _ftpAccess.getReadableDirList(".");
        }
        catch (SecurityException | FtException se) {
            throw new IllegalAccessError("Unable to access root folders");
        }

        if (rootFolders == null || rootFolders.isEmpty()) {
            throw new IllegalArgumentException("No root folders found!");
        }

        return rootFolders;
    }

    private SftpAccess createSFTPClient() throws FtException {
        return new SftpAccess(Constants.getTraceBoMSftpHost(),
                Constants.getTraceBoMSftpAuthUser(),
                Constants.getTraceBoMSftpAuthPassword());
    }

    private FolderConfig setupFolders() throws IllegalAccessError, IllegalArgumentException {
        try {
            FolderConfig folderConfig = new FolderConfig();
            folderConfig.localTraceBoMFolder = new File(Constants.getTraceBoMLocalFolder());
            folderConfig.backupTraceBoMFolder = new File(Constants.getTraceBoMBackupFolder());
            folderConfig.logisticTraceBoMFolder = new File(Constants.getTraceBoMLogisticFolder());
            folderConfig.errorTraceBoMFolder = new File(Constants.getTraceBoMErrorFolder());

            ensureDirectoryExists(folderConfig.localTraceBoMFolder);
            ensureDirectoryExists(folderConfig.backupTraceBoMFolder);
            ensureDirectoryExists(folderConfig.logisticTraceBoMFolder);
            ensureDirectoryExists(folderConfig.errorTraceBoMFolder);
            return folderConfig;
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


    /** Move file to the backup folder of the contract manufacturer */
    private void moveFile(File sourceFile, File targetFile) throws ImportAbortedException {
        try {
            targetFile.mkdirs();
            Files.move(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(
                    sourceFile.getName(), "Could not move splitted file to backup folder: " + e.getMessage()));
        }
    }

    private void deleteFtpFile(SftpAccess ftpAccess, String ftpFolder, String ftpFile) throws ImportAbortedException {
        try {
            if (Constants.IS_PROD_ENVIRONMENT && ftpAccess != null) {
                ftpAccess.deleteFile(ftpFolder, ftpFile);
            }
        }
        catch (FtException e) {
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(
                    ftpFile, "Could not delete file on FTP: " + e.getMessage()));
        }
    }



    private TaskNodeLog initImport() {
        logger.info("Importing Trace-BoM files");

        return new TaskNodeLog(TASKNAME_IMPORT);
    }

    private TaskNodeLog initDownload() {
        logger.info("Downloading Trace-BoM files");

        return new TaskNodeLog(TASKNAME_DOWNLOAD);
    }

    private TaskNodeLog initProcess() {
        logger.info("Processing Trace-BoM files");

        return new TaskNodeLog(TASKNAME_PROCESS);
    }

    private void finishImport(TaskNodeLog tsk) {
        tsk.finishTask();
        // keine Mail schicken, wenn es nichts zu importieren gab oder alles glatt gelaufen ist
        if (!tsk.wasAtLeastOneConcreteTaskPerformed()) {
            logger.info("Finished \"" + tsk.getTaskName() + "\" — no import files");
            return;
        }
        if (tsk.isSuccess()) {
            logger.info("Finished \"" + tsk.getTaskName() + "\" successfully");
            return;
        }

        // ist beendet
        long duration = tsk.getEndTime() - tsk.getStartTime();
        logger.info("Finished \"" + tsk.getTaskName() + "\"");

        String subjectText = Constants.APP_ENV + ": \"" + tsk.getTaskName() + "\" finished " + (tsk.isSuccess() ? "successfully" : "with errors");
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
