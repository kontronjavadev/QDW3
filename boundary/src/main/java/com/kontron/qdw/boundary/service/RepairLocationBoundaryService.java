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
public class RepairLocationBoundaryService {
    @Generated
    private final RepairLocationRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairLocationBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairLocationBoundaryService(RepairLocationRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair location objects
     * @param filter
     * @return a list of repair location objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairLocationListDTO> findRepairLocations(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairLocationListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairLocation a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairLocationListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairLocationListDTO.class, selectTokens);
    }

    /**
     * Find repair location by its ID
     * @param code
     * @return the repair location object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairLocationDTO findRepairLocationById(String code) {
        // Find persistent object
        final RepairLocation repairLocation = repository.findById(code, true);

        final var dto = new RepairLocationDTO();
        dto.setCode(repairLocation.getCode());
        dto.setShortText(repairLocation.getShortText());
        dto.setComment(repairLocation.getComment());
        dto.setCreationDate(repairLocation.getCreationDate());
        dto.setLastUpdate(repairLocation.getLastUpdate());
        dto.setVersion(repairLocation.getVersion());

        return dto;
    }

    /**
     * Create new repair location
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted repair location object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairLocationCreateDTO createRepairLocation(RepairLocationCreateDTO object) {
        // Create new object to be persisted
        var newRepairLocation = new RepairLocation();
        newRepairLocation.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newRepairLocation.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newRepairLocation.setComment(object.getComment() != null ? object.getComment().trim() : null);

        newRepairLocation = repository.persist(newRepairLocation, true, true);

        object.setCreationDate(newRepairLocation.getCreationDate());
        object.setVersion(newRepairLocation.getVersion());

        return object;
    }

    /**
     * Update existing repair location object
     * @param object the repair location to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateRepairLocation(RepairLocationUpdateDTO object) {
        // Find and attach object
        RepairLocation repairLocation = repository.findById(object.getCode(), true);

        repairLocation.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        repairLocation.setComment(object.getComment() != null ? object.getComment().trim() : null);
        repairLocation.setVersion(object.getVersion());

        repository.merge(repairLocation, false);
    }

    /**
     * Find repair location by its ID
     * @param code
     * @return the repair location object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairLocationUpdateDTO getRepairLocationForUpdate(String code) {
        // Find persistent object
        final RepairLocation repairLocation = repository.findById(code, true);

        final var dto = new RepairLocationUpdateDTO();
        dto.setCode(repairLocation.getCode());
        dto.setShortText(repairLocation.getShortText());
        dto.setComment(repairLocation.getComment());
        dto.setCreationDate(repairLocation.getCreationDate());
        dto.setLastUpdate(repairLocation.getLastUpdate());
        dto.setVersion(repairLocation.getVersion());

        return dto;
    }

    /**
     * Delete existing repair location
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRepairLocation(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected repair location
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final RepairLocation sourceObject = repository.findById(sourceObjectId);
        final RepairLocation targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

    /**
     * Search for repair location objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of repair location objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairLocationSearchDTO> searchAllRepairLocations(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairLocationSearchDTO.SELECT_CODE);
        selectTokens.add(RepairLocationSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(RepairLocationSearchDTO.SELECT_COMMENT);
        selectTokens.add(RepairLocationSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(RepairLocationSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from RepairLocation a");

        return repository.search(searchObj, RepairLocationSearchDTO.class, selectTokens);
    }

}