package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "role_tab")
@NamedQuery(name = Role.NQ_UK_FIND_BY_NAME, query = "select a from Role a where a.name = :name")
@NamedQuery(name = Role.NQ_UK_SEARCH_BY_NAME, query = "select a from Role a where a.name like :name")
@NamedQuery(name = Role.NQ_UK_EXISTS_BY_NAME, query = "select count(a) from Role a where a.name = :name")
@NamedQuery(name = Role.NQ_UK_EXISTS_BY_NAME_AND_ID, query = "select count(a) from Role a where a.name = :name and a.id <> :id")
@NamedQuery(name = Role.NQ_DELETE_ALL, query = "delete from Role a")
@NamedQuery(name = Role.NQ_DELETE, query = "delete from Role a where a.id = :id")
@NamedQuery(name = Role.NQ_GET_ALL, query = "select a from Role a")
@NamedQuery(name = Role.NQ_FIND, query = "select a from Role a where a.id = :id")
@NamedQuery(name = Role.NQ_CHECK, query = "select count(a) from Role a where a.id = :id")
@NamedQuery(name = Role.NQ_COUNT, query = "select count(a) from Role a")
public class Role extends AbstractEntityWithId {
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME = "Role.checkByName";
    @Generated
    public static final String NQ_UK_FIND_BY_NAME = "Role.getByName";
    @Generated
    public static final String NQ_DELETE_ALL = "Role.deleteAll";
    @Generated
    public static final String NQ_COUNT = "Role.count";
    @Generated
    public static final String NQ_DELETE = "Role.delete";
    @Generated
    public static final String NQ_GET_ALL = "Role.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_NAME = "Role.findByName";
    @Generated
    public static final String NQ_CHECK = "Role.check";
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME_AND_ID = "Role.checkByNameAndId";
    @Generated
    public static final String NQ_FIND = "Role.find";
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
    public Role() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public Role(long id) {
        super(id);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param id
     * @param name
     */
    @Generated
    public Role(long id, String name) {
        super(id);

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

        final var bean = (Role) obj;

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