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
 * Rebuild der Arrival-"Aggregated table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class ArrivalRebuildAggregatedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("rebuild materialized arrival");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask);
        execCreate(ownTask);
        execCopyData(ownTask);
        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);
        execAddIndices(ownTask);
    }

    private void execDrop(TaskNodeLog ownTask) {
        String executionSection = "rAA: drop table aggregated_arrival_tab_new";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "DROP TABLE IF EXISTS aggregated_arrival_tab_new";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCreate(TaskNodeLog ownTask) {
        String executionSection = "rAA: create table aggregated_arrival_tab_new";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE aggregated_arrival_tab_new ( ");
        sql.append("    id bigint(20) NOT NULL AUTO_INCREMENT, ");
        sql.append("    year int(11) NOT NULL, ");
        sql.append("    month int(11) NOT NULL, ");
        sql.append("    arrivals int(11) NOT NULL, ");
        sql.append("    material_revision bigint(20) NOT NULL, ");
        sql.append("    plant varchar(50) NOT NULL, ");
        sql.append("    movement_type varchar(50) NOT NULL, ");
        sql.append("    supplier varchar(50) NOT NULL, ");
        sql.append("    PRIMARY KEY (id) ");
        sql.append(") ENGINE=InnoDb CHARSET=utf8mb4 COLLATE utf8mb4_0900_ai_ci ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCopyData(TaskNodeLog ownTask) {
        String executionSection = "rAA: insert into aggregated_arrival_tab_new";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("insert into aggregated_arrival_tab_new ");
        sql.append("    (year, month, arrivals, material_revision, plant, movement_type, supplier) ");
        sql.append("select ");
        sql.append("    year(arrival_date), month(arrival_date), count(*) as arrivals, revision_id, plant, movement_type, supplier_code ");
        sql.append("from materialized_arrival_mv ");
        sql.append("group by ");
        sql.append("    year(arrival_date), month(arrival_date), revision_id, plant, movement_type, supplier_code");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execDropFormer(TaskNodeLog ownTask) {
        String executionSection = "rAA: drop table aggregated_arrival_tab";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "drop table if exists aggregated_arrival_tab";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRenameTmp2New(TaskNodeLog ownTask) {
        String executionSection = "rAA: rename aggregated_arrival_tab_new to aggregated_arrival_tab";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "ALTER TABLE aggregated_arrival_tab_new RENAME TO aggregated_arrival_tab";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        String indexCommand = "ALTER TABLE aggregated_arrival_tab ADD INDEX ";

        logger.info("rAA: create index 1");
        StringBuilder sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGA_YEAR_MONTH(year, month)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("rAA: create index 2");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGA_REVISION(material_revision)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("rAA: create index 3");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGA_PLANT(plant)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("rAA: create index 4");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGA_MOV(movement_type)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("rAA: create index 5");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGA_SUPPLIER(supplier)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        subTsk.finishTaskWithSuccess();
    }

}
