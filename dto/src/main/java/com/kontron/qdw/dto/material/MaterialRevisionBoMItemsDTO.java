package com.kontron.qdw.dto.material;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterialRevisionBoMItemsDTO implements Serializable {
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
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_MATERIALID = "materialId";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
    @Generated
    public static final String ATTR_MATERIALSHORTTEXT = "materialShortText";
    @Generated
    public static final String ATTR_MATERIALTYPECODE = "materialTypeCode";
    @Generated
    public static final String ATTR_MATERIALCLASSCODE = "materialClassCode";
    @Generated
    public static final String SELECT_QUANTITY = "a.quantity";
    @Generated
    public static final String SELECT_LABEL = "a.label";
    @Generated
    public static final String SELECT_POSITION = "a.position";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "c.id";
    @Generated
    public static final String SELECT_MATERIALID = "b.id";
    @Generated
    public static final String SELECT_MATERIALMATERIALNUMBER = "b.materialNumber";
    @Generated
    public static final String SELECT_MATERIALSAPNUMBER = "b.sapNumber";
    @Generated
    public static final String SELECT_MATERIALSHORTTEXT = "b.shortText";
    @Generated
    public static final String SELECT_MATERIALTYPECODE = "f.code";
    @Generated
    public static final String SELECT_MATERIALCLASSCODE = "e.code";
    @Generated
    private Double quantity;
    @Generated
    private String label;
    @Generated
    private String position;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private long materialRevisionId;
    @Generated
    private Long materialId;
    @Generated
    private String materialMaterialNumber;
    @Generated
    private String materialSapNumber;
    @Generated
    private String materialShortText;
    @Generated
    private String materialTypeCode;
    @Generated
    private String materialClassCode;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionBoMItemsDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialRevisionBoMItemsDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param quantity
     * @param label
     * @param position
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param materialRevisionId
     * @param materialId
     * @param materialMaterialNumber
     * @param materialSapNumber
     * @param materialShortText
     * @param materialTypeCode
     * @param materialClassCode
     */
    @Generated
    public MaterialRevisionBoMItemsDTO(Double quantity, String label, String position, long id, LocalDateTime creationDate, LocalDateTime lastUpdate,
            long materialRevisionId, Long materialId, String materialMaterialNumber, String materialSapNumber, String materialShortText,
            String materialTypeCode, String materialClassCode) {
        this.quantity = quantity;
        this.label = label;
        this.position = position;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.materialRevisionId = materialRevisionId;
        this.materialId = materialId;
        this.materialMaterialNumber = materialMaterialNumber;
        this.materialSapNumber = materialSapNumber;
        this.materialShortText = materialShortText;
        this.materialTypeCode = materialTypeCode;
        this.materialClassCode = materialClassCode;
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
     * @return the id of the material revision
     */
    @Generated
    public long getMaterialRevisionId() {
        return this.materialRevisionId;
    }

    /**
     * @param materialRevisionId the id of the material revision to set
     */
    @Generated
    public void setMaterialRevisionId(long materialRevisionId) {
        this.materialRevisionId = materialRevisionId;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public Long getMaterialId() {
        return this.materialId;
    }

    /**
     * @param materialId the id of the material to set
     */
    @Generated
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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

    /**
     * @return the code of the material type
     */
    @Generated
    public String getMaterialTypeCode() {
        return this.materialTypeCode;
    }

    /**
     * @param materialTypeCode the code of the material type to set
     */
    @Generated
    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    /**
     * @return the code of the material class
     */
    @Generated
    public String getMaterialClassCode() {
        return this.materialClassCode;
    }

    /**
     * @param materialClassCode the code of the material class to set
     */
    @Generated
    public void setMaterialClassCode(String materialClassCode) {
        this.materialClassCode = materialClassCode;
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

        final var dto = (MaterialRevisionBoMItemsDTO) obj;

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