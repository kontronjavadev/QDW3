package com.kontron.qdw.dto.serial;

import java.io.*;

public class AssemblyCheckMaterialDTO implements Serializable {

    private static final long serialVersionUID = 8873959876484868610L;

    private long materialId;
    private String materialNumber;
    private int bomQuantity;
    private int traceBoMQuantity;
    private String shortText;
    private String materialHierarchy;
    private String materialType;



    public AssemblyCheckMaterialDTO(long materialId, String materialNumber, int bomQuantity, int traceBoMQuantity, String shortText,
            String materialHierarchy, String materialType) {
        super();
        this.materialId = materialId;
        this.materialNumber = materialNumber;
        this.bomQuantity = bomQuantity;
        this.traceBoMQuantity = traceBoMQuantity;
        this.shortText = shortText;
        this.materialHierarchy = materialHierarchy;
        this.materialType = materialType;
    }



    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public int getBomQuantity() {
        return bomQuantity;
    }

    public void setBomQuantity(int bomQuantity) {
        this.bomQuantity = bomQuantity;
    }

    public int getTraceBoMQuantity() {
        return traceBoMQuantity;
    }

    public void setTraceBoMQuantity(int traceBoMQuantity) {
        this.traceBoMQuantity = traceBoMQuantity;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getMaterialHierarchy() {
        return materialHierarchy;
    }

    public void setMaterialHierarchy(String materialHierarchy) {
        this.materialHierarchy = materialHierarchy;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

}
