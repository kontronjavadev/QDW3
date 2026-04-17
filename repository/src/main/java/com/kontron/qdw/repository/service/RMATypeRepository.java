package com.kontron.qdw.repository.service;

import net.sourceforge.jbizmo.commons.random.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class RMATypeRepository extends AbstractRepository<RMAType, String> {
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent RMA type by using the primary key of the provided object
     * @param rMAType
     * @return the RMA type or null if the object could not be found
     */
    @Generated
    public RMAType findById(RMAType rMAType) {
        return findById(rMAType.getCode());
    }

    /**
     * Create a deep copy of the given RMA type
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new RMA type
     */
    @Generated
    public RMAType copy(RMAType sourceObject, RMAType targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new RMAType();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setMappedTo(sourceObject.getMappedTo());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

    /**
     * Get the RMA type of this RMA type
     * @param code
     * @return the RMA type of this RMA type, or null if it could not be found
     */
    @Generated
    public RMAType getMappedTo(String code) {
        final TypedQuery<RMAType> query = em.createNamedQuery(RMAType.NQ_GET_MAPPEDTO, RMAType.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'mappedTo' attribute of this RMA type
     * @param code
     * @param mappedTo
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMappedTo(String code, RMAType mappedTo) {
        final RMAType bean = findById(code, true);

        bean.setMappedTo(mappedTo);
    }

}