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
        // execAddIndex(ownTask, "arrival_shipment_mv_new");
        execUpdateArrDate(ownTask, "arrival_shipment_mv_new");

        execAddIndices(ownTask);
        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);

        execRemoveCanceled(ownTask);
    }



    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        logger.info("create indices");
        String indexCommand = "ALTER TABLE arrival_shipment_mv_new "
                + "ADD INDEX IN_AS_ID(id)"
                + ", ADD INDEX IN_AS_SNR_ID(serial_object_id)"
                + ", ADD INDEX IN_AS_PSNR_ID(parent_serial_object_id)"
                + ", ADD INDEX IN_AS_SNR_NO(serial_number)"
                + ", ADD INDEX IN_AS_PSNR_NO(parent_serial_number)"
                + ", ADD INDEX IN_AS_MAT_NO(material_number)"
                + ", ADD INDEX IN_AS_PMAT_NO(parent_material_number)"
                + ", ADD INDEX IN_AS_MAT_TYPE(material_type)"
                + ", ADD INDEX IN_AS_PMAT_TYPE(parent_material_type)"
                + ", ADD INDEX IN_AS_MAT_STEXT(material_short_text)"
                + ", ADD INDEX IN_AS_PMAT_STEXT(parent_material_short_text)"
                + ", ADD INDEX IN_AS_SAP_NO(sap_no)"
                + ", ADD INDEX IN_AS_PSAP_NO(parent_sap_no)"
                + ", ADD INDEX IN_AS_HIERARC(material_hierarchy)"
                + ", ADD INDEX IN_AS_PHIERARC(parent_material_hierarchy)"
                + ", ADD INDEX IN_AS_REV_ID(revision_id)"
                + ", ADD INDEX IN_AS_PREV_ID(parent_revision_id)"
                + ", ADD INDEX IN_AS_REV_NO(revision_no)"
                + ", ADD INDEX IN_AS_PREV_NO(parent_revision_no)"
                + ", ADD INDEX IN_AS_ASS_DATE(assembly_date)"
                + ", ADD INDEX IN_AS_ASS_PO(assembly_po)"
                + ", ADD INDEX IN_AS_CUST_CODE(customer_code)"
                + ", ADD INDEX IN_AS_CUST_NAME(customer_name)"
                + ", ADD INDEX IN_AS_COUNTRY_CODE(country_code)"
                + ", ADD INDEX IN_AS_COUNTRY_NAME(country_name)"
                + ", ADD INDEX IN_AS_SHIP_DATE(shipment_date)"
                + ", ADD INDEX IN_AS_ARR_DATE(arrival_date)"
                + ", ADD INDEX IN_AS_SUP_CODE(supplier_code)"
                + ", ADD INDEX IN_AS_SUP_NAME(supplier_name)"
                + ", ADD INDEX IN_AS_PLANT(plant)"
                + ", ADD INDEX IN_AS_SHIP_MT(shipment_movement_type)"
                + ", ADD INDEX IN_AS_ARR_MT(arrival_movement_type)"
                + ", ADD INDEX IN_AS_CUST_ORDER(customer_order_number)"
                + ", ADD INDEX IN_AS_PURCH_ORDER(purchase_order_number)"
                + ", ADD INDEX IN_AS_ARR_ID(arrival_id)"
                + ", ADD INDEX IN_AS_MATERIAL(material)"
                + ", ADD INDEX IN_AS_SNR(serial_object)";

        em.createNativeQuery(indexCommand).executeUpdate();
        subTsk.finishTaskWithSuccess();
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

}
