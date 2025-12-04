package com.kontron.qdw.domain.serial;

import jakarta.validation.constraints.*;
import java.util.*;
import java.time.*;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "serial_object_tab")
@NamedQuery(name = SerialObject.NQ_UK_FIND_BY_SERIALNUMBER_AND_MATERIAL, query = "select a from SerialObject a where a.serialNumber = :serialNumber and a.material.id = :material")
@NamedQuery(name = SerialObject.NQ_UK_SEARCH_BY_SERIALNUMBER_AND_MATERIAL, query = "select a from SerialObject a where a.serialNumber like :serialNumber and a.material.id = :material")
@NamedQuery(name = SerialObject.NQ_UK_EXISTS_BY_SERIALNUMBER_AND_MATERIAL, query = "select count(a) from SerialObject a where a.serialNumber = :serialNumber and a.material.id = :material")
@NamedQuery(name = SerialObject.NQ_UK_EXISTS_BY_SERIALNUMBER_AND_MATERIAL_AND_ID, query = "select count(a) from SerialObject a where a.serialNumber = :serialNumber and a.material.id = :material and a.id <> :id")
@NamedQuery(name = SerialObject.NQ_GET_MATERIAL, query = "select b from SerialObject a join a.material b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_PARENTOBJECT, query = "select b from SerialObject a join a.parentObject b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_SERIALOBJECTS, query = "select b from SerialObject a join a.serialObjects b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_ASSEMBLYRECORDS, query = "select b from SerialObject a join a.assemblyRecords b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_ARRIVALS, query = "select b from SerialObject a join a.arrivals b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_SHIPMENTS, query = "select b from SerialObject a join a.shipments b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_TRACEBOM, query = "select b from SerialObject a join a.traceBom b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_SERVICEMESSAGES, query = "select b from SerialObject a join a.serviceMessages b where a.id = :id")
@NamedQuery(name = SerialObject.NQ_DELETE_ALL, query = "delete from SerialObject a")
@NamedQuery(name = SerialObject.NQ_DELETE, query = "delete from SerialObject a where a.id = :id")
@NamedQuery(name = SerialObject.NQ_GET_ALL, query = "select a from SerialObject a")
@NamedQuery(name = SerialObject.NQ_FIND, query = "select a from SerialObject a where a.id = :id")
@NamedQuery(name = SerialObject.NQ_CHECK, query = "select count(a) from SerialObject a where a.id = :id")
@NamedQuery(name = SerialObject.NQ_COUNT, query = "select count(a) from SerialObject a")
public class SerialObject extends AbstractEntityWithId {
    @Generated
    public static final String NQ_UK_EXISTS_BY_SERIALNUMBER_AND_MATERIAL_AND_ID = "SerialObject.checkBySerialNumber_And_Material_And_Id";
    @Generated
    public static final String NQ_DELETE_ALL = "SerialObject.deleteAll";
    @Generated
    public static final String NQ_UK_FIND_BY_SERIALNUMBER_AND_MATERIAL = "SerialObject.getBySerialNumber_And_Material";
    @Generated
    public static final String NQ_DELETE = "SerialObject.delete";
    @Generated
    public static final String NQ_GET_SERIALOBJECTS = "SerialObject.getSerialObjects";
    @Generated
    public static final String NQ_GET_ALL = "SerialObject.getAll";
    @Generated
    public static final String NQ_GET_SHIPMENTS = "SerialObject.getShipments";
    @Generated
    public static final String NQ_GET_MATERIAL = "SerialObject.getMaterial";
    @Generated
    public static final String NQ_GET_TRACEBOM = "SerialObject.getTraceBom";
    @Generated
    public static final String NQ_GET_ARRIVALS = "SerialObject.getArrivals";
    @Generated
    public static final String NQ_GET_SERVICEMESSAGES = "SerialObject.getServiceMessages";
    @Generated
    public static final String NQ_UK_SEARCH_BY_SERIALNUMBER_AND_MATERIAL = "SerialObject.findBySerialNumber_And_Material";
    @Generated
    public static final String NQ_CHECK = "SerialObject.check";
    @Generated
    public static final String NQ_GET_PARENTOBJECT = "SerialObject.getParentObject";
    @Generated
    public static final String NQ_FIND = "SerialObject.find";
    @Generated
    public static final String NQ_UK_EXISTS_BY_SERIALNUMBER_AND_MATERIAL = "SerialObject.checkBySerialNumber_And_Material";
    @Generated
    public static final String NQ_GET_ASSEMBLYRECORDS = "SerialObject.getAssemblyRecords";
    @Generated
    public static final String NQ_COUNT = "SerialObject.count";
    @Basic(optional = false)
    @Column(name = "serial_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"serialNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"serialNumber\" is illegal!")
    @Generated
    private String serialNumber;
    @Column(name = "assembly_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate assemblyDate;
    @Column(name = "customer_serial_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"customerSerialNumber\" is illegal!")
    @Generated
    private String customerSerialNumber;
    @Column(name = "production_order_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"productionOrderNumber\" is illegal!")
    @Generated
    private String productionOrderNumber;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"material\" must not be null!")
    @Generated
    private Material material;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_object", referencedColumnName = "id", nullable = true)
    @Generated
    private SerialObject parentObject;
    @OneToMany(targetEntity = SerialObject.class, mappedBy = "parentObject", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<SerialObject> serialObjects = new ArrayList<>();
    @OneToMany(targetEntity = AssemblyRecord.class, mappedBy = "parentSerialObject", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<AssemblyRecord> assemblyRecords = new ArrayList<>();
    @OneToMany(targetEntity = Arrival.class, mappedBy = "serialObject", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<Arrival> arrivals = new ArrayList<>();
    @OneToMany(targetEntity = Shipment.class, mappedBy = "serialObject", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<Shipment> shipments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "trace_bom", referencedColumnName = "id", nullable = true)
    @Generated
    private TraceBoM traceBom;
    @OneToMany(targetEntity = ServiceMessage.class, mappedBy = "serialObject", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<ServiceMessage> serviceMessages = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public SerialObject() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public SerialObject(long id) {
        super(id);
    }

    /**
     * @return the serial number
     */
    @Generated
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * @param serialNumber the serial number to set
     */
    @Generated
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
     * @return the customer serial number
     */
    @Generated
    public String getCustomerSerialNumber() {
        return this.customerSerialNumber;
    }

    /**
     * @param customerSerialNumber the customer serial number to set
     */
    @Generated
    public void setCustomerSerialNumber(String customerSerialNumber) {
        this.customerSerialNumber = customerSerialNumber;
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
    public SerialObject getParentObject() {
        return this.parentObject;
    }

    /**
     * @param parentObject the serial object to set
     */
    @Generated
    public void setParentObject(SerialObject parentObject) {
        this.parentObject = parentObject;
    }

    /**
     * @return a collection of serial objects
     */
    @Generated
    public Collection<SerialObject> getSerialObjects() {
        return this.serialObjects;
    }

    /**
     * @param serialObjects the serial objects to set
     */
    @Generated
    public void setSerialObjects(Collection<SerialObject> serialObjects) {
        this.serialObjects = serialObjects;
    }

    /**
     * @return a collection of assembly records
     */
    @Generated
    public Collection<AssemblyRecord> getAssemblyRecords() {
        return this.assemblyRecords;
    }

    /**
     * @param assemblyRecords the assembly records to set
     */
    @Generated
    public void setAssemblyRecords(Collection<AssemblyRecord> assemblyRecords) {
        this.assemblyRecords = assemblyRecords;
    }

    /**
     * @return a collection of arrivals
     */
    @Generated
    public Collection<Arrival> getArrivals() {
        return this.arrivals;
    }

    /**
     * @param arrivals the arrivals to set
     */
    @Generated
    public void setArrivals(Collection<Arrival> arrivals) {
        this.arrivals = arrivals;
    }

    /**
     * @return a collection of shipments
     */
    @Generated
    public Collection<Shipment> getShipments() {
        return this.shipments;
    }

    /**
     * @param shipments the shipments to set
     */
    @Generated
    public void setShipments(Collection<Shipment> shipments) {
        this.shipments = shipments;
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

    /**
     * @return a collection of service messages
     */
    @Generated
    public Collection<ServiceMessage> getServiceMessages() {
        return this.serviceMessages;
    }

    /**
     * @param serviceMessages the service messages to set
     */
    @Generated
    public void setServiceMessages(Collection<ServiceMessage> serviceMessages) {
        this.serviceMessages = serviceMessages;
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

        final var bean = (SerialObject) obj;

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