package com.kontron.qdw.boundary.service.repairimport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.arrival.ArrivalMappingType;
import com.kontron.qdw.boundary.service.mapping.svcmsg.ServiceMessageMappingType;
import com.kontron.qdw.boundary.service.mapping.svcmsg.ServiceMessageRootMappingType;
import com.kontron.qdw.boundary.service.process.AbstractImportServiceBean;
import com.kontron.qdw.boundary.service.process.BulkProcess;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Country;
import com.kontron.qdw.domain.base.Customer;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.service.ServiceOrder;
import com.kontron.qdw.repository.base.CustomerRepository;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.service.ServiceOrderRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;
import com.kontron.util.version.RevisionUtil;

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
    private static final String SCHEMA_NAME = "Repair.xsd";

    @EJB
    private MaterialRepository materialManager;
    @EJB
    private PlantRepository plantManager;
    @EJB
    private MaterialRevisionRepository matRevManager;


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

        // in batch vorkommende Materialien holen
        Set<String> matNrOfBatch = curBatch.stream()
                .map(ServiceMessageMappingType::getMaterialSapNumber)
                .collect(Collectors.toSet());

        Map<String, Material> existingMats = materialManager.findBySAPNumbers(matNrOfBatch, true);

        // in batch vorkommende Werke holen und falls nötig anlegen
        Map<String, Plant> existingPlants = findOrCreatePlant(curBatch);


        for (ServiceMessageMappingType svcMsg : curBatch) {
            if (StringUtils.isEmpty(svcMsg.getPlantCode())) {
                String errorMsg = String.format("Eintrag %s ohne Plant in RMA Datei '%s'.",
                        bulkProcess.getCnt(), importFileName);
                logger.warn(errorMsg);
                errorList.add(errorMsg);
                continue;
            }
            if (StringUtils.isEmpty(svcMsg.getSerialObjectSerialNumber())) {
                String errorMsg = String.format("Eintrag %s ohne SerialNumber (serial_no) in RMA Datei '%s'.",
                        bulkProcess.getCnt(), importFileName);
                logger.warn(errorMsg);
                errorList.add(errorMsg);
                continue;
            }

            if (StringUtils.isEmpty(svcMsg.getMaterialSapNumber())) {
                String errorMsg = String.format("Eintrag %s ohne SAP-Nummer (part_no) in RMA Datei '%s'.",
                        bulkProcess.getCnt(), importFileName);
                logger.warn(errorMsg);
                errorList.add(errorMsg);
                continue;
            }

            Material material = existingMats.get(svcMsg.getMaterialSapNumber());
            Plant plant = existingPlants.get(svcMsg.getPlantCode());
            MaterialRevision revision = findOrCreateRevision(svcMsg.getMaterialRevisionNo(), material, plant);

            String currentCode = svcMsg.getCode();
            ServiceOrder existingSvcOrder = existingMats.get(currentCode);
            if (existingSvcOrder != null) {
                existingSvcOrder.setServiceOrderType(svcMsg.getType());
                existingSvcOrder.setComment(svcMsg.getComment());
                existingSvcOrder.setActive(isActive);
                existingSvcOrder.setCustomer(existingCustomer);
                existingSvcOrder.setDocumentDate(LocalDate.parse(svcMsg.getDocumentDate()));
                existingSvcOrder.setShortText(svcMsg.getReportedBy());
            }
            else {
                ServiceOrder newSvcOrder = new ServiceOrder(currentCode);
                newSvcOrder.setServiceOrderType(svcMsg.getType());
                newSvcOrder.setComment(svcMsg.getComment());
                newSvcOrder.setActive(isActive);
                newSvcOrder.setCustomer(existingCustomer);
                newSvcOrder.setDocumentDate(LocalDate.parse(svcMsg.getDocumentDate()));
                newSvcOrder.setShortText(svcMsg.getReportedBy());

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
        curBatch.forEach(importedSvcMsg -> {
            importedSvcMsg.setServiceMessageId(StringUtil.removeLeadingZero(importedSvcMsg.getServiceMessageId()));
            importedSvcMsg.setMaterialSapNumber(StringUtil.removeLeadingZero(importedSvcMsg.getMaterialSapNumber()));
            importedSvcMsg.setServiceOrderCode(StringUtil.removeLeadingZero(importedSvcMsg.getServiceOrderCode()));
            importedSvcMsg.setDeliveryNoteNumber(StringUtil.removeLeadingZero(importedSvcMsg.getDeliveryNoteNumber()));
            importedSvcMsg.setDefectComponent(StringUtil.removeLeadingZero(importedSvcMsg.getDefectComponent()));
        });
    }


    private Map<String, Plant> findOrCreatePlant(List<ServiceMessageMappingType> curBatch) {
        Set<String> plantsOfBatch = curBatch.stream()
                .map(ServiceMessageMappingType::getPlantCode)
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toSet());

        Map<String, Plant> existingPlants = Plant.asMap(plantManager.findAll());
        Set<String> existingPlantCodes = existingPlants.keySet();

        Map<String, Plant> newCreatedPlants = plantsOfBatch.stream()
                .filter(Predicate.not(existingPlantCodes::contains))
                .map(missingCode -> {
                    Plant plant = new Plant(missingCode);
                    plant.setActive(true);
                    plant.setComment("Automatically created at service message import");
                    plant = plantManager.persist(plant, false, false);
                    logger.info("neues Werk erstellt: " + plant);
                    return plant;
                })
                .collect(Collectors.toMap(Plant::getCode, Function.identity()));

        existingPlants.putAll(newCreatedPlants);

        return existingPlants;
    }

    private MaterialRevision findOrCreateRevision(String repairRevNo, Material material, Plant plant) {
        String revNo = RevisionUtil.calculateRevNumberBySapRevNumber(repairRevNo);
        return material.getRevisions().stream()
                .filter(rev -> rev.getPlant().getCode().equals(plant.getCode()))
                .filter(rev -> rev.getRevisionNumber().equals(revNo))
                .findFirst()
                .orElseGet(() -> {
                    MaterialRevision newRev = new MaterialRevision();
                    newRev.setMaterial(material);
                    newRev.setPlant(plant);
                    newRev.setRevisionNumber(revNo);
                    newRev = matRevManager.persist(newRev, true, true);
                    return newRev;
                });
    }

}
