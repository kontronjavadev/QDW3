package com.kontron.qdw.dto.base;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class EWSEntryReceiversDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_NAME = "name";
    @Generated
    public static final String ATTR_EMAIL = "email";
    @Generated
    public static final String ATTR_ACTIVE = "active";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String SELECT_NAME = "a.name";
    @Generated
    public static final String SELECT_EMAIL = "a.email";
    @Generated
    public static final String SELECT_ACTIVE = "a.active";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    private String name;
    @Generated
    private String email;
    @Generated
    private boolean active;
    @Generated
    private long id;

    /**
     * Default constructor
     */
    @Generated
    public EWSEntryReceiversDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public EWSEntryReceiversDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor with ID and display attribute
     * @param name
     * @param id
     */
    @Generated
    public EWSEntryReceiversDTO(String name, long id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param name
     * @param email
     * @param active
     * @param id
     */
    @Generated
    public EWSEntryReceiversDTO(String name, String email, boolean active, long id) {
        this.name = name;
        this.email = email;
        this.active = active;
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

    /**
     * @return the email
     */
    @Generated
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return true if the active flag is set
     */
    @Generated
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param active the value of the active flag to set
     */
    @Generated
    public void setActive(boolean active) {
        this.active = active;
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

        final var dto = (EWSEntryReceiversDTO) obj;

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