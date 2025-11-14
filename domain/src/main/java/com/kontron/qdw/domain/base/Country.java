package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "country_tab")
@NamedQuery(name = Country.NQ_DELETE_ALL, query = "delete from Country a")
@NamedQuery(name = Country.NQ_DELETE, query = "delete from Country a where a.id = :id")
@NamedQuery(name = Country.NQ_GET_ALL, query = "select a from Country a")
@NamedQuery(name = Country.NQ_FIND, query = "select a from Country a where a.id = :id")
@NamedQuery(name = Country.NQ_CHECK, query = "select count(a) from Country a where a.id = :id")
@NamedQuery(name = Country.NQ_COUNT, query = "select count(a) from Country a")
public class Country extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "Country.deleteAll";
    @Generated
    public static final String NQ_COUNT = "Country.count";
    @Generated
    public static final String NQ_DELETE = "Country.delete";
    @Generated
    public static final String NQ_GET_ALL = "Country.getAll";
    @Generated
    public static final String NQ_CHECK = "Country.check";
    @Generated
    public static final String NQ_FIND = "Country.find";
    @Basic(optional = false)
    @Column(name = "name", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"name\" must not be null!")
    @Size(max = 100, message = "Length of field \"name\" is illegal!")
    @Generated
    private String name;

    /**
     * Default constructor
     */
    @Generated
    public Country() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public Country(long id) {
        super(id);
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

        final var bean = (Country) obj;

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