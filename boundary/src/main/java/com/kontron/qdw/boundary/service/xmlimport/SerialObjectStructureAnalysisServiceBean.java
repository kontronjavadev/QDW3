package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

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
 * Analyse der SerialObject-Struktur.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class SerialObjectStructureAnalysisServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int DAYS_DELTA_STRUCTURE_ANALYSIS = 3;

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("serial object delta structure analysis");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    public void execTask(TaskNodeLog ownTask) {
        String executionSection = "collecting serial object ids";
        logger.info(executionSection);
        TaskNodeLog subTsk = ownTask.createNewSubTaskNode(executionSection);

        LocalDateTime thresholdDate = LocalDate.now()
                .minusDays(DAYS_DELTA_STRUCTURE_ANALYSIS)
                .atStartOfDay();
        Set<Long> idList = new TreeSet<>();
        TaskLeafLog leaf = null;

        try {
            // get all new shipments of mentioned interval
            leaf = subTsk.createNewSubTaskLeaf("select serial object ids from shipment");
            String sql = "select distinct a.serialObject.id from Shipment a where a.creationDate > :paramDate";
            idList.addAll(em.createQuery(sql, Long.class).setParameter("paramDate", thresholdDate).getResultList());
            leaf.finishTaskWithSuccess();

            // get all new and updated repairs of mentioned interval
            leaf = subTsk.createNewSubTaskLeaf("select serial object ids from service message");
            sql = "select distinct a.serialObject.id from ServiceMessage a where a.creationDate > :paramCreationDate or a.lastUpdate > :paramLastUpdate";
            idList.addAll(em.createQuery(sql, Long.class)
                    .setParameter("paramCreationDate", thresholdDate)
                    .setParameter("paramLastUpdate", thresholdDate)
                    .getResultList());
            leaf.finishTaskWithSuccess();

            // get all new and updated repairs of mentioned interval
            leaf = subTsk.createNewSubTaskLeaf("select serial object ids from x2 message");
            sql = "select distinct a.serialObject.id from X2Message a where a.creationDate > :paramCreationDate or a.lastUpdate > :paramLastUpdate";
            idList.addAll(em.createQuery(sql, Long.class)
                    .setParameter("paramCreationDate", thresholdDate)
                    .setParameter("paramLastUpdate", thresholdDate)
                    .getResultList());
            leaf.finishTaskWithSuccess();
        }
        catch (Exception e) {
            if (leaf != null) {
                leaf.finishTaskWithError(e);
            }
        }
        subTsk.finishTask();
        logger.info("size: {}", idList.size());
    }

}
