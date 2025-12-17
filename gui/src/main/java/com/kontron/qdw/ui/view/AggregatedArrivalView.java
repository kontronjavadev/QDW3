package com.kontron.qdw.ui.view;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import com.kontron.qdw.dto.mv.*;
import com.kontron.qdw.boundary.mv.*;
import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.dto.base.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import java.text.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.view.util.SuperView;
import com.kontron.qdw.service.*;
import com.kontron.qdw.dto.material.*;
import jakarta.faces.model.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("aggregatedArrivalView")
@ViewScoped
public class AggregatedArrivalView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<AggregatedArrivalSearchDTO> aggregatedArrivalsList = new ArrayList<>();
    @Generated
    private AggregatedArrivalSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient AggregatedArrivalBoundaryService aggregatedArrivalService;
    @Generated
    public static final String PAGE_URL = "/view/AggregatedArrivalView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    private final transient CountryBoundaryService countryService;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.AggregatedArrivalView";
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
    public AggregatedArrivalView() {
        this.userSession = null;
        this.aggregatedArrivalService = null;
        this.countryService = null;
        this.materialService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param aggregatedArrivalService
     * @param countryService
     * @param materialService
     * @param queryManager
     */
    @Inject
    @Generated
    public AggregatedArrivalView(UserSession userSession, AggregatedArrivalBoundaryService aggregatedArrivalService,
            CountryBoundaryService countryService, MaterialBoundaryService materialService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.aggregatedArrivalService = aggregatedArrivalService;
        this.countryService = countryService;
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


        formTitle = bundle.getString(FORM_AGGREGATEDARRIVALVIEW_TITLE);

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
        fetchAggregatedArrivals();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_PLANTCODE,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_PLANTCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_SUPPLIERNAME,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_SUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_COUNTRYNAME,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_COUNTRYNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MATREVMATMATERIALNUMBER,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MATREVMATMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MATREVMATSAPNUMBER,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MATREVMATSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MATREVMATTYPECODE,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MATREVMATTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MATREVMATCLASSCODE,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MATREVMATCLASSCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MATREVMATOWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MATREVMATSHORTTEXT,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MATREVMATSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MATREVREVISIONNUMBER,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MATREVREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MOVEMENTTYPECODE,
                bundle.getString(COL_AGGREGATEDARRIVALVIEW_MOVEMENTTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_YEAR,
                bundle.getString(LBL_ATTR_ABSTRACTAGGREGATEDBASE_YEAR), SearchFieldDataTypeEnum.INTEGER, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_MONTH,
                bundle.getString(LBL_ATTR_ABSTRACTAGGREGATEDBASE_MONTH), SearchFieldDataTypeEnum.INTEGER, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, AggregatedArrivalSearchDTO.SELECT_ARRIVALS,
                bundle.getString(LBL_ATTR_AGGREGATEDARRIVAL_ARRIVALS), SearchFieldDataTypeEnum.INTEGER, 80);

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
    public void fetchAggregatedArrivals() {
        logger.debug("Perform data fetch operation");

        try {
            preSearch();
        }
        catch (final SearchInputFieldValidationException e) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, SEARCH_INPUT_VALIDATION, "", e.getSearchFieldName());
            return;
        }

        refreshFormatSettings();
        setCountFilterDependent();

        try {
            aggregatedArrivalsList = aggregatedArrivalService.searchAllAggregatedArrivals(searchObj);

            if (searchObj.isCount()) {
                countResult = aggregatedArrivalService.countAllAggregatedArrivals(searchObj);
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
        fetchAggregatedArrivals();
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<AggregatedArrivalSearchDTO> getAggregatedArrivalsList() {
        return aggregatedArrivalsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public AggregatedArrivalSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(AggregatedArrivalSearchDTO selectedObject) {
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
    public void deleteAggregatedArrival() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            aggregatedArrivalService.deleteAggregatedArrival(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchAggregatedArrivals();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            aggregatedArrivalService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchAggregatedArrivals();
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
            countResult = aggregatedArrivalService.countAllAggregatedArrivals(searchObj);

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
    public List<String> onCompleteCountryName(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<CountryListDTO> items = countryService.findCountries(query + "%");

            for (final CountryListDTO item : items)
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
        fetchAggregatedArrivals();
    }

}