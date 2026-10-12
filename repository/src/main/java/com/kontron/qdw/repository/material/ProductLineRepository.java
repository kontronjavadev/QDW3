package com.kontron.qdw.repository.material;

import java.util.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class ProductLineRepository extends AbstractRepository<ProductLine, Long> {
    @Generated
    private static final String PARAM_LABEL = "label";
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent product line by using the primary key of the provided object
     * @param productLine
     * @return the product line or null if the object could not be found
     */
    @Generated
    public ProductLine findById(ProductLine productLine) {
        return findById(productLine.getId());
    }

    /**
     * Merge the product line object
     * @param productLine
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged product line object
     */
    @Generated
    public ProductLine merge(ProductLine productLine, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByIdAndLabel(productLine.getId(), productLine.getLabel()))
            throw new UniqueConstraintViolationException("Product line with label '" + productLine.getLabel() + "' already exists!");

        return merge(productLine, performFlush);
    }

    /**
     * Persist the product line object
     * @param productLine
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted product line object
     */
    @Generated
    public ProductLine persist(ProductLine productLine, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByLabel(productLine.getLabel()))
            throw new UniqueConstraintViolationException("Product line with label '" + productLine.getLabel() + "' already exists!");

        return persist(productLine, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given product line
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new product line
     */
    @Generated
    public ProductLine copy(ProductLine sourceObject, ProductLine targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new ProductLine();
        }

        targetObject.setLabel("N/A");

        targetObject = persist(targetObject, true, false, false);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Check if the given product line already exists
     * @param label
     * @return true if the product line already exists
     */
    @Generated
    public boolean existsByLabel(String label) {
        if (label == null)
            throw new IllegalArgumentException("Parameter \"label\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(ProductLine.NQ_UK_EXISTS_BY_LABEL, Long.class);
        query.setParameter(PARAM_LABEL, label);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given product line already exists
     * @param id
     * @param label
     * @return true if the product line already exists
     */
    @Generated
    public boolean existsByIdAndLabel(long id, String label) {
        if (label == null)
            throw new IllegalArgumentException("Parameter \"label\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(ProductLine.NQ_UK_EXISTS_BY_LABEL_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_LABEL, label);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for product line objects by using the provided parameters
     * @param label
     * @return a list that contains all product line objects that match the provided filter criteria
     */
    @Generated
    public List<ProductLine> searchByLabel(String label) {
        final TypedQuery<ProductLine> query = em.createNamedQuery(ProductLine.NQ_UK_SEARCH_BY_LABEL, ProductLine.class);
        query.setParameter(PARAM_LABEL, label);

        return query.getResultList();
    }

    /**
     * Find a persistent product line object by using the provided parameters
     * @param label
     * @return the product line object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public ProductLine findByLabel(String label) {
        final TypedQuery<ProductLine> query = em.createNamedQuery(ProductLine.NQ_UK_FIND_BY_LABEL, ProductLine.class);
        query.setParameter(PARAM_LABEL, label);

        final List<ProductLine> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get all materials of this product line
     * @param id
     * @return a list of materials of this product line
     */
    @Generated
    public List<Material> getMaterials(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(ProductLine.NQ_GET_MATERIALS, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

}