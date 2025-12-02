package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@Entity
@Table(name = "arrival_shipment_mv")
@NamedQuery(name = MaterializedArrivalShipment.NQ_GET_MATERIAL, query = "select b from MaterializedArrivalShipment a join a.material b where a.id = :id")
@NamedQuery(name = MaterializedArrivalShipment.NQ_GET_SERIALOBJECT, query = "select b from MaterializedArrivalShipment a join a.serialObject b where a.id = :id")
@NamedQuery(name = MaterializedArrivalShipment.NQ_DELETE_ALL, query = "delete from MaterializedArrivalShipment a")
@NamedQuery(name = MaterializedArrivalShipment.NQ_DELETE, query = "delete from MaterializedArrivalShipment a where a.id = :id")
@NamedQuery(name = MaterializedArrivalShipment.NQ_GET_ALL, query = "select a from MaterializedArrivalShipment a")
@NamedQuery(name = MaterializedArrivalShipment.NQ_FIND, query = "select a from MaterializedArrivalShipment a where a.id = :id")
@NamedQuery(name = MaterializedArrivalShipment.NQ_CHECK, query = "select count(a) from MaterializedArrivalShipment a where a.id = :id")
@NamedQuery(name = MaterializedArrivalShipment.NQ_COUNT, query = "select count(a) from MaterializedArrivalShipment a")
public class MaterializedArrivalShipment extends MaterializedEntitiy {
    @Generated
    public static final String NQ_GET_SERIALOBJECT = "MaterializedArrivalShipment.getSerialObject";
    @Generated
    public static final String NQ_DELETE_ALL = "MaterializedArrivalShipment.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MaterializedArrivalShipment.count";
    @Generated
    public static final String NQ_DELETE = "MaterializedArrivalShipment.delete";
    @Generated
    public static final String NQ_GET_ALL = "MaterializedArrivalShipment.getAll";
    @Generated
    public static final String NQ_GET_MATERIAL = "MaterializedArrivalShipment.getMaterial";
    @Generated
    public static final String NQ_CHECK = "MaterializedArrivalShipment.check";
    @Generated
    public static final String NQ_FIND = "MaterializedArrivalShipment.find";
    @Column(name = "arrival_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate arrivalDate;
    @Column(name = "arrival_id", nullable = true, updatable = true, insertable = true)
    @Generated
    private Long arrivalId;
    @Column(name = "arrival_movement_type", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"arrivalMovementType\" is illegal!")
    @Generated
    private String arrivalMovementType;
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
    @Basic(optional = false)
    @Column(name = "customer_code", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"customerCode\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"customerCode\" is illegal!")
    @Generated
    private String customerCode;
    @Basic(optional = false)
    @Column(name = "customer_name", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"customerName\" must not be null!")
    @Size(min = 1, max = 100, message = "Length of field \"customerName\" is illegal!")
    @Generated
    private String customerName;
    @Basic(optional = false)
    @Column(name = "customer_order_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"customerOrderNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"customerOrderNumber\" is illegal!")
    @Generated
    private String customerOrderNumber;
    @Column(name = "owner_location", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"ownerLocation\" is illegal!")
    @Generated
    private String ownerLocation;
    @Column(name = "purchase_order_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"purchaseOrderNumber\" is illegal!")
    @Generated
    private String purchaseOrderNumber;
    @Basic(optional = false)
    @Column(name = "shipment_date", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"shipmentDate\" must not be null!")
    @Generated
    private LocalDate shipmentDate;
    @Basic(optional = false)
    @Column(name = "shipment_id", nullable = false, updatable = true, insertable = true)
    @Generated
    private long shipmentId;
    @Basic(optional = false)
    @Column(name = "shipment_movement_type", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"shipmentMovementType\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"shipmentMovementType\" is illegal!")
    @Generated
    private String shipmentMovementType;
    @Column(name = "supplier_code", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"supplierCode\" is illegal!")
    @Generated
    private String supplierCode;
    @Column(name = "supplier_name", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"supplierName\" is illegal!")
    @Generated
    private String supplierName;
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
    public MaterializedArrivalShipment() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MaterializedArrivalShipment(long id) {
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
     * @return the arrival id
     */
    @Generated
    public Long getArrivalId() {
        return this.arrivalId;
    }

    /**
     * @param arrivalId the arrival id to set
     */
    @Generated
    public void setArrivalId(Long arrivalId) {
        this.arrivalId = arrivalId;
    }

    /**
     * @return the arrival movement type
     */
    @Generated
    public String getArrivalMovementType() {
        return this.arrivalMovementType;
    }

    /**
     * @param arrivalMovementType the arrival movement type to set
     */
    @Generated
    public void setArrivalMovementType(String arrivalMovementType) {
        this.arrivalMovementType = arrivalMovementType;
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
     * @return the customer code
     */
    @Generated
    public String getCustomerCode() {
        return this.customerCode;
    }

    /**
     * @param customerCode the customer code to set
     */
    @Generated
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * @return the customer name
     */
    @Generated
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * @param customerName the customer name to set
     */
    @Generated
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customer order number
     */
    @Generated
    public String getCustomerOrderNumber() {
        return this.customerOrderNumber;
    }

    /**
     * @param customerOrderNumber the customer order number to set
     */
    @Generated
    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    /**
     * @return the owner location
     */
    @Generated
    public String getOwnerLocation() {
        return this.ownerLocation;
    }

    /**
     * @param ownerLocation the owner location to set
     */
    @Generated
    public void setOwnerLocation(String ownerLocation) {
        this.ownerLocation = ownerLocation;
    }

    /**
     * @return the purchase order number
     */
    @Generated
    public String getPurchaseOrderNumber() {
        return this.purchaseOrderNumber;
    }

    /**
     * @param purchaseOrderNumber the purchase order number to set
     */
    @Generated
    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    /**
     * @return the shipment date
     */
    @Generated
    public LocalDate getShipmentDate() {
        return this.shipmentDate;
    }

    /**
     * @param shipmentDate the shipment date to set
     */
    @Generated
    public void setShipmentDate(LocalDate shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    /**
     * @return the shipment id
     */
    @Generated
    public long getShipmentId() {
        return this.shipmentId;
    }

    /**
     * @param shipmentId the shipment id to set
     */
    @Generated
    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    /**
     * @return the shipment movement type
     */
    @Generated
    public String getShipmentMovementType() {
        return this.shipmentMovementType;
    }

    /**
     * @param shipmentMovementType the shipment movement type to set
     */
    @Generated
    public void setShipmentMovementType(String shipmentMovementType) {
        this.shipmentMovementType = shipmentMovementType;
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

        final var bean = (MaterializedArrivalShipment) obj;

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