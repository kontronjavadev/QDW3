package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "vertical_sector_tab")
@NamedQuery(name = VerticalSector.NQ_GET_BUSINESSUNIT, query = "select b from VerticalSector a join a.businessUnit b where a.id = :id")
@NamedQuery(name = VerticalSector.NQ_DELETE_ALL, query = "delete from VerticalSector a")
@NamedQuery(name = VerticalSector.NQ_DELETE, query = "delete from VerticalSector a where a.id = :id")
@NamedQuery(name = VerticalSector.NQ_GET_ALL, query = "select a from VerticalSector a")
@NamedQuery(name = VerticalSector.NQ_FIND, query = "select a from VerticalSector a where a.id = :id")
@NamedQuery(name = VerticalSector.NQ_CHECK, query = "select count(a) from VerticalSector a where a.id = :id")
@NamedQuery(name = VerticalSector.NQ_COUNT, query = "select count(a) from VerticalSector a")
public class VerticalSector extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "VerticalSector.deleteAll";
    @Generated
    public static final String NQ_COUNT = "VerticalSector.count";
    @Generated
    public static final String NQ_DELETE = "VerticalSector.delete";
    @Generated
    public static final String NQ_GET_BUSINESSUNIT = "VerticalSector.getBusinessUnit";
    @Generated
    public static final String NQ_GET_ALL = "VerticalSector.getAll";
    @Generated
    public static final String NQ_CHECK = "VerticalSector.check";
    @Generated
    public static final String NQ_FIND = "VerticalSector.find";
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "business_unit", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"businessUnit\" must not be null!")
    @Generated
    private BusinessUnit businessUnit;

    /**
     * Default constructor
     */
    @Generated
    public VerticalSector() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public VerticalSector(long id) {
        super(id);
    }

    /**
     * @return the business unit
     */
    @Generated
    public BusinessUnit getBusinessUnit() {
        return this.businessUnit;
    }

    /**
     * @param businessUnit the business unit to set
     */
    @Generated
    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
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

        final var bean = (VerticalSector) obj;

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