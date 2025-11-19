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
public class CustomerRepository extends AbstractRepository<Customer, String> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent customer by using the primary key of the provided object
     * @param customer
     * @return the customer or null if the object could not be found
     */
    @Generated
    public Customer findById(Customer customer) {
        return findById(customer.getCode());
    }

    /**
     * Merge the customer object
     * @param customer
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged customer object
     */
    @Generated
    public Customer merge(Customer customer, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByCodeAndName(customer.getCode(), customer.getName()))
            throw new UniqueConstraintViolationException("Customer with name '" + customer.getName() + "' already exists!");

        return merge(customer, performFlush);
    }

    /**
     * Persist the customer object
     * @param customer
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted customer object
     */
    @Generated
    public Customer persist(Customer customer, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(customer.getName()))
            throw new UniqueConstraintViolationException("Customer with name '" + customer.getName() + "' already exists!");

        return persist(customer, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given customer
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new customer
     */
    @Generated
    public Customer copy(Customer sourceObject, Customer targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Customer();
        }

        targetObject.setName("N/A");
        targetObject.setStreet(sourceObject.getStreet());
        targetObject.setZipCode(sourceObject.getZipCode());
        targetObject.setCity(sourceObject.getCity());
        targetObject.setInternal(sourceObject.isInternal());
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setCountry(sourceObject.getCountry());
        targetObject.setVerticalSector(sourceObject.getVerticalSector());

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
     * Check if the given customer already exists
     * @param name
     * @return true if the customer already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Customer.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given customer already exists
     * @param code
     * @param name
     * @return true if the customer already exists
     */
    @Generated
    public boolean existsByCodeAndName(String code, String name) {
        if (code == null)
            throw new IllegalArgumentException("Parameter \"code\" must not be null!");

        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Customer.NQ_UK_EXISTS_BY_NAME_AND_CODE, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_CODE, code);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for customer objects by using the provided parameters
     * @param name
     * @return a list that contains all customer objects that match the provided filter criteria
     */
    @Generated
    public List<Customer> searchByName(String name) {
        final TypedQuery<Customer> query = em.createNamedQuery(Customer.NQ_UK_SEARCH_BY_NAME, Customer.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent customer object by using the provided parameters
     * @param name
     * @return the customer object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public Customer findByName(String name) {
        final TypedQuery<Customer> query = em.createNamedQuery(Customer.NQ_UK_FIND_BY_NAME, Customer.class);
        query.setParameter(PARAM_NAME, name);

        final List<Customer> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get the country of this customer
     * @param code
     * @return the country of this customer, or null if it could not be found
     */
    @Generated
    public Country getCountry(String code) {
        final TypedQuery<Country> query = em.createNamedQuery(Customer.NQ_GET_COUNTRY, Country.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the vertical sector of this customer
     * @param code
     * @return the vertical sector of this customer, or null if it could not be found
     */
    @Generated
    public VerticalSector getVerticalSector(String code) {
        final TypedQuery<VerticalSector> query = em.createNamedQuery(Customer.NQ_GET_VERTICALSECTOR, VerticalSector.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'country' attribute of this customer
     * @param code
     * @param country
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setCountry(String code, Country country) {
        final Customer bean = findById(code, true);

        bean.setCountry(country);
    }

    /**
     * Change the 'verticalSector' attribute of this customer
     * @param code
     * @param verticalSector
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setVerticalSector(String code, VerticalSector verticalSector) {
        final Customer bean = findById(code, true);

        bean.setVerticalSector(verticalSector);
    }

}