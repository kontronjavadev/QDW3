package com.kontron.qdw.boundary.service.mapping.tracebomneu;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


@XmlType(name = "HeaderType")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewTraceBoMHeaderType implements Serializable {


    private static final long serialVersionUID = -1595828632949684814L;

    @XmlAttribute(name = "prod_date", required = true)
    private String productionDate;

    @XmlAttribute(name = "del_note_no", required = true)
    private String deliveryNoteNumber;

    @XmlAttribute(name = "order_no", required = true)
    private String orderNumber;

    @XmlAttribute(name = "lot_no", required = false)
    private String lotNumber;

    @XmlAttribute(name = "supplier", required = true)
    private String supplierCode;


    public NewTraceBoMHeaderType() {
    }


    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getDeliveryNoteNumber() {
        return deliveryNoteNumber;
    }

    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

}
