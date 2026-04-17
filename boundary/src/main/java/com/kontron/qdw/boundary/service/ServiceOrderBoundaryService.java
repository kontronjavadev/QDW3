package com.kontron.qdw.boundary.service;

import com.kontron.qdw.repository.service.*;
import com.kontron.qdw.domain.service.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.dto.base.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.service.*;

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

}