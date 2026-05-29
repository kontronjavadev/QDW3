package com.kontron.qdw.boundary.service;

import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.MAX_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
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

    /**
     * Search for service message objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of service message objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ServiceMessageStandardSearchDTO> searchAllServiceMessagesStandard(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_ANALYSISTEXT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_BASICFINISHDATE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_BASICSTARTDATE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_CAUSETEXT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_CUSTOMERFAILURE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_CUSTOMERREPORT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_DEFECTCOMPONENT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_DELIVERYNOTENUMBER);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_DESIGNATOR);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_EPIDEMICFAILURE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_ERRORID);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_EXTERNALREPORT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_INTERNALARRIVALDATE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_INTERNALREPORT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_INTERNALSHIPMENTDATE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_ORIGIN);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRDESCRIPTION);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERVICEMESSAGEID);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_ID);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERVICEORDERCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRSTATENAME);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRSTATECODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERVORDCUSTOMERCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERVORDCUSTOMERNAME);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERVORDCUSTCOUNTRYCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERVORDCUSTCOUNTRYNAME);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVMATERIALID);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVMATSHORTTEXT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVMATMATERIALTYPECODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_MATREVREVISIONNUMBER);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_SERVICEORDERCOMMENT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRERRORCODECODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRERRORCODEGROUPNAME);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRERRORCODENAME);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRERRORCODESHORTTEXT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRLOCATIONCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_RMATYPECODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRSERVICECODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRSERVICENAME);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_FAULTANALYSISCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_FAULTANALYSISSHORTTEXT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRTASKCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_REPAIRTASKSHORTTEXT);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_EXTERNALSUPPLIERCODE);
        selectTokens.add(ServiceMessageStandardSearchDTO.SELECT_EXTERNALSUPPLIERNAME);

        searchObj.setFromClause(
                "from ServiceMessage a join a.materialRevision c join c.material z join z.ownerLocation ac join z.materialClass ad join z.materialType ae left join a.externalSupplier b join a.plant d join a.serialObject e join a.serviceOrder f left join a.faultAnalysis h left join a.rMAType i left join a.repairErrorCode j left join a.repairLocation k left join a.repairService l join a.repairState m left join a.repairTask n left join f.customer p join p.country w where f.serviceOrderType = ServiceOrderType.RMA");

        return repository.search(searchObj, ServiceMessageStandardSearchDTO.class, selectTokens);
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
    public long countAllServiceMessagesStandard(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from ServiceMessage a join a.materialRevision c join c.material z join z.ownerLocation ac join z.materialClass ad join z.materialType ae left join a.externalSupplier b join a.plant d join a.serialObject e join a.serviceOrder f left join a.faultAnalysis h left join a.rMAType i left join a.repairErrorCode j left join a.repairLocation k left join a.repairService l join a.repairState m left join a.repairTask n left join f.customer p join p.country w where f.serviceOrderType = ServiceOrderType.RMA");

        return repository.count(searchObj);
    }

    /**
     * Get all materials of a given service message
     * @param id
     * @return a list of material objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ServiceMessageFailureMaterialsDTO> getFailureMaterialsOfServiceMessage(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_SAPNUMBER);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_SHORTTEXT);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_COMMENT);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_FITVALUE);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_MATERIALHIERARCHY);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_ID);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_CREATIONDATE);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_LASTUPDATE);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_OWNERLOCATIONCODE);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_MATERIALCLASSCODE);
        selectTokens.add(ServiceMessageFailureMaterialsDTO.SELECT_MATERIALTYPECODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(MAX_LIST_SIZE);
        searchObj
                .setFromClause("from ServiceMessage x join x.failureMaterials a join a.ownerLocation b join a.materialClass c join a.materialType d");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, ServiceMessageFailureMaterialsDTO.class, selectTokens);
    }

    /**
     * Get all X2 messages of a given service message
     * @param id
     * @return a list of X2 message objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ServiceMessageX2MessagesDTO> getX2MessagesOfServiceMessage(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_ANALYSISTEXT);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_CAUSETEXT);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_CUSTOMERREPORT);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_DEFECTCOMPONENT);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_DESIGNATOR);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_WORKCENTER);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_ID);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_CREATIONDATE);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_LASTUPDATE);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_REPAIRSTATECODE);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_SERVICEMESSAGEID);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_SERIALOBJECTSERIALNUMBER);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_MATREVMATID);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_MATREVREVISIONNUMBER);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_DEFECTMATERIALID);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_DEFECTMATERIALMATERIALNUMBER);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_FAULTANALYSISCODE);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_REPAIRERRORCODECODE);
        selectTokens.add(ServiceMessageX2MessagesDTO.SELECT_REPAIRTASKCODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(MAX_LIST_SIZE);
        searchObj.setFromClause(
                "from ServiceMessage x join x.x2Messages a left join a.defectMaterial b left join a.faultAnalysis c left join a.materialRevision d left join a.repairErrorCode e left join a.repairState f left join a.repairTask g left join a.serialObject h left join a.serviceMessage i left join d.material p");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, ServiceMessageX2MessagesDTO.class, selectTokens);
    }

    /**
     * Search for service message objects
     * @param filter
     * @return a list of service message objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ServiceMessageListDTO> findServiceMessages(String filter) {
        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            try {
                Long.parseLong(filter);
            }
            catch (NumberFormatException e) {
                return Collections.emptyList();
            }
        }

        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ServiceMessageListDTO.SELECT_ID);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from ServiceMessage a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(ServiceMessageListDTO.SELECT_ID, SearchFieldDataTypeEnum.LONG);
            filterField.setFilterCriteria(filter);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, ServiceMessageListDTO.class, selectTokens);
    }

}
