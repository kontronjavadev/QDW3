package com.kontron.qdw.ui.view;

import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.boundary.serial.*;
import java.text.*;
import static com.kontron.qdw.ui.UserSession.*;
import jakarta.faces.model.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import java.io.*;
import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.SuperView;

import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.service.*;
import com.kontron.qdw.dto.material.*;

import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Named("arrivalLastView")
@ViewScoped
public class ArrivalLastView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<ArrivalLastSearchDTO> arrivalsList = new ArrayList<>();
    @Generated
    private ArrivalLastSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient ArrivalBoundaryService arrivalService;
    @Generated
    public static final String PAGE_URL = "/view/ArrivalLastView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    private final transient SupplierBoundaryService supplierService;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.ArrivalLastView";
    @Generated
    private final transient SavedQueryService queryManager;
    @Generated
    private String savedQueryName;
    @Generated
    private String selectedSavedQuery;

    /**
     * Default constructor
     */
    @Generated
    public ArrivalLastView() {
        this.userSession = null;
        this.arrivalService = null;
        this.supplierService = null;
        this.materialService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param arrivalService
     * @param supplierService
     * @param materialService
     * @param queryManager
     */
    @Inject
    @Generated
    public ArrivalLastView(UserSession userSession, ArrivalBoundaryService arrivalService, SupplierBoundaryService supplierService,
            MaterialBoundaryService materialService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.arrivalService = arrivalService;
        this.supplierService = supplierService;
        this.materialService = materialService;
        this.queryManager = queryManager;
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


        formTitle = bundle.getString(FORM_ARRIVALLASTVIEW_TITLE);

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
        fetchArrivals();

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
        searchObj.setCaseSensitive(false);
        searchObj.setCount(false);

        refreshFormatSettings();

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_ID,
                bundle.getString(COL_ARRIVALLASTVIEW_ID), SearchFieldDataTypeEnum.LONG, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_PLANTCODE,
                bundle.getString(COL_ARRIVALLASTVIEW_PLANTCODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_ORDERNUMBER,
                bundle.getString(COL_ARRIVALLASTVIEW_ORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MOVEMENTTYPECODE,
                bundle.getString(COL_ARRIVALLASTVIEW_MOVEMENTTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_ARRIVALDATE,
                bundle.getString(LBL_ATTR_ARRIVAL_ARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER,
                bundle.getString(COL_ARRIVALLASTVIEW_SERIALOBJECTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_SUPPLIERNAME,
                bundle.getString(COL_ARRIVALLASTVIEW_SUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALNUMBER,
                bundle.getString(COL_ARRIVALLASTVIEW_MATREVMATMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVMATSAPNUMBER,
                bundle.getString(COL_ARRIVALLASTVIEW_MATREVMATSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVMATSHORTTEXT,
                bundle.getString(COL_ARRIVALLASTVIEW_MATREVMATSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE,
                bundle.getString(COL_ARRIVALLASTVIEW_MATREVMATOWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALTYPECODE,
                bundle.getString(COL_ARRIVALLASTVIEW_MATREVMATMATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE,
                bundle.getString(COL_ARRIVALLASTVIEW_MATREVMATMATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIAL_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_MATREVREVISIONNUMBER,
                bundle.getString(COL_ARRIVALLASTVIEW_MATREVREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ArrivalLastSearchDTO.SELECT_LASTUPDATE,
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

    @Override
    protected String getViewName() {
        return VIEW_ID;
    }

    @Override
    public void resetSearchObject() {
        initSearchObject();
        fetchArrivals();
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<ArrivalLastSearchDTO> getArrivalsList() {
        return arrivalsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public ArrivalLastSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(ArrivalLastSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewArrivalDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteArrival() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            arrivalService.deleteArrival(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchArrivals();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            arrivalService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchArrivals();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewArrivalDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
            url = ViewArrivalDialog.PAGE_INIT_URL + selectedObject.getId();

        return url;
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
     * Perform data fetch operation
     */
    @Generated
    public void fetchArrivals() {
        logger.debug("Perform data fetch operation");

        try {
            preSearch();
        }
        catch (final SearchInputFieldValidationException e) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, SEARCH_INPUT_VALIDATION, "", e.getSearchFieldName());
            return;
        }

        refreshFormatSettings();

        try {
            arrivalsList = arrivalService.searchAllArrivalsLast(searchObj);

            if (searchObj.isCount())
                countResult = arrivalService.countAllArrivalsLast(searchObj);

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
            countResult = arrivalService.countAllArrivalsLast(searchObj);

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
    public List<String> onCompleteSupplierName(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<SupplierListDTO> items = supplierService.findSuppliers(query + "%");

            for (final SupplierListDTO item : items)
                results.add(item.getName());
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", query, e);
        }

        return results;
    }

    /**
     * Callback method for auto-complete field
     * @param query the filter criterion inserted by the user
     * @return a list containing all proposals
     */
    @Generated
    public List<String> onCompleteMaterialMaterialNumber(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<MaterialListDTO> items = materialService.findMaterials(query + "%");

            for (final MaterialListDTO item : items)
                results.add(item.getMaterialNumber());
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", query, e);
        }

        return results;
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
        fetchArrivals();
    }

}