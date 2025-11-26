package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ViewMaterialRevisionDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/ViewMaterialRevisionDialog.jsf";
    @Generated
    public static final String FIELD_ID_CBOMATERIAL = "form:tabview1:cboMaterial";
    @Generated
    public static final String FIELD_ID_TXTREVISIONNUMBER = "form:tabview1:txtRevisionNumber";
    @Generated
    public static final String FIELD_ID_TXTREV2 = "form:tabview1:txtRev2";
    @Generated
    public static final String FIELD_ID_TXTREV6 = "form:tabview1:txtRev6";
    @Generated
    public static final String FIELD_ID_TXTALTERNATIVENUMBER = "form:tabview1:txtAlternativeNumber";
    @Generated
    public static final String FIELD_ID_CBOPLANT = "form:tabview1:cboPlant";
    @Generated
    public static final String FIELD_ID_TXTCOMMENT = "form:tabview1:txtComment";
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
    public static final String TAB_PAGE_PANMATERIALREVISION = "form:tabview1:tabPanMaterialRevision";
    @Generated
    private final MaterialRevisionBoMItemsPanel gridPanelBoMItems;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public ViewMaterialRevisionDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelBoMItems = new MaterialRevisionBoMItemsPanel(testContext, "form:gridMaterialRevisionBoMItemsPanel");
    }

    /**
     * @return the grid panel that contains all bom items of this material revision
     */
    @Generated
    public MaterialRevisionBoMItemsPanel getGridPanelBoMItems() {
        return gridPanelBoMItems;
    }

}