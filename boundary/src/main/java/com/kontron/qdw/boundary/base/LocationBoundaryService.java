package com.kontron.qdw.boundary.base;

import com.kontron.qdw.repository.base.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import jakarta.validation.ConstraintViolationException;
import java.util.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class LocationBoundaryService {
    @Generated
    private final LocationRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public LocationBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public LocationBoundaryService(LocationRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new location
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted location object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public LocationCreateDTO createLocation(LocationCreateDTO object) {
        // Create new object to be persisted
        var newLocation = new Location();
        newLocation.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newLocation.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newLocation.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newLocation.setActive(object.isActive());

        newLocation = repository.persist(newLocation, true, true);

        object.setCreationDate(newLocation.getCreationDate());
        object.setVersion(newLocation.getVersion());

        return object;
    }

    /**
     * Update existing location object
     * @param object the location to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateLocation(LocationUpdateDTO object) {
        // Find and attach object
        Location location = repository.findById(object.getCode(), true);

        location.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        location.setComment(object.getComment() != null ? object.getComment().trim() : null);
        location.setActive(object.isActive());
        location.setVersion(object.getVersion());

        repository.merge(location, false);
    }

    /**
     * Find location by its ID
     * @param code
     * @return the location object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public LocationUpdateDTO getLocationForUpdate(String code) {
        // Find persistent object
        final Location location = repository.findById(code, true);

        final var dto = new LocationUpdateDTO();
        dto.setCode(location.getCode());
        dto.setShortText(location.getShortText());
        dto.setComment(location.getComment());
        dto.setActive(location.isActive());
        dto.setCreationDate(location.getCreationDate());
        dto.setLastUpdate(location.getLastUpdate());
        dto.setVersion(location.getVersion());

        return dto;
    }

    /**
     * Search for location objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of location objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<LocationSearchDTO> searchAllLocations(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(LocationSearchDTO.SELECT_CODE);
        selectTokens.add(LocationSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(LocationSearchDTO.SELECT_COMMENT);
        selectTokens.add(LocationSearchDTO.SELECT_ACTIVE);
        selectTokens.add(LocationSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(LocationSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from Location a");

        return repository.search(searchObj, LocationSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing location
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteLocation(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected location
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final Location sourceObject = repository.findById(sourceObjectId);
        final Location targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

    /**
     * Search for location objects
     * @param filter
     * @return a list of location objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<LocationListDTO> findLocations(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(LocationListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from Location a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(LocationListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, LocationListDTO.class, selectTokens);
    }

}