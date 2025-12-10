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
public class AggregatedShipmentArrivalRepository extends AbstractRepository<AggregatedShipmentArrival, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent aggregated shipment arrival by using the primary key of the provided object
     * @param aggregatedShipmentArrival
     * @return the aggregated shipment arrival or null if the object could not be found
     */
    @Generated
    public AggregatedShipmentArrival findById(AggregatedShipmentArrival aggregatedShipmentArrival) {
        return findById(aggregatedShipmentArrival.getId());
    }

    /**
     * Create a deep copy of the given aggregated shipment arrival
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new aggregated shipment arrival
     */
    @Generated
    public AggregatedShipmentArrival copy(AggregatedShipmentArrival sourceObject, AggregatedShipmentArrival targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new AggregatedShipmentArrival();
        }

        targetObject.setShipments(sourceObject.getShipments());
        targetObject.setYear(sourceObject.getYear());
        targetObject.setMonth(sourceObject.getMonth());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setPlant(sourceObject.getPlant());
        targetObject.setCustomer(sourceObject.getCustomer());
        targetObject.setShipmentMovementType(sourceObject.getShipmentMovementType());
        targetObject.setArrivalMovementType(sourceObject.getArrivalMovementType());
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
     * Get the material revision of this aggregated shipment arrival
     * @param id
     * @return the material revision of this aggregated shipment arrival, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(AggregatedShipmentArrival.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this aggregated shipment arrival
     * @param id
     * @return the plant of this aggregated shipment arrival, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(AggregatedShipmentArrival.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the customer of this aggregated shipment arrival
     * @param id
     * @return the customer of this aggregated shipment arrival, or null if it could not be found
     */
    @Generated
    public Customer getCustomer(long id) {
        final TypedQuery<Customer> query = em.createNamedQuery(AggregatedShipmentArrival.NQ_GET_CUSTOMER, Customer.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the movement type of this aggregated shipment arrival
     * @param id
     * @return the movement type of this aggregated shipment arrival, or null if it could not be found
     */
    @Generated
    public MovementType getShipmentMovementType(long id) {
        final TypedQuery<MovementType> query = em.createNamedQuery(AggregatedShipmentArrival.NQ_GET_SHIPMENTMOVEMENTTYPE, MovementType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the movement type of this aggregated shipment arrival
     * @param id
     * @return the movement type of this aggregated shipment arrival, or null if it could not be found
     */
    @Generated
    public MovementType getArrivalMovementType(long id) {
        final TypedQuery<MovementType> query = em.createNamedQuery(AggregatedShipmentArrival.NQ_GET_ARRIVALMOVEMENTTYPE, MovementType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the supplier of this aggregated shipment arrival
     * @param id
     * @return the supplier of this aggregated shipment arrival, or null if it could not be found
     */
    @Generated
    public Supplier getSupplier(long id) {
        final TypedQuery<Supplier> query = em.createNamedQuery(AggregatedShipmentArrival.NQ_GET_SUPPLIER, Supplier.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'materialRevision' attribute of this aggregated shipment arrival
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final AggregatedShipmentArrival bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'plant' attribute of this aggregated shipment arrival
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final AggregatedShipmentArrival bean = findById(id, true);

        bean.setPlant(plant);
    }

    /**
     * Change the 'customer' attribute of this aggregated shipment arrival
     * @param id
     * @param customer
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setCustomer(long id, Customer customer) {
        final AggregatedShipmentArrival bean = findById(id, true);

        bean.setCustomer(customer);
    }

    /**
     * Change the 'shipmentMovementType' attribute of this aggregated shipment arrival
     * @param id
     * @param shipmentMovementType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setShipmentMovementType(long id, MovementType shipmentMovementType) {
        final AggregatedShipmentArrival bean = findById(id, true);

        bean.setShipmentMovementType(shipmentMovementType);
    }

    /**
     * Change the 'arrivalMovementType' attribute of this aggregated shipment arrival
     * @param id
     * @param arrivalMovementType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setArrivalMovementType(long id, MovementType arrivalMovementType) {
        final AggregatedShipmentArrival bean = findById(id, true);

        bean.setArrivalMovementType(arrivalMovementType);
    }

    /**
     * Change the 'supplier' attribute of this aggregated shipment arrival
     * @param id
     * @param supplier
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSupplier(long id, Supplier supplier) {
        final AggregatedShipmentArrival bean = findById(id, true);

        bean.setSupplier(supplier);
    }

}