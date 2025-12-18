package com.kontron.qdw.dto.mv;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterializedArrivalSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;

    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_PLANT = "plant";
    @Generated
    public static final String ATTR_SERIALOBJECTID = "serialObjectId";
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_PARENTSERIALOBJECTID = "parentSerialObjectId";
    @Generated
    public static final String ATTR_PARENTSERIALNUMBER = "parentSerialNumber";
    @Generated
    public static final String ATTR_ORDERNUMBER = "orderNumber";
    @Generated
    public static final String ATTR_ARRIVALDATE = "arrivalDate";
    @Generated
    public static final String ATTR_ASSEMBLYDATE = "assemblyDate";
    @Generated
    public static final String ATTR_ASSEMBLYPO = "assemblyPO";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String ATTR_COUNTRYCODE = "countryCode";
    @Generated
    public static final String ATTR_COUNTRYNAME = "countryName";
    @Generated
    public static final String ATTR_REVISIONID = "revisionId";
    @Generated
    public static final String ATTR_REVISIONNUMBER = "revisionNumber";
    @Generated
    public static final String ATTR_PARENTREVISIONID = "parentRevisionId";
    @Generated
    public static final String ATTR_PARENTREVISIONNUMBER = "parentRevisionNumber";
    @Generated
    public static final String ATTR_MATERIALNUMBER = "materialNumber";
    @Generated
    public static final String ATTR_SAPNUMBER = "sapNumber";
    @Generated
    public static final String ATTR_MATERIALSHORTTEXT = "materialShortText";
    @Generated
    public static final String ATTR_MATERIALHIERARCHY = "materialHierarchy";
    @Generated
    public static final String ATTR_MATERIALTYPE = "materialType";
    @Generated
    public static final String ATTR_PARENTMATERIALNUMBER = "parentMaterialNumber";
    @Generated
    public static final String ATTR_PARENTSAPNUMBER = "parentSapNumber";
    @Generated
    public static final String ATTR_PARENTMATERIALSHORTTEXT = "parentMaterialShortText";
    @Generated
    public static final String ATTR_PARENTMATERIALHIERARCHY = "parentMaterialHierarchy";
    @Generated
    public static final String ATTR_PARENTMATERIALTYPE = "parentMaterialType";
    @Generated
    public static final String ATTR_MOVEMENTTYPE = "movementType";



    public static final String SELECT_ID = "a.id";
    public static final String SELECT_PLANT = "a.plant";
    public static final String SELECT_SERIALOBJECTID = "a.serialObjectId";
    public static final String SELECT_SERIALNUMBER = "a.serialNumber";
    public static final String SELECT_PARENTSERIALOBJECTID = "a.parentSerialObjectId";
    public static final String SELECT_PARENTSERIALNUMBER = "a.parentSerialNumber";
    public static final String SELECT_ORDERNUMBER = "a.orderNumber";
    public static final String SELECT_ARRIVALDATE = "a.arrivalDate";
    public static final String SELECT_ASSEMBLYDATE = "a.assemblyDate";
    public static final String SELECT_ASSEMBLYPO = "a.assemblyPO";
    public static final String SELECT_SUPPLIERCODE = "a.supplierCode";
    public static final String SELECT_SUPPLIERNAME = "a.supplierName";
    public static final String SELECT_COUNTRYCODE = "a.countryCode";
    public static final String SELECT_COUNTRYNAME = "a.countryName";
    public static final String SELECT_REVISIONID = "a.revisionId";
    public static final String SELECT_REVISIONNUMBER = "a.revisionNumber";
    public static final String SELECT_PARENTREVISIONID = "a.parentRevisionId";
    public static final String SELECT_PARENTREVISIONNUMBER = "a.parentRevisionNumber";
    public static final String SELECT_MATERIALNUMBER = "a.materialNumber";
    public static final String SELECT_SAPNUMBER = "a.sapNumber";
    public static final String SELECT_MATERIALSHORTTEXT = "a.materialShortText";
    public static final String SELECT_MATERIALHIERARCHY = "a.materialHierarchy";
    public static final String SELECT_MATERIALTYPE = "a.materialType";
    public static final String SELECT_PARENTMATERIALNUMBER = "a.parentMaterialNumber";
    public static final String SELECT_PARENTSAPNUMBER = "a.parentSapNumber";
    public static final String SELECT_PARENTMATERIALSHORTTEXT = "a.parentMaterialShortText";
    public static final String SELECT_PARENTMATERIALHIERARCHY = "a.parentMaterialHierarchy";
    public static final String SELECT_PARENTMATERIALTYPE = "a.parentMaterialType";
    public static final String SELECT_MOVEMENTTYPE = "a.movementType";



    @Generated
    private long id;
    @Generated
    private String plant;
    @Generated
    private long serialObjectId;
    @Generated
    private String serialNumber;
    @Generated
    private Long parentSerialObjectId;
    @Generated
    private String parentSerialNumber;
    @Generated
    private String orderNumber;
    @Generated
    private LocalDate arrivalDate;
    @Generated
    private LocalDate assemblyDate;
    @Generated
    private String assemblyPO;
    @Generated
    private String supplierCode;
    @Generated
    private String supplierName;
    @Generated
    private String countryCode;
    @Generated
    private String countryName;
    @Generated
    private long revisionId;
    @Generated
    private String revisionNumber;
    @Generated
    private Long parentRevisionId;
    @Generated
    private String parentRevisionNumber;
    @Generated
    private String materialNumber;
    @Generated
    private String sapNumber;
    @Generated
    private String materialShortText;
    @Generated
    private String materialHierarchy;
    @Generated
    private String materialType;
    @Generated
    private String parentMaterialNumber;
    @Generated
    private String parentSapNumber;
    @Generated
    private String parentMaterialShortText;
    @Generated
    private String parentMaterialHierarchy;
    @Generated
    private String parentMaterialType;
    @Generated
    private String movementType;



    @Generated
    public MaterializedArrivalSearchDTO() {
    }

    @Generated
    public MaterializedArrivalSearchDTO(long id) {
        this.id = id;
    }

    @Generated
    public MaterializedArrivalSearchDTO(long id, String plant, long serialObjectId, String serialNumber, Long parentSerialObjectId,
            String parentSerialNumber, String orderNumber, LocalDate arrivalDate, LocalDate assemblyDate, String assemblyPO, String supplierCode,
            String supplierName, String countryCode, String countryName, long revisionId, String revisionNumber, Long parentRevisionId,
            String parentRevisionNumber, String materialNumber, String sapNumber, String materialShortText, String materialHierarchy,
            String materialType, String parentMaterialNumber, String parentSapNumber, String parentMaterialShortText, String parentMaterialHierarchy,
            String parentMaterialType, String movementType) {
        this.id = id;
        this.plant = plant;
        this.serialObjectId = serialObjectId;
        this.serialNumber = serialNumber;
        this.parentSerialObjectId = parentSerialObjectId;
        this.parentSerialNumber = parentSerialNumber;
        this.orderNumber = orderNumber;
        this.arrivalDate = arrivalDate;
        this.assemblyDate = assemblyDate;
        this.assemblyPO = assemblyPO;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.revisionId = revisionId;
        this.revisionNumber = revisionNumber;
        this.parentRevisionId = parentRevisionId;
        this.parentRevisionNumber = parentRevisionNumber;
        this.materialNumber = materialNumber;
        this.sapNumber = sapNumber;
        this.materialShortText = materialShortText;
        this.materialHierarchy = materialHierarchy;
        this.materialType = materialType;
        this.parentMaterialNumber = parentMaterialNumber;
        this.parentSapNumber = parentSapNumber;
        this.parentMaterialShortText = parentMaterialShortText;
        this.parentMaterialHierarchy = parentMaterialHierarchy;
        this.parentMaterialType = parentMaterialType;
        this.movementType = movementType;
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
     * @return the serial object id
     */
    @Generated
    public long getSerialObjectId() {
        return this.serialObjectId;
    }

    /**
     * @param serialObjectId the serial object id to set
     */
    @Generated
    public void setSerialObjectId(long serialObjectId) {
        this.serialObjectId = serialObjectId;
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

    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final var dto = (MaterializedArrivalSearchDTO) obj;

        return this.id == dto.getId();
    }

    @Generated
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
