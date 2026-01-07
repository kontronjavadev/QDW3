package com.kontron.qdw.dto.mv;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterializedArrivalShipmentSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ARRIVALDATE = "arrivalDate";
    @Generated
    public static final String ATTR_ARRIVALID = "arrivalId";
    @Generated
    public static final String ATTR_ARRIVALMOVEMENTTYPE = "arrivalMovementType";
    @Generated
    public static final String ATTR_COUNTRYCODE = "countryCode";
    @Generated
    public static final String ATTR_COUNTRYNAME = "countryName";
    @Generated
    public static final String ATTR_CUSTOMERCODE = "customerCode";
    @Generated
    public static final String ATTR_CUSTOMERNAME = "customerName";
    @Generated
    public static final String ATTR_CUSTOMERORDERNUMBER = "customerOrderNumber";
    @Generated
    public static final String ATTR_OWNERLOCATION = "ownerLocation";
    @Generated
    public static final String ATTR_PURCHASEORDERNUMBER = "purchaseOrderNumber";
    @Generated
    public static final String ATTR_SHIPMENTDATE = "shipmentDate";
    @Generated
    public static final String ATTR_SHIPMENTID = "shipmentId";
    @Generated
    public static final String ATTR_SHIPMENTMOVEMENTTYPE = "shipmentMovementType";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
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
    public static final String SELECT_ARRIVALDATE = "a.arrivalDate";
    @Generated
    public static final String SELECT_ARRIVALID = "a.arrivalId";
    @Generated
    public static final String SELECT_ARRIVALMOVEMENTTYPE = "a.arrivalMovementType";
    @Generated
    public static final String SELECT_COUNTRYCODE = "a.countryCode";
    @Generated
    public static final String SELECT_COUNTRYNAME = "a.countryName";
    @Generated
    public static final String SELECT_CUSTOMERCODE = "a.customerCode";
    @Generated
    public static final String SELECT_CUSTOMERNAME = "a.customerName";
    @Generated
    public static final String SELECT_CUSTOMERORDERNUMBER = "a.customerOrderNumber";
    @Generated
    public static final String SELECT_OWNERLOCATION = "a.ownerLocation";
    @Generated
    public static final String SELECT_PURCHASEORDERNUMBER = "a.purchaseOrderNumber";
    @Generated
    public static final String SELECT_SHIPMENTDATE = "a.shipmentDate";
    @Generated
    public static final String SELECT_SHIPMENTID = "a.shipmentId";
    @Generated
    public static final String SELECT_SHIPMENTMOVEMENTTYPE = "a.shipmentMovementType";
    @Generated
    public static final String SELECT_SUPPLIERCODE = "a.supplierCode";
    @Generated
    public static final String SELECT_SUPPLIERNAME = "a.supplierName";
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
    private LocalDate arrivalDate;
    @Generated
    private Long arrivalId;
    @Generated
    private String arrivalMovementType;
    @Generated
    private String countryCode;
    @Generated
    private String countryName;
    @Generated
    private String customerCode;
    @Generated
    private String customerName;
    @Generated
    private String customerOrderNumber;
    @Generated
    private String ownerLocation;
    @Generated
    private String purchaseOrderNumber;
    @Generated
    private LocalDate shipmentDate;
    @Generated
    private long shipmentId;
    @Generated
    private String shipmentMovementType;
    @Generated
    private String supplierCode;
    @Generated
    private String supplierName;
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

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrivalShipmentSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterializedArrivalShipmentSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param arrivalDate
     * @param arrivalId
     * @param arrivalMovementType
     * @param countryCode
     * @param countryName
     * @param customerCode
     * @param customerName
     * @param customerOrderNumber
     * @param ownerLocation
     * @param purchaseOrderNumber
     * @param shipmentDate
     * @param shipmentId
     * @param shipmentMovementType
     * @param supplierCode
     * @param supplierName
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
     */
    @Generated
    public MaterializedArrivalShipmentSearchDTO(LocalDate arrivalDate, Long arrivalId, String arrivalMovementType, String countryCode,
            String countryName, String customerCode, String customerName, String customerOrderNumber, String ownerLocation,
            String purchaseOrderNumber, LocalDate shipmentDate, long shipmentId, String shipmentMovementType, String supplierCode,
            String supplierName, long id, long meSerialObjectId, Long parentSerialObjectId, String serialNumber, String parentSerialNumber,
            String materialNumber, String parentMaterialNumber, String materialType, String parentMaterialType, String materialShortText,
            String parentMaterialShortText, String sapNumber, String parentSapNumber, String materialHierarchy, String parentMaterialHierarchy,
            long revisionId, Long parentRevisionId, String revisionNumber, String parentRevisionNumber, LocalDate assemblyDate, String assemblyPO,
            String plant) {
        this.arrivalDate = arrivalDate;
        this.arrivalId = arrivalId;
        this.arrivalMovementType = arrivalMovementType;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerOrderNumber = customerOrderNumber;
        this.ownerLocation = ownerLocation;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.shipmentDate = shipmentDate;
        this.shipmentId = shipmentId;
        this.shipmentMovementType = shipmentMovementType;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
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

        final var dto = (MaterializedArrivalShipmentSearchDTO) obj;

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