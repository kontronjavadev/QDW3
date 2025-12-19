package com.kontron.qdw.dto.mv;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterializedArrivalSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ARRIVALDATE = "arrivalDate";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String ATTR_MOVEMENTTYPE = "movementType";
    @Generated
    public static final String ATTR_ORDERNUMBER = "orderNumber";
    @Generated
    public static final String ATTR_COUNTRYCODE = "countryCode";
    @Generated
    public static final String ATTR_COUNTRYNAME = "countryName";
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
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String SELECT_MESERIALOBJECTID = "a.meSerialObjectId";
    @Generated
    private LocalDate arrivalDate;
    @Generated
    private String supplierName;
    @Generated
    private String movementType;
    @Generated
    private String orderNumber;
    @Generated
    private String countryCode;
    @Generated
    private String countryName;
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
    private String supplierCode;
    public static final String SELECT_REVISIONID = "a.revisionId";
    public static final String SELECT_MATERIALHIERARCHY = "a.materialHierarchy";
    public static final String SELECT_PLANT = "a.plant";
    public static final String SELECT_ASSEMBLYDATE = "a.assemblyDate";
    public static final String SELECT_SERIALNUMBER = "a.serialNumber";
    public static final String SELECT_PARENTMATERIALHIERARCHY = "a.parentMaterialHierarchy";
    public static final String SELECT_COUNTRYNAME = "a.countryName";
    public static final String SELECT_MATERIALTYPE = "a.materialType";
    public static final String SELECT_SUPPLIERNAME = "a.supplierName";
    public static final String SELECT_MATERIALNUMBER = "a.materialNumber";
    public static final String SELECT_ORDERNUMBER = "a.orderNumber";
    public static final String SELECT_SUPPLIERCODE = "a.supplierCode";
    public static final String SELECT_MOVEMENTTYPE = "a.movementType";
    public static final String SELECT_PARENTREVISIONID = "a.parentRevisionId";
    public static final String SELECT_ID = "a.id";
    public static final String SELECT_SERIALOBJECTID = "a.serialObjectId";
    public static final String SELECT_PARENTREVISIONNUMBER = "a.parentRevisionNumber";
    public static final String SELECT_PARENTMATERIALNUMBER = "a.parentMaterialNumber";
    public static final String SELECT_COUNTRYCODE = "a.countryCode";
    public static final String SELECT_PARENTSAPNUMBER = "a.parentSapNumber";
    public static final String SELECT_ARRIVALDATE = "a.arrivalDate";
    public static final String SELECT_PARENTSERIALNUMBER = "a.parentSerialNumber";
    public static final String SELECT_PARENTMATERIALTYPE = "a.parentMaterialType";
    public static final String SELECT_ASSEMBLYPO = "a.assemblyPO";
    public static final String SELECT_REVISIONNUMBER = "a.revisionNumber";
    public static final String SELECT_PARENTMATERIALSHORTTEXT = "a.parentMaterialShortText";
    public static final String SELECT_PARENTSERIALOBJECTID = "a.parentSerialObjectId";
    public static final String SELECT_MATERIALSHORTTEXT = "a.materialShortText";
    public static final String SELECT_SAPNUMBER = "a.sapNumber";

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrivalSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterializedArrivalSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param arrivalDate
     * @param supplierName
     * @param movementType
     * @param orderNumber
     * @param countryCode
     * @param countryName
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
     * @param supplierCode
     */
    @Generated
    public MaterializedArrivalSearchDTO(LocalDate arrivalDate, String supplierName, String movementType, String orderNumber, String countryCode,
            String countryName, long id, long meSerialObjectId, Long parentSerialObjectId, String serialNumber, String parentSerialNumber,
            String materialNumber, String parentMaterialNumber, String materialType, String parentMaterialType, String materialShortText,
            String parentMaterialShortText, String sapNumber, String parentSapNumber, String materialHierarchy, String parentMaterialHierarchy,
            long revisionId, Long parentRevisionId, String revisionNumber, String parentRevisionNumber, LocalDate assemblyDate, String assemblyPO,
            String plant, String supplierCode) {
        this.arrivalDate = arrivalDate;
        this.supplierName = supplierName;
        this.movementType = movementType;
        this.orderNumber = orderNumber;
        this.countryCode = countryCode;
        this.countryName = countryName;
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
        this.supplierCode = supplierCode;
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
     * @return the movement type
     */
    @Generated
    public String getMovementType() {
        return this.movementType;
    }

    /**
     * @param movementType the movement type to set
     */
    @Generated
    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    /**
     * @return the order number
     */
    @Generated
    public String getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * @param orderNumber the order number to set
     */
    @Generated
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

        final var dto = (MaterializedArrivalSearchDTO) obj;

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