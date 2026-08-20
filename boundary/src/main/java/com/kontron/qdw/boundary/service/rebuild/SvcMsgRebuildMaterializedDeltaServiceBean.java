package com.kontron.qdw.boundary.service.rebuild;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.process.TaskCall;
import com.kontron.qdw.domain.service.ServiceMessage;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

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
public class SvcMsgRebuildMaterializedDeltaServiceBean extends AbstractSvcMsgRebuildMaterializedServiceBean implements TaskCall {

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
        execDrop(ownTask, "service_message_mv_tmp_delta");
        execCreate(ownTask, "service_message_mv_tmp_delta", true);
        execAddColumns(ownTask, "service_message_mv_tmp_delta");
        execUpdate(ownTask, "service_message_mv_tmp_delta");

        execCopyData(ownTask);
        execResetRebuild(ownTask);
    }



    private void execCopyData(TaskNodeLog ownTask) {
        String executionSection = "copy data from service_message_mv_tmp_delta to service_message_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        List<String> columns_service_message_mv = new ArrayList<>();
        columns_service_message_mv.add("id");
        columns_service_message_mv.add("serial_object_id");
        columns_service_message_mv.add("parent_serial_object_id");
        columns_service_message_mv.add("serial_number");
        columns_service_message_mv.add("parent_serial_number");
        columns_service_message_mv.add("material_number");
        columns_service_message_mv.add("parent_material_number");
        columns_service_message_mv.add("material_type");
        columns_service_message_mv.add("parent_material_type");
        columns_service_message_mv.add("material_short_text");
        columns_service_message_mv.add("parent_material_short_text");
        columns_service_message_mv.add("sap_no");
        columns_service_message_mv.add("parent_sap_no");
        columns_service_message_mv.add("material_hierarchy");
        columns_service_message_mv.add("parent_material_hierarchy");
        columns_service_message_mv.add("revision_id");
        columns_service_message_mv.add("revision_no");
        columns_service_message_mv.add("assembly_date");
        columns_service_message_mv.add("assembly_po");
        columns_service_message_mv.add("plant");
        columns_service_message_mv.add("service_order");
        columns_service_message_mv.add("rma_type");
        columns_service_message_mv.add("location");
        columns_service_message_mv.add("service_name");
        columns_service_message_mv.add("task_name");
        columns_service_message_mv.add("repair_task_short_text");
        columns_service_message_mv.add("state_name");
        columns_service_message_mv.add("internal_arrival_date");
        columns_service_message_mv.add("internal_shipment_date");
        columns_service_message_mv.add("basic_start_date");
        columns_service_message_mv.add("basic_end_date");
        columns_service_message_mv.add("designator");
        columns_service_message_mv.add("defect_component");
        columns_service_message_mv.add("analysis_text");
        columns_service_message_mv.add("internal_report");
        columns_service_message_mv.add("external_report");
        columns_service_message_mv.add("customer_report");
        columns_service_message_mv.add("epidemic_failure");
        columns_service_message_mv.add("error_id");
        columns_service_message_mv.add("origin");
        columns_service_message_mv.add("customer_failure");
        columns_service_message_mv.add("customer_code");
        columns_service_message_mv.add("customer_name");
        columns_service_message_mv.add("customer_group");
        columns_service_message_mv.add("country_code");
        columns_service_message_mv.add("country_name");
        columns_service_message_mv.add("fault_analysis_code");
        columns_service_message_mv.add("symptom_short_text");
        columns_service_message_mv.add("error_code_name");
        columns_service_message_mv.add("error_code_group");
        columns_service_message_mv.add("error_short_text");
        columns_service_message_mv.add("external_supplier_code");
        columns_service_message_mv.add("external_supplier_name");
        columns_service_message_mv.add("delivery_note_number");
        columns_service_message_mv.add("repair_description");
        columns_service_message_mv.add("service_order_type");
        columns_service_message_mv.add("material");
        columns_service_message_mv.add("serial_object");
        columns_service_message_mv.add("owner_location");
        columns_service_message_mv.add("parent_revision_id");
        columns_service_message_mv.add("parent_revision_no");
        columns_service_message_mv.add("sup_arrival_date");
        columns_service_message_mv.add("cust_ship_date");
        columns_service_message_mv.add("supplier_code");
        columns_service_message_mv.add("supplier_name");

        String colForUpdateSet = columns_service_message_mv.stream()
                .map(columnName -> String.format("smmv.%s = smmvdelta.%s", columnName, columnName))
                .collect(Collectors.joining(", "));

        String colForInsertList = StringUtil.collectionToSqlWhereInString(columns_service_message_mv);


        StringBuilder sql = new StringBuilder();
        // vorhandene Einträge aktualisieren (rebuild for updated entries)
        sql.append("update service_message_mv smmv, service_message_mv_tmp_delta smmvdelta ");
        sql.append("set ").append(colForUpdateSet).append(" ");
        sql.append("where smmv.id = smmvdelta.id ");
        sql.append("and smmvdelta.rebuild_flag = ").append(ServiceMessage.REBUILD_FOR_UPDATED_ENTRY).append(" ");

        em.createNativeQuery(sql.toString()).executeUpdate();


        // neue Einträge kopieren (rebuild for new entries)
        sql = new StringBuilder();
        sql.append("insert into service_message_mv (");
        sql.append(colForInsertList);
        sql.append(") ");
        sql.append("select ");
        sql.append(colForInsertList).append(" ");
        sql.append("from service_message_mv_tmp_delta ");
        sql.append("where rebuild_flag = ").append(ServiceMessage.REBUILD_FOR_NEW_ENTRY);

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execResetRebuild(TaskNodeLog ownTask) {
        String executionSection = "reset rebuild flag in service messages";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        // -> Um zu vermeiden, dass das rebuild-flag auch von zwischenzeitlich geänderten Werten
        // zurück gesetzt wird, das Ganze auf die zuvor eingelesenen Einträge beschränken.
        // Die Einschränkung auf das rebuild-flag wird damit obsolet.
        // -> Für die Filterung auf die temporäre Tabelle service_message_mv_tmp_delta muss sinnvollerweise
        // ein Index erstellt werden.

        em.createNativeQuery("ALTER TABLE service_message_mv_tmp_delta ADD INDEX idx_svcmsg_mv_tmp_delta(id)").executeUpdate();


        StringBuilder sql = new StringBuilder();
        sql.append("update service_message_tab a ");
        sql.append("inner join service_message_mv_tmp_delta b on a.id = b.id ");
        sql.append("set a.rebuild_flag = 0 ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
