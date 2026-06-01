package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class CreateNewEWSEntryDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/CreateNewEWSEntryDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTTHRESHOLD = "form:txtThreshold";
    @Generated
    public static final String FIELD_ID_CBOMATERIAL = "form:cboMaterial_input";
    @Generated
    public static final String FIELD_ID_CBOTYPE = "form:cboType";
    @Generated
    public static final String FIELD_ID_CHKBOARDORSYSTEM = "form:chkBoardOrSystem";
    @Generated
    public static final String FIELD_ID_TXTFILTERCRITERION = "form:txtFilterCriterion";
    @Generated
    public static final String FIELD_ID_LISTRECEIVERS = "form:listReceivers";
    @Generated
    private final DualDataListComponent listReceivers;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public CreateNewEWSEntryDialog(SeleniumTestContext testContext) {
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