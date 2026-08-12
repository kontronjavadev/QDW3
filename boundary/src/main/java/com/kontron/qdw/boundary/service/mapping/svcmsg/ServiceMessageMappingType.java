package com.kontron.qdw.boundary.service.mapping.svcmsg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "ServiceMessageType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceMessageMappingType implements Serializable {

    private static final long serialVersionUID = -7825524311553739882L;

    @XmlAttribute(name = "analysis", required = false)
    private String analysisText;

    @XmlAttribute(name = "basic_finish_date", required = false)
    private String basicFinishDate;

    @XmlAttribute(name = "basic_start_date", required = false)
    private String basicStartDate;

    @XmlAttribute(name = "cust_error_note", required = false)
    private String customerReport;

    @XmlAttribute(name = "defect_code", required = true)
    private String faultAnalysisCode;

    @XmlAttribute(name = "delivery_note_no", required = false)
    private String deliveryNoteNumber;

    @XmlAttribute(required = false)
    private String designator;

    @XmlAttribute(name = "epidemic", required = true)
    private boolean epidemicFailure;

    @XmlAttribute(name = "error_code", required = true)
    private String repairErrorCode;

    @XmlAttribute(name = "defect_code_group", required = false)
    private String faultAnalysisGroup;

    @XmlAttribute(name = "error_group", required = false)
    private String repairErrorCodeGroup;

    @XmlAttribute(name = "error_text", required = false)
    private String errorText;

    @XmlAttribute(name = "failure_origin_code", required = false)
    private String origin;

    @XmlAttribute(name = "part_no", required = true)
    private String materialSapNumber;

    @XmlAttribute(name = "plant", required = true)
    private String plantCode = "6000";

    @XmlAttribute(name = "report_ext", required = false)
    private String externalReport;

    @XmlAttribute(name = "report_int", required = false)
    private String internalReport;

    @XmlAttribute(name = "revision", required = true)
    private String materialRevisionNo;

    @XmlAttribute(name = "rma", required = true)
    private String serviceOrderCode;

    @XmlAttribute(name = "rma_arrival_date", required = false)
    private String internalArrivalDate;

    @XmlAttribute(name = "rma_shipment_date", required = false)
    private String internalShipmentDate;

    @XmlAttribute(name = "rma_type_id", required = true)
    private String rMATypeCode;

    @XmlAttribute(name = "rma_type_group", required = false)
    private String rmaTypeGroup;

    @XmlAttribute(name = "serial_no", required = true)
    private String serialObjectSerialNumber;

    @XmlAttribute(name = "service_notif_no", required = false)
    private String serviceMessageId;

    @XmlAttribute(name = "service_order", required = true)
    private String id;

    @XmlAttribute(name = "state", required = true)
    private String repairStateCode;

    @XmlAttribute(name = "task_id", required = true)
    private String repairTaskCode;

    @XmlAttribute(name = "task_group", required = false)
    private String repairTaskGroup;

    @XmlAttribute(name = "task_text", required = false)
    private String repairTaskText;

    @XmlAttribute(name = "work_center", required = false)
    private String workCenter;

    @XmlTransient
    private String repairLocationCode = "UNDEF";

    @XmlTransient
    private String repairServiceCode = "UNDEF";

    @XmlAttribute(name = "position", required = false)
    private String defectComponent;

    @XmlElement(name = "x2", required = false)
    private List<X2MessageMappingType> x2Messages = new ArrayList<>();


    public ServiceMessageMappingType() {
        super();
    }

    public ServiceMessageMappingType(String id) {
        super();
        this.id = id;
    }


    public String getAnalysisText() {
        return analysisText;
    }

    public void setAnalysisText(String analysisText) {
        this.analysisText = analysisText;
    }

    public String getBasicFinishDate() {
        return basicFinishDate;
    }

    public void setBasicFinishDate(String basicFinishDate) {
        this.basicFinishDate = basicFinishDate;
    }

    public String getBasicStartDate() {
        return basicStartDate;
    }

    public void setBasicStartDate(String basicStartDate) {
        this.basicStartDate = basicStartDate;
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

    public String getDeliveryNoteNumber() {
        return deliveryNoteNumber;
    }

    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    public boolean isEpidemicFailure() {
        return epidemicFailure;
    }

    public void setEpidemicFailure(boolean epidemicFailure) {
        this.epidemicFailure = epidemicFailure;
    }

    public String getRepairErrorCode() {
        return repairErrorCode;
    }

    public void setRepairErrorCode(String repairErrorCode) {
        this.repairErrorCode = repairErrorCode;
    }

    public String getFaultAnalysisGroup() {
        return faultAnalysisGroup;
    }

    public void setFaultAnalysisGroup(String faultAnalysisGroup) {
        this.faultAnalysisGroup = faultAnalysisGroup;
    }

    public String getRepairErrorCodeGroup() {
        return repairErrorCodeGroup;
    }

    public void setRepairErrorCodeGroup(String repairErrorCodeGroup) {
        this.repairErrorCodeGroup = repairErrorCodeGroup;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMaterialSapNumber() {
        return materialSapNumber;
    }

    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getExternalReport() {
        return externalReport;
    }

    public void setExternalReport(String externalReport) {
        this.externalReport = externalReport;
    }

    public String getInternalReport() {
        return internalReport;
    }

    public void setInternalReport(String internalReport) {
        this.internalReport = internalReport;
    }

    public String getMaterialRevisionNo() {
        return materialRevisionNo;
    }

    public void setMaterialRevisionNo(String materialRevisionNo) {
        this.materialRevisionNo = materialRevisionNo;
    }

    public String getServiceOrderCode() {
        return serviceOrderCode;
    }

    public void setServiceOrderCode(String serviceOrderCode) {
        this.serviceOrderCode = serviceOrderCode;
    }

    public String getInternalArrivalDate() {
        return internalArrivalDate;
    }

    public void setInternalArrivalDate(String internalArrivalDate) {
        this.internalArrivalDate = internalArrivalDate;
    }

    public String getInternalShipmentDate() {
        return internalShipmentDate;
    }

    public void setInternalShipmentDate(String internalShipmentDate) {
        this.internalShipmentDate = internalShipmentDate;
    }

    public String getrMATypeCode() {
        return rMATypeCode;
    }

    public void setrMATypeCode(String rMATypeCode) {
        this.rMATypeCode = rMATypeCode;
    }

    public String getRmaTypeGroup() {
        return rmaTypeGroup;
    }

    public void setRmaTypeGroup(String rmaTypeGroup) {
        this.rmaTypeGroup = rmaTypeGroup;
    }

    public String getSerialObjectSerialNumber() {
        return serialObjectSerialNumber;
    }

    public void setSerialObjectSerialNumber(String serialObjectSerialNumber) {
        this.serialObjectSerialNumber = serialObjectSerialNumber;
    }

    public String getServiceMessageId() {
        return serviceMessageId;
    }

    public void setServiceMessageId(String serviceMessageId) {
        this.serviceMessageId = serviceMessageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepairStateCode() {
        return repairStateCode;
    }

    public void setRepairStateCode(String repairStateCode) {
        this.repairStateCode = repairStateCode;
    }

    public String getRepairTaskCode() {
        return repairTaskCode;
    }

    public void setRepairTaskCode(String repairTaskCode) {
        this.repairTaskCode = repairTaskCode;
    }

    public String getRepairTaskGroup() {
        return repairTaskGroup;
    }

    public void setRepairTaskGroup(String repairTaskGroup) {
        this.repairTaskGroup = repairTaskGroup;
    }

    public String getRepairTaskText() {
        return repairTaskText;
    }

    public void setRepairTaskText(String repairTaskText) {
        this.repairTaskText = repairTaskText;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }

    public String getRepairLocationCode() {
        return repairLocationCode;
    }

    public void setRepairLocationCode(String repairLocationCode) {
        this.repairLocationCode = repairLocationCode;
    }

    public String getRepairServiceCode() {
        return repairServiceCode;
    }

    public void setRepairServiceCode(String repairServiceCode) {
        this.repairServiceCode = repairServiceCode;
    }

    public String getDefectComponent() {
        return defectComponent;
    }

    public void setDefectComponent(String defectComponent) {
        this.defectComponent = defectComponent;
    }

    public List<X2MessageMappingType> getX2Messages() {
        return x2Messages;
    }

    public void setX2Messages(List<X2MessageMappingType> x2Messages) {
        this.x2Messages = x2Messages;
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

        ServiceMessageMappingType mappingObject = (ServiceMessageMappingType) obj;

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
