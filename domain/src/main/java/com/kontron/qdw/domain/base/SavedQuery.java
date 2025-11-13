package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "saved_query_tab")
@NamedQuery(name = SavedQuery.NQ_GET_USER, query = "select b from SavedQuery a join a.user b where a.id = :id")
@NamedQuery(name = SavedQuery.NQ_DELETE_ALL, query = "delete from SavedQuery a")
@NamedQuery(name = SavedQuery.NQ_DELETE, query = "delete from SavedQuery a where a.id = :id")
@NamedQuery(name = SavedQuery.NQ_GET_ALL, query = "select a from SavedQuery a")
@NamedQuery(name = SavedQuery.NQ_FIND, query = "select a from SavedQuery a where a.id = :id")
@NamedQuery(name = SavedQuery.NQ_CHECK, query = "select count(a) from SavedQuery a where a.id = :id")
@NamedQuery(name = SavedQuery.NQ_COUNT, query = "select count(a) from SavedQuery a")
public class SavedQuery {
    @Generated
    public static final String NQ_DELETE_ALL = "SavedQuery.deleteAll";
    @Generated
    public static final String NQ_COUNT = "SavedQuery.count";
    @Generated
    public static final String NQ_DELETE = "SavedQuery.delete";
    @Generated
    public static final String NQ_GET_ALL = "SavedQuery.getAll";
    @Generated
    public static final String NQ_GET_USER = "SavedQuery.getUser";
    @Generated
    public static final String NQ_CHECK = "SavedQuery.check";
    @Generated
    public static final String NQ_FIND = "SavedQuery.find";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"title\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"title\" is illegal!")
    @Generated
    private String title;
    @Basic(optional = false)
    @Column(name = "view_name", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"viewName\" must not be null!")
    @Size(min = 1, max = 100, message = "Length of field \"viewName\" is illegal!")
    @Generated
    private String viewName;
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "data_obj", nullable = false, updatable = true, insertable = true)
    @Lob
    @NotNull(message = "Field \"dataObj\" must not be null!")
    @Generated
    private byte[] dataObj;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"user\" must not be null!")
    @Generated
    private User user;

    /**
     * Default constructor
     */
    @Generated
    public SavedQuery() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public SavedQuery(long id) {
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
     * @return the title
     */
    @Generated
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title the title to set
     */
    @Generated
    public void setTitle(String title) {
        this.title = title;
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
     * @return the data obj
     */
    @Generated
    public byte[] getDataObj() {
        return this.dataObj;
    }

    /**
     * @param dataObj the data obj to set
     */
    @Generated
    public void setDataObj(byte[] dataObj) {
        this.dataObj = dataObj;
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

        final var bean = (SavedQuery) obj;

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