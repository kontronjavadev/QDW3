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
 * Rebuild der Shipment-Arrival-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class ShipmentArrivalRebuildMaterializedFullServiceBean extends AbstractShipmentArrivalRebuildMaterializedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("shipment arrival rebuild materialized");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask, "arrival_shipment_mv_new");
        execCreate(ownTask, "arrival_shipment_mv_new", false);
        execAddColumns(ownTask, "arrival_shipment_mv_new");
        execUpdateArrId(ownTask, "arrival_shipment_mv_new");
        execAddIndex(ownTask, "arrival_shipment_mv_new");
        execUpdateArrDate(ownTask, "arrival_shipment_mv_new");
        execDropIndex(ownTask, "arrival_shipment_mv_new");

        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);
        execAddIndices(ownTask);

        execRemoveCanceled(ownTask);
    }



    private void execDropFormer(TaskNodeLog ownTask) {
        String executionSection = "drop table arrival_shipment_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "drop table if exists arrival_shipment_mv";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRenameTmp2New(TaskNodeLog ownTask) {
        String executionSection = "rename arrival_shipment_mv_new to arrival_shipment_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "ALTER TABLE arrival_shipment_mv_new RENAME TO arrival_shipment_mv";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        String indexCommand = "ALTER TABLE arrival_shipment_mv ADD INDEX ";

        logger.info("create index 1");
        em.createNativeQuery(indexCommand + "IN_AS_ID(id)").executeUpdate();

        logger.info("create index 2");
        em.createNativeQuery(indexCommand + "IN_AS_SNR_ID(serial_object_id)").executeUpdate();

        logger.info("create index 3");
        em.createNativeQuery(indexCommand + "IN_AS_PSNR_ID(parent_serial_object_id)").executeUpdate();

        logger.info("create index 4");
        em.createNativeQuery(indexCommand + "IN_AS_SNR_NO(serial_number)").executeUpdate();

        logger.info("create index 5");
        em.createNativeQuery(indexCommand + "IN_AS_PSNR_NO(parent_serial_number)").executeUpdate();

        logger.info("create index 6");
        em.createNativeQuery(indexCommand + "IN_AS_MAT_NO(material_number)").executeUpdate();

        logger.info("create index 7");
        em.createNativeQuery(indexCommand + "IN_AS_PMAT_NO(parent_material_number)").executeUpdate();

        logger.info("create index 8");
        em.createNativeQuery(indexCommand + "IN_AS_MAT_TYPE(material_type)").executeUpdate();

        logger.info("create index 9");
        em.createNativeQuery(indexCommand + "IN_AS_PMAT_TYPE(parent_material_type)").executeUpdate();

        logger.info("create index 10");
        em.createNativeQuery(indexCommand + "IN_AS_MAT_STEXT(material_short_text)").executeUpdate();

        logger.info("create index 11");
        em.createNativeQuery(indexCommand + "IN_AS_PMAT_STEXT(parent_material_short_text)").executeUpdate();

        logger.info("create index 12");
        em.createNativeQuery(indexCommand + "IN_AS_SAP_NO(sap_no)").executeUpdate();

        logger.info("create index 13");
        em.createNativeQuery(indexCommand + "IN_AS_PSAP_NO(parent_sap_no)").executeUpdate();

        logger.info("create index 14");
        em.createNativeQuery(indexCommand + "IN_AS_HIERARC(material_hierarchy)").executeUpdate();

        logger.info("create index 15");
        em.createNativeQuery(indexCommand + "IN_AS_PHIERARC(parent_material_hierarchy)").executeUpdate();

        logger.info("create index 16");
        em.createNativeQuery(indexCommand + "IN_AS_REV_ID(revision_id)").executeUpdate();

        logger.info("create index 17");
        em.createNativeQuery(indexCommand + "IN_AS_PREV_ID(parent_revision_id)").executeUpdate();

        logger.info("create index 18");
        em.createNativeQuery(indexCommand + "IN_AS_REV_NO(revision_no)").executeUpdate();

        logger.info("create index 19");
        em.createNativeQuery(indexCommand + "IN_AS_PREV_NO(parent_revision_no)").executeUpdate();

        logger.info("create index 20");
        em.createNativeQuery(indexCommand + "IN_AS_ASS_DATE(assembly_date)").executeUpdate();

        logger.info("create index 21");
        em.createNativeQuery(indexCommand + "IN_AS_ASS_PO(assembly_po)").executeUpdate();

        logger.info("create index 22");
        em.createNativeQuery(indexCommand + "IN_AS_CUST_CODE(customer_code)").executeUpdate();

        logger.info("create index 23");
        em.createNativeQuery(indexCommand + "IN_AS_CUST_NAME(customer_name)").executeUpdate();

        logger.info("create index 24");
        em.createNativeQuery(indexCommand + "IN_AS_COUNTRY_CODE(country_code)").executeUpdate();

        logger.info("create index 25");
        em.createNativeQuery(indexCommand + "IN_AS_COUNTRY_NAME(country_name)").executeUpdate();

        logger.info("create index 26");
        em.createNativeQuery(indexCommand + "IN_AS_SHIP_DATE(shipment_date)").executeUpdate();

        logger.info("create index 27");
        em.createNativeQuery(indexCommand + "IN_AS_ARR_DATE(arrival_date)").executeUpdate();

        logger.info("create index 28");
        em.createNativeQuery(indexCommand + "IN_AS_SUP_CODE(supplier_code)").executeUpdate();

        logger.info("create index 29");
        em.createNativeQuery(indexCommand + "IN_AS_SUP_NAME(supplier_name)").executeUpdate();

        logger.info("create index 30");
        em.createNativeQuery(indexCommand + "IN_AS_PLANT(plant)").executeUpdate();

        logger.info("create index 31");
        em.createNativeQuery(indexCommand + "IN_AS_SHIP_MT(shipment_movement_type)").executeUpdate();

        logger.info("create index 32");
        em.createNativeQuery(indexCommand + "IN_AS_ARR_MT(arrival_movement_type)").executeUpdate();

        logger.info("create index 33");
        em.createNativeQuery(indexCommand + "IN_AS_CUST_ORDER(customer_order_number)").executeUpdate();

        logger.info("create index 34");
        em.createNativeQuery(indexCommand + "IN_AS_PURCH_ORDER(purchase_order_number)").executeUpdate();

        logger.info("create index 35");
        em.createNativeQuery(indexCommand + "IN_AS_ARR_ID(arrival_id)").executeUpdate();

        logger.info("create index 36");
        em.createNativeQuery(indexCommand + "IN_AS_MATERIAL(material)").executeUpdate();

        logger.info("create index 37");
        em.createNativeQuery(indexCommand + "IN_AS_SNR(serial_object)").executeUpdate();

        subTsk.finishTaskWithSuccess();
    }

}
