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
public class RepairErrorCodeBoundaryService {
    @Generated
    private final RepairErrorCodeRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairErrorCodeBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairErrorCodeBoundaryService(RepairErrorCodeRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair error code objects
     * @param filter
     * @return a list of repair error code objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairErrorCodeListDTO> findRepairErrorCodes(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairErrorCodeListDTO.SELECT_CODE);
        selectTokens.add(RepairErrorCodeListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairErrorCode a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairErrorCodeListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairErrorCodeListDTO.class, selectTokens);
    }

    /**
     * Find repair error code by its ID
     * @param id
     * @return the repair error code object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairErrorCodeListDTO findListRepairErrorCode(String id) {
        // Find persistent object
        final RepairErrorCode repairErrorCode = repository.findById(id, true);

        final var dto = new RepairErrorCodeListDTO();
        dto.setCode(repairErrorCode.getCode());
        dto.setName(repairErrorCode.getName());

        return dto;
    }

    /**
     * Find repair error code by its ID
     * @param code
     * @return the repair error code object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairErrorCodeDTO findRepairErrorCodeById(String code) {
        // Find persistent object
        final RepairErrorCode repairErrorCode = repository.findById(code, true);

        final var dto = new RepairErrorCodeDTO();
        dto.setName(repairErrorCode.getName());
        dto.setGroupName(repairErrorCode.getGroupName());
        dto.setCode(repairErrorCode.getCode());
        dto.setShortText(repairErrorCode.getShortText());
        dto.setComment(repairErrorCode.getComment());
        dto.setActive(repairErrorCode.isActive());
        dto.setCreationDate(repairErrorCode.getCreationDate());
        dto.setLastUpdate(repairErrorCode.getLastUpdate());
        dto.setVersion(repairErrorCode.getVersion());

        if (repairErrorCode.getMappedTo() != null) {
            dto.setMappedTo(new RepairErrorCodeListDTO());
            dto.getMappedTo().setCode(repairErrorCode.getMappedTo().getCode());
            dto.getMappedTo().setName(repairErrorCode.getMappedTo().getName());
        }


        return dto;
    }

    /**
     * Update existing repair error code object
     * @param object the repair error code to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateRepairErrorCode(RepairErrorCodeUpdateDTO object) {
        // Find and attach object
        RepairErrorCode repairErrorCode = repository.findById(object.getCode(), true);

        repairErrorCode.setName(object.getName());
        repairErrorCode.setGroupName(object.getGroupName());
        repairErrorCode.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        repairErrorCode.setComment(object.getComment() != null ? object.getComment().trim() : null);
        repairErrorCode.setActive(object.isActive());
        repairErrorCode.setVersion(object.getVersion());

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            repairErrorCode.setMappedTo(null);
        else
            repairErrorCode.setMappedTo(repository.getReference(RepairErrorCode.class, object.getMappedTo().getCode()));


        repository.merge(repairErrorCode, true, false);
    }

    /**
     * Find repair error code by its ID
     * @param code
     * @return the repair error code object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairErrorCodeUpdateDTO getRepairErrorCodeForUpdate(String code) {
        // Find persistent object
        final RepairErrorCode repairErrorCode = repository.findById(code, true);

        final var dto = new RepairErrorCodeUpdateDTO();
        dto.setName(repairErrorCode.getName());
        dto.setGroupName(repairErrorCode.getGroupName());
        dto.setCode(repairErrorCode.getCode());
        dto.setShortText(repairErrorCode.getShortText());
        dto.setComment(repairErrorCode.getComment());
        dto.setActive(repairErrorCode.isActive());
        dto.setCreationDate(repairErrorCode.getCreationDate());
        dto.setLastUpdate(repairErrorCode.getLastUpdate());
        dto.setVersion(repairErrorCode.getVersion());

        if (repairErrorCode.getMappedTo() != null) {
            dto.setMappedTo(new RepairErrorCodeListDTO());
            dto.getMappedTo().setCode(repairErrorCode.getMappedTo().getCode());
            dto.getMappedTo().setName(repairErrorCode.getMappedTo().getName());
        }


        return dto;
    }

    /**
     * Create new repair error code
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted repair error code object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairErrorCodeCreateDTO createRepairErrorCode(RepairErrorCodeCreateDTO object) {
        // Create new object to be persisted
        var newRepairErrorCode = new RepairErrorCode();
        newRepairErrorCode.setName(object.getName());
        newRepairErrorCode.setGroupName(object.getGroupName());
        newRepairErrorCode.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newRepairErrorCode.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newRepairErrorCode.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newRepairErrorCode.setActive(object.isActive());

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            newRepairErrorCode.setMappedTo(null);
        else
            newRepairErrorCode.setMappedTo(repository.getReference(RepairErrorCode.class, object.getMappedTo().getCode()));


        newRepairErrorCode = repository.persist(newRepairErrorCode, true, true, true);

        object.setCreationDate(newRepairErrorCode.getCreationDate());
        object.setVersion(newRepairErrorCode.getVersion());

        return object;
    }

    /**
     * Search for repair error code objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of repair error code objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairErrorCodeSearchDTO> searchAllRepairErrorCodes(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_NAME);
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_GROUPNAME);
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_CODE);
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_COMMENT);
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_ACTIVE);
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(RepairErrorCodeSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from RepairErrorCode a");

        return repository.search(searchObj, RepairErrorCodeSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing repair error code
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRepairErrorCode(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected repair error code
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
        final RepairErrorCode sourceObject = repository.findById(sourceObjectId);
        final RepairErrorCode targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}