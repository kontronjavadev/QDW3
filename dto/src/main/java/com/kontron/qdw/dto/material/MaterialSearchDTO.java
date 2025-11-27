package com.kontron.qdw.dto.material;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterialSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_MATERIALNUMBER = "materialNumber";
    @Generated
    public static final String ATTR_SAPNUMBER = "sapNumber";
    @Generated
    public static final String ATTR_SHORTTEXT = "shortText";
    @Generated
    public static final String ATTR_COMMENT = "comment";
    @Generated
    public static final String ATTR_FITVALUE = "fitValue";
    @Generated
    public static final String ATTR_MATERIALHIERARCHY = "materialHierarchy";
    @Generated
    public static final String ATTR_SEARCHSUBASSEMBLIES = "searchSubAssemblies";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_OWNERLOCATIONCODE = "ownerLocationCode";
    @Generated
    public static final String ATTR_MATERIALCLASSCODE = "materialClassCode";
    @Generated
    public static final String ATTR_MATERIALTYPECODE = "materialTypeCode";
    @Generated
    public static final String SELECT_MATERIALNUMBER = "a.materialNumber";
    @Generated
    public static final String SELECT_SAPNUMBER = "a.sapNumber";
    @Generated
    public static final String SELECT_SHORTTEXT = "a.shortText";
    @Generated
    public static final String SELECT_COMMENT = "a.comment";
    @Generated
    public static final String SELECT_FITVALUE = "a.fitValue";
    @Generated
    public static final String SELECT_MATERIALHIERARCHY = "a.materialHierarchy";
    @Generated
    public static final String SELECT_SEARCHSUBASSEMBLIES = "a.searchSubAssemblies";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_OWNERLOCATIONCODE = "b.code";
    @Generated
    public static final String SELECT_MATERIALCLASSCODE = "c.code";
    @Generated
    public static final String SELECT_MATERIALTYPECODE = "d.code";
    @Generated
    private String materialNumber;
    @Generated
    private String sapNumber;
    @Generated
    private String shortText;
    @Generated
    private String comment;
    @Generated
    private Double fitValue;
    @Generated
    private String materialHierarchy;
    @Generated
    private boolean searchSubAssemblies;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String ownerLocationCode;
    @Generated
    private String materialClassCode;
    @Generated
    private String materialTypeCode;

    /**
     * Default constructor
     */
    @Generated
    public MaterialSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor with ID and display attribute
     * @param materialNumber
     * @param id
     */
    @Generated
    public MaterialSearchDTO(String materialNumber, long id) {
        this.materialNumber = materialNumber;
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param materialNumber
     * @param sapNumber
     * @param shortText
     * @param comment
     * @param fitValue
     * @param materialHierarchy
     * @param searchSubAssemblies
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param ownerLocationCode
     * @param materialClassCode
     * @param materialTypeCode
     */
    @Generated
    public MaterialSearchDTO(String materialNumber, String sapNumber, String shortText, String comment, Double fitValue, String materialHierarchy,
            boolean searchSubAssemblies, long id, LocalDateTime creationDate, LocalDateTime lastUpdate, String ownerLocationCode,
            String materialClassCode, String materialTypeCode) {
        this.materialNumber = materialNumber;
        this.sapNumber = sapNumber;
        this.shortText = shortText;
        this.comment = comment;
        this.fitValue = fitValue;
        this.materialHierarchy = materialHierarchy;
        this.searchSubAssemblies = searchSubAssemblies;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.ownerLocationCode = ownerLocationCode;
        this.materialClassCode = materialClassCode;
        this.materialTypeCode = materialTypeCode;
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
     * @return the short text
     */
    @Generated
    public String getShortText() {
        return this.shortText;
    }

    /**
     * @param shortText the short text to set
     */
    @Generated
    public void setShortText(String shortText) {
        this.shortText = shortText;
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
     * @return the fit value
     */
    @Generated
    public Double getFitValue() {
        return this.fitValue;
    }

    /**
     * @param fitValue the fit value to set
     */
    @Generated
    public void setFitValue(Double fitValue) {
        this.fitValue = fitValue;
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
     * @return true if the search sub assemblies flag is set
     */
    @Generated
    public boolean isSearchSubAssemblies() {
        return this.searchSubAssemblies;
    }

    /**
     * @param searchSubAssemblies the value of the search sub assemblies flag to set
     */
    @Generated
    public void setSearchSubAssemblies(boolean searchSubAssemblies) {
        this.searchSubAssemblies = searchSubAssemblies;
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

        final var dto = (MaterialSearchDTO) obj;

        return this.id == dto.getId() && this.materialNumber.equals(dto.getMaterialNumber());
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