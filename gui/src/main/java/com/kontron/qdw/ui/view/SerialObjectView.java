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
import jakarta.faces.application.FacesMessage;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.boundary.serial.*;
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

@Named("serialObjectView")
@ViewScoped
public class SerialObjectView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.SerialObjectView";
    @Generated
    public static final String PAGE_URL = "/view/SerialObjectView.jsf?faces-redirect=true";
    @Generated
    private static final long serialVersionUID = 1L;

    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;

    @Generated
    private final transient SerialObjectBoundaryService serialObjectService;
    @Generated
    private final transient MaterialBoundaryService materialService;
    private final transient MaterialTypeBoundaryService matTypeService;
    @Generated
    private final transient SavedQueryService queryManager;

    @Generated
    private String savedQueryName;
    @Generated
    private String selectedSavedQuery;
    @Generated
    private String formTitle = "";

    @Generated
    private List<SerialObjectSearchDTO> serialObjectsList = new ArrayList<>();
    @Generated
    private SerialObjectSearchDTO selectedObject;
    @Generated
    private long countResult;



    /**
     * Default constructor
     */
    @Generated
    public SerialObjectView() {
        userSession = null;
        serialObjectService = null;
        materialService = null;
        queryManager = null;
        matTypeService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serialObjectService
     * @param materialService
     * @param queryManager
     */
    @Inject
    @Generated
    public SerialObjectView(UserSession userSession, SerialObjectBoundaryService serialObjectService, MaterialBoundaryService materialService,
            MaterialTypeBoundaryService matTypeService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.serialObjectService = serialObjectService;
        this.materialService = materialService;
        this.queryManager = queryManager;
        this.matTypeService = matTypeService;
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


        formTitle = bundle.getString(FORM_SERIALOBJECTVIEW_TITLE);

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
        fetchSerialObjects();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_SERIALNUMBER,
                bundle.getString(COL_SERIALOBJECTVIEW_SERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_MATMATERIALNUMBER,
                bundle.getString(COL_SERIALOBJECTVIEW_MATMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_MATSAPNUMBER,
                bundle.getString(COL_SERIALOBJECTVIEW_MATSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_MATMATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIAL_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_MATSHORTTEXT,
                bundle.getString(COL_SERIALOBJECTVIEW_MATSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_MATOWNERLOCATIONCODE,
                bundle.getString(COL_SERIALOBJECTVIEW_MATOWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_MATMATERIALTYPECODE,
                bundle.getString(COL_SERIALOBJECTVIEW_MATMATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_MATMATERIALCLASSCODE,
                bundle.getString(COL_SERIALOBJECTVIEW_MATMATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_CUSTOMERSERIALNUMBER,
                bundle.getString(COL_SERIALOBJECTVIEW_CUSTOMERSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_PRODUCTIONORDERNUMBER,
                bundle.getString(COL_SERIALOBJECTVIEW_PRODUCTIONORDERNUMBER), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_ASSEMBLYDATE,
                bundle.getString(COL_SERIALOBJECTVIEW_ASSEMBLYDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, SerialObjectSearchDTO.SELECT_LASTUPDATE,
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
    public void fetchSerialObjects() {
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
            serialObjectsList = serialObjectService.searchAllSerialObjects(searchObj);

            if (searchObj.isCount()) {
                if (serialObjectsList.size() == searchObj.getMaxResult()) {
                    countResult = serialObjectService.countAllSerialObjects(searchObj);
                }
                else {
                    countResult = serialObjectsList.size();
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
        fetchSerialObjects();
    }



    /**
     * Callback method for auto-complete field
     * @param query the filter criterion inserted by the user
     * @return a list containing all proposals
     */
    @Customized
    public List<String> onCompleteMaterialMaterialNumber(String query) {
        return OnCompleteHelper.onCompleteMaterialNumber(materialService, query);
    }

    public List<String> onCompleteSapNumber(String query) {
        return OnCompleteHelper.onCompleteSapNumber(materialService, query);
    }

    public List<String> onCompleteMatType(String query) {
        return OnCompleteHelper.onCompleteMatType(matTypeService, query);
    }



    /**
     * @return the list of elements
     */
    @Generated
    public Collection<SerialObjectSearchDTO> getSerialObjectsList() {
        return serialObjectsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public SerialObjectSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(SerialObjectSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewSerialObjectDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteSerialObject() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            serialObjectService.deleteSerialObject(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchSerialObjects();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            serialObjectService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchSerialObjects();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewSerialObjectDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
            url = ViewSerialObjectDialog.PAGE_INIT_URL + selectedObject.getId();

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
            countResult = serialObjectService.countAllSerialObjects(searchObj);

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
        fetchSerialObjects();
    }

}