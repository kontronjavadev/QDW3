package com.kontron.qdw.boundary.service.mapping.serialobject;

import java.io.*;
import java.util.*;

import net.sourceforge.jbizmo.commons.exchange.*;

import com.kontron.util.text.*;
import com.sap.conn.jco.*;

/**
 * @author smuehlbauer
 */
public class SerialObjectRFCMappingType implements Serializable {
    private static final long serialVersionUID = 1L;
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
    public static final String RETURN_TABLE = "T_PRODUCT_STRUCTURE";
    public static final String FUNCTION_NAME = "ZWW_PRODUCT_STRUCTURE";
    public static String INPUT_VAR_MATERIAL = "IV_MATERIAL";
    public static String INPUT_VAR_SERIAL = "IV_SERIAL";

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

    }

    /**
     * Get mapped Object out of return table
     * @param table
     * @return
     * @throws DataImportException
     */
    public static SerialObjectRFCMappingType getObject(JCoTable table) throws DataImportException {
        SerialObjectRFCMappingType s = new SerialObjectRFCMappingType();

        // System.out.println(table);

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

    /**
     * @return the parentSAPMaterialNumber
     */
    public String getParentSAPMaterialNumber() {
        return parentSAPMaterialNumber;
    }

    /**
     * @param parentSAPMaterialNumber the parentSAPMaterialNumber to set
     */
    public void setParentSAPMaterialNumber(String parentSAPMaterialNumber) {
        this.parentSAPMaterialNumber = parentSAPMaterialNumber;
    }

    /**
     * @return the parentSerialNumber
     */
    public String getParentSerialNumber() {
        return parentSerialNumber;
    }

    /**
     * @param parentSerialNumber the parentSerialNumber to set
     */
    public void setParentSerialNumber(String parentSerialNumber) {
        this.parentSerialNumber = parentSerialNumber;
    }

    /**
     * @return the parentRev10
     */
    public String getParentRev10() {
        return parentRev10;
    }

    /**
     * @param parentRev10 the parentRev10 to set
     */
    public void setParentRev10(String parentRev10) {
        this.parentRev10 = parentRev10;
    }

    /**
     * @return the parentRev2
     */
    public String getParentRev2() {
        return parentRev2;
    }

    /**
     * @param parentRev2 the parentRev2 to set
     */
    public void setParentRev2(String parentRev2) {
        this.parentRev2 = parentRev2;
    }

    /**
     * @return the parentAlternative
     */
    public String getParentAlternative() {
        return parentAlternative;
    }

    /**
     * @param parentAlternative the parentAlternative to set
     */
    public void setParentAlternative(String parentAlternative) {
        this.parentAlternative = parentAlternative;
    }

    /**
     * @return the productionOrderNumber
     */
    public String getProductionOrderNumber() {
        return productionOrderNumber;
    }

    /**
     * @param productionOrderNumber the productionOrderNumber to set
     */
    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
    }

    /**
     * @return the sAPMaterialNumber
     */
    public String getsAPMaterialNumber() {
        return sAPMaterialNumber;
    }

    /**
     * @param sAPMaterialNumber the sAPMaterialNumber to set
     */
    public void setsAPMaterialNumber(String sAPMaterialNumber) {
        this.sAPMaterialNumber = sAPMaterialNumber;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the rev10
     */
    public String getRev10() {
        return rev10;
    }

    /**
     * @param rev10 the rev10 to set
     */
    public void setRev10(String rev10) {
        this.rev10 = rev10;
    }

    /**
     * @return the rev2
     */
    public String getRev2() {
        return rev2;
    }

    /**
     * @param rev2 the rev2 to set
     */
    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    /**
     * @return the alternative
     */
    public String getAlternative() {
        return alternative;
    }

    /**
     * @param alternative the alternative to set
     */
    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    /**
     * @return the assemblyDate
     */
    public Date getAssemblyDate() {
        return assemblyDate;
    }

    /**
     * @param assemblyDate the assemblyDate to set
     */
    public void setAssemblyDate(Date assemblyDate) {
        this.assemblyDate = assemblyDate;
    }

    /**
     * @return the idx
     */
    public int getIdx() {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }
}
