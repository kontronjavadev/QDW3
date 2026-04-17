package com.kontron.qdw.repository.service;

import net.sourceforge.jbizmo.commons.random.*;
import java.util.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class RepairStateRepository extends AbstractRepository<RepairState, String> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent repair state by using the primary key of the provided object
     * @param repairState
     * @return the repair state or null if the object could not be found
     */
    @Generated
    public RepairState findById(RepairState repairState) {
        return findById(repairState.getCode());
    }

    /**
     * Merge the repair state object
     * @param repairState
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged repair state object
     */
    @Generated
    public RepairState merge(RepairState repairState, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByCodeAndName(repairState.getCode(), repairState.getName()))
            throw new UniqueConstraintViolationException("Repair state with name '" + repairState.getName() + "' already exists!");

        return merge(repairState, performFlush);
    }

    /**
     * Persist the repair state object
     * @param repairState
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted repair state object
     */
    @Generated
    public RepairState persist(RepairState repairState, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(repairState.getName()))
            throw new UniqueConstraintViolationException("Repair state with name '" + repairState.getName() + "' already exists!");

        return persist(repairState, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given repair state
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new repair state
     */
    @Generated
    public RepairState copy(RepairState sourceObject, RepairState targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new RepairState();
        }

        targetObject.setName("N/A");
        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());

        targetObject = persist(targetObject, true, false, false);

        return targetObject;
    }

    /**
     * Check if the given repair state already exists
     * @param name
     * @return true if the repair state already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(RepairState.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given repair state already exists
     * @param code
     * @param name
     * @return true if the repair state already exists
     */
    @Generated
    public boolean existsByCodeAndName(String code, String name) {
        if (code == null)
            throw new IllegalArgumentException("Parameter \"code\" must not be null!");

        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(RepairState.NQ_UK_EXISTS_BY_NAME_AND_CODE, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_CODE, code);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for repair state objects by using the provided parameters
     * @param name
     * @return a list that contains all repair state objects that match the provided filter criteria
     */
    @Generated
    public List<RepairState> searchByName(String name) {
        final TypedQuery<RepairState> query = em.createNamedQuery(RepairState.NQ_UK_SEARCH_BY_NAME, RepairState.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent repair state object by using the provided parameters
     * @param name
     * @return the repair state object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public RepairState findByName(String name) {
        final TypedQuery<RepairState> query = em.createNamedQuery(RepairState.NQ_UK_FIND_BY_NAME, RepairState.class);
        query.setParameter(PARAM_NAME, name);

        final List<RepairState> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

}