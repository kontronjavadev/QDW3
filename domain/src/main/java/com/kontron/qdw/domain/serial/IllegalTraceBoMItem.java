package com.kontron.qdw.domain.serial;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "illegal_trace_bom_item_tab")
@NamedQuery(name = IllegalTraceBoMItem.NQ_GET_TRACEBOM, query = "select b from IllegalTraceBoMItem a join a.traceBom b where a.id = :id")
@NamedQuery(name = IllegalTraceBoMItem.NQ_DELETE_ALL, query = "delete from IllegalTraceBoMItem a")
@NamedQuery(name = IllegalTraceBoMItem.NQ_DELETE, query = "delete from IllegalTraceBoMItem a where a.id = :id")
@NamedQuery(name = IllegalTraceBoMItem.NQ_GET_ALL, query = "select a from IllegalTraceBoMItem a")
@NamedQuery(name = IllegalTraceBoMItem.NQ_FIND, query = "select a from IllegalTraceBoMItem a where a.id = :id")
@NamedQuery(name = IllegalTraceBoMItem.NQ_CHECK, query = "select count(a) from IllegalTraceBoMItem a where a.id = :id")
@NamedQuery(name = IllegalTraceBoMItem.NQ_COUNT, query = "select count(a) from IllegalTraceBoMItem a")
public class IllegalTraceBoMItem extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "IllegalTraceBoMItem.deleteAll";
    @Generated
    public static final String NQ_COUNT = "IllegalTraceBoMItem.count";
    @Generated
    public static final String NQ_DELETE = "IllegalTraceBoMItem.delete";
    @Generated
    public static final String NQ_GET_ALL = "IllegalTraceBoMItem.getAll";
    @Generated
    public static final String NQ_GET_TRACEBOM = "IllegalTraceBoMItem.getTraceBom";
    @Generated
    public static final String NQ_CHECK = "IllegalTraceBoMItem.check";
    @Generated
    public static final String NQ_FIND = "IllegalTraceBoMItem.find";
    @Basic(optional = false)
    @Column(name = "date_code", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"dateCode\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"dateCode\" is illegal!")
    @Generated
    private String dateCode;
    @Basic(optional = false)
    @Column(name = "manufacturer", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"manufacturer\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"manufacturer\" is illegal!")
    @Generated
    private String manufacturer;
    @Basic(optional = false)
    @Column(name = "manufacturer_revision", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"manufacturerRevision\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"manufacturerRevision\" is illegal!")
    @Generated
    private String manufacturerRevision;
    @Basic(optional = false)
    @Column(name = "material_number", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"materialNumber\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"materialNumber\" is illegal!")
    @Generated
    private String materialNumber;
    @Basic(optional = false)
    @Column(name = "order_code", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"orderCode\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"orderCode\" is illegal!")
    @Generated
    private String orderCode;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trace_bom", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"traceBom\" must not be null!")
    @Generated
    private TraceBoM traceBom;

    /**
     * Default constructor
     */
    @Generated
    public IllegalTraceBoMItem() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public IllegalTraceBoMItem(long id) {
        super(id);
    }

    /**
     * @return the date code
     */
    @Generated
    public String getDateCode() {
        return this.dateCode;
    }

    /**
     * @param dateCode the date code to set
     */
    @Generated
    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    /**
     * @return the manufacturer
     */
    @Generated
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    @Generated
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the manufacturer revision
     */
    @Generated
    public String getManufacturerRevision() {
        return this.manufacturerRevision;
    }

    /**
     * @param manufacturerRevision the manufacturer revision to set
     */
    @Generated
    public void setManufacturerRevision(String manufacturerRevision) {
        this.manufacturerRevision = manufacturerRevision;
    }

    /**
     * @return the material number
     */
    @Generated
    public String getMaterialNumber() {
        return this.materialNumber;
    }

    /**
     * @param materialNumber the material number to set
     */
    @Generated
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    /**
     * @return the order code
     */
    @Generated
    public String getOrderCode() {
        return this.orderCode;
    }

    /**
     * @param orderCode the order code to set
     */
    @Generated
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * @return the trace BoM
     */
    @Generated
    public TraceBoM getTraceBom() {
        return this.traceBom;
    }

    /**
     * @param traceBom the trace BoM to set
     */
    @Generated
    public void setTraceBom(TraceBoM traceBom) {
        this.traceBom = traceBom;
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

        final var bean = (IllegalTraceBoMItem) obj;

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