package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class TraceBoMSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_DELIVERYNOTENUMBER = "deliveryNoteNumber";
    @Generated
    public static final String ATTR_LOTNUMBER = "lotNumber";
    @Generated
    public static final String ATTR_ORDERNUMBER = "orderNumber";
    @Generated
    public static final String ATTR_PRODUCTIONDATE = "productionDate";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_MATREVMATERIALID = "matrevMaterialId";
    @Generated
    public static final String ATTR_MATREVMATMATERIALNUMBER = "matrevMatMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matrevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVMATSHORTTEXT = "matrevMatShortText";
    @Generated
    public static final String ATTR_MATREVMATMATERIALTYPECODE = "matrevMatMaterialTypeCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALCLASSCODE = "matrevMatMaterialClassCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALHIERARCHY = "matrevMatMaterialHierarchy";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matrevRevisionNumber";
    @Generated
    public static final String SELECT_DELIVERYNOTENUMBER = "a.deliveryNoteNumber";
    @Generated
    public static final String SELECT_LOTNUMBER = "a.lotNumber";
    @Generated
    public static final String SELECT_ORDERNUMBER = "a.orderNumber";
    @Generated
    public static final String SELECT_PRODUCTIONDATE = "a.productionDate";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_SUPPLIERNAME = "c.name";
    @Generated
    public static final String SELECT_SUPPLIERCODE = "c.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "b.id";
    @Generated
    public static final String SELECT_MATREVMATERIALID = "f.id";
    @Generated
    public static final String SELECT_MATREVMATMATERIALNUMBER = "f.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATSAPNUMBER = "f.sapNumber";
    @Generated
    public static final String SELECT_MATREVMATSHORTTEXT = "f.shortText";
    @Generated
    public static final String SELECT_MATREVMATMATERIALTYPECODE = "k.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALCLASSCODE = "j.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALHIERARCHY = "f.materialHierarchy";
    @Generated
    public static final String SELECT_MATREVREVISIONNUMBER = "b.revisionNumber";
    @Generated
    private String deliveryNoteNumber;
    @Generated
    private String lotNumber;
    @Generated
    private String orderNumber;
    @Generated
    private LocalDate productionDate;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String supplierName;
    @Generated
    private String supplierCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private long matrevMaterialId;
    @Generated
    private String matrevMatMaterialNumber;
    @Generated
    private String matrevMatSapNumber;
    @Generated
    private String matrevMatShortText;
    @Generated
    private String matrevMatMaterialTypeCode;
    @Generated
    private String matrevMatMaterialClassCode;
    @Generated
    private String matrevMatMaterialHierarchy;
    @Generated
    private String matrevRevisionNumber;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public TraceBoMSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param deliveryNoteNumber
     * @param lotNumber
     * @param orderNumber
     * @param productionDate
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param supplierName
     * @param supplierCode
     * @param materialRevisionId
     * @param matrevMaterialId
     * @param matrevMatMaterialNumber
     * @param matrevMatSapNumber
     * @param matrevMatShortText
     * @param matrevMatMaterialTypeCode
     * @param matrevMatMaterialClassCode
     * @param matrevMatMaterialHierarchy
     * @param matrevRevisionNumber
     */
    @Generated
    public TraceBoMSearchDTO(String deliveryNoteNumber, String lotNumber, String orderNumber, LocalDate productionDate, long id,
            LocalDateTime creationDate, LocalDateTime lastUpdate, String supplierName, String supplierCode, long materialRevisionId,
            long matrevMaterialId, String matrevMatMaterialNumber, String matrevMatSapNumber, String matrevMatShortText,
            String matrevMatMaterialTypeCode, String matrevMatMaterialClassCode, String matrevMatMaterialHierarchy, String matrevRevisionNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
        this.lotNumber = lotNumber;
        this.orderNumber = orderNumber;
        this.productionDate = productionDate;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.supplierName = supplierName;
        this.supplierCode = supplierCode;
        this.materialRevisionId = materialRevisionId;
        this.matrevMaterialId = matrevMaterialId;
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
        this.matrevMatSapNumber = matrevMatSapNumber;
        this.matrevMatShortText = matrevMatShortText;
        this.matrevMatMaterialTypeCode = matrevMatMaterialTypeCode;
        this.matrevMatMaterialClassCode = matrevMatMaterialClassCode;
        this.matrevMatMaterialHierarchy = matrevMatMaterialHierarchy;
        this.matrevRevisionNumber = matrevRevisionNumber;
    }

    /**
     * @return the delivery note number
     */
    @Generated
    public String getDeliveryNoteNumber() {
        return this.deliveryNoteNumber;
    }

    /**
     * @param deliveryNoteNumber the delivery note number to set
     */
    @Generated
    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    /**
     * @return the lot number
     */
    @Generated
    public String getLotNumber() {
        return this.lotNumber;
    }

    /**
     * @param lotNumber the lot number to set
     */
    @Generated
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * @return the order number
     */
    @Generated
    public String getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * @param orderNumber the order number to set
     */
    @Generated
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the production date
     */
    @Generated
    public LocalDate getProductionDate() {
        return this.productionDate;
    }

    /**
     * @param productionDate the production date to set
     */
    @Generated
    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * @return the id
     */
    @Generated
    public long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    @Generated
    public void setId(long id) {
        this.id = id;
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
     * @return the id of the material revision
     */
    @Generated
    public long getMaterialRevisionId() {
        return this.materialRevisionId;
    }

    /**
     * @param materialRevisionId the id of the material revision to set
     */
    @Generated
    public void setMaterialRevisionId(long materialRevisionId) {
        this.materialRevisionId = materialRevisionId;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public long getMatrevMaterialId() {
        return this.matrevMaterialId;
    }

    /**
     * @param matrevMaterialId the id of the material to set
     */
    @Generated
    public void setMatrevMaterialId(long matrevMaterialId) {
        this.matrevMaterialId = matrevMaterialId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getMatrevMatMaterialNumber() {
        return this.matrevMatMaterialNumber;
    }

    /**
     * @param matrevMatMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMatrevMatMaterialNumber(String matrevMatMaterialNumber) {
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMatrevMatSapNumber() {
        return this.matrevMatSapNumber;
    }

    /**
     * @param matrevMatSapNumber the sap number of the material to set
     */
    @Generated
    public void setMatrevMatSapNumber(String matrevMatSapNumber) {
        this.matrevMatSapNumber = matrevMatSapNumber;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getMatrevMatShortText() {
        return this.matrevMatShortText;
    }

    /**
     * @param matrevMatShortText the short text of the material to set
     */
    @Generated
    public void setMatrevMatShortText(String matrevMatShortText) {
        this.matrevMatShortText = matrevMatShortText;
    }

    /**
     * @return the code of the material type
     */
    @Generated
    public String getMatrevMatMaterialTypeCode() {
        return this.matrevMatMaterialTypeCode;
    }

    /**
     * @param matrevMatMaterialTypeCode the code of the material type to set
     */
    @Generated
    public void setMatrevMatMaterialTypeCode(String matrevMatMaterialTypeCode) {
        this.matrevMatMaterialTypeCode = matrevMatMaterialTypeCode;
    }

    /**
     * @return the code of the material class
     */
    @Generated
    public String getMatrevMatMaterialClassCode() {
        return this.matrevMatMaterialClassCode;
    }

    /**
     * @param matrevMatMaterialClassCode the code of the material class to set
     */
    @Generated
    public void setMatrevMatMaterialClassCode(String matrevMatMaterialClassCode) {
        this.matrevMatMaterialClassCode = matrevMatMaterialClassCode;
    }

    /**
     * @return the material hierarchy of the material
     */
    @Generated
    public String getMatrevMatMaterialHierarchy() {
        return this.matrevMatMaterialHierarchy;
    }

    /**
     * @param matrevMatMaterialHierarchy the material hierarchy of the material to set
     */
    @Generated
    public void setMatrevMatMaterialHierarchy(String matrevMatMaterialHierarchy) {
        this.matrevMatMaterialHierarchy = matrevMatMaterialHierarchy;
    }

    /**
     * @return the revision number of the material revision
     */
    @Generated
    public String getMatrevRevisionNumber() {
        return this.matrevRevisionNumber;
    }

    /**
     * @param matrevRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setMatrevRevisionNumber(String matrevRevisionNumber) {
        this.matrevRevisionNumber = matrevRevisionNumber;
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

        final var dto = (TraceBoMSearchDTO) obj;

        return this.id == dto.getId();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}