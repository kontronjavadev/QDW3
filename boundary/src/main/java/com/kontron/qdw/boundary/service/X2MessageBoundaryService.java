package com.kontron.qdw.boundary.service;

import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import com.kontron.qdw.dto.service.*;
import jakarta.validation.ConstraintViolationException;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.repository.service.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

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

    /**
     * Search for X2 message objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of X2 message objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<X2MessageSearchDTO> searchAllX2Messages(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(X2MessageSearchDTO.SELECT_ANALYSISTEXT);
        selectTokens.add(X2MessageSearchDTO.SELECT_CAUSETEXT);
        selectTokens.add(X2MessageSearchDTO.SELECT_CUSTOMERREPORT);
        selectTokens.add(X2MessageSearchDTO.SELECT_DEFECTCOMPONENT);
        selectTokens.add(X2MessageSearchDTO.SELECT_DESIGNATOR);
        selectTokens.add(X2MessageSearchDTO.SELECT_WORKCENTER);
        selectTokens.add(X2MessageSearchDTO.SELECT_ID);
        selectTokens.add(X2MessageSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(X2MessageSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(X2MessageSearchDTO.SELECT_REPAIRSTATECODE);
        selectTokens.add(X2MessageSearchDTO.SELECT_MATREVID);
        selectTokens.add(X2MessageSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(X2MessageSearchDTO.SELECT_SERVICEMESSAGEID);
        selectTokens.add(X2MessageSearchDTO.SELECT_SERVMESSSERVORDCODE);
        selectTokens.add(X2MessageSearchDTO.SELECT_SERVMESSSEROBJSERIALNUMBER);
        selectTokens.add(X2MessageSearchDTO.SELECT_MATREVMATID);
        selectTokens.add(X2MessageSearchDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(X2MessageSearchDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(X2MessageSearchDTO.SELECT_MATREVREVISIONNUMBER);
        selectTokens.add(X2MessageSearchDTO.SELECT_REPAIRTASKCODE);
        selectTokens.add(X2MessageSearchDTO.SELECT_FAULTANALYSISCODE);
        selectTokens.add(X2MessageSearchDTO.SELECT_REPAIRERRORCODECODE);

        searchObj.setFromClause(
                "from X2Message a left join a.materialRevision d left join d.material ae left join a.faultAnalysis c left join a.repairErrorCode e left join a.repairState f left join a.repairTask g left join a.serialObject h left join a.serviceMessage i left join i.serialObject m left join i.serviceOrder n");

        return repository.search(searchObj, X2MessageSearchDTO.class, selectTokens);
    }

    /**
     * Count X2 message objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllX2Messages(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from X2Message a left join a.materialRevision d left join d.material ae left join a.faultAnalysis c left join a.repairErrorCode e left join a.repairState f left join a.repairTask g left join a.serialObject h left join a.serviceMessage i left join i.serialObject m left join i.serviceOrder n");

        return repository.count(searchObj);
    }

}