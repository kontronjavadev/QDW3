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
public class RepairErrorCodeRepository extends AbstractRepository<RepairErrorCode, String> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent repair error code by using the primary key of the provided object
     * @param repairErrorCode
     * @return the repair error code or null if the object could not be found
     */
    @Generated
    public RepairErrorCode findById(RepairErrorCode repairErrorCode) {
        return findById(repairErrorCode.getCode());
    }

    /**
     * Merge the repair error code object
     * @param repairErrorCode
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged repair error code object
     */
    @Generated
    public RepairErrorCode merge(RepairErrorCode repairErrorCode, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByCodeAndName(repairErrorCode.getCode(), repairErrorCode.getName()))
            throw new UniqueConstraintViolationException("Repair error code with name '" + repairErrorCode.getName() + "' already exists!");

        return merge(repairErrorCode, performFlush);
    }

    /**
     * Persist the repair error code object
     * @param repairErrorCode
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted repair error code object
     */
    @Generated
    public RepairErrorCode persist(RepairErrorCode repairErrorCode, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(repairErrorCode.getName()))
            throw new UniqueConstraintViolationException("Repair error code with name '" + repairErrorCode.getName() + "' already exists!");

        return persist(repairErrorCode, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given repair error code
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new repair error code
     */
    @Generated
    public RepairErrorCode copy(RepairErrorCode sourceObject, RepairErrorCode targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new RepairErrorCode();
        }

        targetObject.setName("N/A");
        targetObject.setGroupName(sourceObject.getGroupName());
        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setActive(sourceObject.isActive());
        targetObject.setMappedTo(sourceObject.getMappedTo());

        targetObject = persist(targetObject, true, false, false);

        return targetObject;
    }

    /**
     * Check if the given repair error code already exists
     * @param name
     * @return true if the repair error code already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(RepairErrorCode.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given repair error code already exists
     * @param code
     * @param name
     * @return true if the repair error code already exists
     */
    @Generated
    public boolean existsByCodeAndName(String code, String name) {
        if (code == null)
            throw new IllegalArgumentException("Parameter \"code\" must not be null!");

        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(RepairErrorCode.NQ_UK_EXISTS_BY_NAME_AND_CODE, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_CODE, code);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for repair error code objects by using the provided parameters
     * @param name
     * @return a list that contains all repair error code objects that match the provided filter criteria
     */
    @Generated
    public List<RepairErrorCode> searchByName(String name) {
        final TypedQuery<RepairErrorCode> query = em.createNamedQuery(RepairErrorCode.NQ_UK_SEARCH_BY_NAME, RepairErrorCode.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent repair error code object by using the provided parameters
     * @param name
     * @return the repair error code object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public RepairErrorCode findByName(String name) {
        final TypedQuery<RepairErrorCode> query = em.createNamedQuery(RepairErrorCode.NQ_UK_FIND_BY_NAME, RepairErrorCode.class);
        query.setParameter(PARAM_NAME, name);

        final List<RepairErrorCode> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get the repair error code of this repair error code
     * @param code
     * @return the repair error code of this repair error code, or null if it could not be found
     */
    @Generated
    public RepairErrorCode getMappedTo(String code) {
        final TypedQuery<RepairErrorCode> query = em.createNamedQuery(RepairErrorCode.NQ_GET_MAPPEDTO, RepairErrorCode.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'mappedTo' attribute of this repair error code
     * @param code
     * @param mappedTo
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMappedTo(String code, RepairErrorCode mappedTo) {
        final RepairErrorCode bean = findById(code, true);

        bean.setMappedTo(mappedTo);
    }

}