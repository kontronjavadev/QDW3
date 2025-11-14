package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "movement_type_tab")
@NamedQuery(name = MovementType.NQ_DELETE_ALL, query = "delete from MovementType a")
@NamedQuery(name = MovementType.NQ_DELETE, query = "delete from MovementType a where a.code = :code")
@NamedQuery(name = MovementType.NQ_GET_ALL, query = "select a from MovementType a")
@NamedQuery(name = MovementType.NQ_FIND, query = "select a from MovementType a where a.code = :code")
@NamedQuery(name = MovementType.NQ_CHECK, query = "select count(a) from MovementType a where a.code = :code")
@NamedQuery(name = MovementType.NQ_COUNT, query = "select count(a) from MovementType a")
public class MovementType extends AbstractFunctionalActiveEntity {
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
     * @param code
     */
    @Generated
    public MovementType(String code) {
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

        final var bean = (MovementType) obj;

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