package com.kontron.qdw.repository.serial;

import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class IllegalTraceBoMItemRepository extends AbstractRepository<IllegalTraceBoMItem, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent illegal trace BoM item by using the primary key of the provided object
     * @param illegalTraceBoMItem
     * @return the illegal trace BoM item or null if the object could not be found
     */
    @Generated
    public IllegalTraceBoMItem findById(IllegalTraceBoMItem illegalTraceBoMItem) {
        return findById(illegalTraceBoMItem.getId());
    }

    /**
     * Create a deep copy of the given illegal trace BoM item
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new illegal trace BoM item
     */
    @Generated
    public IllegalTraceBoMItem copy(IllegalTraceBoMItem sourceObject, IllegalTraceBoMItem targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new IllegalTraceBoMItem();
            targetObject.setTraceBom(sourceObject.getTraceBom());
        }

        targetObject.setDateCode(sourceObject.getDateCode());
        targetObject.setManufacturer(sourceObject.getManufacturer());
        targetObject.setManufacturerRevision(sourceObject.getManufacturerRevision());
        targetObject.setMaterialNumber(sourceObject.getMaterialNumber());
        targetObject.setOrderCode(sourceObject.getOrderCode());

        targetObject = persist(targetObject, false, false);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Get the trace BoM of this illegal trace BoM item
     * @param id
     * @return the trace BoM of this illegal trace BoM item, or null if it could not be found
     */
    @Generated
    public TraceBoM getTraceBom(long id) {
        final TypedQuery<TraceBoM> query = em.createNamedQuery(IllegalTraceBoMItem.NQ_GET_TRACEBOM, TraceBoM.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'traceBom' attribute of this illegal trace BoM item
     * @param id
     * @param traceBom
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setTraceBom(long id, TraceBoM traceBom) {
        final IllegalTraceBoMItem bean = findById(id, true);

        bean.setTraceBom(traceBom);
    }

}