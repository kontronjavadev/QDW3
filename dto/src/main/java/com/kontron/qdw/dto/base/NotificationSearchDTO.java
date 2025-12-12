package com.kontron.qdw.dto.base;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class NotificationSearchDTO implements Serializable {
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
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_INITIATORNAME = "initiatorName";
    @Generated
    public static final String ATTR_INITIATORID = "initiatorId";
    @Generated
    public static final String SELECT_HEADER = "a.header";
    @Generated
    public static final String SELECT_NOTIFICATION = "a.notification";
    @Generated
    public static final String SELECT_NOTIFICATIONSTART = "a.notificationStart";
    @Generated
    public static final String SELECT_NOTIFICATIONEND = "a.notificationEnd";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_INITIATORNAME = "b.name";
    @Generated
    public static final String SELECT_INITIATORID = "b.id";
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
    private LocalDateTime lastUpdate;
    @Generated
    private String initiatorName;
    @Generated
    private long initiatorId;

    /**
     * Default constructor
     */
    @Generated
    public NotificationSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public NotificationSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param header
     * @param notification
     * @param notificationStart
     * @param notificationEnd
     * @param id
     * @param lastUpdate
     * @param initiatorName
     * @param initiatorId
     */
    @Generated
    public NotificationSearchDTO(String header, String notification, LocalDateTime notificationStart, LocalDateTime notificationEnd, long id,
            LocalDateTime lastUpdate, String initiatorName, long initiatorId) {
        this.header = header;
        this.notification = notification;
        this.notificationStart = notificationStart;
        this.notificationEnd = notificationEnd;
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.initiatorName = initiatorName;
        this.initiatorId = initiatorId;
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
     * @return the name of the user
     */
    @Generated
    public String getInitiatorName() {
        return this.initiatorName;
    }

    /**
     * @param initiatorName the name of the user to set
     */
    @Generated
    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    /**
     * @return the id of the user
     */
    @Generated
    public long getInitiatorId() {
        return this.initiatorId;
    }

    /**
     * @param initiatorId the id of the user to set
     */
    @Generated
    public void setInitiatorId(long initiatorId) {
        this.initiatorId = initiatorId;
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

        final var dto = (NotificationSearchDTO) obj;

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