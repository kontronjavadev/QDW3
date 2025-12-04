package com.kontron.qdw.domain.service;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "repair_state_tab")
@NamedQuery(name = RepairState.NQ_UK_FIND_BY_NAME, query = "select a from RepairState a where a.name = :name")
@NamedQuery(name = RepairState.NQ_UK_SEARCH_BY_NAME, query = "select a from RepairState a where a.name like :name")
@NamedQuery(name = RepairState.NQ_UK_EXISTS_BY_NAME, query = "select count(a) from RepairState a where a.name = :name")
@NamedQuery(name = RepairState.NQ_UK_EXISTS_BY_NAME_AND_CODE, query = "select count(a) from RepairState a where a.name = :name and a.code <> :code")
@NamedQuery(name = RepairState.NQ_DELETE_ALL, query = "delete from RepairState a")
@NamedQuery(name = RepairState.NQ_DELETE, query = "delete from RepairState a where a.code = :code")
@NamedQuery(name = RepairState.NQ_GET_ALL, query = "select a from RepairState a")
@NamedQuery(name = RepairState.NQ_FIND, query = "select a from RepairState a where a.code = :code")
@NamedQuery(name = RepairState.NQ_CHECK, query = "select count(a) from RepairState a where a.code = :code")
@NamedQuery(name = RepairState.NQ_COUNT, query = "select count(a) from RepairState a")
public class RepairState extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME = "RepairState.checkByName";
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME_AND_CODE = "RepairState.checkByNameAndCode";
    @Generated
    public static final String NQ_UK_FIND_BY_NAME = "RepairState.getByName";
    @Generated
    public static final String NQ_DELETE_ALL = "RepairState.deleteAll";
    @Generated
    public static final String NQ_COUNT = "RepairState.count";
    @Generated
    public static final String NQ_DELETE = "RepairState.delete";
    @Generated
    public static final String NQ_GET_ALL = "RepairState.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_NAME = "RepairState.findByName";
    @Generated
    public static final String NQ_CHECK = "RepairState.check";
    @Generated
    public static final String NQ_FIND = "RepairState.find";
    @Basic(optional = false)
    @Column(name = "name", nullable = false, updatable = true, insertable = true, length = 50, unique = true)
    @NotNull(message = "Field \"name\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"name\" is illegal!")
    @Generated
    private String name;

    /**
     * Default constructor
     */
    @Generated
    public RepairState() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public RepairState(String code) {
        super(code);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param code
     * @param name
     */
    @Generated
    public RepairState(String code, String name) {
        super(code);

        this.name = name;
    }

    /**
     * @return the name
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    @Generated
    public void setName(String name) {
        this.name = name;
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

        final var bean = (RepairState) obj;

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