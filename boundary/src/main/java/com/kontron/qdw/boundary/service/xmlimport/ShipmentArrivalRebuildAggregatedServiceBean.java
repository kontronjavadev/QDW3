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
        // rASA --> shipt arr reb. aggrgted
        execDrop(ownTask);
    }



    private void execDrop(TaskNodeLog ownTask) {
        String executionSection = "rASA: drop table aggregated_shipment_arrival_tab_new";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        String sql = "DROP TABLE IF EXISTS aggregated_shipment_arrival_tab_new";

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCreate(TaskNodeLog ownTask) {
        String executionSection = "rASA: create table aggregated_shipment_arrival_tab_new";
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



    private void execXxx(TaskNodeLog ownTask) {
        String executionSection = "rASA: ";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
