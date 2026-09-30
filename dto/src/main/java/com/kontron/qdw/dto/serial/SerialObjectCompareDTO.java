package com.kontron.qdw.dto.serial;

import java.io.Serializable;

import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectCompareDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_MATERIALID = "materialId";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_TRACEBOMID = "traceBomId";
    @Generated
    private long id;
    @Generated
    private String serialNumber;
    @Generated
    private long materialId;
    @Generated
    private String materialMaterialNumber;
    @Customized
    private Long traceBomId;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectCompareDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectCompareDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param serialNumber
     * @param materialId
     * @param materialMaterialNumber
     * @param traceBomId
     */
    @Customized
    public SerialObjectCompareDTO(long id, String serialNumber, long materialId, String materialMaterialNumber, Long traceBomId) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.materialId = materialId;
        this.materialMaterialNumber = materialMaterialNumber;
        this.traceBomId = traceBomId;
    }


    @Override
    public String toString() {
        if (getSerialNumber() == null) {
            // es steht wohl überhaupt nichts im DTO, mglw. vom Converter produziert, aber wir wollen trotzdem etwas sehen!
            return "id " + getId();
        }
        return getSerialNumber();
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
     * @return the id of the trace BoM
     */
    @Generated
    public long getTraceBomId() {
        return this.traceBomId;
    }

    /**
     * @param traceBomId the id of the trace BoM to set
     */
    @Customized
    public void setTraceBomId(Long traceBomId) {
        this.traceBomId = traceBomId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final var dto = (SerialObjectCompareDTO) obj;

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
