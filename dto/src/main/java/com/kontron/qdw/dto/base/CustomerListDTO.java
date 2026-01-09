package com.kontron.qdw.dto.base;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class CustomerListDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_CODE = "code";
    @Generated
    public static final String ATTR_NAME = "name";

    public static final String SELECT_CODE = "a." + ATTR_CODE;
    public static final String SELECT_NAME = "a." + ATTR_NAME;

    @Generated
    private String code;
    @Generated
    private String name;

    /**
     * Default constructor
     */
    @Generated
    public CustomerListDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param code
     */
    @Generated
    public CustomerListDTO(String code) {
        this.code = code;
    }

    /**
     * Constructor with ID and display attribute
     * @param code
     * @param name
     */
    @Generated
    public CustomerListDTO(String code, String name) {
        this.code = code;
        this.name = name;
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

    /**
     * @return the name
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    @Generated
    public void setName(String name) {
        this.name = name;
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

        final var dto = (CustomerListDTO) obj;

        if (this.code == null) {
            if (dto.getCode() != null)
                return false;
        }
        else if (!this.code.equals(dto.getCode()) && !this.name.equals(dto.getName()))
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