package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;

public class ViewEWSEntryDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/ViewEWSEntryDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTTHRESHOLD = "form:tabview1:txtThreshold";
    @Generated
    public static final String FIELD_ID_CBOMATERIAL = "form:tabview1:cboMaterial";
    @Generated
    public static final String FIELD_ID_CBOTYPE = "form:tabview1:cboType";
    @Generated
    public static final String FIELD_ID_CHKBOARDORSYSTEM = "form:tabview1:chkBoardOrSystem";
    @Generated
    public static final String FIELD_ID_TXTFILTERCRITERION = "form:tabview1:txtFilterCriterion";
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
    public static final String TAB_PAGE_PANEWSENTRY = "form:tabview1:tabPanEWSEntry";
    @Generated
    private final EWSEntryReceiversPanel gridPanelReceivers;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public ViewEWSEntryDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelReceivers = new EWSEntryReceiversPanel(testContext, "form:gridEWSEntryReceiversPanel");
    }

    /**
     * @return the grid panel that contains all users of this EWS entry
     */
    @Generated
    public EWSEntryReceiversPanel getGridPanelReceivers() {
        return gridPanelReceivers;
    }

}