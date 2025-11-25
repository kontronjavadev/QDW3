package com.kontron.qdw.repository.base;

import net.sourceforge.jbizmo.commons.jpa.*;
import net.sourceforge.jbizmo.commons.random.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class PlantRepository extends AbstractRepository<Plant, String> {
    /**
     * Find a persistent plant by using the primary key of the provided object
     * @param plant
     * @return the plant or null if the object could not be found
     */
    @Generated
    public Plant findById(Plant plant) {
        return findById(plant.getCode());
    }

    /**
     * Create a deep copy of the given plant
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new plant
     */
    @Generated
    public Plant copy(Plant sourceObject, Plant targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new Plant();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setActive(sourceObject.isActive());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

}