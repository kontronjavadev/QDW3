package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "movement_type_tab")
@NamedQuery(name = MovementType.NQ_DELETE_ALL, query = "delete from MovementType a")
@NamedQuery(name = MovementType.NQ_DELETE, query = "delete from MovementType a where a.id = :id")
@NamedQuery(name = MovementType.NQ_GET_ALL, query = "select a from MovementType a")
@NamedQuery(name = MovementType.NQ_FIND, query = "select a from MovementType a where a.id = :id")
@NamedQuery(name = MovementType.NQ_CHECK, query = "select count(a) from MovementType a where a.id = :id")
@NamedQuery(name = MovementType.NQ_COUNT, query = "select count(a) from MovementType a")
public class MovementType extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "MovementType.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MovementType.count";
    @Generated
    public static final String NQ_DELETE = "MovementType.delete";
    @Generated
    public static final String NQ_GET_ALL = "MovementType.getAll";
    @Generated
    public static final String NQ_CHECK = "MovementType.check";
    @Generated
    public static final String NQ_FIND = "MovementType.find";

    /**
     * Default constructor
     */
    @Generated
    public MovementType() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MovementType(long id) {
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

        final var bean = (MovementType) obj;

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