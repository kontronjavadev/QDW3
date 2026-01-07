package com.kontron.qdw.ui.view;

import org.slf4j.*;
import java.lang.invoke.*;
import com.kontron.qdw.dto.mv.*;
import com.kontron.qdw.boundary.mv.*;
import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import java.text.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.view.util.SuperView;
import com.kontron.qdw.service.*;
import jakarta.faces.model.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("materializedArrivalShipmentView")
@ViewScoped
public class MaterializedArrivalShipmentView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<MaterializedArrivalShipmentSearchDTO> materializedArrivalShipmentsList = new ArrayList<>();
    @Generated
    private MaterializedArrivalShipmentSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterializedArrivalShipmentBoundaryService materializedArrivalShipmentService;
    @Generated
    public static final String PAGE_URL = "/view/MaterializedArrivalShipmentView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.MaterializedArrivalShipmentView";
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
    public MaterializedArrivalShipmentView() {
        this.userSession = null;
        this.materializedArrivalShipmentService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param materializedArrivalShipmentService
     * @param queryManager
     */
    @Inject
    @Generated
    public MaterializedArrivalShipmentView(UserSession userSession, MaterializedArrivalShipmentBoundaryService materializedArrivalShipmentService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.materializedArrivalShipmentService = materializedArrivalShipmentService;
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


        formTitle = bundle.getString(FORM_MATERIALIZEDARRIVALSHIPMENTVIEW_TITLE);

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
        fetchMaterializedArrivalShipments();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_SHIPMENTID,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_SHIPMENTID), SearchFieldDataTypeEnum.LONG, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_ARRIVALID,
                bundle.getString(LBL_ATTR_MATERIALIZEDARRIVALSHIPMENT_ARRIVALID), SearchFieldDataTypeEnum.LONG, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PLANT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_PLANT), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_SERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_SERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PARENTSERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_PARENTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_CUSTOMERNAME,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_CUSTOMERNAME), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_COUNTRYNAME,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_COUNTRYNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_SHIPMENTDATE,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_SHIPMENTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_ARRIVALDATE,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_ARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_SUPPLIERNAME,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_SUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_SHIPMENTMOVEMENTTYPE,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_SHIPMENTMOVEMENTTYPE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_ARRIVALMOVEMENTTYPE,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_ARRIVALMOVEMENTTYPE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_CUSTOMERORDERNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_CUSTOMERORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PURCHASEORDERNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_PURCHASEORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_MATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_SAPNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_SAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_MATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_MATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_REVISIONNUMBER,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_REVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_OWNERLOCATION,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_OWNERLOCATION), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_PARENTMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PARENTSAPNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_PARENTSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_PARENTMATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_PARENTMATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALHIERARCHY,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_PARENTMATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_PARENTREVISIONNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_PARENTREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_ASSEMBLYDATE,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_ASSEMBLYDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSearchDTO.SELECT_ASSEMBLYPO,
                bundle.getString(COL_MATERIALIZEDARRIVALSHIPMENTVIEW_ASSEMBLYPO), SearchFieldDataTypeEnum.STRING, 80);


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
    public void fetchMaterializedArrivalShipments() {
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
            materializedArrivalShipmentsList = materializedArrivalShipmentService.searchAllMaterializedArrivalShipments(searchObj);

            if (searchObj.isCount()) {
                if (materializedArrivalShipmentsList.size() == searchObj.getMaxResult()) {
                    countResult = materializedArrivalShipmentService.countAllMaterializedArrivalShipments(searchObj);
                }
                else {
                    countResult = materializedArrivalShipmentsList.size();
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
        fetchMaterializedArrivalShipments();
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<MaterializedArrivalShipmentSearchDTO> getMaterializedArrivalShipmentsList() {
        return materializedArrivalShipmentsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public MaterializedArrivalShipmentSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(MaterializedArrivalShipmentSearchDTO selectedObject) {
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
    public void deleteMaterializedArrivalShipment() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            materializedArrivalShipmentService.deleteMaterializedArrivalShipment(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchMaterializedArrivalShipments();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            materializedArrivalShipmentService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchMaterializedArrivalShipments();
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
            countResult = materializedArrivalShipmentService.countAllMaterializedArrivalShipments(searchObj);

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
        fetchMaterializedArrivalShipments();
    }

}