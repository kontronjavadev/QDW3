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
public class SvcMsgRebuildMaterializedDeltaServiceBean implements TaskCall {

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

        execUpdate(ownTask);
        execCopyData(ownTask);
        execResetRebuild(ownTask);

        // execRemoveCanceled(ownTask);
    }



    protected void execDrop(TaskNodeLog ownTask, String tableName) {
        String executionSection = "drop table " + tableName;
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "DROP TABLE IF EXISTS " + tableName;

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execCreate(TaskNodeLog ownTask, String tableName, boolean delta) {
        String executionSection = "create table " + tableName;
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("create table ").append(tableName).append(" engine = InnoDb CHARSET=utf8mb4 COLLATE utf8mb4_0900_ai_ci as ");
        sql.append("select a.id, ");
        sql.append("b.id as serial_object_id, ");
        sql.append("c.id as parent_serial_object_id, ");
        sql.append("b.serial_number as serial_number, ");
        sql.append("c.serial_number as parent_serial_number, ");
        sql.append("e.material_number, ");
        sql.append("f.material_number as parent_material_number, ");
        sql.append("e.material_type, ");
        sql.append("f.material_type as parent_material_type, ");
        sql.append("e.short_text as material_short_text, ");
        sql.append("f.short_text as parent_material_short_text, ");
        sql.append("e.sap_number as sap_no, ");
        sql.append("f.sap_number as parent_sap_no,");
        sql.append("e.material_hierarchy, ");
        sql.append("f.material_hierarchy as parent_material_hierarchy, ");
        sql.append("d.id as revision_id, ");
        sql.append("d.revision_number as revision_no, ");
        sql.append("b.assembly_date, ");
        sql.append("b.production_order_number as assembly_po, ");
        sql.append("a.plant, ");
        sql.append("g.code as service_order, ");
        sql.append("a.rma_type, ");
        sql.append("a.repair_location as location, ");
        sql.append("j.name as service_name, ");
        sql.append("k.code as task_name, ");
        sql.append("k.short_text as repair_task_short_text, ");
        sql.append("l.name as state_name, ");
        sql.append("a.internal_arrival_date, ");
        sql.append("a.internal_shipment_date, ");
        sql.append("a.basic_start_date, ");
        sql.append("a.basic_finish_date as basic_end_date, ");
        sql.append("a.designator, ");
        sql.append("a.defect_component, ");
        sql.append("a.analysis_text, ");
        sql.append("a.internal_report, ");
        sql.append("a.external_report, ");
        sql.append("a.customer_report, ");
        sql.append("a.epidemic_failure, ");
        sql.append("a.error_id, ");
        sql.append("a.origin, ");
        sql.append("a.customer_failure, ");
        sql.append("m.code as customer_code, ");
        sql.append("m.name as customer_name, ");
        sql.append("n.name as customer_group, ");
        sql.append("i.code as country_code, ");
        sql.append("i.name as country_name, ");
        sql.append("q.code as fault_analysis_code, ");
        sql.append("q.short_text as symptom_short_text, ");
        sql.append("o.name as error_code_name, ");
        sql.append("o.group_name as error_code_group, ");
        sql.append("o.short_text as error_short_text, ");
        sql.append("p.code as external_supplier_code, ");
        sql.append("p.name as external_supplier_name, ");
        sql.append("a.delivery_note_number, ");
        sql.append("a.repair_description, ");
        sql.append("g.service_order_type, ");
        sql.append("e.id as material, ");
        sql.append("b.id as serial_object, ");
        sql.append("e.owner_location, ");
        sql.append("a.rebuild_flag ");
        sql.append("from service_message_tab a ");
        sql.append("inner join serial_object_tab b on (a.serial_object = b.id) ");
        sql.append("left join serial_object_tab c on (b.parent_object = c.id) ");
        sql.append("inner join material_revision_tab d on (a.material_revision = d.id) ");
        sql.append("inner join material_tab e on (d.material = e.id) ");
        sql.append("left join material_tab f on (c.material = f.id) ");
        sql.append("inner join service_order_tab g on (a.service_order = g.code) ");
        sql.append("left join customer_tab m on (g.customer = m.code) ");
        sql.append("left join country_tab i on (m.country = i.code) ");
        sql.append("left join repair_service_tab j on (a.repair_service = j.code) ");
        sql.append("left join repair_task_tab k on (a.repair_task = k.code) ");
        sql.append("left join repair_state_tab l on (a.repair_state = l.code) ");
        sql.append("left join customer_group_tab n on (m.customer_group = n.id) ");
        sql.append("left join repair_error_code_tab o on (a.repair_error_code = o.code) ");
        sql.append("left join fault_analysis_tab q on (a.fault_analysis = q.code) ");
        sql.append("left join supplier_tab p on (a.external_supplier = p.code) ");
        if (delta) {
            sql.append("where a.rebuild_flag <> 0 ");
        }

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execAddColumns(TaskNodeLog ownTask, String tableName) {
        String executionSection = "alter table " + tableName + ": add columns";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("ALTER TABLE ").append(tableName).append(" ");
        sql.append("add COLUMN parent_revision_id BIGINT NOT NULL DEFAULT 0, ");
        sql.append("add COLUMN parent_revision_no VARCHAR(50), ");
        sql.append("add column sup_arrival_date date, ");
        sql.append("add column cust_ship_date date, ");
        sql.append("add column supplier_code varchar(50), ");
        sql.append("add column supplier_name varchar(100) ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execUpdate(TaskNodeLog ownTask) {
        String executionSection = "update cust_ship_date, sup_arrival_date, supplier_code, supplier_name";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update service_message_mv_tmp_delta t ");
        sql.append("set t.cust_ship_date = (");
        sql.append("   select max(a.shipment_date) ");
        sql.append("   from shipment_tab a ");
        sql.append("   where a.serial_object = t.serial_object_id ");
        sql.append("   and a.shipment_date <= t.internal_arrival_date");
        sql.append(")");

        em.createNativeQuery(sql.toString()).executeUpdate();

        // Null-Werte aus anderer Quelle beziehen und neu belegen
        sql = new StringBuilder();
        sql.append("update service_message_mv_tmp_delta t ");
        sql.append("set t.cust_ship_date = (");
        sql.append("   select max(a.shipment_date) ");
        sql.append("   from assembly_shipment_mv a ");
        sql.append("   where a.serial_object_id = t.serial_object_id");
        sql.append("   and a.shipment_date <= t.internal_arrival_date");
        sql.append(")");
        sql.append("where t.cust_ship_date is null");

        em.createNativeQuery(sql.toString()).executeUpdate();

        sql = new StringBuilder();
        sql.append("update service_message_mv_tmp_delta t ");
        sql.append("set t.sup_arrival_date = (");
        sql.append("    select max(ar.arrival_date) ");
        sql.append("    from arrival_tab ar ");
        sql.append("    where ar.serial_object = t.serial_object_id ");
        sql.append("    and ar.arrival_date <= t.cust_ship_date");
        sql.append("), ");
        sql.append("t.supplier_code = (");
        sql.append("    select max(b.supplier) ");
        sql.append("    from arrival_tab b ");
        sql.append("    where b.serial_object = t.serial_object_id ");
        sql.append("    and b.arrival_date = (");
        sql.append("        select max(a.arrival_date) ");
        sql.append("        from arrival_tab a ");
        sql.append("        where a.serial_object = t.serial_object_id ");
        sql.append("        and a.arrival_date <= t.cust_ship_date");
        sql.append("    )");
        sql.append(")");

        em.createNativeQuery(sql.toString()).executeUpdate();

        // Supplier-Name aus Supplier-Code nachbelegen
        sql = new StringBuilder();
        sql.append("update service_message_mv_tmp_delta t ");
        sql.append("inner join supplier_tab s on (t.supplier_code = s.code) ");
        sql.append("set t.supplier_name = s.name ");
        sql.append("where t.supplier_code is not null");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
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

        // WTF?? Ein inner join könnte noch Sinn machen, um nicht alle Service Messages zu
        // aktualisieren, sondern nur die, die nach dem inner join noch in der Ergebnismenge stehen,
        // aber zum einen sollte geklärt werden, warum man das will, zum zweiten sehe ich nicht,
        // weshalb die left joins dabei sind, denn auch wenn eine Verknüpfungsbedingung tiefer in
        // dem join nicht mehr gegeben ist, so wäre die Service Message dennoch dabei.
        // KI bestätigt das
        // -> sämtliche left joins auskommentiert. Kein inner join basiert auf eine Tabelle, die mit
        // left join verbunden wird, also bleiben die.
        // Es werden also keine Service Messages aktualisiert, die nicht mit einem SerObj, Material,
        // Revision oder Service Order verknüpft sind. Die Kriterien dürften dieselben sein, wie beim
        // Aufbau der Tabelle. Die SvcMsg sollen also nicht angefasst werden.
        // Wenn nun aber die Daten genau so erstellt werden, könnte sich maximal ein Unterschied ergeben,
        // wenn eine existierende Verknüpfung wegfällt. Das ist bei den Laufzeitdaten wegen Constraints
        // sicherlich nicht möglich. Ich wüsste nicht, weshalb es bei dieser "materialized view" dann
        // möglich sein soll.
        // -> nicht nur die left joins, sondern auch die inner joins entfernen. Es wird stets alles aktualisiert.
        // -> Um zu vermeiden, dass das rebuild-flag auch von zwischenzeitlich geänderten Werten
        // zurück gesetzt wird, das Ganze auf die zuvor eingelesenen Einträge beschränken. Die Einschränkung auf
        // das rebuild-flag wird damit obsolet.
        // -> Für die Filterung auf die temporäre Tabelle service_message_mv_tmp_delta muss sinnvollerweise
        // ein Index erstellt werden.

        em.createNativeQuery("ALTER TABLE service_message_mv_tmp_delta ADD INDEX idx_svcmsg_mv_tmp_delta(id)").executeUpdate();


        StringBuilder sql = new StringBuilder();
        sql.append("update service_message_tab a ");
        // sql.append("inner join serial_object_tab b on (a.serial_object = b.id) ");
        // sql.append("left join serial_object_tab c on (b.parent_object = c.id) ");
        // sql.append("inner join material_revision_tab d on (a.material_revision = d.id) ");
        // sql.append("inner join material_tab e on (d.material = e.id) ");
        // sql.append("left join material_tab f on (c.material = f.id) ");
        // sql.append("inner join service_order_tab g on (a.service_order = g.code) ");
        // sql.append("left join customer_tab m on (g.customer = m.code) ");
        // sql.append("left join country_tab i on (m.country = i.code) ");
        // sql.append("left join repair_service_tab j on (a.repair_service = j.code) ");
        // sql.append("left join repair_task_tab k on (a.repair_task = k.code) ");
        // sql.append("left join repair_state_tab l on (a.repair_state = l.code) ");
        // sql.append("left join customer_group_tab n on (m.customer_group = n.id) ");
        // sql.append("left join repair_error_code_tab o on (a.repair_error_code = o.code) ");
        // sql.append("left join fault_analysis_tab q on (a.fault_analysis = q.code) ");
        // sql.append("left join supplier_tab p on (a.external_supplier = p.code)");
        sql.append("inner join service_message_mv_tmp_delta b on a.id = b.id ");
        sql.append("set a.rebuild_flag = 0 ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    // private static final String ARRIVAL_MOVEMENT_TYPE = "101";
    // private static final String CANCELED_ARRIVAL_MOVEMENT_TYPE = "102";
    //
    // protected void execRemoveCanceled(TaskNodeLog ownTask) {
    // String executionSection = "delete canceled arrivals from arrival_mv";
    // logger.info(executionSection + " (1/2)");
    // TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);
    //
    // StringBuilder sql = new StringBuilder();
    // sql.append("delete amv ");
    // sql.append("from materialized_arrival_mv amv ");
    // sql.append("join ( ");
    // sql.append(" select b.id ");
    // sql.append(" from materialized_arrival_mv b, ( ");
    // sql.append(" select max(a.id) maxid, a.serial_object_id, a.order_number ");
    // sql.append(" from materialized_arrival_mv a ");
    // sql.append(" where a.movement_type = '").append(CANCELED_ARRIVAL_MOVEMENT_TYPE).append("' ");
    // sql.append(" group by a.serial_object_id, a.order_number ");
    // sql.append(" ) c ");
    // sql.append(" where b.movement_type = '").append(ARRIVAL_MOVEMENT_TYPE).append("' ");
    // sql.append(" and b.id < c.maxid ");
    // sql.append(" and b.serial_object_id = c.serial_object_id ");
    // sql.append(" and b.order_number = c.order_number ");
    // sql.append(") adc ");
    // sql.append("on (amv.id = adc.id) ");
    //
    // em.createNativeQuery(sql.toString()).executeUpdate();
    //
    // // delete arrivals with CANCELED_ARRIVAL_MOVEMENT_TYPE
    // logger.info(executionSection + " (2/2)");
    //
    // sql = new StringBuilder();
    // sql.append("delete from materialized_arrival_mv ");
    // sql.append("where movement_type = '").append(CANCELED_ARRIVAL_MOVEMENT_TYPE).append("' ");
    //
    // em.createNativeQuery(sql.toString()).executeUpdate();
    // subTsk.finishTaskWithSuccess();
    // }

}
