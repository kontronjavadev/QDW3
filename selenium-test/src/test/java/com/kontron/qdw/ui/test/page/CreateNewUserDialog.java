package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;

public class CreateNewUserDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/CreateNewUserDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTNAME = "form:txtName";
    @Generated
    public static final String FIELD_ID_CHKACTIVE = "form:chkActive";
    @Generated
    public static final String FIELD_ID_TXTEMAIL = "form:txtEmail";
    @Generated
    public static final String FIELD_ID_LISTROLES = "form:listRoles";
    @Generated
    private final DualDataListComponent listRoles;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public CreateNewUserDialog(SeleniumTestContext testContext) {
        super(testContext);

        listRoles = new DualDataListComponent(this, FIELD_ID_LISTROLES, false);
    }

    /**
     * @return the role selection list field
     */
    @Generated
    public DualDataListComponent getListRoles() {
        return listRoles;
    }

}