package com.kontron.qdw.boundary.service.mapping.tracebomalt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "STOCK_RECEIPT")
@XmlType(name = "StockReceiptRootType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TraceBoMRootMappingType implements Serializable {

    private static final long serialVersionUID = 697358490058356158L;

    @XmlElement(name = "HEADER", required = false)
    private TraceBoMHeaderType header;

    @XmlElementWrapper(name = "ITEMS")
    @XmlElement(name = "SNR", required = false)
    private List<TraceBoMMappingType> serialObjects = new ArrayList<>();


    public TraceBoMRootMappingType() {
    }


    public TraceBoMHeaderType getHeader() {
        return this.header;
    }

    public void setHeader(TraceBoMHeaderType header) {
        this.header = header;
    }

    public List<TraceBoMMappingType> getSerialObjects() {
        return this.serialObjects;
    }

    public void setSerialObjects(List<TraceBoMMappingType> serialObjects) {
        this.serialObjects = serialObjects;
    }

}
