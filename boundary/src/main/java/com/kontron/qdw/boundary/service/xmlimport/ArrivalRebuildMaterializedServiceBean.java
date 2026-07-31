package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.TaskCall;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Rebuild der Arrival-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class ArrivalRebuildMaterializedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ARRIVAL_MOVEMENT_TYPE = "101";
    private static final String CANCELED_ARRIVAL_MOVEMENT_TYPE = "102";

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        // Es wird das Delta berechnet, also nur die Werte mit rebuildflag=1
        // Die ServiceMessages werden aktualisiert
        // Die Delta-Daten werden in die Materialized Table übernommen
        // Das rebuild-flag wird wieder zurück gesetzt
        // Gecancelte Daten werden gelöscht
        // Dauer: wenige Minuten
        return new TaskNodeLog("arrival rebuild materialized");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask);
        execCreate(ownTask);
        execAddColumns(ownTask);
        execUpdate(ownTask);
        execCopyData(ownTask);
        execResetRebuild(ownTask);
        execRemoveCanceled(ownTask);
    }

    private void execDrop(TaskNodeLog ownTask) {
        String executionSection = "drop table materialized_arrival_mv_tmp_delta";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "DROP TABLE IF EXISTS materialized_arrival_mv_tmp_delta";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCreate(TaskNodeLog ownTask) {
        String executionSection = "create table materialized_arrival_mv_tmp_delta";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("create table materialized_arrival_mv_tmp_delta engine = InnoDb CHARSET=utf8mb4 COLLATE utf8mb4_0900_ai_ci as ");
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
        sql.append("where a.rebuild_flag = 1 ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execAddColumns(TaskNodeLog ownTask) {
        String executionSection = "alter table materialized_arrival_mv_tmp_delta: add columns";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("ALTER TABLE materialized_arrival_mv_tmp_delta ");
        sql.append("add COLUMN parent_revision_id BIGINT NOT NULL DEFAULT 0, ");
        sql.append("add COLUMN parent_revision_no VARCHAR(50);");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execUpdate(TaskNodeLog ownTask) {
        String executionSection = "update sup_arrival_date, supplier_code, supplier_name in service_message_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update service_message_mv smmv, arrival_tab arfilter ");
        sql.append("set smmv.sup_arrival_date = ( ");
        sql.append("    select max(a2.arrival_date) ");
        sql.append("    from arrival_tab a2 ");
        sql.append("    where a2.serial_object = smmv.serial_object_id ");
        sql.append("    and a2.arrival_date <= smmv.cust_ship_date ");
        sql.append("), ");
        sql.append("smmv.supplier_code = ( ");
        sql.append("    select max(a3.supplier) ");
        sql.append("    from arrival_tab a3 ");
        sql.append("    where a3.serial_object = smmv.serial_object_id ");
        sql.append("    and a3.arrival_date = ( ");
        sql.append("        select max(a4.arrival_date) ");
        sql.append("        from arrival_tab a4 ");
        sql.append("        where a4.serial_object = smmv.serial_object_id ");
        sql.append("        and a4.arrival_date <= smmv.cust_ship_date ");
        sql.append("    ) ");
        sql.append(") ");
        sql.append("where arfilter.serial_object = smmv.serial_object_id ");
        sql.append("and arfilter.rebuild_flag = 1 ");

        em.createNativeQuery(sql.toString()).executeUpdate();

        sql = new StringBuilder();
        sql.append("update service_message_mv smmv, arrival_tab arfilter, supplier_tab s ");
        sql.append("set smmv.supplier_name = s.name ");
        sql.append("where arfilter.serial_object = smmv.serial_object_id ");
        sql.append("and arfilter.rebuild_flag = 1 ");
        sql.append("and smmv.supplier_code = s.code ");
        sql.append("and smmv.supplier_code is not null ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCopyData(TaskNodeLog ownTask) {
        String executionSection = "copy data from materialized_arrival_mv_tmp_delta to materialized_arrival_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder columns_arrival_mv = new StringBuilder();
        columns_arrival_mv.append("id, ");
        columns_arrival_mv.append("serial_object_id, ");
        columns_arrival_mv.append("parent_serial_object_id, ");
        columns_arrival_mv.append("serial_number, ");
        columns_arrival_mv.append("parent_serial_number, ");
        columns_arrival_mv.append("material_number, ");
        columns_arrival_mv.append("parent_material_number, ");
        columns_arrival_mv.append("material_type, ");
        columns_arrival_mv.append("parent_material_type, ");
        columns_arrival_mv.append("material_short_text, ");
        columns_arrival_mv.append("parent_material_short_text, ");
        columns_arrival_mv.append("sap_no, ");
        columns_arrival_mv.append("parent_sap_no, ");
        columns_arrival_mv.append("material_hierarchy, ");
        columns_arrival_mv.append("parent_material_hierarchy, ");
        columns_arrival_mv.append("revision_id, ");
        columns_arrival_mv.append("revision_no, ");
        columns_arrival_mv.append("assembly_date, ");
        columns_arrival_mv.append("assembly_po, ");
        columns_arrival_mv.append("supplier_code, ");
        columns_arrival_mv.append("supplier_name, ");
        columns_arrival_mv.append("country_code, ");
        columns_arrival_mv.append("country_name, ");
        columns_arrival_mv.append("arrival_date, ");
        columns_arrival_mv.append("plant, ");
        columns_arrival_mv.append("movement_type, ");
        columns_arrival_mv.append("order_number, ");
        columns_arrival_mv.append("material, ");
        columns_arrival_mv.append("serial_object, ");
        columns_arrival_mv.append("parent_revision_id, ");
        columns_arrival_mv.append("parent_revision_no ");

        StringBuilder sql = new StringBuilder();
        sql.append("insert into materialized_arrival_mv (");
        sql.append(columns_arrival_mv);
        sql.append(") ");
        sql.append("select ");
        sql.append(columns_arrival_mv);
        sql.append("from materialized_arrival_mv_tmp_delta");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execResetRebuild(TaskNodeLog ownTask) {
        String executionSection = "reset rebuild flag in arrivals";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update arrival_tab ");
        sql.append("set rebuild_flag = 0 ");
        sql.append("where rebuild_flag = 1 ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRemoveCanceled(TaskNodeLog ownTask) {
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
