package com.kontron.qdw.boundary.service.sapimport;

import java.lang.invoke.MethodHandles;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.SchedulerServiceBean;
import com.kontron.qdw.boundary.service.analysis.SerialObjectStructureAnalysisServiceBean;
import com.kontron.qdw.boundary.service.process.ImportResource;
import com.kontron.qdw.boundary.service.process.ResourceLockManagerBean;
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
import jakarta.inject.Inject;
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

    private static final String TASKNAME_IMPORT_REBUILD = "Import and rebuild";
    private static final String TASKNAME_IMPORT = "SAP import";
    private static final String TASKNAME_ANALYZE = "analyze";
    private static final String TASKNAME_REBUILD = "rebuild materialized tables";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";
    private static final String PROP_XML_ARCHIVE_FOLDER = "sap_archive_folder";


    @Inject
    private ResourceLockManagerBean lockManager;


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

        TaskNodeLog mainTask = initImportAndRebuild();
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), mainTask,
                () -> {
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
                });

        finishImport(mainTask);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runCustomerImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskImport = initImport("Customer");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskImport,
                () -> {
                    executeTask(taskImport, customerImportServiceBean);
                });

        finishImport(taskImport);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runSupplierImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskImport = initImport("Supplier");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskImport,
                () -> {
                    executeTask(taskImport, supplierImportServiceBean);
                });

        finishImport(taskImport);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runMaterialImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskImport = initImport("Material");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskImport,
                () -> {
                    executeTask(taskImport, materialImportServiceBean);
                });

        finishImport(taskImport);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runBoMImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskImport = initImport("BoM");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskImport,
                () -> {
                    executeTask(taskImport, bomImportServiceBean);
                });

        finishImport(taskImport);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild("Arrival");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), mainTask,
                () -> {
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
                });

        finishImport(mainTask);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildMaterializedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Arrival-Materialized");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, arrivalRebuildMatServiceBean);
                });

        finishImport(taskRebuild);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildAggregatedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Arrival-Aggregated");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, arrivalRebuildAggServiceBean);
                });

        finishImport(taskRebuild);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShipmentImportAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild("Shipment");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), mainTask,
                () -> {
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
                });

        finishImport(mainTask);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildMaterializedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Shipment-Arrival-Materialized");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, shptArrvRebuildMatServiceBean);
                });

        finishImport(taskRebuild);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptRebuildAggregatedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Shipment-Aggregated");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, shptRebuildAggServiceBean);
                });

        finishImport(taskRebuild);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildAggregatedAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Shipment-Arrival-Aggregated");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, shptArrvRebuildAggServiceBean);
                });

        finishImport(taskRebuild);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runAnalyzeSerObjStructureAsync() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskAnalyze = initRebuild("SerObj-Analysis");
        lockManager.executeLocked(EnumSet.of(ImportResource.SAP_IMPORT), taskAnalyze,
                () -> {
                    executeTask(taskAnalyze, serialObjectStructureAnalysisServiceBean);
                });

        finishImport(taskAnalyze);
    }



    private TaskNodeLog initImportAndRebuild() {
        return initImportAndRebuild(null);
    }

    private TaskNodeLog initImportAndRebuild(String description) {
        logger.info("Importing SAP files and rebuilding materialized tables");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog(TASKNAME_IMPORT_REBUILD, description);
    }

    private TaskNodeLog initImport(String description) {
        logger.info("Importing SAP files");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog(TASKNAME_IMPORT, description);
    }

    private TaskNodeLog initRebuild(String description) {
        logger.info("Rebuilding materialized tables");

        return new TaskNodeLog(TASKNAME_REBUILD, description);
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
