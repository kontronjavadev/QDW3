package com.kontron.qdw.dto.service;

import java.io.Serializable;
import com.kontron.qdw.dto.material.*;
import com.kontron.qdw.dto.serial.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.base.*;

public class ServiceMessageDTO implements Serializable {
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
    public static final String ATTR_REBUILDFLAG = "rebuildFlag";
    @Generated
    public static final String ATTR_REPAIRDESCRIPTION = "repairDescription";
    @Generated
    public static final String ATTR_SERVICEMESSAGEID = "serviceMessageId";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_EXTERNALSUPPLIER = "externalSupplier";
    @Generated
    public static final String ATTR_MATERIALREVISION = "materialRevision";
    @Generated
    public static final String ATTR_PLANT = "plant";
    @Generated
    public static final String ATTR_SERIALOBJECT = "serialObject";
    @Generated
    public static final String ATTR_SERVICEORDER = "serviceOrder";
    @Generated
    public static final String ATTR_FAULTANALYSIS = "faultAnalysis";
    @Generated
    public static final String ATTR_RMATYPE = "rMAType";
    @Generated
    public static final String ATTR_REPAIRERRORCODE = "repairErrorCode";
    @Generated
    public static final String ATTR_REPAIRLOCATION = "repairLocation";
    @Generated
    public static final String ATTR_REPAIRSERVICE = "repairService";
    @Generated
    public static final String ATTR_REPAIRSTATE = "repairState";
    @Generated
    public static final String ATTR_REPAIRTASK = "repairTask";
    @Generated
    public static final String ATTR_MATERIALREVISIONMATERIAL = "materialRevisionMaterial";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
    @Generated
    public static final String ATTR_INTERNALREPORT = "internalReport";
    @Generated
    public static final String ATTR_EXTERNALREPORT = "externalReport";
    @Generated
    public static final String ATTR_CUSTOMERREPORT = "customerReport";
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
    private int rebuildFlag;
    @Generated
    private String repairDescription;
    @Generated
    private String serviceMessageId;
    @Generated
    private long id;
    @Generated
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private SupplierListDTO externalSupplier;
    @Generated
    private MaterialRevisionListDTO materialRevision;
    @Generated
    private PlantListDTO plant;
    @Generated
    private SerialObjectListDTO serialObject;
    @Generated
    private ServiceOrderListDTO serviceOrder;
    @Generated
    private FaultAnalysisListDTO faultAnalysis;
    @Generated
    private RMATypeListDTO rMAType;
    @Generated
    private RepairErrorCodeListDTO repairErrorCode;
    @Generated
    private RepairLocationListDTO repairLocation;
    @Generated
    private RepairServiceListDTO repairService;
    @Generated
    private RepairStateListDTO repairState;
    @Generated
    private RepairTaskListDTO repairTask;
    @Generated
    private MaterialListDTO materialRevisionMaterial;
    @Generated
    private String materialSapNumber;
    @Generated
    private String internalReport;
    @Generated
    private String externalReport;
    @Generated
    private String customerReport;

    /**
     * Default constructor
     */
    @Generated
    public ServiceMessageDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public ServiceMessageDTO(long id) {
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
     * @param rebuildFlag
     * @param repairDescription
     * @param serviceMessageId
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     * @param materialSapNumber
     * @param internalReport
     * @param externalReport
     * @param customerReport
     */
    @Generated
    public ServiceMessageDTO(String analysisText, LocalDate basicFinishDate, LocalDate basicStartDate, String causeText, boolean customerFailure,
            String defectComponent, String deliveryNoteNumber, String designator, boolean epidemicFailure, String errorId,
            LocalDate internalArrivalDate, LocalDate internalShipmentDate, String origin, int rebuildFlag, String repairDescription,
            String serviceMessageId, long id, int version, LocalDateTime creationDate, LocalDateTime lastUpdate, String materialSapNumber,
            String internalReport, String externalReport, String customerReport) {
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
        this.rebuildFlag = rebuildFlag;
        this.repairDescription = repairDescription;
        this.serviceMessageId = serviceMessageId;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.materialSapNumber = materialSapNumber;
        this.internalReport = internalReport;
        this.externalReport = externalReport;
        this.customerReport = customerReport;
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
     * @return the supplier
     */
    @Generated
    public SupplierListDTO getExternalSupplier() {
        return this.externalSupplier;
    }

    /**
     * @param externalSupplier the supplier to set
     */
    @Generated
    public void setExternalSupplier(SupplierListDTO externalSupplier) {
        this.externalSupplier = externalSupplier;
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
     * @return the plant
     */
    @Generated
    public PlantListDTO getPlant() {
        return this.plant;
    }

    /**
     * @param plant the plant to set
     */
    @Generated
    public void setPlant(PlantListDTO plant) {
        this.plant = plant;
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
     * @return the service order
     */
    @Generated
    public ServiceOrderListDTO getServiceOrder() {
        return this.serviceOrder;
    }

    /**
     * @param serviceOrder the service order to set
     */
    @Generated
    public void setServiceOrder(ServiceOrderListDTO serviceOrder) {
        this.serviceOrder = serviceOrder;
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
     * @return the RMA type
     */
    @Generated
    public RMATypeListDTO getrMAType() {
        return this.rMAType;
    }

    /**
     * @param rMAType the RMA type to set
     */
    @Generated
    public void setrMAType(RMATypeListDTO rMAType) {
        this.rMAType = rMAType;
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
     * @return the repair location
     */
    @Generated
    public RepairLocationListDTO getRepairLocation() {
        return this.repairLocation;
    }

    /**
     * @param repairLocation the repair location to set
     */
    @Generated
    public void setRepairLocation(RepairLocationListDTO repairLocation) {
        this.repairLocation = repairLocation;
    }

    /**
     * @return the repair service
     */
    @Generated
    public RepairServiceListDTO getRepairService() {
        return this.repairService;
    }

    /**
     * @param repairService the repair service to set
     */
    @Generated
    public void setRepairService(RepairServiceListDTO repairService) {
        this.repairService = repairService;
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
     * @return the material
     */
    @Generated
    public MaterialListDTO getMaterialRevisionMaterial() {
        return this.materialRevisionMaterial;
    }

    /**
     * @param materialRevisionMaterial the material to set
     */
    @Generated
    public void setMaterialRevisionMaterial(MaterialListDTO materialRevisionMaterial) {
        this.materialRevisionMaterial = materialRevisionMaterial;
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

        final var dto = (ServiceMessageDTO) obj;

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