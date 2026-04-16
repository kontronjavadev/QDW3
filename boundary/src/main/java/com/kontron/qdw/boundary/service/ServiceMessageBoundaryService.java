package com.kontron.qdw.boundary.service;

import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.service.*;
import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.service.*;

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
                "from ServiceMessage a join a.materialRevision c join c.material z join z.ownerLocation ac join z.materialClass ad join z.materialType ae left join a.externalSupplier b join a.plant d left join a.serialObject e left join a.serviceOrder f left join a.faultAnalysis h left join a.rMAType i left join a.repairErrorCode j left join a.repairLocation k left join a.repairService l left join a.repairState m left join a.repairTask n left join f.customer p left join p.country w where a.internalShipmentDate is null and f.type = ServiceOrderType.RMA");

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
                "from ServiceMessage a join a.materialRevision c join c.material z join z.ownerLocation ac join z.materialClass ad join z.materialType ae left join a.externalSupplier b join a.plant d left join a.serialObject e left join a.serviceOrder f left join a.faultAnalysis h left join a.rMAType i left join a.repairErrorCode j left join a.repairLocation k left join a.repairService l left join a.repairState m left join a.repairTask n left join f.customer p left join p.country w where a.internalShipmentDate is null and f.type = ServiceOrderType.RMA");

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

}