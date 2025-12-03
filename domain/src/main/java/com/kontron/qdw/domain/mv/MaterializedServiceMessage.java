package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import com.kontron.qdw.domain.serial.*;
import java.util.*;
import java.time.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "service_message_mv")
@NamedQuery(name = MaterializedServiceMessage.NQ_GET_SERIALOBJECT, query = "select b from MaterializedServiceMessage a join a.serialObject b where a.id = :id")
@NamedQuery(name = MaterializedServiceMessage.NQ_GET_FAILUREMATERIALS, query = "select b from MaterializedServiceMessage a join a.failureMaterials b where a.id = :id")
@NamedQuery(name = MaterializedServiceMessage.NQ_GET_MATERIAL, query = "select b from MaterializedServiceMessage a join a.material b where a.id = :id")
@NamedQuery(name = MaterializedServiceMessage.NQ_DELETE_ALL, query = "delete from MaterializedServiceMessage a")
@NamedQuery(name = MaterializedServiceMessage.NQ_DELETE, query = "delete from MaterializedServiceMessage a where a.id = :id")
@NamedQuery(name = MaterializedServiceMessage.NQ_GET_ALL, query = "select a from MaterializedServiceMessage a")
@NamedQuery(name = MaterializedServiceMessage.NQ_FIND, query = "select a from MaterializedServiceMessage a where a.id = :id")
@NamedQuery(name = MaterializedServiceMessage.NQ_CHECK, query = "select count(a) from MaterializedServiceMessage a where a.id = :id")
@NamedQuery(name = MaterializedServiceMessage.NQ_COUNT, query = "select count(a) from MaterializedServiceMessage a")
public class MaterializedServiceMessage extends MaterializedEntitiy {
    @Generated
    public static final String NQ_GET_SERIALOBJECT = "MaterializedServiceMessage.getSerialObject";
    @Generated
    public static final String NQ_DELETE_ALL = "MaterializedServiceMessage.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MaterializedServiceMessage.count";
    @Generated
    public static final String NQ_DELETE = "MaterializedServiceMessage.delete";
    @Generated
    public static final String NQ_GET_ALL = "MaterializedServiceMessage.getAll";
    @Generated
    public static final String NQ_GET_FAILUREMATERIALS = "MaterializedServiceMessage.getFailureMaterials";
    @Generated
    public static final String NQ_GET_MATERIAL = "MaterializedServiceMessage.getMaterial";
    @Generated
    public static final String NQ_CHECK = "MaterializedServiceMessage.check";
    @Generated
    public static final String NQ_FIND = "MaterializedServiceMessage.find";
    @Basic(optional = false)
    @Column(name = "service_order", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"serviceOrder\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"serviceOrder\" is illegal!")
    @Generated
    private String serviceOrder;
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "service_order_type", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"serviceOrderType\" must not be null!")
    @Generated
    private ServiceOrderType serviceOrderType;
    @Column(name = "rma_type", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"rmaType\" is illegal!")
    @Generated
    private String rmaType;
    @Column(name = "location", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"location\" is illegal!")
    @Generated
    private String location;
    @Column(name = "service_name", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"serviceName\" is illegal!")
    @Generated
    private String serviceName;
    @Column(name = "task_name", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"taskName\" is illegal!")
    @Generated
    private String taskName;
    @Column(name = "state_name", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"stateName\" is illegal!")
    @Generated
    private String stateName;
    @Column(name = "internal_arrival_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate internalArrivalDate;
    @Column(name = "internal_shipment_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate internalShipmentDate;
    @Column(name = "basic_start_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate basicStartDate;
    @Column(name = "basic_end_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate basicEndDate;
    @Column(name = "designator", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"designator\" is illegal!")
    @Generated
    private String designator;
    @Column(name = "defect_component", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"defectComponent\" is illegal!")
    @Generated
    private String defectComponent;
    @Column(name = "analysis_text", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"analysisText\" is illegal!")
    @Generated
    private String analysisText;
    @Column(name = "internal_report", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"internalReport\" is illegal!")
    @Generated
    private String internalReport;
    @Column(name = "external_report", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"externalReport\" is illegal!")
    @Generated
    private String externalReport;
    @Column(name = "customer_report", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"customerReport\" is illegal!")
    @Generated
    private String customerReport;
    @Basic(optional = false)
    @Column(name = "epidemic_failure", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean epidemicFailure;
    @Column(name = "error_id", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"errorId\" is illegal!")
    @Generated
    private String errorId;
    @Column(name = "origin", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"origin\" is illegal!")
    @Generated
    private String origin;
    @Basic(optional = false)
    @Column(name = "customer_failure", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean customerFailure;
    @Column(name = "customer_code", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"customerCode\" is illegal!")
    @Generated
    private String customerCode;
    @Column(name = "customer_name", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"customerName\" is illegal!")
    @Generated
    private String customerName;
    @Column(name = "customer_group", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"customerGroup\" is illegal!")
    @Generated
    private String customerGroup;
    @Column(name = "country_code", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"countryCode\" is illegal!")
    @Generated
    private String countryCode;
    @Column(name = "country_name", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"countryName\" is illegal!")
    @Generated
    private String countryName;
    @Column(name = "supplier_code", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"supplierCode\" is illegal!")
    @Generated
    private String supplierCode;
    @Column(name = "supplier_name", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"supplierName\" is illegal!")
    @Generated
    private String supplierName;
    @Column(name = "sup_arrival_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate supplierArrivalDate;
    @Column(name = "cust_ship_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate customerShipmentDate;
    @Column(name = "fault_analysis_code", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"faultAnalysisCode\" is illegal!")
    @Generated
    private String faultAnalysisCode;
    @Column(name = "error_code_name", nullable = true, updatable = true, insertable = true, length = 150)
    @Size(max = 150, message = "Length of field \"errorCodeName\" is illegal!")
    @Generated
    private String errorCodeName;
    @Column(name = "external_supplier_code", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"externalSupplierCode\" is illegal!")
    @Generated
    private String externalSupplierCode;
    @Column(name = "external_supplier_name", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"externalSupplierName\" is illegal!")
    @Generated
    private String externalSupplierName;
    @Column(name = "delivery_note_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"deliveryNoteNumber\" is illegal!")
    @Generated
    private String deliveryNoteNumber;
    @Column(name = "error_code_group", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"errorCodeGroup\" is illegal!")
    @Generated
    private String errorCodeGroup;
    @Column(name = "symptom_short_text", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"symptomShortText\" is illegal!")
    @Generated
    private String symptomShortText;
    @Column(name = "repair_task_short_text", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"repairTaskShortText\" is illegal!")
    @Generated
    private String repairTaskShortText;
    @Column(name = "repair_description", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"repairDescription\" is illegal!")
    @Generated
    private String repairDescription;
    @Basic(optional = false)
    @Column(name = "owner_location", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"ownerLocation\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"ownerLocation\" is illegal!")
    @Generated
    private String ownerLocation;
    @Column(name = "error_short_text", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"errorShortText\" is illegal!")
    @Generated
    private String errorShortText;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "serial_object", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"serialObject\" must not be null!")
    @Generated
    private SerialObject serialObject;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "service_message_materials_tab", joinColumns = { @JoinColumn(name = "service_message_pk") }, inverseJoinColumns = {
            @JoinColumn(name = "material_pk") })
    @Generated
    private Collection<Material> failureMaterials = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "material", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"material\" must not be null!")
    @Generated
    private Material material;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedServiceMessage() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MaterializedServiceMessage(long id) {
        super(id);
    }

    /**
     * @return the service order
     */
    @Generated
    public String getServiceOrder() {
        return this.serviceOrder;
    }

    /**
     * @param serviceOrder the service order to set
     */
    @Generated
    public void setServiceOrder(String serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    /**
     * @return the service order type
     */
    @Generated
    public ServiceOrderType getServiceOrderType() {
        return this.serviceOrderType;
    }

    /**
     * @param serviceOrderType the service order type to set
     */
    @Generated
    public void setServiceOrderType(ServiceOrderType serviceOrderType) {
        this.serviceOrderType = serviceOrderType;
    }

    /**
     * @return the rma type
     */
    @Generated
    public String getRmaType() {
        return this.rmaType;
    }

    /**
     * @param rmaType the rma type to set
     */
    @Generated
    public void setRmaType(String rmaType) {
        this.rmaType = rmaType;
    }

    /**
     * @return the location
     */
    @Generated
    public String getLocation() {
        return this.location;
    }

    /**
     * @param location the location to set
     */
    @Generated
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the service name
     */
    @Generated
    public String getServiceName() {
        return this.serviceName;
    }

    /**
     * @param serviceName the service name to set
     */
    @Generated
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the task name
     */
    @Generated
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * @param taskName the task name to set
     */
    @Generated
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the state name
     */
    @Generated
    public String getStateName() {
        return this.stateName;
    }

    /**
     * @param stateName the state name to set
     */
    @Generated
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the internal arrival date
     */
    @Generated
    public LocalDate getInternalArrivalDate() {
        return this.internalArrivalDate;
    }

    /**
     * @param internalArrivalDate the internal arrival date to set
     */
    @Generated
    public void setInternalArrivalDate(LocalDate internalArrivalDate) {
        this.internalArrivalDate = internalArrivalDate;
    }

    /**
     * @return the internal shipment date
     */
    @Generated
    public LocalDate getInternalShipmentDate() {
        return this.internalShipmentDate;
    }

    /**
     * @param internalShipmentDate the internal shipment date to set
     */
    @Generated
    public void setInternalShipmentDate(LocalDate internalShipmentDate) {
        this.internalShipmentDate = internalShipmentDate;
    }

    /**
     * @return the basic start date
     */
    @Generated
    public LocalDate getBasicStartDate() {
        return this.basicStartDate;
    }

    /**
     * @param basicStartDate the basic start date to set
     */
    @Generated
    public void setBasicStartDate(LocalDate basicStartDate) {
        this.basicStartDate = basicStartDate;
    }

    /**
     * @return the basic end date
     */
    @Generated
    public LocalDate getBasicEndDate() {
        return this.basicEndDate;
    }

    /**
     * @param basicEndDate the basic end date to set
     */
    @Generated
    public void setBasicEndDate(LocalDate basicEndDate) {
        this.basicEndDate = basicEndDate;
    }

    /**
     * @return the designator
     */
    @Generated
    public String getDesignator() {
        return this.designator;
    }

    /**
     * @param designator the designator to set
     */
    @Generated
    public void setDesignator(String designator) {
        this.designator = designator;
    }

    /**
     * @return the defect component
     */
    @Generated
    public String getDefectComponent() {
        return this.defectComponent;
    }

    /**
     * @param defectComponent the defect component to set
     */
    @Generated
    public void setDefectComponent(String defectComponent) {
        this.defectComponent = defectComponent;
    }

    /**
     * @return the analysis text
     */
    @Generated
    public String getAnalysisText() {
        return this.analysisText;
    }

    /**
     * @param analysisText the analysis text to set
     */
    @Generated
    public void setAnalysisText(String analysisText) {
        this.analysisText = analysisText;
    }

    /**
     * @return the internal report
     */
    @Generated
    public String getInternalReport() {
        return this.internalReport;
    }

    /**
     * @param internalReport the internal report to set
     */
    @Generated
    public void setInternalReport(String internalReport) {
        this.internalReport = internalReport;
    }

    /**
     * @return the external report
     */
    @Generated
    public String getExternalReport() {
        return this.externalReport;
    }

    /**
     * @param externalReport the external report to set
     */
    @Generated
    public void setExternalReport(String externalReport) {
        this.externalReport = externalReport;
    }

    /**
     * @return the customer report
     */
    @Generated
    public String getCustomerReport() {
        return this.customerReport;
    }

    /**
     * @param customerReport the customer report to set
     */
    @Generated
    public void setCustomerReport(String customerReport) {
        this.customerReport = customerReport;
    }

    /**
     * @return true if the epidemic failure flag is set
     */
    @Generated
    public boolean isEpidemicFailure() {
        return this.epidemicFailure;
    }

    /**
     * @param epidemicFailure the value of the epidemic failure flag to set
     */
    @Generated
    public void setEpidemicFailure(boolean epidemicFailure) {
        this.epidemicFailure = epidemicFailure;
    }

    /**
     * @return the error id
     */
    @Generated
    public String getErrorId() {
        return this.errorId;
    }

    /**
     * @param errorId the error id to set
     */
    @Generated
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    /**
     * @return the origin
     */
    @Generated
    public String getOrigin() {
        return this.origin;
    }

    /**
     * @param origin the origin to set
     */
    @Generated
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return true if the customer failure flag is set
     */
    @Generated
    public boolean isCustomerFailure() {
        return this.customerFailure;
    }

    /**
     * @param customerFailure the value of the customer failure flag to set
     */
    @Generated
    public void setCustomerFailure(boolean customerFailure) {
        this.customerFailure = customerFailure;
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
     * @return the customer group
     */
    @Generated
    public String getCustomerGroup() {
        return this.customerGroup;
    }

    /**
     * @param customerGroup the customer group to set
     */
    @Generated
    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
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
     * @return the supplier arrival date
     */
    @Generated
    public LocalDate getSupplierArrivalDate() {
        return this.supplierArrivalDate;
    }

    /**
     * @param supplierArrivalDate the supplier arrival date to set
     */
    @Generated
    public void setSupplierArrivalDate(LocalDate supplierArrivalDate) {
        this.supplierArrivalDate = supplierArrivalDate;
    }

    /**
     * @return the customer shipment date
     */
    @Generated
    public LocalDate getCustomerShipmentDate() {
        return this.customerShipmentDate;
    }

    /**
     * @param customerShipmentDate the customer shipment date to set
     */
    @Generated
    public void setCustomerShipmentDate(LocalDate customerShipmentDate) {
        this.customerShipmentDate = customerShipmentDate;
    }

    /**
     * @return the fault analysis code
     */
    @Generated
    public String getFaultAnalysisCode() {
        return this.faultAnalysisCode;
    }

    /**
     * @param faultAnalysisCode the fault analysis code to set
     */
    @Generated
    public void setFaultAnalysisCode(String faultAnalysisCode) {
        this.faultAnalysisCode = faultAnalysisCode;
    }

    /**
     * @return the error code name
     */
    @Generated
    public String getErrorCodeName() {
        return this.errorCodeName;
    }

    /**
     * @param errorCodeName the error code name to set
     */
    @Generated
    public void setErrorCodeName(String errorCodeName) {
        this.errorCodeName = errorCodeName;
    }

    /**
     * @return the external supplier code
     */
    @Generated
    public String getExternalSupplierCode() {
        return this.externalSupplierCode;
    }

    /**
     * @param externalSupplierCode the external supplier code to set
     */
    @Generated
    public void setExternalSupplierCode(String externalSupplierCode) {
        this.externalSupplierCode = externalSupplierCode;
    }

    /**
     * @return the external supplier name
     */
    @Generated
    public String getExternalSupplierName() {
        return this.externalSupplierName;
    }

    /**
     * @param externalSupplierName the external supplier name to set
     */
    @Generated
    public void setExternalSupplierName(String externalSupplierName) {
        this.externalSupplierName = externalSupplierName;
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
     * @return the error code group
     */
    @Generated
    public String getErrorCodeGroup() {
        return this.errorCodeGroup;
    }

    /**
     * @param errorCodeGroup the error code group to set
     */
    @Generated
    public void setErrorCodeGroup(String errorCodeGroup) {
        this.errorCodeGroup = errorCodeGroup;
    }

    /**
     * @return the symptom short text
     */
    @Generated
    public String getSymptomShortText() {
        return this.symptomShortText;
    }

    /**
     * @param symptomShortText the symptom short text to set
     */
    @Generated
    public void setSymptomShortText(String symptomShortText) {
        this.symptomShortText = symptomShortText;
    }

    /**
     * @return the repair task short text
     */
    @Generated
    public String getRepairTaskShortText() {
        return this.repairTaskShortText;
    }

    /**
     * @param repairTaskShortText the repair task short text to set
     */
    @Generated
    public void setRepairTaskShortText(String repairTaskShortText) {
        this.repairTaskShortText = repairTaskShortText;
    }

    /**
     * @return the repair description
     */
    @Generated
    public String getRepairDescription() {
        return this.repairDescription;
    }

    /**
     * @param repairDescription the repair description to set
     */
    @Generated
    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
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
     * @return the error short text
     */
    @Generated
    public String getErrorShortText() {
        return this.errorShortText;
    }

    /**
     * @param errorShortText the error short text to set
     */
    @Generated
    public void setErrorShortText(String errorShortText) {
        this.errorShortText = errorShortText;
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
     * @return a collection of materials
     */
    @Generated
    public Collection<Material> getFailureMaterials() {
        return this.failureMaterials;
    }

    /**
     * @param failureMaterials the materials to set
     */
    @Generated
    public void setFailureMaterials(Collection<Material> failureMaterials) {
        this.failureMaterials = failureMaterials;
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

        final var bean = (MaterializedServiceMessage) obj;

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