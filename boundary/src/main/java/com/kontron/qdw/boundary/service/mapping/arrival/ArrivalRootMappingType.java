package com.kontron.qdw.boundary.service.mapping.arrival;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "arrivals")
@XmlType(name = "ArrivalRootType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArrivalRootMappingType implements Serializable {

    private static final long serialVersionUID = -8244415358786942437L;

    @XmlElement(name = "arrival", required = false)
    private List<ArrivalMappingType> arrivals = new ArrayList<>();


    public ArrivalRootMappingType() {
    }


    public List<ArrivalMappingType> getArrivals() {
        return this.arrivals;
    }

    public void setArrivals(List<ArrivalMappingType> arrivals) {
        this.arrivals = arrivals;
    }

}
