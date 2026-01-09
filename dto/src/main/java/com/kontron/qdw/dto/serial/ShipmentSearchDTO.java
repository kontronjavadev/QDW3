package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ShipmentSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ORDERNUMBER = "orderNumber";
    @Generated
    public static final String ATTR_SHIPMENTDATE = "shipmentDate";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_CUSTOMERNAME = "customerName";
    @Generated
    public static final String ATTR_CUSTOMERCODE = "customerCode";
    @Generated
    public static final String ATTR_MOVEMENTTYPECODE = "movementTypeCode";
    @Generated
    public static final String ATTR_PLANTCODE = "plantCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_SERIALOBJECTID = "serialObjectId";
    @Generated
    public static final String ATTR_SERIALOBJECTSERIALNUMBER = "serialObjectSerialNumber";
    @Generated
    public static final String ATTR_MATREVMATERIALID = "matrevMaterialId";
    @Generated
    public static final String ATTR_MATREVMATERIALMATERIALNUMBER = "matrevMaterialMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATERIALSAPNUMBER = "matrevMaterialSapNumber";
    @Generated
    public static final String ATTR_MATREVMATERIALSHORTTEXT = "matrevMaterialShortText";
    @Generated
    public static final String ATTR_MATREVMATOWNERLOCATIONCODE = "matrevMatOwnerLocationCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALTYPECODE = "matrevMatMaterialTypeCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALCLASSCODE = "matrevMatMaterialClassCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALHIERARCHY = "matrevMatMaterialHierarchy";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matrevRevisionNumber";
    @Generated
    public static final String SELECT_ORDERNUMBER = "a.orderNumber";
    @Generated
    public static final String SELECT_SHIPMENTDATE = "a.shipmentDate";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_CUSTOMERNAME = "b.name";
    @Generated
    public static final String SELECT_CUSTOMERCODE = "b.code";
    @Generated
    public static final String SELECT_MOVEMENTTYPECODE = "d.code";
    @Generated
    public static final String SELECT_PLANTCODE = "e.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "c.id";
    @Generated
    public static final String SELECT_SERIALOBJECTID = "f.id";
    @Generated
    public static final String SELECT_SERIALOBJECTSERIALNUMBER = "f.serialNumber";
    @Generated
    public static final String SELECT_MATREVMATERIALID = "l.id";
    @Generated
    public static final String SELECT_MATREVMATERIALMATERIALNUMBER = "l.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATERIALSAPNUMBER = "l.sapNumber";
    @Generated
    public static final String SELECT_MATREVMATERIALSHORTTEXT = "l.shortText";
    @Generated
    public static final String SELECT_MATREVMATOWNERLOCATIONCODE = "o.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALTYPECODE = "q.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALCLASSCODE = "p.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALHIERARCHY = "l.materialHierarchy";
    @Generated
    public static final String SELECT_MATREVREVISIONNUMBER = "c.revisionNumber";
    @Generated
    private String orderNumber;
    @Generated
    private LocalDate shipmentDate;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String customerName;
    @Generated
    private String customerCode;
    @Generated
    private String movementTypeCode;
    @Generated
    private String plantCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private long serialObjectId;
    @Generated
    private String serialObjectSerialNumber;
    @Generated
    private long matrevMaterialId;
    @Generated
    private String matrevMaterialMaterialNumber;
    @Generated
    private String matrevMaterialSapNumber;
    @Generated
    private String matrevMaterialShortText;
    @Generated
    private String matrevMatOwnerLocationCode;
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
    public ShipmentSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public ShipmentSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param orderNumber
     * @param shipmentDate
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param customerName
     * @param customerCode
     * @param movementTypeCode
     * @param plantCode
     * @param materialRevisionId
     * @param serialObjectId
     * @param serialObjectSerialNumber
     * @param matrevMaterialId
     * @param matrevMaterialMaterialNumber
     * @param matrevMaterialSapNumber
     * @param matrevMaterialShortText
     * @param matrevMatOwnerLocationCode
     * @param matrevMatMaterialTypeCode
     * @param matrevMatMaterialClassCode
     * @param matrevMatMaterialHierarchy
     * @param matrevRevisionNumber
     */
    @Generated
    public ShipmentSearchDTO(String orderNumber, LocalDate shipmentDate, long id, LocalDateTime creationDate, LocalDateTime lastUpdate,
            String customerName, String customerCode, String movementTypeCode, String plantCode, long materialRevisionId, long serialObjectId,
            String serialObjectSerialNumber, long matrevMaterialId, String matrevMaterialMaterialNumber, String matrevMaterialSapNumber,
            String matrevMaterialShortText, String matrevMatOwnerLocationCode, String matrevMatMaterialTypeCode, String matrevMatMaterialClassCode,
            String matrevMatMaterialHierarchy, String matrevRevisionNumber) {
        this.orderNumber = orderNumber;
        this.shipmentDate = shipmentDate;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.movementTypeCode = movementTypeCode;
        this.plantCode = plantCode;
        this.materialRevisionId = materialRevisionId;
        this.serialObjectId = serialObjectId;
        this.serialObjectSerialNumber = serialObjectSerialNumber;
        this.matrevMaterialId = matrevMaterialId;
        this.matrevMaterialMaterialNumber = matrevMaterialMaterialNumber;
        this.matrevMaterialSapNumber = matrevMaterialSapNumber;
        this.matrevMaterialShortText = matrevMaterialShortText;
        this.matrevMatOwnerLocationCode = matrevMatOwnerLocationCode;
        this.matrevMatMaterialTypeCode = matrevMatMaterialTypeCode;
        this.matrevMatMaterialClassCode = matrevMatMaterialClassCode;
        this.matrevMatMaterialHierarchy = matrevMatMaterialHierarchy;
        this.matrevRevisionNumber = matrevRevisionNumber;
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
     * @return the shipment date
     */
    @Generated
    public LocalDate getShipmentDate() {
        return this.shipmentDate;
    }

    /**
     * @param shipmentDate the shipment date to set
     */
    @Generated
    public void setShipmentDate(LocalDate shipmentDate) {
        this.shipmentDate = shipmentDate;
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
     * @return the code of the movement type
     */
    @Generated
    public String getMovementTypeCode() {
        return this.movementTypeCode;
    }

    /**
     * @param movementTypeCode the code of the movement type to set
     */
    @Generated
    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
    }

    /**
     * @return the code of the plant
     */
    @Generated
    public String getPlantCode() {
        return this.plantCode;
    }

    /**
     * @param plantCode the code of the plant to set
     */
    @Generated
    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
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
     * @return the id of the serial object
     */
    @Generated
    public long getSerialObjectId() {
        return this.serialObjectId;
    }

    /**
     * @param serialObjectId the id of the serial object to set
     */
    @Generated
    public void setSerialObjectId(long serialObjectId) {
        this.serialObjectId = serialObjectId;
    }

    /**
     * @return the serial number of the serial object
     */
    @Generated
    public String getSerialObjectSerialNumber() {
        return this.serialObjectSerialNumber;
    }

    /**
     * @param serialObjectSerialNumber the serial number of the serial object to set
     */
    @Generated
    public void setSerialObjectSerialNumber(String serialObjectSerialNumber) {
        this.serialObjectSerialNumber = serialObjectSerialNumber;
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
    public String getMatrevMaterialMaterialNumber() {
        return this.matrevMaterialMaterialNumber;
    }

    /**
     * @param matrevMaterialMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMatrevMaterialMaterialNumber(String matrevMaterialMaterialNumber) {
        this.matrevMaterialMaterialNumber = matrevMaterialMaterialNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMatrevMaterialSapNumber() {
        return this.matrevMaterialSapNumber;
    }

    /**
     * @param matrevMaterialSapNumber the sap number of the material to set
     */
    @Generated
    public void setMatrevMaterialSapNumber(String matrevMaterialSapNumber) {
        this.matrevMaterialSapNumber = matrevMaterialSapNumber;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getMatrevMaterialShortText() {
        return this.matrevMaterialShortText;
    }

    /**
     * @param matrevMaterialShortText the short text of the material to set
     */
    @Generated
    public void setMatrevMaterialShortText(String matrevMaterialShortText) {
        this.matrevMaterialShortText = matrevMaterialShortText;
    }

    /**
     * @return the code of the location
     */
    @Generated
    public String getMatrevMatOwnerLocationCode() {
        return this.matrevMatOwnerLocationCode;
    }

    /**
     * @param matrevMatOwnerLocationCode the code of the location to set
     */
    @Generated
    public void setMatrevMatOwnerLocationCode(String matrevMatOwnerLocationCode) {
        this.matrevMatOwnerLocationCode = matrevMatOwnerLocationCode;
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

        final var dto = (ShipmentSearchDTO) obj;

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