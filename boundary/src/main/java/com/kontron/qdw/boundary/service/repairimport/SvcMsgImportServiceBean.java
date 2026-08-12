package com.kontron.qdw.boundary.service.repairimport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.kontron.qdw.domain.service.ServiceMessage;
import com.kontron.qdw.domain.service.ServiceOrder;
import com.kontron.qdw.repository.base.CustomerRepository;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.service.ServiceMessageRepository;
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
    private ServiceMessageRepository svcMsgManager;
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


        // in batch vorkommende service message Ids
        // Set<Long> idsOfBatch = curBatch.stream()
        // .map(ServiceMessageMappingType::getId)
        // .map(id -> {
        // try {
        // return Long.parseLong(id);
        // }
        // catch (NumberFormatException nfe) {
        // return Long.MIN_VALUE;
        // }
        // })
        // .filter(id -> id != Long.MIN_VALUE)
        // .collect(Collectors.toSet());

        // Map<String, Long> longIdsPerMsgStringId = curBatch.stream()
        // .map(ServiceMessageMappingType::getId)
        // .collect(Collectors.toMap(Function.identity(),
        // id -> {
        // try {
        // return Long.parseLong(id);
        // }
        // catch (NumberFormatException nfe) {
        // return Long.MIN_VALUE;
        // }
        // }));
        // longIdsPerMsgStringId = longIdsPerMsgStringId.entrySet().stream()
        // .filter(idEntry -> idEntry.getValue().longValue() != Long.MIN_VALUE)
        // .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        // Map<String, Long> longIdsPerMsgStringId = curBatch.stream().<Map.Entry<String, Long>> mapMulti((item, consumer) -> {
        // String id = item.getId();
        // try {
        // consumer.accept(Map.entry(id, Long.parseLong(id)));
        // }
        // catch (NumberFormatException ignored) {
        // // Element wird ignoriert und landet nicht im Stream
        // }
        // })
        // .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Ids des Batch in Long umwandeln und in Map speichern mit Long als key und original String als value.
        // Werte, die nicht geparst werden können, werden rausgefiltert.
        Map<Long, String> msgStringIdPerLongIds = curBatch.stream().<Map.Entry<Long, String>> mapMulti((item, consumer) -> {
            String id = item.getId();
            try {
                consumer.accept(Map.entry(Long.parseLong(id), id));
            }
            catch (NumberFormatException ignored) {
                // Element wird ignoriert und landet nicht im Stream
            }
        })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Service messages zu geparsten Long-Ids holen
        // List<ServiceMessage> svcMsg = svcMsgManager.findByIds(longIdsPerMsgStringId.values());
        List<ServiceMessage> svcMsgs = svcMsgManager.findByIds(msgStringIdPerLongIds.keySet());

        // Map an Service messages zu den originalen Ids als String (über oben aufgebaute Map)
        Map<String, ServiceMessage> svcMsgPerMsgStringId = svcMsgs.stream()
                .collect(Collectors.toMap(m -> msgStringIdPerLongIds.get(m.getId()), m -> m));


        // in batch vorkommende Materialien holen
        Set<String> matNrOfBatch = curBatch.stream()
                .map(ServiceMessageMappingType::getMaterialSapNumber)
                .collect(Collectors.toSet());

        Map<String, Material> existingMats = materialManager.findBySAPNumbers(matNrOfBatch, true);


        // in batch vorkommende Werke holen und falls nötig anlegen
        Map<String, Plant> existingPlants = findOrCreatePlant(curBatch);


        for (ServiceMessageMappingType svcMsg : curBatch) {
            if (StringUtils.isEmpty(svcMsg.getId())) {
                String errorMsg = String.format("Eintrag %s ohne Transaction-Id (service_order) in RMA Datei '%s'.",
                        bulkProcess.getCnt(), importFileName);
                logger.warn(errorMsg);
                errorList.add(errorMsg);
                continue;
            }
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


            // long transactionId;
            // try {
            // transactionId = Long.parseLong(svcMsg.getId());
            // }
            // catch (NumberFormatException nfe) {
            // String errorMsg = String.format("Eintrag %s mit korrupter Transaction-Id (service_order) in RMA Datei '%s'. "
            // + "Es wird eine Zahl erwartet. Lediglich etwaige Großbuchstaben wurden dabei entfernt.",
            // bulkProcess.getCnt(), importFileName);
            // logger.warn(errorMsg);
            // errorList.add(errorMsg);
            // continue;
            // }


            Material material = existingMats.get(svcMsg.getMaterialSapNumber());
            Plant plant = existingPlants.get(svcMsg.getPlantCode());
            MaterialRevision revision = findOrCreateRevision(svcMsg.getMaterialRevisionNo(), material, plant);

            ServiceMessage existingSvcMsg = svcMsgPerMsgStringId.get(svcMsg.getId());


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
            String transactionId = StringUtils.defaultString(importedSvcMsg.getId()).replaceAll("[A-Z]", "");
            importedSvcMsg.setId(StringUtil.removeLeadingZero(transactionId));
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
