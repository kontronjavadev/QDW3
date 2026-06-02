package com.kontron.qdw.ui.view;

import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import com.kontron.qdw.domain.material.*;
import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.OnCompleteHelper;
import com.kontron.qdw.ui.view.util.SuperView;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import java.text.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.service.*;
import com.kontron.qdw.dto.material.*;
import jakarta.faces.model.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("eWSEntryView")
@ViewScoped
public class EWSEntryView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<EWSEntrySearchDTO> eWSEntriesList = new ArrayList<>();
    @Generated
    private EWSEntrySearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient EWSEntryBoundaryService eWSEntryService;
    @Generated
    public static final String PAGE_URL = "/view/EWSEntryView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.EWSEntryView";
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
    public EWSEntryView() {
        this.userSession = null;
        this.eWSEntryService = null;
        this.materialService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param eWSEntryService
     * @param materialService
     * @param queryManager
     */
    @Inject
    @Generated
    public EWSEntryView(UserSession userSession, EWSEntryBoundaryService eWSEntryService, MaterialBoundaryService materialService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.eWSEntryService = eWSEntryService;
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_SUPERUSER)) {
            return;
        }


        formTitle = bundle.getString(FORM_EWSENTRYVIEW_TITLE);

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
        fetchEWSEntries();

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
        searchObj.setCount(true);

        refreshFormatSettings();

        new JSFSearchFieldDTO(searchObj, ++colOrderId, EWSEntrySearchDTO.SELECT_MATERIALMATERIALNUMBER,
                bundle.getString(COL_EWSENTRYVIEW_MATERIALMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, EWSEntrySearchDTO.SELECT_TYPE,
                bundle.getString(LBL_ATTR_EWSENTRY_TYPE), SearchFieldDataTypeEnum.ENUM, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, EWSEntrySearchDTO.SELECT_THRESHOLD,
                bundle.getString(LBL_ATTR_EWSENTRY_THRESHOLD), SearchFieldDataTypeEnum.DOUBLE, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, EWSEntrySearchDTO.SELECT_BOARDORSYSTEM,
                bundle.getString(COL_EWSENTRYVIEW_BOARDORSYSTEM), SearchFieldDataTypeEnum.BOOLEAN, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, EWSEntrySearchDTO.SELECT_FILTERCRITERION,
                bundle.getString(LBL_ATTR_EWSENTRY_FILTERCRITERION), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, EWSEntrySearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, EWSEntrySearchDTO.SELECT_LASTUPDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_LASTUPDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        visibleFields = new DualListModel<>();
        visibleFields.setSource(new ArrayList<>());
        visibleFields.setTarget(searchObj.getSearchFields());
    }

    /**
     * Perform data fetch operation
     */
    @Customized
    public void fetchEWSEntries() {
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
            eWSEntriesList = eWSEntryService.searchAllEWSEntries(searchObj);

            if (searchObj.isCount()) {
                if (eWSEntriesList.size() == searchObj.getMaxResult()) {
                    countResult = eWSEntryService.countAllEWSEntries(searchObj);
                }
                else {
                    countResult = eWSEntriesList.size();
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
        fetchEWSEntries();
    }

    /**
     * Handle single click event: set selection to reselect after switching to another view and back to this view.
     */
    public void onClick() {
        onClickId(eWSEntriesList, EWSEntrySearchDTO::getId, this::setSelectedObject);
    }



    @Customized
    public List<String> onCompleteMaterialMaterialNumber(String query) {
        return OnCompleteHelper.onCompleteMaterialNumber(materialService, query);
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<EWSEntrySearchDTO> geteWSEntriesList() {
        return eWSEntriesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public EWSEntrySearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(EWSEntrySearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Perform translation of given enumeration literal
     * @param item
     * @return the translation based on the user's locale
     */
    @Generated
    public String translateType(EWSType item) {
        return bundle.getString("ewstype_" + item.name().toLowerCase());
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        if (userSession.redirectTo(getCurrentPageURL(), openEditEWSEntryDialog())) {
            return;
        }

        userSession.redirectTo(getCurrentPageURL(), openViewEWSEntryDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteEWSEntry() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            eWSEntryService.deleteEWSEntry(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchEWSEntries();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        var url = "";
        long newId;

        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            newId = eWSEntryService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            url = EditEWSEntryDialog.PAGE_INIT_URL + newId;
        }

        userSession.setLastPage(getCurrentPageURL());
        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openCreateNewEWSEntryDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            url = CreateNewEWSEntryDialog.PAGE_INIT_URL;
        }

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openEditEWSEntryDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            url = EditEWSEntryDialog.PAGE_INIT_URL + selectedObject.getId();
        }

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewEWSEntryDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_SUPERUSER)) {
            url = ViewEWSEntryDialog.PAGE_INIT_URL + selectedObject.getId();
        }

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
            countResult = eWSEntryService.countAllEWSEntries(searchObj);

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

        for (final String item : savedQueries) {
            items[i++] = new SelectItem(item, item);
        }

        return items;
    }

    /**
     * Delete saved query
     */
    @Generated
    public void deleteSavedQuery() {
        if (selectedSavedQuery == null) {
            return;
        }

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
        if (selectedSavedQuery == null) {
            return;
        }

        logger.debug("Run saved query");

        searchObj = queryManager.getSavedQuery(userSession.getPrincipal().getId(), VIEW_ID, selectedSavedQuery);

        prepareAfterLoad();
        fetchEWSEntries();
    }

}
