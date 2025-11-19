package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "country_tab")
@NamedQuery(name = Country.NQ_UK_FIND_BY_NAME, query = "select a from Country a where a.name = :name")
@NamedQuery(name = Country.NQ_UK_SEARCH_BY_NAME, query = "select a from Country a where a.name like :name")
@NamedQuery(name = Country.NQ_UK_EXISTS_BY_NAME, query = "select count(a) from Country a where a.name = :name")
@NamedQuery(name = Country.NQ_UK_EXISTS_BY_NAME_AND_CODE, query = "select count(a) from Country a where a.name = :name and a.code <> :code")
@NamedQuery(name = Country.NQ_DELETE_ALL, query = "delete from Country a")
@NamedQuery(name = Country.NQ_DELETE, query = "delete from Country a where a.code = :code")
@NamedQuery(name = Country.NQ_GET_ALL, query = "select a from Country a")
@NamedQuery(name = Country.NQ_FIND, query = "select a from Country a where a.code = :code")
@NamedQuery(name = Country.NQ_CHECK, query = "select count(a) from Country a where a.code = :code")
@NamedQuery(name = Country.NQ_COUNT, query = "select count(a) from Country a")
public class Country extends AbstractFunctionalActiveEntity {
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME = "Country.checkByName";
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME_AND_CODE = "Country.checkByNameAndCode";
    @Generated
    public static final String NQ_UK_FIND_BY_NAME = "Country.getByName";
    @Generated
    public static final String NQ_DELETE_ALL = "Country.deleteAll";
    @Generated
    public static final String NQ_COUNT = "Country.count";
    @Generated
    public static final String NQ_DELETE = "Country.delete";
    @Generated
    public static final String NQ_GET_ALL = "Country.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_NAME = "Country.findByName";
    @Generated
    public static final String NQ_CHECK = "Country.check";
    @Generated
    public static final String NQ_FIND = "Country.find";
    @Basic(optional = false)
    @Column(name = "name", nullable = false, updatable = true, insertable = true, length = 100, unique = true)
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
     * @param code
     */
    @Generated
    public Country(String code) {
        super(code);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param code
     * @param name
     */
    @Generated
    public Country(String code, String name) {
        super(code);

        this.name = name;
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

        if (getCode() == null) {
            if (bean.getCode() != null)
                return false;
        }
        else if (!getCode().equals(bean.getCode()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        // Return hash code of current date if primary key field is not yet set!
        if (getCode() == null)
            return new java.util.Date().hashCode();

        return getCode().hashCode();
    }

}