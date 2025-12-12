package com.kontron.qdw.dto.base;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class NotificationUpdateDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_HEADER = "header";
    @Generated
    public static final String ATTR_NOTIFICATION = "notification";
    @Generated
    public static final String ATTR_NOTIFICATIONSTART = "notificationStart";
    @Generated
    public static final String ATTR_NOTIFICATIONEND = "notificationEnd";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_INITIATOR = "initiator";
    @Generated
    private String header;
    @Generated
    private String notification;
    @Generated
    private LocalDateTime notificationStart;
    @Generated
    private LocalDateTime notificationEnd;
    @Generated
    private long id;
    @Generated
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private UserListDTO initiator;

    /**
     * Default constructor
     */
    @Generated
    public NotificationUpdateDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public NotificationUpdateDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param header
     * @param notification
     * @param notificationStart
     * @param notificationEnd
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     */
    @Generated
    public NotificationUpdateDTO(String header, String notification, LocalDateTime notificationStart, LocalDateTime notificationEnd, long id,
            int version, LocalDateTime creationDate, LocalDateTime lastUpdate) {
        this.header = header;
        this.notification = notification;
        this.notificationStart = notificationStart;
        this.notificationEnd = notificationEnd;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the header
     */
    @Generated
    public String getHeader() {
        return this.header;
    }

    /**
     * @param header the header to set
     */
    @Generated
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return the notification
     */
    @Generated
    public String getNotification() {
        return this.notification;
    }

    /**
     * @param notification the notification to set
     */
    @Generated
    public void setNotification(String notification) {
        this.notification = notification;
    }

    /**
     * @return the notification start
     */
    @Generated
    public LocalDateTime getNotificationStart() {
        return this.notificationStart;
    }

    /**
     * @param notificationStart the notification start to set
     */
    @Generated
    public void setNotificationStart(LocalDateTime notificationStart) {
        this.notificationStart = notificationStart;
    }

    /**
     * @return the notification end
     */
    @Generated
    public LocalDateTime getNotificationEnd() {
        return this.notificationEnd;
    }

    /**
     * @param notificationEnd the notification end to set
     */
    @Generated
    public void setNotificationEnd(LocalDateTime notificationEnd) {
        this.notificationEnd = notificationEnd;
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
     * @return the user
     */
    @Generated
    public UserListDTO getInitiator() {
        return this.initiator;
    }

    /**
     * @param initiator the user to set
     */
    @Generated
    public void setInitiator(UserListDTO initiator) {
        this.initiator = initiator;
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

        final var dto = (NotificationUpdateDTO) obj;

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