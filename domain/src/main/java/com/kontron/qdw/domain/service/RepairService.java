package com.kontron.qdw.domain.service;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "repair_service_tab")
@NamedQuery(name = RepairService.NQ_UK_FIND_BY_NAME, query = "select a from RepairService a where a.name = :name")
@NamedQuery(name = RepairService.NQ_UK_SEARCH_BY_NAME, query = "select a from RepairService a where a.name like :name")
@NamedQuery(name = RepairService.NQ_UK_EXISTS_BY_NAME, query = "select count(a) from RepairService a where a.name = :name")
@NamedQuery(name = RepairService.NQ_UK_EXISTS_BY_NAME_AND_CODE, query = "select count(a) from RepairService a where a.name = :name and a.code <> :code")
@NamedQuery(name = RepairService.NQ_DELETE_ALL, query = "delete from RepairService a")
@NamedQuery(name = RepairService.NQ_DELETE, query = "delete from RepairService a where a.code = :code")
@NamedQuery(name = RepairService.NQ_GET_ALL, query = "select a from RepairService a")
@NamedQuery(name = RepairService.NQ_FIND, query = "select a from RepairService a where a.code = :code")
@NamedQuery(name = RepairService.NQ_CHECK, query = "select count(a) from RepairService a where a.code = :code")
@NamedQuery(name = RepairService.NQ_COUNT, query = "select count(a) from RepairService a")
public class RepairService extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME = "RepairService.checkByName";
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME_AND_CODE = "RepairService.checkByNameAndCode";
    @Generated
    public static final String NQ_UK_FIND_BY_NAME = "RepairService.getByName";
    @Generated
    public static final String NQ_DELETE_ALL = "RepairService.deleteAll";
    @Generated
    public static final String NQ_COUNT = "RepairService.count";
    @Generated
    public static final String NQ_DELETE = "RepairService.delete";
    @Generated
    public static final String NQ_GET_ALL = "RepairService.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_NAME = "RepairService.findByName";
    @Generated
    public static final String NQ_CHECK = "RepairService.check";
    @Generated
    public static final String NQ_FIND = "RepairService.find";
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
    public RepairService() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public RepairService(String code) {
        super(code);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param code
     * @param name
     */
    @Generated
    public RepairService(String code, String name) {
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

        final var bean = (RepairService) obj;

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