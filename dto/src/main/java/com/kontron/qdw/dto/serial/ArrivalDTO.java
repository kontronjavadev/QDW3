package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import com.kontron.qdw.dto.material.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.base.*;

public class ArrivalDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ARRIVALDATE = "arrivalDate";
    @Generated
    public static final String ATTR_ORDERNUMBER = "orderNumber";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MATERIALREVISION = "materialRevision";
    @Generated
    public static final String ATTR_MOVEMENTTYPE = "movementType";
    @Generated
    public static final String ATTR_PLANT = "plant";
    @Generated
    public static final String ATTR_SERIALOBJECT = "serialObject";
    @Generated
    public static final String ATTR_SUPPLIER = "supplier";
    @Generated
    public static final String ATTR_SERIALOBJECTSERIALNUMBER = "serialObjectSerialNumber";
    @Generated
    public static final String ATTR_MATERIALREVISIONMATERIAL = "materialRevisionMaterial";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
    @Generated
    public static final String ATTR_MATERIALREVISIONMATERIALTYPE = "materialRevisionMaterialType";
    @Generated
    public static final String ATTR_MATERIALREVISIONMATERIALCLASS = "materialRevisionMaterialClass";
    @Generated
    public static final String ATTR_MATERIALSHORTTEXT = "materialShortText";
    @Generated
    private LocalDate arrivalDate;
    @Generated
    private String orderNumber;
    @Generated
    private long id;
    @Generated
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private MaterialRevisionListDTO materialRevision;
    @Generated
    private MovementTypeListDTO movementType;
    @Generated
    private PlantListDTO plant;
    @Generated
    private SerialObjectListDTO serialObject;
    @Generated
    private SupplierListDTO supplier;
    @Generated
    private String serialObjectSerialNumber;
    @Generated
    private MaterialListDTO materialRevisionMaterial;
    @Generated
    private String materialSapNumber;
    @Generated
    private MaterialTypeListDTO materialRevisionMaterialType;
    @Generated
    private MaterialClassListDTO materialRevisionMaterialClass;
    @Generated
    private String materialShortText;

    /**
     * Default constructor
     */
    @Generated
    public ArrivalDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public ArrivalDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param arrivalDate
     * @param orderNumber
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     * @param serialObjectSerialNumber
     * @param materialSapNumber
     * @param materialShortText
     */
    @Generated
    public ArrivalDTO(LocalDate arrivalDate, String orderNumber, long id, int version, LocalDateTime creationDate, LocalDateTime lastUpdate,
            String serialObjectSerialNumber, String materialSapNumber, String materialShortText) {
        this.arrivalDate = arrivalDate;
        this.orderNumber = orderNumber;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.serialObjectSerialNumber = serialObjectSerialNumber;
        this.materialSapNumber = materialSapNumber;
        this.materialShortText = materialShortText;
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
     * @return the movement type
     */
    @Generated
    public MovementTypeListDTO getMovementType() {
        return this.movementType;
    }

    /**
     * @param movementType the movement type to set
     */
    @Generated
    public void setMovementType(MovementTypeListDTO movementType) {
        this.movementType = movementType;
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
     * @return the supplier
     */
    @Generated
    public SupplierListDTO getSupplier() {
        return this.supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    @Generated
    public void setSupplier(SupplierListDTO supplier) {
        this.supplier = supplier;
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
     * @return the material type
     */
    @Generated
    public MaterialTypeListDTO getMaterialRevisionMaterialType() {
        return this.materialRevisionMaterialType;
    }

    /**
     * @param materialRevisionMaterialType the material type to set
     */
    @Generated
    public void setMaterialRevisionMaterialType(MaterialTypeListDTO materialRevisionMaterialType) {
        this.materialRevisionMaterialType = materialRevisionMaterialType;
    }

    /**
     * @return the material class
     */
    @Generated
    public MaterialClassListDTO getMaterialRevisionMaterialClass() {
        return this.materialRevisionMaterialClass;
    }

    /**
     * @param materialRevisionMaterialClass the material class to set
     */
    @Generated
    public void setMaterialRevisionMaterialClass(MaterialClassListDTO materialRevisionMaterialClass) {
        this.materialRevisionMaterialClass = materialRevisionMaterialClass;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getMaterialShortText() {
        return this.materialShortText;
    }

    /**
     * @param materialShortText the short text of the material to set
     */
    @Generated
    public void setMaterialShortText(String materialShortText) {
        this.materialShortText = materialShortText;
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

        final var dto = (ArrivalDTO) obj;

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