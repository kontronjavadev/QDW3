package com.kontron.qdw.boundary.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.xmlimport.XMLBoMImportServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLCustomerImportServiceBean;
import com.kontron.qdw.boundary.service.xmlimport.XMLMaterialImportServiceBean;
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


    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    private String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);



    @Asynchronous
    @PermitAll
    public void runImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        List<TaskCall> tasks = new ArrayList<>();
        tasks.add(customerImportServiceBean::runImport);
        tasks.add(supplierImportServiceBean::runImport);
        tasks.add(materialImportServiceBean::runImport);
        tasks.add(bomImportServiceBean::runImport);

        runImport(tasks);
    }



    @Asynchronous
    @PermitAll
    public void runCustomerImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        List<TaskCall> tasks = new ArrayList<>();
        tasks.add(customerImportServiceBean::runImport);

        runImport(tasks);
    }

    @Asynchronous
    @PermitAll
    public void runSupplierImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        List<TaskCall> tasks = new ArrayList<>();
        tasks.add(supplierImportServiceBean::runImport);

        runImport(tasks);
    }

    @Asynchronous
    @PermitAll
    public void runMaterialImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        List<TaskCall> tasks = new ArrayList<>();
        tasks.add(materialImportServiceBean::runImport);

        runImport(tasks);
    }

    @Asynchronous
    @PermitAll
    public void runBoMImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        List<TaskCall> tasks = new ArrayList<>();
        tasks.add(bomImportServiceBean::runImport);

        runImport(tasks);
    }

    @Asynchronous
    @PermitAll
    public void runArrivalImport() {
        if (!schedulerService.isExecuteImport()) {
            return;
        }

        List<TaskCall> tasks = new ArrayList<>();
        // tasks.add(forecastImportServiceBean::runImport);

        runImport(tasks);
    }



    private void runImport(List<TaskCall> tasks) {
        logger.info("Importing SAP files");
        logger.debug(String.format("exchangePath = %s, archivePath = %s", exchangePath, archivePath));
        String adminMailRecipient = Constants.getMailRecipient();

        // importieren
        long start = System.currentTimeMillis();

        TaskNodeLog taskSapImport = new TaskNodeLog("SAP import");
        tasks.forEach(task -> taskSapImport.addSubTask(task.execTask()));
        taskSapImport.finishTask();

        // ist beendet
        long end = System.currentTimeMillis();
        long duration = end - start;
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
            MailServiceFacade.sendMail(adminMailRecipient, subjectText, importLog.toString());
        }
        catch (Exception mailException) {
            logger.error("Sending mail after importing SAP giles failed!", mailException);
        }
    }



    @FunctionalInterface
    interface TaskCall {
        ITaskNodeLog execTask();
    }

}
