package com.kontron.qdw.boundary.service.mapping.arrival;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "ArrivalType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArrivalMappingType implements Serializable {

    private static final long serialVersionUID = -979626436640894816L;

    @XmlAttribute(name = "authorize_code", required = false)
    private String authorizeCode;

    @XmlAttribute(name = "contract", required = true)
    private String plantCode;

    @XmlAttribute(name = "dated", required = true)
    private String arrivalDate;

    @XmlAttribute(name = "location_no", required = false)
    private String location;

    @XmlAttribute(name = "old_part_no", required = false)
    private String oldMaterialNumber;

    @XmlAttribute(name = "part_no", required = true)
    private String materialSapNumber;

    @XmlAttribute(name = "revision_id", required = true)
    private String revisionNumber;

    @XmlAttribute(name = "movement_type", required = false)
    private String movementTypeCode = "UNDEF";

    @XmlAttribute(name = "supplier_id", required = true)
    private String supplierCode;

    @XmlAttribute(name = "serial_no", required = true)
    private String serialNumber;

    @XmlAttribute(name = "transaction_id", required = true)
    private String id;

    @XmlTransient
    private String serialObjectMaterialNumber;

    @XmlAttribute(name = "order_no", required = true)
    private String orderNumber;


    public ArrivalMappingType() {
    }


    public ArrivalMappingType(String id) {
        this.id = id;
    }

    public String getAuthorizeCode() {
        return this.authorizeCode;
    }

    public void setAuthorizeCode(String authorizeCode) {
        this.authorizeCode = authorizeCode;
    }

    public String getPlantCode() {
        return this.plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getArrivalDate() {
        return this.arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOldMaterialNumber() {
        return this.oldMaterialNumber;
    }

    public void setOldMaterialNumber(String oldMaterialNumber) {
        this.oldMaterialNumber = oldMaterialNumber;
    }

    public String getMaterialSapNumber() {
        return this.materialSapNumber;
    }

    public void setMaterialSapNumber(String materialSapNumber) {
        this.materialSapNumber = materialSapNumber;
    }

    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getMovementTypeCode() {
        return this.movementTypeCode;
    }

    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
    }

    public String getSupplierCode() {
        return this.supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getSerialObjectMaterialNumber() {
        return this.serialObjectMaterialNumber;
    }

    public void setSerialObjectMaterialNumber(String serialObjectMaterialNumber) {
        this.serialObjectMaterialNumber = serialObjectMaterialNumber;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

        ArrivalMappingType mappingObject = (ArrivalMappingType) obj;

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
