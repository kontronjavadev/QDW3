package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "location_tab")
@NamedQuery(name = Location.NQ_DELETE_ALL, query = "delete from Location a")
@NamedQuery(name = Location.NQ_DELETE, query = "delete from Location a where a.code = :code")
@NamedQuery(name = Location.NQ_GET_ALL, query = "select a from Location a")
@NamedQuery(name = Location.NQ_FIND, query = "select a from Location a where a.code = :code")
@NamedQuery(name = Location.NQ_CHECK, query = "select count(a) from Location a where a.code = :code")
@NamedQuery(name = Location.NQ_COUNT, query = "select count(a) from Location a")
public class Location extends AbstractFunctionalActiveEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "Location.deleteAll";
    @Generated
    public static final String NQ_COUNT = "Location.count";
    @Generated
    public static final String NQ_DELETE = "Location.delete";
    @Generated
    public static final String NQ_GET_ALL = "Location.getAll";
    @Generated
    public static final String NQ_CHECK = "Location.check";
    @Generated
    public static final String NQ_FIND = "Location.find";

    /**
     * Default constructor
     */
    @Generated
    public Location() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public Location(String code) {
        super(code);
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

        final var bean = (Location) obj;

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