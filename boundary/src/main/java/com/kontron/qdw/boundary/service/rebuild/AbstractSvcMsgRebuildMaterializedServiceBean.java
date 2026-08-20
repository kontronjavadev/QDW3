package com.kontron.qdw.boundary.service.rebuild;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
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
public class AbstractSvcMsgRebuildMaterializedServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



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
        sql.append("e.owner_location ");
        if (delta) {
            sql.append(", a.rebuild_flag ");
        }
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

    protected void execUpdate(TaskNodeLog ownTask, String tableName) {
        String executionSection = "update cust_ship_date, sup_arrival_date, supplier_code, supplier_name";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" t ");
        sql.append("set t.cust_ship_date = (");
        sql.append("   select max(a.shipment_date) ");
        sql.append("   from shipment_tab a ");
        sql.append("   where a.serial_object = t.serial_object_id ");
        sql.append("   and a.shipment_date <= t.internal_arrival_date");
        sql.append(")");

        em.createNativeQuery(sql.toString()).executeUpdate();

        // Null-Werte aus anderer Quelle beziehen und neu belegen
        sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" t ");
        sql.append("set t.cust_ship_date = (");
        sql.append("   select max(a.shipment_date) ");
        sql.append("   from assembly_shipment_mv a ");
        sql.append("   where a.serial_object_id = t.serial_object_id");
        sql.append("   and a.shipment_date <= t.internal_arrival_date");
        sql.append(")");
        sql.append("where t.cust_ship_date is null");

        em.createNativeQuery(sql.toString()).executeUpdate();

        sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" t ");
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
        sql.append("update ").append(tableName).append(" t ");
        sql.append("inner join supplier_tab s on (t.supplier_code = s.code) ");
        sql.append("set t.supplier_name = s.name ");
        sql.append("where t.supplier_code is not null");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
