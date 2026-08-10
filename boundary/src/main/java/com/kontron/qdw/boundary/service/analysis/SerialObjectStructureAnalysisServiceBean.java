package com.kontron.qdw.boundary.service.analysis;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.mapping.serialobject.SerialObjectRFCMappingType;
import com.kontron.qdw.boundary.service.process.BulkProcess;
import com.kontron.qdw.boundary.service.process.TaskCall;
import com.kontron.qdw.domain.base.Country;
import com.kontron.qdw.domain.base.Customer;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.mv.MaterializedAssemblyShipment;
import com.kontron.qdw.domain.serial.Arrival;
import com.kontron.qdw.domain.serial.AssemblyRecord;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.domain.serial.Shipment;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.mv.MaterializedAssemblyShipmentRepository;
import com.kontron.qdw.repository.serial.AssemblyRecordRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;
import com.kontron.util.version.RevisionUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import net.sourceforge.jbizmo.commons.exchange.DataImportException;

/**
 * Analyse der SerialObject-Struktur.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class SerialObjectStructureAnalysisServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int DAYS_DELTA_STRUCTURE_ANALYSIS = 3;

    @EJB
    private SerialObjectRepository serialObjectManager;
    @EJB
    private AssemblyRecordRepository assemblyRecordManager;
    @EJB
    private MaterialRepository materialManager;
    @EJB
    private MaterializedAssemblyShipmentRepository matrlizedAssblyShiptManager;


    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext sessionContext;


    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("serial object delta structure analysis for SAP comparison");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void execTask(TaskNodeLog ownTask) {
        Set<Long> serObjIds = execCollectSerObj(ownTask);

        logger.info("{} serial object ids found", serObjIds.size());
        if (!serObjIds.isEmpty()) {
            execSapComparison(ownTask, serObjIds);
        }
    }



    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Set<Long> execCollectSerObj(TaskNodeLog ownTask) {
        String executionSection = "collecting serial object ids";
        logger.info(executionSection);
        TaskNodeLog subTsk = ownTask.createNewSubTaskNode(executionSection);

        LocalDateTime thresholdDate = LocalDate.now()
                .minusDays(DAYS_DELTA_STRUCTURE_ANALYSIS)
                .atStartOfDay();
        TaskLeafLog leaf = null;
        Set<Long> idList = new TreeSet<>();

        try {
            // get all new shipments of mentioned interval
            leaf = subTsk.createNewSubTaskLeaf("select serial object ids from shipment");
            String sql = "select distinct a.serialObject.id from Shipment a where a.creationDate > :paramDate";
            idList.addAll(em.createQuery(sql, Long.class).setParameter("paramDate", thresholdDate).getResultList());
            leaf.finishTaskWithSuccess();

            // get all new and updated repairs of mentioned interval
            leaf = subTsk.createNewSubTaskLeaf("select serial object ids from service message");
            sql = "select distinct a.serialObject.id from ServiceMessage a where a.creationDate > :paramCreationDate "
                    + "union "
                    + "select distinct a.serialObject.id from ServiceMessage a where a.lastUpdate > :paramLastUpdate";
            idList.addAll(em.createQuery(sql, Long.class)
                    .setParameter("paramCreationDate", thresholdDate)
                    .setParameter("paramLastUpdate", thresholdDate)
                    .getResultList());
            leaf.finishTaskWithSuccess();

            // get all new and updated repairs of mentioned interval
            leaf = subTsk.createNewSubTaskLeaf("select serial object ids from x2 message");
            sql = "select distinct a.serialObject.id from X2Message a where a.creationDate > :paramCreationDate "
                    + "union "
                    + "select distinct a.serialObject.id from X2Message a where a.lastUpdate > :paramLastUpdate";
            idList.addAll(em.createQuery(sql, Long.class)
                    .setParameter("paramCreationDate", thresholdDate)
                    .setParameter("paramLastUpdate", thresholdDate)
                    .getResultList());
            leaf.finishTaskWithSuccess();
        }
        catch (Exception e) {
            if (leaf != null) {
                leaf.finishTaskWithError(e);
            }
        }
        subTsk.finishTask();

        return idList;
    }



    private void execSapComparison(TaskNodeLog ownTask, Set<Long> serObjIds) {
        String executionSection = "SAP comparison";
        logger.info(executionSection);
        TaskLeafLog tskRFC = ownTask.createNewSubTaskLeaf(executionSection);

        if (CollectionUtils.isEmpty(serObjIds)) {
            String errMsg = "No serial object IDs specified!";
            logger.warn(errMsg);
            tskRFC.finishTaskWithError(errMsg);
            return;
        }
        logger.info(String.format("%s serial objects to compare with SAP", serObjIds.size()));



        // SAP-Verbindung
        JCoDestination destination;
        JCoFunction function;
        try {
            destination = JCoDestinationManager.getDestination("\\SAP");
            // Basis-Pfad ist "C:\GPE\wildfly31\bin\.". Das lässt sich leider nicht ändern.
            // Der Datei-Angabe "SAP" wird automatisch ".jcoDestination" angehängt.
            // Der komplette Dateipfad lautet also "C:\GPE\wildfly31\bin\SAP.jcoDestination"
            // Es gibt die Möglichkeit, die Konfiguration im Programm vorzunehmen und auf die Datei zu verzichten,
            // was aber grundsätzlich nicht sinnvoll ist, wenn man potentiell auf Test und Prod unterschiedliche Konfigurationen verwendet.
            if (destination == null) {
                tskRFC.finishTaskWithError("not able to connect to SAP.");
                return;
            }

            function = destination.getRepository().getFunction(SerialObjectRFCMappingType.FUNCTION_NAME);
            if (function == null) {
                tskRFC.finishTaskWithError(SerialObjectRFCMappingType.FUNCTION_NAME + " not found in SAP.");
                return;
            }
        }
        catch (Exception e) {
            tskRFC.finishTaskWithError(e);
            e.printStackTrace();
            return;
        }


        // Set wegen Sortierbarkeit in Liste wandeln
        List<Long> serObjIdList = new ArrayList<>(serObjIds);

        List<String> errorMsgs = new ArrayList<>();
        // in 100-er Schritten verarbeiten
        BulkProcess bulkProcess = new BulkProcess(serObjIds.size(), 100);
        ComparisonAnalysis analysis = new ComparisonAnalysis();

        while (bulkProcess.hasNext()) {
            try {
                // Aufruf der Methode compareBulk() über den injizierten Self-Proxy, damit der EJB-Interceptor die Transaktion greift!
                sessionContext.getBusinessObject(SerialObjectStructureAnalysisServiceBean.class)
                        .compareBulkTransactional(destination, function, serObjIdList, bulkProcess, tskRFC, errorMsgs, analysis);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tskRFC.finishTaskWithError(e);
                return;
            }

            // nächster bulk
            bulkProcess.nextBulk();
        }


        StringBuilder sb = new StringBuilder();
        sb.append(serObjIds.size()).append(" serial number compared, ");
        sb.append(analysis.getCntNewCreatedSerialsInQdw()).append(" serials new created in QDW (");
        sb.append("serial not found in SAP: ").append(analysis.getCntSerialNotFoundInSap());
        sb.append(", no material data in SAP: ").append(analysis.getCntNoMaterialDataInSap());
        sb.append(", no revision data in SAP: ").append(analysis.getCntNoRevDataInSap());
        sb.append(", material not found in QDW: ").append(analysis.getCntMatNotFoundInQdw());
        sb.append(", revision not found in QDW: ").append(analysis.getCntRevNotFoundInQdw()).append("))");
        if (!errorMsgs.isEmpty()) {
            sb.append("\nerror messages:")
                    .append(StringUtil.collectionToArrayPresentationString(errorMsgs, "\n", "\n", "", false, "none", StringUtil.NO_QUOTE));
        }
        tskRFC.finishTaskWithSuccess(sb.toString());
    }


    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void compareBulkTransactional(JCoDestination destination, JCoFunction function, List<Long> serObjIdList,
            BulkProcess bulkProcess, TaskLeafLog tskRFC, List<String> errorMsgs, ComparisonAnalysis analysis) {
        compareBulk(destination, function, serObjIdList, bulkProcess, tskRFC, errorMsgs, analysis);
        em.flush();
        em.clear();
    }

    private void compareBulk(JCoDestination destination, JCoFunction function, List<Long> serObjIdList,
            BulkProcess bulkProcess, TaskLeafLog tskRFC, List<String> errorMsgs, ComparisonAnalysis analysis) {
        // aktuell verarbeiteter Batch
        List<Long> serObjIdBatch = serObjIdList.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());
        List<SerialObject> serObjBatch;
        try {
            serObjBatch = serialObjectManager.findByIds(serObjIdBatch);
        }
        catch (Exception e) {
            tskRFC.finishTaskWithError(e);
            return;
        }


        // Map mit Materialien aus dem SerObj. nach SAP-Nummer erstellen.
        // Zu SerialNo und SapNo wird SAP abgefragt. Die SAP-Nummer sollte überein stimmen
        Map<String, SerialObject> serialObjectMap = new HashMap<>();
        Map<String, Material> materialCache = new HashMap<>();
        Map<String, MaterialRevision> materialRevisionMap = new HashMap<>();
        for (SerialObject serObj : serObjBatch) {
            serialObjectMap.put(serObj.getSerialNumber() + ";" + serObj.getMaterial().getSapNumber(), serObj);
            materialCache.put(serObj.getMaterial().getSapNumber(), serObj.getMaterial());
            for (MaterialRevision revision : serObj.getMaterial().getRevisions()) {
                materialRevisionMap.put(serObj.getMaterial().getId() + ";;" + revision.getRevisionNumber(), revision);
            }
        }


        for (SerialObject serObj : serObjBatch) {
            bulkProcess.logProcess(logger);

            try {
                compareEntry(destination, function, serialObjectMap, materialCache, materialRevisionMap, errorMsgs, analysis, serObj);
            }
            catch (Exception e) {
                // brauchbare Information in Exception packen
                throw new RuntimeException(String.format("Error at serial number %s: %s", serObj.getSerialNumber(), e.getMessage()), e);
            }

            bulkProcess.nextCnt();
        }
    }

    private void compareEntry(JCoDestination destination, JCoFunction function, Map<String, SerialObject> serialObjectMap,
            Map<String, Material> materialCache, Map<String, MaterialRevision> materialRevisionMap, List<String> errorMsgs,
            ComparisonAnalysis analysis, SerialObject serObj) {
        // Abgrageparameter für SAP setzen
        setRFCInputParameters(function, serObj.getMaterial().getSapNumber(), serObj.getSerialNumber());

        try {
            // Funktion ausführen. Die Funktion selbst gib das Ergebnis nicht zurück, sondern stellt es nur zum Auslesen bereit.
            function.execute(destination);
        }
        catch (Throwable e) {
            analysis.increaseSerialNotFoundInSap();
            return;
        }



        JCoTable table = function.getTableParameterList().getTable(SerialObjectRFCMappingType.RETURN_TABLE);

        for (int rowIdx = 0; rowIdx < table.getNumRows(); rowIdx++) {
            table.setRow(rowIdx);
            SerialObjectRFCMappingType serObjSap = SerialObjectRFCMappingType.getInstanceBySapTable(table);

            // Ignore empty sets
            if (serObjSap.getSerialNumber().isEmpty() || serObjSap.getParentSerialNumber().isEmpty()) {
                continue;
            }

            if (serObjSap.getAssemblyDate() == null) {
                continue;
            }

            if (serObjSap.getsAPMaterialNumber().isEmpty()) {
                analysis.increaseNoMaterialDataInSap();
                continue;
            }

            // Material
            Material material = getMaterial(serObjSap, materialCache, analysis, errorMsgs);
            if (material == null) {
                continue; // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
            }

            // Parent-Material
            Material parentMaterial = getParentMaterial(serObjSap, materialCache, analysis, errorMsgs);
            if (parentMaterial == null) {
                continue; // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
            }

            // Revision
            MaterialRevision materialRevision = getRevision(serObjSap, materialRevisionMap, material, analysis, errorMsgs);
            if (materialRevision == null) {
                continue; // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
            }



            // Alle Daten sind vorhanden, wir können loslegen

            // SerialObject
            String key = serObjSap.getSerialNumber() + ";" + serObjSap.getsAPMaterialNumber();
            SerialObject serialObject = getOrCreateSerObj(serObjSap, serialObjectMap, material, analysis, key);

            // Parent-SerialObject
            String parentKey = serObjSap.getParentSerialNumber() + ";" + serObjSap.getParentSAPMaterialNumber();
            SerialObject parentSerialObject = getOrCreateParentSerObj(serObjSap, serialObjectMap, parentMaterial, analysis, parentKey);


            LocalDate sapAssemblyDateAsLocalDate = serObjSap.getAssemblyDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            serialObject.setAssemblyDate(sapAssemblyDateAsLocalDate);
            serialObject.setProductionOrderNumber(serObjSap.getProductionOrderNumber());
            serialObject.setParentObject(parentSerialObject);



            AssemblyRecord rec = parentSerialObject.getAssemblyRecords().stream()
                    .filter(ar -> ar.getSerialObject().getId() == serialObject.getId())
                    .findFirst()
                    .orElse(null);

            boolean assemblyRecordFound = rec != null;

            if (!assemblyRecordFound) {
                rec = new AssemblyRecord();
                rec.setParentSerialObject(parentSerialObject);
                rec.setSerialObject(serialObject);
            }

            rec.setAssemblyDate(sapAssemblyDateAsLocalDate);
            rec.setProductionOrderNumber(serObjSap.getProductionOrderNumber());
            rec.setMaterialRevision(materialRevision);

            if (!assemblyRecordFound) {
                rec = assemblyRecordManager.persist(rec, true, true);
            }

            createOrUpdateMaterializedAssemblyShipment(rec, assemblyRecordFound);
        } // end for SAP-Tabellenspalten
    }



    private void createOrUpdateMaterializedAssemblyShipment(AssemblyRecord rec, boolean assemblyRecordFound) throws ConstraintViolationException {
        // check if MaterializedAssemblyShipment (by AssemblyRecord) already exists
        // wurde AssemblyRecord gerade erst neu angelegt, so kann es kein dazu gehöriges MaterializedAssemblyShipment geben.
        // -> Muss nicht geprüft werden, da die Antwort stets fals sein wird.
        if (assemblyRecordFound && matrlizedAssblyShiptManager.existsById(rec.getId())) {
            return;
        }

        // In einer Schleife den Top-Parent ermitteln. Eine eigene Datenbankabfrage mit hierarchischer Suche ist unnötig, da zum einen nur 1% tiefer
        // als zwei gehen und zum zweiten 90% nur eine Tiefe von eins haben und dieser Parent bereits mit fetch join mitgeholt wurde.
        // Die maximale Tiefe ist sieben und kommt nur ein Mal vor.
        SerialObject shippedObject = rec.getParentSerialObject();
        int maxDepthGuard = 0;
        while (shippedObject.getParentObject() != null && maxDepthGuard < 50) {
            shippedObject = shippedObject.getParentObject();
            maxDepthGuard++;
        }
        if (maxDepthGuard == 50) {
            throw new IllegalStateException("Zirkelbezug in SerObj " + rec.getSerialObject().getSerialNumber());
        }


        // get min shipment after production
        Shipment s = shippedObject.getShipments().stream()
                .filter(sh -> !sh.getShipmentDate().isBefore(rec.getAssemblyDate())) // after || equal
                .min(Comparator.comparing(Shipment::getShipmentDate))
                .orElse(null);

        // return, if there's no valid shipment
        if (s == null) {
            return;
        }


        MaterializedAssemblyShipment mas = new MaterializedAssemblyShipment(rec.getId());
        mas.setAssemblyDate(rec.getAssemblyDate());
        mas.setAssemblyPO(rec.getProductionOrderNumber());
        Customer myCustomer = s.getCustomer();
        Country myCountry = myCustomer.getCountry();
        mas.setCountryCode(myCountry.getCode());
        mas.setCountryName(myCountry.getName());
        mas.setCustomerCode(myCustomer.getCode());
        mas.setCustomerName(myCustomer.getName());
        mas.setCustomerOrderNumber(s.getOrderNumber());
        MaterialRevision myRevision = rec.getMaterialRevision();
        Material myMaterial = myRevision.getMaterial();
        mas.setMaterial(myMaterial);
        mas.setMaterialHierarchy(myMaterial.getMaterialHierarchy());
        mas.setMaterialNumber(myMaterial.getMaterialNumber());
        mas.setMaterialShortText(myMaterial.getShortText());
        mas.setMaterialType(myMaterial.getMaterialType().getCode());

        MaterialRevision myShipmentRevision = s.getMaterialRevision();
        Material myShipmentMaterial = myShipmentRevision.getMaterial();
        mas.setParentMaterialHierarchy(myShipmentMaterial.getMaterialHierarchy());
        mas.setParentMaterialNumber(myShipmentMaterial.getMaterialNumber());
        mas.setParentMaterialShortText(myShipmentMaterial.getShortText());
        mas.setParentMaterialType(myShipmentMaterial.getMaterialType().getCode());
        mas.setParentRevisionId(myShipmentRevision.getId());
        mas.setParentRevisionNumber(myShipmentRevision.getRevisionNumber());
        mas.setParentSapNumber(myShipmentMaterial.getSapNumber());
        mas.setParentSerialNumber(s.getSerialObject().getSerialNumber());
        mas.setParentSerialObjectId(s.getSerialObject().getId());
        mas.setPlant(s.getPlant().getCode());
        mas.setRevisionId(myRevision.getId());
        mas.setRevisionNumber(myRevision.getRevisionNumber());
        mas.setSapNumber(myMaterial.getSapNumber());
        mas.setSerialNumber(rec.getSerialObject().getSerialNumber());
        mas.setSerialObject(rec.getSerialObject());
        mas.setMeSerialObjectId(rec.getSerialObject().getId());
        mas.setShipmentDate(s.getShipmentDate());
        mas.setShipmentId(s.getId());
        mas.setShipmentMovementType(s.getMovementType().getCode());


        // get max arrival before production
        Arrival a = shippedObject.getArrivals().stream()
                .filter(arr -> !arr.getArrivalDate().isAfter(rec.getAssemblyDate())) // before || equal
                .max(Comparator.comparing(Arrival::getArrivalDate))
                .orElse(null);

        if (a != null) {
            mas.setArrivalDate(a.getArrivalDate());
            mas.setArrivalId(a.getId());
            mas.setArrivalMovementType(a.getMovementType().getCode());
            mas.setPurchaseOrderNumber(a.getOrderNumber());
            mas.setSupplierCode(a.getSupplier().getCode());
            mas.setSupplierName(a.getSupplier().getName());
        }

        try {
            matrlizedAssblyShiptManager.persist(mas, true, true);
        }
        catch (EJBTransactionRolledbackException e) {
            Throwable t = e.getCause();
            if (t != null && !(t instanceof ConstraintViolationException)) {
                throw new DataImportException(prepareExceptionInformation(rec, myMaterial) + e);
            }
            if (t instanceof ConstraintViolationException) {
                ConstraintViolationException c = (ConstraintViolationException) t;
                for (ConstraintViolation<?> violation : c.getConstraintViolations()) {
                    String message = violation.getMessage();
                    throw new IllegalStateException(message);
                }
            }
        }
        catch (Exception e2) {
            throw new DataImportException(prepareExceptionInformation(rec, myMaterial) + e2);
        }
    }

    private String prepareExceptionInformation(AssemblyRecord rec, Material myMaterial) {
        StringBuilder sbExceptionInfo = new StringBuilder();
        if (rec.getSerialObject().getSerialNumber() != null) {
            sbExceptionInfo.append("Serial Number : ");
            sbExceptionInfo.append(rec.getSerialObject().getSerialNumber());
            sbExceptionInfo.append("\n");
        }
        if (myMaterial.getSapNumber() != null) {
            sbExceptionInfo.append("Material SAP no: ");
            sbExceptionInfo.append(myMaterial.getSapNumber());
            sbExceptionInfo.append("\n");
        }
        if (myMaterial.getShortText() != null) {
            sbExceptionInfo.append("short text: ");
            sbExceptionInfo.append(myMaterial.getShortText());
            sbExceptionInfo.append("\n");
        }
        return sbExceptionInfo.toString();
    }

    private void setRFCInputParameters(JCoFunction function, String sapMaterialNumber, String serialNumber) {
        function.getImportParameterList().setValue(SerialObjectRFCMappingType.INPUT_VAR_MATERIAL, StringUtils.leftPad(sapMaterialNumber, 18, "0"));
        function.getImportParameterList().setValue(SerialObjectRFCMappingType.INPUT_VAR_SERIAL, StringUtils.leftPad(serialNumber, 18, "0"));
    }



    private Material getMaterial(SerialObjectRFCMappingType serObjSap, Map<String, Material> materialCache,
            ComparisonAnalysis analysis, List<String> errorMsgs) {
        Material material = materialCache.get(serObjSap.getsAPMaterialNumber());
        if (material == null) {
            // nicht in cache, also in DB suchen..
            material = materialManager.findBySapNumber(serObjSap.getsAPMaterialNumber());
            if (material != null) {
                materialCache.put(serObjSap.getsAPMaterialNumber(), material);
            }
        }
        if (material == null) {
            // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
            analysis.increaseMatNotFoundInQdw();
            String errMsg = String.format("Material %s not exists in QDW (serial %s)",
                    serObjSap.getsAPMaterialNumber(), serObjSap.getSerialNumber());
            errorMsgs.add(errMsg);
            logger.error(errMsg);
        }
        return material;
    }

    private Material getParentMaterial(SerialObjectRFCMappingType serObjSap, Map<String, Material> materialCache,
            ComparisonAnalysis analysis, List<String> errorMsgs) {
        Material parentMaterial = materialCache.get(serObjSap.getParentSAPMaterialNumber());
        if (parentMaterial == null) {
            // nicht in cache, also in DB suchen..
            parentMaterial = materialManager.findBySapNumber(serObjSap.getParentSAPMaterialNumber());
            if (parentMaterial != null) {
                materialCache.put(serObjSap.getParentSAPMaterialNumber(), parentMaterial);
            }
        }
        if (parentMaterial == null) {
            // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
            analysis.increaseMatNotFoundInQdw();
            String errMsg = String.format("Parent material %s not exists in QDW (serial %s)",
                    serObjSap.getParentSAPMaterialNumber(), serObjSap.getParentSerialNumber());
            errorMsgs.add(errMsg);
            logger.error(errMsg);
        }
        return parentMaterial;
    }

    private MaterialRevision getRevision(SerialObjectRFCMappingType serObjSap, Map<String, MaterialRevision> materialRevisionMap, Material material,
            ComparisonAnalysis analysis, List<String> errorMsgs) {
        String gpeRevisionNumber = RevisionUtil.calculateRevNumberBySapRevNumber(
                serObjSap.getAlternative(), serObjSap.getRev2(), serObjSap.getRev10());
        if (gpeRevisionNumber.equals(RevisionUtil.REV_NUMBER_UNDEF)) {
            analysis.increaseNoRevDataInSap();
            return null;
        }
        String revKey = material.getId() + ";;" + gpeRevisionNumber;

        MaterialRevision materialRevision = materialRevisionMap.get(revKey);
        if (materialRevision == null) {
            // nicht in cache, also alle Revisionen des Materials durchsuchen..
            for (MaterialRevision r : material.getRevisions()) {
                if (r.getRevisionNumber().equals(gpeRevisionNumber)) {
                    materialRevision = r;
                    break;
                }
            }

            if (materialRevision != null) {
                materialRevisionMap.put(revKey, materialRevision);
            }
        }
        if (materialRevision == null) {
            // immer noch nicht gefunden, dann nach Revisionen mit übereinstimmender rev6 oder rev2 suchen..
            String rev10 = serObjSap.getRev10();
            String rev6 = RevisionUtil.extractGPERev6FromSAPRev10(rev10);

            for (MaterialRevision r : material.getRevisions()) {
                if ((r.getRev6() != null && (r.getRev6().equals(rev6) || r.getRev6().equals(rev10)))
                        || (r.getRev2() != null && r.getRev2().equals(serObjSap.getRev2()))) {
                    materialRevision = r;
                    break;
                }
            }

            if (materialRevision != null) {
                materialRevisionMap.put(revKey, materialRevision);
            }
            if (materialRevision == null) {
                // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
                analysis.increaseRevNotFoundInQdw();
                String errMsg = String.format(
                        "Rev %s of material %s not exists in QDW (revAlt: %s, rev2: %s, rev10: %s) (serial %s)",
                        gpeRevisionNumber, material.getSapNumber(), serObjSap.getAlternative(), serObjSap.getRev2(),
                        serObjSap.getRev10(), serObjSap.getSerialNumber());
                errorMsgs.add(errMsg);
                logger.error(errMsg);
            }
        }
        return materialRevision;
    }


    private SerialObject getOrCreateSerObj(SerialObjectRFCMappingType serObjSap, Map<String, SerialObject> serialObjectMap, Material material,
            ComparisonAnalysis analysis, String key) {
        SerialObject serialObject = serialObjectMap.get(key);
        if (serialObject == null) {
            // nicht in cache, also in DB suchen..
            serialObject = serialObjectManager.findBySerialNumberAndMaterialSapNr(
                    serObjSap.getSerialNumber(),
                    serObjSap.getsAPMaterialNumber());

            if (serialObject != null) {
                serialObjectMap.put(key, serialObject);
            }
        }
        if (serialObject == null) {
            // existiert nicht -> erstellen
            serialObject = new SerialObject();
            serialObject.setSerialNumber(serObjSap.getSerialNumber());
            serialObject.setMaterial(material);

            serialObject = serialObjectManager.persist(serialObject, true, true);
            serialObjectMap.put(key, serialObject);
            analysis.increaseNewCreatedSerialsInQdw();
        }
        return serialObject;
    }

    private SerialObject getOrCreateParentSerObj(SerialObjectRFCMappingType serObjSap, Map<String, SerialObject> serialObjectMap,
            Material parentMaterial, ComparisonAnalysis analysis, String parentKey) {
        SerialObject parentSerialObject = serialObjectMap.get(parentKey);
        if (parentSerialObject == null) {
            // nicht in cache, also in DB suchen..
            parentSerialObject = serialObjectManager.findBySerialNumberAndMaterialSapNr(
                    serObjSap.getParentSerialNumber(),
                    serObjSap.getParentSAPMaterialNumber());

            if (parentSerialObject != null) {
                serialObjectMap.put(parentKey, parentSerialObject);
            }
        }
        if (parentSerialObject == null) {
            // existiert nicht -> erstellen
            parentSerialObject = new SerialObject();
            parentSerialObject.setSerialNumber(serObjSap.getParentSerialNumber());
            parentSerialObject.setMaterial(parentMaterial);

            parentSerialObject = serialObjectManager.persist(parentSerialObject, true, true);
            serialObjectMap.put(parentKey, parentSerialObject);
            analysis.increaseNewCreatedSerialsInQdw();
        }
        return parentSerialObject;
    }



    class ComparisonAnalysis {

        private long cntSerialNotFoundInSap = 0;
        private long cntNoMaterialDataInSap = 0;
        private long cntNoRevDataInSap = 0;
        private long cntMatNotFoundInQdw = 0;
        private long cntRevNotFoundInQdw = 0;
        private long cntNewCreatedSerialsInQdw = 0;

        void increaseSerialNotFoundInSap() {
            cntSerialNotFoundInSap++;
        }

        void increaseNoMaterialDataInSap() {
            cntNoMaterialDataInSap++;
        }

        void increaseNoRevDataInSap() {
            cntNoRevDataInSap++;
        }

        void increaseMatNotFoundInQdw() {
            cntMatNotFoundInQdw++;
        }

        void increaseRevNotFoundInQdw() {
            cntRevNotFoundInQdw++;
        }

        void increaseNewCreatedSerialsInQdw() {
            cntNewCreatedSerialsInQdw++;
        }

        public long getCntSerialNotFoundInSap() {
            return cntSerialNotFoundInSap;
        }

        public long getCntNoMaterialDataInSap() {
            return cntNoMaterialDataInSap;
        }

        public long getCntNoRevDataInSap() {
            return cntNoRevDataInSap;
        }

        public long getCntMatNotFoundInQdw() {
            return cntMatNotFoundInQdw;
        }

        public long getCntRevNotFoundInQdw() {
            return cntRevNotFoundInQdw;
        }

        public long getCntNewCreatedSerialsInQdw() {
            return cntNewCreatedSerialsInQdw;
        }

    }

}
