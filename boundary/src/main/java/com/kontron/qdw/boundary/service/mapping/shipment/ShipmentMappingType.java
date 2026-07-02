package com.kontron.qdw.boundary.service.mapping.shipment;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "ShipmentType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentMappingType implements Serializable {

    private static final long serialVersionUID = -34943833853225586L;

    @XmlAttribute(name = "contract", required = true)
    private String plantCode;

    @XmlAttribute(name = "customer_id", required = true)
    private String customerCode;

    @XmlAttribute(name = "dated", required = true)
    private String shipmentDate;

    @XmlAttribute(name = "order_no", required = true)
    private String orderNumber;

    @XmlAttribute(name = "revision_id", required = true)
    private String revisionNumber;

    @XmlAttribute(name = "part_no", required = true)
    private String materialSapNumber;

    @XmlAttribute(name = "old_part_no", required = false)
    private String materialNumber;

    @XmlAttribute(name = "serial_no", required = true)
    private String serialNumber;

    @XmlAttribute(name = "transaction_id", required = true)
    private String id;

    @XmlTransient
    private String serialObjectSapNumber;

    @XmlAttribute(name = "movement_type", required = false)
    private String movementTypeCode = "UNDEF";


    public ShipmentMappingType() {
    }

    public ShipmentMappingType(String id) {
        this.id = id;
    }


    public String getPlantCode() {
        return this.plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getShipmentDate() {
        return this.shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getMaterialSapNumber() {
        return this.materialSapNumber;
    }

    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
    }

    public String getMaterialNumber() {
        return this.materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialObjectSapNumber() {
        return this.serialObjectSapNumber;
    }

    public void setSerialObjectSapNumber(String serialObjectSapNumber) {
        this.serialObjectSapNumber = serialObjectSapNumber;
    }

    public String getMovementTypeCode() {
        return this.movementTypeCode;
    }

    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        ShipmentMappingType mappingObject = (ShipmentMappingType) obj;

        if (this.id == null) {
            if (mappingObject.getId() != null) {
                return false;
            }
        }
        else if (!this.id.equals(mappingObject.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
