package com.kontron.qdw.boundary.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.process.TaskCall;
import com.kontron.qdw.boundary.service.rebuild.ArrivalRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ArrivalRebuildMaterializedServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ShipmentArrivalRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ShipmentArrivalRebuildMaterializedServiceBean;
import com.kontron.qdw.boundary.service.rebuild.ShipmentRebuildAggregatedServiceBean;
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

/**
 * Rebuild der materialized und aggregated tables.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class RebuildServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String TASKNAME_REBUILD = "rebuild materialized tables";

    @EJB
    private SchedulerServiceBean schedulerService;

    @EJB
    private ArrivalRebuildMaterializedServiceBean arrivalRebuildMatServiceBean;
    @EJB
    private ArrivalRebuildAggregatedServiceBean arrivalRebuildAggServiceBean;

    @EJB
    private ShipmentArrivalRebuildMaterializedServiceBean shptArrvRebuildMatServiceBean;
    @EJB
    private ShipmentRebuildAggregatedServiceBean shptRebuildAggServiceBean;
    @EJB
    private ShipmentArrivalRebuildAggregatedServiceBean shptArrvRebuildAggServiceBean;



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runRebuildDelta() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initRebuild();

        executeTask(mainTask, arrivalRebuildMatServiceBean);
        executeTask(mainTask, arrivalRebuildAggServiceBean);

        executeTask(mainTask, shptArrvRebuildMatServiceBean);
        executeTask(mainTask, shptRebuildAggServiceBean);
        executeTask(mainTask, shptArrvRebuildAggServiceBean);
        mainTask.finishTask();

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

        executeTask(mainTask, arrivalRebuildMatServiceBean);
        executeTask(mainTask, arrivalRebuildAggServiceBean);

        executeTask(mainTask, shptArrvRebuildMatServiceBean);
        executeTask(mainTask, shptRebuildAggServiceBean);
        executeTask(mainTask, shptArrvRebuildAggServiceBean);
        mainTask.finishTask();

        finishRebuild(mainTask, true);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildMaterializedDelta() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, arrivalRebuildMatServiceBean);

        finishRebuild(taskSapImport, true);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildMaterializedDelta() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, shptArrvRebuildMatServiceBean);

        finishRebuild(taskSapImport, true);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildMaterializedFull() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, arrivalRebuildMatServiceBean);

        finishRebuild(taskSapImport, true);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildMaterializedFull() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, shptArrvRebuildMatServiceBean);

        finishRebuild(taskSapImport, true);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runArrivalRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, arrivalRebuildAggServiceBean);

        finishRebuild(taskSapImport, null);
    }



    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, shptRebuildAggServiceBean);

        finishRebuild(taskSapImport, null);
    }

    @Asynchronous
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void runShptArrvRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, shptArrvRebuildAggServiceBean);

        finishRebuild(taskSapImport, null);
    }



    private TaskNodeLog initRebuild() {
        logger.info("Rebuilding materialized and aggregated tables");

        return new TaskNodeLog(TASKNAME_REBUILD);
    }


    private ITaskNodeLog executeTask(TaskNodeLog taskSapImport, TaskCall task) {
        TaskNodeLog taskNodeLog = task.initTask();
        taskSapImport.addSubTask(taskNodeLog);
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
