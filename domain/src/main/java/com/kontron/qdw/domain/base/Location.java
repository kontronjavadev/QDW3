package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "location_tab")
@NamedQuery(name = Location.NQ_DELETE_ALL, query = "delete from Location a")
@NamedQuery(name = Location.NQ_DELETE, query = "delete from Location a where a.id = :id")
@NamedQuery(name = Location.NQ_GET_ALL, query = "select a from Location a")
@NamedQuery(name = Location.NQ_FIND, query = "select a from Location a where a.id = :id")
@NamedQuery(name = Location.NQ_CHECK, query = "select count(a) from Location a where a.id = :id")
@NamedQuery(name = Location.NQ_COUNT, query = "select count(a) from Location a")
public class Location extends AbstractEntityWithId {
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
     * @param id
     */
    @Generated
    public Location(long id) {
        super(id);
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