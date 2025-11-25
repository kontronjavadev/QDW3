package com.kontron.qdw.repository.material;

import net.sourceforge.jbizmo.commons.jpa.*;
import net.sourceforge.jbizmo.commons.random.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@Stateless
public class MaterialTypeRepository extends AbstractRepository<MaterialType, String> {
    /**
     * Find a persistent material type by using the primary key of the provided object
     * @param materialType
     * @return the material type or null if the object could not be found
     */
    @Generated
    public MaterialType findById(MaterialType materialType) {
        return findById(materialType.getCode());
    }

    /**
     * Create a deep copy of the given material type
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new material type
     */
    @Generated
    public MaterialType copy(MaterialType sourceObject, MaterialType targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new MaterialType();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

}