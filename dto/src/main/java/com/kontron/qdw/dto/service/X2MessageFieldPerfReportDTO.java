package com.kontron.qdw.dto.service;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class X2MessageFieldPerfReportDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_DEFECTCOMPONENT = "defectComponent";
    @Generated
    public static final String ATTR_SERVICEMESSAGEID = "serviceMessageId";
    @Generated
    private long id;
    @Generated
    private String defectComponent;
    @Generated
    private long serviceMessageId;

    /**
     * Default constructor
     */
    @Generated
    public X2MessageFieldPerfReportDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public X2MessageFieldPerfReportDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param id
     * @param defectComponent
     * @param serviceMessageId
     */
    @Generated
    public X2MessageFieldPerfReportDTO(long id, String defectComponent, long serviceMessageId) {
        this.id = id;
        this.defectComponent = defectComponent;
        this.serviceMessageId = serviceMessageId;
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

    /**
     * @return the id of the service message
     */
    @Generated
    public long getServiceMessageId() {
        return this.serviceMessageId;
    }

    /**
     * @param serviceMessageId the id of the service message to set
     */
    @Generated
    public void setServiceMessageId(long serviceMessageId) {
        this.serviceMessageId = serviceMessageId;
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

        final var dto = (X2MessageFieldPerfReportDTO) obj;

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