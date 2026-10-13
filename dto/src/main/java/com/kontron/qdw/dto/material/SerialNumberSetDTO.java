package com.kontron.qdw.dto.material;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialNumberSetDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_CODE = "code";
    @Generated
    public static final String ATTR_SHORTTEXT = "shortText";
    @Generated
    public static final String SELECT_CODE = "a.code";
    @Generated
    public static final String SELECT_SHORTTEXT = "a.shortText";
    @Generated
    private String code;
    @Generated
    private String shortText;

    /**
     * Default constructor
     */
    @Generated
    public SerialNumberSetDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param code
     */
    @Generated
    public SerialNumberSetDTO(String code) {
        this.code = code;
    }

    /**
     * Constructor using fields
     * @param code
     * @param shortText
     */
    @Generated
    public SerialNumberSetDTO(String code, String shortText) {
        this.code = code;
        this.shortText = shortText;
    }

    /**
     * @return the code
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * @param code the code to set
     */
    @Generated
    public void setCode(String code) {
        this.code = code;
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

        final var dto = (SerialNumberSetDTO) obj;

        if (this.code == null) {
            if (dto.getCode() != null)
                return false;
        }
        else if (!this.code.equals(dto.getCode()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (code == null) ? 0 : code.hashCode();
    }

}