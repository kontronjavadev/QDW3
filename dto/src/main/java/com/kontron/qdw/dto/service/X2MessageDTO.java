package com.kontron.qdw.dto.service;

import com.kontron.qdw.dto.serial.*;
import java.time.*;
import java.io.Serializable;
import com.kontron.qdw.dto.material.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class X2MessageDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ANALYSISTEXT = "analysisText";
    @Generated
    public static final String ATTR_CAUSETEXT = "causeText";
    @Generated
    public static final String ATTR_CUSTOMERREPORT = "customerReport";
    @Generated
    public static final String ATTR_DEFECTCOMPONENT = "defectComponent";
    @Generated
    public static final String ATTR_DESIGNATOR = "designator";
    @Generated
    public static final String ATTR_WORKCENTER = "workCenter";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_DEFECTMATERIAL = "defectMaterial";
    @Generated
    public static final String ATTR_FAULTANALYSIS = "faultAnalysis";
    @Generated
    public static final String ATTR_MATERIALREVISION = "materialRevision";
    @Generated
    public static final String ATTR_REPAIRERRORCODE = "repairErrorCode";
    @Generated
    public static final String ATTR_REPAIRSTATE = "repairState";
    @Generated
    public static final String ATTR_REPAIRTASK = "repairTask";
    @Generated
    public static final String ATTR_SERIALOBJECT = "serialObject";
    @Generated
    public static final String ATTR_SERVICEMESSAGE = "serviceMessage";
    @Generated
    public static final String ATTR_MATREVMATERIAL = "matrevMaterial";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
    @Generated
    private String analysisText;
    @Generated
    private String causeText;
    @Generated
    private String customerReport;
    @Generated
    private String defectComponent;
    @Generated
    private String designator;
    @Generated
    private String workCenter;
    @Generated
    private long id;
    @Generated
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private MaterialListDTO defectMaterial;
    @Generated
    private FaultAnalysisListDTO faultAnalysis;
    @Generated
    private MaterialRevisionListDTO materialRevision;
    @Generated
    private RepairErrorCodeListDTO repairErrorCode;
    @Generated
    private RepairStateListDTO repairState;
    @Generated
    private RepairTaskListDTO repairTask;
    @Generated
    private SerialObjectListDTO serialObject;
    @Generated
    private ServiceMessageListDTO serviceMessage;
    @Generated
    private MaterialListDTO matrevMaterial;
    @Generated
    private String materialSapNumber;

    /**
     * Default constructor
     */
    @Generated
    public X2MessageDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public X2MessageDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param analysisText
     * @param causeText
     * @param customerReport
     * @param defectComponent
     * @param designator
     * @param workCenter
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     * @param materialSapNumber
     */
    @Generated
    public X2MessageDTO(String analysisText, String causeText, String customerReport, String defectComponent, String designator, String workCenter,
            long id, int version, LocalDateTime creationDate, LocalDateTime lastUpdate, String materialSapNumber) {
        this.analysisText = analysisText;
        this.causeText = causeText;
        this.customerReport = customerReport;
        this.defectComponent = defectComponent;
        this.designator = designator;
        this.workCenter = workCenter;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.materialSapNumber = materialSapNumber;
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
     * @return the id
     */
    @Generated
    public long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    @Generated
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the version
     */
    @Generated
    public int getVersion() {
        return this.version;
    }

    /**
     * @param version the version to set
     */
    @Generated
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return the creation date
     */
    @Generated
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @param creationDate the creation date to set
     */
    @Generated
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the last update
     */
    @Generated
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * @param lastUpdate the last update to set
     */
    @Generated
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the material
     */
    @Generated
    public MaterialListDTO getDefectMaterial() {
        return this.defectMaterial;
    }

    /**
     * @param defectMaterial the material to set
     */
    @Generated
    public void setDefectMaterial(MaterialListDTO defectMaterial) {
        this.defectMaterial = defectMaterial;
    }

    /**
     * @return the fault analysis
     */
    @Generated
    public FaultAnalysisListDTO getFaultAnalysis() {
        return this.faultAnalysis;
    }

    /**
     * @param faultAnalysis the fault analysis to set
     */
    @Generated
    public void setFaultAnalysis(FaultAnalysisListDTO faultAnalysis) {
        this.faultAnalysis = faultAnalysis;
    }

    /**
     * @return the material revision
     */
    @Generated
    public MaterialRevisionListDTO getMaterialRevision() {
        return this.materialRevision;
    }

    /**
     * @param materialRevision the material revision to set
     */
    @Generated
    public void setMaterialRevision(MaterialRevisionListDTO materialRevision) {
        this.materialRevision = materialRevision;
    }

    /**
     * @return the repair error code
     */
    @Generated
    public RepairErrorCodeListDTO getRepairErrorCode() {
        return this.repairErrorCode;
    }

    /**
     * @param repairErrorCode the repair error code to set
     */
    @Generated
    public void setRepairErrorCode(RepairErrorCodeListDTO repairErrorCode) {
        this.repairErrorCode = repairErrorCode;
    }

    /**
     * @return the repair state
     */
    @Generated
    public RepairStateListDTO getRepairState() {
        return this.repairState;
    }

    /**
     * @param repairState the repair state to set
     */
    @Generated
    public void setRepairState(RepairStateListDTO repairState) {
        this.repairState = repairState;
    }

    /**
     * @return the repair task
     */
    @Generated
    public RepairTaskListDTO getRepairTask() {
        return this.repairTask;
    }

    /**
     * @param repairTask the repair task to set
     */
    @Generated
    public void setRepairTask(RepairTaskListDTO repairTask) {
        this.repairTask = repairTask;
    }

    /**
     * @return the serial object
     */
    @Generated
    public SerialObjectListDTO getSerialObject() {
        return this.serialObject;
    }

    /**
     * @param serialObject the serial object to set
     */
    @Generated
    public void setSerialObject(SerialObjectListDTO serialObject) {
        this.serialObject = serialObject;
    }

    /**
     * @return the service message
     */
    @Generated
    public ServiceMessageListDTO getServiceMessage() {
        return this.serviceMessage;
    }

    /**
     * @param serviceMessage the service message to set
     */
    @Generated
    public void setServiceMessage(ServiceMessageListDTO serviceMessage) {
        this.serviceMessage = serviceMessage;
    }

    /**
     * @return the material
     */
    @Generated
    public MaterialListDTO getMatrevMaterial() {
        return this.matrevMaterial;
    }

    /**
     * @param matrevMaterial the material to set
     */
    @Generated
    public void setMatrevMaterial(MaterialListDTO matrevMaterial) {
        this.matrevMaterial = matrevMaterial;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMaterialSapNumber() {
        return this.materialSapNumber;
    }

    /**
     * @param materialSapNumber the sap number of the material to set
     */
    @Generated
    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
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

        final var dto = (X2MessageDTO) obj;

        return this.id == dto.getId();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}