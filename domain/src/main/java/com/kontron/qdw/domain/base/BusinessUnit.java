package com.kontron.qdw.domain.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "business_unit_tab")
@NamedQuery(name = BusinessUnit.NQ_DELETE_ALL, query = "delete from BusinessUnit a")
@NamedQuery(name = BusinessUnit.NQ_DELETE, query = "delete from BusinessUnit a where a.code = :code")
@NamedQuery(name = BusinessUnit.NQ_GET_ALL, query = "select a from BusinessUnit a")
@NamedQuery(name = BusinessUnit.NQ_FIND, query = "select a from BusinessUnit a where a.code = :code")
@NamedQuery(name = BusinessUnit.NQ_CHECK, query = "select count(a) from BusinessUnit a where a.code = :code")
@NamedQuery(name = BusinessUnit.NQ_COUNT, query = "select count(a) from BusinessUnit a")
public class BusinessUnit extends AbstractFunctionalActiveEntity {
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
     * @param code
     */
    @Generated
    public BusinessUnit(String code) {
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

        final var bean = (BusinessUnit) obj;

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