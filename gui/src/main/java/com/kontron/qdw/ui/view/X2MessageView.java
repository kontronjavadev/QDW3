package com.kontron.qdw.ui.view;

import org.slf4j.*;

import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.OnCompleteHelper;
import com.kontron.qdw.ui.view.util.SuperView;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import java.text.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.service.*;
import jakarta.faces.model.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("x2MessageView")
@ViewScoped
public class X2MessageView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<X2MessageSearchDTO> x2MessagesList = new ArrayList<>();
    @Generated
    private X2MessageSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient X2MessageBoundaryService x2MessageService;
    @Generated
    public static final String PAGE_URL = "/view/X2MessageView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.X2MessageView";
    @Generated
    private final transient SavedQueryService queryManager;
    @Generated
    private String savedQueryName;
    @Generated
    private String selectedSavedQuery;

    private final transient RepairStateBoundaryService repairStateService;
    private final transient RepairTaskBoundaryService repairTaskService;
    private final transient FaultAnalysisBoundaryService faultAnalysisService;
    private final transient RepairErrorCodeBoundaryService repairErrorCodeService;


    /**
     * Default constructor
     */
    @Generated
    public X2MessageView() {
        userSession = null;
        x2MessageService = null;
        materialService = null;
        queryManager = null;

        repairStateService = null;
        repairTaskService = null;
        faultAnalysisService = null;
        repairErrorCodeService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param x2MessageService
     * @param materialService
     * @param queryManager
     */
    @Inject
    @Generated
    public X2MessageView(UserSession userSession, X2MessageBoundaryService x2MessageService, MaterialBoundaryService materialService,
            RepairStateBoundaryService repairStateService, RepairTaskBoundaryService repairTaskService,
            FaultAnalysisBoundaryService faultAnalysisService, RepairErrorCodeBoundaryService repairErrorCodeService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.x2MessageService = x2MessageService;
        this.materialService = materialService;
        this.queryManager = queryManager;

        this.repairStateService = repairStateService;
        this.repairTaskService = repairTaskService;
        this.faultAnalysisService = faultAnalysisService;
        this.repairErrorCodeService = repairErrorCodeService;
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


        formTitle = bundle.getString(FORM_X2MESSAGEVIEW_TITLE);

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
        fetchX2Messages();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_ID,
                bundle.getString(COL_X2MESSAGEVIEW_ID), SearchFieldDataTypeEnum.LONG, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_SERVMESSSERVORDCODE,
                bundle.getString(COL_X2MESSAGEVIEW_SERVMESSSERVORDCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_SERVICEMESSAGEID,
                bundle.getString(COL_X2MESSAGEVIEW_SERVICEMESSAGEID), SearchFieldDataTypeEnum.LONG, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_SERVMESSSEROBJSERIALNUMBER,
                bundle.getString(COL_X2MESSAGEVIEW_SERVMESSSEROBJSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_MATREVMATMATERIALNUMBER,
                bundle.getString(COL_X2MESSAGEVIEW_MATREVMATMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_MATREVMATSAPNUMBER,
                bundle.getString(COL_X2MESSAGEVIEW_MATREVMATSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_MATREVREVISIONNUMBER,
                bundle.getString(COL_X2MESSAGEVIEW_MATREVREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_WORKCENTER,
                bundle.getString(LBL_ATTR_X2MESSAGE_WORKCENTER), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_REPAIRSTATECODE,
                bundle.getString(COL_X2MESSAGEVIEW_REPAIRSTATECODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_REPAIRTASKCODE,
                bundle.getString(COL_X2MESSAGEVIEW_REPAIRTASKCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_FAULTANALYSISCODE,
                bundle.getString(COL_X2MESSAGEVIEW_FAULTANALYSISCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_REPAIRERRORCODECODE,
                bundle.getString(COL_X2MESSAGEVIEW_REPAIRERRORCODECODE), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_CUSTOMERREPORT,
                bundle.getString(LBL_ATTR_X2MESSAGE_CUSTOMERREPORT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_DEFECTCOMPONENT,
                bundle.getString(COL_X2MESSAGEVIEW_DEFECTCOMPONENT), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_ANALYSISTEXT,
                bundle.getString(LBL_ATTR_X2MESSAGE_ANALYSISTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_DESIGNATOR,
                bundle.getString(LBL_ATTR_X2MESSAGE_DESIGNATOR), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_CAUSETEXT,
                bundle.getString(LBL_ATTR_X2MESSAGE_CAUSETEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, X2MessageSearchDTO.SELECT_LASTUPDATE,
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
    public void fetchX2Messages() {
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
            x2MessagesList = x2MessageService.searchAllX2Messages(searchObj);

            if (searchObj.isCount()) {
                if (x2MessagesList.size() == searchObj.getMaxResult()) {
                    countResult = x2MessageService.countAllX2Messages(searchObj);
                }
                else {
                    countResult = x2MessagesList.size();
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
        fetchX2Messages();
    }

    /**
     * Handle single click event: set selection to reselect after switching to another view and back to this view.
     */
    public void onClick() {
        onClickId(x2MessagesList, X2MessageSearchDTO::getId, this::setSelectedObject);
    }



    @Customized
    public List<String> onCompleteMaterialMaterialNumber(String query) {
        return OnCompleteHelper.onCompleteMaterialNumber(materialService, query);
    }

    public List<String> onCompleteSapNumber(String searchText) {
        return OnCompleteHelper.onCompleteSapNumber(materialService, searchText);
    }

    public List<String> onCompleteRepairStateCode(String searchText) {
        return OnCompleteHelper.onCompleteRepairStateCode(repairStateService, searchText);
    }

    public List<String> onCompleteRepairTaskCode(String searchText) {
        return OnCompleteHelper.onCompleteRepairTaskCode(repairTaskService, searchText);
    }

    public List<String> onCompleteSymptomCode(String query) {
        return OnCompleteHelper.onCompleteSymptomCode(faultAnalysisService, query);
    }

    public List<String> onCompleteRepairErrorCodeCode(String searchText) {
        return OnCompleteHelper.onCompleteRepairErrorCodeCode(repairErrorCodeService, searchText);
    }



    /**
     * @return the list of elements
     */
    @Generated
    public Collection<X2MessageSearchDTO> getX2MessagesList() {
        return x2MessagesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public X2MessageSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(X2MessageSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewX2MessageDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteX2Message() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            x2MessageService.deleteX2Message(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchX2Messages();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            x2MessageService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchX2Messages();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewX2MessageDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            url = ViewX2MessageDialog.PAGE_INIT_URL + selectedObject.getId();
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
            countResult = x2MessageService.countAllX2Messages(searchObj);

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
        fetchX2Messages();
    }

}
