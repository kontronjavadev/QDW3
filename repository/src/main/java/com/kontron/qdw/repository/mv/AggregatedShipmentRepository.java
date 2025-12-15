package com.kontron.qdw.repository.mv;

import com.kontron.qdw.domain.mv.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class AggregatedShipmentRepository extends AbstractRepository<AggregatedShipment, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent aggregated shipment by using the primary key of the provided object
     * @param aggregatedShipment
     * @return the aggregated shipment or null if the object could not be found
     */
    @Generated
    public AggregatedShipment findById(AggregatedShipment aggregatedShipment) {
        return findById(aggregatedShipment.getId());
    }

    /**
     * Create a deep copy of the given aggregated shipment
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new aggregated shipment
     */
    @Generated
    public AggregatedShipment copy(AggregatedShipment sourceObject, AggregatedShipment targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new AggregatedShipment();
        }

        targetObject.setShipments(sourceObject.getShipments());
        targetObject.setYear(sourceObject.getYear());
        targetObject.setMonth(sourceObject.getMonth());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setPlant(sourceObject.getPlant());
        targetObject.setCustomer(sourceObject.getCustomer());
        targetObject.setMovementType(sourceObject.getMovementType());

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
     * Get the material revision of this aggregated shipment
     * @param id
     * @return the material revision of this aggregated shipment, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(AggregatedShipment.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this aggregated shipment
     * @param id
     * @return the plant of this aggregated shipment, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(AggregatedShipment.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the customer of this aggregated shipment
     * @param id
     * @return the customer of this aggregated shipment, or null if it could not be found
     */
    @Generated
    public Customer getCustomer(long id) {
        final TypedQuery<Customer> query = em.createNamedQuery(AggregatedShipment.NQ_GET_CUSTOMER, Customer.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the movement type of this aggregated shipment
     * @param id
     * @return the movement type of this aggregated shipment, or null if it could not be found
     */
    @Generated
    public MovementType getMovementType(long id) {
        final TypedQuery<MovementType> query = em.createNamedQuery(AggregatedShipment.NQ_GET_MOVEMENTTYPE, MovementType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'materialRevision' attribute of this aggregated shipment
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final AggregatedShipment bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'plant' attribute of this aggregated shipment
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final AggregatedShipment bean = findById(id, true);

        bean.setPlant(plant);
    }

    /**
     * Change the 'customer' attribute of this aggregated shipment
     * @param id
     * @param customer
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setCustomer(long id, Customer customer) {
        final AggregatedShipment bean = findById(id, true);

        bean.setCustomer(customer);
    }

    /**
     * Change the 'movementType' attribute of this aggregated shipment
     * @param id
     * @param movementType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMovementType(long id, MovementType movementType) {
        final AggregatedShipment bean = findById(id, true);

        bean.setMovementType(movementType);
    }

}