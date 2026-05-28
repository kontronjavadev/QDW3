package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;

public class ViewServiceOrderDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/ViewServiceOrderDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTCODE = "form:tabview1:txtCode";
    @Generated
    public static final String FIELD_ID_CBOSERVICEORDERTYPE = "form:tabview1:cboServiceOrderType";
    @Generated
    public static final String FIELD_ID_TXTDOCUMENTDATE = "form:tabview1:txtDocumentDate";
    @Generated
    public static final String FIELD_ID_CHKACTIVE = "form:tabview1:chkActive";
    @Generated
    public static final String FIELD_ID_TXTSHORTTEXT = "form:tabview1:txtShortText";
    @Generated
    public static final String FIELD_ID_CBOCUSTOMER = "form:tabview1:cboCustomer";
    @Generated
    public static final String FIELD_ID_CBOSUPPLIER = "form:tabview1:cboSupplier";
    @Generated
    public static final String FIELD_ID_TXTCOMMENT = "form:tabview1:txtComment";
    @Generated
    public static final String FIELD_ID_TXTCREATIONDATE = "form:tabview1:txtCreationDate";
    @Generated
    public static final String FIELD_ID_TXTLASTUPDATE = "form:tabview1:txtLastUpdate";
    @Generated
    public static final String FIELD_ID_TXTVERSION = "form:tabview1:txtVersion";
    @Generated
    public static final String TAB_PAGE_PANADMINISTRATION = "form:tabview1:tabPanAdministration";
    @Generated
    public static final String TAB_PAGE_PANSERVICEORDER = "form:tabview1:tabPanServiceOrder";
    @Generated
    private final ServiceOrderServiceMessagesPanel gridPanelServiceMessages;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public ViewServiceOrderDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelServiceMessages = new ServiceOrderServiceMessagesPanel(testContext, "form:gridServiceOrderServiceMessagesPanel");
    }

    /**
     * @return the grid panel that contains all service messages of this service order
     */
    @Generated
    public ServiceOrderServiceMessagesPanel getGridPanelServiceMessages() {
        return gridPanelServiceMessages;
    }

}