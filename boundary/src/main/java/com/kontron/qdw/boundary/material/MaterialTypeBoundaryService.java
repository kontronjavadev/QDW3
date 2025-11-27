package com.kontron.qdw.boundary.material;

import com.kontron.qdw.domain.material.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import jakarta.validation.ConstraintViolationException;
import java.util.*;
import com.kontron.qdw.repository.material.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class MaterialTypeBoundaryService {
    @Generated
    private final MaterialTypeRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterialTypeBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterialTypeBoundaryService(MaterialTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new material type
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted material type object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialTypeCreateDTO createMaterialType(MaterialTypeCreateDTO object) {
        // Create new object to be persisted
        var newMaterialType = new MaterialType();
        newMaterialType.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newMaterialType.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newMaterialType.setComment(object.getComment() != null ? object.getComment().trim() : null);

        newMaterialType = repository.persist(newMaterialType, true, true);

        object.setCreationDate(newMaterialType.getCreationDate());
        object.setVersion(newMaterialType.getVersion());

        return object;
    }

    /**
     * Update existing material type object
     * @param object the material type to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateMaterialType(MaterialTypeUpdateDTO object) {
        // Find and attach object
        MaterialType materialType = repository.findById(object.getCode(), true);

        materialType.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        materialType.setComment(object.getComment() != null ? object.getComment().trim() : null);
        materialType.setVersion(object.getVersion());

        repository.merge(materialType, false);
    }

    /**
     * Find material type by its ID
     * @param code
     * @return the material type object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialTypeUpdateDTO getMaterialTypeForUpdate(String code) {
        // Find persistent object
        final MaterialType materialType = repository.findById(code, true);

        final var dto = new MaterialTypeUpdateDTO();
        dto.setCode(materialType.getCode());
        dto.setShortText(materialType.getShortText());
        dto.setComment(materialType.getComment());
        dto.setCreationDate(materialType.getCreationDate());
        dto.setLastUpdate(materialType.getLastUpdate());
        dto.setVersion(materialType.getVersion());

        return dto;
    }

    /**
     * Search for material type objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of material type objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialTypeSearchDTO> searchAllMaterialTypes(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialTypeSearchDTO.SELECT_CODE);
        selectTokens.add(MaterialTypeSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(MaterialTypeSearchDTO.SELECT_COMMENT);
        selectTokens.add(MaterialTypeSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(MaterialTypeSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from MaterialType a");

        return repository.search(searchObj, MaterialTypeSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing material type
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterialType(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected material type
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final MaterialType sourceObject = repository.findById(sourceObjectId);
        final MaterialType targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

    /**
     * Search for material type objects
     * @param filter
     * @return a list of material type objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialTypeListDTO> findMaterialTypes(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialTypeListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from MaterialType a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(MaterialTypeListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, MaterialTypeListDTO.class, selectTokens);
    }

}