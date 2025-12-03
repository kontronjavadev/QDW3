package com.kontron.qdw.domain.mv;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "aggregated_shipment_tab")
@NamedQuery(name = AggregatedShipment.NQ_DELETE_ALL, query = "delete from AggregatedShipment a")
@NamedQuery(name = AggregatedShipment.NQ_DELETE, query = "delete from AggregatedShipment a where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_GET_ALL, query = "select a from AggregatedShipment a")
@NamedQuery(name = AggregatedShipment.NQ_FIND, query = "select a from AggregatedShipment a where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_CHECK, query = "select count(a) from AggregatedShipment a where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_COUNT, query = "select count(a) from AggregatedShipment a")
public class AggregatedShipment {
    @Generated
    public static final String NQ_DELETE_ALL = "AggregatedShipment.deleteAll";
    @Generated
    public static final String NQ_COUNT = "AggregatedShipment.count";
    @Generated
    public static final String NQ_DELETE = "AggregatedShipment.delete";
    @Generated
    public static final String NQ_GET_ALL = "AggregatedShipment.getAll";
    @Generated
    public static final String NQ_CHECK = "AggregatedShipment.check";
    @Generated
    public static final String NQ_FIND = "AggregatedShipment.find";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedShipment() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public AggregatedShipment(long id) {
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

        final var bean = (AggregatedShipment) obj;

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