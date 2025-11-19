package com.kontron.qdw.repository.base;

import java.util.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class SupplierRepository extends AbstractRepository<Supplier, String> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent supplier by using the primary key of the provided object
     * @param supplier
     * @return the supplier or null if the object could not be found
     */
    @Generated
    public Supplier findById(Supplier supplier) {
        return findById(supplier.getCode());
    }

    /**
     * Merge the supplier object
     * @param supplier
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged supplier object
     */
    @Generated
    public Supplier merge(Supplier supplier, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByCodeAndName(supplier.getCode(), supplier.getName()))
            throw new UniqueConstraintViolationException("Supplier with name '" + supplier.getName() + "' already exists!");

        return merge(supplier, performFlush);
    }

    /**
     * Persist the supplier object
     * @param supplier
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted supplier object
     */
    @Generated
    public Supplier persist(Supplier supplier, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(supplier.getName()))
            throw new UniqueConstraintViolationException("Supplier with name '" + supplier.getName() + "' already exists!");

        return persist(supplier, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given supplier
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new supplier
     */
    @Generated
    public Supplier copy(Supplier sourceObject, Supplier targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Supplier();
        }

        targetObject.setName("N/A");
        targetObject.setStreet(sourceObject.getStreet());
        targetObject.setZipCode(sourceObject.getZipCode());
        targetObject.setCity(sourceObject.getCity());
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setCountry(sourceObject.getCountry());

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
     * Check if the given supplier already exists
     * @param name
     * @return true if the supplier already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Supplier.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given supplier already exists
     * @param code
     * @param name
     * @return true if the supplier already exists
     */
    @Generated
    public boolean existsByCodeAndName(String code, String name) {
        if (code == null)
            throw new IllegalArgumentException("Parameter \"code\" must not be null!");

        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Supplier.NQ_UK_EXISTS_BY_NAME_AND_CODE, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_CODE, code);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for supplier objects by using the provided parameters
     * @param name
     * @return a list that contains all supplier objects that match the provided filter criteria
     */
    @Generated
    public List<Supplier> searchByName(String name) {
        final TypedQuery<Supplier> query = em.createNamedQuery(Supplier.NQ_UK_SEARCH_BY_NAME, Supplier.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent supplier object by using the provided parameters
     * @param name
     * @return the supplier object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public Supplier findByName(String name) {
        final TypedQuery<Supplier> query = em.createNamedQuery(Supplier.NQ_UK_FIND_BY_NAME, Supplier.class);
        query.setParameter(PARAM_NAME, name);

        final List<Supplier> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get the country of this supplier
     * @param code
     * @return the country of this supplier, or null if it could not be found
     */
    @Generated
    public Country getCountry(String code) {
        final TypedQuery<Country> query = em.createNamedQuery(Supplier.NQ_GET_COUNTRY, Country.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'country' attribute of this supplier
     * @param code
     * @param country
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setCountry(String code, Country country) {
        final Supplier bean = findById(code, true);

        bean.setCountry(country);
    }

}