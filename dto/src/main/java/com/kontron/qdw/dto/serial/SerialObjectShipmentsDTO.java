package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectShipmentsDTO implements Serializable {
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
    public static final String ATTR_MATREVID = "matRevId";
    @Generated
    public static final String ATTR_SERIALOBJECTID = "serialObjectId";
    @Generated
    public static final String ATTR_MATREVMATID = "matRevMatId";
    @Generated
    public static final String ATTR_MATREVMATMATERIALNUMBER = "matRevMatMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matRevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matRevRevisionNumber";
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
    public static final String SELECT_MATREVID = "c.id";
    @Generated
    public static final String SELECT_SERIALOBJECTID = "f.id";
    @Generated
    public static final String SELECT_MATREVMATID = "g.id";
    @Generated
    public static final String SELECT_MATREVMATMATERIALNUMBER = "g.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATSAPNUMBER = "g.sapNumber";
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
    private long matRevId;
    @Generated
    private long serialObjectId;
    @Generated
    private long matRevMatId;
    @Generated
    private String matRevMatMaterialNumber;
    @Generated
    private String matRevMatSapNumber;
    @Generated
    private String matRevRevisionNumber;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectShipmentsDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectShipmentsDTO(long id) {
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
     * @param matRevId
     * @param serialObjectId
     * @param matRevMatId
     * @param matRevMatMaterialNumber
     * @param matRevMatSapNumber
     * @param matRevRevisionNumber
     */
    @Generated
    public SerialObjectShipmentsDTO(String orderNumber, LocalDate shipmentDate, long id, LocalDateTime creationDate, LocalDateTime lastUpdate,
            String customerName, String customerCode, String movementTypeCode, String plantCode, long matRevId, long serialObjectId, long matRevMatId,
            String matRevMatMaterialNumber, String matRevMatSapNumber, String matRevRevisionNumber) {
        this.orderNumber = orderNumber;
        this.shipmentDate = shipmentDate;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.movementTypeCode = movementTypeCode;
        this.plantCode = plantCode;
        this.matRevId = matRevId;
        this.serialObjectId = serialObjectId;
        this.matRevMatId = matRevMatId;
        this.matRevMatMaterialNumber = matRevMatMaterialNumber;
        this.matRevMatSapNumber = matRevMatSapNumber;
        this.matRevRevisionNumber = matRevRevisionNumber;
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
    public long getMatRevId() {
        return this.matRevId;
    }

    /**
     * @param matRevId the id of the material revision to set
     */
    @Generated
    public void setMatRevId(long matRevId) {
        this.matRevId = matRevId;
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
     * @return the id of the material
     */
    @Generated
    public long getMatRevMatId() {
        return this.matRevMatId;
    }

    /**
     * @param matRevMatId the id of the material to set
     */
    @Generated
    public void setMatRevMatId(long matRevMatId) {
        this.matRevMatId = matRevMatId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getMatRevMatMaterialNumber() {
        return this.matRevMatMaterialNumber;
    }

    /**
     * @param matRevMatMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMatRevMatMaterialNumber(String matRevMatMaterialNumber) {
        this.matRevMatMaterialNumber = matRevMatMaterialNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMatRevMatSapNumber() {
        return this.matRevMatSapNumber;
    }

    /**
     * @param matRevMatSapNumber the sap number of the material to set
     */
    @Generated
    public void setMatRevMatSapNumber(String matRevMatSapNumber) {
        this.matRevMatSapNumber = matRevMatSapNumber;
    }

    /**
     * @return the revision number of the material revision
     */
    @Generated
    public String getMatRevRevisionNumber() {
        return this.matRevRevisionNumber;
    }

    /**
     * @param matRevRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setMatRevRevisionNumber(String matRevRevisionNumber) {
        this.matRevRevisionNumber = matRevRevisionNumber;
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

        final var dto = (SerialObjectShipmentsDTO) obj;

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