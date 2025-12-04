package com.kontron.qdw.domain.serial;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "arrival_tab")
@NamedQuery(name = Arrival.NQ_GET_MATERIALREVISION, query = "select b from Arrival a join a.materialRevision b where a.id = :id")
@NamedQuery(name = Arrival.NQ_GET_MOVEMENTTYPE, query = "select b from Arrival a join a.movementType b where a.id = :id")
@NamedQuery(name = Arrival.NQ_GET_PLANT, query = "select b from Arrival a join a.plant b where a.id = :id")
@NamedQuery(name = Arrival.NQ_GET_SERIALOBJECT, query = "select b from Arrival a join a.serialObject b where a.id = :id")
@NamedQuery(name = Arrival.NQ_GET_SUPPLIER, query = "select b from Arrival a join a.supplier b where a.id = :id")
@NamedQuery(name = Arrival.NQ_DELETE_ALL, query = "delete from Arrival a")
@NamedQuery(name = Arrival.NQ_DELETE, query = "delete from Arrival a where a.id = :id")
@NamedQuery(name = Arrival.NQ_GET_ALL, query = "select a from Arrival a")
@NamedQuery(name = Arrival.NQ_FIND, query = "select a from Arrival a where a.id = :id")
@NamedQuery(name = Arrival.NQ_CHECK, query = "select count(a) from Arrival a where a.id = :id")
@NamedQuery(name = Arrival.NQ_COUNT, query = "select count(a) from Arrival a")
public class Arrival extends AbstractEntityWithId {
    @Generated
    public static final String NQ_GET_SERIALOBJECT = "Arrival.getSerialObject";
    @Generated
    public static final String NQ_DELETE_ALL = "Arrival.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "Arrival.getMaterialRevision";
    @Generated
    public static final String NQ_COUNT = "Arrival.count";
    @Generated
    public static final String NQ_DELETE = "Arrival.delete";
    @Generated
    public static final String NQ_GET_ALL = "Arrival.getAll";
    @Generated
    public static final String NQ_GET_PLANT = "Arrival.getPlant";
    @Generated
    public static final String NQ_GET_MOVEMENTTYPE = "Arrival.getMovementType";
    @Generated
    public static final String NQ_GET_SUPPLIER = "Arrival.getSupplier";
    @Generated
    public static final String NQ_CHECK = "Arrival.check";
    @Generated
    public static final String NQ_FIND = "Arrival.find";
    @Basic(optional = false)
    @Column(name = "arrival_date", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"arrivalDate\" must not be null!")
    @Generated
    private LocalDate arrivalDate;
    @Basic(optional = false)
    @Column(name = "order_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"orderNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"orderNumber\" is illegal!")
    @Generated
    private String orderNumber;
    @Basic(optional = false)
    @Column(name = "rebuild_flag", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean rebuildFlag;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_revision", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"materialRevision\" must not be null!")
    @Generated
    private MaterialRevision materialRevision;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movement_type", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"movementType\" must not be null!")
    @Generated
    private MovementType movementType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plant", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"plant\" must not be null!")
    @Generated
    private Plant plant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serial_object", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"serialObject\" must not be null!")
    @Generated
    private SerialObject serialObject;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"supplier\" must not be null!")
    @Generated
    private Supplier supplier;

    /**
     * Default constructor
     */
    @Generated
    public Arrival() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public Arrival(long id) {
        super(id);
    }

    /**
     * @return the arrival date
     */
    @Generated
    public LocalDate getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * @param arrivalDate the arrival date to set
     */
    @Generated
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * @return the order number
     */
    @Generated
    public String getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * @param orderNumber the order number to set
     */
    @Generated
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return true if the rebuild flag flag is set
     */
    @Generated
    public boolean isRebuildFlag() {
        return this.rebuildFlag;
    }

    /**
     * @param rebuildFlag the value of the rebuild flag flag to set
     */
    @Generated
    public void setRebuildFlag(boolean rebuildFlag) {
        this.rebuildFlag = rebuildFlag;
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
     * @return the serial object
     */
    @Generated
    public SerialObject getSerialObject() {
        return this.serialObject;
    }

    /**
     * @param serialObject the serial object to set
     */
    @Generated
    public void setSerialObject(SerialObject serialObject) {
        this.serialObject = serialObject;
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

        final var bean = (Arrival) obj;

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