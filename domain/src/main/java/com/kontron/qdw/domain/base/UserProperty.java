package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "user_property_tab")
@NamedQuery(name = UserProperty.NQ_GET_USER, query = "select b from UserProperty a join a.user b where a.id = :id")
@NamedQuery(name = UserProperty.NQ_DELETE_ALL, query = "delete from UserProperty a")
@NamedQuery(name = UserProperty.NQ_DELETE, query = "delete from UserProperty a where a.id = :id")
@NamedQuery(name = UserProperty.NQ_GET_ALL, query = "select a from UserProperty a")
@NamedQuery(name = UserProperty.NQ_FIND, query = "select a from UserProperty a where a.id = :id")
@NamedQuery(name = UserProperty.NQ_CHECK, query = "select count(a) from UserProperty a where a.id = :id")
@NamedQuery(name = UserProperty.NQ_COUNT, query = "select count(a) from UserProperty a")
public class UserProperty {
    @Generated
    public static final String NQ_DELETE_ALL = "UserProperty.deleteAll";
    @Generated
    public static final String NQ_COUNT = "UserProperty.count";
    @Generated
    public static final String NQ_DELETE = "UserProperty.delete";
    @Generated
    public static final String NQ_GET_ALL = "UserProperty.getAll";
    @Generated
    public static final String NQ_GET_USER = "UserProperty.getUser";
    @Generated
    public static final String NQ_CHECK = "UserProperty.check";
    @Generated
    public static final String NQ_FIND = "UserProperty.find";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;
    @Basic(optional = false)
    @Column(name = "prop_key", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"propKey\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"propKey\" is illegal!")
    @Generated
    private String propKey;
    @Basic(optional = false)
    @Column(name = "view_name", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"viewName\" must not be null!")
    @Size(max = 100, message = "Length of field \"viewName\" is illegal!")
    @Generated
    private String viewName;
    @Basic(optional = false)
    @Column(name = "value", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"value\" must not be null!")
    @Size(max = 100, message = "Length of field \"value\" is illegal!")
    @Generated
    private String value;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"user\" must not be null!")
    @Generated
    private User user;

    /**
     * Default constructor
     */
    @Generated
    public UserProperty() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public UserProperty(long id) {
        this.id = id;
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
     * @return the prop key
     */
    @Generated
    public String getPropKey() {
        return this.propKey;
    }

    /**
     * @param propKey the prop key to set
     */
    @Generated
    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    /**
     * @return the view name
     */
    @Generated
    public String getViewName() {
        return this.viewName;
    }

    /**
     * @param viewName the view name to set
     */
    @Generated
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    /**
     * @return the value
     */
    @Generated
    public String getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    @Generated
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the user
     */
    @Generated
    public User getUser() {
        return this.user;
    }

    /**
     * @param user the user to set
     */
    @Generated
    public void setUser(User user) {
        this.user = user;
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

        final var bean = (UserProperty) obj;

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