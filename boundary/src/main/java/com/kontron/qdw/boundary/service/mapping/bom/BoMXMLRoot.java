package com.kontron.qdw.boundary.service.mapping.bom;

import java.io.Serializable;
import java.util.ArrayList;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "boms", namespace = Namespace.APP_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bomRootType", namespace = Namespace.APP_URI)
public class BoMXMLRoot implements Serializable {

    private static final long serialVersionUID = 5148934035481849671L;

    @XmlElement(name = "bom", namespace = Namespace.APP_URI)
    private ArrayList<BoMXMLElement> bomList;

    public BoMXMLRoot() {
        super();
        bomList = new ArrayList<BoMXMLElement>();
    }

    public ArrayList<BoMXMLElement> getBoMs() {
        return bomList;
    }

    public void setBoMs(ArrayList<BoMXMLElement> bomList) {
        this.bomList = bomList;
    }

}
