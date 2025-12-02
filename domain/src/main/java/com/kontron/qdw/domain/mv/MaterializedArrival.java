package com.kontron.qdw.domain.mv;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "materialized_arrival_tab")
@NamedQuery(name = MaterializedArrival.NQ_DELETE_ALL, query = "delete from MaterializedArrival a")
@NamedQuery(name = MaterializedArrival.NQ_DELETE, query = "delete from MaterializedArrival a where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_GET_ALL, query = "select a from MaterializedArrival a")
@NamedQuery(name = MaterializedArrival.NQ_FIND, query = "select a from MaterializedArrival a where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_CHECK, query = "select count(a) from MaterializedArrival a where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_COUNT, query = "select count(a) from MaterializedArrival a")
public class MaterializedArrival extends MaterializedEntitiy {
    @Generated
    public static final String NQ_DELETE_ALL = "MaterializedArrival.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MaterializedArrival.count";
    @Generated
    public static final String NQ_DELETE = "MaterializedArrival.delete";
    @Generated
    public static final String NQ_GET_ALL = "MaterializedArrival.getAll";
    @Generated
    public static final String NQ_CHECK = "MaterializedArrival.check";
    @Generated
    public static final String NQ_FIND = "MaterializedArrival.find";

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrival() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MaterializedArrival(long id) {
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

        final var bean = (MaterializedArrival) obj;

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