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
public class MovementTypeBoundaryService {
    @Generated
    private final MovementTypeRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MovementTypeBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MovementTypeBoundaryService(MovementTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new movement type
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted movement type object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MovementTypeCreateDTO createMovementType(MovementTypeCreateDTO object) {
        // Create new object to be persisted
        var newMovementType = new MovementType();
        newMovementType.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newMovementType.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newMovementType.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newMovementType.setActive(object.isActive());

        newMovementType = repository.persist(newMovementType, true, true);

        object.setCreationDate(newMovementType.getCreationDate());
        object.setVersion(newMovementType.getVersion());

        return object;
    }

    /**
     * Update existing movement type object
     * @param object the movement type to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateMovementType(MovementTypeUpdateDTO object) {
        // Find and attach object
        MovementType movementType = repository.findById(object.getCode(), true);

        movementType.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        movementType.setComment(object.getComment() != null ? object.getComment().trim() : null);
        movementType.setActive(object.isActive());
        movementType.setVersion(object.getVersion());

        repository.merge(movementType, false);
    }

    /**
     * Find movement type by its ID
     * @param code
     * @return the movement type object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MovementTypeUpdateDTO getMovementTypeForUpdate(String code) {
        // Find persistent object
        final MovementType movementType = repository.findById(code, true);

        final var dto = new MovementTypeUpdateDTO();
        dto.setCode(movementType.getCode());
        dto.setShortText(movementType.getShortText());
        dto.setComment(movementType.getComment());
        dto.setActive(movementType.isActive());
        dto.setCreationDate(movementType.getCreationDate());
        dto.setLastUpdate(movementType.getLastUpdate());
        dto.setVersion(movementType.getVersion());

        return dto;
    }

    /**
     * Search for movement type objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of movement type objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MovementTypeSearchDTO> searchAllMovementTypes(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MovementTypeSearchDTO.SELECT_CODE);
        selectTokens.add(MovementTypeSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(MovementTypeSearchDTO.SELECT_COMMENT);
        selectTokens.add(MovementTypeSearchDTO.SELECT_ACTIVE);
        selectTokens.add(MovementTypeSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(MovementTypeSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from MovementType a");

        return repository.search(searchObj, MovementTypeSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing movement type
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMovementType(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected movement type
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final MovementType sourceObject = repository.findById(sourceObjectId);
        final MovementType targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}