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
        sql.append("g.code as customer_code, ");
        sql.append("g.name as customer_name, ");
        sql.append("i.code as country_code, ");
        sql.append("i.name as country_name, ");
        sql.append("a.shipment_date, ");
        sql.append("a.plant, ");
        sql.append("a.movement_type as shipment_movement_type, ");
        sql.append("a.order_number as customer_order_number, ");
        sql.append("a.id as shipment_id, ");
        sql.append("e.id as material, ");
        sql.append("b.id as serial_object, ");
        sql.append("e.owner_location ");
        sql.append("from shipment_tab a ");
        sql.append("inner join serial_object_tab b on (a.serial_object = b.id) ");
        sql.append("left join serial_object_tab c on (b.parent_object = c.id) ");
        sql.append("inner join material_revision_tab d on (a.material_revision = d.id) ");
        sql.append("inner join material_tab e on (d.material = e.id) ");
        sql.append("left join material_tab f on (c.material = f.id) ");
        sql.append("inner join customer_tab g on (a.customer = g.code) ");
        sql.append("inner join country_tab i on (g.country = i.code) ");
        if (delta) {
            sql.append("where a.rebuild_flag = 1 ");
        }


        // Session für diesen Lauf optimieren
        em.createNativeQuery("set session transaction isolation level read uncommitted").executeUpdate();
        em.createNativeQuery("set session sql_log_bin = 0").executeUpdate();

        try {
            em.createNativeQuery(sql.toString()).executeUpdate();
        }
        finally {
            // auf Standard zurücksetzen
            em.createNativeQuery("set session transaction isolation level repeatable read").executeUpdate();
            em.createNativeQuery("set session sql_log_bin = 1").executeUpdate();
        }
        subTsk.finishTaskWithSuccess();
    }

    protected void execAddColumns(TaskNodeLog ownTask, String tableName) {
        String executionSection = "alter table " + tableName + ": add columns";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("alter table  ").append(tableName).append(" ");
        sql.append("ADD COLUMN parent_revision_id BIGINT NOT NULL DEFAULT 0, ");
        sql.append("ADD COLUMN parent_revision_no VARCHAR(50), ");
        sql.append("add column arrival_date date, ");
        sql.append("add column supplier_code varchar(50), ");
        sql.append("add column supplier_name varchar(100), ");
        sql.append("add column arrival_movement_type varchar(50), ");
        sql.append("add column purchase_order_number varchar(50), ");
        sql.append("add column arrival_id BIGINT(20) ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execUpdateArrId(TaskNodeLog ownTask, String tableName) {
        String executionSection = "update to last arrival";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" t ");
        sql.append("set t.arrival_id = (");
        sql.append("    select max(a2.id) ");
        sql.append("    from arrival_tab a2 ");
        sql.append("    where a2.serial_object = t.serial_object_id ");
        sql.append("    and a2.arrival_date = (");
        sql.append("        select max(a1.arrival_date) ");
        sql.append("        from arrival_tab a1 ");
        sql.append("        where a1.serial_object = t.serial_object_id ");
        sql.append("        and a1.arrival_date <= t.shipment_date");
        sql.append("    )");
        sql.append(")");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execAddIndex(TaskNodeLog ownTask, String tableName) {
        String executionSection = "create index for arrival_id";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "ALTER TABLE " + tableName + " ADD INDEX IN_AS_ARR_ID_TEMP(arrival_id)";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execUpdateArrDate(TaskNodeLog ownTask, String tableName) {
        String executionSection = "update arrival_date, supplier_code, supplier_name, arrival_movement_type, purchase_order_number";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" t ");
        sql.append("inner join arrival_tab a on (t.arrival_id = a.id) ");
        sql.append("inner join supplier_tab s on (a.supplier = s.code) ");
        sql.append("set t.arrival_date = a.arrival_date, ");
        sql.append("t.supplier_code = s.code, ");
        sql.append("t.supplier_name = s.name, ");
        sql.append("t.arrival_movement_type = a.movement_type, ");
        sql.append("t.purchase_order_number = a.order_number");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execDropIndex(TaskNodeLog ownTask, String tableName) {
        String executionSection = "drop index for arrival_id";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "ALTER TABLE " + tableName + " DROP INDEX IN_AS_ARR_ID_TEMP";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execRemoveCanceled(TaskNodeLog ownTask) {
        execRemoveCanceled1(ownTask);
        execRemoveCanceled2(ownTask);
        execRemoveCanceled3(ownTask);
        execRemoveCanceled4(ownTask);
    }



    private void execRemoveCanceled1(TaskNodeLog ownTask) {
        String executionSection = "delete canceled shipments from arrival_shipment_mv (1/4)";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        // delete all SHIPMENT_MOVEMENT_TYPE_1 items which have a cancel entry (CANCELED_SHIPMENT_MOVEMENT_TYPE_1)
        // and all SHIPMENT_MOVEMENT_TYPE_2 items which have a cancel entry (CANCELED_SHIPMENT_MOVEMENT_TYPE_2)
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE asmv ");
        sql.append("FROM arrival_shipment_mv asmv ");
        sql.append("join ( ");
        sql.append("     select a.id ");
        sql.append("     from arrival_shipment_mv a, arrival_shipment_mv b ");
        sql.append("     where a.id < b.id ");
        sql.append("     and (");
        sql.append("         ( ");
        sql.append("             a.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_1).append("' ");
        sql.append("             and b.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_1).append("' ");
        sql.append("         ) ");
        sql.append("         OR ");
        sql.append("         ( ");
        sql.append("             a.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_2).append("' ");
        sql.append("             and b.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_2).append("' ");
        sql.append("         ) ");
        sql.append("     ) ");
        sql.append("     and a.serial_number = b.serial_number ");
        sql.append("     and a.customer_order_number = b.customer_order_number ");
        sql.append("     and a.customer_order_number != '' ");
        sql.append(") asdc ");
        sql.append("on (asmv.id = asdc.id) ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRemoveCanceled2(TaskNodeLog ownTask) {
        String executionSection = "delete canceled shipments from arrival_shipment_mv (2/4)";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        // if customer_order_number is empty and shipment type CANCELED_SHIPMENT_MOVEMENT_TYPE_1 or CANCELED_SHIPMENT_MOVEMENT_TYPE_2
        // then delete the last item which was transfered before
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE asmv ");
        sql.append("FROM arrival_shipment_mv asmv ");
        sql.append("join ( ");
        sql.append("     SELECT ");
        sql.append("         (");
        sql.append("             SELECT MAX(d.id) as id ");
        sql.append("             FROM arrival_shipment_mv d ");
        sql.append("             WHERE d.shipment_date <= e.shipment_date ");
        sql.append("             and d.serial_number = e.serial_number ");
        sql.append("             and d.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_1).append("' ");
        sql.append("         ) AS my_vorgaenger_id ");
        sql.append("     FROM arrival_shipment_mv e ");
        sql.append("     where e.customer_order_number = '' ");
        sql.append("     and e.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_1).append("' ");
        sql.append(") asdc ");
        sql.append("on (asmv.id = asdc.my_vorgaenger_id) ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRemoveCanceled3(TaskNodeLog ownTask) {
        String executionSection = "delete canceled shipments from arrival_shipment_mv (3/4)";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE asmv ");
        sql.append("FROM arrival_shipment_mv asmv ");
        sql.append("join ( ");
        sql.append("     SELECT ");
        sql.append("         (");
        sql.append("             SELECT MAX(d.id) as id ");
        sql.append("             FROM arrival_shipment_mv d ");
        sql.append("             WHERE d.shipment_date <= e.shipment_date ");
        sql.append("             and d.serial_number = e.serial_number ");
        sql.append("             and d.shipment_movement_type = '").append(SHIPMENT_MOVEMENT_TYPE_2).append("' ");
        sql.append("         ) AS my_vorgaenger_id ");
        sql.append("     FROM arrival_shipment_mv e ");
        sql.append("     where e.customer_order_number = '' ");
        sql.append("     and e.shipment_movement_type = '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_2).append("' ");
        sql.append(") asdc ");
        sql.append("on (asmv.id = asdc.my_vorgaenger_id) ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRemoveCanceled4(TaskNodeLog ownTask) {
        String executionSection = "delete canceled shipments from arrival_shipment_mv (4/4)";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE asmv ");
        sql.append("FROM arrival_shipment_mv asmv ");
        sql.append("join ( ");
        sql.append("    select '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_1).append("' as shipment_movement_type from dual ");
        sql.append("    union ");
        sql.append("    select '").append(CANCELED_SHIPMENT_MOVEMENT_TYPE_2).append("' as shipment_movement_type from dual");
        sql.append(") asdc ");
        sql.append("on (asmv.shipment_movement_type = asdc.shipment_movement_type) ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
