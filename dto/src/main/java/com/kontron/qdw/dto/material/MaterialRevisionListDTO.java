package com.kontron.qdw.dto.material;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterialRevisionListDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_REVISIONNUMBER = "revisionNumber";
    @Generated
    public static final String ATTR_PLANTCODE = "plantCode";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_REVISIONNUMBER = "a.revisionNumber";
    @Generated
    public static final String SELECT_PLANTCODE = "a.plant.code";
    @Generated
    private long id;
    @Generated
    private String revisionNumber;
    @Generated
    private String plantCode;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionListDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialRevisionListDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param revisionNumber
     * @param plantCode
     */
    @Generated
    public MaterialRevisionListDTO(long id, String revisionNumber, String plantCode) {
        this.id = id;
        this.revisionNumber = revisionNumber;
        this.plantCode = plantCode;
    }

    @Override
    public String toString() {
        if (getRevisionNumber() == null) {
            // es steht wohl überhaupt nichts im DTO, mglw. vom Converter produziert, aber wir wollen trotzdem etwas sehen!
            return "id " + getId();
        }
        return getPlantCode() == null
                ? getRevisionNumber()
                : String.format("%s (plant %s)", getRevisionNumber(), getPlantCode());
    }

    public String toRevNrString() {
        if (getRevisionNumber() == null) {
            // es steht wohl überhaupt nichts im DTO, mglw. vom Converter produziert, aber wir wollen trotzdem etwas sehen!
            return "id " + getId();
        }
        return getRevisionNumber();
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

        final var dto = (MaterialRevisionListDTO) obj;

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