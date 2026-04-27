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
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class RepairStateBoundaryService {
    @Generated
    private final RepairStateRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairStateBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairStateBoundaryService(RepairStateRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair state objects
     * @param filter
     * @return a list of repair state objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairStateListDTO> findRepairStates(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairStateListDTO.SELECT_CODE);
        selectTokens.add(RepairStateListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairState a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairStateListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairStateListDTO.class, selectTokens);
    }

    /**
     * Find repair state by its ID
     * @param id
     * @return the repair state object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairStateListDTO findListRepairState(String id) {
        // Find persistent object
        final RepairState repairState = repository.findById(id, true);

        final var dto = new RepairStateListDTO();
        dto.setCode(repairState.getCode());
        dto.setName(repairState.getName());

        return dto;
    }

    /**
     * Find repair state by its ID
     * @param code
     * @return the repair state object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairStateDTO findRepairStateById(String code) {
        // Find persistent object
        final RepairState repairState = repository.findById(code, true);

        final var dto = new RepairStateDTO();
        dto.setName(repairState.getName());
        dto.setCode(repairState.getCode());
        dto.setShortText(repairState.getShortText());
        dto.setComment(repairState.getComment());
        dto.setCreationDate(repairState.getCreationDate());
        dto.setLastUpdate(repairState.getLastUpdate());
        dto.setVersion(repairState.getVersion());

        return dto;
    }

    /**
     * Update existing repair state object
     * @param object the repair state to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateRepairState(RepairStateUpdateDTO object) {
        // Find and attach object
        RepairState repairState = repository.findById(object.getCode(), true);

        repairState.setName(object.getName());
        repairState.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        repairState.setComment(object.getComment() != null ? object.getComment().trim() : null);
        repairState.setVersion(object.getVersion());

        repository.merge(repairState, true, false);
    }

    /**
     * Find repair state by its ID
     * @param code
     * @return the repair state object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairStateUpdateDTO getRepairStateForUpdate(String code) {
        // Find persistent object
        final RepairState repairState = repository.findById(code, true);

        final var dto = new RepairStateUpdateDTO();
        dto.setName(repairState.getName());
        dto.setCode(repairState.getCode());
        dto.setShortText(repairState.getShortText());
        dto.setComment(repairState.getComment());
        dto.setCreationDate(repairState.getCreationDate());
        dto.setLastUpdate(repairState.getLastUpdate());
        dto.setVersion(repairState.getVersion());

        return dto;
    }

    /**
     * Create new repair state
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted repair state object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairStateCreateDTO createRepairState(RepairStateCreateDTO object) {
        // Create new object to be persisted
        var newRepairState = new RepairState();
        newRepairState.setName(object.getName());
        newRepairState.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newRepairState.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newRepairState.setComment(object.getComment() != null ? object.getComment().trim() : null);

        newRepairState = repository.persist(newRepairState, true, true, true);

        object.setCreationDate(newRepairState.getCreationDate());
        object.setVersion(newRepairState.getVersion());

        return object;
    }

    /**
     * Search for repair state objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of repair state objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairStateSearchDTO> searchAllRepairStates(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairStateSearchDTO.SELECT_NAME);
        selectTokens.add(RepairStateSearchDTO.SELECT_CODE);
        selectTokens.add(RepairStateSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(RepairStateSearchDTO.SELECT_COMMENT);
        selectTokens.add(RepairStateSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(RepairStateSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from RepairState a");

        return repository.search(searchObj, RepairStateSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing repair state
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRepairState(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected repair state
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final RepairState sourceObject = repository.findById(sourceObjectId);
        final RepairState targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}