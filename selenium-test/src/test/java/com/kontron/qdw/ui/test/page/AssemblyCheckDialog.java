package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class AssemblyCheckDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/AssemblyCheckDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTSERIALNUMBER = "form:txtSerialNumber";
    @Generated
    public static final String FIELD_ID_TXTMATERIALMATERIALNUMBER = "form:txtMaterialMaterialNumber";
    @Generated
    public static final String FIELD_ID_TXTMATERIALREVISIONREVISIONNUMBER = "form:txtMaterialRevisionRevisionNumber";
    @Generated
    public static final String FIELD_ID_TXTTRACEBOMLOTNUMBER = "form:txtTraceBomLotNumber";
    @Generated
    public static final String FIELD_ID_TXTTRACEBOMORDERNUMBER = "form:txtTraceBomOrderNumber";
    @Generated
    private final SerialObjectServiceMessagesPanel gridPanelServiceMessages;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public AssemblyCheckDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelServiceMessages = new SerialObjectServiceMessagesPanel(testContext, "form:gridSerialObjectServiceMessagesPanel");
    }

    /**
     * @return the grid panel that contains all service messages of this serial object
     */
    @Generated
    public SerialObjectServiceMessagesPanel getGridPanelServiceMessages() {
        return gridPanelServiceMessages;
    }

}