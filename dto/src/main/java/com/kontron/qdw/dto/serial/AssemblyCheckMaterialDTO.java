package com.kontron.qdw.dto.serial;

import java.io.*;

public class AssemblyCheckMaterialDTO implements Serializable {

    private static final long serialVersionUID = 8873959876484868610L;

    private long materialId;
    private String materialNumber;
    private String shortText;
    private String materialHierarchy;
    private String materialType;

    private int revBomQuantity;
    private int traceBoMQuantity;
    private long revBomItemId;
    private long traceBomItemId;



    public AssemblyCheckMaterialDTO(long materialId, String materialNumber,
            String shortText, String materialHierarchy, String materialType,
            int revBomQuantity, int traceBoMQuantity, long revBomItemId, long traceBomItemId) {
        super();
        this.materialId = materialId;
        this.materialNumber = materialNumber;
        this.shortText = shortText;
        this.materialHierarchy = materialHierarchy;
        this.materialType = materialType;

        this.revBomQuantity = revBomQuantity;
        this.traceBoMQuantity = traceBoMQuantity;
        this.revBomItemId = revBomItemId;
        this.traceBomItemId = traceBomItemId;
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

    public int getRevBomQuantity() {
        return revBomQuantity;
    }

    public void setRevBomQuantity(int bomQuantity) {
        this.revBomQuantity = bomQuantity;
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

    public long getRevBomItemId() {
        return revBomItemId;
    }

    public void setRevBomItemId(long revBomItemId) {
        this.revBomItemId = revBomItemId;
    }

    public long getTraceBomItemId() {
        return traceBomItemId;
    }

    public void setTraceBomItemId(long traceBomItemId) {
        this.traceBomItemId = traceBomItemId;
    }

}
