package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.*;
import static com.kontron.qdw.ui.UserSession.*;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.CustomerBoundaryService;
import com.kontron.qdw.boundary.base.MovementTypeBoundaryService;
import com.kontron.qdw.boundary.material.MaterialBoundaryService;
import com.kontron.qdw.boundary.material.MaterialTypeBoundaryService;
import com.kontron.qdw.boundary.serial.ShipmentBoundaryService;
import com.kontron.qdw.dto.serial.ShipmentSearchDTO;
import com.kontron.qdw.service.SavedQueryService;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.view.util.OnCompleteHelper;
import com.kontron.qdw.ui.view.util.SuperView;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import net.sourceforge.jbizmo.commons.search.dto.SearchFieldDTO;
import net.sourceforge.jbizmo.commons.search.dto.SearchFieldDataTypeEnum;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.JSFSearchFieldDTO;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.SearchInputFieldValidationException;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("shipmentView")
@ViewScoped
public class ShipmentView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.ShipmentView";
    @Generated
    public static final String PAGE_URL = "/view/ShipmentView.jsf?faces-redirect=true";
    @Generated
    private static final long serialVersionUID = 1L;

    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;

    @Generated
    private final transient ShipmentBoundaryService shipmentService;
    @Generated
    private final transient MaterialBoundaryService materialService;
    private final transient CustomerBoundaryService customerService;
    private final transient MaterialTypeBoundaryService matTypeService;
    private final transient MovementTypeBoundaryService mvtTypeService;
    @Generated
    private final transient SavedQueryService queryManager;


    @Generated
    private String savedQueryName;
    @Generated
    private String selectedSavedQuery;
    @Generated
    private String formTitle = "";

    @Generated
    private List<ShipmentSearchDTO> shipmentsList = new ArrayList<>();
    @Generated
    private ShipmentSearchDTO selectedObject;
    @Generated
    private long countResult;



    /**
     * Default constructor
     */
    @Generated
    public ShipmentView() {
        userSession = null;
        shipmentService = null;
        materialService = null;
        queryManager = null;
        customerService = null;
        matTypeService = null;
        mvtTypeService = null;

    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param shipmentService
     * @param materialService
     * @param queryManager
     */
    @Inject
    @Generated
    public ShipmentView(UserSession userSession, ShipmentBoundaryService shipmentService, MaterialBoundaryService materialService,
            CustomerBoundaryService customerService, MaterialTypeBoundaryService matTypeService, MovementTypeBoundaryService mvtTypeService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.shipmentService = shipmentService;
        this.materialService = materialService;
        this.queryManager = queryManager;
        this.customerService = customerService;
        this.matTypeService = matTypeService;
        this.mvtTypeService = mvtTypeService;
    }



    /**
     * Initialize view
     */
    @Customized
    public void initView() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return;
        }


        formTitle = bundle.getString(FORM_SHIPMENTVIEW_TITLE);

        if (searchObj == null) {
            // Check if previous search exists!
            final SearchDTO lastSearch = queryManager.getLastQuery(userSession.getPrincipal().getId(), VIEW_ID);
            if (lastSearch != null) {
                searchObj = lastSearch;
                prepareAfterLoad();
            }
            else {
                initSearchObject();
            }
        }

        initProperties();
        fetchShipments();

        logger.debug("View initialization finished");
    }

    /**
     * Initialize search object
     */
    @Customized
    public void initSearchObject() {
        searchObj = new SearchDTO();
        int colOrderId = -1;

        // Initialize search object
        searchObj.setMaxResult(1000);
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setCount(false);

        refreshFormatSettings();

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_ID,
                bundle.getString(COL_SHIPMENTVIEW_ID), SearchFieldDataTypeEnum.LONG, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_PLANTCODE,
                bundle.getString(COL_SHIPMENTVIEW_PLANTCODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_ORDERNUMBER,
                bundle.getString(COL_SHIPMENTVIEW_ORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MOVEMENTTYPECODE,
                bundle.getString(COL_SHIPMENTVIEW_MOVEMENTTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_SHIPMENTDATE,
                bundle.getString(COL_SHIPMENTVIEW_SHIPMENTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER,
                bundle.getString(COL_SHIPMENTVIEW_SERIALOBJECTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_CUSTOMERCODE,
                bundle.getString(COL_SHIPMENTVIEW_CUSTOMERCODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_CUSTOMERNAME,
                bundle.getString(COL_SHIPMENTVIEW_CUSTOMERNAME), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVMATERIALMATERIALNUMBER,
                bundle.getString(COL_SHIPMENTVIEW_MATREVMATERIALMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVMATERIALSAPNUMBER,
                bundle.getString(COL_SHIPMENTVIEW_MATREVMATERIALSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVREVISIONNUMBER,
                bundle.getString(COL_SHIPMENTVIEW_MATREVREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVMATMATERIALTYPECODE,
                bundle.getString(COL_SHIPMENTVIEW_MATREVMATMATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE,
                bundle.getString(COL_SHIPMENTVIEW_MATREVMATMATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVMATMATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIAL_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVMATERIALSHORTTEXT,
                bundle.getString(COL_SHIPMENTVIEW_MATREVMATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE,
                bundle.getString(COL_SHIPMENTVIEW_MATREVMATOWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ShipmentSearchDTO.SELECT_LASTUPDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_LASTUPDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);


        visibleFields = new DualListModel<>();
        visibleFields.setSource(new ArrayList<>());
        visibleFields.setTarget(new ArrayList<>());

        for (final SearchFieldDTO d : searchObj.getSearchFields()) {
            if (!d.isVisible()) {
                visibleFields.getSource().add(d);
            }
            else {
                visibleFields.getTarget().add(d);
            }
        }
    }

    /**
     * Perform data fetch operation
     */
    @Customized
    public void fetchShipments() {
        logger.debug("Perform data fetch operation");

        try {
            preSearch();
        }
        catch (final SearchInputFieldValidationException e) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, SEARCH_INPUT_VALIDATION, "", e.getSearchFieldName());
            return;
        }

        refreshFormatSettings();
        setCountFilterDependent(2);

        try {
            shipmentsList = shipmentService.searchAllShipments(searchObj);

            if (searchObj.isCount()) {
                if (shipmentsList.size() == searchObj.getMaxResult()) {
                    countResult = shipmentService.countAllShipments(searchObj);
                }
                else {
                    countResult = shipmentsList.size();
                }
            }

            queryManager.saveQuery(userSession.getPrincipal().getId(), VIEW_ID, null, searchObj);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_FETCH_FAIL, e);
        }
        finally {
            postSearch();
        }
    }

    @Override
    protected String getViewName() {
        return VIEW_ID;
    }

    @Override
    public void resetSearchObject() {
        initSearchObject();
        fetchShipments();
    }



    /**
     * @return the list of elements
     */
    @Generated
    public Collection<ShipmentSearchDTO> getShipmentsList() {
        return shipmentsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public ShipmentSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(ShipmentSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        // No appropriate form found!
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteShipment() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            shipmentService.deleteShipment(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchShipments();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            shipmentService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchShipments();
        return "";
    }

    /**
     * @return the form title
     */
    @Generated
    public String getFormTitle() {
        return formTitle;
    }

    /**
     * @param formTitle
     */
    @Generated
    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    /**
     * @return the result of the count operation
     */
    @Generated
    public long getCountResult() {
        return countResult;
    }

    /**
     * @return the name of the query
     */
    @Generated
    public String getSavedQueryName() {
        return savedQueryName;
    }

    /**
     * @param savedQueryName
     */
    @Generated
    public void setSavedQueryName(String savedQueryName) {
        this.savedQueryName = savedQueryName;
    }

    /**
     * @return the name of the selected saved query
     */
    @Override
    @Generated
    public String getSelectedSavedQuery() {
        return selectedSavedQuery;
    }

    /**
     * @param selectedSavedQuery
     */
    @Generated
    public void setSelectedSavedQuery(String selectedSavedQuery) {
        this.selectedSavedQuery = selectedSavedQuery;
    }

    /**
     * @return the URL of the current page
     */
    @Generated
    public String getCurrentPageURL() {
        return PAGE_URL;
    }

    /**
     * Refresh format settings from user session
     */
    @Generated
    public void refreshFormatSettings() {
        searchObj.setDateFormat(userSession.getDateFormat());
        searchObj.setDateTimeFormat(userSession.getDateTimeFormat());
        searchObj.setNumberFormat(userSession.getNumberFormat());
        searchObj.setDecimalSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getDecimalSeparator());
        searchObj.setGroupingSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getGroupingSeparator());
    }

    /**
     * Perform count operation
     */
    @Generated
    public void countRecords() {
        logger.debug("Perform count operation");

        try {
            preSearch();
        }
        catch (final SearchInputFieldValidationException e) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, SEARCH_INPUT_VALIDATION, "", e.getSearchFieldName());
            return;
        }

        refreshFormatSettings();

        try {
            countResult = shipmentService.countAllShipments(searchObj);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, OPERATION_COUNT_RESULT, "", countResult);
        }
        catch (final Exception e) {
            logger.error("Error while performing count operation!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COUNT_FAIL, e);
        }
        finally {
            postSearch();
        }
    }

    /**
     * Callback method for auto-complete field
     * @param query the filter criterion inserted by the user
     * @return a list containing all proposals
     */
    @Generated
    public List<String> onCompleteMaterialMaterialNumber(String query) {
        return OnCompleteHelper.onCompleteMaterialNumber(materialService, query);
    }

    public List<String> onCompleteSapNumber(String query) {
        return OnCompleteHelper.onCompleteSapNumber(materialService, query);
    }

    public List<String> onCompleteCustomerName(String query) {
        return OnCompleteHelper.onCompleteCustomerName(customerService, query);
    }

    public List<String> onCompleteMatType(String query) {
        return OnCompleteHelper.onCompleteMatType(matTypeService, query);
    }

    public List<String> onCompleteMvtType(String query) {
        return OnCompleteHelper.onCompleteMvtType(mvtTypeService, query);
    }

    /**
     * Save new query
     */
    @Generated
    public void saveNewQuery() {
        if (savedQueryName.isEmpty()) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, SAVED_QUERY_EMPTY_NAME);
            return;
        }

        if (savedQueryName.equals(SavedQueryService.LAST_QUERY_TITLE)) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, SAVED_QUERY_ILLEGAL_NAME);
            return;
        }

        logger.debug("Save new query");

        queryManager.saveQuery(userSession.getPrincipal().getId(), VIEW_ID, savedQueryName, searchObj);
        MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, SAVED_QUERY_NEW_SUCCESS, "", savedQueryName);
    }

    /**
     * @return an array containing all saved queries
     */
    @Generated
    public SelectItem[] getSavedQueries() {
        logger.debug("Load saved queries");

        final Collection<String> savedQueries = queryManager.getSavedQueries(userSession.getPrincipal().getId(), VIEW_ID);
        final var items = new SelectItem[savedQueries.size()];
        int i = 0;

        for (final String item : savedQueries)
            items[i++] = new SelectItem(item, item);

        return items;
    }

    /**
     * Delete saved query
     */
    @Generated
    public void deleteSavedQuery() {
        if (selectedSavedQuery == null)
            return;

        logger.debug("Delete saved query");

        queryManager.deleteSavedQuery(userSession.getPrincipal().getId(), VIEW_ID, selectedSavedQuery);

        MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, SAVED_QUERY_DELETE_SUCCESS, "", selectedSavedQuery);
        selectedSavedQuery = null;
    }

    /**
     * Run selected saved query
     */
    @Generated
    public void runSavedQuery() {
        if (selectedSavedQuery == null)
            return;

        logger.debug("Run saved query");

        searchObj = queryManager.getSavedQuery(userSession.getPrincipal().getId(), VIEW_ID, selectedSavedQuery);

        prepareAfterLoad();
        fetchShipments();
    }

}