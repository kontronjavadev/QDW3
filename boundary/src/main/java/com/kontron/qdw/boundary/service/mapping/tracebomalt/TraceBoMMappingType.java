package com.kontron.qdw.boundary.service.mapping.tracebomalt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TraceBoMMappingType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TraceBoMMappingType implements Serializable {

    private static final long serialVersionUID = -8940036294446795405L;

    @XmlAttribute(name = "SERIAL_NO", required = true)
    private String serialNumber;

    @XmlAttribute(name = "CUST_SERIAL_NO", required = false)
    private String customerSerialNumber;

    @XmlElementWrapper(name = "CONCRETE_BOM")
    @XmlElement(name = "BOM_ITEM", required = false)
    private List<TraceBoMItemMappingType> traceBoMItems = new ArrayList<>();


    public TraceBoMMappingType() {
    }


    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCustomerSerialNumber() {
        return this.customerSerialNumber;
    }

    public void setCustomerSerialNumber(String customerSerialNumber) {
        this.customerSerialNumber = customerSerialNumber;
    }

    public List<TraceBoMItemMappingType> getTraceBoMItems() {
        return this.traceBoMItems;
    }

    public void setTraceBoMItems(List<TraceBoMItemMappingType> traceBoMItems) {
        this.traceBoMItems = traceBoMItems;
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TraceBoMMappingType)) {
            return false;
        }

        TraceBoMMappingType bom = (TraceBoMMappingType) obj;

        boolean exists = false;

        // First iterate over BoM that should be compared
        for (TraceBoMItemMappingType i : bom.getTraceBoMItems()) {
            for (TraceBoMItemMappingType j : getTraceBoMItems()) {
                if (i.getMaterialSapNumber().equals(j.getMaterialSapNumber()) &&
                        i.getManufacturerName().equals(j.getManufacturerName()) &&
                        i.getOrderCode().equals(j.getOrderCode()) &&
                        i.getDateCode().equals(j.getDateCode())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                return false;
            }

            exists = false;
        }

        // Now iterate over this BoM in order to make a full test concerning all items!
        for (TraceBoMItemMappingType i : getTraceBoMItems()) {
            for (TraceBoMItemMappingType j : bom.getTraceBoMItems()) {
                if (i.getMaterialSapNumber().equals(j.getMaterialSapNumber()) &&
                        i.getManufacturerName().equals(j.getManufacturerName()) &&
                        i.getOrderCode().equals(j.getOrderCode()) &&
                        i.getDateCode().equals(j.getDateCode())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                return false;
            }

            exists = false;
        }

        return true;
    }

}
