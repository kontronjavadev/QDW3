package com.kontron.qdw.repository.material;

import net.sourceforge.jbizmo.commons.jpa.*;
import net.sourceforge.jbizmo.commons.random.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@Stateless
public class MaterialClassRepository extends AbstractRepository<MaterialClass, String> {
    /**
     * Find a persistent material class by using the primary key of the provided object
     * @param materialClass
     * @return the material class or null if the object could not be found
     */
    @Generated
    public MaterialClass findById(MaterialClass materialClass) {
        return findById(materialClass.getCode());
    }

    /**
     * Create a deep copy of the given material class
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new material class
     */
    @Generated
    public MaterialClass copy(MaterialClass sourceObject, MaterialClass targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new MaterialClass();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

}