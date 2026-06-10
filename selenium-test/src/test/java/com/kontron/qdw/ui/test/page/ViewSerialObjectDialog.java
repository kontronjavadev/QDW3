package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;

public class ViewSerialObjectDialog extends AbstractPageObject {
    @Generated
    public static final String RESOURCE_PATH = "/dialog/ViewSerialObjectDialog.jsf";
    @Generated
    public static final String FIELD_ID_TXTSERIALNUMBER = "form:tabview1:txtSerialNumber";
    @Generated
    public static final String FIELD_ID_TXTCUSTOMERSERIALNUMBER = "form:tabview1:txtCustomerSerialNumber";
    @Generated
    public static final String FIELD_ID_TXTPRODUCTIONORDERNUMBER = "form:tabview1:txtProductionOrderNumber";
    @Generated
    public static final String FIELD_ID_TXTASSEMBLYDATE = "form:tabview1:txtAssemblyDate";
    @Generated
    public static final String FIELD_ID_CBOPARENTOBJECT = "form:tabview1:cboParentObject";
    @Generated
    public static final String FIELD_ID_CBOTRACEBOM = "form:tabview1:cboTraceBom";
    @Generated
    public static final String FIELD_ID_CBOMATERIAL = "form:tabview1:cboMaterial";
    @Generated
    public static final String FIELD_ID_TXTMATERIALSAPNUMBER = "form:tabview1:txtMaterialSapNumber";
    @Generated
    public static final String FIELD_ID_TXTID = "form:tabview1:txtId";
    @Generated
    public static final String FIELD_ID_TXTVERSION = "form:tabview1:txtVersion";
    @Generated
    public static final String FIELD_ID_TXTCREATIONDATE = "form:tabview1:txtCreationDate";
    @Generated
    public static final String FIELD_ID_TXTLASTUPDATE = "form:tabview1:txtLastUpdate";
    @Generated
    public static final String TAB_PAGE_PANASSEMBLYRECORDS = "form:tabview2:tabPanAssemblyRecords";
    @Generated
    public static final String TAB_PAGE_PANSERIALOBJECTS = "form:tabview2:tabPanSerialObjects";
    @Generated
    public static final String TAB_PAGE_PANADMINISTRATION = "form:tabview1:tabPanAdministration";
    @Generated
    public static final String TAB_PAGE_PANARRIVALS = "form:tabview2:tabPanArrivals";
    @Generated
    public static final String TAB_PAGE_PANSERVICEMESSAGES = "form:tabview2:tabPanServiceMessages";
    @Generated
    public static final String TAB_PAGE_PANSHIPMENTS = "form:tabview2:tabPanShipments";
    @Generated
    public static final String TAB_PAGE_PANSERIALOBJECT = "form:tabview1:tabPanSerialObject";
    @Generated
    private final SerialObjectSerialObjectsPanel gridPanelSerialObjects;
    @Generated
    private final SerialObjectArrivalsPanel gridPanelArrivals;
    @Generated
    private final SerialObjectShipmentsPanel gridPanelShipments;
    @Generated
    private final SerialObjectServiceMessagesPanel gridPanelServiceMessages;
    @Generated
    private final SerialObjectAssemblyRecordsPanel gridPanelAssemblyRecords;

    /**
     * Constructor
     * @param testContext
     */
    @Generated
    public ViewSerialObjectDialog(SeleniumTestContext testContext) {
        super(testContext);

        gridPanelSerialObjects = new SerialObjectSerialObjectsPanel(testContext, "form:tabview2:gridSerialObjectSerialObjectsPanel");
        gridPanelArrivals = new SerialObjectArrivalsPanel(testContext, "form:tabview2:gridSerialObjectArrivalsPanel");
        gridPanelShipments = new SerialObjectShipmentsPanel(testContext, "form:tabview2:gridSerialObjectShipmentsPanel");
        gridPanelServiceMessages = new SerialObjectServiceMessagesPanel(testContext, "form:tabview2:gridSerialObjectServiceMessagesPanel");
        gridPanelAssemblyRecords = new SerialObjectAssemblyRecordsPanel(testContext, "form:tabview2:gridSerialObjectAssemblyRecordsPanel");
    }

    /**
     * @return the grid panel that contains all serial objects of this serial object
     */
    @Generated
    public SerialObjectSerialObjectsPanel getGridPanelSerialObjects() {
        return gridPanelSerialObjects;
    }

    /**
     * @return the grid panel that contains all arrivals of this serial object
     */
    @Generated
    public SerialObjectArrivalsPanel getGridPanelArrivals() {
        return gridPanelArrivals;
    }

    /**
     * @return the grid panel that contains all shipments of this serial object
     */
    @Generated
    public SerialObjectShipmentsPanel getGridPanelShipments() {
        return gridPanelShipments;
    }

    /**
     * @return the grid panel that contains all service messages of this serial object
     */
    @Generated
    public SerialObjectServiceMessagesPanel getGridPanelServiceMessages() {
        return gridPanelServiceMessages;
    }

    /**
     * @return the grid panel that contains all assembly records of this serial object
     */
    @Generated
    public SerialObjectAssemblyRecordsPanel getGridPanelAssemblyRecords() {
        return gridPanelAssemblyRecords;
    }

}