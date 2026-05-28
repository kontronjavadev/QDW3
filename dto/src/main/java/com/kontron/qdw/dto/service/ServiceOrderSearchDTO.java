package com.kontron.qdw.dto.service;

import java.io.Serializable;
import com.kontron.qdw.domain.mv.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ServiceOrderSearchDTO implements Serializable {
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
    public static final String ATTR_CUSTOMERCODE = "customerCode";
    @Generated
    public static final String ATTR_CUSTOMERNAME = "customerName";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String SELECT_DOCUMENTDATE = "a.documentDate";
    @Generated
    public static final String SELECT_SERVICEORDERTYPE = "a.serviceOrderType";
    @Generated
    public static final String SELECT_CODE = "a.code";
    @Generated
    public static final String SELECT_SHORTTEXT = "a.shortText";
    @Generated
    public static final String SELECT_COMMENT = "a.comment";
    @Generated
    public static final String SELECT_ACTIVE = "a.active";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_CUSTOMERCODE = "b.code";
    @Generated
    public static final String SELECT_CUSTOMERNAME = "b.name";
    @Generated
    public static final String SELECT_SUPPLIERCODE = "c.code";
    @Generated
    public static final String SELECT_SUPPLIERNAME = "c.name";
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
    private String customerCode;
    @Generated
    private String customerName;
    @Generated
    private String supplierCode;
    @Generated
    private String supplierName;

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrderSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param code
     */
    @Generated
    public ServiceOrderSearchDTO(String code) {
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
     * @param customerCode
     * @param customerName
     * @param supplierCode
     * @param supplierName
     */
    @Generated
    public ServiceOrderSearchDTO(LocalDate documentDate, ServiceOrderType serviceOrderType, String code, String shortText, String comment,
            boolean active, LocalDateTime creationDate, LocalDateTime lastUpdate, String customerCode, String customerName, String supplierCode,
            String supplierName) {
        this.documentDate = documentDate;
        this.serviceOrderType = serviceOrderType;
        this.code = code;
        this.shortText = shortText;
        this.comment = comment;
        this.active = active;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
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
     * @return the code of the customer
     */
    @Generated
    public String getCustomerCode() {
        return this.customerCode;
    }

    /**
     * @param customerCode the code of the customer to set
     */
    @Generated
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * @return the name of the customer
     */
    @Generated
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * @param customerName the name of the customer to set
     */
    @Generated
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the code of the supplier
     */
    @Generated
    public String getSupplierCode() {
        return this.supplierCode;
    }

    /**
     * @param supplierCode the code of the supplier to set
     */
    @Generated
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * @return the name of the supplier
     */
    @Generated
    public String getSupplierName() {
        return this.supplierName;
    }

    /**
     * @param supplierName the name of the supplier to set
     */
    @Generated
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

        final var dto = (ServiceOrderSearchDTO) obj;

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