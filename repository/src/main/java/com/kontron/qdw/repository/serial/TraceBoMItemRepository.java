package com.kontron.qdw.repository.serial;

import com.kontron.qdw.domain.serial.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class TraceBoMItemRepository extends AbstractRepository<TraceBoMItem, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent trace BoM item by using the primary key of the provided object
     * @param traceBoMItem
     * @return the trace BoM item or null if the object could not be found
     */
    @Generated
    public TraceBoMItem findById(TraceBoMItem traceBoMItem) {
        return findById(traceBoMItem.getId());
    }

    /**
     * Create a deep copy of the given trace BoM item
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new trace BoM item
     */
    @Generated
    public TraceBoMItem copy(TraceBoMItem sourceObject, TraceBoMItem targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new TraceBoMItem();
            targetObject.setTraceBom(sourceObject.getTraceBom());
        }

        targetObject.setDateCode(sourceObject.getDateCode());
        targetObject.setInfoField1(sourceObject.getInfoField1());
        targetObject.setInfoField2(sourceObject.getInfoField2());
        targetObject.setInfoField3(sourceObject.getInfoField3());
        targetObject.setInfoField4(sourceObject.getInfoField4());
        targetObject.setManufacturerName(sourceObject.getManufacturerName());
        targetObject.setManufacturerRevision(sourceObject.getManufacturerRevision());
        targetObject.setOrderCode(sourceObject.getOrderCode());
        targetObject.setQuantity(sourceObject.getQuantity());
        targetObject.setMaterial(sourceObject.getMaterial());

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
     * Get the material of this trace BoM item
     * @param id
     * @return the material of this trace BoM item, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(TraceBoMItem.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the trace BoM of this trace BoM item
     * @param id
     * @return the trace BoM of this trace BoM item, or null if it could not be found
     */
    @Generated
    public TraceBoM getTraceBom(long id) {
        final TypedQuery<TraceBoM> query = em.createNamedQuery(TraceBoMItem.NQ_GET_TRACEBOM, TraceBoM.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'material' attribute of this trace BoM item
     * @param id
     * @param material
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final TraceBoMItem bean = findById(id, true);

        bean.setMaterial(material);
    }

    /**
     * Change the 'traceBom' attribute of this trace BoM item
     * @param id
     * @param traceBom
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setTraceBom(long id, TraceBoM traceBom) {
        final TraceBoMItem bean = findById(id, true);

        bean.setTraceBom(traceBom);
    }

}