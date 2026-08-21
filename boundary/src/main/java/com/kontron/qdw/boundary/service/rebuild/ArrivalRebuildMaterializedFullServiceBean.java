package com.kontron.qdw.boundary.service.rebuild;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.process.TaskCall;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Rebuild der Arrival-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class ArrivalRebuildMaterializedFullServiceBean extends AbstractArrivalRebuildMaterializedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        // Es wird das Delta berechnet, also nur die Werte mit rebuildflag=1
        // Die ServiceMessages werden aktualisiert
        // Die Delta-Daten werden in die Materialized Table übernommen
        // Das rebuild-flag wird wieder zurück gesetzt
        // Gecancelte Daten werden gelöscht
        // Dauer: wenige Minuten
        return new TaskNodeLog("arrival rebuild materialized");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask, "materialized_arrival_mv_new");
        execCreate(ownTask, "materialized_arrival_mv_new", false);

        execAddIndices(ownTask);
        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);

        execRemoveCanceled(ownTask);
        ownTask.finishTask();
    }



    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        logger.info("create indices");
        String indexCommand = "ALTER TABLE materialized_arrival_mv_new "
                + "add index IN_A_ID (id)"
                // + ", add index IN_A_SNR_ID (serial_object_id)" // stattdessen erweiterten Index ganz unten nehmen
                + ", add index IN_A_PSNR_ID (parent_serial_object_id)"
                + ", add index IN_A_SNR_NO (serial_number)"
                + ", add index IN_A_PSNR_NO (parent_serial_number)"
                + ", add index IN_A_MAT_NO (material_number)"
                + ", add index IN_A_PMAT_NO (parent_material_number)"
                + ", add index IN_A_MAT_TYPE (material_type)"
                + ", add index IN_A_PMAT_TYPE (parent_material_type)"
                + ", add index IN_A_MAT_STEXT (material_short_text)"
                + ", add index IN_A_PMAT_STEXT (parent_material_short_text)"
                + ", add index IN_A_SAP_NO (sap_no)"
                + ", add index IN_A_PSAP_NO (parent_sap_no)"
                + ", add index IN_A_HIERARC (material_hierarchy)"
                + ", add index IN_A_PHIERARC (parent_material_hierarchy)"
                + ", add index IN_A_REV_ID (revision_id)"
                + ", add index IN_A_PREV_ID (parent_revision_id)"
                + ", add index IN_A_REV_NO (revision_no)"
                + ", add index IN_A_PREV_NO (parent_revision_no)"
                + ", add index IN_A_ASS_DATE (assembly_date)"
                + ", add index IN_A_ASS_PO (assembly_po)"
                + ", add index IN_A_ARR_DATE (arrival_date)"
                + ", add index IN_A_SUP_CODE (supplier_code)"
                + ", add index IN_A_SUP_NAME (supplier_name)"
                + ", add index IN_A_COUNTRY_CODE (country_code)"
                + ", add index IN_A_COUNTRY_NAME (country_name)"
                + ", add index IN_A_PLANT (plant)"
                + ", add index IN_A_ARR_MT (movement_type)"
                + ", add index IN_A_PURCH_ORDER (order_number)"
                + ", add index IN_A_MATERIAL (material)"
                + ", add index IN_A_SNR (serial_object)"
                + ", add index idx_ar_serobj_ordernr_mvttype_id (serial_object_id, order_number, movement_type, id)";

        em.createNativeQuery(indexCommand).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execDropFormer(TaskNodeLog ownTask) {
        String executionSection = "drop table materialized_arrival_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "drop table if exists materialized_arrival_mv";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRenameTmp2New(TaskNodeLog ownTask) {
        String executionSection = "rename materialized_arrival_mv_new to materialized_arrival_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "ALTER TABLE materialized_arrival_mv_new RENAME TO materialized_arrival_mv";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
