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
 * Abstrakte Superklasse für Delta- und Full-Rebuild der Arrival-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class AbstractArrivalRebuildMaterializedServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ARRIVAL_MOVEMENT_TYPE = "101";
    private static final String CANCELED_ARRIVAL_MOVEMENT_TYPE = "102";

    @PersistenceContext
    protected EntityManager em;



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
        ddl.append("  id bigint NOT NULL,");
        ddl.append("  serial_object_id bigint NOT NULL DEFAULT '0',");
        ddl.append("  parent_serial_object_id bigint DEFAULT '0',");
        ddl.append("  serial_number varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  parent_serial_number varchar(50) CHARACTER SET utf8mb3,");
        ddl.append("  material_number varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  parent_material_number varchar(50) CHARACTER SET utf8mb3,");
        ddl.append("  material_type varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  parent_material_type varchar(50) CHARACTER SET utf8mb3,");
        ddl.append("  material_short_text varchar(200) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  parent_material_short_text varchar(200) CHARACTER SET utf8mb3,");
        ddl.append("  sap_no varchar(20) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  parent_sap_no varchar(20) CHARACTER SET utf8mb3,");
        ddl.append("  material_hierarchy varchar(250) CHARACTER SET utf8mb3 DEFAULT NULL,");
        ddl.append("  parent_material_hierarchy varchar(250) CHARACTER SET utf8mb3 DEFAULT NULL,");
        ddl.append("  revision_id bigint NOT NULL DEFAULT '0',");
        ddl.append("  revision_no varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  assembly_date date DEFAULT NULL,");
        ddl.append("  assembly_po varchar(50) CHARACTER SET utf8mb3 DEFAULT NULL,");
        ddl.append("  supplier_code varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  supplier_name varchar(100) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  country_code varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  country_name varchar(100) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  arrival_date date NOT NULL,");
        ddl.append("  plant varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  movement_type varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  order_number varchar(50) CHARACTER SET utf8mb3 NOT NULL,");
        ddl.append("  material bigint NOT NULL DEFAULT '0',");
        ddl.append("  serial_object bigint NOT NULL DEFAULT '0',");
        ddl.append("  parent_revision_id bigint NOT NULL DEFAULT '0',");
        ddl.append("  parent_revision_no varchar(50) DEFAULT NULL");
        ddl.append(") engine = InnoDb CHARSET=utf8mb4 COLLATE utf8mb4_0900_ai_ci");

        StringBuilder insert = new StringBuilder();
        insert.append("insert into ").append(tableName).append(" ( ");
        insert.append("  id, serial_object_id, parent_serial_object_id, serial_number, parent_serial_number, ");
        insert.append("  material_number, parent_material_number, material_type, parent_material_type, ");
        insert.append("  material_short_text, parent_material_short_text, sap_no, parent_sap_no, ");
        insert.append("  material_hierarchy, parent_material_hierarchy, revision_id, revision_no, ");
        insert.append("  assembly_date, assembly_po, supplier_code, supplier_name, country_code, ");
        insert.append("  country_name, arrival_date, plant, movement_type, order_number, material, serial_object, ");
        insert.append("  parent_revision_id, parent_revision_no ");
        insert.append(") ");
        insert.append("select ");
        insert.append("  a.id, b.id, c.id, b.serial_number, c.serial_number, ");
        insert.append("  e.material_number, f.material_number, e.material_type, f.material_type, ");
        insert.append("  e.short_text, f.short_text, e.sap_number, f.sap_number, ");
        insert.append("  e.material_hierarchy, f.material_hierarchy, d.id, d.revision_number, ");
        insert.append("  b.assembly_date, b.production_order_number, g.code, g.name, h.code, h.name, ");
        insert.append("  a.arrival_date, a.plant, a.movement_type, a.order_number, e.id, b.id, ");
        insert.append("  0, null ");
        insert.append("from arrival_tab a ");
        insert.append("inner join serial_object_tab b on (a.serial_object = b.id) ");
        insert.append("left join serial_object_tab c on (b.parent_object = c.id) ");
        insert.append("inner join material_revision_tab d on (a.material_revision = d.id) ");
        insert.append("inner join material_tab e on (d.material = e.id) ");
        insert.append("left join material_tab f on (c.material = f.id) ");
        insert.append("inner join supplier_tab g on (a.supplier = g.code) ");
        insert.append("inner join country_tab h on (g.country = h.code) ");

        if (delta) {
            insert.append("where a.rebuild_flag = 1 ");
        }



        em.createNativeQuery(ddl.toString()).executeUpdate();
        em.createNativeQuery(insert.toString()).executeUpdate();

        subTsk.finishTaskWithSuccess();
    }

    protected void execRemoveCanceled(TaskNodeLog ownTask) {
        String executionSection = "delete canceled arrivals from arrival_mv";
        logger.info(executionSection + " (1/2)");
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("delete amv ");
        sql.append("from materialized_arrival_mv amv ");
        sql.append("inner join materialized_arrival_mv cancel on ( ");
        sql.append("    amv.serial_object_id = cancel.serial_object_id ");
        sql.append("    and amv.order_number = cancel.order_number ");
        sql.append(") ");
        sql.append("where amv.movement_type = '").append(ARRIVAL_MOVEMENT_TYPE).append("' ");
        sql.append("and cancel.movement_type = '").append(CANCELED_ARRIVAL_MOVEMENT_TYPE).append("' ");
        sql.append("and amv.id < cancel.id ");

        em.createNativeQuery(sql.toString()).executeUpdate();

        // delete arrivals with CANCELED_ARRIVAL_MOVEMENT_TYPE
        logger.info(executionSection + " (2/2)");

        sql = new StringBuilder();
        sql.append("delete from materialized_arrival_mv ");
        sql.append("where movement_type = '").append(CANCELED_ARRIVAL_MOVEMENT_TYPE).append("' ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
