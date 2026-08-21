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
 * Rebuild der Service message-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class SvcMsgRebuildMaterializedFullServiceBean extends AbstractSvcMsgRebuildMaterializedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("service message rebuild materialized");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask, "service_message_mv_new");
        execCreate(ownTask, "service_message_mv_new", true);
        execAddColumns(ownTask, "service_message_mv_new");

        execAddIndices(ownTask);
        execUpdate(ownTask, "service_message_mv_new");

        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);
        ownTask.finishTask();
    }



    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        logger.info("create indices");
        String indexCommand = "ALTER TABLE service_message_mv_new "
                + "add index IN_SM_ID (id)"
                + ", add index IN_SM_SNR_ID (serial_object_id)"
                + ", add index IN_SM_PSNR_ID (parent_serial_object_id)"
                + ", add index IN_SM_SNR_NO (serial_number)"
                + ", add index IN_SM_PSNR_NO (parent_serial_number)"
                + ", add index IN_SM_MAT_NO (material_number)"
                + ", add index IN_SM_PMAT_NO (parent_material_number)"
                + ", add index IN_SM_MAT_TYPE (material_type)"
                + ", add index IN_SM_PMAT_TYPE (parent_material_type)"
                + ", add index IN_SM_MAT_STEXT (material_short_text)"
                + ", add index IN_SM_PMAT_STEXT (parent_material_short_text)"
                + ", add index IN_SM_SAP_NO (sap_no)"
                + ", add index IN_SM_PSAP_NO (parent_sap_no)"
                + ", add index IN_SM_HIERARC (material_hierarchy)"
                + ", add index IN_SM_PHIERARC (parent_material_hierarchy)"
                + ", add index IN_SM_REV_ID (revision_id)"
                + ", add index IN_SM_PREV_ID (parent_revision_id)"
                + ", add index IN_SM_REV_NO (revision_no)"
                + ", add index IN_SM_PREV_NO (parent_revision_no)"
                + ", add index IN_SM_ASS_DATE (assembly_date)"
                + ", add index IN_SM_ASS_PO (assembly_po)"
                + ", add index IN_SM_PLANT (plant)"
                + ", add index IN_SM_SERV_ORDER (service_order)"
                + ", add index IN_SM_RMA_TYPE (rma_type)"
                + ", add index IN_SM_LOCATION (location)"
                + ", add index IN_SM_SERVICE (service_name)"
                + ", add index IN_SM_TASK (task_name)"
                + ", add index IN_SM_STATE (state_name)"
                + ", add index IN_SM_INT_ADATE (internal_arrival_date)"
                + ", add index IN_SM_INT_SDATE (internal_shipment_date)"
                + ", add index IN_SM_BAS_SDATE (basic_start_date)"
                + ", add index IN_SM_BAS_EDATE (basic_end_date)"
                + ", add index IN_SM_DESIGNATOR (designator)"
                + ", add index IN_SM_DEF_COMP (defect_component)"
                + ", add index IN_SM_ANAL_TEXT (analysis_text)"
                + ", add index IN_SM_ERROR_ID (error_id)"
                + ", add index IN_SM_ORIGIN (origin)"
                + ", add index IN_SM_CUST_CODE (customer_code)"
                + ", add index IN_SM_CUST_NAME (customer_name)"
                + ", add index IN_SM_COUNTRY_CODE (country_code)"
                + ", add index IN_SM_COUNTRY_NAME (country_name)"
                + ", add index IN_SM_FAULT (fault_analysis_code)"
                + ", add index IN_SM_ERROR_CODE (error_code_name)"
                + ", add index IN_SM_ERROR_GROUP (error_code_group)"
                + ", add index IN_SM_EXT_SUP_CODE (external_supplier_code)"
                + ", add index IN_SM_EXT_SUP_NAME (external_supplier_name)"
                + ", add index IN_SM_DEL_NOTE_NO (delivery_note_number)"
                + ", add index IN_SM_SERV_ORDER_TYPE (service_order_type)"
                + ", add index IN_SM_MATERIAL (material)"
                + ", add index IN_SM_SNR (serial_object)"
                + ", add index IN_SM_SUP_ADATE (sup_arrival_date)"
                + ", add index IN_SM_CUST_SDATE (cust_ship_date)"
                + ", add index IN_SM_SUP_CODE (supplier_code)"
                + ", add index IN_SM_SUP_NAME (supplier_name)";

        em.createNativeQuery(indexCommand).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execDropFormer(TaskNodeLog ownTask) {
        String executionSection = "drop table service_message_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "drop table if exists service_message_mv";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRenameTmp2New(TaskNodeLog ownTask) {
        String executionSection = "rename service_message_mv_new to service_message_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "ALTER TABLE service_message_mv_new RENAME TO service_message_mv";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
