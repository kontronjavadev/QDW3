package com.kontron.qdw.dto.material;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterialListSapDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_SAPNUMBER = "sapNumber";

    public static final String SELECT_ID = "a." + ATTR_ID;
    public static final String SELECT_SAPNUMBER = "a." + ATTR_SAPNUMBER;

    @Generated
    private long id;
    @Generated
    private String sapNumber;

    /**
     * Default constructor
     */
    @Generated
    public MaterialListSapDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterialListSapDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param sapNumber
     */
    @Generated
    public MaterialListSapDTO(long id, String sapNumber) {
        this.id = id;
        this.sapNumber = sapNumber;
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

        final var dto = (MaterialListSapDTO) obj;

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