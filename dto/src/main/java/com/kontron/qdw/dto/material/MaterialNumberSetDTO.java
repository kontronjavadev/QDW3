package com.kontron.qdw.dto.material;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterialNumberSetDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_LABEL = "label";
    @Generated
    private long id;
    @Generated
    private String label;

    /**
     * Default constructor
     */
    @Generated
    public MaterialNumberSetDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialNumberSetDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param label
     */
    @Generated
    public MaterialNumberSetDTO(long id, String label) {
        this.id = id;
        this.label = label;
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

        final var dto = (MaterialNumberSetDTO) obj;

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