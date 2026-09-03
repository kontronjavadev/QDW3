package com.kontron.qdw.boundary.service.mapping.tracebom.neu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "trace_boms")
@XmlType(name = "TraceBoMRootType")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewTraceBoMRootType implements Serializable {

    private static final long serialVersionUID = -3596501849546568987L;

    @XmlElement(name = "header", required = true)
    private NewTraceBoMHeaderType header;

    @XmlElement(name = "trace_bom", required = false)
    private List<NewTraceBoMType> serialObjects = new ArrayList<>();


    public NewTraceBoMRootType() {
    }


    public NewTraceBoMHeaderType getHeader() {
        return header;
    }

    public void setHeader(NewTraceBoMHeaderType header) {
        this.header = header;
    }

    public List<NewTraceBoMType> getSerialObjects() {
        return serialObjects;
    }

    public void setSerialObjects(List<NewTraceBoMType> serialObjects) {
        this.serialObjects = serialObjects;
    }

}
