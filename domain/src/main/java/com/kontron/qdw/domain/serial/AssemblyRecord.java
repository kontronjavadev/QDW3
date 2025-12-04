package com.kontron.qdw.domain.serial;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "assembly_record_tab")
@NamedQuery(name = AssemblyRecord.NQ_GET_MATERIALREVISION, query = "select b from AssemblyRecord a join a.materialRevision b where a.id = :id")
@NamedQuery(name = AssemblyRecord.NQ_GET_PARENTSERIALOBJECT, query = "select b from AssemblyRecord a join a.parentSerialObject b where a.id = :id")
@NamedQuery(name = AssemblyRecord.NQ_GET_SERIALOBJECT, query = "select b from AssemblyRecord a join a.serialObject b where a.id = :id")
@NamedQuery(name = AssemblyRecord.NQ_DELETE_ALL, query = "delete from AssemblyRecord a")
@NamedQuery(name = AssemblyRecord.NQ_DELETE, query = "delete from AssemblyRecord a where a.id = :id")
@NamedQuery(name = AssemblyRecord.NQ_GET_ALL, query = "select a from AssemblyRecord a")
@NamedQuery(name = AssemblyRecord.NQ_FIND, query = "select a from AssemblyRecord a where a.id = :id")
@NamedQuery(name = AssemblyRecord.NQ_CHECK, query = "select count(a) from AssemblyRecord a where a.id = :id")
@NamedQuery(name = AssemblyRecord.NQ_COUNT, query = "select count(a) from AssemblyRecord a")
public class AssemblyRecord extends AbstractEntityWithId {
    @Generated
    public static final String NQ_GET_SERIALOBJECT = "AssemblyRecord.getSerialObject";
    @Generated
    public static final String NQ_DELETE_ALL = "AssemblyRecord.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "AssemblyRecord.getMaterialRevision";
    @Generated
    public static final String NQ_COUNT = "AssemblyRecord.count";
    @Generated
    public static final String NQ_DELETE = "AssemblyRecord.delete";
    @Generated
    public static final String NQ_GET_ALL = "AssemblyRecord.getAll";
    @Generated
    public static final String NQ_GET_PARENTSERIALOBJECT = "AssemblyRecord.getParentSerialObject";
    @Generated
    public static final String NQ_CHECK = "AssemblyRecord.check";
    @Generated
    public static final String NQ_FIND = "AssemblyRecord.find";
    @Basic(optional = false)
    @Column(name = "assembly_date", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"assemblyDate\" must not be null!")
    @Generated
    private LocalDate assemblyDate;
    @Basic(optional = false)
    @Column(name = "production_order_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"productionOrderNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"productionOrderNumber\" is illegal!")
    @Generated
    private String productionOrderNumber;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_revision", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"materialRevision\" must not be null!")
    @Generated
    private MaterialRevision materialRevision;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_serial_object", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"parentSerialObject\" must not be null!")
    @Generated
    private SerialObject parentSerialObject;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serial_object", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"serialObject\" must not be null!")
    @Generated
    private SerialObject serialObject;

    /**
     * Default constructor
     */
    @Generated
    public AssemblyRecord() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public AssemblyRecord(long id) {
        super(id);
    }

    /**
     * @return the assembly date
     */
    @Generated
    public LocalDate getAssemblyDate() {
        return this.assemblyDate;
    }

    /**
     * @param assemblyDate the assembly date to set
     */
    @Generated
    public void setAssemblyDate(LocalDate assemblyDate) {
        this.assemblyDate = assemblyDate;
    }

    /**
     * @return the production order number
     */
    @Generated
    public String getProductionOrderNumber() {
        return this.productionOrderNumber;
    }

    /**
     * @param productionOrderNumber the production order number to set
     */
    @Generated
    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
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
     * @return the serial object
     */
    @Generated
    public SerialObject getParentSerialObject() {
        return this.parentSerialObject;
    }

    /**
     * @param parentSerialObject the serial object to set
     */
    @Generated
    public void setParentSerialObject(SerialObject parentSerialObject) {
        this.parentSerialObject = parentSerialObject;
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

        final var bean = (AssemblyRecord) obj;

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