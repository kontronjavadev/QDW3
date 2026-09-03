package com.kontron.qdw.boundary.service.mapping.tracebom.alt;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlType(name = "MaterialRevisionType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TraceBoMRevisionMappingType implements Serializable {

    private static final long serialVersionUID = 6590657517194339549L;

    @XmlAttribute(name = "REVISION", required = true)
    private String revisionNumber;

    @XmlValue
    private String materialNumber;


    public TraceBoMRevisionMappingType() {
    }


    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getMaterialNumber() {
        return this.materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

}
