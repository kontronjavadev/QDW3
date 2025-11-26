package com.kontron.qdw.dto.material;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterialListDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_MATERIALNUMBER = "materialNumber";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_MATERIALNUMBER = "a.materialNumber";
    @Generated
    private long id;
    @Generated
    private String materialNumber;

    /**
     * Default constructor
     */
    @Generated
    public MaterialListDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialListDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor with ID and display attribute
     * @param id
     * @param materialNumber
     */
    @Generated
    public MaterialListDTO(long id, String materialNumber) {
        this.id = id;
        this.materialNumber = materialNumber;
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

        final var dto = (MaterialListDTO) obj;

        return this.id == dto.getId() && this.materialNumber.equals(dto.getMaterialNumber());
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