package com.kontron.qdw.dto.base;

import java.util.*;
import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class PasswordResetDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_UUID = "uuid";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_USERID = "userId";
    @Generated
    public static final String ATTR_USERNAME = "userName";
    @Generated
    private UUID uuid;
    @Generated
    private long id;
    @Generated
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private long userId;
    @Generated
    private String userName;

    /**
     * Default constructor
     */
    @Generated
    public PasswordResetDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public PasswordResetDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param uuid
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     * @param userId
     * @param userName
     */
    @Generated
    public PasswordResetDTO(UUID uuid, long id, int version, LocalDateTime creationDate, LocalDateTime lastUpdate, long userId, String userName) {
        this.uuid = uuid;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * @return the uuid
     */
    @Generated
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    @Generated
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
     * @return the id of the user
     */
    @Generated
    public long getUserId() {
        return this.userId;
    }

    /**
     * @param userId the id of the user to set
     */
    @Generated
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the name of the user
     */
    @Generated
    public String getUserName() {
        return this.userName;
    }

    /**
     * @param userName the name of the user to set
     */
    @Generated
    public void setUserName(String userName) {
        this.userName = userName;
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

        final var dto = (PasswordResetDTO) obj;

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