package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "aggregated_shipment_arrival_tab")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_MATERIALREVISION, query = "select b from AggregatedShipmentArrival a join a.materialRevision b where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_PLANT, query = "select b from AggregatedShipmentArrival a join a.plant b where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_CUSTOMER, query = "select b from AggregatedShipmentArrival a join a.customer b where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_SHIPMENTMOVEMENTTYPE, query = "select b from AggregatedShipmentArrival a join a.shipmentMovementType b where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_ARRIVALMOVEMENTTYPE, query = "select b from AggregatedShipmentArrival a join a.arrivalMovementType b where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_SUPPLIER, query = "select b from AggregatedShipmentArrival a join a.supplier b where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_DELETE_ALL, query = "delete from AggregatedShipmentArrival a")
@NamedQuery(name = AggregatedShipmentArrival.NQ_DELETE, query = "delete from AggregatedShipmentArrival a where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_GET_ALL, query = "select a from AggregatedShipmentArrival a")
@NamedQuery(name = AggregatedShipmentArrival.NQ_FIND, query = "select a from AggregatedShipmentArrival a where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_CHECK, query = "select count(a) from AggregatedShipmentArrival a where a.id = :id")
@NamedQuery(name = AggregatedShipmentArrival.NQ_COUNT, query = "select count(a) from AggregatedShipmentArrival a")
public class AggregatedShipmentArrival extends AbstractAggregatedBase {
    @Generated
    public static final String NQ_DELETE_ALL = "AggregatedShipmentArrival.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "AggregatedShipmentArrival.getMaterialRevision";
    @Generated
    public static final String NQ_COUNT = "AggregatedShipmentArrival.count";
    @Generated
    public static final String NQ_DELETE = "AggregatedShipmentArrival.delete";
    @Generated
    public static final String NQ_GET_CUSTOMER = "AggregatedShipmentArrival.getCustomer";
    @Generated
    public static final String NQ_GET_ALL = "AggregatedShipmentArrival.getAll";
    @Generated
    public static final String NQ_GET_PLANT = "AggregatedShipmentArrival.getPlant";
    @Generated
    public static final String NQ_GET_SHIPMENTMOVEMENTTYPE = "AggregatedShipmentArrival.getShipmentMovementType";
    @Generated
    public static final String NQ_GET_ARRIVALMOVEMENTTYPE = "AggregatedShipmentArrival.getArrivalMovementType";
    @Generated
    public static final String NQ_GET_SUPPLIER = "AggregatedShipmentArrival.getSupplier";
    @Generated
    public static final String NQ_CHECK = "AggregatedShipmentArrival.check";
    @Generated
    public static final String NQ_FIND = "AggregatedShipmentArrival.find";
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
    @JoinColumn(name = "shipment_movement_type", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"shipmentMovementType\" must not be null!")
    @Generated
    private MovementType shipmentMovementType;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "arrival_movement_type", referencedColumnName = "code", nullable = true)
    @Generated
    private MovementType arrivalMovementType;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "supplier", referencedColumnName = "code", nullable = true)
    @Generated
    private Supplier supplier;

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
    public MovementType getShipmentMovementType() {
        return this.shipmentMovementType;
    }

    /**
     * @param shipmentMovementType the movement type to set
     */
    @Generated
    public void setShipmentMovementType(MovementType shipmentMovementType) {
        this.shipmentMovementType = shipmentMovementType;
    }

    /**
     * @return the movement type
     */
    @Generated
    public MovementType getArrivalMovementType() {
        return this.arrivalMovementType;
    }

    /**
     * @param arrivalMovementType the movement type to set
     */
    @Generated
    public void setArrivalMovementType(MovementType arrivalMovementType) {
        this.arrivalMovementType = arrivalMovementType;
    }

    /**
     * @return the supplier
     */
    @Generated
    public Supplier getSupplier() {
        return this.supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    @Generated
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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