package com.kontron.qdw.boundary.service.tracebomimport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.common.filetransfer.FtException;
import com.kontron.common.filetransfer.SftpAccess;
import com.kontron.qdw.boundary.service.SchedulerServiceBean;
import com.kontron.qdw.boundary.service.process.FileUtils;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.util.datetime.TimeUtil;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import net.sourceforge.jbizmo.commons.server.logging.LoggingDTO;

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

    // private static final String TASKNAME_IMPORT_REBUILD = "Import and rebuild";
    private static final String TASKNAME_IMPORT = "Trace-BoM import";
    // private static final String TASKNAME_REBUILD = "rebuild materialized tables";

    // private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";
    // private static final String PROP_XML_ARCHIVE_FOLDER = "sap_archive_folder";


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
        for (String ftpManufacturerFolder : rootFolders) {
            splitNewFilesForFolder(mainTask, ftpAccess, ftpManufacturerFolder, folder);
        }

        // <--- execTask()
        finishImport(mainTask);
    }



    private void splitNewFilesForFolder(TaskNodeLog mainTask, SftpAccess ftpAccess, String ftpManufacturerFolder, Folder folder) {
        if (Constants.IS_PROD_ENVIRONMENT && ftpManufacturerFolder.equalsIgnoreCase("test")) {
            // Ein Test-Ordner für die Testumgebung
            return;
        }

        Map<File, List<File>> zipToExtractedFilesMapping = new HashMap<>();

        downloadAndUnzipFilesForFolder(mainTask, ftpAccess, ftpManufacturerFolder, zipToExtractedFilesMapping, folder);
        splitFilesInFolder(mainTask, ftpAccess, ftpManufacturerFolder, zipToExtractedFilesMapping, folder);
    }


    private void downloadAndUnzipFilesForFolder(TaskNodeLog mainTask, SftpAccess ftpAccess, String ftpManufacturerFolder,
            Map<File, List<File>> zipToExtractedFilesMapping, Folder folder) {
        // Liste an Dateien in Verzeichnis holen
        List<String> ftpFiles;
        try {
            ftpFiles = ftpAccess.getReadableFileList(ftpManufacturerFolder);
        }
        catch (FtException ftpe) {
            String message = "Error while splitting trace files: reading file list failed!";
            LoggingDTO logEntry = new LoggingDTO(message, System.currentTimeMillis() - start, ftpe);
            logger.error(logEntry);

            StringWriter stackTraceWriter = new StringWriter();
            ftpe.printStackTrace(new PrintWriter(stackTraceWriter));
            // Send email that an error occurred!
            QDWHelper.sendErrorMail(ftpe, message);
            return;
        }
        if (ftpFiles.isEmpty()) {
            return;
        }



        File curLocalFolder = new File(folder.localTraceBoMFolder.getAbsolutePath() + File.separator + ftpManufacturerFolder);
        curLocalFolder.mkdirs();

        try {
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
        }
        catch (Exception e) {
            String message = "Error while splitting trace files: reading file failed!";
            LoggingDTO logEntry = new LoggingDTO(message, System.currentTimeMillis() - start, e);
            logger.error(logEntry);

            StringWriter stackTraceWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stackTraceWriter));
            // Send email that an error occurred!
            QDWHelper.sendErrorMail(e, message);
            return;
        }
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


    private TaskNodeLog initImport() {
        logger.info("Importing Trace-BoM files");

        return new TaskNodeLog(TASKNAME_IMPORT);
    }

    private void finishImport(TaskNodeLog tsk) {
        tsk.finishTask();

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
            MailServiceFacade.sendMail(Constants.getMailRecipient(), subjectText, importLog.toString());
        }
        catch (Exception mailException) {
            logger.error("Sending mail after importing Trace-BoM files failed!", mailException);
        }
    }


    class Folder {
        File localTraceBoMFolder;
        File backupTraceBoMFolder;
        File errorTraceBoMFolder;
        File logisticTraceBoMFolder;
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
