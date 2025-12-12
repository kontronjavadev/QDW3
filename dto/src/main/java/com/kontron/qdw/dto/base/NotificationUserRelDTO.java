package com.kontron.qdw.dto.base;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NotificationUserRelDTO implements Serializable {

    private static final long serialVersionUID = -1074088002272964366L;

    public static final String SELECT_ID = "a.id";
    public static final String SELECT_HEADER = "a.header";
    public static final String SELECT_NOTIFICATION = "a.notification";
    public static final String SELECT_NOTIFICATIONSTART = "a.notificationStart";
    public static final String SELECT_NOTIFICATIONEND = "a.notificationEnd";
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    public static final String SELECT_INITIATORNAME = "b.name";

    private final long id;
    private final String header;
    private final String notification;
    private final LocalDateTime notificationStart;
    private final LocalDateTime notificationEnd;
    private final String initiatorName;
    private final boolean read;

    public NotificationUserRelDTO(long id, String header, String notification, LocalDateTime notificationStart, LocalDateTime notificationEnd,
            String initiatorName, boolean read) {
        this.id = id;
        this.header = header;
        this.notification = notification;
        this.notificationStart = notificationStart;
        this.notificationEnd = notificationEnd;
        this.initiatorName = initiatorName;
        this.read = read;
    }



    @Override
    public String toString() {
        return "[" + initiatorName + "]: "
                + header + ": " + notification
                + " (" + notificationStart + " - " + notificationEnd + ")";
    }

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

        final var dto = (NotificationUserRelDTO) obj;

        return this.id == dto.getId();
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }



    public long getId() {
        return id;
    }

    public String getHeader() {
        return this.header;
    }

    public String getNotification() {
        return this.notification;
    }

    public LocalDateTime getNotificationStart() {
        return notificationStart;
    }

    public LocalDateTime getNotificationEnd() {
        return notificationEnd;
    }

    public String getInitiatorName() {
        return this.initiatorName;
    }

    public boolean isRead() {
        return read;
    }

}
