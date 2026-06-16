package com.kontron.qdw.boundary.service.mapping.bom;

import java.io.Serializable;
import java.util.ArrayList;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bomType", namespace = Namespace.APP_URI)
public class BoMXMLElement implements Serializable {

    private static final long serialVersionUID = -5631998593900141362L;

    @XmlAttribute(required = true)
    private String materialNumber;

    @XmlAttribute(required = true)
    private String plant;

    @XmlAttribute(required = true)
    private String alt;

    @XmlAttribute(required = true)
    private String rev2;

    @XmlAttribute(required = true)
    private String rev6;

    @XmlElementWrapper(name = "items", namespace = Namespace.APP_URI)
    @XmlElement(name = "item", namespace = Namespace.APP_URI)
    private ArrayList<BoMItemXMLElement> items;

    public BoMXMLElement() {
        super();
        items = new ArrayList<BoMItemXMLElement>();
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getRev2() {
        return rev2;
    }

    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    public String getRev6() {
        return rev6;
    }

    public void setRev6(String rev6) {
        this.rev6 = rev6;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public ArrayList<BoMItemXMLElement> getItems() {
        return items;
    }

    public void setItems(ArrayList<BoMItemXMLElement> items) {
        this.items = items;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

}
