package com.kontron.qdw.dto.base;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class RoleDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_NAME = "name";
    @Generated
    private long id;
    @Generated
    private String name;

    /**
     * Default constructor
     */
    @Generated
    public RoleDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public RoleDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor with ID and display attribute
     * @param id
     * @param name
     */
    @Generated
    public RoleDTO(long id, String name) {
        this.id = id;
        this.name = name;
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

        final var dto = (RoleDTO) obj;

        return this.id == dto.getId() && this.name.equals(dto.getName());
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