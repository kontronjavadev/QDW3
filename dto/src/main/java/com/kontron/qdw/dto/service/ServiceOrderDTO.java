package com.kontron.qdw.dto.service;

import com.kontron.qdw.domain.mv.*;
import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.base.*;

public class ServiceOrderDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_DOCUMENTDATE = "documentDate";
    @Generated
    public static final String ATTR_SERVICEORDERTYPE = "serviceOrderType";
    @Generated
    public static final String ATTR_CODE = "code";
    @Generated
    public static final String ATTR_SHORTTEXT = "shortText";
    @Generated
    public static final String ATTR_COMMENT = "comment";
    @Generated
    public static final String ATTR_ACTIVE = "active";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CUSTOMER = "customer";
    @Generated
    public static final String ATTR_SUPPLIER = "supplier";
    @Generated
    private LocalDate documentDate;
    @Generated
    private ServiceOrderType serviceOrderType;
    @Generated
    private String code;
    @Generated
    private String shortText;
    @Generated
    private String comment;
    @Generated
    private boolean active;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private int version;
    @Generated
    private CustomerListDTO customer;
    @Generated
    private SupplierListDTO supplier;

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrderDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param code
     */
    @Generated
    public ServiceOrderDTO(String code) {
        this.code = code;
    }

    /**
     * Constructor using fields
     * @param documentDate
     * @param serviceOrderType
     * @param code
     * @param shortText
     * @param comment
     * @param active
     * @param creationDate
     * @param lastUpdate
     * @param version
     */
    @Generated
    public ServiceOrderDTO(LocalDate documentDate, ServiceOrderType serviceOrderType, String code, String shortText, String comment, boolean active,
            LocalDateTime creationDate, LocalDateTime lastUpdate, int version) {
        this.documentDate = documentDate;
        this.serviceOrderType = serviceOrderType;
        this.code = code;
        this.shortText = shortText;
        this.comment = comment;
        this.active = active;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.version = version;
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
     * @return the code
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * @param code the code to set
     */
    @Generated
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the short text
     */
    @Generated
    public String getShortText() {
        return this.shortText;
    }

    /**
     * @param shortText the short text to set
     */
    @Generated
    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    /**
     * @return the comment
     */
    @Generated
    public String getComment() {
        return this.comment;
    }

    /**
     * @param comment the comment to set
     */
    @Generated
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return true if the active flag is set
     */
    @Generated
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param active the value of the active flag to set
     */
    @Generated
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the creation date
     */
    @Generated
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @param creationDate the creation date to set
     */
    @Generated
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the last update
     */
    @Generated
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * @param lastUpdate the last update to set
     */
    @Generated
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the version
     */
    @Generated
    public int getVersion() {
        return this.version;
    }

    /**
     * @param version the version to set
     */
    @Generated
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return the customer
     */
    @Generated
    public CustomerListDTO getCustomer() {
        return this.customer;
    }

    /**
     * @param customer the customer to set
     */
    @Generated
    public void setCustomer(CustomerListDTO customer) {
        this.customer = customer;
    }

    /**
     * @return the supplier
     */
    @Generated
    public SupplierListDTO getSupplier() {
        return this.supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    @Generated
    public void setSupplier(SupplierListDTO supplier) {
        this.supplier = supplier;
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

        final var dto = (ServiceOrderDTO) obj;

        if (this.code == null) {
            if (dto.getCode() != null)
                return false;
        }
        else if (!this.code.equals(dto.getCode()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (code == null) ? 0 : code.hashCode();
    }

}