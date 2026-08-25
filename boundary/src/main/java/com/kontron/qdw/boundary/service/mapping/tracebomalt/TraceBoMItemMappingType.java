package com.kontron.qdw.boundary.service.mapping.tracebomalt;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TraceBoMItemType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TraceBoMItemMappingType implements Serializable {

    private static final long serialVersionUID = 2212960387749961506L;

    @XmlAttribute(name = "PART", required = true)
    private String materialSapNumber;

    @XmlAttribute(name = "QTY", required = true)
    private int quantity;

    @XmlAttribute(name = "MANUFACTURER", required = true)
    private String manufacturerName;

    @XmlAttribute(name = "MAN_ORDER_NO", required = true)
    private String orderCode;

    @XmlAttribute(name = "MAN_REVISION", required = true)
    private String manufacturerRevision;

    @XmlAttribute(name = "DATECODE", required = true)
    private String dateCode;

    @XmlAttribute(name = "ADD1", required = true)
    private String infoField1;

    @XmlAttribute(name = "ADD2", required = true)
    private String infoField2;

    @XmlAttribute(name = "ADD3", required = true)
    private String infoField3;

    @XmlAttribute(name = "ADD4", required = true)
    private String infoField4;


    public TraceBoMItemMappingType() {
    }


    public String getMaterialSapNumber() {
        return this.materialSapNumber;
    }

    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getManufacturerRevision() {
        return this.manufacturerRevision;
    }

    public void setManufacturerRevision(String manufacturerRevision) {
        this.manufacturerRevision = manufacturerRevision;
    }

    public String getDateCode() {
        return this.dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getInfoField1() {
        return this.infoField1;
    }

    public void setInfoField1(String infoField1) {
        this.infoField1 = infoField1;
    }

    public String getInfoField2() {
        return this.infoField2;
    }

    public void setInfoField2(String infoField2) {
        this.infoField2 = infoField2;
    }

    public String getInfoField3() {
        return this.infoField3;
    }

    public void setInfoField3(String infoField3) {
        this.infoField3 = infoField3;
    }

    public String getInfoField4() {
        return this.infoField4;
    }

    public void setInfoField4(String infoField4) {
        this.infoField4 = infoField4;
    }

}
