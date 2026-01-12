package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import com.kontron.qdw.dto.material.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.base.*;

public class TraceBoMDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_DELIVERYNOTENUMBER = "deliveryNoteNumber";
    @Generated
    public static final String ATTR_LOTNUMBER = "lotNumber";
    @Generated
    public static final String ATTR_ORDERNUMBER = "orderNumber";
    @Generated
    public static final String ATTR_PRODUCTIONDATE = "productionDate";
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
    public static final String ATTR_SUPPLIER = "supplier";
    @Generated
    public static final String ATTR_MATERIALREVISIONMATERIAL = "materialRevisionMaterial";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
    @Generated
    public static final String ATTR_MATERIALSHORTTEXT = "materialShortText";
    @Generated
    private String deliveryNoteNumber;
    @Generated
    private String lotNumber;
    @Generated
    private String orderNumber;
    @Generated
    private LocalDate productionDate;
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
    private SupplierListDTO supplier;
    @Generated
    private MaterialListDTO materialRevisionMaterial;
    @Generated
    private String materialSapNumber;
    @Generated
    private String materialShortText;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public TraceBoMDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param deliveryNoteNumber
     * @param lotNumber
     * @param orderNumber
     * @param productionDate
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     * @param materialSapNumber
     * @param materialShortText
     */
    @Generated
    public TraceBoMDTO(String deliveryNoteNumber, String lotNumber, String orderNumber, LocalDate productionDate, long id, int version,
            LocalDateTime creationDate, LocalDateTime lastUpdate, String materialSapNumber, String materialShortText) {
        this.deliveryNoteNumber = deliveryNoteNumber;
        this.lotNumber = lotNumber;
        this.orderNumber = orderNumber;
        this.productionDate = productionDate;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.materialSapNumber = materialSapNumber;
        this.materialShortText = materialShortText;
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
     * @return the lot number
     */
    @Generated
    public String getLotNumber() {
        return this.lotNumber;
    }

    /**
     * @param lotNumber the lot number to set
     */
    @Generated
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
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
     * @return the production date
     */
    @Generated
    public LocalDate getProductionDate() {
        return this.productionDate;
    }

    /**
     * @param productionDate the production date to set
     */
    @Generated
    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
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

        final var dto = (TraceBoMDTO) obj;

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