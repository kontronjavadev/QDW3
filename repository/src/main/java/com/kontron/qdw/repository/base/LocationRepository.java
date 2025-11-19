package com.kontron.qdw.repository.base;

import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class LocationRepository extends AbstractRepository<Location, String> {
    /**
     * Find a persistent location by using the primary key of the provided object
     * @param location
     * @return the location or null if the object could not be found
     */
    @Generated
    public Location findById(Location location) {
        return findById(location.getCode());
    }

    /**
     * Create a deep copy of the given location
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new location
     */
    @Generated
    public Location copy(Location sourceObject, Location targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Location();
        }

        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setActive(sourceObject.isActive());

        targetObject = persist(targetObject, false, false);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

}