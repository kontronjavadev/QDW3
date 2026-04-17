package com.kontron.qdw.boundary.service;

import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.dto.service.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.repository.service.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class ServiceMessageBoundaryService {
    @Generated
    private final ServiceMessageRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public ServiceMessageBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public ServiceMessageBoundaryService(ServiceMessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for service message objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of service message objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ServiceMessageOpenSearchDTO> searchAllServiceMessagesOpen(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_ANALYSISTEXT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_BASICFINISHDATE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_BASICSTARTDATE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_CAUSETEXT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_CUSTOMERFAILURE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_CUSTOMERREPORT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_DEFECTCOMPONENT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_DELIVERYNOTENUMBER);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_DESIGNATOR);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_EPIDEMICFAILURE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_ERRORID);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_EXTERNALREPORT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_INTERNALARRIVALDATE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_INTERNALREPORT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_ORIGIN);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRDESCRIPTION);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERVICEMESSAGEID);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_ID);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERVICEORDERCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRSTATENAME);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRSTATECODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERVORDERCUSTOMERCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERVORDERCUSTOMERNAME);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERVORDERCUSTCOUNTRYCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_SERVORDERCUSTCOUNTRYNAME);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATREVMATERIALID);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATREVMATERIALMATERIALNUMBER);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATREVMATERIALSAPNUMBER);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATREVMATERIALSHORTTEXT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATREVMATMATERIALTYPECODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_MATERIALREVISIONREVISIONNUMBER);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODECODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODEGROUPNAME);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODENAME);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODESHORTTEXT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRLOCATIONCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_RMATYPECODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRSERVICECODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRSERVICENAME);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_FAULTANALYSISCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_FAULTANALYSISSHORTTEXT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRTASKCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_REPAIRTASKSHORTTEXT);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_EXTERNALSUPPLIERCODE);
        selectTokens.add(ServiceMessageOpenSearchDTO.SELECT_EXTERNALSUPPLIERNAME);

        searchObj.setFromClause(
                "from ServiceMessage a join a.materialRevision c join c.material z join z.ownerLocation ac join z.materialClass ad join z.materialType ae left join a.externalSupplier b join a.plant d left join a.serialObject e left join a.serviceOrder f left join a.faultAnalysis h left join a.rMAType i left join a.repairErrorCode j left join a.repairLocation k left join a.repairService l left join a.repairState m left join a.repairTask n left join f.customer p left join p.country w where a.internalShipmentDate is null and f.serviceOrderType = ServiceOrderType.RMA");

        return repository.search(searchObj, ServiceMessageOpenSearchDTO.class, selectTokens);
    }

    /**
     * Count service message objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllServiceMessagesOpen(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from ServiceMessage a join a.materialRevision c join c.material z join z.ownerLocation ac join z.materialClass ad join z.materialType ae left join a.externalSupplier b join a.plant d left join a.serialObject e left join a.serviceOrder f left join a.faultAnalysis h left join a.rMAType i left join a.repairErrorCode j left join a.repairLocation k left join a.repairService l left join a.repairState m left join a.repairTask n left join f.customer p left join p.country w where a.internalShipmentDate is null and f.serviceOrderType = ServiceOrderType.RMA");

        return repository.count(searchObj);
    }

    /**
     * Delete existing service message
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteServiceMessage(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected service message
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final ServiceMessage sourceObject = repository.findById(sourceObjectId);
        final ServiceMessage targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Find service message by its ID
     * @param id
     * @return the service message object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ServiceMessageDTO findServiceMessageById(long id) {
        // Find persistent object
        final ServiceMessage serviceMessage = repository.findById(id, true);

        final var dto = new ServiceMessageDTO();
        dto.setAnalysisText(serviceMessage.getAnalysisText());
        dto.setBasicFinishDate(serviceMessage.getBasicFinishDate());
        dto.setBasicStartDate(serviceMessage.getBasicStartDate());
        dto.setCauseText(serviceMessage.getCauseText());
        dto.setCustomerFailure(serviceMessage.isCustomerFailure());
        dto.setDefectComponent(serviceMessage.getDefectComponent());
        dto.setDeliveryNoteNumber(serviceMessage.getDeliveryNoteNumber());
        dto.setDesignator(serviceMessage.getDesignator());
        dto.setEpidemicFailure(serviceMessage.isEpidemicFailure());
        dto.setErrorId(serviceMessage.getErrorId());
        dto.setInternalArrivalDate(serviceMessage.getInternalArrivalDate());
        dto.setInternalShipmentDate(serviceMessage.getInternalShipmentDate());
        dto.setOrigin(serviceMessage.getOrigin());
        dto.setRebuildFlag(serviceMessage.getRebuildFlag());
        dto.setRepairDescription(serviceMessage.getRepairDescription());
        dto.setServiceMessageId(serviceMessage.getServiceMessageId());
        dto.setId(serviceMessage.getId());
        dto.setVersion(serviceMessage.getVersion());
        dto.setCreationDate(serviceMessage.getCreationDate());
        dto.setLastUpdate(serviceMessage.getLastUpdate());

        if (serviceMessage.getExternalSupplier() != null) {
            dto.setExternalSupplier(new SupplierListDTO());
            dto.getExternalSupplier().setCode(serviceMessage.getExternalSupplier().getCode());
            dto.getExternalSupplier().setName(serviceMessage.getExternalSupplier().getName());
        }

        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(serviceMessage.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(serviceMessage.getMaterialRevision().getRevisionNumber());
        dto.setPlant(new PlantListDTO());
        dto.getPlant().setCode(serviceMessage.getPlant().getCode());
        dto.setSerialObject(new SerialObjectListDTO());
        dto.getSerialObject().setId(serviceMessage.getSerialObject().getId());
        dto.getSerialObject().setSerialNumber(serviceMessage.getSerialObject().getSerialNumber());
        dto.setServiceOrder(new ServiceOrderListDTO());
        dto.getServiceOrder().setCode(serviceMessage.getServiceOrder().getCode());

        if (serviceMessage.getFaultAnalysis() != null) {
            dto.setFaultAnalysis(new FaultAnalysisListDTO());
            dto.getFaultAnalysis().setCode(serviceMessage.getFaultAnalysis().getCode());
        }


        if (serviceMessage.getrMAType() != null) {
            dto.setrMAType(new RMATypeListDTO());
            dto.getrMAType().setCode(serviceMessage.getrMAType().getCode());
        }


        if (serviceMessage.getRepairErrorCode() != null) {
            dto.setRepairErrorCode(new RepairErrorCodeListDTO());
            dto.getRepairErrorCode().setCode(serviceMessage.getRepairErrorCode().getCode());
            dto.getRepairErrorCode().setName(serviceMessage.getRepairErrorCode().getName());
        }


        if (serviceMessage.getRepairLocation() != null) {
            dto.setRepairLocation(new RepairLocationListDTO());
            dto.getRepairLocation().setCode(serviceMessage.getRepairLocation().getCode());
        }


        if (serviceMessage.getRepairService() != null) {
            dto.setRepairService(new RepairServiceListDTO());
            dto.getRepairService().setCode(serviceMessage.getRepairService().getCode());
            dto.getRepairService().setName(serviceMessage.getRepairService().getName());
        }

        dto.setRepairState(new RepairStateListDTO());
        dto.getRepairState().setCode(serviceMessage.getRepairState().getCode());
        dto.getRepairState().setName(serviceMessage.getRepairState().getName());

        if (serviceMessage.getRepairTask() != null) {
            dto.setRepairTask(new RepairTaskListDTO());
            dto.getRepairTask().setCode(serviceMessage.getRepairTask().getCode());
        }

        dto.setMaterialRevisionMaterial(new MaterialListDTO());
        dto.getMaterialRevisionMaterial().setId(serviceMessage.getMaterialRevision().getMaterial().getId());
        dto.getMaterialRevisionMaterial().setMaterialNumber(serviceMessage.getMaterialRevision().getMaterial().getMaterialNumber());
        dto.setMaterialSapNumber(serviceMessage.getMaterialRevision().getMaterial().getSapNumber());
        dto.setInternalReport(serviceMessage.getInternalReport());
        dto.setExternalReport(serviceMessage.getExternalReport());
        dto.setCustomerReport(serviceMessage.getCustomerReport());

        return dto;
    }

}