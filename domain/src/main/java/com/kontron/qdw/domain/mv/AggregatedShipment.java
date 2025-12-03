package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "aggregated_shipment_tab")
@NamedQuery(name = AggregatedShipment.NQ_GET_MATERIALREVISION, query = "select b from AggregatedShipment a join a.materialRevision b where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_GET_PLANT, query = "select b from AggregatedShipment a join a.plant b where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_GET_CUSTOMER, query = "select b from AggregatedShipment a join a.customer b where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_GET_MOVEMENTTYPE, query = "select b from AggregatedShipment a join a.movementType b where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_DELETE_ALL, query = "delete from AggregatedShipment a")
@NamedQuery(name = AggregatedShipment.NQ_DELETE, query = "delete from AggregatedShipment a where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_GET_ALL, query = "select a from AggregatedShipment a")
@NamedQuery(name = AggregatedShipment.NQ_FIND, query = "select a from AggregatedShipment a where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_CHECK, query = "select count(a) from AggregatedShipment a where a.id = :id")
@NamedQuery(name = AggregatedShipment.NQ_COUNT, query = "select count(a) from AggregatedShipment a")
public class AggregatedShipment extends AbstractAggregatedBase {
    @Generated
    public static final String NQ_DELETE_ALL = "AggregatedShipment.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "AggregatedShipment.getMaterialRevision";
    @Generated
    public static final String NQ_COUNT = "AggregatedShipment.count";
    @Generated
    public static final String NQ_DELETE = "AggregatedShipment.delete";
    @Generated
    public static final String NQ_GET_CUSTOMER = "AggregatedShipment.getCustomer";
    @Generated
    public static final String NQ_GET_ALL = "AggregatedShipment.getAll";
    @Generated
    public static final String NQ_GET_PLANT = "AggregatedShipment.getPlant";
    @Generated
    public static final String NQ_GET_MOVEMENTTYPE = "AggregatedShipment.getMovementType";
    @Generated
    public static final String NQ_CHECK = "AggregatedShipment.check";
    @Generated
    public static final String NQ_FIND = "AggregatedShipment.find";
    @Basic(optional = false)
    @Column(name = "shipments", nullable = false, updatable = true, insertable = true)
    @Generated
    private int shipments;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "material_revision", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"materialRevision\" must not be null!")
    @Generated
    private MaterialRevision materialRevision;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "plant", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"plant\" must not be null!")
    @Generated
    private Plant plant;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"customer\" must not be null!")
    @Generated
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "movement_type", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"movementType\" must not be null!")
    @Generated
    private MovementType movementType;

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
        super(id);
    }

    /**
     * @return the shipments
     */
    @Generated
    public int getShipments() {
        return this.shipments;
    }

    /**
     * @param shipments the shipments to set
     */
    @Generated
    public void setShipments(int shipments) {
        this.shipments = shipments;
    }

    /**
     * @return the material revision
     */
    @Generated
    public MaterialRevision getMaterialRevision() {
        return this.materialRevision;
    }

    /**
     * @param materialRevision the material revision to set
     */
    @Generated
    public void setMaterialRevision(MaterialRevision materialRevision) {
        this.materialRevision = materialRevision;
    }

    /**
     * @return the plant
     */
    @Generated
    public Plant getPlant() {
        return this.plant;
    }

    /**
     * @param plant the plant to set
     */
    @Generated
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     * @return the customer
     */
    @Generated
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * @param customer the customer to set
     */
    @Generated
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the movement type
     */
    @Generated
    public MovementType getMovementType() {
        return this.movementType;
    }

    /**
     * @param movementType the movement type to set
     */
    @Generated
    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
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