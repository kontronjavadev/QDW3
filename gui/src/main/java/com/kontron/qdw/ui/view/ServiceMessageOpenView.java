package com.kontron.qdw.ui.view;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
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
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("serviceMessageOpenView")
@ViewScoped
public class ServiceMessageOpenView extends AbstractSearchableView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<ServiceMessageOpenSearchDTO> serviceMessagesList = new ArrayList<>();
    @Generated
    private ServiceMessageOpenSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient ServiceMessageBoundaryService serviceMessageService;
    @Generated
    public static final String PAGE_URL = "/view/ServiceMessageOpenView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    private final transient CustomerBoundaryService customerService;
    @Generated
    private final transient CountryBoundaryService countryService;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    private final transient SupplierBoundaryService supplierService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.ServiceMessageOpenView";
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
    public ServiceMessageOpenView() {
        this.userSession = null;
        this.serviceMessageService = null;
        this.customerService = null;
        this.countryService = null;
        this.materialService = null;
        this.supplierService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serviceMessageService
     * @param customerService
     * @param countryService
     * @param materialService
     * @param supplierService
     * @param queryManager
     */
    @Inject
    @Generated
    public ServiceMessageOpenView(UserSession userSession, ServiceMessageBoundaryService serviceMessageService,
            CustomerBoundaryService customerService, CountryBoundaryService countryService, MaterialBoundaryService materialService,
            SupplierBoundaryService supplierService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.serviceMessageService = serviceMessageService;
        this.customerService = customerService;
        this.countryService = countryService;
        this.materialService = materialService;
        this.supplierService = supplierService;
        this.queryManager = queryManager;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<ServiceMessageOpenSearchDTO> getServiceMessagesList() {
        return serviceMessagesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public ServiceMessageOpenSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(ServiceMessageOpenSearchDTO selectedObject) {
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
    public void deleteServiceMessage() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            serviceMessageService.deleteServiceMessage(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchServiceMessages();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            serviceMessageService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchServiceMessages();
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
     * Initialize search object
     */
    @Generated
    public void initSearchObject() {
        searchObj = new SearchDTO();
        int colOrderId = -1;

        // Initialize search object
        searchObj.setMaxResult(1000);
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(false);
        searchObj.setCount(false);

        refreshFormatSettings();

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ID, bundle.getString(COL_SERVICEMESSAGEOPENVIEW_ID),
                SearchFieldDataTypeEnum.LONG, 120);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_PLANTCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_PLANTCODE), SearchFieldDataTypeEnum.STRING, 80);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_SERVICEORDERCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_SERVICEORDERCODE), SearchFieldDataTypeEnum.STRING, 120);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_SERIALOBJECTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 120);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_SERVORDERCUSTOMERNAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_SERVORDERCUSTOMERNAME), SearchFieldDataTypeEnum.STRING, 200);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_SERVORDERCUSTCOUNTRYNAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_SERVORDERCUSTCOUNTRYNAME), SearchFieldDataTypeEnum.STRING, 100);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATERIALMATERIALNUMBER,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATERIALMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATERIALSAPNUMBER,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATERIALSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATERIALSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATOWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        final var f11 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATMATERIALTYPECODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATMATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 80);
        f11.setVisible(false);


        final var f12 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATMATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 120);
        f12.setVisible(false);


        final var f13 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATERIALREVISIONREVISIONNUMBER,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATERIALREVISIONREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 100);
        f13.setVisible(false);


        final var f14 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRSTATENAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRSTATENAME), SearchFieldDataTypeEnum.STRING, 120);
        f14.setVisible(false);


        final var f15 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODEGROUPNAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRERRORCODEGROUPNAME), SearchFieldDataTypeEnum.STRING, 150);
        f15.setVisible(false);


        final var f16 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODENAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRERRORCODENAME), SearchFieldDataTypeEnum.STRING, 150);
        f16.setVisible(false);


        final var f17 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODESHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRERRORCODESHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f17.setVisible(false);


        final var f18 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRLOCATIONCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);
        f18.setVisible(false);


        final var f19 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_RMATYPECODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_RMATYPECODE), SearchFieldDataTypeEnum.STRING, 100);
        f19.setVisible(false);


        final var f20 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRSERVICENAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRSERVICENAME), SearchFieldDataTypeEnum.STRING, 100);
        f20.setVisible(false);


        final var f21 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_FAULTANALYSISCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_FAULTANALYSISCODE), SearchFieldDataTypeEnum.STRING, 100);
        f21.setVisible(false);


        final var f22 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_FAULTANALYSISSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_FAULTANALYSISSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f22.setVisible(false);


        final var f23 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRTASKCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRTASKCODE), SearchFieldDataTypeEnum.STRING, 100);
        f23.setVisible(false);


        final var f24 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRTASKSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRTASKSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f24.setVisible(false);


        final var f25 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_EXTERNALSUPPLIERNAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_EXTERNALSUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 200);
        f25.setVisible(false);


        final var f26 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ERRORID,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ERRORID), SearchFieldDataTypeEnum.STRING, 100);
        f26.setVisible(false);


        final var f27 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ORIGIN,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ORIGIN), SearchFieldDataTypeEnum.STRING, 150);
        f27.setVisible(false);


        final var f28 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_DESIGNATOR,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_DESIGNATOR), SearchFieldDataTypeEnum.STRING, 100);
        f28.setVisible(false);


        final var f29 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_DEFECTCOMPONENT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_DEFECTCOMPONENT), SearchFieldDataTypeEnum.STRING, 120);
        f29.setVisible(false);


        final var f30 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_INTERNALREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_INTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f30.setVisible(false);


        final var f31 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_EXTERNALREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_EXTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f31.setVisible(false);


        final var f32 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CUSTOMERREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_CUSTOMERREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f32.setVisible(false);


        final var f33 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CUSTOMERFAILURE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_CUSTOMERFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);
        f33.setVisible(false);


        final var f34 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_BASICSTARTDATE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_BASICSTARTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);
        f34.setVisible(false);


        final var f35 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_BASICFINISHDATE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_BASICFINISHDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);
        f35.setVisible(false);


        final var f36 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_INTERNALARRIVALDATE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_INTERNALARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);
        f36.setVisible(false);


        final var f37 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_EPIDEMICFAILURE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_EPIDEMICFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);
        f37.setVisible(false);


        final var f38 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ANALYSISTEXT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ANALYSISTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f38.setVisible(false);


        final var f39 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_DELIVERYNOTENUMBER,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_DELIVERYNOTENUMBER), SearchFieldDataTypeEnum.STRING, 100);
        f39.setVisible(false);


        final var f40 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_SERVICEMESSAGEID,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_SERVICEMESSAGEID), SearchFieldDataTypeEnum.STRING, 100);
        f40.setVisible(false);


        final var f41 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CAUSETEXT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_CAUSETEXT), SearchFieldDataTypeEnum.STRING, 250);
        f41.setVisible(false);


        final var f42 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRDESCRIPTION,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_REPAIRDESCRIPTION), SearchFieldDataTypeEnum.STRING, 250);
        f42.setVisible(false);


        final var f43 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        f43.setVisible(false);


        final var f44 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_LASTUPDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_LASTUPDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        f44.setVisible(false);


        visibleFields = new DualListModel<>();
        visibleFields.setSource(new ArrayList<>());
        visibleFields.setTarget(new ArrayList<>());

        for (final SearchFieldDTO d : searchObj.getSearchFields())
            if (!d.isVisible())
                visibleFields.getSource().add(d);
            else
                visibleFields.getTarget().add(d);
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY))
            return;


        formTitle = bundle.getString(FORM_SERVICEMESSAGEOPENVIEW_TITLE);

        // Check if previous search exists!
        final SearchDTO lastSearch = queryManager.getLastQuery(userSession.getPrincipal().getId(), VIEW_ID);

        if (lastSearch != null) {
            searchObj = lastSearch;

            prepareAfterLoad();
        }
        else
            initSearchObject();

        fetchServiceMessages();

        logger.debug("View initialization finished");
    }

    /**
     * Perform data fetch operation
     */
    @Generated
    public void fetchServiceMessages() {
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
            serviceMessagesList = serviceMessageService.searchAllServiceMessagesOpen(searchObj);

            if (searchObj.isCount())
                countResult = serviceMessageService.countAllServiceMessagesOpen(searchObj);

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
            countResult = serviceMessageService.countAllServiceMessagesOpen(searchObj);

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
    public List<String> onCompleteCustomerName(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<CustomerListDTO> items = customerService.findCustomers(query + "%");

            for (final CustomerListDTO item : items)
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
        fetchServiceMessages();
    }

}