package com.kontron.qdw.boundary.service.sapimport;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.SchedulerServiceBean;
import com.kontron.qdw.boundary.service.analysis.SerialObjectStructureAnalysisServiceBean;
import com.kontron.qdw.boundary.service.process.TaskCall;
import com.kontron.qdw.boundary.service.rebuild.ArrivalRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ArrivalRebuildMaterializedDeltaServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ShipmentArrivalRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ShipmentArrivalRebuildMaterializedDeltaServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ShipmentRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.util.datetime.TimeUtil;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJB;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Import der XML-Dateien, die der Downloader in verschiedenen Verzeichnissen bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Singleton
@Lock(LockType.READ) // Zwingend erforderlich, überschreibt das implizite WRITE-Lock des @Singleton!
public class SapDataImportServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // unser eigener, Thread-sicherer Wächter
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    private static final String TASKNAME_IMPORT_REBUILD = "Import and rebuild";
    private static final String TASKNAME_IMPORT = "SAP import";
    private static final String TASKNAME_ANALYZE = "analyze";
    private static final String TASKNAME_REBUILD = "rebuild materialized tables";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";
    private static final String PROP_XML_ARCHIVE_FOLDER = "sap_archive_folder";


    @EJB
    private SchedulerServiceBean schedulerService;

    @EJB
    private CustomerImportServiceBean customerImportServiceBean;
    @EJB
    private SupplierImportServiceBean supplierImportServiceBean;
    @EJB
    private MaterialImportServiceBean materialImportServiceBean;
    @EJB
    private BoMImportServiceBean bomImportServiceBean;
    @EJB
    private ArrivalImportServiceBean arrivalImportServiceBean;
    @EJB
    private ShipmentImportServiceBean shipmentImportServiceBean;

    @EJB
    private SerialObjectStructureAnalysisServiceBean serialObjectStructureAnalysisServiceBean;
    @EJB
    private ArrivalRebuildMaterializedDeltaServiceBean arrivalRebuildMatServiceBean;
    @EJB
    private ArrivalRebuildAggregatedServiceBean arrivalRebuildAggServiceBean;

    @EJB
    private ShipmentArrivalRebuildMaterializedDeltaServiceBean shptArrvRebuildMatServiceBean;
    @EJB
    private ShipmentRebuildAggregatedServiceBean shptRebuildAggServiceBean;
    @EJB
    private ShipmentArrivalRebuildAggregatedServiceBean shptArrvRebuildAggServiceBean;


    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    private String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);



    /** Asynchroner Einstieg für die Administrationsoberfläche */
    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runImportAsync() {
        runImportSched();
    }

    /** Synchroner Einstieg für den Scheduler */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runImportSched() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();
        try {
            TaskNodeLog taskImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
            executeTask(taskImport, customerImportServiceBean);
            executeTask(taskImport, supplierImportServiceBean);
            executeTask(taskImport, materialImportServiceBean);
            executeTask(taskImport, bomImportServiceBean);

            ITaskNodeLog arrivalImportTask = executeTask(taskImport, arrivalImportServiceBean);
            ITaskNodeLog shipmentImportTask = executeTask(taskImport, shipmentImportServiceBean);

            taskImport.finishTask();
            // vorerst keinen Zwischenbericht senden, da der bisherige Teil ziemlich zügig durch läuft
            // sendeZwischenbericht(taskImport);


            TaskNodeLog taskAnalyze = mainTask.createNewSubTaskNode(TASKNAME_ANALYZE);
            executeTask(taskAnalyze, serialObjectStructureAnalysisServiceBean);
            taskAnalyze.finishTask();


            TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
            if (arrivalImportTask.wasAtLeastOneConcreteTaskPerformed() && arrivalImportTask.isSuccess()) {
                executeTask(taskRebuild, arrivalRebuildMatServiceBean);
                executeTask(taskRebuild, arrivalRebuildAggServiceBean);
            }
            if (shipmentImportTask.wasAtLeastOneConcreteTaskPerformed() && shipmentImportTask.isSuccess()) {
                executeTask(taskRebuild, shptArrvRebuildMatServiceBean);
                executeTask(taskRebuild, shptRebuildAggServiceBean);
                executeTask(taskRebuild, shptArrvRebuildAggServiceBean);
            }
            taskRebuild.finishTask();
        }
        finally {
            isRunning.set(false);
        }

        finishImport(mainTask);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runCustomerImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskImport = initImport();
        try {
            executeTask(taskImport, customerImportServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskImport);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runSupplierImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskImport = initImport();
        try {
            executeTask(taskImport, supplierImportServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskImport);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runMaterialImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskImport = initImport();
        try {
            executeTask(taskImport, materialImportServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskImport);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runBoMImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskImport = initImport();
        try {
            executeTask(taskImport, bomImportServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskImport);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();
        try {
            TaskNodeLog taskImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
            ITaskNodeLog arrivalImportTask = executeTask(taskImport, arrivalImportServiceBean);
            taskImport.finishTask();

            TaskNodeLog taskAnalyze = mainTask.createNewSubTaskNode(TASKNAME_ANALYZE);
            executeTask(taskAnalyze, serialObjectStructureAnalysisServiceBean);
            taskAnalyze.finishTask();

            TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
            if (arrivalImportTask.wasAtLeastOneConcreteTaskPerformed() && arrivalImportTask.isSuccess()) {
                executeTask(taskRebuild, arrivalRebuildMatServiceBean);
                executeTask(taskRebuild, arrivalRebuildAggServiceBean);
            }
            taskRebuild.finishTask();
        }
        finally {
            isRunning.set(false);
        }

        finishImport(mainTask);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildMaterializedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskRebuild = initRebuild();
        try {
            executeTask(taskRebuild, arrivalRebuildMatServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskRebuild);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildAggregatedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskRebuild = initRebuild();
        try {
            executeTask(taskRebuild, arrivalRebuildAggServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskRebuild);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShipmentImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();
        try {
            TaskNodeLog taskImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
            ITaskNodeLog shipmentImportTask = executeTask(taskImport, shipmentImportServiceBean);
            taskImport.finishTask();

            TaskNodeLog taskAnalyze = mainTask.createNewSubTaskNode(TASKNAME_ANALYZE);
            executeTask(taskAnalyze, serialObjectStructureAnalysisServiceBean);
            taskAnalyze.finishTask();

            TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
            if (shipmentImportTask.wasAtLeastOneConcreteTaskPerformed() && shipmentImportTask.isSuccess()) {
                executeTask(taskRebuild, shptArrvRebuildMatServiceBean);
                executeTask(taskRebuild, shptRebuildAggServiceBean);
                executeTask(taskRebuild, shptArrvRebuildAggServiceBean);
            }
            taskRebuild.finishTask();
        }
        finally {
            isRunning.set(false);
        }

        finishImport(mainTask);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildMaterializedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskRebuild = initRebuild();
        try {
            executeTask(taskRebuild, shptArrvRebuildMatServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskRebuild);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptRebuildAggregatedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskRebuild = initRebuild();
        try {
            executeTask(taskRebuild, shptRebuildAggServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskRebuild);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildAggregatedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskRebuild = initRebuild();
        try {
            executeTask(taskRebuild, shptArrvRebuildAggServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskRebuild);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runAnalyzeSerObjStructureAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        // Atomare Prüfung und Setzen des Locks
        if (!isRunning.compareAndSet(false, true)) {
            logger.warn("Job läuft bereits!");
            return;
        }

        TaskNodeLog taskAnalyze = initRebuild();
        try {
            executeTask(taskAnalyze, serialObjectStructureAnalysisServiceBean);
        }
        finally {
            isRunning.set(false);
        }

        finishImport(taskAnalyze);
    }



    private TaskNodeLog initImportAndRebuild() {
        logger.info("Importing SAP files and rebuilding materialized tables");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog(TASKNAME_IMPORT_REBUILD);
    }

    private TaskNodeLog initImport() {
        logger.info("Importing SAP files");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog(TASKNAME_IMPORT);
    }

    private TaskNodeLog initRebuild() {
        logger.info("Rebuilding materialized tables");

        return new TaskNodeLog(TASKNAME_REBUILD);
    }


    private ITaskNodeLog executeTask(TaskNodeLog taskSapImport, TaskCall task) {
        TaskNodeLog taskNodeLog = task.initTask();
        taskSapImport.addSubTask(taskNodeLog);
        task.execTask(taskNodeLog);
        return taskNodeLog;
    }

    @SuppressWarnings("unused")
    private void sendeZwischenbericht(TaskNodeLog tsk) {
        String subjectText = Constants.APP_ENV + ": SAP import part 1 finished " + (tsk.isSuccess() ? "successfully" : "with errors");
        StringBuilder importLog = new StringBuilder();
        importLog.append(subjectText).append(".\n\n");
        importLog.append("First step was importing SAP master data files, next is analyzing and rebuilding materialized tables\n\n");
        importLog.append("Overview (Details below):\n");
        importLog.append(tsk.getTaskOverviewInformation()).append("\n\n");
        importLog.append("Details:\n");
        importLog.append(tsk.getTaskHierarchicalDetailInformation()).append("\n\n");

        // schicke Informationsmail
        try {
            MailServiceFacade.sendMail(Constants.getMailRecipient(), subjectText, importLog.toString());
        }
        catch (Exception mailException) {
            logger.error("Sending mail after importing SAP files failed!", mailException);
        }
    }

    private void finishImport(TaskNodeLog tsk) {
        tsk.finishTask();

        // ist beendet
        long duration = tsk.getEndTime() - tsk.getStartTime();
        logger.info("Finished importing SAP files");

        String subjectText = Constants.APP_ENV + ": SAP import finished " + (tsk.isSuccess() ? "successfully" : "with errors");
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
            logger.error("Sending mail after importing SAP files failed!", mailException);
        }
    }

}
