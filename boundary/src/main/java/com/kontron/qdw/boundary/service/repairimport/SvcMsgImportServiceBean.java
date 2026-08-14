package com.kontron.qdw.boundary.service.repairimport;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.svcmsg.ServiceMessageMappingType;
import com.kontron.qdw.boundary.service.mapping.svcmsg.ServiceMessageRootMappingType;
import com.kontron.qdw.boundary.service.process.AbstractImportServiceBean;
import com.kontron.qdw.boundary.service.process.BulkProcess;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.domain.service.FaultAnalysis;
import com.kontron.qdw.domain.service.RepairErrorCode;
import com.kontron.qdw.domain.service.ServiceMessage;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoJeMatIdFilter;
import com.kontron.qdw.repository.serial.SerialObjectRepository.SerNoMatIdResult;
import com.kontron.qdw.repository.service.FaultAnalysisRepository;
import com.kontron.qdw.repository.service.RepairErrorCodeRepository;
import com.kontron.qdw.repository.service.ServiceMessageRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
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
    @EJB
    private SerialObjectRepository serObjManager;
    @EJB
    private FaultAnalysisRepository faultManager;
    @EJB
    private RepairErrorCodeRepository errorCodeManager;


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


        // Ids des Batch in Long umwandeln.
        // Wir brauchen eine Map mit Long als key und original String als value: wir suchen die Service messages im bulk nach Id
        // und erhalten eine Liste an Entitäten. Für den folgenden Zugriff auf diese brauchen wir eine Map der Entitäten mit den
        // originalen String-Ids als key.
        // Wird ein Objekt nicht gefunden, muss es neu erstellt werden. Dafür muss die String-Id erneut umgewandelt werden. Also
        // erstellen wir auch gleich eine Map mit den String-Ids als key und den Long-Ids als value.
        // Um feststellen zu können, dass eine String-Id nicht geparst werden kann, müssen wir das markieren. Etwa als Long.MIN_VALUE
        // für long oder mit null bei Long-Werten oder einfach keinen Wert in die Map eintragen, weil auch dann das Ergebnis von get()
        // null sein wird.
        Map<Long, String> msgStringIdPerLongIds = new HashMap<>();
        Map<String, Long> longIdsPerMsgStringIds = new HashMap<>();

        curBatch.stream()
                .map(ServiceMessageMappingType::getId)
                .forEach(msgStringId -> {
                    // Wenn der String geparst werden kann, die Werte in beide Maps eintragen.
                    // Ansonsten nichts machen.
                    try {
                        Long longId = Long.parseLong(msgStringId);
                        msgStringIdPerLongIds.put(longId, msgStringId);
                        longIdsPerMsgStringIds.put(msgStringId, longId);
                    }
                    catch (NumberFormatException nfe) {
                    }
                });

        // Service messages zu geparsten Long-Ids holen
        List<ServiceMessage> importedSvcMsgs = svcMsgManager.findByIds(msgStringIdPerLongIds.keySet());

        // Map an Service messages zu den originalen Ids als String (über oben aufgebaute Map)
        Map<String, ServiceMessage> svcMsgPerMsgStringId = importedSvcMsgs.stream()
                .collect(Collectors.toMap(m -> msgStringIdPerLongIds.get(m.getId()), Function.identity()));


        // in batch vorkommende Materialien holen
        Set<String> matNrOfBatch = curBatch.stream()
                .map(ServiceMessageMappingType::getMaterialSapNumber)
                .collect(Collectors.toSet());

        Map<String, Material> existingMats = materialManager.findBySAPNumbers(matNrOfBatch, true);
        Set<String> missingMats = existingMats.entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .map(Entry::getKey)
                .collect(Collectors.toSet());
        if (!missingMats.isEmpty()) { // sofortiger Abbruch
            String errorMsg = String.format("SAP-Nummer(n) (part_no) nicht gefunden: '%s'", String.join(", ", missingMats));
            logger.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }


        // in batch vorkommende Werke holen und falls nötig anlegen
        Map<String, Plant> existingPlants = findOrCreatePlant(curBatch);


        // in batch vorkommende SerialObjects holen und falls nötig anlegen (Suche erfolgt nach SerialNumber und Material id)
        Map<SerNoMatIdResult, SerialObject> existingSerObjs = findOrCreateSerObj(curBatch, existingMats);


        // in batch vorkommende FaultAnalysis ("Symptom") holen und falls nötig anlegen
        Map<String, FaultAnalysis> existingFaults = findOrCreateFaultAnalysis(curBatch);


        // in batch vorkommende Error Code ("Repair cause codes") holen und falls nötig anlegen
        Map<String, RepairErrorCode> existingErrorCodes = findOrCreateRepairErrorCode(curBatch);


        for (ServiceMessageMappingType importedSvcMsg : curBatch) {
            bulkProcess.logProcess(logger);

            try {
                importEntry(importedSvcMsg, bulkProcess.getCnt(), errorList,
                        longIdsPerMsgStringIds, svcMsgPerMsgStringId,
                        existingMats, existingPlants, existingSerObjs,
                        existingFaults, existingErrorCodes);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, importedSvcMsg.getId(), e));
                tsk.abortTask();
                throw e;
            }

            bulkProcess.nextCnt();
        } // end for curBatch
    }



    private void importEntry(ServiceMessageMappingType importedSvcMsg, int bulkCnt, List<String> errorList,
            Map<String, Long> longIdsPerMsgStringIds, Map<String, ServiceMessage> svcMsgPerMsgStringId,
            Map<String, Material> existingMats, Map<String, Plant> existingPlants, Map<SerNoMatIdResult, SerialObject> existingSerObjs,
            Map<String, FaultAnalysis> existingFaults, Map<String, RepairErrorCode> existingErrorCodes) {

        if (StringUtils.isEmpty(importedSvcMsg.getId())) {
            String errorMsg = String.format("Eintrag %s ohne Transaction-Id (service_order).", bulkCnt + 1);
            logger.info(errorMsg);
            return;
        }
        if (StringUtils.isEmpty(importedSvcMsg.getMaterialSapNumber())) {
            String errorMsg = String.format("Eintrag %s ohne SAP-Nummer (part_no).", bulkCnt + 1);
            logger.info(errorMsg);
            return;
        }
        if (StringUtils.isEmpty(importedSvcMsg.getPlantCode())) {
            String errorMsg = String.format("Eintrag %s ohne Plant.", bulkCnt + 1);
            logger.info(errorMsg);
            return;
        }
        if (StringUtils.isEmpty(importedSvcMsg.getSerialObjectSerialNumber())) {
            String errorMsg = String.format("Eintrag %s ohne SerialNumber (serial_no).", bulkCnt + 1);
            logger.info(errorMsg);
            return;
        }
        // if (StringUtils.isEmpty(importedSvcMsg.getFaultAnalysisCode()) || StringUtils.isEmpty(importedSvcMsg.getFaultAnalysisGroup())) {
        // String errorMsg = String.format("Eintrag %s ohne Defect code (defect_code) oder Defect code group (defect_code_group).", bulkCnt + 1);
        // logger.info(errorMsg);
        // return;
        // }


        Long transactionId = longIdsPerMsgStringIds.get(importedSvcMsg.getId());
        if (transactionId == null) {
            // gibt es keinen Eintrag, konnte die String-Id nicht geparst werden
            String errorMsg = String.format("Eintrag %s mit korrupter Transaction-Id (service_order) '%s'. "
                    + "Es wird eine Zahl erwartet. Lediglich etwaige Großbuchstaben wurden dabei entfernt.", bulkCnt + 1, importedSvcMsg.getId());
            logger.warn(errorMsg);
            errorList.add(errorMsg);
            return;
        }
        // -> else: die Id konnte geparst werden und mit ihr wurden Service messages in der Datenbank gesucht


        Material material = existingMats.get(importedSvcMsg.getMaterialSapNumber());
        Plant plant = existingPlants.get(importedSvcMsg.getPlantCode());
        MaterialRevision revision = findOrCreateRevision(importedSvcMsg.getMaterialRevisionNo(), material, plant);
        SerialObject serObj = existingSerObjs.get(new SerNoMatIdResult(material.getId(), importedSvcMsg.getSerialObjectSerialNumber()));
        FaultAnalysis fault = existingFaults.get(importedSvcMsg.getFaultAnalysisGroup() + "-" + importedSvcMsg.getFaultAnalysisCode()); // kann null sein
        RepairErrorCode errorCode = existingErrorCodes.get( // kann null sein
                (StringUtils.isEmpty(importedSvcMsg.getRepairErrorCodeGroup())
                        ? ""
                        : importedSvcMsg.getRepairErrorCodeGroup() + "-")
                        + importedSvcMsg.getRepairErrorCode());


        // gibt es keinen Eintrag, so wurde zu der geparsten Id kein Eintrag in der Datenbank gefunden
        ServiceMessage existingSvcMsg = svcMsgPerMsgStringId.get(importedSvcMsg.getId());
        boolean createNewSvcMsg = existingSvcMsg == null;
        if (createNewSvcMsg) {
            existingSvcMsg = new ServiceMessage();
            existingSvcMsg.setId(transactionId);
            existingSvcMsg.setRebuildFlag(ServiceMessage.REBUILD_FOR_NEW_ENTRY);
        }
        else {
            if (existingSvcMsg.getRebuildFlag() == ServiceMessage.REBUILD_NOT_NECCESSARY) {
                existingSvcMsg.setRebuildFlag(ServiceMessage.REBUILD_FOR_UPDATED_ENTRY);
            }
            // wenn rebuild-flag == 1, dann ist der Datensatz bereits angelegt, wurde aber noch nicht in die materialized table übernommen.
            // Dann darf das flag nicht auf update gesetzt werden, da in der materialized table noch kein Datensatz steht,
            // der aktualisiert werden könnte. Der Datensatz ginge dann verloren.
        }


        existingSvcMsg.setSerialObject(serObj);
        existingSvcMsg.setPlant(plant);
        existingSvcMsg.setMaterialRevision(revision);
        existingSvcMsg.setAnalysisText(importedSvcMsg.getAnalysisText());
        existingSvcMsg.setCustomerReport(StringUtils.substring(importedSvcMsg.getCustomerReport(), 0, 4000));
        existingSvcMsg.setFaultAnalysis(fault);
        existingSvcMsg.setDeliveryNoteNumber(importedSvcMsg.getDeliveryNoteNumber());

        existingSvcMsg.setDesignator(importedSvcMsg.getDesignator());
        // Designator aus cause text ermitteln, wenn keiner angegeben ist
        if (StringUtils.isEmpty(existingSvcMsg.getDesignator())) {
            extractDesignatorFromCause(importedSvcMsg).ifPresent(existingSvcMsg::setDesignator);
        }

        existingSvcMsg.setEpidemicFailure(importedSvcMsg.isEpidemicFailure());
        existingSvcMsg.setRepairErrorCode(errorCode);
        existingSvcMsg.setOrigin(mapFailureOriginCode(importedSvcMsg.getOrigin()));
        existingSvcMsg.setExternalReport(StringUtils.substring(importedSvcMsg.getExternalReport(), 0, 4000));
        existingSvcMsg.setInternalReport(StringUtils.substring(importedSvcMsg.getInternalReport(), 0, 4000));
        existingSvcMsg.setServiceOrder(findServiceOrder(importedSvcMsg.getServiceOrderCode()));
        existingSvcMsg.setRepairState(findRepairState(importedSvcMsg.getRepairStateCode()));
        existingSvcMsg.setServiceMessageId(importedSvcMsg.getServiceMessageId());
        existingSvcMsg.setrMAType(findRMAType(importedSvcMsg.getrMATypeCode(), importedSvcMsg.getRmaTypeGroup()));
        existingSvcMsg.setRepairTask(findRepairTask(importedSvcMsg.getRepairTaskCode(), importedSvcMsg.getRepairTaskGroup()));
        existingSvcMsg.setDefectComponent(importedSvcMsg.getDefectComponent());
        existingSvcMsg.setExternalSupplier(findSupplier(importedSvcMsg.getWorkCenter()));
        existingSvcMsg.setCauseText(importedSvcMsg.getErrorText());
        existingSvcMsg.setRepairDescription(importedSvcMsg.getRepairTaskText());


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
            importedSvcMsg.setSerialObjectSerialNumber(StringUtil.removeLeadingZero(importedSvcMsg.getSerialObjectSerialNumber()));
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
                    logger.debug("Neue Revision {} zu MatSapNr {} und Werk {} erstellt",
                            revNo, material.getSapNumber(), plant.getCode());
                    material.getRevisions().add(newRev);
                    return matRevManager.persist(newRev, true, true);
                });
    }

    private Map<SerNoMatIdResult, SerialObject> findOrCreateSerObj(List<ServiceMessageMappingType> curBatch, Map<String, Material> existingMats) {
        Set<SerNoJeMatIdFilter> requestedSerObjPerSerNrAndMatId = curBatch.stream()
                .map(importedSvcMsg -> {
                    String importedSerNr = importedSvcMsg.getSerialObjectSerialNumber();
                    if (StringUtils.isEmpty(importedSerNr)) {
                        return null;
                    }
                    Material existingMat = existingMats.get(importedSvcMsg.getMaterialSapNumber());
                    return new SerNoJeMatIdFilter(existingMat.getId(), Set.of(importedSerNr));
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<SerNoMatIdResult, SerialObject> existingSerObjPerSerNrAndMatId = serObjManager
                .findBySerialNumberAndMaterialIds(requestedSerObjPerSerNrAndMatId);

        if (existingSerObjPerSerNrAndMatId.size() == requestedSerObjPerSerNrAndMatId.size()) {
            return existingSerObjPerSerNrAndMatId;
        }


        Set<SerNoMatIdResult> requestedAsResultRecord = requestedSerObjPerSerNrAndMatId.stream()
                .map(reqFilter -> new SerNoMatIdResult(reqFilter.materialId(), reqFilter.serialNumbers().iterator().next()))
                .collect(Collectors.toSet());
        // die keys des Ergebnisses abziehen. Es verbleiben die Einträge, die es nicht in der Datenbank gobt
        requestedAsResultRecord.removeAll(existingSerObjPerSerNrAndMatId.keySet());

        // für unkomplizierten Zugriff die Materialien in eine Map nach Id packen
        Map<Long, Material> matPerId = existingMats.values().stream()
                .collect(Collectors.toMap(Material::getId, Function.identity()));

        // die nicht existierenden Einträge erzeugen und in die Struktur bringen, die als Rückgabe erwartet wird
        Map<SerNoMatIdResult, SerialObject> newSerObjPerSerNrAndMatId = requestedAsResultRecord.stream()
                .map(reqFilter -> {
                    SerialObject newSerObj = new SerialObject();
                    newSerObj.setSerialNumber(reqFilter.serialNumber());
                    Material material = matPerId.get(reqFilter.materialId());
                    newSerObj.setMaterial(material);
                    logger.debug("Neue SerObj zu SerNr {} und MatSapNr {} erstellt",
                            reqFilter.serialNumber(), material.getSapNumber());
                    return serObjManager.persist(newSerObj, true, true);
                })
                .collect(Collectors.toMap(so -> new SerNoMatIdResult(so.getMaterial().getId(), so.getSerialNumber()), Function.identity()));

        // die fehlenden Einträge den existierenden hinzufügen
        existingSerObjPerSerNrAndMatId.putAll(newSerObjPerSerNrAndMatId);

        return existingSerObjPerSerNrAndMatId;
    }

    private Map<String, FaultAnalysis> findOrCreateFaultAnalysis(List<ServiceMessageMappingType> curBatch) {
        Set<String> requestedFaultCodes = curBatch.stream()
                .map(importedSvcMsg -> {
                    if (StringUtils.isEmpty(importedSvcMsg.getFaultAnalysisCode()) || StringUtils.isEmpty(importedSvcMsg.getFaultAnalysisGroup())) {
                        return null;
                    }
                    return importedSvcMsg.getFaultAnalysisGroup() + "-" + importedSvcMsg.getFaultAnalysisCode();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<FaultAnalysis> existingFaults = faultManager.findByIds(requestedFaultCodes);

        Map<String, FaultAnalysis> existingFaultsPerCode = existingFaults.stream()
                .collect(Collectors.toMap(FaultAnalysis::getCode, Function.identity()));

        if (existingFaultsPerCode.size() == requestedFaultCodes.size()) {
            return existingFaultsPerCode;
        }


        // die keys des Ergebnisses abziehen. Es verbleiben die Einträge, die es nicht in der Datenbank gobt
        requestedFaultCodes.removeAll(existingFaultsPerCode.keySet());

        // die nicht existierenden Einträge erzeugen und in die Struktur bringen, die als Rückgabe erwartet wird
        Map<String, FaultAnalysis> newFaultsPerCode = requestedFaultCodes.stream()
                .map(faultCode -> {
                    FaultAnalysis newFault = new FaultAnalysis(faultCode);
                    newFault.setShortText("Automatically created by QDW import");
                    logger.debug("Neue FaultAnalyses mit code {} erstellt", faultCode);
                    return faultManager.persist(newFault, true, true);
                })
                .collect(Collectors.toMap(FaultAnalysis::getCode, Function.identity()));

        // die fehlenden Einträge den existierenden hinzufügen
        existingFaultsPerCode.putAll(newFaultsPerCode);

        return existingFaultsPerCode;
    }

    private Map<String, RepairErrorCode> findOrCreateRepairErrorCode(List<ServiceMessageMappingType> curBatch) {
        // == Repair cause codes
        Set<RepairErrorCodeRecord> requestedErrorCodes = curBatch.stream()
                .map(importedSvcMsg -> {
                    if (StringUtils.isEmpty(importedSvcMsg.getRepairErrorCode())) {
                        return null;
                    }

                    String group = importedSvcMsg.getRepairErrorCodeGroup();
                    String code = importedSvcMsg.getRepairErrorCode();
                    if (!StringUtils.isEmpty(group)) {
                        code = group + "-" + code;
                    }
                    return new RepairErrorCodeRecord(code, group);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<RepairErrorCode> existingErrorCodes = errorCodeManager.findByIds(
                requestedErrorCodes.stream().map(RepairErrorCodeRecord::code).collect(Collectors.toSet()));

        Map<String, RepairErrorCode> existingErrorCodesPerCode = existingErrorCodes.stream()
                .collect(Collectors.toMap(RepairErrorCode::getCode, Function.identity()));

        if (existingErrorCodes.size() == requestedErrorCodes.size()) {
            return existingErrorCodesPerCode;
        }


        // die keys des Ergebnisses abziehen. Es verbleiben die Einträge, die es nicht in der Datenbank gobt
        requestedErrorCodes.removeIf(reqCode -> existingErrorCodesPerCode.containsKey(reqCode.code));

        // die nicht existierenden Einträge erzeugen und in die Struktur bringen, die als Rückgabe erwartet wird
        Map<String, RepairErrorCode> newFaultsPerCode = requestedErrorCodes.stream()
                .map(errorCodeRec -> {
                    RepairErrorCode newErrorCode = new RepairErrorCode(errorCodeRec.code);
                    newErrorCode.setGroupName(errorCodeRec.group);
                    newErrorCode.setActive(true);
                    newErrorCode.setName(errorCodeRec.code);
                    newErrorCode.setShortText("Automatically created by QDW import");
                    logger.debug("Neuer RepairErrorCode mit code {} erstellt", errorCodeRec.code);
                    return errorCodeManager.persist(newErrorCode, true, true);
                })
                .collect(Collectors.toMap(RepairErrorCode::getCode, Function.identity()));

        // die fehlenden Einträge den existierenden hinzufügen
        existingErrorCodesPerCode.putAll(newFaultsPerCode);

        return existingErrorCodesPerCode;
    }

    record RepairErrorCodeRecord(String code, String group) {
    }

    private String mapFailureOriginCode(String origin) {
        // Hinweis: Die Daten sollten besser aus SAP kommen. Die Erweiterung des Codes um einen Text ist programatisch eine schlechte Lösung,
        // aber da auch nach Monaten SAP-seitig nichts geändert wurde, die einzige Lösung, die bleibt.
        if (StringUtils.isEmpty(origin)) {
            return origin;
        }

        return origin.trim()
                .replaceAll("^800$", "800 - Customer")
                .replaceAll("^805$", "805 - Vendor")
                .replaceAll("^810$", "810 - Kontron")
                .replaceAll("^815$", "815 - Unknown");
    }


    private Optional<String> extractDesignatorFromCause(ServiceMessageMappingType importedSvcMsg) {
        String causeText = importedSvcMsg.getErrorText();

        if (Strings.CS.contains(causeText, " - ")) {
            return Optional.empty();
        }

        causeText = causeText.substring(0, causeText.indexOf(" - "));

        // auf mögliche Designatorangaben filtern
        // ein Großbuchstabe, gefolgt von 1-4 Ziffern, optional gefolgt von einem Großbuchstaben -> Designator
        return Arrays.stream(StringUtils.split(causeText, ",."))
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(potentialDesignator -> potentialDesignator.trim().matches("^[A-Z]\\d{1,4}[A-Z]{0,1}$"))
                .sorted()
                // Werte kommasepariert in Attribut "designator" übernehmen
                // XXX: fürs erste nur den ersten Wert übernehmen, bis geklärt ist, welche Auswirkungen eine Mehrfachnennung hat
                .findFirst();
    }

}
