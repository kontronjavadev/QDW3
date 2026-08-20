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
        execUpdate(ownTask, "service_message_mv_new");

        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);
        execAddIndices(ownTask);
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

    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        String indexCommand = "ALTER TABLE service_message_mv ADD INDEX ";

        logger.info("create index 1");
        em.createNativeQuery(indexCommand + "IN_SM_ID(id)").executeUpdate();

        logger.info("create index 2");
        em.createNativeQuery(indexCommand + "IN_SM_SNR_ID(serial_object_id)").executeUpdate();

        logger.info("create index 3");
        em.createNativeQuery(indexCommand + "IN_SM_PSNR_ID(parent_serial_object_id)").executeUpdate();

        logger.info("create index 4");
        em.createNativeQuery(indexCommand + "IN_SM_SNR_NO(serial_number)").executeUpdate();

        logger.info("create index 5");
        em.createNativeQuery(indexCommand + "IN_SM_PSNR_NO(parent_serial_number)").executeUpdate();

        logger.info("create index 6");
        em.createNativeQuery(indexCommand + "IN_SM_MAT_NO(material_number)").executeUpdate();

        logger.info("create index 7");
        em.createNativeQuery(indexCommand + "IN_SM_PMAT_NO(parent_material_number)").executeUpdate();

        logger.info("create index 8");
        em.createNativeQuery(indexCommand + "IN_SM_MAT_TYPE(material_type)").executeUpdate();

        logger.info("create index 9");
        em.createNativeQuery(indexCommand + "IN_SM_PMAT_TYPE(parent_material_type)").executeUpdate();

        logger.info("create index 10");
        em.createNativeQuery(indexCommand + "IN_SM_MAT_STEXT(material_short_text)").executeUpdate();

        logger.info("create index 11");
        em.createNativeQuery(indexCommand + "IN_SM_PMAT_STEXT(parent_material_short_text)").executeUpdate();

        logger.info("create index 12");
        em.createNativeQuery(indexCommand + "IN_SM_SAP_NO(sap_no)").executeUpdate();

        logger.info("create index 13");
        em.createNativeQuery(indexCommand + "IN_SM_PSAP_NO(parent_sap_no)").executeUpdate();

        logger.info("create index 14");
        em.createNativeQuery(indexCommand + "IN_SM_HIERARC(material_hierarchy)").executeUpdate();

        logger.info("create index 15");
        em.createNativeQuery(indexCommand + "IN_SM_PHIERARC(parent_material_hierarchy)").executeUpdate();

        logger.info("create index 16");
        em.createNativeQuery(indexCommand + "IN_SM_REV_ID(revision_id)").executeUpdate();

        logger.info("create index 17");
        em.createNativeQuery(indexCommand + "IN_SM_PREV_ID(parent_revision_id)").executeUpdate();

        logger.info("create index 18");
        em.createNativeQuery(indexCommand + "IN_SM_REV_NO(revision_no)").executeUpdate();

        logger.info("create index 19");
        em.createNativeQuery(indexCommand + "IN_SM_PREV_NO(parent_revision_no)").executeUpdate();

        logger.info("create index 20");
        em.createNativeQuery(indexCommand + "IN_SM_ASS_DATE(assembly_date)").executeUpdate();

        logger.info("create index 21");
        em.createNativeQuery(indexCommand + "IN_SM_ASS_PO(assembly_po)").executeUpdate();

        logger.info("create index 22");
        em.createNativeQuery(indexCommand + "IN_SM_PLANT(plant)").executeUpdate();

        logger.info("create index 23");
        em.createNativeQuery(indexCommand + "IN_SM_SERV_ORDER(service_order)").executeUpdate();

        logger.info("create index 24");
        em.createNativeQuery(indexCommand + "IN_SM_RMA_TYPE(rma_type)").executeUpdate();

        logger.info("create index 25");
        em.createNativeQuery(indexCommand + "IN_SM_LOCATION(location)").executeUpdate();

        logger.info("create index 26");
        em.createNativeQuery(indexCommand + "IN_SM_SERVICE(service_name)").executeUpdate();

        logger.info("create index 27");
        em.createNativeQuery(indexCommand + "IN_SM_TASK(task_name)").executeUpdate();

        logger.info("create index 28");
        em.createNativeQuery(indexCommand + "IN_SM_STATE(state_name)").executeUpdate();

        logger.info("create index 29");
        em.createNativeQuery(indexCommand + "IN_SM_INT_ADATE(internal_arrival_date)").executeUpdate();

        logger.info("create index 30");
        em.createNativeQuery(indexCommand + "IN_SM_INT_SDATE(internal_shipment_date)").executeUpdate();

        logger.info("create index 31");
        em.createNativeQuery(indexCommand + "IN_SM_BAS_SDATE(basic_start_date)").executeUpdate();

        logger.info("create index 32");
        em.createNativeQuery(indexCommand + "IN_SM_BAS_EDATE(basic_end_date)").executeUpdate();

        logger.info("create index 33");
        em.createNativeQuery(indexCommand + "IN_SM_DESIGNATOR(designator)").executeUpdate();

        logger.info("create index 34");
        em.createNativeQuery(indexCommand + "IN_SM_DEF_COMP(defect_component)").executeUpdate();

        logger.info("create index 35");
        em.createNativeQuery(indexCommand + "IN_SM_ANAL_TEXT(analysis_text)").executeUpdate();

        logger.info("create index 36");
        em.createNativeQuery(indexCommand + "IN_SM_ERROR_ID(error_id)").executeUpdate();

        logger.info("create index 37");
        em.createNativeQuery(indexCommand + "IN_SM_ORIGIN(origin)").executeUpdate();

        logger.info("create index 38");
        em.createNativeQuery(indexCommand + "IN_SM_CUST_CODE(customer_code)").executeUpdate();

        logger.info("create index 39");
        em.createNativeQuery(indexCommand + "IN_SM_CUST_NAME(customer_name)").executeUpdate();

        logger.info("create index 40");
        em.createNativeQuery(indexCommand + "IN_SM_CUST_GROUP(customer_group)").executeUpdate();

        logger.info("create index 41");
        em.createNativeQuery(indexCommand + "IN_SM_COUNTRY_CODE(country_code)").executeUpdate();

        logger.info("create index 42");
        em.createNativeQuery(indexCommand + "IN_SM_COUNTRY_NAME(country_name)").executeUpdate();

        logger.info("create index 43");
        em.createNativeQuery(indexCommand + "IN_SM_FAULT(fault_analysis_code)").executeUpdate();

        logger.info("create index 44");
        em.createNativeQuery(indexCommand + "IN_SM_ERROR_CODE(error_code_name)").executeUpdate();

        logger.info("create index 45");
        em.createNativeQuery(indexCommand + "IN_SM_ERROR_GROUP(error_code_group)").executeUpdate();

        logger.info("create index 46");
        em.createNativeQuery(indexCommand + "IN_SM_EXT_SUP_CODE(external_supplier_code)").executeUpdate();

        logger.info("create index 47");
        em.createNativeQuery(indexCommand + "IN_SM_EXT_SUP_NAME(external_supplier_name)").executeUpdate();

        logger.info("create index 48");
        em.createNativeQuery(indexCommand + "IN_SM_DEL_NOTE_NO(delivery_note_number)").executeUpdate();

        logger.info("create index 49");
        em.createNativeQuery(indexCommand + "IN_SM_SERV_ORDER_TYPE(service_order_type)").executeUpdate();

        logger.info("create index 50");
        em.createNativeQuery(indexCommand + "IN_SM_MATERIAL(material)").executeUpdate();

        logger.info("create index 51");
        em.createNativeQuery(indexCommand + "IN_SM_SNR(serial_object)").executeUpdate();

        logger.info("create index 52");
        em.createNativeQuery(indexCommand + "IN_SM_SUP_ADATE(sup_arrival_date)").executeUpdate();

        logger.info("create index 53");
        em.createNativeQuery(indexCommand + "IN_SM_CUST_SDATE(cust_ship_date)").executeUpdate();

        logger.info("create index 54");
        em.createNativeQuery(indexCommand + "IN_SM_SUP_CODE(supplier_code)").executeUpdate();

        logger.info("create index 55");
        em.createNativeQuery(indexCommand + "IN_SM_SUP_NAME(supplier_name)").executeUpdate();

        subTsk.finishTaskWithSuccess();
    }

}
