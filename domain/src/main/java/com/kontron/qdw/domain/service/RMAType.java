package com.kontron.qdw.domain.service;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "rma_type_tab")
@NamedQuery(name = RMAType.NQ_GET_MAPPEDTO, query = "select b from RMAType a join a.mappedTo b where a.code = :code")
@NamedQuery(name = RMAType.NQ_DELETE_ALL, query = "delete from RMAType a")
@NamedQuery(name = RMAType.NQ_DELETE, query = "delete from RMAType a where a.code = :code")
@NamedQuery(name = RMAType.NQ_GET_ALL, query = "select a from RMAType a")
@NamedQuery(name = RMAType.NQ_FIND, query = "select a from RMAType a where a.code = :code")
@NamedQuery(name = RMAType.NQ_CHECK, query = "select count(a) from RMAType a where a.code = :code")
@NamedQuery(name = RMAType.NQ_COUNT, query = "select count(a) from RMAType a")
public class RMAType extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "RMAType.deleteAll";
    @Generated
    public static final String NQ_COUNT = "RMAType.count";
    @Generated
    public static final String NQ_DELETE = "RMAType.delete";
    @Generated
    public static final String NQ_GET_ALL = "RMAType.getAll";
    @Generated
    public static final String NQ_GET_MAPPEDTO = "RMAType.getMappedTo";
    @Generated
    public static final String NQ_CHECK = "RMAType.check";
    @Generated
    public static final String NQ_FIND = "RMAType.find";
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "mapped_to", referencedColumnName = "code", nullable = true)
    @Generated
    private RMAType mappedTo;

    /**
     * Default constructor
     */
    @Generated
    public RMAType() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public RMAType(String code) {
        super(code);
    }

    /**
     * @return the RMA type
     */
    @Generated
    public RMAType getMappedTo() {
        return this.mappedTo;
    }

    /**
     * @param mappedTo the RMA type to set
     */
    @Generated
    public void setMappedTo(RMAType mappedTo) {
        this.mappedTo = mappedTo;
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

        final var bean = (RMAType) obj;

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