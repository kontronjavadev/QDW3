package com.kontron.qdw.dto.serial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectAssemblyCheckDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_MATERIALMATERIALNUMBER = "materialMaterialNumber";
    @Generated
    public static final String ATTR_MATERIALREVISIONREVISIONNUMBER = "materialRevisionRevisionNumber";
    @Generated
    public static final String ATTR_TRACEBOMLOTNUMBER = "traceBomLotNumber";
    @Generated
    public static final String ATTR_TRACEBOMORDERNUMBER = "traceBomOrderNumber";
    @Generated
    private long id;
    @Generated
    private String serialNumber;
    @Generated
    private String materialMaterialNumber;
    @Generated
    private String materialRevisionRevisionNumber;
    @Generated
    private String traceBomLotNumber;
    @Generated
    private String traceBomOrderNumber;

    private List<AssemblyCheckMaterialDTO> onlyInRevBoMList = new ArrayList<AssemblyCheckMaterialDTO>();
    private List<AssemblyCheckMaterialDTO> onlyInTraceBoMList = new ArrayList<AssemblyCheckMaterialDTO>();
    private List<AssemblyCheckMaterialDTO> diffQtyList = new ArrayList<AssemblyCheckMaterialDTO>();



    @Generated
    public SerialObjectAssemblyCheckDTO() {
    }

    @Generated
    public SerialObjectAssemblyCheckDTO(long id) {
        this.id = id;
    }

    @Generated
    public SerialObjectAssemblyCheckDTO(long id, String serialNumber, String materialMaterialNumber, String materialRevisionRevisionNumber,
            String traceBomLotNumber, String traceBomOrderNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.materialMaterialNumber = materialMaterialNumber;
        this.materialRevisionRevisionNumber = materialRevisionRevisionNumber;
        this.traceBomLotNumber = traceBomLotNumber;
        this.traceBomOrderNumber = traceBomOrderNumber;
    }



    @Generated
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

        final var dto = (SerialObjectAssemblyCheckDTO) obj;

        return this.id == dto.getId();
    }

    @Generated
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }



    @Generated
    public long getId() {
        return this.id;
    }

    @Generated
    public void setId(long id) {
        this.id = id;
    }

    @Generated
    public String getSerialNumber() {
        return this.serialNumber;
    }

    @Generated
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Generated
    public String getMaterialMaterialNumber() {
        return this.materialMaterialNumber;
    }

    @Generated
    public void setMaterialMaterialNumber(String materialMaterialNumber) {
        this.materialMaterialNumber = materialMaterialNumber;
    }

    @Generated
    public String getMaterialRevisionRevisionNumber() {
        return this.materialRevisionRevisionNumber;
    }

    @Generated
    public void setMaterialRevisionRevisionNumber(String materialRevisionRevisionNumber) {
        this.materialRevisionRevisionNumber = materialRevisionRevisionNumber;
    }

    @Generated
    public String getTraceBomLotNumber() {
        return this.traceBomLotNumber;
    }

    @Generated
    public void setTraceBomLotNumber(String traceBomLotNumber) {
        this.traceBomLotNumber = traceBomLotNumber;
    }

    @Generated
    public String getTraceBomOrderNumber() {
        return this.traceBomOrderNumber;
    }

    @Generated
    public void setTraceBomOrderNumber(String traceBomOrderNumber) {
        this.traceBomOrderNumber = traceBomOrderNumber;
    }

    public List<AssemblyCheckMaterialDTO> getOnlyInRevBoMList() {
        return onlyInRevBoMList;
    }

    public void setOnlyInRevBoMList(List<AssemblyCheckMaterialDTO> onlyInRevBoMList) {
        this.onlyInRevBoMList = onlyInRevBoMList;
    }

    public List<AssemblyCheckMaterialDTO> getOnlyInTraceBoMList() {
        return onlyInTraceBoMList;
    }

    public void setOnlyInTraceBoMList(List<AssemblyCheckMaterialDTO> onlyInTraceBoMList) {
        this.onlyInTraceBoMList = onlyInTraceBoMList;
    }

    public List<AssemblyCheckMaterialDTO> getDiffQtyList() {
        return diffQtyList;
    }

    public void setDiffQtyList(List<AssemblyCheckMaterialDTO> diffQtyList) {
        this.diffQtyList = diffQtyList;
    }

}
