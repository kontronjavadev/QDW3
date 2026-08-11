package com.kontron.qdw.boundary.service.mapping.rma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rmas")
@XmlType(name = "RMARootType")
@XmlAccessorType(XmlAccessType.FIELD)
public class RMARootMappingType implements Serializable {

    private static final long serialVersionUID = -3661344895581043129L;

    @XmlElement(name = "rma", required = false)
    private List<RMAMappingType> items = new ArrayList<>();


    public RMARootMappingType() {
        super();
    }


    public List<RMAMappingType> getItems() {
        return this.items;
    }

    public void setItems(List<RMAMappingType> items) {
        this.items = items;
    }

}
