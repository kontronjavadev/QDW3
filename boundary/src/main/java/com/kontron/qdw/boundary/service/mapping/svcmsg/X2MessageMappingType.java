package com.kontron.qdw.boundary.service.mapping.svcmsg;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "X2MessageType")
@XmlAccessorType(XmlAccessType.FIELD)
public class X2MessageMappingType implements Serializable {

    private static final long serialVersionUID = -8791346723732000043L;

    @XmlAttribute(name = "x2_alt_part_no", required = false)
    private String alternativePartNo;

    @XmlAttribute(name = "x2_analysis", required = false)
    private String analysisText;

    @XmlAttribute(name = "x2_cust_error_note", required = false)
    private String customerReport;

    @XmlAttribute(name = "x2_defect_code", required = true)
    private String faultAnalysisCode;

    @XmlAttribute(name = "x2_defect_code_group", required = false)
    private String faultAnalysisGroup;

    @XmlAttribute(name = "x2_designator", required = false)
    private String designator;

    @XmlAttribute(name = "x2_error_code", required = true)
    private String repairErrorCode;

    @XmlAttribute(name = "x2_error_group", required = false)
    private String errorCodeGroup;

    @XmlAttribute(name = "x2_error_text", required = false)
    private String errorText;

    @XmlAttribute(name = "x2_id", required = true)
    private String id;

    @XmlAttribute(name = "x2_part_no", required = true)
    private String materialSapNumber;

    @XmlAttribute(name = "x2_position", required = false)
    private String defectComponent;

    @XmlAttribute(name = "x2_revision", required = true)
    private String materialRevisionNo;

    @XmlAttribute(name = "x2_serial_no", required = true)
    private String serialObjectSerialNumber;

    @XmlAttribute(name = "x2_state", required = true)
    private String repairStateCode;

    @XmlAttribute(name = "x2_work_center", required = false)
    private String workCenter;


    public X2MessageMappingType() {
        super();
    }

    public X2MessageMappingType(String id) {
        super();
        this.id = id;
    }


    public String getAlternativePartNo() {
        return alternativePartNo;
    }

    public void setAlternativePartNo(String alternativePartNo) {
        this.alternativePartNo = alternativePartNo;
    }

    public String getAnalysisText() {
        return analysisText;
    }

    public void setAnalysisText(String analysisText) {
        this.analysisText = analysisText;
    }

    public String getCustomerReport() {
        return customerReport;
    }

    public void setCustomerReport(String customerReport) {
        this.customerReport = customerReport;
    }

    public String getFaultAnalysisCode() {
        return faultAnalysisCode;
    }

    public void setFaultAnalysisCode(String faultAnalysisCode) {
        this.faultAnalysisCode = faultAnalysisCode;
    }

    public String getFaultAnalysisGroup() {
        return faultAnalysisGroup;
    }

    public void setFaultAnalysisGroup(String faultAnalysisGroup) {
        this.faultAnalysisGroup = faultAnalysisGroup;
    }

    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    public String getRepairErrorCode() {
        return repairErrorCode;
    }

    public void setRepairErrorCode(String repairErrorCode) {
        this.repairErrorCode = repairErrorCode;
    }

    public String getErrorCodeGroup() {
        return errorCodeGroup;
    }

    public void setErrorCodeGroup(String errorCodeGroup) {
        this.errorCodeGroup = errorCodeGroup;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialSapNumber() {
        return materialSapNumber;
    }

    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
    }

    public String getDefectComponent() {
        return defectComponent;
    }

    public void setDefectComponent(String defectComponent) {
        this.defectComponent = defectComponent;
    }

    public String getMaterialRevisionNo() {
        return materialRevisionNo;
    }

    public void setMaterialRevisionNo(String materialRevisionNo) {
        this.materialRevisionNo = materialRevisionNo;
    }

    public String getSerialObjectSerialNumber() {
        return serialObjectSerialNumber;
    }

    public void setSerialObjectSerialNumber(String serialObjectSerialNumber) {
        this.serialObjectSerialNumber = serialObjectSerialNumber;
    }

    public String getRepairStateCode() {
        return repairStateCode;
    }

    public void setRepairStateCode(String repairStateCode) {
        this.repairStateCode = repairStateCode;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        X2MessageMappingType mappingObject = (X2MessageMappingType) obj;

        if (this.id == null) {
            if (mappingObject.getId() != null) {
                return false;
            }
        }
        else if (!this.id.equals(mappingObject.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
