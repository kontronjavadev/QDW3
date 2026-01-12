package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class IllegalTraceBoMItemBoundaryService {
    @Generated
    private final IllegalTraceBoMItemRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public IllegalTraceBoMItemBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public IllegalTraceBoMItemBoundaryService(IllegalTraceBoMItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Find illegal trace BoM item by its ID
     * @param id
     * @return the illegal trace BoM item object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public IllegalTraceBoMItemDTO findIllegalTraceBoMItemById(long id) {
        // Find persistent object
        final IllegalTraceBoMItem illegalTraceBoMItem = repository.findById(id, true);

        final var dto = new IllegalTraceBoMItemDTO();
        dto.setDateCode(illegalTraceBoMItem.getDateCode());
        dto.setManufacturer(illegalTraceBoMItem.getManufacturer());
        dto.setManufacturerRevision(illegalTraceBoMItem.getManufacturerRevision());
        dto.setMaterialNumber(illegalTraceBoMItem.getMaterialNumber());
        dto.setOrderCode(illegalTraceBoMItem.getOrderCode());
        dto.setId(illegalTraceBoMItem.getId());
        dto.setVersion(illegalTraceBoMItem.getVersion());
        dto.setCreationDate(illegalTraceBoMItem.getCreationDate());
        dto.setLastUpdate(illegalTraceBoMItem.getLastUpdate());
        dto.setTraceBom(new TraceBoMListDTO());
        dto.getTraceBom().setId(illegalTraceBoMItem.getTraceBom().getId());

        return dto;
    }

    /**
     * Delete existing illegal trace BoM item
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteIllegalTraceBoMItem(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected illegal trace BoM item
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final IllegalTraceBoMItem sourceObject = repository.findById(sourceObjectId);
        final IllegalTraceBoMItem targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}