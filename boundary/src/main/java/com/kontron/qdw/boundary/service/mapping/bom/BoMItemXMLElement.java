package com.kontron.qdw.boundary.service.mapping.bom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bomItemType", namespace = Namespace.APP_URI)
public class BoMItemXMLElement implements Serializable {

    private static final long serialVersionUID = 7398273837359792336L;

    @XmlAttribute
    private String consignment;

    @XmlAttribute
    private Double quantity;

    @XmlAttribute
    private String label;

    @XmlAttribute(required = true)
    private String lineNumber;

    @XmlAttribute
    private String itemMaterial;

    @XmlAttribute
    private String altGroup;

    @XmlAttribute
    private Integer usageProb;
    // private String usageProb;

    @XmlAttribute
    private boolean assembly;

    @XmlElementWrapper(name = "items", namespace = Namespace.APP_URI)
    @XmlElement(name = "item", namespace = Namespace.APP_URI)
    private List<BoMItemXMLElement> subItems;

    @XmlElementWrapper(name = "designators", namespace = Namespace.APP_URI)
    @XmlElement(name = "designator", namespace = Namespace.APP_URI)
    private List<DesignatorXMLElement> designators;


    public BoMItemXMLElement() {
        designators = new ArrayList<DesignatorXMLElement>();
    }

    public List<BoMItemXMLElement> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<BoMItemXMLElement> subItems) {
        this.subItems = subItems;
    }

    public String getConsignment() {
        return consignment;
    }

    public void setConsignment(String consignment) {
        this.consignment = consignment;
    }

    public String getAltGroup() {
        return altGroup;
    }

    public void setAltGroup(String altGroup) {
        this.altGroup = altGroup;
    }

    public Integer getUsageProb() {
        return usageProb;
    }

    public void setUsageProb(Integer usageProb) {
        this.usageProb = usageProb;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getItemMaterial() {
        return itemMaterial;
    }

    public void setItemMaterial(String itemMaterial) {
        this.itemMaterial = itemMaterial;
    }

    public List<DesignatorXMLElement> getDesignators() {
        return designators;
    }

    public void setDesignators(List<DesignatorXMLElement> designators) {
        this.designators = designators;
    }

    public boolean isAssembly() {
        return assembly;
    }

    public void setAssembly(boolean assembly) {
        this.assembly = assembly;
    }

}
