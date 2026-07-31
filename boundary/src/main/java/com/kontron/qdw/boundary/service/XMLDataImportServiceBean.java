package com.kontron.qdw.boundary.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.xmlimport.ArrivalRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.ArrivalRebuildMaterializedServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.SerialObjectStructureAnalysisServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.ShipmentArrivalRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.ShipmentArrivalRebuildMaterializedServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.ShipmentRebuildAggregatedServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLArrivalImportServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLBoMImportServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLCustomerImportServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLMaterialImportServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLShipmentImportServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLSupplierImportServiceBean;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.util.datetime.TimeUtil;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Import der XML-Dateien, die der Downloader in verschiedenen Verzeichnissen bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLDataImportServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String TASKNAME_IMPORT = "SAP import";
    private static final String TASKNAME_ANALYZE = "analyze";
    private static final String TASKNAME_REBUILD = "rebuild materialized tables";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";
    private static final String PROP_XML_ARCHIVE_FOLDER = "sap_archive_folder";


    @EJB
    private SchedulerServiceBean schedulerService;

    @EJB
    private XMLCustomerImportServiceBean customerImportServiceBean;
    @EJB
    private XMLSupplierImportServiceBean supplierImportServiceBean;
    @EJB
    private XMLMaterialImportServiceBean materialImportServiceBean;
    @EJB
    private XMLBoMImportServiceBean bomImportServiceBean;
    @EJB
    private XMLArrivalImportServiceBean arrivalImportServiceBean;
    @EJB
    private XMLShipmentImportServiceBean shipmentImportServiceBean;

    @EJB
    private SerialObjectStructureAnalysisServiceBean serialObjectStructureAnalysisServiceBean;
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


    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    private String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);



    @Asynchronous
    @PermitAll
    public void runImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();
        TaskNodeLog taskSapImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);

        executeTask(taskSapImport, customerImportServiceBean);
        executeTask(taskSapImport, supplierImportServiceBean);
        executeTask(taskSapImport, materialImportServiceBean);
        executeTask(taskSapImport, bomImportServiceBean);

        ITaskNodeLog arrivalImportTask = executeTask(taskSapImport, arrivalImportServiceBean);
        ITaskNodeLog shipmentImportTask = executeTask(taskSapImport, shipmentImportServiceBean);

        taskSapImport.finishTask();
        sendeZwischenbericht(taskSapImport);


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

        finishImport(mainTask);
    }



    @Asynchronous
    @PermitAll
    public void runCustomerImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeTask(taskSapImport, customerImportServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runSupplierImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeTask(taskSapImport, supplierImportServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runMaterialImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeTask(taskSapImport, materialImportServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runBoMImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeTask(taskSapImport, bomImportServiceBean);

        finishImport(taskSapImport);
    }



    @Asynchronous
    @PermitAll
    public void runArrivalImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();
        TaskNodeLog taskSapImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
        taskSapImport.finishTask();

        ITaskNodeLog arrivalImportTask = executeTask(taskSapImport, arrivalImportServiceBean);

        TaskNodeLog taskAnalyze = mainTask.createNewSubTaskNode(TASKNAME_ANALYZE);
        executeTask(taskAnalyze, serialObjectStructureAnalysisServiceBean);
        taskAnalyze.finishTask();

        TaskNodeLog taskRebuild = mainTask.createNewSubTaskNode(TASKNAME_REBUILD);
        if (arrivalImportTask.wasAtLeastOneConcreteTaskPerformed() && arrivalImportTask.isSuccess()) {
            executeTask(taskRebuild, arrivalRebuildMatServiceBean);
            executeTask(taskRebuild, arrivalRebuildAggServiceBean);
        }
        taskRebuild.finishTask();

        finishImport(mainTask);
    }

    @Asynchronous
    @PermitAll
    public void runArrivalRebuildMaterialized() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, arrivalRebuildMatServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runArrivalRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, arrivalRebuildAggServiceBean);

        finishImport(taskSapImport);
    }



    @Asynchronous
    @PermitAll
    public void runShipmentImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog mainTask = initImportAndRebuild();
        TaskNodeLog taskSapImport = mainTask.createNewSubTaskNode(TASKNAME_IMPORT);
        taskSapImport.finishTask();

        ITaskNodeLog shipmentImportTask = executeTask(taskSapImport, shipmentImportServiceBean);

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

        finishImport(mainTask);
    }

    @Asynchronous
    @PermitAll
    public void runShptArrvRebuildMaterialized() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, shptArrvRebuildMatServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runShptRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, shptRebuildAggServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runShptArrvRebuildAggregated() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, shptArrvRebuildAggServiceBean);

        finishImport(taskSapImport);
    }



    @Asynchronous
    @PermitAll
    public void runAnalyzeSerObjStructure() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initRebuild();
        executeTask(taskSapImport, serialObjectStructureAnalysisServiceBean);

        finishImport(taskSapImport);
    }



    private TaskNodeLog initImportAndRebuild() {
        logger.info("Importing SAP files and rebuilding materialized tables");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog("Import and rebuild");
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

    private void sendeZwischenbericht(TaskNodeLog tsk) {
        String subjectText = Constants.APP_ENV + ": SAP import finished " + (tsk.isSuccess() ? "successfully" : "with errors");
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

        String subjectText = Constants.APP_ENV + ": SAP import part 1 finished " + (tsk.isSuccess() ? "successfully" : "with errors");
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
