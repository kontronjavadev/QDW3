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
public class RepairTaskBoundaryService {
    @Generated
    private final RepairTaskRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairTaskBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairTaskBoundaryService(RepairTaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair task objects
     * @param filter
     * @return a list of repair task objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairTaskListDTO> findRepairTasks(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairTaskListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairTask a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairTaskListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairTaskListDTO.class, selectTokens);
    }

    /**
     * Find repair task by its ID
     * @param code
     * @return the repair task object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairTaskDTO findRepairTaskById(String code) {
        // Find persistent object
        final RepairTask repairTask = repository.findById(code, true);

        final var dto = new RepairTaskDTO();
        dto.setCode(repairTask.getCode());
        dto.setShortText(repairTask.getShortText());
        dto.setComment(repairTask.getComment());
        dto.setCreationDate(repairTask.getCreationDate());
        dto.setLastUpdate(repairTask.getLastUpdate());
        dto.setVersion(repairTask.getVersion());

        if (repairTask.getMappedTo() != null) {
            dto.setMappedTo(new RepairTaskListDTO());
            dto.getMappedTo().setCode(repairTask.getMappedTo().getCode());
        }


        return dto;
    }

    /**
     * Update existing repair task object
     * @param object the repair task to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateRepairTask(RepairTaskUpdateDTO object) {
        // Find and attach object
        RepairTask repairTask = repository.findById(object.getCode(), true);

        repairTask.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        repairTask.setComment(object.getComment() != null ? object.getComment().trim() : null);
        repairTask.setVersion(object.getVersion());

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            repairTask.setMappedTo(null);
        else
            repairTask.setMappedTo(repository.getReference(RepairTask.class, object.getMappedTo().getCode()));


        repository.merge(repairTask, false);
    }

    /**
     * Find repair task by its ID
     * @param code
     * @return the repair task object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairTaskUpdateDTO getRepairTaskForUpdate(String code) {
        // Find persistent object
        final RepairTask repairTask = repository.findById(code, true);

        final var dto = new RepairTaskUpdateDTO();
        dto.setCode(repairTask.getCode());
        dto.setShortText(repairTask.getShortText());
        dto.setComment(repairTask.getComment());
        dto.setCreationDate(repairTask.getCreationDate());
        dto.setLastUpdate(repairTask.getLastUpdate());
        dto.setVersion(repairTask.getVersion());

        if (repairTask.getMappedTo() != null) {
            dto.setMappedTo(new RepairTaskListDTO());
            dto.getMappedTo().setCode(repairTask.getMappedTo().getCode());
        }


        return dto;
    }

    /**
     * Create new repair task
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted repair task object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairTaskCreateDTO createRepairTask(RepairTaskCreateDTO object) {
        // Create new object to be persisted
        var newRepairTask = new RepairTask();
        newRepairTask.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newRepairTask.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newRepairTask.setComment(object.getComment() != null ? object.getComment().trim() : null);

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            newRepairTask.setMappedTo(null);
        else
            newRepairTask.setMappedTo(repository.getReference(RepairTask.class, object.getMappedTo().getCode()));


        newRepairTask = repository.persist(newRepairTask, true, true);

        object.setCreationDate(newRepairTask.getCreationDate());
        object.setVersion(newRepairTask.getVersion());

        return object;
    }

    /**
     * Search for repair task objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of repair task objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairTaskSearchDTO> searchAllRepairTasks(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairTaskSearchDTO.SELECT_CODE);
        selectTokens.add(RepairTaskSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(RepairTaskSearchDTO.SELECT_COMMENT);
        selectTokens.add(RepairTaskSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(RepairTaskSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from RepairTask a");

        return repository.search(searchObj, RepairTaskSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing repair task
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRepairTask(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected repair task
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final RepairTask sourceObject = repository.findById(sourceObjectId);
        final RepairTask targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}