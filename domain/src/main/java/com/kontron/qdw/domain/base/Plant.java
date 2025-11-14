package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "plant_tab")
@NamedQuery(name = Plant.NQ_DELETE_ALL, query = "delete from Plant a")
@NamedQuery(name = Plant.NQ_DELETE, query = "delete from Plant a where a.id = :id")
@NamedQuery(name = Plant.NQ_GET_ALL, query = "select a from Plant a")
@NamedQuery(name = Plant.NQ_FIND, query = "select a from Plant a where a.id = :id")
@NamedQuery(name = Plant.NQ_CHECK, query = "select count(a) from Plant a where a.id = :id")
@NamedQuery(name = Plant.NQ_COUNT, query = "select count(a) from Plant a")
public class Plant extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "Plant.deleteAll";
    @Generated
    public static final String NQ_COUNT = "Plant.count";
    @Generated
    public static final String NQ_DELETE = "Plant.delete";
    @Generated
    public static final String NQ_GET_ALL = "Plant.getAll";
    @Generated
    public static final String NQ_CHECK = "Plant.check";
    @Generated
    public static final String NQ_FIND = "Plant.find";

    /**
     * Default constructor
     */
    @Generated
    public Plant() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public Plant(long id) {
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

        final var bean = (Plant) obj;

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