package com.kontron.qdw.boundary.service.mapping.bom;

import java.io.Serializable;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "designatorType", namespace = Namespace.APP_URI)
public class DesignatorXMLElement implements Serializable {

    private static final long serialVersionUID = -6194635363119603867L;

    @XmlAttribute(required = true, name = "name")
    private String designator;

    @XmlAttribute(required = true)
    private boolean top;

    @XmlAttribute(required = false)
    private String footprint;

    @XmlAttribute(required = false)
    private String prodHints;

    public DesignatorXMLElement() {
    }

    public String getProdHints() {
        return prodHints;
    }

    public void setProdHints(String prodHints) {
        this.prodHints = prodHints;
    }

    public String getDesignator() {
        return this.designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    public boolean isTop() {
        return this.top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getFootprint() {
        return this.footprint;
    }

    public void setFootprint(String footprint) {
        this.footprint = footprint;
    }

}
