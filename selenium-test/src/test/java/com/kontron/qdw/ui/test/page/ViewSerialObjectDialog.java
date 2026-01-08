package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ViewSerialObjectDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/ViewSerialObjectDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTSERIALNUMBER = "form:tabview1:txtSerialNumber";
    @Generated
    public static final String FIELD_ID_TXTCUSTOMERSERIALNUMBER = "form:tabview1:txtCustomerSerialNumber";
    @Generated
    public static final String FIELD_ID_TXTPRODUCTIONORDERNUMBER = "form:tabview1:txtProductionOrderNumber";
    @Generated
    public static final String FIELD_ID_TXTASSEMBLYDATE = "form:tabview1:txtAssemblyDate";
    @Generated
    public static final String FIELD_ID_TXTPARENTOBJECTSERIALNUMBER = "form:tabview1:txtParentObjectSerialNumber";
    @Generated
    public static final String FIELD_ID_CBOPARENTOBJECT = "form:tabview1:cboParentObject";
    @Generated
    public static final String FIELD_ID_CBOTRACEBOM = "form:tabview1:cboTraceBom";
    @Generated
    public static final String FIELD_ID_CBOMATERIAL = "form:tabview1:cboMaterial";
    @Generated
    public static final String FIELD_ID_TXTMATERIALMATERIALNUMBER = "form:tabview1:txtMaterialMaterialNumber";
    @Generated
    public static final String FIELD_ID_TXTMATERIALSAPNUMBER = "form:tabview1:txtMaterialSapNumber";
    @Generated
    public static final String FIELD_ID_TXTID = "form:tabview1:txtId";
    @Generated
    public static final String FIELD_ID_TXTVERSION = "form:tabview1:txtVersion";
    @Generated
    public static final String FIELD_ID_TXTCREATIONDATE = "form:tabview1:txtCreationDate";
    @Generated
    public static final String FIELD_ID_TXTLASTUPDATE = "form:tabview1:txtLastUpdate";
    @Generated
    public static final String TAB_PAGE_PANADMINISTRATION = "form:tabview1:tabPanAdministration";
    @Generated
    public static final String TAB_PAGE_PANSERIALOBJECT = "form:tabview1:tabPanSerialObject";
    @Generated
    private final SerialObjectSerialObjectsPanel gridPanelSerialObjects;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public ViewSerialObjectDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelSerialObjects = new SerialObjectSerialObjectsPanel(testContext, "form:gridSerialObjectSerialObjectsPanel");
    }

    /**
     * @return the grid panel that contains all serial objects of this serial object
     */
    @Generated
    public SerialObjectSerialObjectsPanel getGridPanelSerialObjects() {
        return gridPanelSerialObjects;
    }

}