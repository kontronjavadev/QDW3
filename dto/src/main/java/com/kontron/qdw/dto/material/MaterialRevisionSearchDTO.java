package com.kontron.qdw.dto.material;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterialRevisionSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_REVISIONNUMBER = "revisionNumber";
    @Generated
    public static final String ATTR_REV2 = "rev2";
    @Generated
    public static final String ATTR_REV6 = "rev6";
    @Generated
    public static final String ATTR_ALTERNATIVENUMBER = "alternativeNumber";
    @Generated
    public static final String ATTR_COMMENT = "comment";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_PLANTCODE = "plantCode";
    @Generated
    public static final String ATTR_MATERIALID = "materialId";
    @Generated
    public static final String ATTR_MATERIALSAPNUMBER = "materialSapNumber";
    @Generated
    public static final String ATTR_MATERIALSHORTTEXT = "materialShortText";
    @Generated
    public static final String ATTR_OWNERLOCATIONCODE = "ownerLocationCode";
    @Generated
    public static final String ATTR_MATERIALTYPECODE = "materialTypeCode";
    @Generated
    public static final String ATTR_MATERIALCLASSCODE = "materialClassCode";
    @Generated
    public static final String SELECT_REVISIONNUMBER = "a.revisionNumber";
    @Generated
    public static final String SELECT_REV2 = "a.rev2";
    @Generated
    public static final String SELECT_REV6 = "a.rev6";
    @Generated
    public static final String SELECT_ALTERNATIVENUMBER = "a.alternativeNumber";
    @Generated
    public static final String SELECT_COMMENT = "a.comment";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_MATERIALMATERIALNUMBER = "b.materialNumber";
    @Generated
    public static final String SELECT_PLANTCODE = "c.code";
    @Generated
    public static final String SELECT_MATERIALID = "b.id";
    @Generated
    public static final String SELECT_MATERIALSAPNUMBER = "b.sapNumber";
    @Generated
    public static final String SELECT_MATERIALSHORTTEXT = "b.shortText";
    @Generated
    public static final String SELECT_OWNERLOCATIONCODE = "e.code";
    @Generated
    public static final String SELECT_MATERIALTYPECODE = "g.code";
    @Generated
    public static final String SELECT_MATERIALCLASSCODE = "f.code";
    @Generated
    private String revisionNumber;
    @Generated
    private String rev2;
    @Generated
    private String rev6;
    @Generated
    private String alternativeNumber;
    @Generated
    private String comment;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String materialMaterialNumber;
    @Generated
    private String plantCode;
    @Generated
    private long materialId;
    @Generated
    private String materialSapNumber;
    @Generated
    private String materialShortText;
    @Generated
    private String ownerLocationCode;
    @Generated
    private String materialTypeCode;
    @Generated
    private String materialClassCode;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialRevisionSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param revisionNumber
     * @param rev2
     * @param rev6
     * @param alternativeNumber
     * @param comment
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param materialMaterialNumber
     * @param plantCode
     * @param materialId
     * @param materialSapNumber
     * @param materialShortText
     * @param ownerLocationCode
     * @param materialTypeCode
     * @param materialClassCode
     */
    @Generated
    public MaterialRevisionSearchDTO(String revisionNumber, String rev2, String rev6, String alternativeNumber, String comment, long id,
            LocalDateTime creationDate, LocalDateTime lastUpdate, String materialMaterialNumber, String plantCode, long materialId,
            String materialSapNumber, String materialShortText, String ownerLocationCode, String materialTypeCode, String materialClassCode) {
        this.revisionNumber = revisionNumber;
        this.rev2 = rev2;
        this.rev6 = rev6;
        this.alternativeNumber = alternativeNumber;
        this.comment = comment;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.materialMaterialNumber = materialMaterialNumber;
        this.plantCode = plantCode;
        this.materialId = materialId;
        this.materialSapNumber = materialSapNumber;
        this.materialShortText = materialShortText;
        this.ownerLocationCode = ownerLocationCode;
        this.materialTypeCode = materialTypeCode;
        this.materialClassCode = materialClassCode;
    }

    /**
     * @return the revision number
     */
    @Generated
    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    /**
     * @param revisionNumber the revision number to set
     */
    @Generated
    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    /**
     * @return the rev 2
     */
    @Generated
    public String getRev2() {
        return this.rev2;
    }

    /**
     * @param rev2 the rev 2 to set
     */
    @Generated
    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    /**
     * @return the rev 6
     */
    @Generated
    public String getRev6() {
        return this.rev6;
    }

    /**
     * @param rev6 the rev 6 to set
     */
    @Generated
    public void setRev6(String rev6) {
        this.rev6 = rev6;
    }

    /**
     * @return the alternative number
     */
    @Generated
    public String getAlternativeNumber() {
        return this.alternativeNumber;
    }

    /**
     * @param alternativeNumber the alternative number to set
     */
    @Generated
    public void setAlternativeNumber(String alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }

    /**
     * @return the comment
     */
    @Generated
    public String getComment() {
        return this.comment;
    }

    /**
     * @param comment the comment to set
     */
    @Generated
    public void setComment(String comment) {
        this.comment = comment;
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
     * @return the code of the plant
     */
    @Generated
    public String getPlantCode() {
        return this.plantCode;
    }

    /**
     * @param plantCode the code of the plant to set
     */
    @Generated
    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
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
     * @return the code of the location
     */
    @Generated
    public String getOwnerLocationCode() {
        return this.ownerLocationCode;
    }

    /**
     * @param ownerLocationCode the code of the location to set
     */
    @Generated
    public void setOwnerLocationCode(String ownerLocationCode) {
        this.ownerLocationCode = ownerLocationCode;
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

        final var dto = (MaterialRevisionSearchDTO) obj;

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