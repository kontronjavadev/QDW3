package com.kontron.qdw.repository.service;

import net.sourceforge.jbizmo.commons.jpa.*;
import net.sourceforge.jbizmo.commons.random.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.service.*;

@Stateless
public class RepairLocationRepository extends AbstractRepository<RepairLocation, String> {
    /**
     * Find a persistent repair location by using the primary key of the provided object
     * @param repairLocation
     * @return the repair location or null if the object could not be found
     */
    @Generated
    public RepairLocation findById(RepairLocation repairLocation) {
        return findById(repairLocation.getCode());
    }

    /**
     * Create a deep copy of the given repair location
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new repair location
     */
    @Generated
    public RepairLocation copy(RepairLocation sourceObject, RepairLocation targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new RepairLocation();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

}