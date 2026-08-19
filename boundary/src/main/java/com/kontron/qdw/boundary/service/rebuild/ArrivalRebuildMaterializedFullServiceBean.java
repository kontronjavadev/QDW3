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
        execAddColumns(ownTask, "materialized_arrival_mv_new");

        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);
        execAddIndices(ownTask);

        execRemoveCanceled(ownTask);
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

    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        String indexCommand = "ALTER TABLE materialized_arrival_mv ADD INDEX ";

        logger.info("create index 1");
        StringBuilder sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_ID(id)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 2");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_SNR_ID(serial_object_id)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 3");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PSNR_ID(parent_serial_object_id)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 4");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_SNR_NO(serial_number)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 5");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PSNR_NO(parent_serial_number)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 6");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_MAT_NO(material_number)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 7");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PMAT_NO(parent_material_number)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 8");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_MAT_TYPE(material_type)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 9");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PMAT_TYPE(parent_material_type)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 10");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_MAT_STEXT(material_short_text)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 11");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PMAT_STEXT(parent_material_short_text)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 12");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_SAP_NO(sap_no)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 13");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PSAP_NO(parent_sap_no)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 14");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_HIERARC(material_hierarchy)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 15");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PHIERARC(parent_material_hierarchy)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 16");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_REV_ID(revision_id)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 17");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PREV_ID(parent_revision_id)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 18");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_REV_NO(revision_no)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 19");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PREV_NO(parent_revision_no)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 20");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_ASS_DATE(assembly_date)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 21");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_ASS_PO(assembly_po)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 22");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_ARR_DATE(arrival_date)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 23");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_SUP_CODE(supplier_code)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 24");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_SUP_NAME(supplier_name)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 25");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_COUNTRY_CODE(country_code)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 26");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_COUNTRY_NAME(country_name)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 27");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PLANT(plant)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 28");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_ARR_MT(movement_type)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 29");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_PURCH_ORDER(order_number)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 30");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_MATERIAL(material)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("create index 31");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_A_SNR(serial_object)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        subTsk.finishTaskWithSuccess();
    }

}
