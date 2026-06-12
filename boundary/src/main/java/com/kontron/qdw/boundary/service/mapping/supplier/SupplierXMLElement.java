package com.kontron.qdw.boundary.service.mapping.supplier;

import java.io.Serializable;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplierType", namespace = Namespace.APP_URI)
public class SupplierXMLElement implements Serializable {

    private static final long serialVersionUID = 4379099472021750552L;

    @XmlAttribute(required = true)
    private String code;

    @XmlAttribute(required = true)
    private String name;

    @XmlAttribute
    private String zip;

    @XmlAttribute
    private String city;

    @XmlAttribute
    private String street;

    @XmlAttribute
    private String countryCode;

    public SupplierXMLElement() {
    }

    public SupplierXMLElement(String code) {
        this.code = code;
    }

    public SupplierXMLElement(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the ISO country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode ISO country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

        SupplierXMLElement dto = (SupplierXMLElement) obj;

        if (this.code == null) {
            if (dto.getCode() != null) {
                return false;
            }
        }
        else if (!this.code.equals(dto.getCode())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

}
