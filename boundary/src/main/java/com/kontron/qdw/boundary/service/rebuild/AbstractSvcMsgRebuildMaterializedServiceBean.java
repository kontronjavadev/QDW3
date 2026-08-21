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

        StringBuilder ddl = new StringBuilder();
        ddl.append("create table ").append(tableName).append(" ( ");
        ddl.append("  id bigint not null, ");
        ddl.append("  serial_object_id bigint not null default 0, ");
        ddl.append("  parent_serial_object_id bigint default 0, ");
        ddl.append("  serial_number varchar(50) not null, ");
        ddl.append("  parent_serial_number varchar(50), ");
        ddl.append("  material_number varchar(50) not null, ");
        ddl.append("  parent_material_number varchar(50), ");
        ddl.append("  material_type varchar(50) not null, ");
        ddl.append("  parent_material_type varchar(50), ");
        ddl.append("  material_short_text varchar(200) not null, ");
        ddl.append("  parent_material_short_text varchar(200), ");
        ddl.append("  sap_no varchar(20) not null, ");
        ddl.append("  parent_sap_no varchar(20), ");
        ddl.append("  material_hierarchy varchar(250) default null, ");
        ddl.append("  parent_material_hierarchy varchar(250) default null, ");
        ddl.append("  revision_id bigint not null default 0, ");
        ddl.append("  revision_no varchar(50) not null, ");
        ddl.append("  assembly_date date default null, ");
        ddl.append("  assembly_po varchar(50) default null, ");
        ddl.append("  plant varchar(50) not null, ");
        ddl.append("  service_order varchar(50) not null, ");
        ddl.append("  rma_type varchar(50) default null, ");
        ddl.append("  location varchar(50) default null, ");
        ddl.append("  service_name varchar(50), ");
        ddl.append("  task_name varchar(50), ");
        ddl.append("  repair_task_short_text varchar(100), ");
        ddl.append("  state_name varchar(50), ");
        ddl.append("  internal_arrival_date date default null, ");
        ddl.append("  internal_shipment_date date default null, ");
        ddl.append("  basic_start_date date default null, ");
        ddl.append("  basic_end_date date default null, ");
        ddl.append("  designator varchar(50) default null, ");
        ddl.append("  defect_component varchar(100) default null, ");
        ddl.append("  analysis_text varchar(100) default null, ");
        ddl.append("  internal_report varchar(4000) default null, ");
        ddl.append("  external_report varchar(4000) default null, ");
        ddl.append("  customer_report varchar(4000) default null, ");
        ddl.append("  epidemic_failure tinyint(1) not null, ");
        ddl.append("  error_id varchar(100) default null, ");
        ddl.append("  origin varchar(100) default null, ");
        ddl.append("  customer_failure tinyint(1) not null, ");
        ddl.append("  customer_code varchar(50), ");
        ddl.append("  customer_name varchar(100), ");
        ddl.append("  customer_group binary(0) default null, ");
        ddl.append("  country_code varchar(50), ");
        ddl.append("  country_name varchar(100), ");
        ddl.append("  fault_analysis_code varchar(50), ");
        ddl.append("  symptom_short_text varchar(100), ");
        ddl.append("  error_code_name varchar(150), ");
        ddl.append("  error_code_group varchar(50) default null, ");
        ddl.append("  error_short_text varchar(100), ");
        ddl.append("  external_supplier_code varchar(50), ");
        ddl.append("  external_supplier_name varchar(100), ");
        ddl.append("  delivery_note_number varchar(50) default null, ");
        ddl.append("  repair_description varchar(4000) default null, ");
        ddl.append("  service_order_type varchar(255) not null, ");
        ddl.append("  material bigint not null default 0, ");
        ddl.append("  serial_object bigint not null default 0, ");
        ddl.append("  owner_location varchar(50) not null, ");
        ddl.append("  parent_revision_id bigint not null default 0, ");
        ddl.append("  parent_revision_no varchar(50) default null, ");
        ddl.append("  sup_arrival_date date default null, ");
        ddl.append("  cust_ship_date date default null, ");
        ddl.append("  supplier_code varchar(50) default null, ");
        ddl.append("  supplier_name varchar(100) default null ");
        if (delta) {
            ddl.append(", rebuild_flag int not null ");
        }
        ddl.append(") engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci");



        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append(" (");
        // Spaltenliste identisch zur DDL-Definition
        sql.append("  id, serial_object_id, parent_serial_object_id, serial_number, parent_serial_number, ");
        sql.append("  material_number, parent_material_number, material_type, parent_material_type, ");
        sql.append("  material_short_text, parent_material_short_text, sap_no, parent_sap_no, ");
        sql.append("  material_hierarchy, parent_material_hierarchy, revision_id, revision_no, ");
        sql.append("  assembly_date, assembly_po, plant, service_order, rma_type, location, ");
        sql.append("  service_name, task_name, repair_task_short_text, state_name, ");
        sql.append("  internal_arrival_date, internal_shipment_date, basic_start_date, basic_end_date, ");
        sql.append("  designator, defect_component, analysis_text, internal_report, external_report, ");
        sql.append("  customer_report, epidemic_failure, error_id, origin, customer_failure, ");
        sql.append("  customer_code, customer_name, customer_group, country_code, country_name, ");
        sql.append("  fault_analysis_code, symptom_short_text, error_code_name, error_code_group, ");
        sql.append("  error_short_text, external_supplier_code, external_supplier_name, ");
        sql.append("  delivery_note_number, repair_description, service_order_type, ");
        sql.append("  material, serial_object, owner_location, ");
        // Neue Spalten direkt mit Werten/Defaults befüllen
        sql.append("  parent_revision_id, parent_revision_no, ");
        sql.append("  cust_ship_date, sup_arrival_date, supplier_code, supplier_name");
        if (delta) {
            sql.append(", rebuild_flag");
        }
        sql.append(") select ");
        // Mapping der regulären Werte
        sql.append("  a.id, b.id, c.id, b.serial_number, c.serial_number, ");
        sql.append("  e.material_number, f.material_number, e.material_type, f.material_type, ");
        sql.append("  e.short_text, f.short_text, e.sap_number, f.sap_number, ");
        sql.append("  e.material_hierarchy, f.material_hierarchy, d.id, d.revision_number, ");
        sql.append("  b.assembly_date, b.production_order_number, a.plant, g.code, a.rma_type, ");
        sql.append("  a.repair_location, j.name, k.code, k.short_text, l.name, ");
        sql.append("  a.internal_arrival_date, a.internal_shipment_date, a.basic_start_date, ");
        sql.append("  a.basic_finish_date, a.designator, a.defect_component, a.analysis_text, ");
        sql.append("  a.internal_report, a.external_report, a.customer_report, a.epidemic_failure, ");
        sql.append("  a.error_id, a.origin, a.customer_failure, m.code, m.name, null, ");
        sql.append("  i.code, i.name, q.code, q.short_text, o.name, o.group_name, ");
        sql.append("  o.short_text, p.code, p.name, a.delivery_note_number, a.repair_description, ");
        sql.append("  g.service_order_type, e.id, b.id, e.owner_location, ");

        // Defaults für parent_revision
        sql.append("  0, null, ");

        // Werte aus den Lateral Joins mappen
        sql.append("  coalesce(ship1.shipment_date, ship2.shipment_date), ");
        sql.append("  arr.arrival_date, arr.supplier, sup.name ");

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
        sql.append("left join repair_error_code_tab o on (a.repair_error_code = o.code) ");
        sql.append("left join fault_analysis_tab q on (a.fault_analysis = q.code) ");
        sql.append("left join supplier_tab p on (a.external_supplier = p.code) ");

        // Die Lateral Joins zur Auflösung der kaskadierenden Datums- und Supplier-Werte.
        // t.serial_object_id wird durch b.id abgebildet, t.internal_arrival_date durch a.internal_arrival_date.
        sql.append("left join lateral ( ");
        sql.append("  select a1.shipment_date ");
        sql.append("  from shipment_tab a1 ");
        sql.append("  where a1.serial_object = b.id ");
        sql.append("  and a1.shipment_date <= a.internal_arrival_date ");
        sql.append("  order by a1.shipment_date desc, a1.id desc limit 1 ");
        sql.append(") ship1 on true ");

        sql.append("left join lateral ( ");
        sql.append("  select a2.shipment_date ");
        sql.append("  from assembly_shipment_mv a2 ");
        sql.append("  where a2.serial_object_id = b.id ");
        sql.append("  and a2.shipment_date <= a.internal_arrival_date ");
        sql.append("  order by a2.shipment_date desc, a2.id desc limit 1 ");
        sql.append(") ship2 on true ");

        sql.append("left join lateral ( ");
        sql.append("  select ar.arrival_date, ar.supplier ");
        sql.append("  from arrival_tab ar ");
        sql.append("  where ar.serial_object = b.id ");
        sql.append("  and ar.arrival_date <= coalesce(ship1.shipment_date, ship2.shipment_date) ");
        sql.append("  order by ar.arrival_date desc, ar.id desc limit 1 ");
        sql.append(") arr on true ");

        sql.append("left join supplier_tab sup on (arr.supplier = sup.code) ");

        if (delta) {
            sql.append("where a.rebuild_flag <> 0 ");
        }


        // Session für diesen Lauf optimieren
        em.createNativeQuery("set session transaction isolation level read uncommitted").executeUpdate();
        em.createNativeQuery("set session sql_log_bin = 0").executeUpdate();

        try {
            em.createNativeQuery(ddl.toString()).executeUpdate();
            em.createNativeQuery(sql.toString()).executeUpdate();
        }
        finally {
            // auf Standard zurücksetzen
            em.createNativeQuery("set session transaction isolation level repeatable read").executeUpdate();
            em.createNativeQuery("set session sql_log_bin = 1").executeUpdate();
        }
        subTsk.finishTaskWithSuccess();
    }

}
