package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class TraceBoMTraceBoMItemsDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_DATECODE = "dateCode";
    @Generated
    public static final String ATTR_INFOFIELD1 = "infoField1";
    @Generated
    public static final String ATTR_INFOFIELD2 = "infoField2";
    @Generated
    public static final String ATTR_INFOFIELD3 = "infoField3";
    @Generated
    public static final String ATTR_INFOFIELD4 = "infoField4";
    @Generated
    public static final String ATTR_MANUFACTURERNAME = "manufacturerName";
    @Generated
    public static final String ATTR_MANUFACTURERREVISION = "manufacturerRevision";
    @Generated
    public static final String ATTR_ORDERCODE = "orderCode";
    @Generated
    public static final String ATTR_QUANTITY = "quantity";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_MATERIALID = "materialId";
    @Generated
    public static final String ATTR_TRACEBOMID = "traceBomId";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
    @Generated
    public static final String ATTR_MATERIALSHORTTEXT = "materialShortText";
    @Generated
    public static final String SELECT_DATECODE = "a.dateCode";
    @Generated
    public static final String SELECT_INFOFIELD1 = "a.infoField1";
    @Generated
    public static final String SELECT_INFOFIELD2 = "a.infoField2";
    @Generated
    public static final String SELECT_INFOFIELD3 = "a.infoField3";
    @Generated
    public static final String SELECT_INFOFIELD4 = "a.infoField4";
    @Generated
    public static final String SELECT_MANUFACTURERNAME = "a.manufacturerName";
    @Generated
    public static final String SELECT_MANUFACTURERREVISION = "a.manufacturerRevision";
    @Generated
    public static final String SELECT_ORDERCODE = "a.orderCode";
    @Generated
    public static final String SELECT_QUANTITY = "a.quantity";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_MATERIALMATERIALNUMBER = "b.materialNumber";
    @Generated
    public static final String SELECT_MATERIALID = "b.id";
    @Generated
    public static final String SELECT_TRACEBOMID = "c.id";
    @Generated
    public static final String SELECT_MATERIALSAPNUMBER = "b.sapNumber";
    @Generated
    public static final String SELECT_MATERIALSHORTTEXT = "b.shortText";
    @Generated
    private String dateCode;
    @Generated
    private String infoField1;
    @Generated
    private String infoField2;
    @Generated
    private String infoField3;
    @Generated
    private String infoField4;
    @Generated
    private String manufacturerName;
    @Generated
    private String manufacturerRevision;
    @Generated
    private String orderCode;
    @Generated
    private int quantity;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String materialMaterialNumber;
    @Generated
    private long materialId;
    @Generated
    private long traceBomId;
    @Generated
    private String materialSapNumber;
    @Generated
    private String materialShortText;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMTraceBoMItemsDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public TraceBoMTraceBoMItemsDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param dateCode
     * @param infoField1
     * @param infoField2
     * @param infoField3
     * @param infoField4
     * @param manufacturerName
     * @param manufacturerRevision
     * @param orderCode
     * @param quantity
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param materialMaterialNumber
     * @param materialId
     * @param traceBomId
     * @param materialSapNumber
     * @param materialShortText
     */
    @Generated
    public TraceBoMTraceBoMItemsDTO(String dateCode, String infoField1, String infoField2, String infoField3, String infoField4,
            String manufacturerName, String manufacturerRevision, String orderCode, int quantity, long id, LocalDateTime creationDate,
            LocalDateTime lastUpdate, String materialMaterialNumber, long materialId, long traceBomId, String materialSapNumber,
            String materialShortText) {
        this.dateCode = dateCode;
        this.infoField1 = infoField1;
        this.infoField2 = infoField2;
        this.infoField3 = infoField3;
        this.infoField4 = infoField4;
        this.manufacturerName = manufacturerName;
        this.manufacturerRevision = manufacturerRevision;
        this.orderCode = orderCode;
        this.quantity = quantity;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.materialMaterialNumber = materialMaterialNumber;
        this.materialId = materialId;
        this.traceBomId = traceBomId;
        this.materialSapNumber = materialSapNumber;
        this.materialShortText = materialShortText;
    }

    /**
     * @return the date code
     */
    @Generated
    public String getDateCode() {
        return this.dateCode;
    }

    /**
     * @param dateCode the date code to set
     */
    @Generated
    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    /**
     * @return the info field 1
     */
    @Generated
    public String getInfoField1() {
        return this.infoField1;
    }

    /**
     * @param infoField1 the info field 1 to set
     */
    @Generated
    public void setInfoField1(String infoField1) {
        this.infoField1 = infoField1;
    }

    /**
     * @return the info field 2
     */
    @Generated
    public String getInfoField2() {
        return this.infoField2;
    }

    /**
     * @param infoField2 the info field 2 to set
     */
    @Generated
    public void setInfoField2(String infoField2) {
        this.infoField2 = infoField2;
    }

    /**
     * @return the info field 3
     */
    @Generated
    public String getInfoField3() {
        return this.infoField3;
    }

    /**
     * @param infoField3 the info field 3 to set
     */
    @Generated
    public void setInfoField3(String infoField3) {
        this.infoField3 = infoField3;
    }

    /**
     * @return the info field 4
     */
    @Generated
    public String getInfoField4() {
        return this.infoField4;
    }

    /**
     * @param infoField4 the info field 4 to set
     */
    @Generated
    public void setInfoField4(String infoField4) {
        this.infoField4 = infoField4;
    }

    /**
     * @return the manufacturer name
     */
    @Generated
    public String getManufacturerName() {
        return this.manufacturerName;
    }

    /**
     * @param manufacturerName the manufacturer name to set
     */
    @Generated
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * @return the manufacturer revision
     */
    @Generated
    public String getManufacturerRevision() {
        return this.manufacturerRevision;
    }

    /**
     * @param manufacturerRevision the manufacturer revision to set
     */
    @Generated
    public void setManufacturerRevision(String manufacturerRevision) {
        this.manufacturerRevision = manufacturerRevision;
    }

    /**
     * @return the order code
     */
    @Generated
    public String getOrderCode() {
        return this.orderCode;
    }

    /**
     * @param orderCode the order code to set
     */
    @Generated
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * @return the quantity
     */
    @Generated
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    @Generated
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
     * @return the material number of the material
     */
    @Generated
    public String getMaterialMaterialNumber() {
        return this.materialMaterialNumber;
    }

    /**
     * @param materialMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMaterialMaterialNumber(String materialMaterialNumber) {
        this.materialMaterialNumber = materialMaterialNumber;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public long getMaterialId() {
        return this.materialId;
    }

    /**
     * @param materialId the id of the material to set
     */
    @Generated
    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    /**
     * @return the id of the trace BoM
     */
    @Generated
    public long getTraceBomId() {
        return this.traceBomId;
    }

    /**
     * @param traceBomId the id of the trace BoM to set
     */
    @Generated
    public void setTraceBomId(long traceBomId) {
        this.traceBomId = traceBomId;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMaterialSapNumber() {
        return this.materialSapNumber;
    }

    /**
     * @param materialSapNumber the sap number of the material to set
     */
    @Generated
    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getMaterialShortText() {
        return this.materialShortText;
    }

    /**
     * @param materialShortText the short text of the material to set
     */
    @Generated
    public void setMaterialShortText(String materialShortText) {
        this.materialShortText = materialShortText;
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

        final var dto = (TraceBoMTraceBoMItemsDTO) obj;

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