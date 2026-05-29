package com.kontron.qdw.boundary.service;

import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.repository.service.*;
import com.kontron.qdw.domain.service.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.service.*;

@Stateless
public class X2MessageBoundaryService {
    @Generated
    private final X2MessageRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public X2MessageBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public X2MessageBoundaryService(X2MessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Delete existing X2 message
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteX2Message(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected X2 message
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final X2Message sourceObject = repository.findById(sourceObjectId);
        final X2Message targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Find X2 message by its ID
     * @param id
     * @return the X2 message object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public X2MessageDTO findX2MessageById(long id) {
        // Find persistent object
        final X2Message x2Message = repository.findById(id, true);

        final var dto = new X2MessageDTO();
        dto.setAnalysisText(x2Message.getAnalysisText());
        dto.setCauseText(x2Message.getCauseText());
        dto.setCustomerReport(x2Message.getCustomerReport());
        dto.setDefectComponent(x2Message.getDefectComponent());
        dto.setDesignator(x2Message.getDesignator());
        dto.setWorkCenter(x2Message.getWorkCenter());
        dto.setId(x2Message.getId());
        dto.setVersion(x2Message.getVersion());
        dto.setCreationDate(x2Message.getCreationDate());
        dto.setLastUpdate(x2Message.getLastUpdate());

        if (x2Message.getDefectMaterial() != null) {
            dto.setDefectMaterial(new MaterialListDTO());
            dto.getDefectMaterial().setId(x2Message.getDefectMaterial().getId());
            dto.getDefectMaterial().setMaterialNumber(x2Message.getDefectMaterial().getMaterialNumber());
        }


        if (x2Message.getFaultAnalysis() != null) {
            dto.setFaultAnalysis(new FaultAnalysisListDTO());
            dto.getFaultAnalysis().setCode(x2Message.getFaultAnalysis().getCode());
        }

        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(x2Message.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(x2Message.getMaterialRevision().getRevisionNumber());
        if (x2Message.getMaterialRevision().getPlant() != null) {
            dto.getMaterialRevision().setPlantCode(x2Message.getMaterialRevision().getPlant().getCode());
        }

        if (x2Message.getRepairErrorCode() != null) {
            dto.setRepairErrorCode(new RepairErrorCodeListDTO());
            dto.getRepairErrorCode().setCode(x2Message.getRepairErrorCode().getCode());
            dto.getRepairErrorCode().setName(x2Message.getRepairErrorCode().getName());
        }

        dto.setRepairState(new RepairStateListDTO());
        dto.getRepairState().setCode(x2Message.getRepairState().getCode());
        dto.getRepairState().setName(x2Message.getRepairState().getName());

        if (x2Message.getRepairTask() != null) {
            dto.setRepairTask(new RepairTaskListDTO());
            dto.getRepairTask().setCode(x2Message.getRepairTask().getCode());
        }

        dto.setSerialObject(new SerialObjectListDTO());
        dto.getSerialObject().setId(x2Message.getSerialObject().getId());
        dto.getSerialObject().setSerialNumber(x2Message.getSerialObject().getSerialNumber());
        dto.setServiceMessage(new ServiceMessageListDTO());
        dto.getServiceMessage().setId(x2Message.getServiceMessage().getId());
        dto.setMatrevMaterial(new MaterialListDTO());
        dto.getMatrevMaterial().setId(x2Message.getMaterialRevision().getMaterial().getId());
        dto.getMatrevMaterial().setMaterialNumber(x2Message.getMaterialRevision().getMaterial().getMaterialNumber());
        dto.setMaterialSapNumber(x2Message.getMaterialRevision().getMaterial().getSapNumber());

        return dto;
    }

}
