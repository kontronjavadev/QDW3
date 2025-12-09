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
public class AggregatedArrivalRepository extends AbstractRepository<AggregatedArrival, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent aggregated arrival by using the primary key of the provided object
     * @param aggregatedArrival
     * @return the aggregated arrival or null if the object could not be found
     */
    @Generated
    public AggregatedArrival findById(AggregatedArrival aggregatedArrival) {
        return findById(aggregatedArrival.getId());
    }

    /**
     * Create a deep copy of the given aggregated arrival
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new aggregated arrival
     */
    @Generated
    public AggregatedArrival copy(AggregatedArrival sourceObject, AggregatedArrival targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new AggregatedArrival();
        }

        targetObject.setArrivals(sourceObject.getArrivals());
        targetObject.setYear(sourceObject.getYear());
        targetObject.setMonth(sourceObject.getMonth());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setPlant(sourceObject.getPlant());
        targetObject.setMovementType(sourceObject.getMovementType());
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
     * Get the material revision of this aggregated arrival
     * @param id
     * @return the material revision of this aggregated arrival, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(AggregatedArrival.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this aggregated arrival
     * @param id
     * @return the plant of this aggregated arrival, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(AggregatedArrival.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the movement type of this aggregated arrival
     * @param id
     * @return the movement type of this aggregated arrival, or null if it could not be found
     */
    @Generated
    public MovementType getMovementType(long id) {
        final TypedQuery<MovementType> query = em.createNamedQuery(AggregatedArrival.NQ_GET_MOVEMENTTYPE, MovementType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the supplier of this aggregated arrival
     * @param id
     * @return the supplier of this aggregated arrival, or null if it could not be found
     */
    @Generated
    public Supplier getSupplier(long id) {
        final TypedQuery<Supplier> query = em.createNamedQuery(AggregatedArrival.NQ_GET_SUPPLIER, Supplier.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'materialRevision' attribute of this aggregated arrival
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final AggregatedArrival bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'plant' attribute of this aggregated arrival
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final AggregatedArrival bean = findById(id, true);

        bean.setPlant(plant);
    }

    /**
     * Change the 'movementType' attribute of this aggregated arrival
     * @param id
     * @param movementType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMovementType(long id, MovementType movementType) {
        final AggregatedArrival bean = findById(id, true);

        bean.setMovementType(movementType);
    }

    /**
     * Change the 'supplier' attribute of this aggregated arrival
     * @param id
     * @param supplier
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSupplier(long id, Supplier supplier) {
        final AggregatedArrival bean = findById(id, true);

        bean.setSupplier(supplier);
    }

}