package com.kontron.qdw.boundary.service.mapping.serialobject;

import java.io.Serializable;
import java.util.Date;

import com.kontron.util.text.StringUtil;
import com.sap.conn.jco.JCoTable;

import net.sourceforge.jbizmo.commons.exchange.DataImportException;

/**
 * @author smuehlbauer
 */
public final class SerialObjectRFCMappingType implements Serializable {

    private static final long serialVersionUID = 2296796934637219374L;

    // Name der aufgerufenen SAP-Funktion
    public static final String FUNCTION_NAME = "ZWW_PRODUCT_STRUCTURE";

    // Parameter für die SAP-Abfrage
    public static final String INPUT_VAR_MATERIAL = "IV_MATERIAL";
    public static final String INPUT_VAR_SERIAL = "IV_SERIAL";

    // Ergebnistabelle und dessen Parameter, die durch die Abfrage gesetzt wird
    public static final String RETURN_TABLE = "T_PRODUCT_STRUCTURE";
    public static final String PROP_TOP_MATERIAL = "TOP";
    public static final String PROP_TOP_SERIAL = "TOP_SERIAL";
    public static final String PROP_TOP_REV10 = "TOP_BATCH";
    public static final String PROP_TOP_REV2 = "TOP_REVLV";
    public static final String PROP_TOP_PROD_ORDER_NO = "TOP_AUFNR";
    public static final String PROP_TOP_ASSEMBLY_DATE = "TOP_ASSEMBLY_DAT";
    public static final String PROP_TOP_ALTERNATIVE = "TOP_ALT_BOM";
    public static final String PROP_COMP_MATERIAL = "COMPONENT";
    public static final String PROP_COMP_SERIAL = "COMPONENT_SERNR";
    public static final String PROP_COMP_REV10 = "COMPONENT_BATCH";
    public static final String PROP_COMP_REV2 = "COMPONENT_REVLV";
    public static final String PROP_COMP_ALTERNATIVE = "COMPONENT_ALT_BOM";
    public static final String PROP_INDEX = "INDX";

    private String parentSAPMaterialNumber;
    private String parentSerialNumber;
    private String parentRev10;
    private String parentRev2;
    private String parentAlternative;
    private String productionOrderNumber;
    private String sAPMaterialNumber;
    private String serialNumber;
    private String rev10;
    private String rev2;
    private String alternative = "";
    private Date assemblyDate;
    private int idx;

    /**
     * Prevent instantiation
     */
    private SerialObjectRFCMappingType() {
        // privater ctor; wird nur über #getInstanceBySapTable(JCoTable) instanziiert
    }

    /**
     * Get mapped Object out of return table
     * @param table
     * @return
     * @throws DataImportException
     */
    public static SerialObjectRFCMappingType getInstanceBySapTable(JCoTable table) throws DataImportException {
        SerialObjectRFCMappingType s = new SerialObjectRFCMappingType();

        s.setParentSAPMaterialNumber(StringUtil.removeLeadingZero(table.getString(PROP_TOP_MATERIAL)));
        s.setParentSerialNumber(table.getString(PROP_TOP_SERIAL));
        s.setParentRev10(table.getString(PROP_TOP_REV10));
        s.setParentRev2(table.getString(PROP_TOP_REV2));
        s.setParentAlternative(table.getString(PROP_TOP_ALTERNATIVE));
        s.setProductionOrderNumber(StringUtil.removeLeadingZero(table.getString(PROP_TOP_PROD_ORDER_NO)));
        s.setsAPMaterialNumber(StringUtil.removeLeadingZero(table.getString(PROP_COMP_MATERIAL)));
        s.setSerialNumber(table.getString(PROP_COMP_SERIAL));
        s.setRev10(table.getString(PROP_COMP_REV10));
        s.setRev2(table.getString(PROP_COMP_REV2));
        s.setAlternative(table.getString(PROP_COMP_ALTERNATIVE));
        s.setAssemblyDate(table.getDate(PROP_TOP_ASSEMBLY_DATE));
        s.setIdx(table.getInt(PROP_INDEX));

        try {
            s.setParentSerialNumber(Long.toString(Long.parseLong(s.getParentSerialNumber())));
        }
        catch (Exception e) {
        }

        try {
            s.setSerialNumber(Long.toString(Long.parseLong(s.getSerialNumber())));
        }
        catch (Exception e) {
        }

        if (s.getProductionOrderNumber().isEmpty()) {
            s.setProductionOrderNumber("---");
        }

        return s;
    }



    public String getParentSAPMaterialNumber() {
        return parentSAPMaterialNumber;
    }

    public void setParentSAPMaterialNumber(String parentSAPMaterialNumber) {
        this.parentSAPMaterialNumber = parentSAPMaterialNumber;
    }

    public String getParentSerialNumber() {
        return parentSerialNumber;
    }

    public void setParentSerialNumber(String parentSerialNumber) {
        this.parentSerialNumber = parentSerialNumber;
    }

    public String getParentRev10() {
        return parentRev10;
    }

    public void setParentRev10(String parentRev10) {
        this.parentRev10 = parentRev10;
    }

    public String getParentRev2() {
        return parentRev2;
    }

    public void setParentRev2(String parentRev2) {
        this.parentRev2 = parentRev2;
    }

    public String getParentAlternative() {
        return parentAlternative;
    }

    public void setParentAlternative(String parentAlternative) {
        this.parentAlternative = parentAlternative;
    }

    public String getProductionOrderNumber() {
        return productionOrderNumber;
    }

    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
    }

    public String getsAPMaterialNumber() {
        return sAPMaterialNumber;
    }

    public void setsAPMaterialNumber(String sAPMaterialNumber) {
        this.sAPMaterialNumber = sAPMaterialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRev10() {
        return rev10;
    }

    public void setRev10(String rev10) {
        this.rev10 = rev10;
    }

    public String getRev2() {
        return rev2;
    }

    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public Date getAssemblyDate() {
        return assemblyDate;
    }

    public void setAssemblyDate(Date assemblyDate) {
        this.assemblyDate = assemblyDate;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

}
