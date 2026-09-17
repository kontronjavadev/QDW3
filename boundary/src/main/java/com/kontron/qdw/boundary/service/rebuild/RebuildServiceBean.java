package com.kontron.qdw.boundary.service.rebuild;

import java.lang.invoke.MethodHandles;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.SchedulerServiceBean;
import com.kontron.qdw.boundary.service.process.ImportResource;
import com.kontron.qdw.boundary.service.process.ResourceLockManagerBean;
import com.kontron.qdw.boundary.service.process.TaskCall;
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

/**
 * Rebuild der materialized und aggregated tables.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Singleton
@Lock(LockType.READ) // Zwingend erforderlich, überschreibt das implizite WRITE-Lock des @Singleton!
public class RebuildServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String TASKNAME_REBUILD = "rebuild materialized tables";


    @Inject
    private ResourceLockManagerBean lockManager;


    @EJB
    private SchedulerServiceBean schedulerService;

    // Materialized: SAP-Import
    @EJB
    private ArrivalRebuildMaterializedDeltaServiceBean arrivalRebuildMatDeltaServiceBean;
    @EJB
    private ShipmentArrivalRebuildMaterializedDeltaServiceBean shptArrvRebuildMatDeltaServiceBean;

    // Materialized: wird nicht von scheduler aufgerufen
    @EJB
    private ArrivalRebuildMaterializedFullServiceBean arrivalRebuildMatFullServiceBean;
    @EJB
    private ShipmentArrivalRebuildMaterializedFullServiceBean shptArrvRebuildMatFullServiceBean;

    // Aggregated: SAP-Import
    @EJB
    private ArrivalRebuildAggregatedServiceBean arrivalRebuildAggServiceBean;
    @EJB
    private ShipmentRebuildAggregatedServiceBean shptRebuildAggServiceBean;
    @EJB
    private ShipmentArrivalRebuildAggregatedServiceBean shptArrvRebuildAggServiceBean;

    // Materialized: Repair
    @EJB
    private SvcMsgRebuildMaterializedDeltaServiceBean svcMsgRebuildMatDeltaServiceBean;

    // Materialized: wird nicht von scheduler aufgerufen
    @EJB
    private SvcMsgRebuildMaterializedFullServiceBean svcMsgRebuildMatFullServiceBean;



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runRebuildDelta() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initRebuild();
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.SAP_IMPORT, ImportResource.REPAIR_IMPORT), mainTask,
                () -> {
                    executeTask(mainTask, arrivalRebuildMatDeltaServiceBean);
                    executeTask(mainTask, shptArrvRebuildMatDeltaServiceBean);

                    executeTask(mainTask, arrivalRebuildAggServiceBean);
                    executeTask(mainTask, shptRebuildAggServiceBean);
                    executeTask(mainTask, shptArrvRebuildAggServiceBean);

                    executeTask(mainTask, svcMsgRebuildMatDeltaServiceBean);
                });

        finishRebuild(mainTask, true);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runRebuildFull() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initRebuild();
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.SAP_IMPORT), mainTask,
                () -> {
                    executeTask(mainTask, arrivalRebuildMatFullServiceBean);
                    executeTask(mainTask, shptArrvRebuildMatFullServiceBean);

                    executeTask(mainTask, arrivalRebuildAggServiceBean);
                    executeTask(mainTask, shptRebuildAggServiceBean);
                    executeTask(mainTask, shptArrvRebuildAggServiceBean);

                    executeTask(mainTask, svcMsgRebuildMatFullServiceBean);
                });

        finishRebuild(mainTask, true);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildMaterializedDelta() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Arrival-Materialized");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, arrivalRebuildMatDeltaServiceBean);
                });

        finishRebuild(taskRebuild, true);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildMaterializedDelta() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Shipment-Arrival-Materialized");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, shptArrvRebuildMatDeltaServiceBean);
                });

        finishRebuild(taskRebuild, true);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runSvcMsgRebuildMaterializedDelta() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Service Messages");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.REPAIR_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, svcMsgRebuildMatDeltaServiceBean);
                });

        finishRebuild(taskRebuild, true);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildMaterializedFull() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Arrival-Materialized");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD), taskRebuild,
                () -> {
                    executeTask(taskRebuild, arrivalRebuildMatFullServiceBean);
                });

        finishRebuild(taskRebuild, true);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildMaterializedFull() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Shipment-Arrival-Materialized");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD), taskRebuild,
                () -> {
                    executeTask(taskRebuild, shptArrvRebuildMatFullServiceBean);
                });

        finishRebuild(taskRebuild, true);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runSvcMsgRebuildMaterializedFull() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Service Messages");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD), taskRebuild,
                () -> {
                    executeTask(taskRebuild, svcMsgRebuildMatFullServiceBean);
                });

        finishRebuild(taskRebuild, true);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Arrival-Aggregated");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, arrivalRebuildAggServiceBean);
                });

        finishRebuild(taskRebuild, null);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Shipment-Aggregated");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, shptRebuildAggServiceBean);
                });

        finishRebuild(taskRebuild, null);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskRebuild = initRebuild("Shipment-Arrival-Aggregated");
        lockManager.executeLocked(EnumSet.of(ImportResource.REBUILD, ImportResource.SAP_IMPORT), taskRebuild,
                () -> {
                    executeTask(taskRebuild, shptArrvRebuildAggServiceBean);
                });

        finishRebuild(taskRebuild, null);
    }



    private TaskNodeLog initRebuild() {
        return initRebuild(null);
    }

    private TaskNodeLog initRebuild(String description) {
        logger.info("Rebuilding materialized and aggregated tables");

        return new TaskNodeLog(TASKNAME_REBUILD, description);
    }


    private ITaskNodeLog executeTask(TaskNodeLog taskRebuild, TaskCall task) {
        TaskNodeLog taskNodeLog = task.initTask();
        taskRebuild.addSubTask(taskNodeLog);
        task.execTask(taskNodeLog);
        return taskNodeLog;
    }

    private void finishRebuild(TaskNodeLog tsk, Boolean delta) {
        tsk.finishTask();

        // ist beendet
        long duration = tsk.getEndTime() - tsk.getStartTime();
        logger.info("Finished rebuilding materialized and aggregated tables" + deltaToString(delta));

        String subjectText = Constants.APP_ENV + ": rebuilding tables" + deltaToString(delta) + " finished "
                + (tsk.isSuccess() ? "successfully" : "with errors");
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



    private String deltaToString(Boolean delta) {
        if (delta == null) {
            return "";
        }
        return " (" + (delta ? "delta" : "full") + ")";
    }

}
