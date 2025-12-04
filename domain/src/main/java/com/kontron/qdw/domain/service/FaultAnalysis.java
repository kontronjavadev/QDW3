package com.kontron.qdw.domain.service;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "fault_analysis_tab")
@NamedQuery(name = FaultAnalysis.NQ_GET_MAPPEDTO, query = "select b from FaultAnalysis a join a.mappedTo b where a.code = :code")
@NamedQuery(name = FaultAnalysis.NQ_DELETE_ALL, query = "delete from FaultAnalysis a")
@NamedQuery(name = FaultAnalysis.NQ_DELETE, query = "delete from FaultAnalysis a where a.code = :code")
@NamedQuery(name = FaultAnalysis.NQ_GET_ALL, query = "select a from FaultAnalysis a")
@NamedQuery(name = FaultAnalysis.NQ_FIND, query = "select a from FaultAnalysis a where a.code = :code")
@NamedQuery(name = FaultAnalysis.NQ_CHECK, query = "select count(a) from FaultAnalysis a where a.code = :code")
@NamedQuery(name = FaultAnalysis.NQ_COUNT, query = "select count(a) from FaultAnalysis a")
public class FaultAnalysis extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "FaultAnalysis.deleteAll";
    @Generated
    public static final String NQ_COUNT = "FaultAnalysis.count";
    @Generated
    public static final String NQ_DELETE = "FaultAnalysis.delete";
    @Generated
    public static final String NQ_GET_ALL = "FaultAnalysis.getAll";
    @Generated
    public static final String NQ_GET_MAPPEDTO = "FaultAnalysis.getMappedTo";
    @Generated
    public static final String NQ_CHECK = "FaultAnalysis.check";
    @Generated
    public static final String NQ_FIND = "FaultAnalysis.find";
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "mapped_to", referencedColumnName = "code", nullable = true)
    @Generated
    private FaultAnalysis mappedTo;

    /**
     * Default constructor
     */
    @Generated
    public FaultAnalysis() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public FaultAnalysis(String code) {
        super(code);
    }

    /**
     * @return the fault analysis
     */
    @Generated
    public FaultAnalysis getMappedTo() {
        return this.mappedTo;
    }

    /**
     * @param mappedTo the fault analysis to set
     */
    @Generated
    public void setMappedTo(FaultAnalysis mappedTo) {
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

        final var bean = (FaultAnalysis) obj;

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