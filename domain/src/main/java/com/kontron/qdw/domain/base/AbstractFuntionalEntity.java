package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import com.kontron.qdw.domain.base.listen.AbstractFuntionalEntityCallbackListener;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@MappedSuperclass
@EntityListeners(AbstractFuntionalEntityCallbackListener.class)
public class AbstractFuntionalEntity {
    @Id
    @Column(name = "code")
    @NotNull(message = "Field \"code\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"code\" is illegal!")
    @Generated
    private String code;
    @Basic(optional = false)
    @Column(name = "short_text", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"shortText\" must not be null!")
    @Size(max = 100, message = "Length of field \"shortText\" is illegal!")
    @Generated
    private String shortText;
    @Column(name = "comment", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"comment\" is illegal!")
    @Generated
    private String comment;
    @Basic(optional = false)
    @Column(name = "creation_date", nullable = false, updatable = false, insertable = true)
    @NotNull(message = "Field \"creationDate\" must not be null!")
    @Generated
    private LocalDateTime creationDate;
    @Column(name = "last_update", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDateTime lastUpdate;
    @Version
    @Basic(optional = false)
    @Column(name = "version", nullable = false, updatable = true, insertable = true)
    @Generated
    private int version;

    /**
     * Default constructor
     */
    @Generated
    public AbstractFuntionalEntity() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public AbstractFuntionalEntity(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * @param code the code to set
     */
    @Generated
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the short text
     */
    @Generated
    public String getShortText() {
        return this.shortText;
    }

    /**
     * @param shortText the short text to set
     */
    @Generated
    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    /**
     * @return the comment
     */
    @Generated
    public String getComment() {
        return this.comment;
    }

    /**
     * @param comment the comment to set
     */
    @Generated
    public void setComment(String comment) {
        this.comment = comment;
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

        final var bean = (AbstractFuntionalEntity) obj;

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