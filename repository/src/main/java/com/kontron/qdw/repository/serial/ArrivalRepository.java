package com.kontron.qdw.repository.serial;

import com.kontron.qdw.domain.serial.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class ArrivalRepository extends AbstractRepository<Arrival, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent arrival by using the primary key of the provided object
     * @param arrival
     * @return the arrival or null if the object could not be found
     */
    @Generated
    public Arrival findById(Arrival arrival) {
        return findById(arrival.getId());
    }

    /**
     * Create a deep copy of the given arrival
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new arrival
     */
    @Generated
    public Arrival copy(Arrival sourceObject, Arrival targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Arrival();
            targetObject.setSerialObject(sourceObject.getSerialObject());
        }

        targetObject.setArrivalDate(sourceObject.getArrivalDate());
        targetObject.setOrderNumber(sourceObject.getOrderNumber());
        targetObject.setRebuildFlag(sourceObject.isRebuildFlag());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setMovementType(sourceObject.getMovementType());
        targetObject.setPlant(sourceObject.getPlant());
        targetObject.setSupplier(sourceObject.getSupplier());

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
     * Get the material revision of this arrival
     * @param id
     * @return the material revision of this arrival, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(Arrival.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the movement type of this arrival
     * @param id
     * @return the movement type of this arrival, or null if it could not be found
     */
    @Generated
    public MovementType getMovementType(long id) {
        final TypedQuery<MovementType> query = em.createNamedQuery(Arrival.NQ_GET_MOVEMENTTYPE, MovementType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this arrival
     * @param id
     * @return the plant of this arrival, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(Arrival.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this arrival
     * @param id
     * @return the serial object of this arrival, or null if it could not be found
     */
    @Generated
    public SerialObject getSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(Arrival.NQ_GET_SERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the supplier of this arrival
     * @param id
     * @return the supplier of this arrival, or null if it could not be found
     */
    @Generated
    public Supplier getSupplier(long id) {
        final TypedQuery<Supplier> query = em.createNamedQuery(Arrival.NQ_GET_SUPPLIER, Supplier.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'materialRevision' attribute of this arrival
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final Arrival bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'movementType' attribute of this arrival
     * @param id
     * @param movementType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMovementType(long id, MovementType movementType) {
        final Arrival bean = findById(id, true);

        bean.setMovementType(movementType);
    }

    /**
     * Change the 'plant' attribute of this arrival
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final Arrival bean = findById(id, true);

        bean.setPlant(plant);
    }

    /**
     * Change the 'serialObject' attribute of this arrival
     * @param id
     * @param serialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialObject(long id, SerialObject serialObject) {
        final Arrival bean = findById(id, true);

        bean.setSerialObject(serialObject);
    }

    /**
     * Change the 'supplier' attribute of this arrival
     * @param id
     * @param supplier
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSupplier(long id, Supplier supplier) {
        final Arrival bean = findById(id, true);

        bean.setSupplier(supplier);
    }

}