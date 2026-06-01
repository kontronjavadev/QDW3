package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class EditEWSEntryDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/EditEWSEntryDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTTHRESHOLD = "form:tabview1:txtThreshold";
    @Generated
    public static final String FIELD_ID_CBOMATERIAL = "form:tabview1:cboMaterial_input";
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
    public static final String FIELD_ID_LISTRECEIVERS = "form:listReceivers";
    @Generated
    public static final String TAB_PAGE_PANADMINISTRATION = "form:tabview1:tabPanAdministration";
    @Generated
    public static final String TAB_PAGE_PANEWSENTRY = "form:tabview1:tabPanEWSEntry";
    @Generated
    private final DualDataListComponent listReceivers;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public EditEWSEntryDialog(SeleniumTestContext testContext) {
        super(testContext);

        listReceivers = new DualDataListComponent(this, FIELD_ID_LISTRECEIVERS, true);
    }

    /**
     * @return the user selection list field
     */
    @Generated
    public DualDataListComponent getListReceivers() {
        return listReceivers;
    }

}