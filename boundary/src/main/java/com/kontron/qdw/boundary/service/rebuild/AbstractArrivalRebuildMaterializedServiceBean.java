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
        sql.append("g.code as supplier_code, ");
        sql.append("g.name as supplier_name, ");
        sql.append("h.code as country_code, ");
        sql.append("h.name as country_name, ");
        sql.append("a.arrival_date, ");
        sql.append("a.plant, ");
        sql.append("a.movement_type as movement_type, ");
        sql.append("a.order_number as order_number, ");
        sql.append("e.id as material, ");
        sql.append("b.id as serial_object ");
        sql.append("from arrival_tab a ");
        sql.append("inner join serial_object_tab b on (a.serial_object = b.id) ");
        sql.append("left join serial_object_tab c on (b.parent_object = c.id) ");
        sql.append("inner join material_revision_tab d on (a.material_revision = d.id) ");
        sql.append("inner join material_tab e on (d.material = e.id) ");
        sql.append("left join material_tab f on (c.material = f.id) ");
        sql.append("inner join supplier_tab g on (a.supplier = g.code) ");
        sql.append("inner join country_tab h on (g.country = h.code) ");
        if (delta) {
            sql.append("where a.rebuild_flag = 1 ");
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
        sql.append("add COLUMN parent_revision_no VARCHAR(50);");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    protected void execRemoveCanceled(TaskNodeLog ownTask) {
        String executionSection = "delete canceled arrivals from arrival_mv";
        logger.info(executionSection + " (1/2)");
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("delete amv ");
        sql.append("from materialized_arrival_mv amv ");
        sql.append("join ( ");
        sql.append("    select b.id ");
        sql.append("    from materialized_arrival_mv b, ( ");
        sql.append("        select max(a.id) maxid, a.serial_object_id, a.order_number ");
        sql.append("        from materialized_arrival_mv a ");
        sql.append("        where a.movement_type = '").append(CANCELED_ARRIVAL_MOVEMENT_TYPE).append("' ");
        sql.append("        group by a.serial_object_id, a.order_number ");
        sql.append("    ) c ");
        sql.append("    where b.movement_type = '").append(ARRIVAL_MOVEMENT_TYPE).append("' ");
        sql.append("    and b.id < c.maxid ");
        sql.append("    and b.serial_object_id = c.serial_object_id ");
        sql.append("    and b.order_number = c.order_number ");
        sql.append(") adc ");
        sql.append("on (amv.id = adc.id) ");

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
