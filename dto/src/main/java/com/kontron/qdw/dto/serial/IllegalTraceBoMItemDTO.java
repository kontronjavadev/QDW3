package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class IllegalTraceBoMItemDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_DATECODE = "dateCode";
    @Generated
    public static final String ATTR_MANUFACTURER = "manufacturer";
    @Generated
    public static final String ATTR_MANUFACTURERREVISION = "manufacturerRevision";
    @Generated
    public static final String ATTR_MATERIALNUMBER = "materialNumber";
    @Generated
    public static final String ATTR_ORDERCODE = "orderCode";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_TRACEBOM = "traceBom";
    @Generated
    private String dateCode;
    @Generated
    private String manufacturer;
    @Generated
    private String manufacturerRevision;
    @Generated
    private String materialNumber;
    @Generated
    private String orderCode;
    @Generated
    private long id;
    @Generated
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private TraceBoMListDTO traceBom;

    /**
     * Default constructor
     */
    @Generated
    public IllegalTraceBoMItemDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public IllegalTraceBoMItemDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param dateCode
     * @param manufacturer
     * @param manufacturerRevision
     * @param materialNumber
     * @param orderCode
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     */
    @Generated
    public IllegalTraceBoMItemDTO(String dateCode, String manufacturer, String manufacturerRevision, String materialNumber, String orderCode, long id,
            int version, LocalDateTime creationDate, LocalDateTime lastUpdate) {
        this.dateCode = dateCode;
        this.manufacturer = manufacturer;
        this.manufacturerRevision = manufacturerRevision;
        this.materialNumber = materialNumber;
        this.orderCode = orderCode;
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
     * @return the manufacturer
     */
    @Generated
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    @Generated
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
     * @return the material number
     */
    @Generated
    public String getMaterialNumber() {
        return this.materialNumber;
    }

    /**
     * @param materialNumber the material number to set
     */
    @Generated
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
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

        final var dto = (IllegalTraceBoMItemDTO) obj;

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