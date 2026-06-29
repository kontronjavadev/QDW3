package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;
import java.util.Map;

import com.kontron.qdw.domain.base.listen.AbstractEntityWithIdCallbackListener;
import com.kontron.util.collection.CollectionUtil;

import net.sourceforge.jbizmo.commons.annotation.Generated;

@MappedSuperclass
@EntityListeners(AbstractEntityWithIdCallbackListener.class)
public class AbstractEntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    public AbstractEntityWithId() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public AbstractEntityWithId(long id) {
        this.id = id;
    }



    /**
     * @param <T> Entityklasse
     * @param entities Liste an Entities
     * @return Map an Entities nach {@link #getId()}
     */
    public static <T extends AbstractEntityWithId> Map<Long, T> asMap(List<T> entities) {
        return CollectionUtil.convertCollectionToMap(entities, T::getId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id = " + id + "]";
    }

    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final var bean = (AbstractEntityWithId) obj;

        return getId() == bean.getId();
    }

    @Generated
    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
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

}
