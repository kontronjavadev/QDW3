package com.kontron.qdw.boundary.service.mapping.tracebomneu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kontron.qdw.boundary.service.mapping.tracebom.TraceBoMTypeIF;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TraceBoMType")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewTraceBoMType implements Serializable, TraceBoMTypeIF<NewTraceBoMItemType> {

    private static final long serialVersionUID = -50340228580041376L;

    @XmlAttribute(name = "snr", required = true)
    private String serialNumber;

    @XmlAttribute(name = "cust_snr", required = false)
    private String customerSerialNumber;

    @XmlAttribute(name = "material_no", required = true)
    private String materialNumber;

    @XmlAttribute(name = "revision_no", required = true)
    private String revisionNumber;

    @XmlElement(name = "trace_bom", required = false)
    private List<NewTraceBoMType> traceBoms = new ArrayList<>();

    @XmlElement(name = "bom_item", required = false)
    private List<NewTraceBoMItemType> traceBoMItems = new ArrayList<>();


    public NewTraceBoMType() {
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCustomerSerialNumber() {
        return customerSerialNumber;
    }

    public void setCustomerSerialNumber(String customerSerialNumber) {
        this.customerSerialNumber = customerSerialNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public List<NewTraceBoMType> getTraceBoms() {
        return this.traceBoms;
    }

    public void setTraceBoms(List<NewTraceBoMType> traceBoms) {
        this.traceBoms = traceBoms;
    }

    @Override
    public List<NewTraceBoMItemType> getTraceBoMItems() {
        return traceBoMItems;
    }

    public void setTraceBoMItems(List<NewTraceBoMItemType> traceBoMItems) {
        this.traceBoMItems = traceBoMItems;
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NewTraceBoMType)) {
            return false;
        }

        NewTraceBoMType bom = (NewTraceBoMType) obj;

        boolean exists = false;

        // First iterate over BoM that should be compared
        for (NewTraceBoMItemType i : bom.getTraceBoMItems()) {
            for (NewTraceBoMItemType j : getTraceBoMItems()) {
                if (i.getMaterialSapNumber().equals(j.getMaterialSapNumber()) && i.getManufacturerName().equals(j.getManufacturerName())
                        && i.getOrderCode().equals(j.getOrderCode()) && i.getDateCode().equals(j.getDateCode())) {
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
        for (NewTraceBoMItemType i : getTraceBoMItems()) {
            for (NewTraceBoMItemType j : bom.getTraceBoMItems()) {
                if (i.getMaterialSapNumber().equals(j.getMaterialSapNumber()) && i.getManufacturerName().equals(j.getManufacturerName())
                        && i.getOrderCode().equals(j.getOrderCode()) && i.getDateCode().equals(j.getDateCode())) {
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
