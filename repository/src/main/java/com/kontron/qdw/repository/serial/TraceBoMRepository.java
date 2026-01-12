package com.kontron.qdw.repository.serial;

import com.kontron.qdw.domain.serial.*;
import java.util.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class TraceBoMRepository extends AbstractRepository<TraceBoM, Long> {
    @Generated
    private static final String PARAM_ID = "id";
    @Generated
    private final TraceBoMItemRepository traceBoMItemManager;
    @Generated
    private final IllegalTraceBoMItemRepository illegalTraceBoMItemManager;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMRepository() {
        this.traceBoMItemManager = null;
        this.illegalTraceBoMItemManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param traceBoMItemManager
     * @param illegalTraceBoMItemManager
     */
    @Inject
    @Generated
    public TraceBoMRepository(TraceBoMItemRepository traceBoMItemManager, IllegalTraceBoMItemRepository illegalTraceBoMItemManager) {
        this.traceBoMItemManager = traceBoMItemManager;
        this.illegalTraceBoMItemManager = illegalTraceBoMItemManager;
    }

    /**
     * Find a persistent trace BoM by using the primary key of the provided object
     * @param traceBoM
     * @return the trace BoM or null if the object could not be found
     */
    @Generated
    public TraceBoM findById(TraceBoM traceBoM) {
        return findById(traceBoM.getId());
    }

    /**
     * Create a deep copy of the given trace BoM
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new trace BoM
     */
    @Generated
    public TraceBoM copy(TraceBoM sourceObject, TraceBoM targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new TraceBoM();
        }

        targetObject.setDeliveryNoteNumber(sourceObject.getDeliveryNoteNumber());
        targetObject.setLotNumber(sourceObject.getLotNumber());
        targetObject.setOrderNumber(sourceObject.getOrderNumber());
        targetObject.setProductionDate(sourceObject.getProductionDate());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setSupplier(sourceObject.getSupplier());

        targetObject = persist(targetObject, false, false);

        for (final TraceBoMItem traceBoMItem : sourceObject.getTraceBoMItems()) {
            var newTraceBoMItem = new TraceBoMItem();
            newTraceBoMItem.setTraceBom(targetObject);

            newTraceBoMItem = traceBoMItemManager.copy(traceBoMItem, newTraceBoMItem, loggedOnUserId);
            targetObject.getTraceBoMItems().add(newTraceBoMItem);
        }

        for (final IllegalTraceBoMItem illegalTraceBoMItem : sourceObject.getIllegalTraceBoMItems()) {
            var newIllegalTraceBoMItem = new IllegalTraceBoMItem();
            newIllegalTraceBoMItem.setTraceBom(targetObject);

            newIllegalTraceBoMItem = illegalTraceBoMItemManager.copy(illegalTraceBoMItem, newIllegalTraceBoMItem, loggedOnUserId);
            targetObject.getIllegalTraceBoMItems().add(newIllegalTraceBoMItem);
        }

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Get the material revision of this trace BoM
     * @param id
     * @return the material revision of this trace BoM, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(TraceBoM.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the supplier of this trace BoM
     * @param id
     * @return the supplier of this trace BoM, or null if it could not be found
     */
    @Generated
    public Supplier getSupplier(long id) {
        final TypedQuery<Supplier> query = em.createNamedQuery(TraceBoM.NQ_GET_SUPPLIER, Supplier.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all trace BoM items of this trace BoM
     * @param id
     * @return a list of trace BoM items of this trace BoM
     */
    @Generated
    public List<TraceBoMItem> getTraceBoMItems(long id) {
        final TypedQuery<TraceBoMItem> query = em.createNamedQuery(TraceBoM.NQ_GET_TRACEBOMITEMS, TraceBoMItem.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Get all illegal trace BoM items of this trace BoM
     * @param id
     * @return a list of illegal trace BoM items of this trace BoM
     */
    @Generated
    public List<IllegalTraceBoMItem> getIllegalTraceBoMItems(long id) {
        final TypedQuery<IllegalTraceBoMItem> query = em.createNamedQuery(TraceBoM.NQ_GET_ILLEGALTRACEBOMITEMS, IllegalTraceBoMItem.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'materialRevision' attribute of this trace BoM
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final TraceBoM bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'supplier' attribute of this trace BoM
     * @param id
     * @param supplier
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSupplier(long id, Supplier supplier) {
        final TraceBoM bean = findById(id, true);

        bean.setSupplier(supplier);
    }

}