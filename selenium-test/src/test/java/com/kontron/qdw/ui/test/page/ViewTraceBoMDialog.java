package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ViewTraceBoMDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/ViewTraceBoMDialog.jsf";
    @Generated
    public static final String FIELD_ID_CBOMATERIALREVISIONMATERIAL = "form:tabview1:cboMaterialRevisionMaterial";
    @Generated
    public static final String FIELD_ID_TXTMATERIALSAPNUMBER = "form:tabview1:txtMaterialSapNumber";
    @Generated
    public static final String FIELD_ID_TXTMATERIALSHORTTEXT = "form:tabview1:txtMaterialShortText";
    @Generated
    public static final String FIELD_ID_CBOMATERIALREVISION = "form:tabview1:cboMaterialRevision";
    @Generated
    public static final String FIELD_ID_CBOSUPPLIER = "form:tabview1:cboSupplier";
    @Generated
    public static final String FIELD_ID_TXTORDERNUMBER = "form:tabview1:txtOrderNumber";
    @Generated
    public static final String FIELD_ID_TXTDELIVERYNOTENUMBER = "form:tabview1:txtDeliveryNoteNumber";
    @Generated
    public static final String FIELD_ID_TXTPRODUCTIONDATE = "form:tabview1:txtProductionDate";
    @Generated
    public static final String FIELD_ID_TXTLOTNUMBER = "form:tabview1:txtLotNumber";
    @Generated
    public static final String FIELD_ID_TXTID = "form:tabview1:txtId";
    @Generated
    public static final String FIELD_ID_TXTVERSION = "form:tabview1:txtVersion";
    @Generated
    public static final String FIELD_ID_TXTCREATIONDATE = "form:tabview1:txtCreationDate";
    @Generated
    public static final String FIELD_ID_TXTLASTUPDATE = "form:tabview1:txtLastUpdate";
    @Generated
    public static final String TAB_PAGE_PANTRACEBOM = "form:tabview1:tabPanTraceBoM";
    @Generated
    public static final String TAB_PAGE_PANADMINISTRATION = "form:tabview1:tabPanAdministration";
    @Generated
    public static final String TAB_PAGE_PANILLEGALTRACEBOMITEMS = "form:tabview2:tabPanIllegalTraceBoMItems";
    @Generated
    public static final String TAB_PAGE_PANTRACEBOMITEMS = "form:tabview2:tabPanTraceBoMItems";
    @Generated
    private final TraceBoMTraceBoMItemsPanel gridPanelTraceBoMItems;
    @Generated
    private final TraceBoMIllegalTraceBoMItemsPanel gridPanelIllegalTraceBoMItems;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public ViewTraceBoMDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelTraceBoMItems = new TraceBoMTraceBoMItemsPanel(testContext, "form:tabview2:gridTraceBoMTraceBoMItemsPanel");
        gridPanelIllegalTraceBoMItems = new TraceBoMIllegalTraceBoMItemsPanel(testContext, "form:tabview2:gridTraceBoMIllegalTraceBoMItemsPanel");
    }

    /**
     * @return the grid panel that contains all trace BoM items of this trace BoM
     */
    @Generated
    public TraceBoMTraceBoMItemsPanel getGridPanelTraceBoMItems() {
        return gridPanelTraceBoMItems;
    }

    /**
     * @return the grid panel that contains all illegal trace BoM items of this trace BoM
     */
    @Generated
    public TraceBoMIllegalTraceBoMItemsPanel getGridPanelIllegalTraceBoMItems() {
        return gridPanelIllegalTraceBoMItems;
    }

}