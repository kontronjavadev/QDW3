package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "plant_tab")
@NamedQuery(name = Plant.NQ_DELETE_ALL, query = "delete from Plant a")
@NamedQuery(name = Plant.NQ_DELETE, query = "delete from Plant a where a.code = :code")
@NamedQuery(name = Plant.NQ_GET_ALL, query = "select a from Plant a")
@NamedQuery(name = Plant.NQ_FIND, query = "select a from Plant a where a.code = :code")
@NamedQuery(name = Plant.NQ_CHECK, query = "select count(a) from Plant a where a.code = :code")
@NamedQuery(name = Plant.NQ_COUNT, query = "select count(a) from Plant a")
public class Plant extends AbstractFunctionalActiveEntity {
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
     * @param code
     */
    @Generated
    public Plant(String code) {
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

        final var bean = (Plant) obj;

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