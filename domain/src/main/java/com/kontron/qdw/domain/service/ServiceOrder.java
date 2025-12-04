package com.kontron.qdw.domain.service;

import jakarta.validation.constraints.*;
import java.util.*;
import com.kontron.qdw.domain.mv.*;
import java.time.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "service_order_tab")
@NamedQuery(name = ServiceOrder.NQ_GET_CUSTOMER, query = "select b from ServiceOrder a join a.customer b where a.code = :code")
@NamedQuery(name = ServiceOrder.NQ_GET_SUPPLIER, query = "select b from ServiceOrder a join a.supplier b where a.code = :code")
@NamedQuery(name = ServiceOrder.NQ_GET_SERVICEMESSAGES, query = "select b from ServiceOrder a join a.serviceMessages b where a.code = :code")
@NamedQuery(name = ServiceOrder.NQ_DELETE_ALL, query = "delete from ServiceOrder a")
@NamedQuery(name = ServiceOrder.NQ_DELETE, query = "delete from ServiceOrder a where a.code = :code")
@NamedQuery(name = ServiceOrder.NQ_GET_ALL, query = "select a from ServiceOrder a")
@NamedQuery(name = ServiceOrder.NQ_FIND, query = "select a from ServiceOrder a where a.code = :code")
@NamedQuery(name = ServiceOrder.NQ_CHECK, query = "select count(a) from ServiceOrder a where a.code = :code")
@NamedQuery(name = ServiceOrder.NQ_COUNT, query = "select count(a) from ServiceOrder a")
public class ServiceOrder extends AbstractFunctionalActiveEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "ServiceOrder.deleteAll";
    @Generated
    public static final String NQ_COUNT = "ServiceOrder.count";
    @Generated
    public static final String NQ_DELETE = "ServiceOrder.delete";
    @Generated
    public static final String NQ_GET_CUSTOMER = "ServiceOrder.getCustomer";
    @Generated
    public static final String NQ_GET_ALL = "ServiceOrder.getAll";
    @Generated
    public static final String NQ_GET_SERVICEMESSAGES = "ServiceOrder.getServiceMessages";
    @Generated
    public static final String NQ_GET_SUPPLIER = "ServiceOrder.getSupplier";
    @Generated
    public static final String NQ_CHECK = "ServiceOrder.check";
    @Generated
    public static final String NQ_FIND = "ServiceOrder.find";
    @Basic(optional = false)
    @Column(name = "document_date", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"documentDate\" must not be null!")
    @Generated
    private LocalDate documentDate;
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "service_order_type", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"serviceOrderType\" must not be null!")
    @Generated
    private ServiceOrderType serviceOrderType;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer", referencedColumnName = "code", nullable = true)
    @Generated
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "supplier", referencedColumnName = "code", nullable = true)
    @Generated
    private Supplier supplier;
    @OneToMany(targetEntity = ServiceMessage.class, mappedBy = "serviceOrder", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<ServiceMessage> serviceMessages = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrder() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public ServiceOrder(String code) {
        super(code);
    }

    /**
     * @return the document date
     */
    @Generated
    public LocalDate getDocumentDate() {
        return this.documentDate;
    }

    /**
     * @param documentDate the document date to set
     */
    @Generated
    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    /**
     * @return the service order type
     */
    @Generated
    public ServiceOrderType getServiceOrderType() {
        return this.serviceOrderType;
    }

    /**
     * @param serviceOrderType the service order type to set
     */
    @Generated
    public void setServiceOrderType(ServiceOrderType serviceOrderType) {
        this.serviceOrderType = serviceOrderType;
    }

    /**
     * @return the customer
     */
    @Generated
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * @param customer the customer to set
     */
    @Generated
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the supplier
     */
    @Generated
    public Supplier getSupplier() {
        return this.supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    @Generated
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return a collection of service messages
     */
    @Generated
    public Collection<ServiceMessage> getServiceMessages() {
        return this.serviceMessages;
    }

    /**
     * @param serviceMessages the service messages to set
     */
    @Generated
    public void setServiceMessages(Collection<ServiceMessage> serviceMessages) {
        this.serviceMessages = serviceMessages;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final var bean = (ServiceOrder) obj;

        if (getCode() == null) {
            if (bean.getCode() != null)
                return false;
        }
        else if (!getCode().equals(bean.getCode()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        // Return hash code of current date if primary key field is not yet set!
        if (getCode() == null)
            return new java.util.Date().hashCode();

        return getCode().hashCode();
    }

}