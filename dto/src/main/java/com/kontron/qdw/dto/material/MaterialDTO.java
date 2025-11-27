package com.kontron.qdw.dto.material;

import java.io.Serializable;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.base.*;

public class MaterialDTO implements Serializable {
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
    public static final String ATTR_VERSION = "version";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_OWNERLOCATION = "ownerLocation";
    @Generated
    public static final String ATTR_MATERIALCLASS = "materialClass";
    @Generated
    public static final String ATTR_MATERIALTYPE = "materialType";
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
    private int version;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private LocationListDTO ownerLocation;
    @Generated
    private MaterialClassListDTO materialClass;
    @Generated
    private MaterialTypeListDTO materialType;

    /**
     * Default constructor
     */
    @Generated
    public MaterialDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor with ID and display attribute
     * @param materialNumber
     * @param id
     */
    @Generated
    public MaterialDTO(String materialNumber, long id) {
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
     * @param version
     * @param creationDate
     * @param lastUpdate
     */
    @Generated
    public MaterialDTO(String materialNumber, String sapNumber, String shortText, String comment, Double fitValue, String materialHierarchy,
            boolean searchSubAssemblies, long id, int version, LocalDateTime creationDate, LocalDateTime lastUpdate) {
        this.materialNumber = materialNumber;
        this.sapNumber = sapNumber;
        this.shortText = shortText;
        this.comment = comment;
        this.fitValue = fitValue;
        this.materialHierarchy = materialHierarchy;
        this.searchSubAssemblies = searchSubAssemblies;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
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
     * @return the location
     */
    @Generated
    public LocationListDTO getOwnerLocation() {
        return this.ownerLocation;
    }

    /**
     * @param ownerLocation the location to set
     */
    @Generated
    public void setOwnerLocation(LocationListDTO ownerLocation) {
        this.ownerLocation = ownerLocation;
    }

    /**
     * @return the material class
     */
    @Generated
    public MaterialClassListDTO getMaterialClass() {
        return this.materialClass;
    }

    /**
     * @param materialClass the material class to set
     */
    @Generated
    public void setMaterialClass(MaterialClassListDTO materialClass) {
        this.materialClass = materialClass;
    }

    /**
     * @return the material type
     */
    @Generated
    public MaterialTypeListDTO getMaterialType() {
        return this.materialType;
    }

    /**
     * @param materialType the material type to set
     */
    @Generated
    public void setMaterialType(MaterialTypeListDTO materialType) {
        this.materialType = materialType;
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

        final var dto = (MaterialDTO) obj;

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