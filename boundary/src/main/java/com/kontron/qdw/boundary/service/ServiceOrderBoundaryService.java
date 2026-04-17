package com.kontron.qdw.boundary.service;

import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
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

}