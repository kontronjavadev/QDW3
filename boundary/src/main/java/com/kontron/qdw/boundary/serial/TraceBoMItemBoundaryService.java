package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class TraceBoMItemBoundaryService {
    @Generated
    private final TraceBoMItemRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMItemBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public TraceBoMItemBoundaryService(TraceBoMItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Find trace BoM item by its ID
     * @param id
     * @return the trace BoM item object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public TraceBoMItemDTO findTraceBoMItemById(long id) {
        // Find persistent object
        final TraceBoMItem traceBoMItem = repository.findById(id, true);

        final var dto = new TraceBoMItemDTO();
        dto.setDateCode(traceBoMItem.getDateCode());
        dto.setInfoField1(traceBoMItem.getInfoField1());
        dto.setInfoField2(traceBoMItem.getInfoField2());
        dto.setInfoField3(traceBoMItem.getInfoField3());
        dto.setInfoField4(traceBoMItem.getInfoField4());
        dto.setManufacturerName(traceBoMItem.getManufacturerName());
        dto.setManufacturerRevision(traceBoMItem.getManufacturerRevision());
        dto.setOrderCode(traceBoMItem.getOrderCode());
        dto.setQuantity(traceBoMItem.getQuantity());
        dto.setId(traceBoMItem.getId());
        dto.setVersion(traceBoMItem.getVersion());
        dto.setCreationDate(traceBoMItem.getCreationDate());
        dto.setLastUpdate(traceBoMItem.getLastUpdate());
        dto.setMaterial(new MaterialListDTO());
        dto.getMaterial().setId(traceBoMItem.getMaterial().getId());
        dto.getMaterial().setMaterialNumber(traceBoMItem.getMaterial().getMaterialNumber());
        dto.setTraceBom(new TraceBoMListDTO());
        dto.getTraceBom().setId(traceBoMItem.getTraceBom().getId());

        return dto;
    }

    /**
     * Delete existing trace BoM item
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteTraceBoMItem(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected trace BoM item
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final TraceBoMItem sourceObject = repository.findById(sourceObjectId);
        final TraceBoMItem targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}