package com.kontron.qdw.dto.base;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class PlantListDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_CODE = "code";
    @Generated
    public static final String SELECT_CODE = "a.code";
    @Generated
    private String code;

    /**
     * Default constructor
     */
    @Generated
    public PlantListDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param code
     */
    @Generated
    public PlantListDTO(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * @param code the code to set
     */
    @Generated
    public void setCode(String code) {
        this.code = code;
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

        final var dto = (PlantListDTO) obj;

        if (this.code == null) {
            if (dto.getCode() != null)
                return false;
        }
        else if (!this.code.equals(dto.getCode()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (code == null) ? 0 : code.hashCode();
    }

}