package com.kontron.qdw.boundary.service.mapping.material;

import java.io.Serializable;
import java.util.List;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "materialType", namespace = Namespace.APP_URI)
public class MaterialXMLElement implements Serializable {

    private static final long serialVersionUID = -3979844010829422223L;

    @XmlAttribute(required = true)
    private String materialNumber;

    @XmlAttribute(required = true)
    private String sapNumber;

    @XmlAttribute(required = true)
    private String shortText;

    @XmlElement(required = false)
    private String description;

    @XmlAttribute(name = "labor", required = true)
    private String ownerLocation;

    @XmlAttribute(required = true)
    private String plant;

    @XmlAttribute(required = true)
    private String materialType;

    @XmlAttribute(required = false)
    private String materialClass;

    @XmlAttribute(required = true)
    private String quantityUnit;

    // Fix levels of material hierarchy
    @XmlAttribute(required = false)
    private String level1;

    @XmlAttribute(required = false)
    private String level2;

    @XmlAttribute(required = false)
    private String level3;

    @XmlAttribute(required = false)
    private String level4;

    //
    // QDW hat gegenüber GPE keine AVL / Manufacturer material.
    // List<ManufacturerMaterialXMLElement> fehlt hier also, "avl" im xml wird nicht beachtet.
    //

    // List that holds all new or changed material revisions
    @XmlElementWrapper(name = "revisions")
    @XmlElement(name = "materialRevision", namespace = Namespace.APP_URI)
    private List<MaterialRevisionXMLElement> revisions;

    public MaterialXMLElement() {

    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getSapNumber() {
        return sapNumber;
    }

    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerLocation() {
        return ownerLocation;
    }

    public void setOwnerLocation(String ownerLocation) {
        this.ownerLocation = ownerLocation;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialClass() {
        return materialClass;
    }

    public void setMaterialClass(String materialClass) {
        this.materialClass = materialClass;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    /**
     * @return level 1 of material hierarchy
     */
    public String getLevel1() {
        return level1;
    }

    /**
     * @param level1 level 1 of material hierarchy
     */
    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    /**
     * @return level 2 of material hierarchy
     */
    public String getLevel2() {
        return level2;
    }

    /**
     * @param level2 level 2 of material hierarchy
     */
    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    /**
     * @return level 3 of material hierarchy
     */
    public String getLevel3() {
        return level3;
    }

    /**
     * @param level3 level 3 of material hierarchy
     */
    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    /**
     * @return level 4 of material hierarchy
     */
    public String getLevel4() {
        return level4;
    }

    /**
     * @param level4 level 4 of material hierarchy
     */
    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public List<MaterialRevisionXMLElement> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<MaterialRevisionXMLElement> revisions) {
        this.revisions = revisions;
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
        MaterialXMLElement dto = (MaterialXMLElement) obj;
        if (!getMaterialNumber().equals(dto.getMaterialNumber())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return getMaterialNumber().hashCode();
    }

}
