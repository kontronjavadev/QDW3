package com.kontron.qdw.boundary.service.repairimport;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.SchedulerServiceBean;
import com.kontron.qdw.boundary.service.process.TaskCall;
import com.kontron.qdw.boundary.service.rebuild.SvcMsgRebuildMaterializedDeltaServiceBean;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.util.datetime.TimeUtil;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Import der Repair-Dateien, die der Downloader in verschiedenen Verzeichnissen bereitstellt.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class RepairImportServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String TASKNAME_IMPORT_REBUILD = "Import and rebuild";
    private static final String TASKNAME_IMPORT = "Repair import";
    private static final String TASKNAME_REBUILD = "rebuild materialized tables";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";
    private static final String PROP_XML_ARCHIVE_FOLDER = "sap_archive_folder";


    @EJB
    private SchedulerServiceBean schedulerService;

    @EJB
    private RmaImportServiceBean rmaImportServiceBean;
    @EJB
    private SvcMsgImportServiceBean svcMsgImportServiceBean;
    @EJB
    private SvcMsgRebuildMaterializedDeltaServiceBean svcMsgRebuildServiceBean;


    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    private String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();

        TaskNodeLog taskImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
        ITaskNodeLog rmaImportTask = executeTask(taskImport, rmaImportServiceBean);
        ITaskNodeLog svcMsgImportTask = executeTask(taskImport, svcMsgImportServiceBean);
        taskImport.finishTask();

        TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
        if (rmaImportTask.wasAtLeastOneConcreteTaskPerformed() && rmaImportTask.isSuccess()
                && svcMsgImportTask.wasAtLeastOneConcreteTaskPerformed() && svcMsgImportTask.isSuccess()) {
            executeTask(taskRebuild, svcMsgRebuildServiceBean);
        }
        taskRebuild.finishTask();

        finishImport(mainTask);
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
        ITaskNodeLog rmaImportTask = executeTask(taskImport, rmaImportServiceBean);
        taskImport.finishTask();

        TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
        if (rmaImportTask.wasAtLeastOneConcreteTaskPerformed() && rmaImportTask.isSuccess()) {
            executeTask(taskRebuild, svcMsgRebuildServiceBean);
        }
        taskRebuild.finishTask();

        finishImport(mainTask);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runSvcMsgImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();

        TaskNodeLog taskImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
        ITaskNodeLog svcMsgImportTask = executeTask(taskImport, svcMsgImportServiceBean);
        taskImport.finishTask();

        TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
        if (svcMsgImportTask.wasAtLeastOneConcreteTaskPerformed() && svcMsgImportTask.isSuccess()) {
            executeTask(taskRebuild, svcMsgRebuildServiceBean);
        }
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
        executeTask(taskRebuild, svcMsgRebuildServiceBean);
        finishImport(taskRebuild);
    }



    private TaskNodeLog initImportAndRebuild() {
        logger.info("Importing Repair files and rebuilding materialized tables");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog(TASKNAME_IMPORT_REBUILD);
    }

    @SuppressWarnings("unused")
    private TaskNodeLog initImport() {
        logger.info("Importing Repair files");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog(TASKNAME_IMPORT);
    }

    private TaskNodeLog initRebuild() {
        logger.info("Rebuilding materialized tables");

        return new TaskNodeLog(TASKNAME_REBUILD);
    }


    private ITaskNodeLog executeTask(TaskNodeLog parentTask, TaskCall execInstance) {
        TaskNodeLog taskNodeLog = execInstance.initTask();
        parentTask.addSubTask(taskNodeLog);
        execInstance.execTask(taskNodeLog);
        return taskNodeLog;
    }

    private void finishImport(TaskNodeLog tsk) {
        tsk.finishTask();

        // ist beendet
        long duration = tsk.getEndTime() - tsk.getStartTime();
        logger.info("Finished importing Repair files");

        String subjectText = Constants.APP_ENV + ": Repair import finished " + (tsk.isSuccess() ? "successfully" : "with errors");
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
            logger.error("Sending mail after importing Repair files failed!", mailException);
        }
    }

}
