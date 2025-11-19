package com.kontron.qdw.repository.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class VerticalSectorRepository extends AbstractRepository<VerticalSector, String> {
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent vertical sector by using the primary key of the provided object
     * @param verticalSector
     * @return the vertical sector or null if the object could not be found
     */
    @Generated
    public VerticalSector findById(VerticalSector verticalSector) {
        return findById(verticalSector.getCode());
    }

    /**
     * Create a deep copy of the given vertical sector
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new vertical sector
     */
    @Generated
    public VerticalSector copy(VerticalSector sourceObject, VerticalSector targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new VerticalSector();
        }

        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setActive(sourceObject.isActive());
        targetObject.setBusinessUnit(sourceObject.getBusinessUnit());

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
     * Get the business unit of this vertical sector
     * @param code
     * @return the business unit of this vertical sector, or null if it could not be found
     */
    @Generated
    public BusinessUnit getBusinessUnit(String code) {
        final TypedQuery<BusinessUnit> query = em.createNamedQuery(VerticalSector.NQ_GET_BUSINESSUNIT, BusinessUnit.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'businessUnit' attribute of this vertical sector
     * @param code
     * @param businessUnit
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setBusinessUnit(String code, BusinessUnit businessUnit) {
        final VerticalSector bean = findById(code, true);

        bean.setBusinessUnit(businessUnit);
    }

}