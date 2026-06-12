package com.kontron.qdw.boundary.service.mapping.supplier;

import java.io.Serializable;
import java.util.ArrayList;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "suppliers", namespace = Namespace.APP_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplierRootType", namespace = Namespace.APP_URI)
public class SupplierXMLRoot implements Serializable {

    private static final long serialVersionUID = -6581759560382411586L;

    @XmlElement(name = "supplier", namespace = Namespace.APP_URI)
    private ArrayList<SupplierXMLElement> supplierList;

    public SupplierXMLRoot() {
        super();
        supplierList = new ArrayList<SupplierXMLElement>();
    }

    public ArrayList<SupplierXMLElement> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(ArrayList<SupplierXMLElement> supplierList) {
        this.supplierList = supplierList;
    }

}
