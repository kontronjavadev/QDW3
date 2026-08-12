package com.kontron.qdw.boundary.service.mapping.svcmsg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "repairs")
@XmlType(name = "ServiceMessageRootType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceMessageRootMappingType implements Serializable {

    private static final long serialVersionUID = 2917181432647155324L;

    @XmlElement(name = "repair", required = false)
    private List<ServiceMessageMappingType> serviceMessages = new ArrayList<>();


    public ServiceMessageRootMappingType() {
        super();
    }


    public List<ServiceMessageMappingType> getServiceMessages() {
        return this.serviceMessages;
    }

    public void setServiceMessages(List<ServiceMessageMappingType> serviceMessages) {
        this.serviceMessages = serviceMessages;
    }

}
