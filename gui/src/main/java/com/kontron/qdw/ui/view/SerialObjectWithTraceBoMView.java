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

@Named("serialObjectWithTraceBoMView")
@ViewScoped
public class SerialObjectWithTraceBoMView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<MaterializedArrivalShipmentSerObjTBoMSearchDTO> materializedArrivalShipmentsList = new ArrayList<>();
    @Generated
    private MaterializedArrivalShipmentSerObjTBoMSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterializedArrivalShipmentBoundaryService materializedArrivalShipmentService;
    @Generated
    public static final String PAGE_URL = "/view/SerialObjectWithTraceBoMView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    private final transient SupplierBoundaryService supplierService;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.SerialObjectWithTraceBoMView";
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
    public SerialObjectWithTraceBoMView() {
        this.userSession = null;
        this.materializedArrivalShipmentService = null;
        this.supplierService = null;
        this.materialService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param materializedArrivalShipmentService
     * @param supplierService
     * @param materialService
     * @param queryManager
     */
    @Inject
    @Generated
    public SerialObjectWithTraceBoMView(UserSession userSession, MaterializedArrivalShipmentBoundaryService materializedArrivalShipmentService,
            SupplierBoundaryService supplierService, MaterialBoundaryService materialService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.materializedArrivalShipmentService = materializedArrivalShipmentService;
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


        formTitle = bundle.getString(FORM_SERIALOBJECTWITHTRACEBOMVIEW_TITLE);

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
        searchObj.setCaseSensitive(true);
        searchObj.setCount(false);

        refreshFormatSettings();

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_PLANT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_PLANT), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SERIALNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SERIALNUMBER), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_PARENTSERIALNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_PARENTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJIDCUSTOMERSERIALNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJIDCUSTOMERSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMSUPPLIERNAME,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMSUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVMATMATERIALNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMMATREVMATMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVREVISIONNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMMATREVREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVMATSAPNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMMATREVMATSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVMATMATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIAL_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVMATSHORTTEXT,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMMATREVMATSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVMATOWNERLOCATIONCODE,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMMATREVMATOWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVMATMATERIALTYPECODE,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMMATREVMATMATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMMATREVMATMATERIALCLASSCODE,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMMATREVMATMATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_CUSTOMERNAME,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_CUSTOMERNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_COUNTRYNAME,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_COUNTRYNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_ASSEMBLYDATE,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_ASSEMBLYDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_ASSEMBLYPO,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_ASSEMBLYPO), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMPRODUCTIONDATE,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMPRODUCTIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMDELIVERYNOTENUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMDELIVERYNOTENUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMORDERNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalShipmentSerObjTBoMSearchDTO.SELECT_SEROBJTBOMLOTNUMBER,
                bundle.getString(COL_SERIALOBJECTWITHTRACEBOMVIEW_SEROBJTBOMLOTNUMBER), SearchFieldDataTypeEnum.STRING, 80);


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
        setCountFilterDependent();

        try {
            materializedArrivalShipmentsList = materializedArrivalShipmentService.searchAllMaterializedArrivalShipmentSerObjTBoMs(searchObj);

            if (searchObj.isCount()) {
                if (materializedArrivalShipmentsList.size() == searchObj.getMaxResult()) {
                    countResult = materializedArrivalShipmentService.countAllMaterializedArrivalShipmentSerObjTBoMs(searchObj);
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
    public Collection<MaterializedArrivalShipmentSerObjTBoMSearchDTO> getMaterializedArrivalShipmentsList() {
        return materializedArrivalShipmentsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public MaterializedArrivalShipmentSerObjTBoMSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(MaterializedArrivalShipmentSerObjTBoMSearchDTO selectedObject) {
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
            countResult = materializedArrivalShipmentService.countAllMaterializedArrivalShipmentSerObjTBoMs(searchObj);

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
        fetchMaterializedArrivalShipments();
    }

}