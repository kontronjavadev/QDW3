package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import com.kontron.qdw.dto.material.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectDTO implements Serializable {
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
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MATERIAL = "material";
    @Generated
    public static final String ATTR_PARENTOBJECT = "parentObject";
    @Generated
    public static final String ATTR_TRACEBOM = "traceBom";
    @Generated
    public static final String ATTR_PARENTOBJECTSERIALNUMBER = "parentObjectSerialNumber";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
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
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private MaterialListDTO material;
    @Generated
    private SerialObjectListDTO parentObject;
    @Generated
    private TraceBoMListDTO traceBom;
    @Generated
    private String parentObjectSerialNumber;
    @Generated
    private String materialMaterialNumber;
    @Generated
    private String materialSapNumber;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param serialNumber
     * @param assemblyDate
     * @param customerSerialNumber
     * @param productionOrderNumber
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     * @param parentObjectSerialNumber
     * @param materialMaterialNumber
     * @param materialSapNumber
     */
    @Generated
    public SerialObjectDTO(String serialNumber, LocalDate assemblyDate, String customerSerialNumber, String productionOrderNumber, long id,
            int version, LocalDateTime creationDate, LocalDateTime lastUpdate, String parentObjectSerialNumber, String materialMaterialNumber,
            String materialSapNumber) {
        this.serialNumber = serialNumber;
        this.assemblyDate = assemblyDate;
        this.customerSerialNumber = customerSerialNumber;
        this.productionOrderNumber = productionOrderNumber;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.parentObjectSerialNumber = parentObjectSerialNumber;
        this.materialMaterialNumber = materialMaterialNumber;
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
    public MaterialListDTO getMaterial() {
        return this.material;
    }

    /**
     * @param material the material to set
     */
    @Generated
    public void setMaterial(MaterialListDTO material) {
        this.material = material;
    }

    /**
     * @return the serial object
     */
    @Generated
    public SerialObjectListDTO getParentObject() {
        return this.parentObject;
    }

    /**
     * @param parentObject the serial object to set
     */
    @Generated
    public void setParentObject(SerialObjectListDTO parentObject) {
        this.parentObject = parentObject;
    }

    /**
     * @return the trace BoM
     */
    @Generated
    public TraceBoMListDTO getTraceBom() {
        return this.traceBom;
    }

    /**
     * @param traceBom the trace BoM to set
     */
    @Generated
    public void setTraceBom(TraceBoMListDTO traceBom) {
        this.traceBom = traceBom;
    }

    /**
     * @return the serial number of the serial object
     */
    @Generated
    public String getParentObjectSerialNumber() {
        return this.parentObjectSerialNumber;
    }

    /**
     * @param parentObjectSerialNumber the serial number of the serial object to set
     */
    @Generated
    public void setParentObjectSerialNumber(String parentObjectSerialNumber) {
        this.parentObjectSerialNumber = parentObjectSerialNumber;
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

        final var dto = (SerialObjectDTO) obj;

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