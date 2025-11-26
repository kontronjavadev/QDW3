package com.kontron.qdw.repository.material;

import java.util.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class MaterialRevisionRepository extends AbstractRepository<MaterialRevision, Long> {
    @Generated
    private static final String PARAM_ID = "id";
    @Generated
    private final BoMItemRepository boMItemManager;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionRepository() {
        this.boMItemManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param boMItemManager
     */
    @Inject
    @Generated
    public MaterialRevisionRepository(BoMItemRepository boMItemManager) {
        this.boMItemManager = boMItemManager;
    }

    /**
     * Find a persistent material revision by using the primary key of the provided object
     * @param materialRevision
     * @return the material revision or null if the object could not be found
     */
    @Generated
    public MaterialRevision findById(MaterialRevision materialRevision) {
        return findById(materialRevision.getId());
    }

    /**
     * Create a deep copy of the given material revision
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new material revision
     */
    @Generated
    public MaterialRevision copy(MaterialRevision sourceObject, MaterialRevision targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new MaterialRevision();
            targetObject.setMaterial(sourceObject.getMaterial());
        }

        targetObject.setRevisionNumber(sourceObject.getRevisionNumber());
        targetObject.setRev2(sourceObject.getRev2());
        targetObject.setRev6(sourceObject.getRev6());
        targetObject.setAlternativeNumber(sourceObject.getAlternativeNumber());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setPlant(sourceObject.getPlant());

        targetObject = persist(targetObject, false, false);

        for (final BoMItem boMItem : sourceObject.getBoMItems()) {
            var newBoMItem = new BoMItem();
            newBoMItem.setMaterialRevision(targetObject);

            newBoMItem = boMItemManager.copy(boMItem, newBoMItem, loggedOnUserId);
            targetObject.getBoMItems().add(newBoMItem);
        }

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Get the material of this material revision
     * @param id
     * @return the material of this material revision, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(MaterialRevision.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this material revision
     * @param id
     * @return the plant of this material revision, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(MaterialRevision.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all bom items of this material revision
     * @param id
     * @return a list of bom items of this material revision
     */
    @Generated
    public List<BoMItem> getBoMItems(long id) {
        final TypedQuery<BoMItem> query = em.createNamedQuery(MaterialRevision.NQ_GET_BOMITEMS, BoMItem.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'material' attribute of this material revision
     * @param id
     * @param material
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final MaterialRevision bean = findById(id, true);

        bean.setMaterial(material);
    }

    /**
     * Change the 'plant' attribute of this material revision
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final MaterialRevision bean = findById(id, true);

        bean.setPlant(plant);
    }

}