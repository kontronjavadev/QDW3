package com.kontron.qdw.domain.service;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "repair_error_code_tab")
@NamedQuery(name = RepairErrorCode.NQ_UK_FIND_BY_NAME, query = "select a from RepairErrorCode a where a.name = :name")
@NamedQuery(name = RepairErrorCode.NQ_UK_SEARCH_BY_NAME, query = "select a from RepairErrorCode a where a.name like :name")
@NamedQuery(name = RepairErrorCode.NQ_UK_EXISTS_BY_NAME, query = "select count(a) from RepairErrorCode a where a.name = :name")
@NamedQuery(name = RepairErrorCode.NQ_UK_EXISTS_BY_NAME_AND_CODE, query = "select count(a) from RepairErrorCode a where a.name = :name and a.code <> :code")
@NamedQuery(name = RepairErrorCode.NQ_GET_MAPPEDTO, query = "select b from RepairErrorCode a join a.mappedTo b where a.code = :code")
@NamedQuery(name = RepairErrorCode.NQ_DELETE_ALL, query = "delete from RepairErrorCode a")
@NamedQuery(name = RepairErrorCode.NQ_DELETE, query = "delete from RepairErrorCode a where a.code = :code")
@NamedQuery(name = RepairErrorCode.NQ_GET_ALL, query = "select a from RepairErrorCode a")
@NamedQuery(name = RepairErrorCode.NQ_FIND, query = "select a from RepairErrorCode a where a.code = :code")
@NamedQuery(name = RepairErrorCode.NQ_CHECK, query = "select count(a) from RepairErrorCode a where a.code = :code")
@NamedQuery(name = RepairErrorCode.NQ_COUNT, query = "select count(a) from RepairErrorCode a")
public class RepairErrorCode extends AbstractFunctionalActiveEntity {
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME = "RepairErrorCode.checkByName";
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME_AND_CODE = "RepairErrorCode.checkByNameAndCode";
    @Generated
    public static final String NQ_UK_FIND_BY_NAME = "RepairErrorCode.getByName";
    @Generated
    public static final String NQ_DELETE_ALL = "RepairErrorCode.deleteAll";
    @Generated
    public static final String NQ_COUNT = "RepairErrorCode.count";
    @Generated
    public static final String NQ_DELETE = "RepairErrorCode.delete";
    @Generated
    public static final String NQ_GET_ALL = "RepairErrorCode.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_NAME = "RepairErrorCode.findByName";
    @Generated
    public static final String NQ_GET_MAPPEDTO = "RepairErrorCode.getMappedTo";
    @Generated
    public static final String NQ_CHECK = "RepairErrorCode.check";
    @Generated
    public static final String NQ_FIND = "RepairErrorCode.find";
    @Basic(optional = false)
    @Column(name = "name", nullable = false, updatable = true, insertable = true, length = 150, unique = true)
    @NotNull(message = "Field \"name\" must not be null!")
    @Size(min = 1, max = 150, message = "Length of field \"name\" is illegal!")
    @Generated
    private String name;
    @Column(name = "group_name", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"groupName\" is illegal!")
    @Generated
    private String groupName;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "mapped_to", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairErrorCode mappedTo;

    /**
     * Default constructor
     */
    @Generated
    public RepairErrorCode() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public RepairErrorCode(String code) {
        super(code);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param code
     * @param name
     */
    @Generated
    public RepairErrorCode(String code, String name) {
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

    /**
     * @return the group name
     */
    @Generated
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * @param groupName the group name to set
     */
    @Generated
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the repair error code
     */
    @Generated
    public RepairErrorCode getMappedTo() {
        return this.mappedTo;
    }

    /**
     * @param mappedTo the repair error code to set
     */
    @Generated
    public void setMappedTo(RepairErrorCode mappedTo) {
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

        final var bean = (RepairErrorCode) obj;

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