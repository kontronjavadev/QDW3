package com.kontron.qdw.boundary.material;

import com.kontron.qdw.domain.material.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.DEFAULT_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.material.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class MaterialBoundaryService {
    @Generated
    private final MaterialRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterialBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterialBoundaryService(MaterialRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for material objects
     * @param filter
     * @return a list of material objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialListDTO> findMaterials(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialListDTO.SELECT_ID);
        selectTokens.add(MaterialListDTO.SELECT_MATERIALNUMBER);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from Material a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(MaterialListDTO.SELECT_MATERIALNUMBER, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, MaterialListDTO.class, selectTokens);
    }

    /**
     * Find material by its ID
     * @param id
     * @return the material object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialListDTO findListMaterial(long id) {
        // Find persistent object
        final Material material = repository.findById(id, true);

        final var dto = new MaterialListDTO();
        dto.setId(material.getId());
        dto.setMaterialNumber(material.getMaterialNumber());

        return dto;
    }

    /**
     * Get all material revisions of a given material
     * @param id
     * @return a list of material revision objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialRevisionsDTO> getRevisionsOfMaterial(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialRevisionsDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterialRevisionsDTO.SELECT_REV2);
        selectTokens.add(MaterialRevisionsDTO.SELECT_REV6);
        selectTokens.add(MaterialRevisionsDTO.SELECT_ALTERNATIVENUMBER);
        selectTokens.add(MaterialRevisionsDTO.SELECT_COMMENT);
        selectTokens.add(MaterialRevisionsDTO.SELECT_ID);
        selectTokens.add(MaterialRevisionsDTO.SELECT_CREATIONDATE);
        selectTokens.add(MaterialRevisionsDTO.SELECT_LASTUPDATE);
        selectTokens.add(MaterialRevisionsDTO.SELECT_PLANTCODE);
        selectTokens.add(MaterialRevisionsDTO.SELECT_MATERIALID);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(DEFAULT_LIST_SIZE);
        searchObj.setFromClause("from Material x join x.revisions a join a.material b join a.plant c");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, MaterialRevisionsDTO.class, selectTokens);
    }

    /**
     * Update existing material object
     * @param object the material to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateMaterial(MaterialUpdateDTO object) {
        // Find and attach object
        Material material = repository.findById(object.getId(), true);

        material.setMaterialNumber(object.getMaterialNumber() != null ? object.getMaterialNumber().trim() : null);
        material.setSapNumber(object.getSapNumber() != null ? object.getSapNumber().trim() : null);
        material.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        material.setComment(object.getComment() != null ? object.getComment().trim() : null);
        material.setFitValue(object.getFitValue());
        material.setMaterialHierarchy(object.getMaterialHierarchy() != null ? object.getMaterialHierarchy().trim() : null);
        material.setSearchSubAssemblies(object.isSearchSubAssemblies());
        material.setVersion(object.getVersion());
        material.setOwnerLocation(repository.getReference(Location.class, object.getOwnerLocation().getCode()));
        material.setMaterialClass(repository.getReference(MaterialClass.class, object.getMaterialClass().getCode()));
        material.setMaterialType(repository.getReference(MaterialType.class, object.getMaterialType().getCode()));

        repository.merge(material, true, false);
    }

    /**
     * Find material by its ID
     * @param id
     * @return the material object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialUpdateDTO getMaterialForUpdate(long id) {
        // Find persistent object
        final Material material = repository.findById(id, true);

        final var dto = new MaterialUpdateDTO();
        dto.setMaterialNumber(material.getMaterialNumber());
        dto.setSapNumber(material.getSapNumber());
        dto.setShortText(material.getShortText());
        dto.setComment(material.getComment());
        dto.setFitValue(material.getFitValue());
        dto.setMaterialHierarchy(material.getMaterialHierarchy());
        dto.setSearchSubAssemblies(material.isSearchSubAssemblies());
        dto.setId(material.getId());
        dto.setVersion(material.getVersion());
        dto.setCreationDate(material.getCreationDate());
        dto.setLastUpdate(material.getLastUpdate());
        dto.setOwnerLocation(new LocationListDTO());
        dto.getOwnerLocation().setCode(material.getOwnerLocation().getCode());
        dto.setMaterialClass(new MaterialClassListDTO());
        dto.getMaterialClass().setCode(material.getMaterialClass().getCode());
        dto.setMaterialType(new MaterialTypeListDTO());
        dto.getMaterialType().setCode(material.getMaterialType().getCode());

        return dto;
    }

    /**
     * Find material by its ID
     * @param id
     * @return the material object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialDTO findMaterialById(long id) {
        // Find persistent object
        final Material material = repository.findById(id, true);

        final var dto = new MaterialDTO();
        dto.setMaterialNumber(material.getMaterialNumber());
        dto.setSapNumber(material.getSapNumber());
        dto.setShortText(material.getShortText());
        dto.setComment(material.getComment());
        dto.setFitValue(material.getFitValue());
        dto.setMaterialHierarchy(material.getMaterialHierarchy());
        dto.setSearchSubAssemblies(material.isSearchSubAssemblies());
        dto.setId(material.getId());
        dto.setVersion(material.getVersion());
        dto.setCreationDate(material.getCreationDate());
        dto.setLastUpdate(material.getLastUpdate());
        dto.setOwnerLocation(new LocationListDTO());
        dto.getOwnerLocation().setCode(material.getOwnerLocation().getCode());
        dto.setMaterialClass(new MaterialClassListDTO());
        dto.getMaterialClass().setCode(material.getMaterialClass().getCode());
        dto.setMaterialType(new MaterialTypeListDTO());
        dto.getMaterialType().setCode(material.getMaterialType().getCode());

        return dto;
    }

    /**
     * Create new material
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted material object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialCreateDTO createMaterial(MaterialCreateDTO object) {
        // Create new object to be persisted
        var newMaterial = new Material();
        newMaterial.setMaterialNumber(object.getMaterialNumber() != null ? object.getMaterialNumber().trim() : null);
        newMaterial.setSapNumber(object.getSapNumber() != null ? object.getSapNumber().trim() : null);
        newMaterial.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newMaterial.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newMaterial.setFitValue(object.getFitValue());
        newMaterial.setMaterialHierarchy(object.getMaterialHierarchy() != null ? object.getMaterialHierarchy().trim() : null);
        newMaterial.setSearchSubAssemblies(object.isSearchSubAssemblies());
        newMaterial.setOwnerLocation(repository.getReference(Location.class, object.getOwnerLocation().getCode()));
        newMaterial.setMaterialClass(repository.getReference(MaterialClass.class, object.getMaterialClass().getCode()));
        newMaterial.setMaterialType(repository.getReference(MaterialType.class, object.getMaterialType().getCode()));

        newMaterial = repository.persist(newMaterial, true, true, true);

        object.setId(newMaterial.getId());
        object.setVersion(newMaterial.getVersion());
        object.setCreationDate(newMaterial.getCreationDate());

        return object;
    }

    /**
     * Search for material objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of material objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialSearchDTO> searchAllMaterials(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialSearchDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(MaterialSearchDTO.SELECT_SAPNUMBER);
        selectTokens.add(MaterialSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(MaterialSearchDTO.SELECT_COMMENT);
        selectTokens.add(MaterialSearchDTO.SELECT_FITVALUE);
        selectTokens.add(MaterialSearchDTO.SELECT_MATERIALHIERARCHY);
        selectTokens.add(MaterialSearchDTO.SELECT_SEARCHSUBASSEMBLIES);
        selectTokens.add(MaterialSearchDTO.SELECT_ID);
        selectTokens.add(MaterialSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(MaterialSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(MaterialSearchDTO.SELECT_OWNERLOCATIONCODE);
        selectTokens.add(MaterialSearchDTO.SELECT_MATERIALCLASSCODE);
        selectTokens.add(MaterialSearchDTO.SELECT_MATERIALTYPECODE);

        searchObj.setFromClause("from Material a join a.ownerLocation b join a.materialClass c join a.materialType d");

        return repository.search(searchObj, MaterialSearchDTO.class, selectTokens);
    }

    /**
     * Count material objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllMaterials(SearchDTO searchObj) {
        searchObj.setFromClause("from Material a join a.ownerLocation b join a.materialClass c join a.materialType d");

        return repository.count(searchObj);
    }

    /**
     * Delete existing material
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterial(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected material
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final Material sourceObject = repository.findById(sourceObjectId);
        final Material targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}