package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@Entity
@Table(name = "materialized_arrival_mv")
@NamedQuery(name = MaterializedArrival.NQ_GET_MATERIAL, query = "select b from MaterializedArrival a join a.material b where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_GET_SERIALOBJECT, query = "select b from MaterializedArrival a join a.serialObject b where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_DELETE_ALL, query = "delete from MaterializedArrival a")
@NamedQuery(name = MaterializedArrival.NQ_DELETE, query = "delete from MaterializedArrival a where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_GET_ALL, query = "select a from MaterializedArrival a")
@NamedQuery(name = MaterializedArrival.NQ_FIND, query = "select a from MaterializedArrival a where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_CHECK, query = "select count(a) from MaterializedArrival a where a.id = :id")
@NamedQuery(name = MaterializedArrival.NQ_COUNT, query = "select count(a) from MaterializedArrival a")
public class MaterializedArrival extends MaterializedEntitiy {
    @Generated
    public static final String NQ_GET_SERIALOBJECT = "MaterializedArrival.getSerialObject";
    @Generated
    public static final String NQ_DELETE_ALL = "MaterializedArrival.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MaterializedArrival.count";
    @Generated
    public static final String NQ_DELETE = "MaterializedArrival.delete";
    @Generated
    public static final String NQ_GET_ALL = "MaterializedArrival.getAll";
    @Generated
    public static final String NQ_GET_MATERIAL = "MaterializedArrival.getMaterial";
    @Generated
    public static final String NQ_CHECK = "MaterializedArrival.check";
    @Generated
    public static final String NQ_FIND = "MaterializedArrival.find";
    @Basic(optional = false)
    @Column(name = "arrival_date", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"arrivalDate\" must not be null!")
    @Generated
    private LocalDate arrivalDate;
    @Basic(optional = false)
    @Column(name = "supplier_code", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"supplierCode\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"supplierCode\" is illegal!")
    @Generated
    private String supplierCode;
    @Basic(optional = false)
    @Column(name = "supplier_name", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"supplierName\" must not be null!")
    @Size(min = 1, max = 100, message = "Length of field \"supplierName\" is illegal!")
    @Generated
    private String supplierName;
    @Basic(optional = false)
    @Column(name = "movement_type", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"movementType\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"movementType\" is illegal!")
    @Generated
    private String movementType;
    @Basic(optional = false)
    @Column(name = "order_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"orderNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"orderNumber\" is illegal!")
    @Generated
    private String orderNumber;
    @Basic(optional = false)
    @Column(name = "country_code", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"countryCode\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"countryCode\" is illegal!")
    @Generated
    private String countryCode;
    @Basic(optional = false)
    @Column(name = "country_name", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"countryName\" must not be null!")
    @Size(min = 1, max = 100, message = "Length of field \"countryName\" is illegal!")
    @Generated
    private String countryName;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "material", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"material\" must not be null!")
    @Generated
    private Material material;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "serial_object", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"serialObject\" must not be null!")
    @Generated
    private SerialObject serialObject;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrival() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MaterializedArrival(long id) {
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
     * @return the supplier code
     */
    @Generated
    public String getSupplierCode() {
        return this.supplierCode;
    }

    /**
     * @param supplierCode the supplier code to set
     */
    @Generated
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * @return the supplier name
     */
    @Generated
    public String getSupplierName() {
        return this.supplierName;
    }

    /**
     * @param supplierName the supplier name to set
     */
    @Generated
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the movement type
     */
    @Generated
    public String getMovementType() {
        return this.movementType;
    }

    /**
     * @param movementType the movement type to set
     */
    @Generated
    public void setMovementType(String movementType) {
        this.movementType = movementType;
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
     * @return the country code
     */
    @Generated
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @param countryCode the country code to set
     */
    @Generated
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the country name
     */
    @Generated
    public String getCountryName() {
        return this.countryName;
    }

    /**
     * @param countryName the country name to set
     */
    @Generated
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

        final var bean = (MaterializedArrival) obj;

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