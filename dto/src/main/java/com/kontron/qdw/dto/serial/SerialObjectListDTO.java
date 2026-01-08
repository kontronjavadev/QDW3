package com.kontron.qdw.dto.serial;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectListDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_SERIALNUMBER = "a.serialNumber";
    @Generated
    private long id;
    @Generated
    private String serialNumber;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectListDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectListDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param serialNumber
     */
    @Generated
    public SerialObjectListDTO(long id, String serialNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
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
     * @return the serial number
     */
    @Generated
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * @param serialNumber the serial number to set
     */
    @Generated
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

        final var dto = (SerialObjectListDTO) obj;

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