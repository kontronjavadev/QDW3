package com.kontron.qdw.boundary.material;

import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.material.*;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class BoMItemBoundaryService {
    @Generated
    private final BoMItemRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public BoMItemBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public BoMItemBoundaryService(BoMItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Find bom item by its ID
     * @param id
     * @return the bom item object
     */
    @Customized
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BoMItemUpdateDTO getBoMItemForUpdate(long id) {
        // Find persistent object
        final BoMItem boMItem = repository.findById(id, true);

        final var dto = new BoMItemUpdateDTO();
        dto.setQuantity(boMItem.getQuantity());
        dto.setLabel(boMItem.getLabel());
        dto.setPosition(boMItem.getPosition());
        dto.setId(boMItem.getId());
        dto.setVersion(boMItem.getVersion());
        dto.setCreationDate(boMItem.getCreationDate());
        dto.setLastUpdate(boMItem.getLastUpdate());

        if (boMItem.getMaterial() != null) {
            dto.setMaterial(new MaterialListDTO());
            dto.getMaterial().setId(boMItem.getMaterial().getId());
            dto.getMaterial().setMaterialNumber(boMItem.getMaterial().getMaterialNumber());
        }

        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(boMItem.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(boMItem.getMaterialRevision().getRevisionNumber());
        dto.getMaterialRevision().setPlantCode(boMItem.getMaterialRevision().getPlant().getCode());
        dto.setMaterialRevisionMaterial(new MaterialListDTO());
        dto.getMaterialRevisionMaterial().setId(boMItem.getMaterialRevision().getMaterial().getId());
        dto.getMaterialRevisionMaterial().setMaterialNumber(boMItem.getMaterialRevision().getMaterial().getMaterialNumber());

        return dto;
    }

    /**
     * Find bom item by its ID
     * @param id
     * @return the bom item object
     */
    @Customized
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BoMItemDTO findBoMItemById(long id) {
        // Find persistent object
        final BoMItem boMItem = repository.findById(id, true);

        final var dto = new BoMItemDTO();
        dto.setQuantity(boMItem.getQuantity());
        dto.setLabel(boMItem.getLabel());
        dto.setPosition(boMItem.getPosition());
        dto.setId(boMItem.getId());
        dto.setVersion(boMItem.getVersion());
        dto.setCreationDate(boMItem.getCreationDate());
        dto.setLastUpdate(boMItem.getLastUpdate());

        if (boMItem.getMaterial() != null) {
            dto.setMaterial(new MaterialListDTO());
            dto.getMaterial().setId(boMItem.getMaterial().getId());
            dto.getMaterial().setMaterialNumber(boMItem.getMaterial().getMaterialNumber());
        }

        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(boMItem.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(boMItem.getMaterialRevision().getRevisionNumber());
        dto.getMaterialRevision().setPlantCode(boMItem.getMaterialRevision().getPlant().getCode());
        dto.setMaterialRevisionMaterial(new MaterialListDTO());
        dto.getMaterialRevisionMaterial().setId(boMItem.getMaterialRevision().getMaterial().getId());
        dto.getMaterialRevisionMaterial().setMaterialNumber(boMItem.getMaterialRevision().getMaterial().getMaterialNumber());

        return dto;
    }

    /**
     * Update existing bom item object
     * @param object the bom item to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateBoMItem(BoMItemUpdateDTO object) {
        // Find and attach object
        BoMItem boMItem = repository.findById(object.getId(), true);

        boMItem.setQuantity(object.getQuantity());
        boMItem.setLabel(object.getLabel() != null ? object.getLabel().trim() : null);
        boMItem.setPosition(object.getPosition() != null ? object.getPosition().trim() : null);
        boMItem.setVersion(object.getVersion());

        if (object.getMaterial() == null || object.getMaterial().getId() == Long.MIN_VALUE)
            boMItem.setMaterial(null);
        else
            boMItem.setMaterial(repository.getReference(Material.class, object.getMaterial().getId()));

        boMItem.setMaterialRevision(repository.getReference(MaterialRevision.class, object.getMaterialRevision().getId()));
        boMItem.getMaterialRevision().setMaterial(repository.getReference(Material.class, object.getMaterialRevisionMaterial().getId()));

        repository.merge(boMItem, false);
    }

    /**
     * Delete existing bom item
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteBoMItem(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected bom item
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final BoMItem sourceObject = repository.findById(sourceObjectId);
        final BoMItem targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Create new bom item
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted bom item object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BoMItemCreateDTO createBoMItem(BoMItemCreateDTO object) {
        // Create new object to be persisted
        var newBoMItem = new BoMItem();
        newBoMItem.setQuantity(object.getQuantity());
        newBoMItem.setLabel(object.getLabel() != null ? object.getLabel().trim() : null);
        newBoMItem.setPosition(object.getPosition() != null ? object.getPosition().trim() : null);

        if (object.getMaterial() == null || object.getMaterial().getId() == Long.MIN_VALUE)
            newBoMItem.setMaterial(null);
        else
            newBoMItem.setMaterial(repository.getReference(Material.class, object.getMaterial().getId()));

        newBoMItem.setMaterialRevision(repository.getReference(MaterialRevision.class, object.getMaterialRevision().getId()));

        newBoMItem = repository.persist(newBoMItem, true, true);

        object.setId(newBoMItem.getId());
        object.setVersion(newBoMItem.getVersion());
        object.setCreationDate(newBoMItem.getCreationDate());

        return object;
    }

}