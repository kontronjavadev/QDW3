package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "user_tab")
@NamedQuery(name = User.NQ_UK_FIND_BY_NAME, query = "select a from User a where a.name = :name")
@NamedQuery(name = User.NQ_UK_SEARCH_BY_NAME, query = "select a from User a where a.name like :name")
@NamedQuery(name = User.NQ_UK_EXISTS_BY_NAME, query = "select count(a) from User a where a.name = :name")
@NamedQuery(name = User.NQ_UK_EXISTS_BY_NAME_AND_ID, query = "select count(a) from User a where a.name = :name and a.id <> :id")
@NamedQuery(name = User.NQ_GET_ROLES, query = "select b from User a join a.roles b where a.id = :id")
@NamedQuery(name = User.NQ_DELETE_ALL, query = "delete from User a")
@NamedQuery(name = User.NQ_DELETE, query = "delete from User a where a.id = :id")
@NamedQuery(name = User.NQ_GET_ALL, query = "select a from User a")
@NamedQuery(name = User.NQ_FIND, query = "select a from User a where a.id = :id")
@NamedQuery(name = User.NQ_CHECK, query = "select count(a) from User a where a.id = :id")
@NamedQuery(name = User.NQ_COUNT, query = "select count(a) from User a")
public class User extends AbstractEntityWithId {
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME = "User.checkByName";
    @Generated
    public static final String NQ_UK_FIND_BY_NAME = "User.getByName";
    @Generated
    public static final String NQ_GET_ROLES = "User.getRoles";
    @Generated
    public static final String NQ_DELETE_ALL = "User.deleteAll";
    @Generated
    public static final String NQ_COUNT = "User.count";
    @Generated
    public static final String NQ_DELETE = "User.delete";
    @Generated
    public static final String NQ_GET_ALL = "User.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_NAME = "User.findByName";
    @Generated
    public static final String NQ_CHECK = "User.check";
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME_AND_ID = "User.checkByNameAndId";
    @Generated
    public static final String NQ_FIND = "User.find";
    @Basic(optional = false)
    @Column(name = "name", nullable = false, updatable = true, insertable = true, length = 50, unique = true)
    @NotNull(message = "Field \"name\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"name\" is illegal!")
    @Generated
    private String name;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, updatable = true, insertable = true, length = 64)
    @NotNull(message = "Field \"password\" must not be null!")
    @Size(min = 1, max = 64, message = "Length of field \"password\" is illegal!")
    @Generated
    private String password;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"email\" must not be null!")
    @Size(min = 1, max = 100, message = "Length of field \"email\" is illegal!")
    @Generated
    private String email;
    @Basic(optional = false)
    @Column(name = "active", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles_tab", joinColumns = { @JoinColumn(name = "user_pk") }, inverseJoinColumns = { @JoinColumn(name = "role_pk") })
    @Generated
    private Collection<Role> roles = new ArrayList<>();
    public static final String NQ_UK_FIND_ACTIVE_BY_EMAIL = "User.getActiveByEmail";

    /**
     * Default constructor
     */
    @Generated
    public User() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public User(long id) {
        super(id);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param id
     * @param name
     */
    @Generated
    public User(long id, String name) {
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

    /**
     * @return the password
     */
    @Generated
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    @Generated
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return true if the active flag is set
     */
    @Generated
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param active the value of the active flag to set
     */
    @Generated
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return a collection of roles
     */
    @Generated
    public Collection<Role> getRoles() {
        return this.roles;
    }

    /**
     * @param roles the roles to set
     */
    @Generated
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
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

        final var bean = (User) obj;

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