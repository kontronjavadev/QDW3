package com.kontron.qdw.dto.mv;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ServiceMessageFieldPerfReportDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_DEFECTCOMPONENT = "defectComponent";
    @Generated
    private Long id;
    @Generated
    private String defectComponent;

    /**
     * Default constructor
     */
    @Generated
    public ServiceMessageFieldPerfReportDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public ServiceMessageFieldPerfReportDTO(Long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param defectComponent
     */
    @Generated
    public ServiceMessageFieldPerfReportDTO(Long id, String defectComponent) {
        this.id = id;
        this.defectComponent = defectComponent;
    }

    /**
     * @return the id
     */
    @Generated
    public Long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the defect component
     */
    @Generated
    public String getDefectComponent() {
        return this.defectComponent;
    }

    /**
     * @param defectComponent the defect component to set
     */
    @Generated
    public void setDefectComponent(String defectComponent) {
        this.defectComponent = defectComponent;
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

        final var dto = (ServiceMessageFieldPerfReportDTO) obj;

        if (this.id == null) {
            if (dto.getId() != null)
                return false;
        }
        else if (!this.id.equals(dto.getId()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

}