package com.kontron.qdw.dto.service;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ServiceMessageX2MessagesDTO implements Serializable {
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
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_REPAIRSTATECODE = "repairStateCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_SERIALOBJECTID = "serialObjectId";
    @Generated
    public static final String ATTR_SERVICEMESSAGEID = "serviceMessageId";
    @Generated
    public static final String ATTR_SERIALOBJECTSERIALNUMBER = "serialObjectSerialNumber";
    @Generated
    public static final String ATTR_MATREVMATID = "matrevMatId";
    @Generated
    public static final String ATTR_MATREVMATMATERIALNUMBER = "matrevMatMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matrevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matrevRevisionNumber";
    @Generated
    public static final String ATTR_DEFECTMATERIALID = "defectMaterialId";
    @Generated
    public static final String ATTR_DEFECTMATERIALMATERIALNUMBER = "defectMaterialMaterialNumber";
    @Generated
    public static final String ATTR_FAULTANALYSISCODE = "faultAnalysisCode";
    @Generated
    public static final String ATTR_REPAIRERRORCODECODE = "repairErrorCodeCode";
    @Generated
    public static final String ATTR_REPAIRTASKCODE = "repairTaskCode";
    @Generated
    public static final String SELECT_ANALYSISTEXT = "a.analysisText";
    @Generated
    public static final String SELECT_CAUSETEXT = "a.causeText";
    @Generated
    public static final String SELECT_CUSTOMERREPORT = "a.customerReport";
    @Generated
    public static final String SELECT_DEFECTCOMPONENT = "a.defectComponent";
    @Generated
    public static final String SELECT_DESIGNATOR = "a.designator";
    @Generated
    public static final String SELECT_WORKCENTER = "a.workCenter";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_REPAIRSTATECODE = "f.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "d.id";
    @Generated
    public static final String SELECT_SERIALOBJECTID = "h.id";
    @Generated
    public static final String SELECT_SERVICEMESSAGEID = "i.id";
    @Generated
    public static final String SELECT_SERIALOBJECTSERIALNUMBER = "h.serialNumber";
    @Generated
    public static final String SELECT_MATREVMATID = "p.id";
    @Generated
    public static final String SELECT_MATREVMATMATERIALNUMBER = "p.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATSAPNUMBER = "p.sapNumber";
    @Generated
    public static final String SELECT_MATREVREVISIONNUMBER = "d.revisionNumber";
    @Generated
    public static final String SELECT_DEFECTMATERIALID = "b.id";
    @Generated
    public static final String SELECT_DEFECTMATERIALMATERIALNUMBER = "b.materialNumber";
    @Generated
    public static final String SELECT_FAULTANALYSISCODE = "c.code";
    @Generated
    public static final String SELECT_REPAIRERRORCODECODE = "e.code";
    @Generated
    public static final String SELECT_REPAIRTASKCODE = "g.code";
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
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String repairStateCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private long serialObjectId;
    @Generated
    private long serviceMessageId;
    @Generated
    private String serialObjectSerialNumber;
    @Generated
    private long matrevMatId;
    @Generated
    private String matrevMatMaterialNumber;
    @Generated
    private String matrevMatSapNumber;
    @Generated
    private String matrevRevisionNumber;
    @Generated
    private Long defectMaterialId;
    @Generated
    private String defectMaterialMaterialNumber;
    @Generated
    private String faultAnalysisCode;
    @Generated
    private String repairErrorCodeCode;
    @Generated
    private String repairTaskCode;

    /**
     * Default constructor
     */
    @Generated
    public ServiceMessageX2MessagesDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public ServiceMessageX2MessagesDTO(long id) {
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
     * @param creationDate
     * @param lastUpdate
     * @param repairStateCode
     * @param materialRevisionId
     * @param serialObjectId
     * @param serviceMessageId
     * @param serialObjectSerialNumber
     * @param matrevMatId
     * @param matrevMatMaterialNumber
     * @param matrevMatSapNumber
     * @param matrevRevisionNumber
     * @param defectMaterialId
     * @param defectMaterialMaterialNumber
     * @param faultAnalysisCode
     * @param repairErrorCodeCode
     * @param repairTaskCode
     */
    @Generated
    public ServiceMessageX2MessagesDTO(String analysisText, String causeText, String customerReport, String defectComponent, String designator,
            String workCenter, long id, LocalDateTime creationDate, LocalDateTime lastUpdate, String repairStateCode, long materialRevisionId,
            long serialObjectId, long serviceMessageId, String serialObjectSerialNumber, long matrevMatId, String matrevMatMaterialNumber,
            String matrevMatSapNumber, String matrevRevisionNumber, Long defectMaterialId, String defectMaterialMaterialNumber,
            String faultAnalysisCode, String repairErrorCodeCode, String repairTaskCode) {
        this.analysisText = analysisText;
        this.causeText = causeText;
        this.customerReport = customerReport;
        this.defectComponent = defectComponent;
        this.designator = designator;
        this.workCenter = workCenter;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.repairStateCode = repairStateCode;
        this.materialRevisionId = materialRevisionId;
        this.serialObjectId = serialObjectId;
        this.serviceMessageId = serviceMessageId;
        this.serialObjectSerialNumber = serialObjectSerialNumber;
        this.matrevMatId = matrevMatId;
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
        this.matrevMatSapNumber = matrevMatSapNumber;
        this.matrevRevisionNumber = matrevRevisionNumber;
        this.defectMaterialId = defectMaterialId;
        this.defectMaterialMaterialNumber = defectMaterialMaterialNumber;
        this.faultAnalysisCode = faultAnalysisCode;
        this.repairErrorCodeCode = repairErrorCodeCode;
        this.repairTaskCode = repairTaskCode;
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
     * @return the code of the repair state
     */
    @Generated
    public String getRepairStateCode() {
        return this.repairStateCode;
    }

    /**
     * @param repairStateCode the code of the repair state to set
     */
    @Generated
    public void setRepairStateCode(String repairStateCode) {
        this.repairStateCode = repairStateCode;
    }

    /**
     * @return the id of the material revision
     */
    @Generated
    public long getMaterialRevisionId() {
        return this.materialRevisionId;
    }

    /**
     * @param materialRevisionId the id of the material revision to set
     */
    @Generated
    public void setMaterialRevisionId(long materialRevisionId) {
        this.materialRevisionId = materialRevisionId;
    }

    /**
     * @return the id of the serial object
     */
    @Generated
    public long getSerialObjectId() {
        return this.serialObjectId;
    }

    /**
     * @param serialObjectId the id of the serial object to set
     */
    @Generated
    public void setSerialObjectId(long serialObjectId) {
        this.serialObjectId = serialObjectId;
    }

    /**
     * @return the id of the service message
     */
    @Generated
    public long getServiceMessageId() {
        return this.serviceMessageId;
    }

    /**
     * @param serviceMessageId the id of the service message to set
     */
    @Generated
    public void setServiceMessageId(long serviceMessageId) {
        this.serviceMessageId = serviceMessageId;
    }

    /**
     * @return the serial number of the serial object
     */
    @Generated
    public String getSerialObjectSerialNumber() {
        return this.serialObjectSerialNumber;
    }

    /**
     * @param serialObjectSerialNumber the serial number of the serial object to set
     */
    @Generated
    public void setSerialObjectSerialNumber(String serialObjectSerialNumber) {
        this.serialObjectSerialNumber = serialObjectSerialNumber;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public long getMatrevMatId() {
        return this.matrevMatId;
    }

    /**
     * @param matrevMatId the id of the material to set
     */
    @Generated
    public void setMatrevMatId(long matrevMatId) {
        this.matrevMatId = matrevMatId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getMatrevMatMaterialNumber() {
        return this.matrevMatMaterialNumber;
    }

    /**
     * @param matrevMatMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMatrevMatMaterialNumber(String matrevMatMaterialNumber) {
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMatrevMatSapNumber() {
        return this.matrevMatSapNumber;
    }

    /**
     * @param matrevMatSapNumber the sap number of the material to set
     */
    @Generated
    public void setMatrevMatSapNumber(String matrevMatSapNumber) {
        this.matrevMatSapNumber = matrevMatSapNumber;
    }

    /**
     * @return the revision number of the material revision
     */
    @Generated
    public String getMatrevRevisionNumber() {
        return this.matrevRevisionNumber;
    }

    /**
     * @param matrevRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setMatrevRevisionNumber(String matrevRevisionNumber) {
        this.matrevRevisionNumber = matrevRevisionNumber;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public Long getDefectMaterialId() {
        return this.defectMaterialId;
    }

    /**
     * @param defectMaterialId the id of the material to set
     */
    @Generated
    public void setDefectMaterialId(Long defectMaterialId) {
        this.defectMaterialId = defectMaterialId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getDefectMaterialMaterialNumber() {
        return this.defectMaterialMaterialNumber;
    }

    /**
     * @param defectMaterialMaterialNumber the material number of the material to set
     */
    @Generated
    public void setDefectMaterialMaterialNumber(String defectMaterialMaterialNumber) {
        this.defectMaterialMaterialNumber = defectMaterialMaterialNumber;
    }

    /**
     * @return the code of the fault analysis
     */
    @Generated
    public String getFaultAnalysisCode() {
        return this.faultAnalysisCode;
    }

    /**
     * @param faultAnalysisCode the code of the fault analysis to set
     */
    @Generated
    public void setFaultAnalysisCode(String faultAnalysisCode) {
        this.faultAnalysisCode = faultAnalysisCode;
    }

    /**
     * @return the code of the repair error code
     */
    @Generated
    public String getRepairErrorCodeCode() {
        return this.repairErrorCodeCode;
    }

    /**
     * @param repairErrorCodeCode the code of the repair error code to set
     */
    @Generated
    public void setRepairErrorCodeCode(String repairErrorCodeCode) {
        this.repairErrorCodeCode = repairErrorCodeCode;
    }

    /**
     * @return the code of the repair task
     */
    @Generated
    public String getRepairTaskCode() {
        return this.repairTaskCode;
    }

    /**
     * @param repairTaskCode the code of the repair task to set
     */
    @Generated
    public void setRepairTaskCode(String repairTaskCode) {
        this.repairTaskCode = repairTaskCode;
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

        final var dto = (ServiceMessageX2MessagesDTO) obj;

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