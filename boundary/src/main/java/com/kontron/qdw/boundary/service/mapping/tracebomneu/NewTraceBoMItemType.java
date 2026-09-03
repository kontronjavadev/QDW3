package com.kontron.qdw.boundary.service.mapping.tracebomneu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kontron.qdw.boundary.service.mapping.tracebom.TraceBoMItemTypeIF;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TraceBoMItemType")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewTraceBoMItemType implements Serializable, TraceBoMItemTypeIF {

    private static final long serialVersionUID = -7457591599770542066L;

    @XmlAttribute(name = "part_no", required = true)
    private String materialSapNumber;

    @XmlAttribute(name = "qty", required = true)
    private int quantity;

    @XmlAttribute(name = "man_name", required = true)
    private String manufacturerName;

    @XmlAttribute(name = "order_code", required = true)
    private String orderCode;

    @XmlAttribute(name = "date_code", required = true)
    private String dateCode;

    @XmlAttribute(name = "add1", required = true)
    private String infoField1;

    @XmlAttribute(name = "add2", required = true)
    private String infoField2;

    @XmlElement(name = "bom_item", required = false)
    private List<NewTraceBoMItemType> traceBoMItems = new ArrayList<>();


    public NewTraceBoMItemType() {
    }


    @Override
    public String getMaterialSapNumber() {
        return materialSapNumber;
    }

    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    @Override
    public String getInfoField1() {
        return infoField1;
    }

    public void setInfoField1(String infoField1) {
        this.infoField1 = infoField1;
    }

    @Override
    public String getInfoField2() {
        return infoField2;
    }

    public void setInfoField2(String infoField2) {
        this.infoField2 = infoField2;
    }

    public List<NewTraceBoMItemType> getTraceBoMItems() {
        return traceBoMItems;
    }

    public void setTraceBoMItems(List<NewTraceBoMItemType> traceBoMItems) {
        this.traceBoMItems = traceBoMItems;
    }

}
