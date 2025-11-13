package com.kontron.qdw.dto.base;

import java.util.*;
import java.io.Serializable;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class UserCreateDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_NAME = "name";
    @Generated
    public static final String ATTR_PASSWORD = "password";
    @Generated
    public static final String ATTR_EMAIL = "email";
    @Generated
    public static final String ATTR_ACTIVE = "active";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_ROLES = "roles";
    @Generated
    private String name;
    @Generated
    private String password;
    @Generated
    private String email;
    @Generated
    private boolean active;
    @Generated
    private long id;
    @Generated
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private Collection<RoleListDTO> roles = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public UserCreateDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public UserCreateDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor with ID and display attribute
     * @param name
     * @param id
     */
    @Generated
    public UserCreateDTO(String name, long id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param name
     * @param password
     * @param email
     * @param active
     * @param id
     * @param version
     * @param creationDate
     */
    @Generated
    public UserCreateDTO(String name, String password, String email, boolean active, long id, int version, LocalDateTime creationDate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.active = active;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
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
     * @return the password
     */
    @Generated
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    @Generated
    public void setPassword(String password) {
        this.password = password;
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
     * @return a collection of roles
     */
    @Generated
    public Collection<RoleListDTO> getRoles() {
        return this.roles;
    }

    /**
     * @param roles the roles to set
     */
    @Generated
    public void setRoles(Collection<RoleListDTO> roles) {
        this.roles = roles;
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

        final var dto = (UserCreateDTO) obj;

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