package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class EditUserDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/EditUserDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTNAME = "form:tabview1:txtName";
    @Generated
    public static final String FIELD_ID_CHKACTIVE = "form:tabview1:chkActive";
    @Generated
    public static final String FIELD_ID_TXTEMAIL = "form:tabview1:txtEmail";
    @Generated
    public static final String FIELD_ID_TXTID = "form:tabview1:txtId";
    @Generated
    public static final String FIELD_ID_TXTVERSION = "form:tabview1:txtVersion";
    @Generated
    public static final String FIELD_ID_TXTCREATIONDATE = "form:tabview1:txtCreationDate";
    @Generated
    public static final String FIELD_ID_TXTLASTUPDATE = "form:tabview1:txtLastUpdate";
    @Generated
    public static final String FIELD_ID_LISTROLES = "form:listRoles";
    @Generated
    public static final String TAB_PAGE_PANADMINISTRATION = "form:tabview1:tabPanAdministration";
    @Generated
    public static final String TAB_PAGE_PANUSER = "form:tabview1:tabPanUser";
    @Generated
    private final DualDataListComponent listRoles;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public EditUserDialog(SeleniumTestContext testContext) {
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