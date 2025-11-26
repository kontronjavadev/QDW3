package com.kontron.qdw.dto.material;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class BoMItemDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_QUANTITY = "quantity";
    @Generated
    public static final String ATTR_LABEL = "label";
    @Generated
    public static final String ATTR_POSITION = "position";
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
    public static final String ATTR_MATERIALREVISION = "materialRevision";
    @Generated
    public static final String ATTR_MATERIALREVISIONMATERIAL = "materialRevisionMaterial";
    @Generated
    private Double quantity;
    @Generated
    private String label;
    @Generated
    private String position;
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
    private MaterialRevisionListDTO materialRevision;
    @Generated
    private MaterialListDTO materialRevisionMaterial;

    /**
     * Default constructor
     */
    @Generated
    public BoMItemDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public BoMItemDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param quantity
     * @param label
     * @param position
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     */
    @Generated
    public BoMItemDTO(Double quantity, String label, String position, long id, int version, LocalDateTime creationDate, LocalDateTime lastUpdate) {
        this.quantity = quantity;
        this.label = label;
        this.position = position;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the quantity
     */
    @Generated
    public Double getQuantity() {
        return this.quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    @Generated
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the label
     */
    @Generated
    public String getLabel() {
        return this.label;
    }

    /**
     * @param label the label to set
     */
    @Generated
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the position
     */
    @Generated
    public String getPosition() {
        return this.position;
    }

    /**
     * @param position the position to set
     */
    @Generated
    public void setPosition(String position) {
        this.position = position;
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

        final var dto = (BoMItemDTO) obj;

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