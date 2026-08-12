package com.kontron.qdw.boundary.service.repairimport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.svcmsg.ServiceMessageMappingType;
import com.kontron.qdw.boundary.service.mapping.svcmsg.ServiceMessageRootMappingType;
import com.kontron.qdw.boundary.service.process.AbstractImportServiceBean;
import com.kontron.qdw.boundary.service.process.BulkProcess;
import com.kontron.qdw.domain.base.Customer;
import com.kontron.qdw.domain.service.ServiceOrder;
import com.kontron.qdw.repository.base.CustomerRepository;
import com.kontron.qdw.repository.service.ServiceOrderRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Import der Service-Message-Repair-Dateien, die der Downloader bereitstellt.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Superklasse Interface implementiert und sonst keine No-Interface-View bereit gestellt wird
public class SvcMsgImportServiceBean extends AbstractImportServiceBean<ServiceMessageRootMappingType, ServiceMessageMappingType> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "Repair service message";
    private static final String FOLDER_SUB_PATH = "repair";
    private static final String SCHEMA_NAME = "RMA.xsd";

    @EJB
    private ServiceOrderRepository serviceOrderManager;
    @EJB
    private CustomerRepository customerManager;


    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    protected String getFolderSubPath() {
        return FOLDER_SUB_PATH;
    }

    @Override
    protected String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override
    protected ImportType getImportType() {
        return ImportType.QDW_SERVICE_MESSAGE;
    }

    @Override
    protected boolean isWithBulk() {
        return false;
    }

    @Override
    protected Class<ServiceMessageRootMappingType> getXmlRootClazz() {
        return ServiceMessageRootMappingType.class;
    }

    @Override
    protected Function<ServiceMessageRootMappingType, List<ServiceMessageMappingType>> getGetElementsFunction() {
        return ServiceMessageRootMappingType::getServiceMessages;
    }



    @Override
    protected void importBulk(String importFileName, TaskNodeLog tsk, List<ServiceMessageMappingType> importedSuppliers, List<String> errorList,
            BulkProcess bulkProcess) throws Exception {
        bulkProcess.logProcessBulkLevel(logger);

        // aktuell verarbeiteter Batch
        List<ServiceMessageMappingType> curBatch = importedSuppliers.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());
        batchNormalisieren(curBatch);

        Set<String> unknownCustomer = new TreeSet<>();

        Map<String, ServiceOrder> existingSvoMap = serviceOrderManager.findByIds(curBatch.stream()
                .map(ServiceMessageMappingType::getCode)
                .collect(Collectors.toSet())).stream()
                .collect(Collectors.toMap(ServiceOrder::getCode, Function.identity()));

        Map<String, Customer> existingCustMap = customerManager.findByIds(curBatch.stream()
                .map(ServiceMessageMappingType::getCustomerCode)
                .collect(Collectors.toSet())).stream()
                .collect(Collectors.toMap(Customer::getCode, Function.identity()));


        for (ServiceMessageMappingType svcOrder : curBatch) {
            // Bearbeitung ist unspektakulär und muss nicht in eine eigene Methode ausgelagert werden.
            // So sparen wir den Overhead, mit jedem Aufruf die Suplier-Liste auf den Stack zu legen.

            Customer existingCustomer = existingCustMap.get(svcOrder.getCustomerCode());
            if (existingCustomer == null) {
                logger.info(String.format("Fehler in RMA Datei '%s': unbekannter Customer '%s'.", importFileName, svcOrder.getCustomerCode()));
                unknownCustomer.add(svcOrder.getCustomerCode());
                continue;
            }

            boolean isActive = evaluateActiveState(svcOrder);
            String currentCode = svcOrder.getCode();
            ServiceOrder existingSvcOrder = existingSvoMap.get(currentCode);
            if (existingSvcOrder != null) {
                existingSvcOrder.setServiceOrderType(svcOrder.getType());
                existingSvcOrder.setComment(svcOrder.getComment());
                existingSvcOrder.setActive(isActive);
                existingSvcOrder.setCustomer(existingCustomer);
                existingSvcOrder.setDocumentDate(LocalDate.parse(svcOrder.getDocumentDate()));
                existingSvcOrder.setShortText(svcOrder.getReportedBy());
            }
            else {
                ServiceOrder newSvcOrder = new ServiceOrder(currentCode);
                newSvcOrder.setServiceOrderType(svcOrder.getType());
                newSvcOrder.setComment(svcOrder.getComment());
                newSvcOrder.setActive(isActive);
                newSvcOrder.setCustomer(existingCustomer);
                newSvcOrder.setDocumentDate(LocalDate.parse(svcOrder.getDocumentDate()));
                newSvcOrder.setShortText(svcOrder.getReportedBy());

                newSvcOrder = serviceOrderManager.persist(newSvcOrder, false, false);
            }
        } // end for curBatch

        if (!unknownCustomer.isEmpty()) {
            String errorMsg = String.format("Fehler in RMA Datei '%s': unbekannte Customer '%s'.",
                    importFileName, String.join(", ", unknownCustomer));
            errorList.add(errorMsg);

        }
    }

    private void batchNormalisieren(List<ServiceMessageMappingType> curBatch) {
        curBatch.forEach(importedRma -> {
            importedRma.setCode(StringUtil.removeLeadingZero(importedRma.getCode()));
            importedRma.setCustomerCode(StringUtil.removeLeadingZero(importedRma.getCustomerCode()));
        });
    }

    private boolean evaluateActiveState(ServiceMessageMappingType svcOrder) {
        return svcOrder != null
                && svcOrder.getStatus() != null
                && (svcOrder.getStatus().equalsIgnoreCase("Being processed")
                        || svcOrder.getStatus().equalsIgnoreCase("Open"));
    }

}
