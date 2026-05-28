package com.kontron.qdw.boundary.service;

import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.DEFAULT_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.dto.service.*;
import java.util.*;
import com.kontron.qdw.repository.service.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class ServiceOrderBoundaryService {
    @Generated
    private final ServiceOrderRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrderBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public ServiceOrderBoundaryService(ServiceOrderRepository repository) {
        this.repository = repository;
    }

    /**
     * Find service order by its ID
     * @param code
     * @return the service order object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ServiceOrderDTO findServiceOrderById(String code) {
        // Find persistent object
        final ServiceOrder serviceOrder = repository.findById(code, true);

        final var dto = new ServiceOrderDTO();
        dto.setDocumentDate(serviceOrder.getDocumentDate());
        dto.setServiceOrderType(serviceOrder.getServiceOrderType());
        dto.setCode(serviceOrder.getCode());
        dto.setShortText(serviceOrder.getShortText());
        dto.setComment(serviceOrder.getComment());
        dto.setActive(serviceOrder.isActive());
        dto.setCreationDate(serviceOrder.getCreationDate());
        dto.setLastUpdate(serviceOrder.getLastUpdate());
        dto.setVersion(serviceOrder.getVersion());

        if (serviceOrder.getCustomer() != null) {
            dto.setCustomer(new CustomerListDTO());
            dto.getCustomer().setCode(serviceOrder.getCustomer().getCode());
            dto.getCustomer().setName(serviceOrder.getCustomer().getName());
        }


        if (serviceOrder.getSupplier() != null) {
            dto.setSupplier(new SupplierListDTO());
            dto.getSupplier().setCode(serviceOrder.getSupplier().getCode());
            dto.getSupplier().setName(serviceOrder.getSupplier().getName());
        }


        return dto;
    }

    /**
     * Search for service order objects
     * @param filter
     * @return a list of service order objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ServiceOrderListDTO> findServiceOrders(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ServiceOrderListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from ServiceOrder a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(ServiceOrderListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, ServiceOrderListDTO.class, selectTokens);
    }

    /**
     * Get all service messages of a given service order
     * @param id
     * @return a list of service message objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ServiceOrderServiceMessagesDTO> getServiceMessagesOfServiceOrder(String id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_ANALYSISTEXT);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_BASICFINISHDATE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_BASICSTARTDATE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_CAUSETEXT);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_CUSTOMERFAILURE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_DEFECTCOMPONENT);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_DELIVERYNOTENUMBER);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_DESIGNATOR);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_EPIDEMICFAILURE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_ERRORID);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_INTERNALARRIVALDATE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_INTERNALSHIPMENTDATE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_ORIGIN);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRDESCRIPTION);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_SERVICEMESSAGEID);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_ID);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_CREATIONDATE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_LASTUPDATE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_PLANTCODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_SERVICEORDERCODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRSTATECODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_SERIALOBJECTSERIALNUMBER);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_MATREVMATERIALID);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_MATREVREVISIONNUMBER);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_RMATYPECODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRSERVICECODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRLOCATIONCODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRERRORCODECODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRERRORCODEGROUPNAME);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRTASKCODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_REPAIRTASKSHORTTEXT);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_FAULTANALYSISCODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_FAULTANALYSISSHORTTEXT);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_EXTERNALSUPPLIERCODE);
        selectTokens.add(ServiceOrderServiceMessagesDTO.SELECT_EXTERNALSUPPLIERNAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(DEFAULT_LIST_SIZE);
        searchObj.setFromClause(
                "from ServiceOrder x join x.serviceMessages a left join a.externalSupplier b left join a.materialRevision c left join a.plant d left join a.serialObject e left join a.serviceOrder f left join a.faultAnalysis h left join a.rMAType i left join a.repairErrorCode j left join a.repairLocation k left join a.repairService l left join a.repairState m left join a.repairTask n left join c.material u");

        final var parentFilterField = searchObj.addSearchField("x.code", SearchFieldDataTypeEnum.STRING);
        parentFilterField.setFilterCriteria(id);

        return repository.search(searchObj, ServiceOrderServiceMessagesDTO.class, selectTokens);
    }

}