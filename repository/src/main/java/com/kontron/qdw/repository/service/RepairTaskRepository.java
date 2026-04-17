package com.kontron.qdw.repository.service;

import net.sourceforge.jbizmo.commons.random.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class RepairTaskRepository extends AbstractRepository<RepairTask, String> {
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent repair task by using the primary key of the provided object
     * @param repairTask
     * @return the repair task or null if the object could not be found
     */
    @Generated
    public RepairTask findById(RepairTask repairTask) {
        return findById(repairTask.getCode());
    }

    /**
     * Create a deep copy of the given repair task
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new repair task
     */
    @Generated
    public RepairTask copy(RepairTask sourceObject, RepairTask targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new RepairTask();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setMappedTo(sourceObject.getMappedTo());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

    /**
     * Get the repair task of this repair task
     * @param code
     * @return the repair task of this repair task, or null if it could not be found
     */
    @Generated
    public RepairTask getMappedTo(String code) {
        final TypedQuery<RepairTask> query = em.createNamedQuery(RepairTask.NQ_GET_MAPPEDTO, RepairTask.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'mappedTo' attribute of this repair task
     * @param code
     * @param mappedTo
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMappedTo(String code, RepairTask mappedTo) {
        final RepairTask bean = findById(code, true);

        bean.setMappedTo(mappedTo);
    }

}