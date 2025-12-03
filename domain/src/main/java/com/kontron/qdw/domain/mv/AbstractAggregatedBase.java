package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@MappedSuperclass
public class AbstractAggregatedBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;
    @Basic(optional = false)
    @Column(name = "year", nullable = false, updatable = true, insertable = true)
    @Generated
    private int year;
    @Basic(optional = false)
    @Column(name = "month", nullable = false, updatable = true, insertable = true)
    @Generated
    private int month;

    /**
     * Default constructor
     */
    @Generated
    public AbstractAggregatedBase() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public AbstractAggregatedBase(long id) {
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
     * @return the year
     */
    @Generated
    public int getYear() {
        return this.year;
    }

    /**
     * @param year the year to set
     */
    @Generated
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    @Generated
    public int getMonth() {
        return this.month;
    }

    /**
     * @param month the month to set
     */
    @Generated
    public void setMonth(int month) {
        this.month = month;
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

        final var bean = (AbstractAggregatedBase) obj;

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