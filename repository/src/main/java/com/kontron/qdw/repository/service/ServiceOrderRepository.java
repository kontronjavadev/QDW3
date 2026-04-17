package com.kontron.qdw.repository.service;

import net.sourceforge.jbizmo.commons.random.*;
import java.util.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class ServiceOrderRepository extends AbstractRepository<ServiceOrder, String> {
    @Generated
    private static final String PARAM_CODE = "code";
    @Generated
    private final ServiceMessageRepository serviceMessageManager;

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrderRepository() {
        this.serviceMessageManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param serviceMessageManager
     */
    @Inject
    @Generated
    public ServiceOrderRepository(ServiceMessageRepository serviceMessageManager) {
        this.serviceMessageManager = serviceMessageManager;
    }

    /**
     * Find a persistent service order by using the primary key of the provided object
     * @param serviceOrder
     * @return the service order or null if the object could not be found
     */
    @Generated
    public ServiceOrder findById(ServiceOrder serviceOrder) {
        return findById(serviceOrder.getCode());
    }

    /**
     * Create a deep copy of the given service order
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new service order
     */
    @Generated
    public ServiceOrder copy(ServiceOrder sourceObject, ServiceOrder targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new ServiceOrder();
        }

        targetObject.setDocumentDate(sourceObject.getDocumentDate());
        targetObject.setServiceOrderType(sourceObject.getServiceOrderType());
        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setActive(sourceObject.isActive());
        targetObject.setCustomer(sourceObject.getCustomer());
        targetObject.setSupplier(sourceObject.getSupplier());

        targetObject = persist(targetObject, false, false);

        for (final ServiceMessage serviceMessage : sourceObject.getServiceMessages()) {
            var newServiceMessage = new ServiceMessage();
            newServiceMessage.setServiceOrder(targetObject);

            newServiceMessage = serviceMessageManager.copy(serviceMessage, newServiceMessage, loggedOnUserId);
            targetObject.getServiceMessages().add(newServiceMessage);
        }

        return targetObject;
    }

    /**
     * Get the customer of this service order
     * @param code
     * @return the customer of this service order, or null if it could not be found
     */
    @Generated
    public Customer getCustomer(String code) {
        final TypedQuery<Customer> query = em.createNamedQuery(ServiceOrder.NQ_GET_CUSTOMER, Customer.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the supplier of this service order
     * @param code
     * @return the supplier of this service order, or null if it could not be found
     */
    @Generated
    public Supplier getSupplier(String code) {
        final TypedQuery<Supplier> query = em.createNamedQuery(ServiceOrder.NQ_GET_SUPPLIER, Supplier.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all service messages of this service order
     * @param code
     * @return a list of service messages of this service order
     */
    @Generated
    public List<ServiceMessage> getServiceMessages(String code) {
        final TypedQuery<ServiceMessage> query = em.createNamedQuery(ServiceOrder.NQ_GET_SERVICEMESSAGES, ServiceMessage.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList();
    }

    /**
     * Change the 'customer' attribute of this service order
     * @param code
     * @param customer
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setCustomer(String code, Customer customer) {
        final ServiceOrder bean = findById(code, true);

        bean.setCustomer(customer);
    }

    /**
     * Change the 'supplier' attribute of this service order
     * @param code
     * @param supplier
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSupplier(String code, Supplier supplier) {
        final ServiceOrder bean = findById(code, true);

        bean.setSupplier(supplier);
    }

}