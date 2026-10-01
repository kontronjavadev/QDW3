package com.kontron.qdw.dto.serial;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectAssemblyCheckDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_MATERIALREVISIONREVISIONNUMBER = "materialRevisionRevisionNumber";
    @Generated
    public static final String ATTR_TRACEBOMLOTNUMBER = "traceBomLotNumber";
    @Generated
    public static final String ATTR_TRACEBOMORDERNUMBER = "traceBomOrderNumber";
    @Generated
    private long id;
    @Generated
    private String serialNumber;
    @Generated
    private String materialMaterialNumber;
    @Generated
    private String materialRevisionRevisionNumber;
    @Generated
    private String traceBomLotNumber;
    @Generated
    private String traceBomOrderNumber;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectAssemblyCheckDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectAssemblyCheckDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param serialNumber
     * @param materialMaterialNumber
     * @param materialRevisionRevisionNumber
     * @param traceBomLotNumber
     * @param traceBomOrderNumber
     */
    @Generated
    public SerialObjectAssemblyCheckDTO(long id, String serialNumber, String materialMaterialNumber, String materialRevisionRevisionNumber,
            String traceBomLotNumber, String traceBomOrderNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.materialMaterialNumber = materialMaterialNumber;
        this.materialRevisionRevisionNumber = materialRevisionRevisionNumber;
        this.traceBomLotNumber = traceBomLotNumber;
        this.traceBomOrderNumber = traceBomOrderNumber;
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
     * @return the revision number of the material revision
     */
    @Generated
    public String getMaterialRevisionRevisionNumber() {
        return this.materialRevisionRevisionNumber;
    }

    /**
     * @param materialRevisionRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setMaterialRevisionRevisionNumber(String materialRevisionRevisionNumber) {
        this.materialRevisionRevisionNumber = materialRevisionRevisionNumber;
    }

    /**
     * @return the lot number of the trace BoM
     */
    @Generated
    public String getTraceBomLotNumber() {
        return this.traceBomLotNumber;
    }

    /**
     * @param traceBomLotNumber the lot number of the trace BoM to set
     */
    @Generated
    public void setTraceBomLotNumber(String traceBomLotNumber) {
        this.traceBomLotNumber = traceBomLotNumber;
    }

    /**
     * @return the order number of the trace BoM
     */
    @Generated
    public String getTraceBomOrderNumber() {
        return this.traceBomOrderNumber;
    }

    /**
     * @param traceBomOrderNumber the order number of the trace BoM to set
     */
    @Generated
    public void setTraceBomOrderNumber(String traceBomOrderNumber) {
        this.traceBomOrderNumber = traceBomOrderNumber;
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

        final var dto = (SerialObjectAssemblyCheckDTO) obj;

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
