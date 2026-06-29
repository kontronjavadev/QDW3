package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import com.kontron.qdw.domain.base.listen.AbstractTransactionalEntityCallbackListener;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@MappedSuperclass
@EntityListeners(AbstractTransactionalEntityCallbackListener.class)
public class AbstractTransactionalEntity {
    @Id
    @Column(name = "id")
    @NotNull(message = "Field \"id\" must not be null!")
    @Generated
    private long id;
    @Version
    @Basic(optional = false)
    @Column(name = "version", nullable = false, updatable = true, insertable = true)
    @Generated
    private int version;
    @Basic(optional = false)
    @Column(name = "creation_date", nullable = false, updatable = false, insertable = true)
    @NotNull(message = "Field \"creationDate\" must not be null!")
    @Generated
    private LocalDateTime creationDate;
    @Column(name = "last_update", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDateTime lastUpdate;

    /**
     * Default constructor
     */
    @Generated
    public AbstractTransactionalEntity() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public AbstractTransactionalEntity(long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    @Generated
    public long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    @Generated
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the version
     */
    @Generated
    public int getVersion() {
        return this.version;
    }

    /**
     * @param version the version to set
     */
    @Generated
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return the creation date
     */
    @Generated
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @param creationDate the creation date to set
     */
    @Generated
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the last update
     */
    @Generated
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * @param lastUpdate the last update to set
     */
    @Generated
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
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

        final var bean = (AbstractTransactionalEntity) obj;

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