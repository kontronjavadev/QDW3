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
public class ShipmentRepository extends AbstractRepository<Shipment, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent shipment by using the primary key of the provided object
     * @param shipment
     * @return the shipment or null if the object could not be found
     */
    @Generated
    public Shipment findById(Shipment shipment) {
        return findById(shipment.getId());
    }

    /**
     * Create a deep copy of the given shipment
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new shipment
     */
    @Generated
    public Shipment copy(Shipment sourceObject, Shipment targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Shipment();
            targetObject.setSerialObject(sourceObject.getSerialObject());
        }

        targetObject.setOrderNumber(sourceObject.getOrderNumber());
        targetObject.setRebuildFlag(sourceObject.isRebuildFlag());
        targetObject.setShipmentDate(sourceObject.getShipmentDate());
        targetObject.setCustomer(sourceObject.getCustomer());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setMovementType(sourceObject.getMovementType());
        targetObject.setPlant(sourceObject.getPlant());

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
     * Get the customer of this shipment
     * @param id
     * @return the customer of this shipment, or null if it could not be found
     */
    @Generated
    public Customer getCustomer(long id) {
        final TypedQuery<Customer> query = em.createNamedQuery(Shipment.NQ_GET_CUSTOMER, Customer.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the material revision of this shipment
     * @param id
     * @return the material revision of this shipment, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(Shipment.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the movement type of this shipment
     * @param id
     * @return the movement type of this shipment, or null if it could not be found
     */
    @Generated
    public MovementType getMovementType(long id) {
        final TypedQuery<MovementType> query = em.createNamedQuery(Shipment.NQ_GET_MOVEMENTTYPE, MovementType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this shipment
     * @param id
     * @return the plant of this shipment, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(Shipment.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this shipment
     * @param id
     * @return the serial object of this shipment, or null if it could not be found
     */
    @Generated
    public SerialObject getSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(Shipment.NQ_GET_SERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'customer' attribute of this shipment
     * @param id
     * @param customer
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setCustomer(long id, Customer customer) {
        final Shipment bean = findById(id, true);

        bean.setCustomer(customer);
    }

    /**
     * Change the 'materialRevision' attribute of this shipment
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final Shipment bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'movementType' attribute of this shipment
     * @param id
     * @param movementType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMovementType(long id, MovementType movementType) {
        final Shipment bean = findById(id, true);

        bean.setMovementType(movementType);
    }

    /**
     * Change the 'plant' attribute of this shipment
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final Shipment bean = findById(id, true);

        bean.setPlant(plant);
    }

    /**
     * Change the 'serialObject' attribute of this shipment
     * @param id
     * @param serialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialObject(long id, SerialObject serialObject) {
        final Shipment bean = findById(id, true);

        bean.setSerialObject(serialObject);
    }

}