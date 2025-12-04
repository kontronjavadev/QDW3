package com.kontron.qdw.domain.serial;

import jakarta.validation.constraints.*;
import java.util.*;
import java.time.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "trace_bom_tab")
@NamedQuery(name = TraceBoM.NQ_GET_MATERIALREVISION, query = "select b from TraceBoM a join a.materialRevision b where a.id = :id")
@NamedQuery(name = TraceBoM.NQ_GET_SUPPLIER, query = "select b from TraceBoM a join a.supplier b where a.id = :id")
@NamedQuery(name = TraceBoM.NQ_GET_TRACEBOMITEMS, query = "select b from TraceBoM a join a.traceBoMItems b where a.id = :id")
@NamedQuery(name = TraceBoM.NQ_GET_ILLEGALTRACEBOMITEMS, query = "select b from TraceBoM a join a.illegalTraceBoMItems b where a.id = :id")
@NamedQuery(name = TraceBoM.NQ_DELETE_ALL, query = "delete from TraceBoM a")
@NamedQuery(name = TraceBoM.NQ_DELETE, query = "delete from TraceBoM a where a.id = :id")
@NamedQuery(name = TraceBoM.NQ_GET_ALL, query = "select a from TraceBoM a")
@NamedQuery(name = TraceBoM.NQ_FIND, query = "select a from TraceBoM a where a.id = :id")
@NamedQuery(name = TraceBoM.NQ_CHECK, query = "select count(a) from TraceBoM a where a.id = :id")
@NamedQuery(name = TraceBoM.NQ_COUNT, query = "select count(a) from TraceBoM a")
public class TraceBoM extends AbstractEntityWithId {
    @Generated
    public static final String NQ_GET_TRACEBOMITEMS = "TraceBoM.getTraceBoMItems";
    @Generated
    public static final String NQ_GET_ILLEGALTRACEBOMITEMS = "TraceBoM.getIllegalTraceBoMItems";
    @Generated
    public static final String NQ_DELETE_ALL = "TraceBoM.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "TraceBoM.getMaterialRevision";
    @Generated
    public static final String NQ_COUNT = "TraceBoM.count";
    @Generated
    public static final String NQ_DELETE = "TraceBoM.delete";
    @Generated
    public static final String NQ_GET_ALL = "TraceBoM.getAll";
    @Generated
    public static final String NQ_GET_SUPPLIER = "TraceBoM.getSupplier";
    @Generated
    public static final String NQ_CHECK = "TraceBoM.check";
    @Generated
    public static final String NQ_FIND = "TraceBoM.find";
    @Basic(optional = false)
    @Column(name = "delivery_note_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"deliveryNoteNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"deliveryNoteNumber\" is illegal!")
    @Generated
    private String deliveryNoteNumber;
    @Column(name = "lot_number", nullable = true, updatable = true, insertable = true, length = 20)
    @Size(max = 20, message = "Length of field \"lotNumber\" is illegal!")
    @Generated
    private String lotNumber;
    @Basic(optional = false)
    @Column(name = "order_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"orderNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"orderNumber\" is illegal!")
    @Generated
    private String orderNumber;
    @Basic(optional = false)
    @Column(name = "production_date", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"productionDate\" must not be null!")
    @Generated
    private LocalDate productionDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_revision", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"materialRevision\" must not be null!")
    @Generated
    private MaterialRevision materialRevision;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"supplier\" must not be null!")
    @Generated
    private Supplier supplier;
    @OneToMany(targetEntity = TraceBoMItem.class, mappedBy = "traceBom", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<TraceBoMItem> traceBoMItems = new ArrayList<>();
    @OneToMany(targetEntity = IllegalTraceBoMItem.class, mappedBy = "traceBom", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<IllegalTraceBoMItem> illegalTraceBoMItems = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public TraceBoM() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public TraceBoM(long id) {
        super(id);
    }

    /**
     * @return the delivery note number
     */
    @Generated
    public String getDeliveryNoteNumber() {
        return this.deliveryNoteNumber;
    }

    /**
     * @param deliveryNoteNumber the delivery note number to set
     */
    @Generated
    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    /**
     * @return the lot number
     */
    @Generated
    public String getLotNumber() {
        return this.lotNumber;
    }

    /**
     * @param lotNumber the lot number to set
     */
    @Generated
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
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
     * @return the production date
     */
    @Generated
    public LocalDate getProductionDate() {
        return this.productionDate;
    }

    /**
     * @param productionDate the production date to set
     */
    @Generated
    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
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

    /**
     * @return a collection of trace BoM items
     */
    @Generated
    public Collection<TraceBoMItem> getTraceBoMItems() {
        return this.traceBoMItems;
    }

    /**
     * @param traceBoMItems the trace BoM items to set
     */
    @Generated
    public void setTraceBoMItems(Collection<TraceBoMItem> traceBoMItems) {
        this.traceBoMItems = traceBoMItems;
    }

    /**
     * @return a collection of illegal trace BoM items
     */
    @Generated
    public Collection<IllegalTraceBoMItem> getIllegalTraceBoMItems() {
        return this.illegalTraceBoMItems;
    }

    /**
     * @param illegalTraceBoMItems the illegal trace BoM items to set
     */
    @Generated
    public void setIllegalTraceBoMItems(Collection<IllegalTraceBoMItem> illegalTraceBoMItems) {
        this.illegalTraceBoMItems = illegalTraceBoMItems;
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

        final var bean = (TraceBoM) obj;

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