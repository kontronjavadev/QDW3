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
public class ShipmentArrivalRebuildAggregatedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("shipment arrival rebuild aggregated");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask);
        execCreate(ownTask);
        execInsertData(ownTask);
        execDropFormer(ownTask);
        execRenameTmp2New(ownTask);
        execAddIndices(ownTask);
    }



    private void execDrop(TaskNodeLog ownTask) {
        String executionSection = "shipt arr reb. aggrgted: drop table aggregated_shipment_arrival_tab_new";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "DROP TABLE IF EXISTS aggregated_shipment_arrival_tab_new";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCreate(TaskNodeLog ownTask) {
        String executionSection = "shipt arr reb. aggrgted: create table aggregated_shipment_arrival_tab_new";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE  aggregated_shipment_arrival_tab_new(id bigint(20) NOT NULL AUTO_INCREMENT,");
        sql.append("    year int(11) NOT NULL,");
        sql.append("    month int(11) NOT NULL,");
        sql.append("    shipments int(11) NOT NULL,");
        sql.append("    customer varchar(50) NOT NULL,");
        sql.append("    supplier varchar(50),");
        sql.append("    material_revision bigint(20) NOT NULL,");
        sql.append("    shipment_movement_type varchar(50) NOT NULL,");
        sql.append("    arrival_movement_type varchar(50),");
        sql.append("    plant varchar(50) NOT NULL,");
        sql.append("    PRIMARY KEY (id)");
        sql.append(") ENGINE=InnoDb CHARSET=utf8mb4 COLLATE utf8mb4_0900_ai_ci ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execInsertData(TaskNodeLog ownTask) {
        String executionSection = "shipt arr reb. aggrgted: insert into aggregated_shipment_arrival_tab_new";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("insert into aggregated_shipment_arrival_tab_new ");
        sql.append("(year, month, shipments, customer, supplier, material_revision, shipment_movement_type, arrival_movement_type, plant) ");
        sql.append("select ");
        sql.append("    year(shipment_date), month(shipment_date), count(*) as shipments, customer_code, ");
        sql.append("    supplier_code, revision_id, shipment_movement_type, arrival_movement_type, plant ");
        sql.append("from arrival_shipment_mv ");
        sql.append("group by ");
        sql.append("    year(shipment_date), month(shipment_date), revision_id, plant, ");
        sql.append("    shipment_movement_type, arrival_movement_type, customer_code, supplier_code");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execDropFormer(TaskNodeLog ownTask) {
        String executionSection = "shipt arr reb. aggrgted: drop table aggregated_shipment_arrival_tab";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "drop table if exists aggregated_shipment_arrival_tab";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execRenameTmp2New(TaskNodeLog ownTask) {
        String executionSection = "shipt arr reb. aggrgted: rename aggregated_shipment_arrival_tab_new to aggregated_shipment_arrival_tab";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "ALTER TABLE aggregated_shipment_arrival_tab_new RENAME TO aggregated_shipment_arrival_tab";

        em.createNativeQuery(sql).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execAddIndices(TaskNodeLog ownTask) {
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf("create indices");
        String indexCommand = "ALTER TABLE aggregated_shipment_arrival_tab ADD INDEX ";

        logger.info("shipt arr reb. aggrgted: create index 1");
        StringBuilder sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGSA_YEAR_MONTH(year, month)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("shipt arr reb. aggrgted: create index 2");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGSA_REVISION(material_revision)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("shipt arr reb. aggrgted: create index 3");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGSA_PLANT(plant)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("shipt arr reb. aggrgted: create index 4");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGSA_SHIP_MOV(shipment_movement_type)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("shipt arr reb. aggrgted: create index 5");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGSA_CUSTOMER(customer)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("shipt arr reb. aggrgted: create index 6");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGSA_AR_MOV(arrival_movement_type)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        logger.info("shipt arr reb. aggrgted: create index 7");
        sql = new StringBuilder();
        sql.append(indexCommand).append("IN_AGSA_SUPPLIER(supplier)");
        em.createNativeQuery(sql.toString()).executeUpdate();

        subTsk.finishTaskWithSuccess();
    }

}
