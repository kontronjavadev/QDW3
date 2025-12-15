package com.kontron.qdw.repository.material;

import java.util.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class MaterialRepository extends AbstractRepository<Material, Long> {
    @Generated
    private static final String PARAM_MATERIALNUMBER = "materialNumber";
    @Generated
    private static final String PARAM_SAPNUMBER = "sapNumber";
    @Generated
    private static final String PARAM_ID = "id";
    @Generated
    private final MaterialRevisionRepository materialRevisionManager;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRepository() {
        this.materialRevisionManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param materialRevisionManager
     */
    @Inject
    @Generated
    public MaterialRepository(MaterialRevisionRepository materialRevisionManager) {
        this.materialRevisionManager = materialRevisionManager;
    }

    /**
     * Find a persistent material by using the primary key of the provided object
     * @param material
     * @return the material or null if the object could not be found
     */
    @Generated
    public Material findById(Material material) {
        return findById(material.getId());
    }

    /**
     * Merge the material object
     * @param material
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged material object
     */
    @Generated
    public Material merge(Material material, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByIdAndMaterialNumber(material.getId(), material.getMaterialNumber()))
            throw new UniqueConstraintViolationException("Material with material number '" + material.getMaterialNumber() + "' already exists!");

        if (performChecks && existsByIdAndSapNumber(material.getId(), material.getSapNumber()))
            throw new UniqueConstraintViolationException("Material with sap number '" + material.getSapNumber() + "' already exists!");

        return merge(material, performFlush);
    }

    /**
     * Persist the material object
     * @param material
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted material object
     */
    @Generated
    public Material persist(Material material, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByMaterialNumber(material.getMaterialNumber()))
            throw new UniqueConstraintViolationException("Material with material number '" + material.getMaterialNumber() + "' already exists!");

        if (performChecks && existsBySapNumber(material.getSapNumber()))
            throw new UniqueConstraintViolationException("Material with sap number '" + material.getSapNumber() + "' already exists!");

        return persist(material, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given material
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new material
     */
    @Generated
    public Material copy(Material sourceObject, Material targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Material();
        }

        targetObject.setMaterialNumber("N/A");
        targetObject.setSapNumber(sourceObject.getSapNumber());
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setFitValue(sourceObject.getFitValue());
        targetObject.setMaterialHierarchy(sourceObject.getMaterialHierarchy());
        targetObject.setSearchSubAssemblies(sourceObject.isSearchSubAssemblies());
        targetObject.setOwnerLocation(sourceObject.getOwnerLocation());
        targetObject.setMaterialClass(sourceObject.getMaterialClass());
        targetObject.setMaterialType(sourceObject.getMaterialType());

        targetObject = persist(targetObject, true, false, false);

        for (final MaterialRevision materialRevision : sourceObject.getRevisions()) {
            var newMaterialRevision = new MaterialRevision();
            newMaterialRevision.setMaterial(targetObject);

            newMaterialRevision = materialRevisionManager.copy(materialRevision, newMaterialRevision, loggedOnUserId);
            targetObject.getRevisions().add(newMaterialRevision);
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
     * Check if the given material already exists
     * @param materialNumber
     * @return true if the material already exists
     */
    @Generated
    public boolean existsByMaterialNumber(String materialNumber) {
        if (materialNumber == null)
            throw new IllegalArgumentException("Parameter \"materialNumber\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Material.NQ_UK_EXISTS_BY_MATERIALNUMBER, Long.class);
        query.setParameter(PARAM_MATERIALNUMBER, materialNumber);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given material already exists
     * @param id
     * @param materialNumber
     * @return true if the material already exists
     */
    @Generated
    public boolean existsByIdAndMaterialNumber(long id, String materialNumber) {
        if (materialNumber == null)
            throw new IllegalArgumentException("Parameter \"materialNumber\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Material.NQ_UK_EXISTS_BY_MATERIALNUMBER_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_MATERIALNUMBER, materialNumber);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for material objects by using the provided parameters
     * @param materialNumber
     * @return a list that contains all material objects that match the provided filter criteria
     */
    @Generated
    public List<Material> searchByMaterialNumber(String materialNumber) {
        final TypedQuery<Material> query = em.createNamedQuery(Material.NQ_UK_SEARCH_BY_MATERIALNUMBER, Material.class);
        query.setParameter(PARAM_MATERIALNUMBER, materialNumber);

        return query.getResultList();
    }

    /**
     * Find a persistent material object by using the provided parameters
     * @param materialNumber
     * @return the material object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public Material findByMaterialNumber(String materialNumber) {
        final TypedQuery<Material> query = em.createNamedQuery(Material.NQ_UK_FIND_BY_MATERIALNUMBER, Material.class);
        query.setParameter(PARAM_MATERIALNUMBER, materialNumber);

        final List<Material> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Check if the given material already exists
     * @param sapNumber
     * @return true if the material already exists
     */
    @Generated
    public boolean existsBySapNumber(String sapNumber) {
        if (sapNumber == null)
            throw new IllegalArgumentException("Parameter \"sapNumber\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Material.NQ_UK_EXISTS_BY_SAPNUMBER, Long.class);
        query.setParameter(PARAM_SAPNUMBER, sapNumber);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given material already exists
     * @param id
     * @param sapNumber
     * @return true if the material already exists
     */
    @Generated
    public boolean existsByIdAndSapNumber(long id, String sapNumber) {
        if (sapNumber == null)
            throw new IllegalArgumentException("Parameter \"sapNumber\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Material.NQ_UK_EXISTS_BY_SAPNUMBER_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_SAPNUMBER, sapNumber);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for material objects by using the provided parameters
     * @param sapNumber
     * @return a list that contains all material objects that match the provided filter criteria
     */
    @Generated
    public List<Material> searchBySapNumber(String sapNumber) {
        final TypedQuery<Material> query = em.createNamedQuery(Material.NQ_UK_SEARCH_BY_SAPNUMBER, Material.class);
        query.setParameter(PARAM_SAPNUMBER, sapNumber);

        return query.getResultList();
    }

    /**
     * Find a persistent material object by using the provided parameters
     * @param sapNumber
     * @return the material object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public Material findBySapNumber(String sapNumber) {
        final TypedQuery<Material> query = em.createNamedQuery(Material.NQ_UK_FIND_BY_SAPNUMBER, Material.class);
        query.setParameter(PARAM_SAPNUMBER, sapNumber);

        final List<Material> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get the location of this material
     * @param id
     * @return the location of this material, or null if it could not be found
     */
    @Generated
    public Location getOwnerLocation(long id) {
        final TypedQuery<Location> query = em.createNamedQuery(Material.NQ_GET_OWNERLOCATION, Location.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the material class of this material
     * @param id
     * @return the material class of this material, or null if it could not be found
     */
    @Generated
    public MaterialClass getMaterialClass(long id) {
        final TypedQuery<MaterialClass> query = em.createNamedQuery(Material.NQ_GET_MATERIALCLASS, MaterialClass.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the material type of this material
     * @param id
     * @return the material type of this material, or null if it could not be found
     */
    @Generated
    public MaterialType getMaterialType(long id) {
        final TypedQuery<MaterialType> query = em.createNamedQuery(Material.NQ_GET_MATERIALTYPE, MaterialType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all material revisions of this material
     * @param id
     * @return a list of material revisions of this material
     */
    @Generated
    public List<MaterialRevision> getRevisions(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(Material.NQ_GET_REVISIONS, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'ownerLocation' attribute of this material
     * @param id
     * @param ownerLocation
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setOwnerLocation(long id, Location ownerLocation) {
        final Material bean = findById(id, true);

        bean.setOwnerLocation(ownerLocation);
    }

    /**
     * Change the 'materialClass' attribute of this material
     * @param id
     * @param materialClass
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialClass(long id, MaterialClass materialClass) {
        final Material bean = findById(id, true);

        bean.setMaterialClass(materialClass);
    }

    /**
     * Change the 'materialType' attribute of this material
     * @param id
     * @param materialType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialType(long id, MaterialType materialType) {
        final Material bean = findById(id, true);

        bean.setMaterialType(materialType);
    }

    /**
     * Get all product lines of this material
     * @param id
     * @return a list of product lines of this material
     */
    @Generated
    public List<ProductLine> getProductLines(long id) {
        final TypedQuery<ProductLine> query = em.createNamedQuery(Material.NQ_GET_PRODUCTLINES, ProductLine.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Get all material number sets of this material
     * @param id
     * @return a list of material number sets of this material
     */
    @Generated
    public List<MaterialNumberSet> getMaterialNumberSets(long id) {
        final TypedQuery<MaterialNumberSet> query = em.createNamedQuery(Material.NQ_GET_MATERIALNUMBERSETS, MaterialNumberSet.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Remove the persistent product line object from the corresponding list of this material
     * @param id
     * @param productLine
     */
    @Generated
    public void removeProductLineFromProductLines(long id, ProductLine productLine) {
        final Material bean = findById(id, true);

        for (final ProductLine item : bean.getProductLines())
            if (productLine.getId() == item.getId()) {
                bean.getProductLines().remove(item);
                return;
            }
    }

    /**
     * Add the persistent product line object to the corresponding list of this material
     * @param id
     * @param productLine
     * @throws DuplicateCollectionEntryException if the caller tries to add an element to the collection twice
     */
    @Generated
    public void addProductLineToProductLines(long id, ProductLine productLine) {
        final Material bean = findById(id, true);

        // Prevent duplicate entries
        for (final ProductLine item : bean.getProductLines())
            if (productLine.getId() == item.getId())
                throw new DuplicateCollectionEntryException("Entry already exists in this collection!");

        bean.getProductLines().add(productLine);
    }

}