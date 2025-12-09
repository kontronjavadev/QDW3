package com.kontron.qdw.domain.service;

import jakarta.validation.constraints.*;
import com.kontron.qdw.domain.serial.*;
import java.util.*;
import java.time.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "service_message_tab")
@NamedQuery(name = ServiceMessage.NQ_GET_EXTERNALSUPPLIER, query = "select b from ServiceMessage a join a.externalSupplier b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_MATERIALREVISION, query = "select b from ServiceMessage a join a.materialRevision b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_PLANT, query = "select b from ServiceMessage a join a.plant b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_SERIALOBJECT, query = "select b from ServiceMessage a join a.serialObject b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_SERVICEORDER, query = "select b from ServiceMessage a join a.serviceOrder b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_FAILUREMATERIALS, query = "select b from ServiceMessage a join a.failureMaterials b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_FAULTANALYSIS, query = "select b from ServiceMessage a join a.faultAnalysis b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_RMATYPE, query = "select b from ServiceMessage a join a.rMAType b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_REPAIRERRORCODE, query = "select b from ServiceMessage a join a.repairErrorCode b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_REPAIRLOCATION, query = "select b from ServiceMessage a join a.repairLocation b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_REPAIRSERVICE, query = "select b from ServiceMessage a join a.repairService b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_REPAIRSTATE, query = "select b from ServiceMessage a join a.repairState b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_REPAIRTASK, query = "select b from ServiceMessage a join a.repairTask b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_X2MESSAGES, query = "select b from ServiceMessage a join a.x2Messages b where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_DELETE_ALL, query = "delete from ServiceMessage a")
@NamedQuery(name = ServiceMessage.NQ_DELETE, query = "delete from ServiceMessage a where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_GET_ALL, query = "select a from ServiceMessage a")
@NamedQuery(name = ServiceMessage.NQ_FIND, query = "select a from ServiceMessage a where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_CHECK, query = "select count(a) from ServiceMessage a where a.id = :id")
@NamedQuery(name = ServiceMessage.NQ_COUNT, query = "select count(a) from ServiceMessage a")
public class ServiceMessage extends AbstractEntityWithId {
    @Generated
    public static final String NQ_GET_SERIALOBJECT = "ServiceMessage.getSerialObject";
    @Generated
    public static final String NQ_GET_REPAIRERRORCODE = "ServiceMessage.getRepairErrorCode";
    @Generated
    public static final String NQ_GET_X2MESSAGES = "ServiceMessage.getX2Messages";
    @Generated
    public static final String NQ_DELETE_ALL = "ServiceMessage.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "ServiceMessage.getMaterialRevision";
    @Generated
    public static final String NQ_DELETE = "ServiceMessage.delete";
    @Generated
    public static final String NQ_GET_ALL = "ServiceMessage.getAll";
    @Generated
    public static final String NQ_GET_RMATYPE = "ServiceMessage.getRMAType";
    @Generated
    public static final String NQ_GET_REPAIRTASK = "ServiceMessage.getRepairTask";
    @Generated
    public static final String NQ_CHECK = "ServiceMessage.check";
    @Generated
    public static final String NQ_FIND = "ServiceMessage.find";
    @Generated
    public static final String NQ_GET_SERVICEORDER = "ServiceMessage.getServiceOrder";
    @Generated
    public static final String NQ_GET_FAULTANALYSIS = "ServiceMessage.getFaultAnalysis";
    @Generated
    public static final String NQ_GET_REPAIRLOCATION = "ServiceMessage.getRepairLocation";
    @Generated
    public static final String NQ_GET_EXTERNALSUPPLIER = "ServiceMessage.getExternalSupplier";
    @Generated
    public static final String NQ_COUNT = "ServiceMessage.count";
    @Generated
    public static final String NQ_GET_REPAIRSTATE = "ServiceMessage.getRepairState";
    @Generated
    public static final String NQ_GET_PLANT = "ServiceMessage.getPlant";
    @Generated
    public static final String NQ_GET_FAILUREMATERIALS = "ServiceMessage.getFailureMaterials";
    @Generated
    public static final String NQ_GET_REPAIRSERVICE = "ServiceMessage.getRepairService";
    @Column(name = "analysis_text", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"analysisText\" is illegal!")
    @Generated
    private String analysisText;
    @Column(name = "basic_finish_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate basicFinishDate;
    @Column(name = "basic_start_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate basicStartDate;
    @Column(name = "cause_text", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"causeText\" is illegal!")
    @Generated
    private String causeText;
    @Basic(optional = false)
    @Column(name = "customer_failure", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean customerFailure;
    @Column(name = "customer_report", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"customerReport\" is illegal!")
    @Generated
    private String customerReport;
    @Column(name = "defect_component", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"defectComponent\" is illegal!")
    @Generated
    private String defectComponent;
    @Column(name = "delivery_note_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"deliveryNoteNumber\" is illegal!")
    @Generated
    private String deliveryNoteNumber;
    @Column(name = "designator", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"designator\" is illegal!")
    @Generated
    private String designator;
    @Basic(optional = false)
    @Column(name = "epidemic_failure", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean epidemicFailure;
    @Column(name = "error_id", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"errorId\" is illegal!")
    @Generated
    private String errorId;
    @Column(name = "external_report", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"externalReport\" is illegal!")
    @Generated
    private String externalReport;
    @Column(name = "internal_arrival_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate internalArrivalDate;
    @Column(name = "internal_report", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"internalReport\" is illegal!")
    @Generated
    private String internalReport;
    @Column(name = "internal_shipment_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate internalShipmentDate;
    @Column(name = "origin", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"origin\" is illegal!")
    @Generated
    private String origin;
    @Basic(optional = false)
    @Column(name = "rebuild_flag", nullable = false, updatable = true, insertable = true)
    @Generated
    private int rebuildFlag;
    @Column(name = "repair_description", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"repairDescription\" is illegal!")
    @Generated
    private String repairDescription;
    @Column(name = "service_message_id", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"serviceMessageId\" is illegal!")
    @Generated
    private String serviceMessageId;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "external_supplier", referencedColumnName = "code", nullable = true)
    @Generated
    private Supplier externalSupplier;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_revision", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"materialRevision\" must not be null!")
    @Generated
    private MaterialRevision materialRevision;
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
    @JoinColumn(name = "service_order", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"serviceOrder\" must not be null!")
    @Generated
    private ServiceOrder serviceOrder;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "service_message_materials_tab", joinColumns = { @JoinColumn(name = "service_message_pk") }, inverseJoinColumns = {
            @JoinColumn(name = "material_pk") })
    @Generated
    private Collection<Material> failureMaterials = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "fault_analysis", referencedColumnName = "code", nullable = true)
    @Generated
    private FaultAnalysis faultAnalysis;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "rma_type", referencedColumnName = "code", nullable = true)
    @Generated
    private RMAType rMAType;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "repair_error_code", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairErrorCode repairErrorCode;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "repair_location", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairLocation repairLocation;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "repair_service", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairService repairService;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repair_state", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"repairState\" must not be null!")
    @Generated
    private RepairState repairState;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "repair_task", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairTask repairTask;
    @OneToMany(targetEntity = X2Message.class, mappedBy = "serviceMessage", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<X2Message> x2Messages = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public ServiceMessage() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public ServiceMessage(long id) {
        super(id);
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
     * @return the basic finish date
     */
    @Generated
    public LocalDate getBasicFinishDate() {
        return this.basicFinishDate;
    }

    /**
     * @param basicFinishDate the basic finish date to set
     */
    @Generated
    public void setBasicFinishDate(LocalDate basicFinishDate) {
        this.basicFinishDate = basicFinishDate;
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
     * @return the cause text
     */
    @Generated
    public String getCauseText() {
        return this.causeText;
    }

    /**
     * @param causeText the cause text to set
     */
    @Generated
    public void setCauseText(String causeText) {
        this.causeText = causeText;
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
     * @return the rebuild flag
     */
    @Generated
    public int getRebuildFlag() {
        return this.rebuildFlag;
    }

    /**
     * @param rebuildFlag the rebuild flag to set
     */
    @Generated
    public void setRebuildFlag(int rebuildFlag) {
        this.rebuildFlag = rebuildFlag;
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
     * @return the service message id
     */
    @Generated
    public String getServiceMessageId() {
        return this.serviceMessageId;
    }

    /**
     * @param serviceMessageId the service message id to set
     */
    @Generated
    public void setServiceMessageId(String serviceMessageId) {
        this.serviceMessageId = serviceMessageId;
    }

    /**
     * @return the supplier
     */
    @Generated
    public Supplier getExternalSupplier() {
        return this.externalSupplier;
    }

    /**
     * @param externalSupplier the supplier to set
     */
    @Generated
    public void setExternalSupplier(Supplier externalSupplier) {
        this.externalSupplier = externalSupplier;
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
     * @return the service order
     */
    @Generated
    public ServiceOrder getServiceOrder() {
        return this.serviceOrder;
    }

    /**
     * @param serviceOrder the service order to set
     */
    @Generated
    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
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
     * @return the fault analysis
     */
    @Generated
    public FaultAnalysis getFaultAnalysis() {
        return this.faultAnalysis;
    }

    /**
     * @param faultAnalysis the fault analysis to set
     */
    @Generated
    public void setFaultAnalysis(FaultAnalysis faultAnalysis) {
        this.faultAnalysis = faultAnalysis;
    }

    /**
     * @return the RMA type
     */
    @Generated
    public RMAType getrMAType() {
        return this.rMAType;
    }

    /**
     * @param rMAType the RMA type to set
     */
    @Generated
    public void setrMAType(RMAType rMAType) {
        this.rMAType = rMAType;
    }

    /**
     * @return the repair error code
     */
    @Generated
    public RepairErrorCode getRepairErrorCode() {
        return this.repairErrorCode;
    }

    /**
     * @param repairErrorCode the repair error code to set
     */
    @Generated
    public void setRepairErrorCode(RepairErrorCode repairErrorCode) {
        this.repairErrorCode = repairErrorCode;
    }

    /**
     * @return the repair location
     */
    @Generated
    public RepairLocation getRepairLocation() {
        return this.repairLocation;
    }

    /**
     * @param repairLocation the repair location to set
     */
    @Generated
    public void setRepairLocation(RepairLocation repairLocation) {
        this.repairLocation = repairLocation;
    }

    /**
     * @return the repair service
     */
    @Generated
    public RepairService getRepairService() {
        return this.repairService;
    }

    /**
     * @param repairService the repair service to set
     */
    @Generated
    public void setRepairService(RepairService repairService) {
        this.repairService = repairService;
    }

    /**
     * @return the repair state
     */
    @Generated
    public RepairState getRepairState() {
        return this.repairState;
    }

    /**
     * @param repairState the repair state to set
     */
    @Generated
    public void setRepairState(RepairState repairState) {
        this.repairState = repairState;
    }

    /**
     * @return the repair task
     */
    @Generated
    public RepairTask getRepairTask() {
        return this.repairTask;
    }

    /**
     * @param repairTask the repair task to set
     */
    @Generated
    public void setRepairTask(RepairTask repairTask) {
        this.repairTask = repairTask;
    }

    /**
     * @return a collection of X2 messages
     */
    @Generated
    public Collection<X2Message> getX2Messages() {
        return this.x2Messages;
    }

    /**
     * @param x2Messages the X2 messages to set
     */
    @Generated
    public void setX2Messages(Collection<X2Message> x2Messages) {
        this.x2Messages = x2Messages;
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

        final var bean = (ServiceMessage) obj;

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