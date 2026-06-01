package com.kontron.qdw.dto.material;

import java.io.Serializable;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

public class EWSEntryDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_THRESHOLD = "threshold";
    @Generated
    public static final String ATTR_BOARDORSYSTEM = "boardOrSystem";
    @Generated
    public static final String ATTR_FILTERCRITERION = "filterCriterion";
    @Generated
    public static final String ATTR_TYPE = "type";
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
    private double threshold;
    @Generated
    private boolean boardOrSystem;
    @Generated
    private String filterCriterion;
    @Generated
    private EWSType type;
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

    /**
     * Default constructor
     */
    @Generated
    public EWSEntryDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public EWSEntryDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param threshold
     * @param boardOrSystem
     * @param filterCriterion
     * @param type
     * @param id
     * @param version
     * @param creationDate
     * @param lastUpdate
     */
    @Generated
    public EWSEntryDTO(double threshold, boolean boardOrSystem, String filterCriterion, EWSType type, long id, int version,
            LocalDateTime creationDate, LocalDateTime lastUpdate) {
        this.threshold = threshold;
        this.boardOrSystem = boardOrSystem;
        this.filterCriterion = filterCriterion;
        this.type = type;
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the threshold
     */
    @Generated
    public double getThreshold() {
        return this.threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    @Generated
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * @return true if the board or system flag is set
     */
    @Generated
    public boolean isBoardOrSystem() {
        return this.boardOrSystem;
    }

    /**
     * @param boardOrSystem the value of the board or system flag to set
     */
    @Generated
    public void setBoardOrSystem(boolean boardOrSystem) {
        this.boardOrSystem = boardOrSystem;
    }

    /**
     * @return the filter criterion
     */
    @Generated
    public String getFilterCriterion() {
        return this.filterCriterion;
    }

    /**
     * @param filterCriterion the filter criterion to set
     */
    @Generated
    public void setFilterCriterion(String filterCriterion) {
        this.filterCriterion = filterCriterion;
    }

    /**
     * @return the type
     */
    @Generated
    public EWSType getType() {
        return this.type;
    }

    /**
     * @param type the type to set
     */
    @Generated
    public void setType(EWSType type) {
        this.type = type;
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

        final var dto = (EWSEntryDTO) obj;

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