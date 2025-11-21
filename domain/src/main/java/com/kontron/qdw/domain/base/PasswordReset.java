package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.jpa.converter.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "password_reset_tab")
@NamedQuery(name = PasswordReset.NQ_UK_FIND_BY_USER, query = "select a from PasswordReset a where a.user.id = :user")
@NamedQuery(name = PasswordReset.NQ_UK_EXISTS_BY_USER, query = "select count(a) from PasswordReset a where a.user.id = :user")
@NamedQuery(name = PasswordReset.NQ_UK_EXISTS_BY_USER_AND_ID, query = "select count(a) from PasswordReset a where a.user.id = :user and a.id <> :id")
@NamedQuery(name = PasswordReset.NQ_UK_FIND_BY_UUID, query = "select a from PasswordReset a where a.uuid = :uuid")
@NamedQuery(name = PasswordReset.NQ_UK_EXISTS_BY_UUID, query = "select count(a) from PasswordReset a where a.uuid = :uuid")
@NamedQuery(name = PasswordReset.NQ_UK_EXISTS_BY_UUID_AND_ID, query = "select count(a) from PasswordReset a where a.uuid = :uuid and a.id <> :id")
@NamedQuery(name = PasswordReset.NQ_GET_USER, query = "select b from PasswordReset a join a.user b where a.id = :id")
@NamedQuery(name = PasswordReset.NQ_DELETE_ALL, query = "delete from PasswordReset a")
@NamedQuery(name = PasswordReset.NQ_DELETE, query = "delete from PasswordReset a where a.id = :id")
@NamedQuery(name = PasswordReset.NQ_GET_ALL, query = "select a from PasswordReset a")
@NamedQuery(name = PasswordReset.NQ_FIND, query = "select a from PasswordReset a where a.id = :id")
@NamedQuery(name = PasswordReset.NQ_CHECK, query = "select count(a) from PasswordReset a where a.id = :id")
@NamedQuery(name = PasswordReset.NQ_COUNT, query = "select count(a) from PasswordReset a")
public class PasswordReset extends AbstractEntityWithId {
    @Generated
    public static final String NQ_UK_EXISTS_BY_UUID_AND_ID = "PasswordReset.checkByUuidAndId";
    @Generated
    public static final String NQ_UK_EXISTS_BY_USER = "PasswordReset.checkByUser";
    @Generated
    public static final String NQ_DELETE_ALL = "PasswordReset.deleteAll";
    @Generated
    public static final String NQ_UK_EXISTS_BY_USER_AND_ID = "PasswordReset.checkByUser_And_Id";
    @Generated
    public static final String NQ_DELETE = "PasswordReset.delete";
    @Generated
    public static final String NQ_UK_FIND_BY_UUID = "PasswordReset.getByUuid";
    @Generated
    public static final String NQ_GET_ALL = "PasswordReset.getAll";
    @Generated
    public static final String NQ_CHECK = "PasswordReset.check";
    @Generated
    public static final String NQ_FIND = "PasswordReset.find";
    @Generated
    public static final String NQ_UK_EXISTS_BY_UUID = "PasswordReset.checkByUuid";
    @Generated
    public static final String NQ_COUNT = "PasswordReset.count";
    @Generated
    public static final String NQ_UK_FIND_BY_USER = "PasswordReset.getByUser";
    @Generated
    public static final String NQ_GET_USER = "PasswordReset.getUser";
    @Basic(optional = false)
    @Column(name = "uuid", nullable = false, updatable = true, insertable = true, unique = true)
    @Convert(converter = UUIDStringConverter.class)
    @NotNull(message = "Field \"uuid\" must not be null!")
    @Generated
    private UUID uuid;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"user\" must not be null!")
    @Generated
    private User user;

    /**
     * Default constructor
     */
    @Generated
    public PasswordReset() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public PasswordReset(long id) {
        super(id);
    }

    /**
     * @return the uuid
     */
    @Generated
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    @Generated
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the user
     */
    @Generated
    public User getUser() {
        return this.user;
    }

    /**
     * @param user the user to set
     */
    @Generated
    public void setUser(User user) {
        this.user = user;
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

        final var bean = (PasswordReset) obj;

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