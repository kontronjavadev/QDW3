package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import com.kontron.qdw.dto.material.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class TraceBoMItemDTO implements Serializable {
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
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MATERIAL = "material";
    @Generated
    public static final String ATTR_TRACEBOM = "traceBom";
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
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private MaterialListDTO material;
    @Generated
    private TraceBoMListDTO traceBom;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMItemDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public TraceBoMItemDTO(long id) {
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
     * @param version
     * @param creationDate
     * @param lastUpdate
     */
    @Generated
    public TraceBoMItemDTO(String dateCode, String infoField1, String infoField2, String infoField3, String infoField4, String manufacturerName,
            String manufacturerRevision, String orderCode, int quantity, long id, int version, LocalDateTime creationDate, LocalDateTime lastUpdate) {
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
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
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
     * @return the material
     */
    @Generated
    public MaterialListDTO getMaterial() {
        return this.material;
    }

    /**
     * @param material the material to set
     */
    @Generated
    public void setMaterial(MaterialListDTO material) {
        this.material = material;
    }

    /**
     * @return the trace BoM
     */
    @Generated
    public TraceBoMListDTO getTraceBom() {
        return this.traceBom;
    }

    /**
     * @param traceBom the trace BoM to set
     */
    @Generated
    public void setTraceBom(TraceBoMListDTO traceBom) {
        this.traceBom = traceBom;
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

        final var dto = (TraceBoMItemDTO) obj;

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