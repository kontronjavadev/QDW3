
package com.kontron.qdw.boundary.service.mapping.rma;

import java.io.Serializable;

import com.kontron.qdw.domain.mv.ServiceOrderType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "RMAType")
@XmlAccessorType(XmlAccessType.FIELD)
public class RMAMappingType implements Serializable {

    private static final long serialVersionUID = 127734612250668477L;

    @XmlAttribute(name = "rma_no", required = true)
    private String code;

    @XmlAttribute(name = "status", required = false)
    private String status;

    @XmlAttribute(name = "note", required = false)
    private String comment;

    @XmlAttribute(name = "quantity", required = false)
    private Integer quantity;

    @XmlTransient
    private Boolean active = true;

    @XmlAttribute(name = "customer_no", required = true)
    private String customerCode;

    @XmlTransient
    private ServiceOrderType type = ServiceOrderType.RMA;

    @XmlAttribute(name = "creation_date", required = true)
    private String documentDate;

    @XmlAttribute(name = "reported_by", required = false)
    private String reportedBy;


    public RMAMappingType() {
        super();
    }


    public RMAMappingType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public ServiceOrderType getType() {
        return this.type;
    }

    public void setType(ServiceOrderType type) {
        this.type = type;
    }

    public String getDocumentDate() {
        return this.documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getReportedBy() {
        return this.reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
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

        RMAMappingType mappingObject = (RMAMappingType) obj;

        if (this.code == null) {
            if (mappingObject.getCode() != null) {
                return false;
            }
        }
        else if (!this.code.equals(mappingObject.getCode())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

}
