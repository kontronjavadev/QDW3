package com.kontron.qdw.dto.service;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ServiceOrderServiceMessagesDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ANALYSISTEXT = "analysisText";
    @Generated
    public static final String ATTR_BASICFINISHDATE = "basicFinishDate";
    @Generated
    public static final String ATTR_BASICSTARTDATE = "basicStartDate";
    @Generated
    public static final String ATTR_CAUSETEXT = "causeText";
    @Generated
    public static final String ATTR_CUSTOMERFAILURE = "customerFailure";
    @Generated
    public static final String ATTR_DEFECTCOMPONENT = "defectComponent";
    @Generated
    public static final String ATTR_DELIVERYNOTENUMBER = "deliveryNoteNumber";
    @Generated
    public static final String ATTR_DESIGNATOR = "designator";
    @Generated
    public static final String ATTR_EPIDEMICFAILURE = "epidemicFailure";
    @Generated
    public static final String ATTR_ERRORID = "errorId";
    @Generated
    public static final String ATTR_INTERNALARRIVALDATE = "internalArrivalDate";
    @Generated
    public static final String ATTR_INTERNALSHIPMENTDATE = "internalShipmentDate";
    @Generated
    public static final String ATTR_ORIGIN = "origin";
    @Generated
    public static final String ATTR_REPAIRDESCRIPTION = "repairDescription";
    @Generated
    public static final String ATTR_SERVICEMESSAGEID = "serviceMessageId";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_PLANTCODE = "plantCode";
    @Generated
    public static final String ATTR_SERVICEORDERCODE = "serviceOrderCode";
    @Generated
    public static final String ATTR_REPAIRSTATECODE = "repairStateCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_SERIALOBJECTID = "serialObjectId";
    @Generated
    public static final String ATTR_SERIALOBJECTSERIALNUMBER = "serialObjectSerialNumber";
    @Generated
    public static final String ATTR_MATREVMATERIALID = "matrevMaterialId";
    @Generated
    public static final String ATTR_MATREVMATMATERIALNUMBER = "matrevMatMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matrevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matrevRevisionNumber";
    @Generated
    public static final String ATTR_RMATYPECODE = "rMATypeCode";
    @Generated
    public static final String ATTR_REPAIRSERVICECODE = "repairServiceCode";
    @Generated
    public static final String ATTR_REPAIRLOCATIONCODE = "repairLocationCode";
    @Generated
    public static final String ATTR_REPAIRERRORCODECODE = "repairErrorCodeCode";
    @Generated
    public static final String ATTR_REPAIRERRORCODEGROUPNAME = "repairErrorCodeGroupName";
    @Generated
    public static final String ATTR_REPAIRTASKCODE = "repairTaskCode";
    @Generated
    public static final String ATTR_REPAIRTASKSHORTTEXT = "repairTaskShortText";
    @Generated
    public static final String ATTR_FAULTANALYSISCODE = "faultAnalysisCode";
    @Generated
    public static final String ATTR_FAULTANALYSISSHORTTEXT = "faultAnalysisShortText";
    @Generated
    public static final String ATTR_EXTERNALSUPPLIERCODE = "externalSupplierCode";
    @Generated
    public static final String ATTR_EXTERNALSUPPLIERNAME = "externalSupplierName";
    @Generated
    public static final String SELECT_ANALYSISTEXT = "a.analysisText";
    @Generated
    public static final String SELECT_BASICFINISHDATE = "a.basicFinishDate";
    @Generated
    public static final String SELECT_BASICSTARTDATE = "a.basicStartDate";
    @Generated
    public static final String SELECT_CAUSETEXT = "a.causeText";
    @Generated
    public static final String SELECT_CUSTOMERFAILURE = "a.customerFailure";
    @Generated
    public static final String SELECT_DEFECTCOMPONENT = "a.defectComponent";
    @Generated
    public static final String SELECT_DELIVERYNOTENUMBER = "a.deliveryNoteNumber";
    @Generated
    public static final String SELECT_DESIGNATOR = "a.designator";
    @Generated
    public static final String SELECT_EPIDEMICFAILURE = "a.epidemicFailure";
    @Generated
    public static final String SELECT_ERRORID = "a.errorId";
    @Generated
    public static final String SELECT_INTERNALARRIVALDATE = "a.internalArrivalDate";
    @Generated
    public static final String SELECT_INTERNALSHIPMENTDATE = "a.internalShipmentDate";
    @Generated
    public static final String SELECT_ORIGIN = "a.origin";
    @Generated
    public static final String SELECT_REPAIRDESCRIPTION = "a.repairDescription";
    @Generated
    public static final String SELECT_SERVICEMESSAGEID = "a.serviceMessageId";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_PLANTCODE = "d.code";
    @Generated
    public static final String SELECT_SERVICEORDERCODE = "f.code";
    @Generated
    public static final String SELECT_REPAIRSTATECODE = "m.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "c.id";
    @Generated
    public static final String SELECT_SERIALOBJECTID = "e.id";
    @Generated
    public static final String SELECT_SERIALOBJECTSERIALNUMBER = "e.serialNumber";
    @Generated
    public static final String SELECT_MATREVMATERIALID = "u.id";
    @Generated
    public static final String SELECT_MATREVMATMATERIALNUMBER = "u.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATSAPNUMBER = "u.sapNumber";
    @Generated
    public static final String SELECT_MATREVREVISIONNUMBER = "c.revisionNumber";
    @Generated
    public static final String SELECT_RMATYPECODE = "i.code";
    @Generated
    public static final String SELECT_REPAIRSERVICECODE = "l.code";
    @Generated
    public static final String SELECT_REPAIRLOCATIONCODE = "k.code";
    @Generated
    public static final String SELECT_REPAIRERRORCODECODE = "j.code";
    @Generated
    public static final String SELECT_REPAIRERRORCODEGROUPNAME = "j.groupName";
    @Generated
    public static final String SELECT_REPAIRTASKCODE = "n.code";
    @Generated
    public static final String SELECT_REPAIRTASKSHORTTEXT = "n.shortText";
    @Generated
    public static final String SELECT_FAULTANALYSISCODE = "h.code";
    @Generated
    public static final String SELECT_FAULTANALYSISSHORTTEXT = "h.shortText";
    @Generated
    public static final String SELECT_EXTERNALSUPPLIERCODE = "b.code";
    @Generated
    public static final String SELECT_EXTERNALSUPPLIERNAME = "b.name";
    @Generated
    private String analysisText;
    @Generated
    private LocalDate basicFinishDate;
    @Generated
    private LocalDate basicStartDate;
    @Generated
    private String causeText;
    @Generated
    private boolean customerFailure;
    @Generated
    private String defectComponent;
    @Generated
    private String deliveryNoteNumber;
    @Generated
    private String designator;
    @Generated
    private boolean epidemicFailure;
    @Generated
    private String errorId;
    @Generated
    private LocalDate internalArrivalDate;
    @Generated
    private LocalDate internalShipmentDate;
    @Generated
    private String origin;
    @Generated
    private String repairDescription;
    @Generated
    private String serviceMessageId;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String plantCode;
    @Generated
    private String serviceOrderCode;
    @Generated
    private String repairStateCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private long serialObjectId;
    @Generated
    private String serialObjectSerialNumber;
    @Generated
    private long matrevMaterialId;
    @Generated
    private String matrevMatMaterialNumber;
    @Generated
    private String matrevMatSapNumber;
    @Generated
    private String matrevRevisionNumber;
    @Generated
    private String rMATypeCode;
    @Generated
    private String repairServiceCode;
    @Generated
    private String repairLocationCode;
    @Generated
    private String repairErrorCodeCode;
    @Generated
    private String repairErrorCodeGroupName;
    @Generated
    private String repairTaskCode;
    @Generated
    private String repairTaskShortText;
    @Generated
    private String faultAnalysisCode;
    @Generated
    private String faultAnalysisShortText;
    @Generated
    private String externalSupplierCode;
    @Generated
    private String externalSupplierName;

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrderServiceMessagesDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public ServiceOrderServiceMessagesDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param analysisText
     * @param basicFinishDate
     * @param basicStartDate
     * @param causeText
     * @param customerFailure
     * @param defectComponent
     * @param deliveryNoteNumber
     * @param designator
     * @param epidemicFailure
     * @param errorId
     * @param internalArrivalDate
     * @param internalShipmentDate
     * @param origin
     * @param repairDescription
     * @param serviceMessageId
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param plantCode
     * @param serviceOrderCode
     * @param repairStateCode
     * @param materialRevisionId
     * @param serialObjectId
     * @param serialObjectSerialNumber
     * @param matrevMaterialId
     * @param matrevMatMaterialNumber
     * @param matrevMatSapNumber
     * @param matrevRevisionNumber
     * @param rMATypeCode
     * @param repairServiceCode
     * @param repairLocationCode
     * @param repairErrorCodeCode
     * @param repairErrorCodeGroupName
     * @param repairTaskCode
     * @param repairTaskShortText
     * @param faultAnalysisCode
     * @param faultAnalysisShortText
     * @param externalSupplierCode
     * @param externalSupplierName
     */
    @Generated
    public ServiceOrderServiceMessagesDTO(String analysisText, LocalDate basicFinishDate, LocalDate basicStartDate, String causeText,
            boolean customerFailure, String defectComponent, String deliveryNoteNumber, String designator, boolean epidemicFailure, String errorId,
            LocalDate internalArrivalDate, LocalDate internalShipmentDate, String origin, String repairDescription, String serviceMessageId, long id,
            LocalDateTime creationDate, LocalDateTime lastUpdate, String plantCode, String serviceOrderCode, String repairStateCode,
            long materialRevisionId, long serialObjectId, String serialObjectSerialNumber, long matrevMaterialId, String matrevMatMaterialNumber,
            String matrevMatSapNumber, String matrevRevisionNumber, String rMATypeCode, String repairServiceCode, String repairLocationCode,
            String repairErrorCodeCode, String repairErrorCodeGroupName, String repairTaskCode, String repairTaskShortText, String faultAnalysisCode,
            String faultAnalysisShortText, String externalSupplierCode, String externalSupplierName) {
        this.analysisText = analysisText;
        this.basicFinishDate = basicFinishDate;
        this.basicStartDate = basicStartDate;
        this.causeText = causeText;
        this.customerFailure = customerFailure;
        this.defectComponent = defectComponent;
        this.deliveryNoteNumber = deliveryNoteNumber;
        this.designator = designator;
        this.epidemicFailure = epidemicFailure;
        this.errorId = errorId;
        this.internalArrivalDate = internalArrivalDate;
        this.internalShipmentDate = internalShipmentDate;
        this.origin = origin;
        this.repairDescription = repairDescription;
        this.serviceMessageId = serviceMessageId;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.plantCode = plantCode;
        this.serviceOrderCode = serviceOrderCode;
        this.repairStateCode = repairStateCode;
        this.materialRevisionId = materialRevisionId;
        this.serialObjectId = serialObjectId;
        this.serialObjectSerialNumber = serialObjectSerialNumber;
        this.matrevMaterialId = matrevMaterialId;
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
        this.matrevMatSapNumber = matrevMatSapNumber;
        this.matrevRevisionNumber = matrevRevisionNumber;
        this.rMATypeCode = rMATypeCode;
        this.repairServiceCode = repairServiceCode;
        this.repairLocationCode = repairLocationCode;
        this.repairErrorCodeCode = repairErrorCodeCode;
        this.repairErrorCodeGroupName = repairErrorCodeGroupName;
        this.repairTaskCode = repairTaskCode;
        this.repairTaskShortText = repairTaskShortText;
        this.faultAnalysisCode = faultAnalysisCode;
        this.faultAnalysisShortText = faultAnalysisShortText;
        this.externalSupplierCode = externalSupplierCode;
        this.externalSupplierName = externalSupplierName;
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
     * @return the code of the plant
     */
    @Generated
    public String getPlantCode() {
        return this.plantCode;
    }

    /**
     * @param plantCode the code of the plant to set
     */
    @Generated
    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    /**
     * @return the code of the service order
     */
    @Generated
    public String getServiceOrderCode() {
        return this.serviceOrderCode;
    }

    /**
     * @param serviceOrderCode the code of the service order to set
     */
    @Generated
    public void setServiceOrderCode(String serviceOrderCode) {
        this.serviceOrderCode = serviceOrderCode;
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
    public long getMatrevMaterialId() {
        return this.matrevMaterialId;
    }

    /**
     * @param matrevMaterialId the id of the material to set
     */
    @Generated
    public void setMatrevMaterialId(long matrevMaterialId) {
        this.matrevMaterialId = matrevMaterialId;
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
     * @return the code of the RMA type
     */
    @Generated
    public String getrMATypeCode() {
        return this.rMATypeCode;
    }

    /**
     * @param rMATypeCode the code of the RMA type to set
     */
    @Generated
    public void setrMATypeCode(String rMATypeCode) {
        this.rMATypeCode = rMATypeCode;
    }

    /**
     * @return the code of the repair service
     */
    @Generated
    public String getRepairServiceCode() {
        return this.repairServiceCode;
    }

    /**
     * @param repairServiceCode the code of the repair service to set
     */
    @Generated
    public void setRepairServiceCode(String repairServiceCode) {
        this.repairServiceCode = repairServiceCode;
    }

    /**
     * @return the code of the repair location
     */
    @Generated
    public String getRepairLocationCode() {
        return this.repairLocationCode;
    }

    /**
     * @param repairLocationCode the code of the repair location to set
     */
    @Generated
    public void setRepairLocationCode(String repairLocationCode) {
        this.repairLocationCode = repairLocationCode;
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
     * @return the group name of the repair error code
     */
    @Generated
    public String getRepairErrorCodeGroupName() {
        return this.repairErrorCodeGroupName;
    }

    /**
     * @param repairErrorCodeGroupName the group name of the repair error code to set
     */
    @Generated
    public void setRepairErrorCodeGroupName(String repairErrorCodeGroupName) {
        this.repairErrorCodeGroupName = repairErrorCodeGroupName;
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

    /**
     * @return the short text of the repair task
     */
    @Generated
    public String getRepairTaskShortText() {
        return this.repairTaskShortText;
    }

    /**
     * @param repairTaskShortText the short text of the repair task to set
     */
    @Generated
    public void setRepairTaskShortText(String repairTaskShortText) {
        this.repairTaskShortText = repairTaskShortText;
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
     * @return the short text of the fault analysis
     */
    @Generated
    public String getFaultAnalysisShortText() {
        return this.faultAnalysisShortText;
    }

    /**
     * @param faultAnalysisShortText the short text of the fault analysis to set
     */
    @Generated
    public void setFaultAnalysisShortText(String faultAnalysisShortText) {
        this.faultAnalysisShortText = faultAnalysisShortText;
    }

    /**
     * @return the code of the supplier
     */
    @Generated
    public String getExternalSupplierCode() {
        return this.externalSupplierCode;
    }

    /**
     * @param externalSupplierCode the code of the supplier to set
     */
    @Generated
    public void setExternalSupplierCode(String externalSupplierCode) {
        this.externalSupplierCode = externalSupplierCode;
    }

    /**
     * @return the name of the supplier
     */
    @Generated
    public String getExternalSupplierName() {
        return this.externalSupplierName;
    }

    /**
     * @param externalSupplierName the name of the supplier to set
     */
    @Generated
    public void setExternalSupplierName(String externalSupplierName) {
        this.externalSupplierName = externalSupplierName;
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

        final var dto = (ServiceOrderServiceMessagesDTO) obj;

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