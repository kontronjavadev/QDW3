package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectSerialObjectsDTO implements Serializable {
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
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_MATERIALID = "materialId";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
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
    public static final String SELECT_MATERIALMATERIALNUMBER = "b.materialNumber";
    @Generated
    public static final String SELECT_MATERIALID = "b.id";
    @Generated
    public static final String SELECT_MATERIALSAPNUMBER = "b.sapNumber";
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
    private String materialMaterialNumber;
    @Generated
    private long materialId;
    @Generated
    private String materialSapNumber;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectSerialObjectsDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectSerialObjectsDTO(long id) {
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
     * @param materialMaterialNumber
     * @param materialId
     * @param materialSapNumber
     */
    @Generated
    public SerialObjectSerialObjectsDTO(String serialNumber, LocalDate assemblyDate, String customerSerialNumber, String productionOrderNumber,
            long id, LocalDateTime creationDate, LocalDateTime lastUpdate, String materialMaterialNumber, long materialId, String materialSapNumber) {
        this.serialNumber = serialNumber;
        this.assemblyDate = assemblyDate;
        this.customerSerialNumber = customerSerialNumber;
        this.productionOrderNumber = productionOrderNumber;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.materialMaterialNumber = materialMaterialNumber;
        this.materialId = materialId;
        this.materialSapNumber = materialSapNumber;
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

        final var dto = (SerialObjectSerialObjectsDTO) obj;

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