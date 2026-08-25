package com.kontron.qdw.boundary.service.mapping.tracebomalt;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TraceBoMHeaderType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TraceBoMHeaderType implements Serializable {

    private static final long serialVersionUID = 8404741216126093575L;

    @XmlElement(name = "CREATION_DATE", required = true)
    private String productionDate;

    @XmlElement(name = "DELIVERY_NOTE_NO", required = true)
    private String deliveryNoteNumber;

    @XmlElement(name = "ORDER_NO", required = true)
    private String orderNumber;

    @XmlElement(name = "LOT_NO", required = false)
    private String lotNumber;

    @XmlElement(name = "SUPPLIER_ID", required = true)
    private String supplierCode;

    @XmlElement(name = "ARTICLE", required = true)
    private TraceBoMRevisionMappingType materialRevision;


    public TraceBoMHeaderType() {
    }


    public String getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getDeliveryNoteNumber() {
        return this.deliveryNoteNumber;
    }

    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLotNumber() {
        return this.lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getSupplierCode() {
        return this.supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public TraceBoMRevisionMappingType getMaterialRevision() {
        return this.materialRevision;
    }

    public void setMaterialRevision(TraceBoMRevisionMappingType materialRevision) {
        this.materialRevision = materialRevision;
    }

}
