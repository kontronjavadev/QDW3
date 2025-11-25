package com.kontron.qdw.boundary.base;

import com.kontron.qdw.repository.base.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class VerticalSectorBoundaryService {
    @Generated
    private final VerticalSectorRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public VerticalSectorBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public VerticalSectorBoundaryService(VerticalSectorRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for vertical sector objects
     * @param filter
     * @return a list of vertical sector objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<VerticalSectorListDTO> findVerticalSectors(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(VerticalSectorListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from VerticalSector a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(VerticalSectorListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, VerticalSectorListDTO.class, selectTokens);
    }

    /**
     * Create new vertical sector
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted vertical sector object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public VerticalSectorCreateDTO createVerticalSector(VerticalSectorCreateDTO object) {
        // Create new object to be persisted
        var newVerticalSector = new VerticalSector();
        newVerticalSector.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newVerticalSector.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newVerticalSector.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newVerticalSector.setActive(object.isActive());
        newVerticalSector.setBusinessUnit(repository.getReference(BusinessUnit.class, object.getBusinessUnit().getCode()));

        newVerticalSector = repository.persist(newVerticalSector, true, true);

        object.setCreationDate(newVerticalSector.getCreationDate());
        object.setVersion(newVerticalSector.getVersion());

        return object;
    }

    /**
     * Update existing vertical sector object
     * @param object the vertical sector to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateVerticalSector(VerticalSectorUpdateDTO object) {
        // Find and attach object
        VerticalSector verticalSector = repository.findById(object.getCode(), true);

        verticalSector.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        verticalSector.setComment(object.getComment() != null ? object.getComment().trim() : null);
        verticalSector.setActive(object.isActive());
        verticalSector.setVersion(object.getVersion());
        verticalSector.setBusinessUnit(repository.getReference(BusinessUnit.class, object.getBusinessUnit().getCode()));

        repository.merge(verticalSector, false);
    }

    /**
     * Find vertical sector by its ID
     * @param code
     * @return the vertical sector object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public VerticalSectorUpdateDTO getVerticalSectorForUpdate(String code) {
        // Find persistent object
        final VerticalSector verticalSector = repository.findById(code, true);

        final var dto = new VerticalSectorUpdateDTO();
        dto.setCode(verticalSector.getCode());
        dto.setShortText(verticalSector.getShortText());
        dto.setComment(verticalSector.getComment());
        dto.setActive(verticalSector.isActive());
        dto.setCreationDate(verticalSector.getCreationDate());
        dto.setLastUpdate(verticalSector.getLastUpdate());
        dto.setVersion(verticalSector.getVersion());
        dto.setBusinessUnit(new BusinessUnitListDTO());
        dto.getBusinessUnit().setCode(verticalSector.getBusinessUnit().getCode());

        return dto;
    }

    /**
     * Search for vertical sector objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of vertical sector objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<VerticalSectorSearchDTO> searchAllVerticalSectors(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(VerticalSectorSearchDTO.SELECT_CODE);
        selectTokens.add(VerticalSectorSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(VerticalSectorSearchDTO.SELECT_COMMENT);
        selectTokens.add(VerticalSectorSearchDTO.SELECT_ACTIVE);
        selectTokens.add(VerticalSectorSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(VerticalSectorSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(VerticalSectorSearchDTO.SELECT_BUSINESSUNITCODE);

        searchObj.setFromClause("from VerticalSector a join a.businessUnit b");

        return repository.search(searchObj, VerticalSectorSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing vertical sector
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteVerticalSector(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected vertical sector
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final VerticalSector sourceObject = repository.findById(sourceObjectId);
        final VerticalSector targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}