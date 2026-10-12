package com.kontron.qdw.repository.material;

import java.util.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class MaterialNumberSetRepository extends AbstractRepository<MaterialNumberSet, Long> {
    @Generated
    private static final String PARAM_LABEL = "label";
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent material number set by using the primary key of the provided object
     * @param materialNumberSet
     * @return the material number set or null if the object could not be found
     */
    @Generated
    public MaterialNumberSet findById(MaterialNumberSet materialNumberSet) {
        return findById(materialNumberSet.getId());
    }

    /**
     * Merge the material number set object
     * @param materialNumberSet
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged material number set object
     */
    @Generated
    public MaterialNumberSet merge(MaterialNumberSet materialNumberSet, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByIdAndLabel(materialNumberSet.getId(), materialNumberSet.getLabel()))
            throw new UniqueConstraintViolationException("Material number set with label '" + materialNumberSet.getLabel() + "' already exists!");

        return merge(materialNumberSet, performFlush);
    }

    /**
     * Persist the material number set object
     * @param materialNumberSet
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted material number set object
     */
    @Generated
    public MaterialNumberSet persist(MaterialNumberSet materialNumberSet, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByLabel(materialNumberSet.getLabel()))
            throw new UniqueConstraintViolationException("Material number set with label '" + materialNumberSet.getLabel() + "' already exists!");

        return persist(materialNumberSet, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given material number set
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new material number set
     */
    @Generated
    public MaterialNumberSet copy(MaterialNumberSet sourceObject, MaterialNumberSet targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new MaterialNumberSet();
        }

        targetObject.setLabel(sourceObject.getLabel());

        targetObject = persist(targetObject, true, false, false);

        for (final Material material : sourceObject.getMaterials())
            targetObject.getMaterials().add(material);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Check if the given material number set already exists
     * @param label
     * @return true if the material number set already exists
     */
    @Generated
    public boolean existsByLabel(String label) {
        if (label == null)
            throw new IllegalArgumentException("Parameter \"label\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(MaterialNumberSet.NQ_UK_EXISTS_BY_LABEL, Long.class);
        query.setParameter(PARAM_LABEL, label);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given material number set already exists
     * @param id
     * @param label
     * @return true if the material number set already exists
     */
    @Generated
    public boolean existsByIdAndLabel(long id, String label) {
        if (label == null)
            throw new IllegalArgumentException("Parameter \"label\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(MaterialNumberSet.NQ_UK_EXISTS_BY_LABEL_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_LABEL, label);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for material number set objects by using the provided parameters
     * @param label
     * @return a list that contains all material number set objects that match the provided filter criteria
     */
    @Generated
    public List<MaterialNumberSet> searchByLabel(String label) {
        final TypedQuery<MaterialNumberSet> query = em.createNamedQuery(MaterialNumberSet.NQ_UK_SEARCH_BY_LABEL, MaterialNumberSet.class);
        query.setParameter(PARAM_LABEL, label);

        return query.getResultList();
    }

    /**
     * Find a persistent material number set object by using the provided parameters
     * @param label
     * @return the material number set object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public MaterialNumberSet findByLabel(String label) {
        final TypedQuery<MaterialNumberSet> query = em.createNamedQuery(MaterialNumberSet.NQ_UK_FIND_BY_LABEL, MaterialNumberSet.class);
        query.setParameter(PARAM_LABEL, label);

        final List<MaterialNumberSet> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get all materials of this material number set
     * @param id
     * @return a list of materials of this material number set
     */
    @Generated
    public List<Material> getMaterials(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(MaterialNumberSet.NQ_GET_MATERIALS, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Remove the persistent material object from the corresponding list of this material number set
     * @param id
     * @param material
     */
    @Generated
    public void removeMaterialFromMaterials(long id, Material material) {
        final MaterialNumberSet bean = findById(id, true);

        for (final Material item : bean.getMaterials())
            if (material.getId() == item.getId()) {
                bean.getMaterials().remove(item);
                return;
            }
    }

    /**
     * Add the persistent material object to the corresponding list of this material number set
     * @param id
     * @param material
     * @throws DuplicateCollectionEntryException if the caller tries to add an element to the collection twice
     */
    @Generated
    public void addMaterialToMaterials(long id, Material material) {
        final MaterialNumberSet bean = findById(id, true);

        // Prevent duplicate entries
        for (final Material item : bean.getMaterials())
            if (material.getId() == item.getId())
                throw new DuplicateCollectionEntryException("Entry already exists in this collection!");

        bean.getMaterials().add(material);
    }

}