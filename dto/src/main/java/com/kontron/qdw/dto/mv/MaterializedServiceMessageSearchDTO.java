package com.kontron.qdw.dto.mv;

import java.io.Serializable;
import com.kontron.qdw.domain.mv.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterializedServiceMessageSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_SERVICEORDER = "serviceOrder";
    @Generated
    public static final String ATTR_SERVICEORDERTYPE = "serviceOrderType";
    @Generated
    public static final String ATTR_RMATYPE = "rmaType";
    @Generated
    public static final String ATTR_SERVICENAME = "serviceName";
    @Generated
    public static final String ATTR_TASKNAME = "taskName";
    @Generated
    public static final String ATTR_STATENAME = "stateName";
    @Generated
    public static final String ATTR_INTERNALARRIVALDATE = "internalArrivalDate";
    @Generated
    public static final String ATTR_INTERNALSHIPMENTDATE = "internalShipmentDate";
    @Generated
    public static final String ATTR_BASICSTARTDATE = "basicStartDate";
    @Generated
    public static final String ATTR_BASICENDDATE = "basicEndDate";
    @Generated
    public static final String ATTR_DESIGNATOR = "designator";
    @Generated
    public static final String ATTR_DEFECTCOMPONENT = "defectComponent";
    @Generated
    public static final String ATTR_ANALYSISTEXT = "analysisText";
    @Generated
    public static final String ATTR_INTERNALREPORT = "internalReport";
    @Generated
    public static final String ATTR_EXTERNALREPORT = "externalReport";
    @Generated
    public static final String ATTR_CUSTOMERREPORT = "customerReport";
    @Generated
    public static final String ATTR_EPIDEMICFAILURE = "epidemicFailure";
    @Generated
    public static final String ATTR_ERRORID = "errorId";
    @Generated
    public static final String ATTR_ORIGIN = "origin";
    @Generated
    public static final String ATTR_CUSTOMERFAILURE = "customerFailure";
    @Generated
    public static final String ATTR_CUSTOMERCODE = "customerCode";
    @Generated
    public static final String ATTR_CUSTOMERNAME = "customerName";
    @Generated
    public static final String ATTR_CUSTOMERGROUP = "customerGroup";
    @Generated
    public static final String ATTR_COUNTRYCODE = "countryCode";
    @Generated
    public static final String ATTR_COUNTRYNAME = "countryName";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String ATTR_SUPPLIERARRIVALDATE = "supplierArrivalDate";
    @Generated
    public static final String ATTR_CUSTOMERSHIPMENTDATE = "customerShipmentDate";
    @Generated
    public static final String ATTR_FAULTANALYSISCODE = "faultAnalysisCode";
    @Generated
    public static final String ATTR_ERRORCODENAME = "errorCodeName";
    @Generated
    public static final String ATTR_EXTERNALSUPPLIERCODE = "externalSupplierCode";
    @Generated
    public static final String ATTR_EXTERNALSUPPLIERNAME = "externalSupplierName";
    @Generated
    public static final String ATTR_DELIVERYNOTENUMBER = "deliveryNoteNumber";
    @Generated
    public static final String ATTR_ERRORCODEGROUP = "errorCodeGroup";
    @Generated
    public static final String ATTR_SYMPTOMSHORTTEXT = "symptomShortText";
    @Generated
    public static final String ATTR_REPAIRTASKSHORTTEXT = "repairTaskShortText";
    @Generated
    public static final String ATTR_REPAIRDESCRIPTION = "repairDescription";
    @Generated
    public static final String ATTR_OWNERLOCATION = "ownerLocation";
    @Generated
    public static final String ATTR_ERRORSHORTTEXT = "errorShortText";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_MESERIALOBJECTID = "meSerialObjectId";
    @Generated
    public static final String ATTR_PARENTSERIALOBJECTID = "parentSerialObjectId";
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_PARENTSERIALNUMBER = "parentSerialNumber";
    @Generated
    public static final String ATTR_MATERIALNUMBER = "materialNumber";
    @Generated
    public static final String ATTR_PARENTMATERIALNUMBER = "parentMaterialNumber";
    @Generated
    public static final String ATTR_MATERIALTYPE = "materialType";
    @Generated
    public static final String ATTR_PARENTMATERIALTYPE = "parentMaterialType";
    @Generated
    public static final String ATTR_MATERIALSHORTTEXT = "materialShortText";
    @Generated
    public static final String ATTR_PARENTMATERIALSHORTTEXT = "parentMaterialShortText";
    @Generated
    public static final String ATTR_SAPNUMBER = "sapNumber";
    @Generated
    public static final String ATTR_PARENTSAPNUMBER = "parentSapNumber";
    @Generated
    public static final String ATTR_MATERIALHIERARCHY = "materialHierarchy";
    @Generated
    public static final String ATTR_PARENTMATERIALHIERARCHY = "parentMaterialHierarchy";
    @Generated
    public static final String ATTR_REVISIONID = "revisionId";
    @Generated
    public static final String ATTR_PARENTREVISIONID = "parentRevisionId";
    @Generated
    public static final String ATTR_REVISIONNUMBER = "revisionNumber";
    @Generated
    public static final String ATTR_PARENTREVISIONNUMBER = "parentRevisionNumber";
    @Generated
    public static final String ATTR_ASSEMBLYDATE = "assemblyDate";
    @Generated
    public static final String ATTR_ASSEMBLYPO = "assemblyPO";
    @Generated
    public static final String ATTR_PLANT = "plant";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_SERIALOBJECTID = "serialObjectId";
    @Generated
    public static final String ATTR_MATERIALID = "materialId";
    @Generated
    public static final String SELECT_SERVICEORDER = "a.serviceOrder";
    @Generated
    public static final String SELECT_SERVICEORDERTYPE = "a.serviceOrderType";
    @Generated
    public static final String SELECT_RMATYPE = "a.rmaType";
    @Generated
    public static final String SELECT_SERVICENAME = "a.serviceName";
    @Generated
    public static final String SELECT_TASKNAME = "a.taskName";
    @Generated
    public static final String SELECT_STATENAME = "a.stateName";
    @Generated
    public static final String SELECT_INTERNALARRIVALDATE = "a.internalArrivalDate";
    @Generated
    public static final String SELECT_INTERNALSHIPMENTDATE = "a.internalShipmentDate";
    @Generated
    public static final String SELECT_BASICSTARTDATE = "a.basicStartDate";
    @Generated
    public static final String SELECT_BASICENDDATE = "a.basicEndDate";
    @Generated
    public static final String SELECT_DESIGNATOR = "a.designator";
    @Generated
    public static final String SELECT_DEFECTCOMPONENT = "a.defectComponent";
    @Generated
    public static final String SELECT_ANALYSISTEXT = "a.analysisText";
    @Generated
    public static final String SELECT_INTERNALREPORT = "a.internalReport";
    @Generated
    public static final String SELECT_EXTERNALREPORT = "a.externalReport";
    @Generated
    public static final String SELECT_CUSTOMERREPORT = "a.customerReport";
    @Generated
    public static final String SELECT_EPIDEMICFAILURE = "a.epidemicFailure";
    @Generated
    public static final String SELECT_ERRORID = "a.errorId";
    @Generated
    public static final String SELECT_ORIGIN = "a.origin";
    @Generated
    public static final String SELECT_CUSTOMERFAILURE = "a.customerFailure";
    @Generated
    public static final String SELECT_CUSTOMERCODE = "a.customerCode";
    @Generated
    public static final String SELECT_CUSTOMERNAME = "a.customerName";
    @Generated
    public static final String SELECT_CUSTOMERGROUP = "a.customerGroup";
    @Generated
    public static final String SELECT_COUNTRYCODE = "a.countryCode";
    @Generated
    public static final String SELECT_COUNTRYNAME = "a.countryName";
    @Generated
    public static final String SELECT_SUPPLIERCODE = "a.supplierCode";
    @Generated
    public static final String SELECT_SUPPLIERNAME = "a.supplierName";
    @Generated
    public static final String SELECT_SUPPLIERARRIVALDATE = "a.supplierArrivalDate";
    @Generated
    public static final String SELECT_CUSTOMERSHIPMENTDATE = "a.customerShipmentDate";
    @Generated
    public static final String SELECT_FAULTANALYSISCODE = "a.faultAnalysisCode";
    @Generated
    public static final String SELECT_ERRORCODENAME = "a.errorCodeName";
    @Generated
    public static final String SELECT_EXTERNALSUPPLIERCODE = "a.externalSupplierCode";
    @Generated
    public static final String SELECT_EXTERNALSUPPLIERNAME = "a.externalSupplierName";
    @Generated
    public static final String SELECT_DELIVERYNOTENUMBER = "a.deliveryNoteNumber";
    @Generated
    public static final String SELECT_ERRORCODEGROUP = "a.errorCodeGroup";
    @Generated
    public static final String SELECT_SYMPTOMSHORTTEXT = "a.symptomShortText";
    @Generated
    public static final String SELECT_REPAIRTASKSHORTTEXT = "a.repairTaskShortText";
    @Generated
    public static final String SELECT_REPAIRDESCRIPTION = "a.repairDescription";
    @Generated
    public static final String SELECT_OWNERLOCATION = "a.ownerLocation";
    @Generated
    public static final String SELECT_ERRORSHORTTEXT = "a.errorShortText";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_MESERIALOBJECTID = "a.meSerialObjectId";
    @Generated
    public static final String SELECT_PARENTSERIALOBJECTID = "a.parentSerialObjectId";
    @Generated
    public static final String SELECT_SERIALNUMBER = "a.serialNumber";
    @Generated
    public static final String SELECT_PARENTSERIALNUMBER = "a.parentSerialNumber";
    @Generated
    public static final String SELECT_MATERIALNUMBER = "a.materialNumber";
    @Generated
    public static final String SELECT_PARENTMATERIALNUMBER = "a.parentMaterialNumber";
    @Generated
    public static final String SELECT_MATERIALTYPE = "a.materialType";
    @Generated
    public static final String SELECT_PARENTMATERIALTYPE = "a.parentMaterialType";
    @Generated
    public static final String SELECT_MATERIALSHORTTEXT = "a.materialShortText";
    @Generated
    public static final String SELECT_PARENTMATERIALSHORTTEXT = "a.parentMaterialShortText";
    @Generated
    public static final String SELECT_SAPNUMBER = "a.sapNumber";
    @Generated
    public static final String SELECT_PARENTSAPNUMBER = "a.parentSapNumber";
    @Generated
    public static final String SELECT_MATERIALHIERARCHY = "a.materialHierarchy";
    @Generated
    public static final String SELECT_PARENTMATERIALHIERARCHY = "a.parentMaterialHierarchy";
    @Generated
    public static final String SELECT_REVISIONID = "a.revisionId";
    @Generated
    public static final String SELECT_PARENTREVISIONID = "a.parentRevisionId";
    @Generated
    public static final String SELECT_REVISIONNUMBER = "a.revisionNumber";
    @Generated
    public static final String SELECT_PARENTREVISIONNUMBER = "a.parentRevisionNumber";
    @Generated
    public static final String SELECT_ASSEMBLYDATE = "a.assemblyDate";
    @Generated
    public static final String SELECT_ASSEMBLYPO = "a.assemblyPO";
    @Generated
    public static final String SELECT_PLANT = "a.plant";
    @Generated
    public static final String SELECT_MATERIALMATERIALNUMBER = "c.materialNumber";
    @Generated
    public static final String SELECT_SERIALOBJECTID = "b.id";
    @Generated
    public static final String SELECT_MATERIALID = "c.id";
    @Generated
    private String serviceOrder;
    @Generated
    private ServiceOrderType serviceOrderType;
    @Generated
    private String rmaType;
    @Generated
    private String serviceName;
    @Generated
    private String taskName;
    @Generated
    private String stateName;
    @Generated
    private LocalDate internalArrivalDate;
    @Generated
    private LocalDate internalShipmentDate;
    @Generated
    private LocalDate basicStartDate;
    @Generated
    private LocalDate basicEndDate;
    @Generated
    private String designator;
    @Generated
    private String defectComponent;
    @Generated
    private String analysisText;
    @Generated
    private String internalReport;
    @Generated
    private String externalReport;
    @Generated
    private String customerReport;
    @Generated
    private boolean epidemicFailure;
    @Generated
    private String errorId;
    @Generated
    private String origin;
    @Generated
    private boolean customerFailure;
    @Generated
    private String customerCode;
    @Generated
    private String customerName;
    @Generated
    private String customerGroup;
    @Generated
    private String countryCode;
    @Generated
    private String countryName;
    @Generated
    private String supplierCode;
    @Generated
    private String supplierName;
    @Generated
    private LocalDate supplierArrivalDate;
    @Generated
    private LocalDate customerShipmentDate;
    @Generated
    private String faultAnalysisCode;
    @Generated
    private String errorCodeName;
    @Generated
    private String externalSupplierCode;
    @Generated
    private String externalSupplierName;
    @Generated
    private String deliveryNoteNumber;
    @Generated
    private String errorCodeGroup;
    @Generated
    private String symptomShortText;
    @Generated
    private String repairTaskShortText;
    @Generated
    private String repairDescription;
    @Generated
    private String ownerLocation;
    @Generated
    private String errorShortText;
    @Generated
    private long id;
    @Generated
    private long meSerialObjectId;
    @Generated
    private Long parentSerialObjectId;
    @Generated
    private String serialNumber;
    @Generated
    private String parentSerialNumber;
    @Generated
    private String materialNumber;
    @Generated
    private String parentMaterialNumber;
    @Generated
    private String materialType;
    @Generated
    private String parentMaterialType;
    @Generated
    private String materialShortText;
    @Generated
    private String parentMaterialShortText;
    @Generated
    private String sapNumber;
    @Generated
    private String parentSapNumber;
    @Generated
    private String materialHierarchy;
    @Generated
    private String parentMaterialHierarchy;
    @Generated
    private long revisionId;
    @Generated
    private Long parentRevisionId;
    @Generated
    private String revisionNumber;
    @Generated
    private String parentRevisionNumber;
    @Generated
    private LocalDate assemblyDate;
    @Generated
    private String assemblyPO;
    @Generated
    private String plant;
    @Generated
    private String materialMaterialNumber;
    @Generated
    private long serialObjectId;
    @Generated
    private long materialId;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedServiceMessageSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterializedServiceMessageSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param serviceOrder
     * @param serviceOrderType
     * @param rmaType
     * @param serviceName
     * @param taskName
     * @param stateName
     * @param internalArrivalDate
     * @param internalShipmentDate
     * @param basicStartDate
     * @param basicEndDate
     * @param designator
     * @param defectComponent
     * @param analysisText
     * @param internalReport
     * @param externalReport
     * @param customerReport
     * @param epidemicFailure
     * @param errorId
     * @param origin
     * @param customerFailure
     * @param customerCode
     * @param customerName
     * @param customerGroup
     * @param countryCode
     * @param countryName
     * @param supplierCode
     * @param supplierName
     * @param supplierArrivalDate
     * @param customerShipmentDate
     * @param faultAnalysisCode
     * @param errorCodeName
     * @param externalSupplierCode
     * @param externalSupplierName
     * @param deliveryNoteNumber
     * @param errorCodeGroup
     * @param symptomShortText
     * @param repairTaskShortText
     * @param repairDescription
     * @param ownerLocation
     * @param errorShortText
     * @param id
     * @param meSerialObjectId
     * @param parentSerialObjectId
     * @param serialNumber
     * @param parentSerialNumber
     * @param materialNumber
     * @param parentMaterialNumber
     * @param materialType
     * @param parentMaterialType
     * @param materialShortText
     * @param parentMaterialShortText
     * @param sapNumber
     * @param parentSapNumber
     * @param materialHierarchy
     * @param parentMaterialHierarchy
     * @param revisionId
     * @param parentRevisionId
     * @param revisionNumber
     * @param parentRevisionNumber
     * @param assemblyDate
     * @param assemblyPO
     * @param plant
     * @param materialMaterialNumber
     * @param serialObjectId
     * @param materialId
     */
    @Generated
    public MaterializedServiceMessageSearchDTO(String serviceOrder, ServiceOrderType serviceOrderType, String rmaType, String serviceName,
            String taskName, String stateName, LocalDate internalArrivalDate, LocalDate internalShipmentDate, LocalDate basicStartDate,
            LocalDate basicEndDate, String designator, String defectComponent, String analysisText, String internalReport, String externalReport,
            String customerReport, boolean epidemicFailure, String errorId, String origin, boolean customerFailure, String customerCode,
            String customerName, String customerGroup, String countryCode, String countryName, String supplierCode, String supplierName,
            LocalDate supplierArrivalDate, LocalDate customerShipmentDate, String faultAnalysisCode, String errorCodeName,
            String externalSupplierCode, String externalSupplierName, String deliveryNoteNumber, String errorCodeGroup, String symptomShortText,
            String repairTaskShortText, String repairDescription, String ownerLocation, String errorShortText, long id, long meSerialObjectId,
            Long parentSerialObjectId, String serialNumber, String parentSerialNumber, String materialNumber, String parentMaterialNumber,
            String materialType, String parentMaterialType, String materialShortText, String parentMaterialShortText, String sapNumber,
            String parentSapNumber, String materialHierarchy, String parentMaterialHierarchy, long revisionId, Long parentRevisionId,
            String revisionNumber, String parentRevisionNumber, LocalDate assemblyDate, String assemblyPO, String plant,
            String materialMaterialNumber, long serialObjectId, long materialId) {
        this.serviceOrder = serviceOrder;
        this.serviceOrderType = serviceOrderType;
        this.rmaType = rmaType;
        this.serviceName = serviceName;
        this.taskName = taskName;
        this.stateName = stateName;
        this.internalArrivalDate = internalArrivalDate;
        this.internalShipmentDate = internalShipmentDate;
        this.basicStartDate = basicStartDate;
        this.basicEndDate = basicEndDate;
        this.designator = designator;
        this.defectComponent = defectComponent;
        this.analysisText = analysisText;
        this.internalReport = internalReport;
        this.externalReport = externalReport;
        this.customerReport = customerReport;
        this.epidemicFailure = epidemicFailure;
        this.errorId = errorId;
        this.origin = origin;
        this.customerFailure = customerFailure;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerGroup = customerGroup;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.supplierArrivalDate = supplierArrivalDate;
        this.customerShipmentDate = customerShipmentDate;
        this.faultAnalysisCode = faultAnalysisCode;
        this.errorCodeName = errorCodeName;
        this.externalSupplierCode = externalSupplierCode;
        this.externalSupplierName = externalSupplierName;
        this.deliveryNoteNumber = deliveryNoteNumber;
        this.errorCodeGroup = errorCodeGroup;
        this.symptomShortText = symptomShortText;
        this.repairTaskShortText = repairTaskShortText;
        this.repairDescription = repairDescription;
        this.ownerLocation = ownerLocation;
        this.errorShortText = errorShortText;
        this.id = id;
        this.meSerialObjectId = meSerialObjectId;
        this.parentSerialObjectId = parentSerialObjectId;
        this.serialNumber = serialNumber;
        this.parentSerialNumber = parentSerialNumber;
        this.materialNumber = materialNumber;
        this.parentMaterialNumber = parentMaterialNumber;
        this.materialType = materialType;
        this.parentMaterialType = parentMaterialType;
        this.materialShortText = materialShortText;
        this.parentMaterialShortText = parentMaterialShortText;
        this.sapNumber = sapNumber;
        this.parentSapNumber = parentSapNumber;
        this.materialHierarchy = materialHierarchy;
        this.parentMaterialHierarchy = parentMaterialHierarchy;
        this.revisionId = revisionId;
        this.parentRevisionId = parentRevisionId;
        this.revisionNumber = revisionNumber;
        this.parentRevisionNumber = parentRevisionNumber;
        this.assemblyDate = assemblyDate;
        this.assemblyPO = assemblyPO;
        this.plant = plant;
        this.materialMaterialNumber = materialMaterialNumber;
        this.serialObjectId = serialObjectId;
        this.materialId = materialId;
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
     * @return the serial object id
     */
    @Generated
    public long getMeSerialObjectId() {
        return this.meSerialObjectId;
    }

    /**
     * @param meSerialObjectId the serial object id to set
     */
    @Generated
    public void setMeSerialObjectId(long meSerialObjectId) {
        this.meSerialObjectId = meSerialObjectId;
    }

    /**
     * @return the parent serial object id
     */
    @Generated
    public Long getParentSerialObjectId() {
        return this.parentSerialObjectId;
    }

    /**
     * @param parentSerialObjectId the parent serial object id to set
     */
    @Generated
    public void setParentSerialObjectId(Long parentSerialObjectId) {
        this.parentSerialObjectId = parentSerialObjectId;
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
     * @return the parent serial number
     */
    @Generated
    public String getParentSerialNumber() {
        return this.parentSerialNumber;
    }

    /**
     * @param parentSerialNumber the parent serial number to set
     */
    @Generated
    public void setParentSerialNumber(String parentSerialNumber) {
        this.parentSerialNumber = parentSerialNumber;
    }

    /**
     * @return the material number
     */
    @Generated
    public String getMaterialNumber() {
        return this.materialNumber;
    }

    /**
     * @param materialNumber the material number to set
     */
    @Generated
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    /**
     * @return the parent material number
     */
    @Generated
    public String getParentMaterialNumber() {
        return this.parentMaterialNumber;
    }

    /**
     * @param parentMaterialNumber the parent material number to set
     */
    @Generated
    public void setParentMaterialNumber(String parentMaterialNumber) {
        this.parentMaterialNumber = parentMaterialNumber;
    }

    /**
     * @return the material type
     */
    @Generated
    public String getMaterialType() {
        return this.materialType;
    }

    /**
     * @param materialType the material type to set
     */
    @Generated
    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    /**
     * @return the parent material type
     */
    @Generated
    public String getParentMaterialType() {
        return this.parentMaterialType;
    }

    /**
     * @param parentMaterialType the parent material type to set
     */
    @Generated
    public void setParentMaterialType(String parentMaterialType) {
        this.parentMaterialType = parentMaterialType;
    }

    /**
     * @return the material short text
     */
    @Generated
    public String getMaterialShortText() {
        return this.materialShortText;
    }

    /**
     * @param materialShortText the material short text to set
     */
    @Generated
    public void setMaterialShortText(String materialShortText) {
        this.materialShortText = materialShortText;
    }

    /**
     * @return the parent material short text
     */
    @Generated
    public String getParentMaterialShortText() {
        return this.parentMaterialShortText;
    }

    /**
     * @param parentMaterialShortText the parent material short text to set
     */
    @Generated
    public void setParentMaterialShortText(String parentMaterialShortText) {
        this.parentMaterialShortText = parentMaterialShortText;
    }

    /**
     * @return the sap number
     */
    @Generated
    public String getSapNumber() {
        return this.sapNumber;
    }

    /**
     * @param sapNumber the sap number to set
     */
    @Generated
    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    /**
     * @return the parent sap number
     */
    @Generated
    public String getParentSapNumber() {
        return this.parentSapNumber;
    }

    /**
     * @param parentSapNumber the parent sap number to set
     */
    @Generated
    public void setParentSapNumber(String parentSapNumber) {
        this.parentSapNumber = parentSapNumber;
    }

    /**
     * @return the material hierarchy
     */
    @Generated
    public String getMaterialHierarchy() {
        return this.materialHierarchy;
    }

    /**
     * @param materialHierarchy the material hierarchy to set
     */
    @Generated
    public void setMaterialHierarchy(String materialHierarchy) {
        this.materialHierarchy = materialHierarchy;
    }

    /**
     * @return the parent material hierarchy
     */
    @Generated
    public String getParentMaterialHierarchy() {
        return this.parentMaterialHierarchy;
    }

    /**
     * @param parentMaterialHierarchy the parent material hierarchy to set
     */
    @Generated
    public void setParentMaterialHierarchy(String parentMaterialHierarchy) {
        this.parentMaterialHierarchy = parentMaterialHierarchy;
    }

    /**
     * @return the revision id
     */
    @Generated
    public long getRevisionId() {
        return this.revisionId;
    }

    /**
     * @param revisionId the revision id to set
     */
    @Generated
    public void setRevisionId(long revisionId) {
        this.revisionId = revisionId;
    }

    /**
     * @return the parent revision id
     */
    @Generated
    public Long getParentRevisionId() {
        return this.parentRevisionId;
    }

    /**
     * @param parentRevisionId the parent revision id to set
     */
    @Generated
    public void setParentRevisionId(Long parentRevisionId) {
        this.parentRevisionId = parentRevisionId;
    }

    /**
     * @return the revision no.
     */
    @Generated
    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    /**
     * @param revisionNumber the revision no. to set
     */
    @Generated
    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    /**
     * @return the parent revision number
     */
    @Generated
    public String getParentRevisionNumber() {
        return this.parentRevisionNumber;
    }

    /**
     * @param parentRevisionNumber the parent revision number to set
     */
    @Generated
    public void setParentRevisionNumber(String parentRevisionNumber) {
        this.parentRevisionNumber = parentRevisionNumber;
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
     * @return the assembly PO
     */
    @Generated
    public String getAssemblyPO() {
        return this.assemblyPO;
    }

    /**
     * @param assemblyPO the assembly PO to set
     */
    @Generated
    public void setAssemblyPO(String assemblyPO) {
        this.assemblyPO = assemblyPO;
    }

    /**
     * @return the plant
     */
    @Generated
    public String getPlant() {
        return this.plant;
    }

    /**
     * @param plant the plant to set
     */
    @Generated
    public void setPlant(String plant) {
        this.plant = plant;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getMaterialMaterialNumber() {
        return this.materialMaterialNumber;
    }

    /**
     * @param materialMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMaterialMaterialNumber(String materialMaterialNumber) {
        this.materialMaterialNumber = materialMaterialNumber;
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
     * @return the id of the material
     */
    @Generated
    public long getMaterialId() {
        return this.materialId;
    }

    /**
     * @param materialId the id of the material to set
     */
    @Generated
    public void setMaterialId(long materialId) {
        this.materialId = materialId;
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

        final var dto = (MaterializedServiceMessageSearchDTO) obj;

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