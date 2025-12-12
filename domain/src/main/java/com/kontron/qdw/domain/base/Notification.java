package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "notification_tab")
@NamedQuery(name = Notification.NQ_GET_INITIATOR, query = "select b from Notification a join a.initiator b where a.id = :id")
@NamedQuery(name = Notification.NQ_GET_GELESEN, query = "select b from Notification a join a.gelesen b where a.id = :id")
@NamedQuery(name = Notification.NQ_DELETE_ALL, query = "delete from Notification a")
@NamedQuery(name = Notification.NQ_DELETE, query = "delete from Notification a where a.id = :id")
@NamedQuery(name = Notification.NQ_GET_ALL, query = "select a from Notification a")
@NamedQuery(name = Notification.NQ_FIND, query = "select a from Notification a where a.id = :id")
@NamedQuery(name = Notification.NQ_CHECK, query = "select count(a) from Notification a where a.id = :id")
@NamedQuery(name = Notification.NQ_COUNT, query = "select count(a) from Notification a")
public class Notification extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "Notification.deleteAll";
    @Generated
    public static final String NQ_COUNT = "Notification.count";
    @Generated
    public static final String NQ_DELETE = "Notification.delete";
    @Generated
    public static final String NQ_GET_ALL = "Notification.getAll";
    @Generated
    public static final String NQ_GET_INITIATOR = "Notification.getInitiator";
    @Generated
    public static final String NQ_GET_GELESEN = "Notification.getGelesen";
    @Generated
    public static final String NQ_CHECK = "Notification.check";
    @Generated
    public static final String NQ_FIND = "Notification.find";
    @Basic(optional = false)
    @Column(name = "header", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"header\" must not be null!")
    @Size(max = 50, message = "Length of field \"header\" is illegal!")
    @Generated
    private String header;
    @Basic(optional = false)
    @Column(name = "notification", nullable = false, updatable = true, insertable = true, length = 500)
    @NotNull(message = "Field \"notification\" must not be null!")
    @Size(max = 500, message = "Length of field \"notification\" is illegal!")
    @Generated
    private String notification;
    @Basic(optional = false)
    @Column(name = "notification_start", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"notificationStart\" must not be null!")
    @Generated
    private LocalDateTime notificationStart;
    @Column(name = "notification_end", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDateTime notificationEnd;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "initiator", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"initiator\" must not be null!")
    @Generated
    private User initiator;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "notification_users_tab", joinColumns = { @JoinColumn(name = "notification_pk") }, inverseJoinColumns = {
            @JoinColumn(name = "user_pk") })
    @Generated
    private Collection<User> gelesen = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public Notification() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public Notification(long id) {
        super(id);
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
     * @return the user
     */
    @Generated
    public User getInitiator() {
        return this.initiator;
    }

    /**
     * @param initiator the user to set
     */
    @Generated
    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    /**
     * @return a collection of users
     */
    @Generated
    public Collection<User> getGelesen() {
        return this.gelesen;
    }

    /**
     * @param gelesen the users to set
     */
    @Generated
    public void setGelesen(Collection<User> gelesen) {
        this.gelesen = gelesen;
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

        final var bean = (Notification) obj;

        return getId() == bean.getId();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

}