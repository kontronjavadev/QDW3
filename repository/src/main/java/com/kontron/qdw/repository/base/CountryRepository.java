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
public class CountryRepository extends AbstractRepository<Country, String> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent country by using the primary key of the provided object
     * @param country
     * @return the country or null if the object could not be found
     */
    @Generated
    public Country findById(Country country) {
        return findById(country.getCode());
    }

    /**
     * Merge the country object
     * @param country
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged country object
     */
    @Generated
    public Country merge(Country country, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByCodeAndName(country.getCode(), country.getName()))
            throw new UniqueConstraintViolationException("Country with name '" + country.getName() + "' already exists!");

        return merge(country, performFlush);
    }

    /**
     * Persist the country object
     * @param country
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted country object
     */
    @Generated
    public Country persist(Country country, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(country.getName()))
            throw new UniqueConstraintViolationException("Country with name '" + country.getName() + "' already exists!");

        return persist(country, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given country
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new country
     */
    @Generated
    public Country copy(Country sourceObject, Country targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Country();
        }

        targetObject.setName("N/A");
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setActive(sourceObject.isActive());

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
     * Check if the given country already exists
     * @param name
     * @return true if the country already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Country.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given country already exists
     * @param code
     * @param name
     * @return true if the country already exists
     */
    @Generated
    public boolean existsByCodeAndName(String code, String name) {
        if (code == null)
            throw new IllegalArgumentException("Parameter \"code\" must not be null!");

        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Country.NQ_UK_EXISTS_BY_NAME_AND_CODE, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_CODE, code);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for country objects by using the provided parameters
     * @param name
     * @return a list that contains all country objects that match the provided filter criteria
     */
    @Generated
    public List<Country> searchByName(String name) {
        final TypedQuery<Country> query = em.createNamedQuery(Country.NQ_UK_SEARCH_BY_NAME, Country.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent country object by using the provided parameters
     * @param name
     * @return the country object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public Country findByName(String name) {
        final TypedQuery<Country> query = em.createNamedQuery(Country.NQ_UK_FIND_BY_NAME, Country.class);
        query.setParameter(PARAM_NAME, name);

        final List<Country> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

}