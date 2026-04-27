package com.kontron.qdw.boundary.service;

import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.service.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.service.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class RMATypeBoundaryService {
    @Generated
    private final RMATypeRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RMATypeBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RMATypeBoundaryService(RMATypeRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for RMA type objects
     * @param filter
     * @return a list of RMA type objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RMATypeListDTO> findRMATypes(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RMATypeListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RMAType a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RMATypeListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RMATypeListDTO.class, selectTokens);
    }

    /**
     * Find RMA type by its ID
     * @param code
     * @return the RMA type object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RMATypeDTO findRMATypeById(String code) {
        // Find persistent object
        final RMAType rMAType = repository.findById(code, true);

        final var dto = new RMATypeDTO();
        dto.setCode(rMAType.getCode());
        dto.setShortText(rMAType.getShortText());
        dto.setComment(rMAType.getComment());
        dto.setCreationDate(rMAType.getCreationDate());
        dto.setLastUpdate(rMAType.getLastUpdate());
        dto.setVersion(rMAType.getVersion());

        if (rMAType.getMappedTo() != null) {
            dto.setMappedTo(new RMATypeListDTO());
            dto.getMappedTo().setCode(rMAType.getMappedTo().getCode());
        }


        return dto;
    }

    /**
     * Update existing RMA type object
     * @param object the RMA type to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateRMAType(RMATypeUpdateDTO object) {
        // Find and attach object
        RMAType rMAType = repository.findById(object.getCode(), true);

        rMAType.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        rMAType.setComment(object.getComment() != null ? object.getComment().trim() : null);
        rMAType.setVersion(object.getVersion());

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            rMAType.setMappedTo(null);
        else
            rMAType.setMappedTo(repository.getReference(RMAType.class, object.getMappedTo().getCode()));


        repository.merge(rMAType, false);
    }

    /**
     * Find RMA type by its ID
     * @param code
     * @return the RMA type object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RMATypeUpdateDTO getRMATypeForUpdate(String code) {
        // Find persistent object
        final RMAType rMAType = repository.findById(code, true);

        final var dto = new RMATypeUpdateDTO();
        dto.setCode(rMAType.getCode());
        dto.setShortText(rMAType.getShortText());
        dto.setComment(rMAType.getComment());
        dto.setCreationDate(rMAType.getCreationDate());
        dto.setLastUpdate(rMAType.getLastUpdate());
        dto.setVersion(rMAType.getVersion());

        if (rMAType.getMappedTo() != null) {
            dto.setMappedTo(new RMATypeListDTO());
            dto.getMappedTo().setCode(rMAType.getMappedTo().getCode());
        }


        return dto;
    }

    /**
     * Create new RMA type
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted RMA type object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RMATypeCreateDTO createRMAType(RMATypeCreateDTO object) {
        // Create new object to be persisted
        var newRMAType = new RMAType();
        newRMAType.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newRMAType.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newRMAType.setComment(object.getComment() != null ? object.getComment().trim() : null);

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            newRMAType.setMappedTo(null);
        else
            newRMAType.setMappedTo(repository.getReference(RMAType.class, object.getMappedTo().getCode()));


        newRMAType = repository.persist(newRMAType, true, true);

        object.setCreationDate(newRMAType.getCreationDate());
        object.setVersion(newRMAType.getVersion());

        return object;
    }

    /**
     * Search for RMA type objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of RMA type objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RMATypeSearchDTO> searchAllRMATypes(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RMATypeSearchDTO.SELECT_CODE);
        selectTokens.add(RMATypeSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(RMATypeSearchDTO.SELECT_COMMENT);
        selectTokens.add(RMATypeSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(RMATypeSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from RMAType a");

        return repository.search(searchObj, RMATypeSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing RMA type
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRMAType(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected RMA type
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final RMAType sourceObject = repository.findById(sourceObjectId);
        final RMAType targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}