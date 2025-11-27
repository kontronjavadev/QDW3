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
public class MaterialClassBoundaryService {
    @Generated
    private final MaterialClassRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterialClassBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterialClassBoundaryService(MaterialClassRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new material class
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted material class object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialClassCreateDTO createMaterialClass(MaterialClassCreateDTO object) {
        // Create new object to be persisted
        var newMaterialClass = new MaterialClass();
        newMaterialClass.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newMaterialClass.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newMaterialClass.setComment(object.getComment() != null ? object.getComment().trim() : null);

        newMaterialClass = repository.persist(newMaterialClass, true, true);

        object.setCreationDate(newMaterialClass.getCreationDate());
        object.setVersion(newMaterialClass.getVersion());

        return object;
    }

    /**
     * Update existing material class object
     * @param object the material class to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateMaterialClass(MaterialClassUpdateDTO object) {
        // Find and attach object
        MaterialClass materialClass = repository.findById(object.getCode(), true);

        materialClass.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        materialClass.setComment(object.getComment() != null ? object.getComment().trim() : null);
        materialClass.setVersion(object.getVersion());

        repository.merge(materialClass, false);
    }

    /**
     * Find material class by its ID
     * @param code
     * @return the material class object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialClassUpdateDTO getMaterialClassForUpdate(String code) {
        // Find persistent object
        final MaterialClass materialClass = repository.findById(code, true);

        final var dto = new MaterialClassUpdateDTO();
        dto.setCode(materialClass.getCode());
        dto.setShortText(materialClass.getShortText());
        dto.setComment(materialClass.getComment());
        dto.setCreationDate(materialClass.getCreationDate());
        dto.setLastUpdate(materialClass.getLastUpdate());
        dto.setVersion(materialClass.getVersion());

        return dto;
    }

    /**
     * Search for material class objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of material class objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialClassSearchDTO> searchAllMaterialClasses(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialClassSearchDTO.SELECT_CODE);
        selectTokens.add(MaterialClassSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(MaterialClassSearchDTO.SELECT_COMMENT);
        selectTokens.add(MaterialClassSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(MaterialClassSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from MaterialClass a");

        return repository.search(searchObj, MaterialClassSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing material class
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterialClass(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected material class
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final MaterialClass sourceObject = repository.findById(sourceObjectId);
        final MaterialClass targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

    /**
     * Search for material class objects
     * @param filter
     * @return a list of material class objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialClassListDTO> findMaterialClasses(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialClassListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from MaterialClass a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(MaterialClassListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, MaterialClassListDTO.class, selectTokens);
    }

}