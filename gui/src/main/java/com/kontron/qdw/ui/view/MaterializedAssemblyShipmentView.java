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

@Named("materializedAssemblyShipmentView")
@ViewScoped
public class MaterializedAssemblyShipmentView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<MaterializedAssemblyShipmentSearchDTO> materializedAssemblyShipmentsList = new ArrayList<>();
    @Generated
    private MaterializedAssemblyShipmentSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterializedAssemblyShipmentBoundaryService materializedAssemblyShipmentService;
    @Generated
    public static final String PAGE_URL = "/view/MaterializedAssemblyShipmentView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.MaterializedAssemblyShipmentView";
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
    public MaterializedAssemblyShipmentView() {
        this.userSession = null;
        this.materializedAssemblyShipmentService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param materializedAssemblyShipmentService
     * @param queryManager
     */
    @Inject
    @Generated
    public MaterializedAssemblyShipmentView(UserSession userSession, MaterializedAssemblyShipmentBoundaryService materializedAssemblyShipmentService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.materializedAssemblyShipmentService = materializedAssemblyShipmentService;
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


        formTitle = bundle.getString(FORM_MATERIALIZEDASSEMBLYSHIPMENTVIEW_TITLE);

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
        fetchMaterializedAssemblyShipments();

        logger.debug("View initialization finished");
    }

    /**
     * Initialize search object
     */
    @Generated
    public void initSearchObject() {
        searchObj = new SearchDTO();
        int colOrderId = -1;

        // Initialize search object
        searchObj.setMaxResult(1000);
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setCount(false);

        refreshFormatSettings();

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_ID,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_ID), SearchFieldDataTypeEnum.LONG, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PLANT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_PLANT), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_ASSEMBLYDATE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_ASSEMBLYDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_ASSEMBLYPO,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_ASSEMBLYPO), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_SERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_SERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTSERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_PARENTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_CUSTOMERCODE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_CUSTOMERCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_CUSTOMERNAME,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_CUSTOMERNAME), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_COUNTRYCODE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_COUNTRYCODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_COUNTRYNAME,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_COUNTRYNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_SHIPMENTDATE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_SHIPMENTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_ARRIVALDATE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_ARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_SUPPLIERCODE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_SUPPLIERCODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_SUPPLIERNAME,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_SUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_SHIPMENTMOVEMENTTYPE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_SHIPMENTMOVEMENTTYPE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_ARRIVALMOVEMENTTYPE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_ARRIVALMOVEMENTTYPE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_CUSTOMERORDERNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_CUSTOMERORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PURCHASEORDERNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_PURCHASEORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_MATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_SAPNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_SAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_REVISIONNUMBER,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_REVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_MATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_MATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_PARENTMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTSAPNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_PARENTSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTREVISIONNUMBER,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_PARENTREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_PARENTMATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALHIERARCHY,
                bundle.getString(COL_MATERIALIZEDASSEMBLYSHIPMENTVIEW_PARENTMATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_PARENTMATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);


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
    public void fetchMaterializedAssemblyShipments() {
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
            materializedAssemblyShipmentsList = materializedAssemblyShipmentService.searchAllMaterializedAssemblyShipments(searchObj);

            if (searchObj.isCount()) {
                if (materializedAssemblyShipmentsList.size() == searchObj.getMaxResult()) {
                    countResult = materializedAssemblyShipmentService.countAllMaterializedAssemblyShipments(searchObj);
                }
                else {
                    countResult = materializedAssemblyShipmentsList.size();
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
        fetchMaterializedAssemblyShipments();
    }


    /**
     * @return the list of elements
     */
    @Generated
    public Collection<MaterializedAssemblyShipmentSearchDTO> getMaterializedAssemblyShipmentsList() {
        return materializedAssemblyShipmentsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public MaterializedAssemblyShipmentSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(MaterializedAssemblyShipmentSearchDTO selectedObject) {
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
    public void deleteMaterializedAssemblyShipment() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            materializedAssemblyShipmentService.deleteMaterializedAssemblyShipment(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchMaterializedAssemblyShipments();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            materializedAssemblyShipmentService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchMaterializedAssemblyShipments();
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
            countResult = materializedAssemblyShipmentService.countAllMaterializedAssemblyShipments(searchObj);

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
        fetchMaterializedAssemblyShipments();
    }

}
