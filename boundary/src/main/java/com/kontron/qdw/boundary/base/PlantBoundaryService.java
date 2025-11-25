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
public class PlantBoundaryService {
    @Generated
    private final PlantRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public PlantBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public PlantBoundaryService(PlantRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new plant
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted plant object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PlantCreateDTO createPlant(PlantCreateDTO object) {
        // Create new object to be persisted
        var newPlant = new Plant();
        newPlant.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newPlant.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newPlant.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newPlant.setActive(object.isActive());

        newPlant = repository.persist(newPlant, true, true);

        object.setCreationDate(newPlant.getCreationDate());
        object.setVersion(newPlant.getVersion());

        return object;
    }

    /**
     * Update existing plant object
     * @param object the plant to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updatePlant(PlantUpdateDTO object) {
        // Find and attach object
        Plant plant = repository.findById(object.getCode(), true);

        plant.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        plant.setComment(object.getComment() != null ? object.getComment().trim() : null);
        plant.setActive(object.isActive());
        plant.setVersion(object.getVersion());

        repository.merge(plant, false);
    }

    /**
     * Find plant by its ID
     * @param code
     * @return the plant object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PlantUpdateDTO getPlantForUpdate(String code) {
        // Find persistent object
        final Plant plant = repository.findById(code, true);

        final var dto = new PlantUpdateDTO();
        dto.setCode(plant.getCode());
        dto.setShortText(plant.getShortText());
        dto.setComment(plant.getComment());
        dto.setActive(plant.isActive());
        dto.setCreationDate(plant.getCreationDate());
        dto.setLastUpdate(plant.getLastUpdate());
        dto.setVersion(plant.getVersion());

        return dto;
    }

    /**
     * Search for plant objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of plant objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<PlantSearchDTO> searchAllPlants(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(PlantSearchDTO.SELECT_CODE);
        selectTokens.add(PlantSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(PlantSearchDTO.SELECT_COMMENT);
        selectTokens.add(PlantSearchDTO.SELECT_ACTIVE);
        selectTokens.add(PlantSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(PlantSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from Plant a");

        return repository.search(searchObj, PlantSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing plant
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deletePlant(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected plant
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final Plant sourceObject = repository.findById(sourceObjectId);
        final Plant targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}