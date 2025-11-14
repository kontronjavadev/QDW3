package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "business_unit_tab")
@NamedQuery(name = BusinessUnit.NQ_DELETE_ALL, query = "delete from BusinessUnit a")
@NamedQuery(name = BusinessUnit.NQ_DELETE, query = "delete from BusinessUnit a where a.id = :id")
@NamedQuery(name = BusinessUnit.NQ_GET_ALL, query = "select a from BusinessUnit a")
@NamedQuery(name = BusinessUnit.NQ_FIND, query = "select a from BusinessUnit a where a.id = :id")
@NamedQuery(name = BusinessUnit.NQ_CHECK, query = "select count(a) from BusinessUnit a where a.id = :id")
@NamedQuery(name = BusinessUnit.NQ_COUNT, query = "select count(a) from BusinessUnit a")
public class BusinessUnit extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "BusinessUnit.deleteAll";
    @Generated
    public static final String NQ_COUNT = "BusinessUnit.count";
    @Generated
    public static final String NQ_DELETE = "BusinessUnit.delete";
    @Generated
    public static final String NQ_GET_ALL = "BusinessUnit.getAll";
    @Generated
    public static final String NQ_CHECK = "BusinessUnit.check";
    @Generated
    public static final String NQ_FIND = "BusinessUnit.find";

    /**
     * Default constructor
     */
    @Generated
    public BusinessUnit() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public BusinessUnit(long id) {
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

        final var bean = (BusinessUnit) obj;

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