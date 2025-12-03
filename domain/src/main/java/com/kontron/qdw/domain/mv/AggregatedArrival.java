package com.kontron.qdw.domain.mv;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "aggregated_arrival_tab")
@NamedQuery(name = AggregatedArrival.NQ_DELETE_ALL, query = "delete from AggregatedArrival a")
@NamedQuery(name = AggregatedArrival.NQ_DELETE, query = "delete from AggregatedArrival a where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_GET_ALL, query = "select a from AggregatedArrival a")
@NamedQuery(name = AggregatedArrival.NQ_FIND, query = "select a from AggregatedArrival a where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_CHECK, query = "select count(a) from AggregatedArrival a where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_COUNT, query = "select count(a) from AggregatedArrival a")
public class AggregatedArrival {
    @Generated
    public static final String NQ_DELETE_ALL = "AggregatedArrival.deleteAll";
    @Generated
    public static final String NQ_COUNT = "AggregatedArrival.count";
    @Generated
    public static final String NQ_DELETE = "AggregatedArrival.delete";
    @Generated
    public static final String NQ_GET_ALL = "AggregatedArrival.getAll";
    @Generated
    public static final String NQ_CHECK = "AggregatedArrival.check";
    @Generated
    public static final String NQ_FIND = "AggregatedArrival.find";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedArrival() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public AggregatedArrival(long id) {
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

        final var bean = (AggregatedArrival) obj;

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