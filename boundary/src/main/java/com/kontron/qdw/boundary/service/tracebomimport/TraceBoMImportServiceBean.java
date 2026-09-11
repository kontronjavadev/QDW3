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
import com.kontron.qdw.boundary.service.process.FileUtils;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.util.datetime.TimeUtil;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
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
            finishImport(mainTask);
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
            finishImport(downloadTask);
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
            finishImport(processTask);
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
            List<File> extractedFiles;
            try {
                // zip-Datei entpacken, ...
                extractedFiles = FileUtils.unzipFile(zipFile, curLocalFolder.getAbsolutePath(),
                        Optional.of(f -> f.getName().toLowerCase().endsWith(".xml")));
            }
            catch (IOException | SecurityException e) {
                folderTask.addSubTask(new FileImportAbortedWithErrorsLog(zipFile.getAbsolutePath(), "Error when unzipping"));
                folderTask.abortTask();
                return;
            }

            if (extractedFiles.isEmpty()) {
                // zip-Datei hat keine Inhalte, die wir iportieren können -> in Fehler-Ordner schieben
                try {
                    moveFile(zipFile, new File(folderConfig.backupTraceBoMFolder.getAbsolutePath()
                            + File.separator + localManufacturerFolder + File.separator + zipFile.getName()));
                }
                catch (ImportAbortedException e) {
                    folderTask.addSubTask(new FileImportAbortedWithErrorsLog(zipFile.getAbsolutePath(), "Zip file without supported files"));
                }
            }
            else {
                // andernfalls in Map merken
                zipToExtractedFilesMapping.put(zipFile, extractedFiles);
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


            ImportResult importResult = null;
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


                // Unterscheidung, ob es sich um eine alte oder neue XML-Struktur handelt
                if (rootElementLine.contains(ROOT_ELEMENT_TRACE_BOMS)) { // neu
                    importResult = tbNewService.processFileInFolder(folderTask, curLocalFolder, inputFile, folderConfig);
                }
                else { // ROOT_ELEMENT_STOCK_RECEIPT (alt)
                    importResult = tbOldService.processFileInFolder(folderTask, curLocalFolder, inputFile, folderConfig);
                }
                logger.info("importiert: {}{}{}", curLocalFolder, File.separator, inputFile.getName());


                cleanUp(ftpAccess, localManufacturerFolder, folderConfig, zipToExtractedFilesMapping, inputFile, importResult);

                // Information für Mail erstellen
                if (importResult.success()) {
                    folderTask.addSubTask(new FileImportSuccessfulLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            importResult.numberEntries(), startTime));
                }
                else {
                    folderTask.addSubTask(new FileImportAbortedWithErrorsLog(localManufacturerFolder + File.separator + inputFile.getName(),
                            importResult.errorMessage()));
                }
            }
            catch (ImportAbortedException e) {
                // ist nur noch Methode cleanUp(), die eine Exception werfen kann
                folderTask.addSubTask(e.getTaskLog());
                folderTask.abortTask();
            }

            // Sind alle Dateien behandelt, unabhängig davon, ob sie aus einer zip-Datei stammen oder direkt herunter geladen wurden,
            // so sind die Dateien aus der zip-Datei gelöscht und die zip-Datei und die direkt herunter geladenen Dateien archiviert.
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



    private void cleanUp(SftpAccess ftpAccess, String localManufacturerFolder, FolderConfig folderConfig,
            Map<File, List<File>> zipToExtractedFilesMapping, File inputFile, ImportResult importResult) throws ImportAbortedException {
        // check if the file originates from a zip file
        Optional<Entry<File, List<File>>> zipFileEntrySet = zipToExtractedFilesMapping.entrySet().stream()
                .filter(e -> e.getValue().stream()
                        .anyMatch(f -> f.equals(inputFile)))
                .findFirst();

        if (zipFileEntrySet.isPresent()) {
            // Importierte Datei kam aus einer heruntergeladenen zip-Datei.
            File zipFile = zipFileEntrySet.get().getKey();
            List<File> filesOfZipFile = zipFileEntrySet.get().getValue();

            // Importierte Datei aus der Liste des gemerkten Dateiinhalts der zip-Datei entfernen.
            filesOfZipFile.remove(inputFile);
            // Datei physikalisch löschen
            inputFile.delete();

            // Im Falle eines Fehlers, die zip-Datei in den Fehler-Ordner verschieben.
            // Da die Dateien bereits entpackt sind, könnte eine zuvor importierte Datei ebenfalls fehlerhaft
            // gewesen sein und die zip-Datei bereits verschoben sein -> prüfen, ob sie noch da ist
            if (!importResult.success() && zipFile.exists()) {
                moveFile(zipFile, new File(folderConfig.errorTraceBoMFolder.getAbsolutePath()
                        + File.separator + localManufacturerFolder + File.separator + zipFile.getName()));
            }

            // Sind nun alle Dateien aus der zip-Datei verarbeitet, so wird die zip-Datei in das Archiv geschoben,
            // damit die zip-Datei nicht erneut entpackt wird.
            if (filesOfZipFile.isEmpty()) {
                // Es müssen alle Dateien fehlerfrei importiert worden sein, da sie zip-Datei andernfalls im vorherigen Schritt
                // bereits verschoben worden wäre. Ist si also noch da, kommt sie in den Backup-Ordner.
                if (zipFile.exists()) {
                    moveFile(zipFile, new File(folderConfig.backupTraceBoMFolder.getAbsolutePath()
                            + File.separator + localManufacturerFolder + File.separator + zipFile.getName()));
                }

                // Unabhängig davon, ob der Inhalt der zip-Datei fehlerfrei oder fehlerhaft war, kann sie auf dem SFTP gelöscht werden.
                deleteFtpFile(ftpAccess, localManufacturerFolder, zipFile.getName());
            }
        }
        else {
            // Importierte Datei wurde direkt herunter geladen und kam NICHT aus einer heruntergeladenen zip-Datei.
            if (importResult.success()) {
                // Im Erfolgsfall in den Backup-Ordner schieben.
                moveFile(inputFile, new File(folderConfig.backupTraceBoMFolder.getAbsolutePath()
                        + File.separator + localManufacturerFolder + File.separator + inputFile.getName()));
            }
            else {
                // Im Fehlerfall in den Fehler-Ordner schieben.
                moveFile(inputFile, new File(folderConfig.errorTraceBoMFolder.getAbsolutePath()
                        + File.separator + localManufacturerFolder + File.separator + inputFile.getName()));
            }

            deleteFtpFile(ftpAccess, localManufacturerFolder, inputFile.getName());
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
        return init(TASKNAME_IMPORT);
    }

    private TaskNodeLog initDownload() {
        return init(TASKNAME_DOWNLOAD);
    }

    private TaskNodeLog initProcess() {
        return init(TASKNAME_PROCESS);
    }

    private TaskNodeLog init(String taskName) {
        logger.info("\"" + taskName + "\" started");
        return new TaskNodeLog(taskName);
    }

    private void finishImport(TaskNodeLog tsk) {
        tsk.finishTask();
        String baseMsg = "\"" + tsk.getTaskName() + "\" finished";
        // keine Mail schicken, wenn es nichts zu importieren gab
        // Bei QDW gibt es auf oberster Ebene zwei Subtasks, einen für den Download und einen für die Verarbeitung.
        // Bei "process" ist die Ordnerstruktur als Subtask angelegt und der wiederum hat für jede Datei einen weiteren Subtask.
        // Nur wenn es _dort_ nichts zu tun gibt, gibt es wirklich nichts zu tun.
        // Bei "download" gibt es lediglich ordnerspezifische Subtasks, FileImportSuccessfulLog, die dann die Anzahl der heruntergeladenen
        // Dateien hat.
        // Falls der konkrete Vorgang, Download oder Verarbeitung, direkt aus der Administrationsoberfläche
        // gestartet wird, ist dieser Vorgang der oberste Knoten!

        Optional<ITaskNodeLog> downloadTsk = find(tsk, TASKNAME_DOWNLOAD);
        Optional<ITaskNodeLog> processTsk = find(tsk, TASKNAME_PROCESS);
        boolean anyDownloadTaskPerformed = wasAtLeastOneConcreteDownloadTaskPerformed(downloadTsk);
        boolean anyProcessTaskPerformed = wasAtLeastOneConcreteProcessTaskPerformed(processTsk);

        if (!anyDownloadTaskPerformed && !anyProcessTaskPerformed && tsk.isSuccess()) {
            // Es gibt keine Subtasks, die etwas ausgeführt und geloggt haben und der komplette Prozess war erfolgreich.
            // (Es könnte auch einen fehler gegeben haben, noch bevor Subtasks zu den einzelnen Ordner erstellt wurden)
            logger.info(baseMsg + " — no import files");
            return;
        }

        baseMsg += (tsk.isSuccess() ? " successfully" : " with errors");
        logger.info(baseMsg);

        long duration = tsk.getEndTime() - tsk.getStartTime();
        String subjectText = Constants.APP_ENV + ": " + baseMsg;
        StringBuilder importLog = new StringBuilder();
        importLog.append(subjectText);
        importLog.append(" in ").append(TimeUtil.toBestPracticeStringShort(duration)).append(".\n\n");
        importLog.append("Overview (Details below):\n");
        importLog.append(tsk.getTaskOverviewInformation()).append("\n\n");
        importLog.append("Details:\n");
        importLog.append(tsk.getTaskHierarchicalDetailInformation()).append("\n\n");

        List<String> to = List.of(Constants.getMailRecipientLogistic(), Constants.getMailRecipient());
        // schicke Informationsmail
        try {
            MailServiceFacade.sendMail(to, subjectText, importLog.toString());
        }
        catch (Exception mailException) {
            logger.error("Sending mail after importing Trace-BoM files failed!", mailException);
        }
    }


    private Optional<ITaskNodeLog> find(ITaskNodeLog tsk, String name) {
        if (tsk.getTaskName().equals(name)) {
            return Optional.of(tsk);
        }
        return tsk.getSubTasks().stream()
                .filter(ITaskNodeLog.class::isInstance)
                .map(ITaskNodeLog.class::cast)
                .filter(t -> t.getTaskName().equals(name))
                .findFirst();
    }

    private boolean wasAtLeastOneConcreteDownloadTaskPerformed(Optional<ITaskNodeLog> tsk) {
        if (tsk.isEmpty()) {
            return false;
        }
        return tsk.get().getSubTasks().stream()
                .filter(FileImportSuccessfulLog.class::isInstance)
                .map(FileImportSuccessfulLog.class::cast)
                .map(FileImportSuccessfulLog::getNumberEntries)
                .anyMatch(nr -> nr > 0);

        // stream() auf Optional liefert einen "Stream" über das Element des Optionals, mit dem weiter gearbeitet wird.
        // Der Code ist schlecht verständlich: return tsk.stream().map(ITaskNodeLog::getSubTasks).flatMap(Collection::stream)...
    }

    private boolean wasAtLeastOneConcreteProcessTaskPerformed(Optional<ITaskNodeLog> tsk) {
        return tsk.map(ITaskNodeLog::getSubTasks).stream()
                .filter(ITaskNodeLog.class::isInstance)
                .map(ITaskNodeLog.class::cast)
                .anyMatch(ITaskNodeLog::wasAtLeastOneConcreteTaskPerformed);
    }

}
