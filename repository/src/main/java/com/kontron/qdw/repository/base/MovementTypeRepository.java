package com.kontron.qdw.repository.base;

import net.sourceforge.jbizmo.commons.jpa.*;
import net.sourceforge.jbizmo.commons.random.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class MovementTypeRepository extends AbstractRepository<MovementType, String> {
    /**
     * Find a persistent movement type by using the primary key of the provided object
     * @param movementType
     * @return the movement type or null if the object could not be found
     */
    @Generated
    public MovementType findById(MovementType movementType) {
        return findById(movementType.getCode());
    }

    /**
     * Create a deep copy of the given movement type
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new movement type
     */
    @Generated
    public MovementType copy(MovementType sourceObject, MovementType targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new MovementType();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setActive(sourceObject.isActive());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

}