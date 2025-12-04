package com.kontron.qdw.domain.service;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "repair_location_tab")
@NamedQuery(name = RepairLocation.NQ_DELETE_ALL, query = "delete from RepairLocation a")
@NamedQuery(name = RepairLocation.NQ_DELETE, query = "delete from RepairLocation a where a.code = :code")
@NamedQuery(name = RepairLocation.NQ_GET_ALL, query = "select a from RepairLocation a")
@NamedQuery(name = RepairLocation.NQ_FIND, query = "select a from RepairLocation a where a.code = :code")
@NamedQuery(name = RepairLocation.NQ_CHECK, query = "select count(a) from RepairLocation a where a.code = :code")
@NamedQuery(name = RepairLocation.NQ_COUNT, query = "select count(a) from RepairLocation a")
public class RepairLocation extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "RepairLocation.deleteAll";
    @Generated
    public static final String NQ_COUNT = "RepairLocation.count";
    @Generated
    public static final String NQ_DELETE = "RepairLocation.delete";
    @Generated
    public static final String NQ_GET_ALL = "RepairLocation.getAll";
    @Generated
    public static final String NQ_CHECK = "RepairLocation.check";
    @Generated
    public static final String NQ_FIND = "RepairLocation.find";

    /**
     * Default constructor
     */
    @Generated
    public RepairLocation() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public RepairLocation(String code) {
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

        final var bean = (RepairLocation) obj;

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