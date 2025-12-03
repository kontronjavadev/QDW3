package com.kontron.qdw.domain.mv;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "aggregated_shipment_arrival_tab")
@NamedQuery(name = AggregatedShipmentArrival.NQ_DELETE_ALL, query = "delete from AggregatedShipmentArrival a")
@NamedQuery(name = AggregatedShipmentArrival.NQ_DELETE, query = "delete from AggregatedShipmentArrival a where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_ALL, query = "select a from AggregatedShipmentArrival a")
@NamedQuery(name = AggregatedShipmentArrival.NQ_FIND, query = "select a from AggregatedShipmentArrival a where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_CHECK, query = "select count(a) from AggregatedShipmentArrival a where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_COUNT, query = "select count(a) from AggregatedShipmentArrival a")
public class AggregatedShipmentArrival {
    @Generated
    public static final String NQ_DELETE_ALL = "AggregatedShipmentArrival.deleteAll";
    @Generated
    public static final String NQ_COUNT = "AggregatedShipmentArrival.count";
    @Generated
    public static final String NQ_DELETE = "AggregatedShipmentArrival.delete";
    @Generated
    public static final String NQ_GET_ALL = "AggregatedShipmentArrival.getAll";
    @Generated
    public static final String NQ_CHECK = "AggregatedShipmentArrival.check";
    @Generated
    public static final String NQ_FIND = "AggregatedShipmentArrival.find";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedShipmentArrival() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public AggregatedShipmentArrival(long id) {
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

        final var bean = (AggregatedShipmentArrival) obj;

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