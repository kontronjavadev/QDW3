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
 * Abstrakte Superklasse für Delta- und Full-Rebuild der Shipment-Arrival-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class AbstractShipmentArrivalRebuildMaterializedServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String SHIPMENT_MOVEMENT_TYPE_1 = "601";
    private static final String CANCELED_SHIPMENT_MOVEMENT_TYPE_1 = "602";

    private static final String SHIPMENT_MOVEMENT_TYPE_2 = "633";
    private static final String CANCELED_SHIPMENT_MOVEMENT_TYPE_2 = "634";

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
        ddl.append("  customer_code varchar(50) not null, ");
        ddl.append("  customer_name varchar(100) not null, ");
        ddl.append("  country_code varchar(50) not null, ");
        ddl.append("  country_name varchar(100) not null, ");
        ddl.append("  shipment_date date not null, ");
        ddl.append("  plant varchar(50) not null, ");
        ddl.append("  shipment_movement_type varchar(50) not null, ");
        ddl.append("  customer_order_number varchar(50) not null, ");
        ddl.append("  shipment_id bigint not null, ");
        ddl.append("  material bigint not null default 0, ");
        ddl.append("  serial_object bigint not null default 0, ");
        ddl.append("  owner_location varchar(50) not null, ");
        ddl.append("  parent_revision_id bigint not null default 0, ");
        ddl.append("  parent_revision_no varchar(50) default null, ");
        ddl.append("  arrival_date date default null, ");
        ddl.append("  supplier_code varchar(50) default null, ");
        ddl.append("  supplier_name varchar(100) default null, ");
        ddl.append("  arrival_movement_type varchar(50) default null, ");
        ddl.append("  purchase_order_number varchar(50) default null, ");
        ddl.append("  arrival_id bigint default null, ");
        ddl.append("  primary key (id) ");
        ddl.append(") engine = innodb charset=utf8mb4 collate utf8mb4_0900_ai_ci");

        // 2. Daten inkl. direkt aufgelöster Arrival-Informationen per Lateral Join einfügen
        StringBuilder insert = new StringBuilder();
        insert.append("insert into ").append(tableName).append(" ( ");
        insert.append("  id, serial_object_id, parent_serial_object_id, serial_number, parent_serial_number, ");
        insert.append("  material_number, parent_material_number, material_type, parent_material_type, ");
        insert.append("  material_short_text, parent_material_short_text, sap_no, parent_sap_no, ");
        insert.append("  material_hierarchy, parent_material_hierarchy, revision_id, revision_no, ");
        insert.append("  assembly_date, assembly_po, customer_code, customer_name, country_code, ");
        insert.append("  country_name, shipment_date, plant, shipment_movement_type, customer_order_number, ");
        insert.append("  shipment_id, material, serial_object, owner_location, ");
        insert.append("  arrival_date, supplier_code, supplier_name, arrival_movement_type, purchase_order_number, arrival_id ");
        insert.append(") ");
        insert.append("select ");
        insert.append("  a.id, b.id, c.id, b.serial_number, c.serial_number, ");
        insert.append("  e.material_number, f.material_number, e.material_type, f.material_type, ");
        insert.append("  e.short_text, f.short_text, e.sap_number, f.sap_number, ");
        insert.append("  e.material_hierarchy, f.material_hierarchy, d.id, d.revision_number, ");
        insert.append("  b.assembly_date, b.production_order_number, g.code, g.name, i.code, i.name, ");
        insert.append("  a.shipment_date, a.plant, a.movement_type, a.order_number, ");
        insert.append("  a.id, e.id, b.id, e.owner_location, ");
        insert.append("  arr.arrival_date, arr.supplier, sup.name, arr.movement_type, arr.order_number, arr.id ");
        insert.append("from shipment_tab a ");
        insert.append("inner join serial_object_tab b on (a.serial_object = b.id) ");
        insert.append("left join serial_object_tab c on (b.parent_object = c.id) ");
        insert.append("inner join material_revision_tab d on (a.material_revision = d.id) ");
        insert.append("inner join material_tab e on (d.material = e.id) ");
        insert.append("left join material_tab f on (c.material = f.id) ");
        insert.append("inner join customer_tab g on (a.customer = g.code) ");
        insert.append("inner join country_tab i on (g.country = i.code) ");

        // Das ehemals separate Update direkt als performanter Lateral Join integriert:
        insert.append("left join lateral ( ");
        insert.append("  select ar.id, ar.arrival_date, ar.supplier, ar.movement_type, ar.order_number ");
        insert.append("  from arrival_tab ar ");
        insert.append("  where ar.serial_object = a.serial_object ");
        insert.append("  and ar.arrival_date <= a.shipment_date ");
        insert.append("  order by ar.arrival_date desc, ar.id desc limit 1 ");
        insert.append(") arr on true ");

        insert.append("left join supplier_tab sup on (arr.supplier = sup.code) ");

        if (delta) {
            insert.append("where a.rebuild_flag = 1 ");
        }


        // Session für diesen Lauf optimieren
        em.createNativeQuery("set session transaction isolation level read uncommitted").executeUpdate();
        em.createNativeQuery("set session sql_log_bin = 0").executeUpdate();

        try {
            em.createNativeQuery(ddl.toString()).executeUpdate();
            em.createNativeQuery(insert.toString()).executeUpdate();
        }
        finally {
            // auf Standard zurücksetzen
            em.createNativeQuery("set session transaction isolation level repeatable read").executeUpdate();
            em.createNativeQuery("set session sql_log_bin = 1").executeUpdate();
        }
        subTsk.finishTaskWithSuccess();
    }

    protected void execRemoveCanceled(TaskNodeLog ownTask) {
        execRemoveCanceled1(ownTask);
        execRemoveCanceled2(ownTask);
        execRemoveCanceled3(ownTask);
    }



    private void execRemoveCanceled1(TaskNodeLog ownTask) {
        String executionSection = "delete canceled shipments from arrival_shipment_mv (1/3)";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        // delete all SHIPMENT_MOVEMENT_TYPE_1 items which have a cancel entry (CANCELED_SHIPMENT_MOVEMENT_TYPE_1)
        // and all SHIPMENT_MOVEMENT_TYPE_2 items which have a cancel entry (CANCELED_SHIPMENT_MOVEMENT_TYPE_2)
        StringBuilder sql = new StringBuilder();
        sql.append("delete amv ");
        sql.append("from arrival_shipment_mv amv ");

        // direkter self-join über die fachlichen Schlüssel
        sql.append("inner join arrival_shipment_mv cancel on ( ");
        sql.append("    amv.serial_number = cancel.serial_number ");
        sql.append("    and amv.customer_order_number = cancel.customer_order_number ");
        sql.append(") ");

        sql.append("where amv.customer_order_number != '' ");
        sql.append("and amv.id < cancel.id ");

        // die movementtypes paarweise abgleichen
        sql.append("and ( ");
        sql.append("    (amv.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_1).append("' ");
        sql.append("     and cancel.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_1).append("') ");
        sql.append("    or ");
        sql.append("    (amv.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_2).append("' ");
        sql.append("     and cancel.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_2).append("') ");
        sql.append(") ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRemoveCanceled2(TaskNodeLog ownTask) {
        String executionSection = "delete canceled shipments from arrival_shipment_mv (2/3)";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        // if customer_order_number is empty and shipment type CANCELED_SHIPMENT_MOVEMENT_TYPE_1 or CANCELED_SHIPMENT_MOVEMENT_TYPE_2
        // then delete the last item which was transfered before
        StringBuilder sql = new StringBuilder();
        sql.append("delete amv ");

        // Die treibende Tabelle sind die Stornos (alias cancel) für beide Typen
        sql.append("from arrival_shipment_mv cancel ");

        // LATERAL Join: Ermittelt pro Storno den neuesten Vorgänger
        // passend zum jeweiligen Bewegungstyp-Paar.
        sql.append("inner join lateral ( ");
        sql.append("    select d.id ");
        sql.append("    from arrival_shipment_mv d ");
        sql.append("    where d.serial_number = cancel.serial_number ");
        sql.append("    and d.shipment_date <= cancel.shipment_date ");
        sql.append("    and ( ");
        sql.append("          (");
        sql.append("            cancel.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_1);
        sql.append("'           and d.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_1).append("'");
        sql.append("          ) or (");
        sql.append("            cancel.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_2);
        sql.append("'           and d.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_2).append("'");
        sql.append("          ) ");
        sql.append("    ) ");
        sql.append("    order by d.id desc limit 1 ");
        sql.append(") pred on true ");

        // Den ermittelten Vorgänger zum Löschen anbinden
        sql.append("inner join arrival_shipment_mv amv on (amv.id = pred.id) ");

        // Filterbedingungen: Beide Storno-Typen ohne Auftragsnummer abfangen
        sql.append("where cancel.shipment_movement_type in ('");
        sql.append(CANCELED_SHIPMENT_MOVEMENT_TYPE_1).append("', '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_2).append("') ");
        sql.append("and cancel.customer_order_number = '' ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRemoveCanceled3(TaskNodeLog ownTask) {
        String executionSection = "delete canceled shipments from arrival_shipment_mv (3/3)";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("delete from arrival_shipment_mv ");
        sql.append("where shipment_movement_type in (");
        sql.append("    '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_1).append("', ");
        sql.append("    '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_2).append("'");
        sql.append(")");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
