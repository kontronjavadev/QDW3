package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;

public class EditMaterialDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/EditMaterialDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTMATERIALNUMBER = "form:tabview1:txtMaterialNumber";
    @Generated
    public static final String FIELD_ID_TXTSAPNUMBER = "form:tabview1:txtSapNumber";
    @Generated
    public static final String FIELD_ID_CBOMATERIALCLASS = "form:tabview1:cboMaterialClass";
    @Generated
    public static final String FIELD_ID_CBOMATERIALTYPE = "form:tabview1:cboMaterialType";
    @Generated
    public static final String FIELD_ID_CBOOWNERLOCATION = "form:tabview1:cboOwnerLocation";
    @Generated
    public static final String FIELD_ID_CHKSEARCHSUBASSEMBLIES = "form:tabview1:chkSearchSubAssemblies";
    @Generated
    public static final String FIELD_ID_TXTMATERIALHIERARCHY = "form:tabview1:txtMaterialHierarchy";
    @Generated
    public static final String FIELD_ID_TXTFITVALUE = "form:tabview1:txtFitValue";
    @Generated
    public static final String FIELD_ID_TXTSHORTTEXT = "form:tabview1:txtShortText";
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
    public static final String TAB_PAGE_PANMATERIAL = "form:tabview1:tabPanMaterial";
    @Generated
    public static final String TAB_PAGE_PANADMINISTRATION = "form:tabview1:tabPanAdministration";
    @Generated
    private final MaterialRevisionsPanel gridPanelRevisions;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public EditMaterialDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelRevisions = new MaterialRevisionsPanel(testContext, "form:gridMaterialRevisionsPanel");
    }

    /**
     * @return the grid panel that contains all material revisions of this material
     */
    @Generated
    public MaterialRevisionsPanel getGridPanelRevisions() {
        return gridPanelRevisions;
    }

}