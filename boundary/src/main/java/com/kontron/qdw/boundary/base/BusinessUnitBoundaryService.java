package com.kontron.qdw.boundary.base;

import jakarta.validation.ConstraintViolationException;
import java.util.*;
import com.kontron.qdw.repository.base.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import com.kontron.qdw.dto.base.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class BusinessUnitBoundaryService {
    @Generated
    private final BusinessUnitRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public BusinessUnitBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public BusinessUnitBoundaryService(BusinessUnitRepository repository) {
        this.repository = repository;
    }

    /**
     * Update existing business unit object
     * @param object the business unit to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateBusinessUnit(BusinessUnitUpdateDTO object) {
        // Find and attach object
        BusinessUnit businessUnit = repository.findById(object.getCode(), true);

        businessUnit.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        businessUnit.setComment(object.getComment() != null ? object.getComment().trim() : null);
        businessUnit.setActive(object.isActive());
        businessUnit.setVersion(object.getVersion());

        repository.merge(businessUnit, false);
    }

    /**
     * Find business unit by its ID
     * @param code
     * @return the business unit object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BusinessUnitUpdateDTO getBusinessUnitForUpdate(String code) {
        // Find persistent object
        final BusinessUnit businessUnit = repository.findById(code, true);

        final var dto = new BusinessUnitUpdateDTO();
        dto.setCode(businessUnit.getCode());
        dto.setShortText(businessUnit.getShortText());
        dto.setComment(businessUnit.getComment());
        dto.setActive(businessUnit.isActive());
        dto.setCreationDate(businessUnit.getCreationDate());
        dto.setLastUpdate(businessUnit.getLastUpdate());
        dto.setVersion(businessUnit.getVersion());

        return dto;
    }

    /**
     * Search for business unit objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of business unit objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<BusinessUnitSearchDTO> searchAllBusinessUnits(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(BusinessUnitSearchDTO.SELECT_CODE);
        selectTokens.add(BusinessUnitSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(BusinessUnitSearchDTO.SELECT_COMMENT);
        selectTokens.add(BusinessUnitSearchDTO.SELECT_ACTIVE);
        selectTokens.add(BusinessUnitSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(BusinessUnitSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from BusinessUnit a");

        return repository.search(searchObj, BusinessUnitSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing business unit
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteBusinessUnit(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected business unit
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final BusinessUnit sourceObject = repository.findById(sourceObjectId);
        final BusinessUnit targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

    /**
     * Create new business unit
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted business unit object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BusinessUnitCreateDTO createBusinessUnit(BusinessUnitCreateDTO object) {
        // Create new object to be persisted
        var newBusinessUnit = new BusinessUnit();
        newBusinessUnit.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newBusinessUnit.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newBusinessUnit.setActive(object.isActive());

        newBusinessUnit = repository.persist(newBusinessUnit, true, true);

        object.setCode(newBusinessUnit.getCode());
        object.setCreationDate(newBusinessUnit.getCreationDate());
        object.setVersion(newBusinessUnit.getVersion());

        return object;
    }

}