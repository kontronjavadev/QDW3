package com.kontron.qdw.domain.serial;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "trace_bom_item_tab")
@NamedQuery(name = TraceBoMItem.NQ_GET_MATERIAL, query = "select b from TraceBoMItem a join a.material b where a.id = :id")
@NamedQuery(name = TraceBoMItem.NQ_GET_TRACEBOM, query = "select b from TraceBoMItem a join a.traceBom b where a.id = :id")
@NamedQuery(name = TraceBoMItem.NQ_DELETE_ALL, query = "delete from TraceBoMItem a")
@NamedQuery(name = TraceBoMItem.NQ_DELETE, query = "delete from TraceBoMItem a where a.id = :id")
@NamedQuery(name = TraceBoMItem.NQ_GET_ALL, query = "select a from TraceBoMItem a")
@NamedQuery(name = TraceBoMItem.NQ_FIND, query = "select a from TraceBoMItem a where a.id = :id")
@NamedQuery(name = TraceBoMItem.NQ_CHECK, query = "select count(a) from TraceBoMItem a where a.id = :id")
@NamedQuery(name = TraceBoMItem.NQ_COUNT, query = "select count(a) from TraceBoMItem a")
public class TraceBoMItem extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "TraceBoMItem.deleteAll";
    @Generated
    public static final String NQ_COUNT = "TraceBoMItem.count";
    @Generated
    public static final String NQ_DELETE = "TraceBoMItem.delete";
    @Generated
    public static final String NQ_GET_ALL = "TraceBoMItem.getAll";
    @Generated
    public static final String NQ_GET_MATERIAL = "TraceBoMItem.getMaterial";
    @Generated
    public static final String NQ_GET_TRACEBOM = "TraceBoMItem.getTraceBom";
    @Generated
    public static final String NQ_CHECK = "TraceBoMItem.check";
    @Generated
    public static final String NQ_FIND = "TraceBoMItem.find";
    @Basic(optional = false)
    @Column(name = "date_code", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"dateCode\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"dateCode\" is illegal!")
    @Generated
    private String dateCode;
    @Basic(optional = false)
    @Column(name = "info_field_1", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"infoField1\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"infoField1\" is illegal!")
    @Generated
    private String infoField1;
    @Basic(optional = false)
    @Column(name = "info_field_2", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"infoField2\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"infoField2\" is illegal!")
    @Generated
    private String infoField2;
    @Basic(optional = false)
    @Column(name = "info_field_3", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"infoField3\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"infoField3\" is illegal!")
    @Generated
    private String infoField3;
    @Basic(optional = false)
    @Column(name = "info_field_4", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"infoField4\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"infoField4\" is illegal!")
    @Generated
    private String infoField4;
    @Basic(optional = false)
    @Column(name = "manufacturer_name", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"manufacturerName\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"manufacturerName\" is illegal!")
    @Generated
    private String manufacturerName;
    @Basic(optional = false)
    @Column(name = "manufacturer_revision", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"manufacturerRevision\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"manufacturerRevision\" is illegal!")
    @Generated
    private String manufacturerRevision;
    @Basic(optional = false)
    @Column(name = "order_code", nullable = false, updatable = true, insertable = true, length = 255)
    @NotNull(message = "Field \"orderCode\" must not be null!")
    @Size(min = 1, max = 255, message = "Length of field \"orderCode\" is illegal!")
    @Generated
    private String orderCode;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false, updatable = true, insertable = true)
    @Generated
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"material\" must not be null!")
    @Generated
    private Material material;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trace_bom", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"traceBom\" must not be null!")
    @Generated
    private TraceBoM traceBom;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMItem() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public TraceBoMItem(long id) {
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
     * @return the info field 1
     */
    @Generated
    public String getInfoField1() {
        return this.infoField1;
    }

    /**
     * @param infoField1 the info field 1 to set
     */
    @Generated
    public void setInfoField1(String infoField1) {
        this.infoField1 = infoField1;
    }

    /**
     * @return the info field 2
     */
    @Generated
    public String getInfoField2() {
        return this.infoField2;
    }

    /**
     * @param infoField2 the info field 2 to set
     */
    @Generated
    public void setInfoField2(String infoField2) {
        this.infoField2 = infoField2;
    }

    /**
     * @return the info field 3
     */
    @Generated
    public String getInfoField3() {
        return this.infoField3;
    }

    /**
     * @param infoField3 the info field 3 to set
     */
    @Generated
    public void setInfoField3(String infoField3) {
        this.infoField3 = infoField3;
    }

    /**
     * @return the info field 4
     */
    @Generated
    public String getInfoField4() {
        return this.infoField4;
    }

    /**
     * @param infoField4 the info field 4 to set
     */
    @Generated
    public void setInfoField4(String infoField4) {
        this.infoField4 = infoField4;
    }

    /**
     * @return the manufacturer name
     */
    @Generated
    public String getManufacturerName() {
        return this.manufacturerName;
    }

    /**
     * @param manufacturerName the manufacturer name to set
     */
    @Generated
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
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
     * @return the quantity
     */
    @Generated
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    @Generated
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the material
     */
    @Generated
    public Material getMaterial() {
        return this.material;
    }

    /**
     * @param material the material to set
     */
    @Generated
    public void setMaterial(Material material) {
        this.material = material;
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

        final var bean = (TraceBoMItem) obj;

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