package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_ASSEMBLYDATE = "assemblyDate";
    @Generated
    public static final String ATTR_CUSTOMERSERIALNUMBER = "customerSerialNumber";
    @Generated
    public static final String ATTR_PRODUCTIONORDERNUMBER = "productionOrderNumber";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MATMATERIALNUMBER = "matMaterialNumber";
    @Generated
    public static final String ATTR_MATID = "matId";
    @Generated
    public static final String ATTR_MATSAPNUMBER = "matSapNumber";
    @Generated
    public static final String ATTR_MATMATERIALHIERARCHY = "matMaterialHierarchy";
    @Generated
    public static final String ATTR_MATSHORTTEXT = "matShortText";
    @Generated
    public static final String ATTR_MATOWNERLOCATIONCODE = "matOwnerLocationCode";
    @Generated
    public static final String ATTR_MATMATERIALTYPECODE = "matMaterialTypeCode";
    @Generated
    public static final String ATTR_MATMATERIALCLASSCODE = "matMaterialClassCode";
    @Generated
    public static final String SELECT_SERIALNUMBER = "a.serialNumber";
    @Generated
    public static final String SELECT_ASSEMBLYDATE = "a.assemblyDate";
    @Generated
    public static final String SELECT_CUSTOMERSERIALNUMBER = "a.customerSerialNumber";
    @Generated
    public static final String SELECT_PRODUCTIONORDERNUMBER = "a.productionOrderNumber";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_MATMATERIALNUMBER = "b.materialNumber";
    @Generated
    public static final String SELECT_MATID = "b.id";
    @Generated
    public static final String SELECT_MATSAPNUMBER = "b.sapNumber";
    @Generated
    public static final String SELECT_MATMATERIALHIERARCHY = "b.materialHierarchy";
    @Generated
    public static final String SELECT_MATSHORTTEXT = "b.shortText";
    @Generated
    public static final String SELECT_MATOWNERLOCATIONCODE = "h.code";
    @Generated
    public static final String SELECT_MATMATERIALTYPECODE = "j.code";
    @Generated
    public static final String SELECT_MATMATERIALCLASSCODE = "i.code";
    @Generated
    private String serialNumber;
    @Generated
    private LocalDate assemblyDate;
    @Generated
    private String customerSerialNumber;
    @Generated
    private String productionOrderNumber;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String matMaterialNumber;
    @Generated
    private long matId;
    @Generated
    private String matSapNumber;
    @Generated
    private String matMaterialHierarchy;
    @Generated
    private String matShortText;
    @Generated
    private String matOwnerLocationCode;
    @Generated
    private String matMaterialTypeCode;
    @Generated
    private String matMaterialClassCode;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param serialNumber
     * @param assemblyDate
     * @param customerSerialNumber
     * @param productionOrderNumber
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param matMaterialNumber
     * @param matId
     * @param matSapNumber
     * @param matMaterialHierarchy
     * @param matShortText
     * @param matOwnerLocationCode
     * @param matMaterialTypeCode
     * @param matMaterialClassCode
     */
    @Generated
    public SerialObjectSearchDTO(String serialNumber, LocalDate assemblyDate, String customerSerialNumber, String productionOrderNumber, long id,
            LocalDateTime creationDate, LocalDateTime lastUpdate, String matMaterialNumber, long matId, String matSapNumber,
            String matMaterialHierarchy, String matShortText, String matOwnerLocationCode, String matMaterialTypeCode, String matMaterialClassCode) {
        this.serialNumber = serialNumber;
        this.assemblyDate = assemblyDate;
        this.customerSerialNumber = customerSerialNumber;
        this.productionOrderNumber = productionOrderNumber;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.matMaterialNumber = matMaterialNumber;
        this.matId = matId;
        this.matSapNumber = matSapNumber;
        this.matMaterialHierarchy = matMaterialHierarchy;
        this.matShortText = matShortText;
        this.matOwnerLocationCode = matOwnerLocationCode;
        this.matMaterialTypeCode = matMaterialTypeCode;
        this.matMaterialClassCode = matMaterialClassCode;
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
     * @return the customer serial number
     */
    @Generated
    public String getCustomerSerialNumber() {
        return this.customerSerialNumber;
    }

    /**
     * @param customerSerialNumber the customer serial number to set
     */
    @Generated
    public void setCustomerSerialNumber(String customerSerialNumber) {
        this.customerSerialNumber = customerSerialNumber;
    }

    /**
     * @return the production order number
     */
    @Generated
    public String getProductionOrderNumber() {
        return this.productionOrderNumber;
    }

    /**
     * @param productionOrderNumber the production order number to set
     */
    @Generated
    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
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
     * @return the material number of the material
     */
    @Generated
    public String getMatMaterialNumber() {
        return this.matMaterialNumber;
    }

    /**
     * @param matMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMatMaterialNumber(String matMaterialNumber) {
        this.matMaterialNumber = matMaterialNumber;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public long getMatId() {
        return this.matId;
    }

    /**
     * @param matId the id of the material to set
     */
    @Generated
    public void setMatId(long matId) {
        this.matId = matId;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMatSapNumber() {
        return this.matSapNumber;
    }

    /**
     * @param matSapNumber the sap number of the material to set
     */
    @Generated
    public void setMatSapNumber(String matSapNumber) {
        this.matSapNumber = matSapNumber;
    }

    /**
     * @return the material hierarchy of the material
     */
    @Generated
    public String getMatMaterialHierarchy() {
        return this.matMaterialHierarchy;
    }

    /**
     * @param matMaterialHierarchy the material hierarchy of the material to set
     */
    @Generated
    public void setMatMaterialHierarchy(String matMaterialHierarchy) {
        this.matMaterialHierarchy = matMaterialHierarchy;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getMatShortText() {
        return this.matShortText;
    }

    /**
     * @param matShortText the short text of the material to set
     */
    @Generated
    public void setMatShortText(String matShortText) {
        this.matShortText = matShortText;
    }

    /**
     * @return the code of the location
     */
    @Generated
    public String getMatOwnerLocationCode() {
        return this.matOwnerLocationCode;
    }

    /**
     * @param matOwnerLocationCode the code of the location to set
     */
    @Generated
    public void setMatOwnerLocationCode(String matOwnerLocationCode) {
        this.matOwnerLocationCode = matOwnerLocationCode;
    }

    /**
     * @return the code of the material type
     */
    @Generated
    public String getMatMaterialTypeCode() {
        return this.matMaterialTypeCode;
    }

    /**
     * @param matMaterialTypeCode the code of the material type to set
     */
    @Generated
    public void setMatMaterialTypeCode(String matMaterialTypeCode) {
        this.matMaterialTypeCode = matMaterialTypeCode;
    }

    /**
     * @return the code of the material class
     */
    @Generated
    public String getMatMaterialClassCode() {
        return this.matMaterialClassCode;
    }

    /**
     * @param matMaterialClassCode the code of the material class to set
     */
    @Generated
    public void setMatMaterialClassCode(String matMaterialClassCode) {
        this.matMaterialClassCode = matMaterialClassCode;
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

        final var dto = (SerialObjectSearchDTO) obj;

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