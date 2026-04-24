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
public class RepairServiceBoundaryService {
    @Generated
    private final RepairServiceRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairServiceBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairServiceBoundaryService(RepairServiceRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair service objects
     * @param filter
     * @return a list of repair service objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairServiceListDTO> findRepairServices(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairServiceListDTO.SELECT_CODE);
        selectTokens.add(RepairServiceListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairService a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairServiceListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairServiceListDTO.class, selectTokens);
    }

    /**
     * Find repair service by its ID
     * @param id
     * @return the repair service object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairServiceListDTO findListRepairService(String id) {
        // Find persistent object
        final RepairService repairService = repository.findById(id, true);

        final var dto = new RepairServiceListDTO();
        dto.setCode(repairService.getCode());
        dto.setName(repairService.getName());

        return dto;
    }

    /**
     * Create new repair service
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted repair service object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairServiceCreateDTO createRepairService(RepairServiceCreateDTO object) {
        // Create new object to be persisted
        var newRepairService = new RepairService();
        newRepairService.setName(object.getName());
        newRepairService.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newRepairService.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newRepairService.setComment(object.getComment() != null ? object.getComment().trim() : null);

        newRepairService = repository.persist(newRepairService, true, true, true);

        object.setCreationDate(newRepairService.getCreationDate());
        object.setVersion(newRepairService.getVersion());

        return object;
    }

    /**
     * Update existing repair service object
     * @param object the repair service to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateRepairService(RepairServiceUpdateDTO object) {
        // Find and attach object
        RepairService repairService = repository.findById(object.getCode(), true);

        repairService.setName(object.getName());
        repairService.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        repairService.setComment(object.getComment() != null ? object.getComment().trim() : null);
        repairService.setVersion(object.getVersion());

        repository.merge(repairService, true, false);
    }

    /**
     * Find repair service by its ID
     * @param code
     * @return the repair service object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairServiceUpdateDTO getRepairServiceForUpdate(String code) {
        // Find persistent object
        final RepairService repairService = repository.findById(code, true);

        final var dto = new RepairServiceUpdateDTO();
        dto.setName(repairService.getName());
        dto.setCode(repairService.getCode());
        dto.setShortText(repairService.getShortText());
        dto.setComment(repairService.getComment());
        dto.setCreationDate(repairService.getCreationDate());
        dto.setLastUpdate(repairService.getLastUpdate());
        dto.setVersion(repairService.getVersion());

        return dto;
    }

    /**
     * Find repair service by its ID
     * @param code
     * @return the repair service object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairServiceDTO findRepairServiceById(String code) {
        // Find persistent object
        final RepairService repairService = repository.findById(code, true);

        final var dto = new RepairServiceDTO();
        dto.setName(repairService.getName());
        dto.setCode(repairService.getCode());
        dto.setShortText(repairService.getShortText());
        dto.setComment(repairService.getComment());
        dto.setCreationDate(repairService.getCreationDate());
        dto.setLastUpdate(repairService.getLastUpdate());
        dto.setVersion(repairService.getVersion());

        return dto;
    }

    /**
     * Search for repair service objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of repair service objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairServiceSearchDTO> searchAllRepairServices(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairServiceSearchDTO.SELECT_NAME);
        selectTokens.add(RepairServiceSearchDTO.SELECT_CODE);
        selectTokens.add(RepairServiceSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(RepairServiceSearchDTO.SELECT_COMMENT);
        selectTokens.add(RepairServiceSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(RepairServiceSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from RepairService a");

        return repository.search(searchObj, RepairServiceSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing repair service
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRepairService(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected repair service
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
        final RepairService sourceObject = repository.findById(sourceObjectId);
        final RepairService targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}