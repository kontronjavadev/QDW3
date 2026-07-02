package com.kontron.qdw.boundary.service.mapping.shipment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "shipments")
@XmlType(name = "ShipmentRootType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentRootMappingType implements Serializable {

    private static final long serialVersionUID = 6521934190333657070L;

    @XmlElement(name = "shipment", required = false)
    private List<ShipmentMappingType> shipments = new ArrayList<>();


    public ShipmentRootMappingType() {
    }


    public List<ShipmentMappingType> getShipments() {
        return this.shipments;
    }

    public void setShipments(List<ShipmentMappingType> shipments) {
        this.shipments = shipments;
    }

}
