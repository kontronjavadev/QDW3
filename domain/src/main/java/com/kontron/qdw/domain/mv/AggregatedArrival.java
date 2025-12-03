package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "aggregated_arrival_tab")
@NamedQuery(name = AggregatedArrival.NQ_GET_MATERIALREVISION, query = "select b from AggregatedArrival a join a.materialRevision b where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_GET_PLANT, query = "select b from AggregatedArrival a join a.plant b where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_GET_MOVEMENTTYPE, query = "select b from AggregatedArrival a join a.movementType b where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_GET_SUPPLIER, query = "select b from AggregatedArrival a join a.supplier b where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_DELETE_ALL, query = "delete from AggregatedArrival a")
@NamedQuery(name = AggregatedArrival.NQ_DELETE, query = "delete from AggregatedArrival a where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_GET_ALL, query = "select a from AggregatedArrival a")
@NamedQuery(name = AggregatedArrival.NQ_FIND, query = "select a from AggregatedArrival a where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_CHECK, query = "select count(a) from AggregatedArrival a where a.id = :id")
@NamedQuery(name = AggregatedArrival.NQ_COUNT, query = "select count(a) from AggregatedArrival a")
public class AggregatedArrival extends AbstractAggregatedBase {
    @Generated
    public static final String NQ_DELETE_ALL = "AggregatedArrival.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "AggregatedArrival.getMaterialRevision";
    @Generated
    public static final String NQ_COUNT = "AggregatedArrival.count";
    @Generated
    public static final String NQ_DELETE = "AggregatedArrival.delete";
    @Generated
    public static final String NQ_GET_ALL = "AggregatedArrival.getAll";
    @Generated
    public static final String NQ_GET_PLANT = "AggregatedArrival.getPlant";
    @Generated
    public static final String NQ_GET_MOVEMENTTYPE = "AggregatedArrival.getMovementType";
    @Generated
    public static final String NQ_GET_SUPPLIER = "AggregatedArrival.getSupplier";
    @Generated
    public static final String NQ_CHECK = "AggregatedArrival.check";
    @Generated
    public static final String NQ_FIND = "AggregatedArrival.find";
    @Basic(optional = false)
    @Column(name = "arrivals", nullable = false, updatable = true, insertable = true)
    @Generated
    private int arrivals;
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
    @JoinColumn(name = "movement_type", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"movementType\" must not be null!")
    @Generated
    private MovementType movementType;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "supplier", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"supplier\" must not be null!")
    @Generated
    private Supplier supplier;

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
        super(id);
    }

    /**
     * @return the arrivals
     */
    @Generated
    public int getArrivals() {
        return this.arrivals;
    }

    /**
     * @param arrivals the arrivals to set
     */
    @Generated
    public void setArrivals(int arrivals) {
        this.arrivals = arrivals;
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