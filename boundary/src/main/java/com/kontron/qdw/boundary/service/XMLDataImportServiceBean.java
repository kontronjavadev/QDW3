package com.kontron.qdw.boundary.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.xmlimport.ArrivalRebuildMaterializedServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.ShipmentRebuildMaterializedServiceBean;
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
    private ArrivalRebuildMaterializedServiceBean arrivalRebuildMatServiceBean;
    @EJB
    private ShipmentRebuildMaterializedServiceBean shipmentRebuildMatServiceBean;


    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    private String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);



    @Asynchronous
    @PermitAll
    public void runImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        List<TaskCall> tasks = new ArrayList<>();
        tasks.add(customerImportServiceBean);
        tasks.add(supplierImportServiceBean);
        tasks.add(materialImportServiceBean);
        tasks.add(bomImportServiceBean);

        TaskNodeLog taskSapImport = executeImportTasks(tasks);

        ITaskNodeLog arrivalImportTask = executeAdditionalTask(taskSapImport, arrivalImportServiceBean);
        ITaskNodeLog shipmentImportTask = executeAdditionalTask(taskSapImport, shipmentImportServiceBean);

        if (arrivalImportTask.wasAtLeastOneConcreteTaskPerformed() && arrivalImportTask.isSuccess()) {
            executeAdditionalTask(taskSapImport, arrivalRebuildMatServiceBean);
        }
        if (shipmentImportTask.wasAtLeastOneConcreteTaskPerformed() && shipmentImportTask.isSuccess()) {
            executeAdditionalTask(taskSapImport, shipmentRebuildMatServiceBean);
        }

        finishImport(taskSapImport);
    }



    @Asynchronous
    @PermitAll
    public void runCustomerImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeAdditionalTask(taskSapImport, customerImportServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runSupplierImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeAdditionalTask(taskSapImport, supplierImportServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runMaterialImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeAdditionalTask(taskSapImport, materialImportServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runBoMImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeAdditionalTask(taskSapImport, bomImportServiceBean);

        finishImport(taskSapImport);
    }


    @Asynchronous
    @PermitAll
    public void runArrivalImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        ITaskNodeLog arrivalImportTask = executeAdditionalTask(taskSapImport, arrivalImportServiceBean);

        if (arrivalImportTask.wasAtLeastOneConcreteTaskPerformed() && arrivalImportTask.isSuccess()) {
            executeAdditionalTask(taskSapImport, arrivalRebuildMatServiceBean);
        }

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runRebuildMatArrivalImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        executeAdditionalTask(taskSapImport, arrivalRebuildMatServiceBean);

        finishImport(taskSapImport);
    }

    @Asynchronous
    @PermitAll
    public void runRebuildAggArrivalImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        // executeAdditionalTask(taskSapImport, arrivalRebuildMatServiceBean);

        finishImport(taskSapImport);
    }


    @Asynchronous
    @PermitAll
    public void runShipmentImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        TaskNodeLog taskSapImport = initImport();
        ITaskNodeLog shipmentImportTask = executeAdditionalTask(taskSapImport, shipmentImportServiceBean);
        if (shipmentImportTask.wasAtLeastOneConcreteTaskPerformed() && shipmentImportTask.isSuccess()) {
            executeAdditionalTask(taskSapImport, shipmentRebuildMatServiceBean);
        }

        finishImport(taskSapImport);
    }



    private TaskNodeLog initImport() {
        logger.info("Importing SAP files");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));

        return new TaskNodeLog("SAP import");
    }

    private TaskNodeLog executeImportTasks(List<TaskCall> tasks) {
        TaskNodeLog taskSapImport = initImport();
        tasks.stream()
                .forEach(task -> executeAdditionalTask(taskSapImport, task));

        return taskSapImport;
    }

    private ITaskNodeLog executeAdditionalTask(TaskNodeLog taskSapImport, TaskCall task) {
        TaskNodeLog taskNodeLog = task.initTask();
        taskSapImport.addSubTask(taskNodeLog);
        task.execTask(taskSapImport);
        return taskNodeLog;
    }

    private void finishImport(TaskNodeLog taskSapImport) {
        taskSapImport.finishTask();

        // ist beendet
        long duration = taskSapImport.getEndTime() - taskSapImport.getStartTime();
        logger.info("Finished importing SAP files");

        String subjectText = Constants.APP_ENV + ": SAP import finished " + (taskSapImport.isSuccess() ? "successfully" : "with errors");
        StringBuilder importLog = new StringBuilder();
        importLog.append(subjectText);
        importLog.append(" in ").append(TimeUtil.toBestPracticeStringShort(duration)).append(".\n\n");
        importLog.append("Overview (Details below):\n");
        importLog.append(taskSapImport.getTaskOverviewInformation()).append("\n\n");
        importLog.append("Details:\n");
        importLog.append(taskSapImport.getTaskHierarchicalDetailInformation()).append("\n\n");

        // schicke Informationsmail
        try {
            MailServiceFacade.sendMail(Constants.getMailRecipient(), subjectText, importLog.toString());
        }
        catch (Exception mailException) {
            logger.error("Sending mail after importing SAP giles failed!", mailException);
        }
    }

}
