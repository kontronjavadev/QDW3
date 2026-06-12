package com.kontron.qdw.boundary.service.mapping.material;

import java.io.Serializable;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "materialRevisionType", namespace = Namespace.APP_URI)
public class MaterialRevisionXMLElement implements Serializable {

    private static final long serialVersionUID = -2305010402370213652L;

    @XmlAttribute(required = true)
    private String revisionNumber;

    @XmlElement(required = false)
    private String comment;

    @XmlAttribute(required = false)
    private String chargeNumber;

    public MaterialRevisionXMLElement() {
    }

    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getChargeNumber() {
        return this.chargeNumber;
    }

    public void setChargeNumber(String chargeNumber) {
        this.chargeNumber = chargeNumber;
    }

}
