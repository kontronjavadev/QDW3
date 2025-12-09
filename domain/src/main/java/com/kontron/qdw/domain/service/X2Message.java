package com.kontron.qdw.domain.service;

import jakarta.validation.constraints.*;
import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;
import com.kontron.qdw.domain.material.*;

@Entity
@Table(name = "x_2_message_tab")
@NamedQuery(name = X2Message.NQ_GET_DEFECTMATERIAL, query = "select b from X2Message a join a.defectMaterial b where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_FAULTANALYSIS, query = "select b from X2Message a join a.faultAnalysis b where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_MATERIALREVISION, query = "select b from X2Message a join a.materialRevision b where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_REPAIRERRORCODE, query = "select b from X2Message a join a.repairErrorCode b where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_REPAIRSTATE, query = "select b from X2Message a join a.repairState b where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_REPAIRTASK, query = "select b from X2Message a join a.repairTask b where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_SERIALOBJECT, query = "select b from X2Message a join a.serialObject b where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_SERVICEMESSAGE, query = "select b from X2Message a join a.serviceMessage b where a.id = :id")
@NamedQuery(name = X2Message.NQ_DELETE_ALL, query = "delete from X2Message a")
@NamedQuery(name = X2Message.NQ_DELETE, query = "delete from X2Message a where a.id = :id")
@NamedQuery(name = X2Message.NQ_GET_ALL, query = "select a from X2Message a")
@NamedQuery(name = X2Message.NQ_FIND, query = "select a from X2Message a where a.id = :id")
@NamedQuery(name = X2Message.NQ_CHECK, query = "select count(a) from X2Message a where a.id = :id")
@NamedQuery(name = X2Message.NQ_COUNT, query = "select count(a) from X2Message a")
public class X2Message extends AbstractEntityWithId {
    @Generated
    public static final String NQ_GET_SERIALOBJECT = "X2Message.getSerialObject";
    @Generated
    public static final String NQ_GET_REPAIRERRORCODE = "X2Message.getRepairErrorCode";
    @Generated
    public static final String NQ_DELETE_ALL = "X2Message.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "X2Message.getMaterialRevision";
    @Generated
    public static final String NQ_DELETE = "X2Message.delete";
    @Generated
    public static final String NQ_GET_ALL = "X2Message.getAll";
    @Generated
    public static final String NQ_GET_REPAIRTASK = "X2Message.getRepairTask";
    @Generated
    public static final String NQ_CHECK = "X2Message.check";
    @Generated
    public static final String NQ_FIND = "X2Message.find";
    @Generated
    public static final String NQ_GET_SERVICEMESSAGE = "X2Message.getServiceMessage";
    @Generated
    public static final String NQ_GET_FAULTANALYSIS = "X2Message.getFaultAnalysis";
    @Generated
    public static final String NQ_GET_DEFECTMATERIAL = "X2Message.getDefectMaterial";
    @Generated
    public static final String NQ_COUNT = "X2Message.count";
    @Generated
    public static final String NQ_GET_REPAIRSTATE = "X2Message.getRepairState";
    @Column(name = "analysis_text", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"analysisText\" is illegal!")
    @Generated
    private String analysisText;
    @Column(name = "cause_text", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"causeText\" is illegal!")
    @Generated
    private String causeText;
    @Column(name = "customer_report", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"customerReport\" is illegal!")
    @Generated
    private String customerReport;
    @Column(name = "defect_component", nullable = true, updatable = true, insertable = true, length = 100)
    @Size(max = 100, message = "Length of field \"defectComponent\" is illegal!")
    @Generated
    private String defectComponent;
    @Column(name = "designator", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"designator\" is illegal!")
    @Generated
    private String designator;
    @Column(name = "work_center", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"workCenter\" is illegal!")
    @Generated
    private String workCenter;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "defect_material", referencedColumnName = "id", nullable = true)
    @Generated
    private Material defectMaterial;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "fault_analysis", referencedColumnName = "code", nullable = true)
    @Generated
    private FaultAnalysis faultAnalysis;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_revision", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"materialRevision\" must not be null!")
    @Generated
    private MaterialRevision materialRevision;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "repair_error_code", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairErrorCode repairErrorCode;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repair_state", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"repairState\" must not be null!")
    @Generated
    private RepairState repairState;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "repair_task", referencedColumnName = "code", nullable = true)
    @Generated
    private RepairTask repairTask;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serial_object", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"serialObject\" must not be null!")
    @Generated
    private SerialObject serialObject;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_message", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"serviceMessage\" must not be null!")
    @Generated
    private ServiceMessage serviceMessage;

    /**
     * Default constructor
     */
    @Generated
    public X2Message() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public X2Message(long id) {
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
     * @return the work center
     */
    @Generated
    public String getWorkCenter() {
        return this.workCenter;
    }

    /**
     * @param workCenter the work center to set
     */
    @Generated
    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }

    /**
     * @return the material
     */
    @Generated
    public Material getDefectMaterial() {
        return this.defectMaterial;
    }

    /**
     * @param defectMaterial the material to set
     */
    @Generated
    public void setDefectMaterial(Material defectMaterial) {
        this.defectMaterial = defectMaterial;
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
     * @return the service message
     */
    @Generated
    public ServiceMessage getServiceMessage() {
        return this.serviceMessage;
    }

    /**
     * @param serviceMessage the service message to set
     */
    @Generated
    public void setServiceMessage(ServiceMessage serviceMessage) {
        this.serviceMessage = serviceMessage;
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

        final var bean = (X2Message) obj;

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