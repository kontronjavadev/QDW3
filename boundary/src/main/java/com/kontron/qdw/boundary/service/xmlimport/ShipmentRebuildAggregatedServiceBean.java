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
public class ShipmentRebuildAggregatedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("rebuild materialized shipment");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    public void execTask(TaskNodeLog ownTask) {
        TaskNodeLog parentTask = ownTask.getParentTask();
        // execXxx(ownTask);
    }

    private void execXxx(TaskNodeLog ownTask) {
        String executionSection = "rAA: ";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
