package com.kontron.qdw.boundary.service.mapping.material;

import java.io.Serializable;
import java.util.ArrayList;

import com.kontron.qdw.boundary.service.mapping.Namespace;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "materials", namespace = Namespace.APP_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "materialRootType", namespace = Namespace.APP_URI)
public class MaterialXMLRoot implements Serializable {

    private static final long serialVersionUID = -2709046329169309731L;

    @XmlElement(name = "material", namespace = Namespace.APP_URI)
    private ArrayList<MaterialXMLElement> materialList;

    public MaterialXMLRoot() {
        super();
        materialList = new ArrayList<MaterialXMLElement>();
    }

    public ArrayList<MaterialXMLElement> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(ArrayList<MaterialXMLElement> materialList) {
        this.materialList = materialList;
    }

}
