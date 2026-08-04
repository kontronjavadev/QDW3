package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.TaskCall;
import com.kontron.qdw.boundary.service.mapping.serialobject.SerialObjectRFCMappingType;
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
import com.kontron.util.batch.MultiThreadHelper;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;
import com.kontron.util.version.RevisionUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
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
    private static final int DAYS_DELTA_STRUCTURE_ANALYSIS = 90;
    // private static final int DAYS_DELTA_STRUCTURE_ANALYSIS = 3;

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



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("serial object delta structure analysis for SAP comparison");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    public void execTask(TaskNodeLog ownTask) {
        Set<Long> serObjIds = execCollectSerObj(ownTask);

        logger.info("{} serial object ids found", serObjIds.size());
        if (!serObjIds.isEmpty()) {
            // per BAPI auf SAP zugreifen und abgleichen.
            // siehe com.kontron.qdw.integration.serial.SerialObjectExchangeService#performDeltaSerialObjectStructureAnalysis
            // und com.kontron.qdw.integration.rfc.bean.SerialObjectStructureRFCServiceBean
            // Bsp.: 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280
            execSapComparison(ownTask, serObjIds);
        }
    }



    protected Set<Long> execCollectSerObj(TaskNodeLog ownTask) {
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
            sql = "select distinct a.serialObject.id from ServiceMessage a where a.creationDate > :paramCreationDate or a.lastUpdate > :paramLastUpdate";
            idList.addAll(em.createQuery(sql, Long.class)
                    .setParameter("paramCreationDate", thresholdDate)
                    .setParameter("paramLastUpdate", thresholdDate)
                    .getResultList());
            leaf.finishTaskWithSuccess();

            // get all new and updated repairs of mentioned interval
            leaf = subTsk.createNewSubTaskLeaf("select serial object ids from x2 message");
            sql = "select distinct a.serialObject.id from X2Message a where a.creationDate > :paramCreationDate or a.lastUpdate > :paramLastUpdate";
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



    protected void execSapComparison(TaskNodeLog ownTask, Set<Long> serObjIds) {
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



        // SerialObjects zu den IDs aus der DB holen; Materialien und Revisionen cachen
        String stmt = "select s "
                + "from SerialObject s "
                + "where s.id in :serObjIds "
                + "order by s.id ";

        List<SerialObject> serialObjects;
        try {
            serialObjects = MultiThreadHelper.executeInBatches(
                    // Function wird in einer Schleife jeweils mit einer Teilmenge der Ids ausgeführt
                    m -> em.createQuery(stmt, SerialObject.class)
                            .setHint("org.hibernate.fetchSize", 2000)
                            .setParameter("serObjIds", m)
                            .getResultList(),
                    new ArrayList<>(serObjIds), 2000);
        }
        catch (Exception e) {
            tskRFC.finishTaskWithError(e);
            return;
        }

        // serialObjects.sort(Comparator.comparingLong(SerialObject::getId));


        // Materialien und Revisionen cachen
        // TODO: wozu genau werden die gecached, wo SerObj.-Entität die doch schon mitbringt?
        // Werden nur die gecached, die zusätzlich erstellt werden müssen?
        // TODO: das kann vermutlich alles in compareBulk und dort werden nur die behandelt, die zum aktuellen bulk gehören
        Map<String, SerialObject> serialObjectMap = new HashMap<>();
        Map<String, Material> materialCache = new HashMap<>();
        Map<String, MaterialRevision> materialRevisionMap = new HashMap<>();
        for (SerialObject serialObject : serialObjects) {
            serialObjectMap.put(serialObject.getSerialNumber() + ";" + serialObject.getMaterial().getSapNumber(), serialObject);
            materialCache.put(serialObject.getMaterial().getSapNumber(), serialObject.getMaterial());
            for (MaterialRevision revision : serialObject.getMaterial().getRevisions()) {
                materialRevisionMap.put(serialObject.getMaterial().getId() + ";;" + revision.getRevisionNumber(), revision);
            }
        }



        List<String> errorMsgs = new ArrayList<>();

        // ####################################################################################################
        // ####################################################################################################
        // ####################################################################################################

        BulkProcess bulkProcess = new BulkProcess(serialObjects.size(), 100);


        ComparisonAnalysis analysis = new ComparisonAnalysis();
        // long start = System.currentTimeMillis();
        while (bulkProcess.getBulkToIdx() - bulkProcess.getBulkFromIdx() > 0) {
            // in 100-er Schritten verarbeiten

            // TODO: vielleicht interessant!
            // // den verarbeiteten Schritt protokollieren
            // if (soFromIdx > 0) {
            // long duration = System.currentTimeMillis() - start; // verstrichene Zeit (ms)
            // long expectedDuration = duration * serialObjects.size() / soFromIdx; // erwartete Dauer (ms) für alle Einträge
            // Date expectedEnd = new Date(start + expectedDuration);
            // double performance = soFromIdx * 60000.0 / duration; // Einträge / min
            // logger.info(String.format(
            // "runSerialObjectStructureRFC(): processing serial object %s - %s of %s (%.1f per minute; expected duration: %s; expected end: %s",
            // (soFromIdx + 1), soToIdx, serialObjects.size(), performance, TimeUtil.toBestPracticeStringShort(expectedDuration),
            // DateUtil.dateToString(expectedEnd, DateUtil.FORMAT_PATTERN_GERMAN_DATE_TIME)));
            // }
            // else {
            // logger.info(String.format("runSerialObjectStructureRFC(): processing serial object %s - %s of %s",
            // (soFromIdx + 1), soToIdx, serialObjects.size()));
            // }

            try {
                compareBulk(destination, function, serialObjects,
                        serialObjectMap, materialCache, materialRevisionMap,
                        bulkProcess, tskRFC, errorMsgs, analysis);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tskRFC.finishTaskWithError(e);
                return;
            }

            // nächster bulk
            bulkProcess.nextBulk();
            em.flush();
            em.clear();
        } // end while bulk


        // ####################################################################################################
        // ####################################################################################################
        // ####################################################################################################


        StringBuilder sb = new StringBuilder();
        sb.append(serialObjects.size()).append(" serial number compared, ");
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



    private void compareBulk(JCoDestination destination, JCoFunction function, List<SerialObject> serialObjects,
            Map<String, SerialObject> serialObjectMap, Map<String, Material> materialCache, Map<String, MaterialRevision> materialRevisionMap,
            BulkProcess bulkProcess, TaskLeafLog tskRFC, List<String> errorMsgs, ComparisonAnalysis analysis) {
        // aktuell verarbeiteter Batch
        List<SerialObject> curBatch = serialObjects.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());

        // TODO: Map mit Materialien nach SAP-Nummern des bulks aufbauen
        // Map mit Materialien und Parent-Materialien aus dem SerObj. nach SAP-Nummer erstellen.
        // Zu SerialNo und SapNo wird SAP abgefragt. Die SAP-Nummer sollte überein stimmen
        for (SerialObject serObj : curBatch) {
            bulkProcess.logProcess(logger);

            try {
                compareEntry(destination, function, serialObjectMap, materialCache, materialRevisionMap, errorMsgs, analysis, serObj);
            }
            catch (Exception e) {
                // brauchbare Information in Exception packen
                throw new RuntimeException(String.format("Error at serial number %s: %s", serObj.getSerialNumber(), e.getMessage()), e);
            }

            bulkProcess.nextCnt();
        } // end for serialObjects
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
            SerialObjectRFCMappingType mappingObject = SerialObjectRFCMappingType.getInstanceBySapTable(table);

            // Ignore empty sets
            if (mappingObject.getSerialNumber().isEmpty() || mappingObject.getParentSerialNumber().isEmpty()) {
                continue;
            }

            if (mappingObject.getAssemblyDate() == null) {
                continue;
            }

            if (mappingObject.getsAPMaterialNumber().isEmpty()) {
                analysis.increaseNoMaterialDataInSap();
                continue;
            }



            // Material
            Material material = materialCache.get(mappingObject.getsAPMaterialNumber());
            if (material == null) {
                // nicht in cache, also in DB suchen..
                material = materialManager.findBySapNumber(mappingObject.getsAPMaterialNumber());
                if (material != null) {
                    materialCache.put(mappingObject.getsAPMaterialNumber(), material);
                }
            }
            if (material == null) {
                // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
                analysis.increaseMatNotFoundInQdw();
                String errMsg = String.format("Material %s not exists in QDW (serial %s)",
                        mappingObject.getsAPMaterialNumber(), mappingObject.getSerialNumber());
                errorMsgs.add(errMsg);
                logger.error(errMsg);
                continue;
            }



            // Parent-Material
            Material parentMaterial = materialCache.get(mappingObject.getParentSAPMaterialNumber());
            if (parentMaterial == null) {
                // nicht in cache, also in DB suchen..
                parentMaterial = materialManager.findBySapNumber(mappingObject.getParentSAPMaterialNumber());
                if (material != null) {
                    materialCache.put(mappingObject.getParentSAPMaterialNumber(), parentMaterial);
                }
            }
            if (parentMaterial == null) {
                // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
                analysis.increaseMatNotFoundInQdw();
                String errMsg = String.format("Parent material %s not exists in QDW (serial %s)",
                        mappingObject.getParentSAPMaterialNumber(), mappingObject.getParentSerialNumber());
                errorMsgs.add(errMsg);
                logger.error(errMsg);
                continue;
            }



            // Revision
            String gpeRevisionNumber = RevisionUtil.calculateRevNumberBySapRevNumber(
                    mappingObject.getAlternative(), mappingObject.getRev2(), mappingObject.getRev10());
            if (gpeRevisionNumber.equals(RevisionUtil.REV_NUMBER_UNDEF)) {
                analysis.increaseNoRevDataInSap();
                continue;
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
                String rev10 = mappingObject.getRev10();
                String rev6 = RevisionUtil.extractGPERev6FromSAPRev10(rev10);

                for (MaterialRevision r : material.getRevisions()) {
                    if (r.getRev6() != null) {
                        if (r.getRev6().equals(rev6) || r.getRev6().equals(rev10)) {
                            materialRevision = r;
                            break;
                        }
                    }
                    if (r.getRev2() != null) {
                        if (r.getRev2().equals(mappingObject.getRev2())) {
                            materialRevision = r;
                            break;
                        }
                    }
                }

                if (materialRevision != null) {
                    materialRevisionMap.put(revKey, materialRevision);
                }
            }
            if (materialRevision == null) {
                // existiert nicht -> Fehler und weiter mit dem nächsten Eintrag
                analysis.increaseRevNotFoundInQdw();
                String errMsg = String.format(
                        "Rev %s of material %s not exists in QDW (revAlt: %s, rev2: %s, rev10: %s) (serial %s)",
                        gpeRevisionNumber, material.getSapNumber(), mappingObject.getAlternative(), mappingObject.getRev2(),
                        mappingObject.getRev10(), mappingObject.getSerialNumber());
                errorMsgs.add(errMsg);
                logger.error(errMsg);
                continue;
            }



            // SerialObject
            String key = mappingObject.getSerialNumber() + ";" + mappingObject.getsAPMaterialNumber();
            SerialObject serialObject = serialObjectMap.get(key);
            if (serialObject == null) {
                // nicht in cache, also in DB suchen..
                serialObject = serialObjectManager.findBySerialNumberAndMaterialSapNr(
                        mappingObject.getSerialNumber(),
                        mappingObject.getsAPMaterialNumber());

                if (serialObject != null) {
                    serialObjectMap.put(key, serialObject);
                }
            }
            if (serialObject == null) {
                // existiert nicht -> erstellen
                serialObject = new SerialObject();
                serialObject.setSerialNumber(mappingObject.getSerialNumber());
                serialObject.setMaterial(material);

                serialObject = serialObjectManager.persist(serialObject, true, true);
                serialObjectMap.put(key, serialObject);
                analysis.increaseNewCreatedSerialsInQdw();
            }

            LocalDate sapAssemblyDateAsLocalDate = mappingObject.getAssemblyDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            serialObject.setAssemblyDate(sapAssemblyDateAsLocalDate);
            serialObject.setProductionOrderNumber(mappingObject.getProductionOrderNumber());



            // Parent-SerialObject
            String parentKey = mappingObject.getParentSerialNumber() + ";" + mappingObject.getParentSAPMaterialNumber();
            SerialObject parentSerialObject = serialObjectMap.get(parentKey);
            if (parentSerialObject == null) {
                // nicht in cache, also in DB suchen..
                parentSerialObject = serialObjectManager.findBySerialNumberAndMaterialSapNr(
                        mappingObject.getParentSerialNumber(),
                        mappingObject.getParentSAPMaterialNumber());

                if (parentSerialObject != null) {
                    serialObjectMap.put(parentKey, parentSerialObject);
                }
            }
            if (parentSerialObject == null) {
                // existiert nicht -> erstellen
                parentSerialObject = new SerialObject();
                parentSerialObject.setSerialNumber(mappingObject.getParentSerialNumber());
                parentSerialObject.setMaterial(parentMaterial);

                parentSerialObject = serialObjectManager.persist(parentSerialObject, true, true);
                serialObjectMap.put(parentKey, parentSerialObject);
                analysis.increaseNewCreatedSerialsInQdw();
            }

            serialObject.setParentObject(parentSerialObject);



            // Clear old assembly records
            // serialObject.getAssemblyRecords().clear();
            // serialObject = serialObjectManager.mergeSerialObject(serialObject, false, true);

            boolean assemblyRecordFound = false;
            AssemblyRecord rec = null;

            for (AssemblyRecord r : parentSerialObject.getAssemblyRecords()) {
                if (r.getSerialObject().getId() == serialObject.getId()) {
                    rec = r;
                    assemblyRecordFound = true;
                    break;
                }
            }

            if (rec == null) {
                rec = new AssemblyRecord();
                rec.setParentSerialObject(parentSerialObject);
                rec.setSerialObject(serialObject);
            }

            rec.setAssemblyDate(sapAssemblyDateAsLocalDate);
            rec.setProductionOrderNumber(mappingObject.getProductionOrderNumber());
            rec.setMaterialRevision(materialRevision);

            if (!assemblyRecordFound) {
                rec = assemblyRecordManager.persist(rec, true, true);
                // parent = serialObjectManager.mergeSerialObject(parent, false, true);
            }


            createOrUpdateMaterializedAssemblyShipment(rec);

            serialObjectMap.put(key, serialObject);
            serialObjectMap.put(parentKey, parentSerialObject);

        } // end for SAP-Tabellenspalten
    }



    private void createOrUpdateMaterializedAssemblyShipment(AssemblyRecord rec) throws ConstraintViolationException {
        // check if object already exists
        if (matrlizedAssblyShiptManager.existsById(rec.getId())) {
            return;
        }

        SerialObject shippedObject = rec.getParentSerialObject();
        while (true) {
            if (shippedObject.getParentObject() == null) {
                break;
            }
            else {
                shippedObject = shippedObject.getParentObject();
            }
        }

        // get min shipment after production
        Shipment s = null;
        for (Shipment sh : shippedObject.getShipments()) {
            if (s == null) {
                if (!sh.getShipmentDate().isBefore(rec.getAssemblyDate())) { // after || equal
                    s = sh;
                    continue;
                }
            }
            else {
                if (!sh.getShipmentDate().isBefore(rec.getAssemblyDate())) { // before || equal
                    if (sh.getShipmentDate().isBefore(s.getShipmentDate())) {
                        s = sh;
                        continue;
                    }
                }
            }
        }

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
        Arrival a = null;
        for (Arrival arr : shippedObject.getArrivals()) {
            if (a == null) {
                if (!arr.getArrivalDate().isAfter(rec.getAssemblyDate())) { // before || equal
                    a = arr;
                    continue;
                }
            }
            else {
                if (!arr.getArrivalDate().isAfter(rec.getAssemblyDate())) { // before || equal
                    if (arr.getArrivalDate().isAfter(a.getArrivalDate())) {
                        a = arr;
                        continue;
                    }
                }
            }
        }

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
