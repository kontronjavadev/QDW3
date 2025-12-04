package com.kontron.qdw.domain.service;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "repair_task_tab")
@NamedQuery(name = RepairTask.NQ_GET_MAPPEDTO, query = "select b from RepairTask a join a.mappedTo b where a.code = :code")
@NamedQuery(name = RepairTask.NQ_DELETE_ALL, query = "delete from RepairTask a")
@NamedQuery(name = RepairTask.NQ_DELETE, query = "delete from RepairTask a where a.code = :code")
@NamedQuery(name = RepairTask.NQ_GET_ALL, query = "select a from RepairTask a")
@NamedQuery(name = RepairTask.NQ_FIND, query = "select a from RepairTask a where a.code = :code")
@NamedQuery(name = RepairTask.NQ_CHECK, query = "select count(a) from RepairTask a where a.code = :code")
@NamedQuery(name = RepairTask.NQ_COUNT, query = "select count(a) from RepairTask a")
public class RepairTask extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "RepairTask.deleteAll";
    @Generated
    public static final String NQ_COUNT = "RepairTask.count";
    @Generated
    public static final String NQ_DELETE = "RepairTask.delete";
    @Generated
    public static final String NQ_GET_ALL = "RepairTask.getAll";
    @Generated
    public static final String NQ_GET_MAPPEDTO = "RepairTask.getMappedTo";
    @Generated
    public static final String NQ_CHECK = "RepairTask.check";
    @Generated
    public static final String NQ_FIND = "RepairTask.find";
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "mapped_to", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairTask mappedTo;

    /**
     * Default constructor
     */
    @Generated
    public RepairTask() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public RepairTask(String code) {
        super(code);
    }

    /**
     * @return the repair task
     */
    @Generated
    public RepairTask getMappedTo() {
        return this.mappedTo;
    }

    /**
     * @param mappedTo the repair task to set
     */
    @Generated
    public void setMappedTo(RepairTask mappedTo) {
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

        final var bean = (RepairTask) obj;

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