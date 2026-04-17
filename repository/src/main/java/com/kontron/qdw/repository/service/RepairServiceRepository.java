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
public class RepairServiceRepository extends AbstractRepository<RepairService, String> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent repair service by using the primary key of the provided object
     * @param repairService
     * @return the repair service or null if the object could not be found
     */
    @Generated
    public RepairService findById(RepairService repairService) {
        return findById(repairService.getCode());
    }

    /**
     * Merge the repair service object
     * @param repairService
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged repair service object
     */
    @Generated
    public RepairService merge(RepairService repairService, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByCodeAndName(repairService.getCode(), repairService.getName()))
            throw new UniqueConstraintViolationException("Repair service with name '" + repairService.getName() + "' already exists!");

        return merge(repairService, performFlush);
    }

    /**
     * Persist the repair service object
     * @param repairService
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted repair service object
     */
    @Generated
    public RepairService persist(RepairService repairService, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(repairService.getName()))
            throw new UniqueConstraintViolationException("Repair service with name '" + repairService.getName() + "' already exists!");

        return persist(repairService, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given repair service
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new repair service
     */
    @Generated
    public RepairService copy(RepairService sourceObject, RepairService targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new RepairService();
        }

        targetObject.setName("N/A");
        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());

        targetObject = persist(targetObject, true, false, false);

        return targetObject;
    }

    /**
     * Check if the given repair service already exists
     * @param name
     * @return true if the repair service already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(RepairService.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given repair service already exists
     * @param code
     * @param name
     * @return true if the repair service already exists
     */
    @Generated
    public boolean existsByCodeAndName(String code, String name) {
        if (code == null)
            throw new IllegalArgumentException("Parameter \"code\" must not be null!");

        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(RepairService.NQ_UK_EXISTS_BY_NAME_AND_CODE, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_CODE, code);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for repair service objects by using the provided parameters
     * @param name
     * @return a list that contains all repair service objects that match the provided filter criteria
     */
    @Generated
    public List<RepairService> searchByName(String name) {
        final TypedQuery<RepairService> query = em.createNamedQuery(RepairService.NQ_UK_SEARCH_BY_NAME, RepairService.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent repair service object by using the provided parameters
     * @param name
     * @return the repair service object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public RepairService findByName(String name) {
        final TypedQuery<RepairService> query = em.createNamedQuery(RepairService.NQ_UK_FIND_BY_NAME, RepairService.class);
        query.setParameter(PARAM_NAME, name);

        final List<RepairService> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

}