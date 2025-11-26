package com.kontron.qdw.repository.material;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@Stateless
public class BoMItemRepository extends AbstractRepository<BoMItem, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent bom item by using the primary key of the provided object
     * @param boMItem
     * @return the bom item or null if the object could not be found
     */
    @Generated
    public BoMItem findById(BoMItem boMItem) {
        return findById(boMItem.getId());
    }

    /**
     * Create a deep copy of the given bom item
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new bom item
     */
    @Generated
    public BoMItem copy(BoMItem sourceObject, BoMItem targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new BoMItem();
            targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        }

        targetObject.setQuantity(sourceObject.getQuantity());
        targetObject.setLabel(sourceObject.getLabel());
        targetObject.setPosition(sourceObject.getPosition());
        targetObject.setMaterial(sourceObject.getMaterial());

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
     * Get the material of this bom item
     * @param id
     * @return the material of this bom item, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(BoMItem.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the material revision of this bom item
     * @param id
     * @return the material revision of this bom item, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(BoMItem.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'material' attribute of this bom item
     * @param id
     * @param material
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final BoMItem bean = findById(id, true);

        bean.setMaterial(material);
    }

    /**
     * Change the 'materialRevision' attribute of this bom item
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final BoMItem bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

}