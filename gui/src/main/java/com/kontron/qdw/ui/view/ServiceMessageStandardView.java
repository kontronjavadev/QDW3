package com.kontron.qdw.ui.view;

import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
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
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.service.*;
import com.kontron.qdw.dto.material.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Named("serviceMessageStandardView")
@ViewScoped
public class ServiceMessageStandardView extends AbstractSearchableView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<ServiceMessageStandardSearchDTO> serviceMessagesList = new ArrayList<>();
    @Generated
    private ServiceMessageStandardSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient ServiceMessageBoundaryService serviceMessageService;
    @Generated
    public static final String PAGE_URL = "/view/ServiceMessageStandardView.jsf?faces-redirect=true";
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
    private final transient RepairStateBoundaryService repairStateService;
    @Generated
    private final transient RepairErrorCodeBoundaryService repairErrorCodeService;
    @Generated
    private final transient RepairServiceBoundaryService repairServiceService;
    @Generated
    private final transient SupplierBoundaryService supplierService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.ServiceMessageStandardView";
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
    public ServiceMessageStandardView() {
        this.userSession = null;
        this.serviceMessageService = null;
        this.customerService = null;
        this.countryService = null;
        this.materialService = null;
        this.repairStateService = null;
        this.repairErrorCodeService = null;
        this.repairServiceService = null;
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
     * @param repairStateService
     * @param repairErrorCodeService
     * @param repairServiceService
     * @param supplierService
     * @param queryManager
     */
    @Inject
    @Generated
    public ServiceMessageStandardView(UserSession userSession, ServiceMessageBoundaryService serviceMessageService,
            CustomerBoundaryService customerService, CountryBoundaryService countryService, MaterialBoundaryService materialService,
            RepairStateBoundaryService repairStateService, RepairErrorCodeBoundaryService repairErrorCodeService,
            RepairServiceBoundaryService repairServiceService, SupplierBoundaryService supplierService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.serviceMessageService = serviceMessageService;
        this.customerService = customerService;
        this.countryService = countryService;
        this.materialService = materialService;
        this.repairStateService = repairStateService;
        this.repairErrorCodeService = repairErrorCodeService;
        this.repairServiceService = repairServiceService;
        this.supplierService = supplierService;
        this.queryManager = queryManager;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<ServiceMessageStandardSearchDTO> getServiceMessagesList() {
        return serviceMessagesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public ServiceMessageStandardSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(ServiceMessageStandardSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewServiceMessageDialog());
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
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewServiceMessageDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
            url = ViewServiceMessageDialog.PAGE_INIT_URL + selectedObject.getId();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_ID, bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_ID),
                SearchFieldDataTypeEnum.LONG, 120);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_SERVICEORDERCODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_SERVICEORDERCODE), SearchFieldDataTypeEnum.STRING, 100);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_PLANTCODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_PLANTCODE), SearchFieldDataTypeEnum.STRING, 80);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_SERIALOBJECTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_SERVORDCUSTOMERNAME,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_SERVORDCUSTOMERNAME), SearchFieldDataTypeEnum.STRING, 200);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_SERVORDCUSTCOUNTRYNAME,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_SERVORDCUSTCOUNTRYNAME), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_MATREVMATMATERIALNUMBER,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_MATREVMATMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_MATREVMATSAPNUMBER,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_MATREVMATSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_MATREVMATSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_MATREVMATSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_MATREVMATOWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        final var f11 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_MATREVMATMATERIALTYPECODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_MATREVMATMATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 80);
        f11.setVisible(false);


        final var f12 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_MATREVMATMATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 100);
        f12.setVisible(false);


        final var f13 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_MATREVREVISIONNUMBER,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_MATREVREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        f13.setVisible(false);


        final var f14 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_SERVICEORDERCOMMENT,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_SERVICEORDERCOMMENT), SearchFieldDataTypeEnum.STRING, 250);
        f14.setVisible(false);


        final var f15 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRSTATENAME,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRSTATENAME), SearchFieldDataTypeEnum.STRING, 120);
        f15.setVisible(false);


        final var f16 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRERRORCODEGROUPNAME,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRERRORCODEGROUPNAME), SearchFieldDataTypeEnum.STRING, 150);
        f16.setVisible(false);


        final var f17 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRERRORCODENAME,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRERRORCODENAME), SearchFieldDataTypeEnum.STRING, 150);
        f17.setVisible(false);


        final var f18 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRERRORCODESHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRERRORCODESHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f18.setVisible(false);


        final var f19 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRLOCATIONCODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);
        f19.setVisible(false);


        final var f20 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_RMATYPECODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_RMATYPECODE), SearchFieldDataTypeEnum.STRING, 100);
        f20.setVisible(false);


        final var f21 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRSERVICENAME,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRSERVICENAME), SearchFieldDataTypeEnum.STRING, 100);
        f21.setVisible(false);


        final var f22 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_FAULTANALYSISCODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_FAULTANALYSISCODE), SearchFieldDataTypeEnum.STRING, 120);
        f22.setVisible(false);


        final var f23 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_FAULTANALYSISSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_FAULTANALYSISSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f23.setVisible(false);


        final var f24 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRTASKCODE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRTASKCODE), SearchFieldDataTypeEnum.STRING, 120);
        f24.setVisible(false);


        final var f25 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRTASKSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_REPAIRTASKSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f25.setVisible(false);


        final var f26 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_EXTERNALSUPPLIERNAME,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_EXTERNALSUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 200);
        f26.setVisible(false);


        final var f27 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_ERRORID,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ERRORID), SearchFieldDataTypeEnum.STRING, 100);
        f27.setVisible(false);


        final var f28 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_ORIGIN,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ORIGIN), SearchFieldDataTypeEnum.STRING, 150);
        f28.setVisible(false);


        final var f29 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_DESIGNATOR,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_DESIGNATOR), SearchFieldDataTypeEnum.STRING, 100);
        f29.setVisible(false);


        final var f30 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_DEFECTCOMPONENT,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_DEFECTCOMPONENT), SearchFieldDataTypeEnum.STRING, 120);
        f30.setVisible(false);


        final var f31 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_INTERNALREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_INTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f31.setVisible(false);


        final var f32 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_EXTERNALREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_EXTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f32.setVisible(false);


        final var f33 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_CUSTOMERREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_CUSTOMERREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f33.setVisible(false);


        final var f34 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_CUSTOMERFAILURE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_CUSTOMERFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);
        f34.setVisible(false);


        final var f35 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_BASICSTARTDATE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_BASICSTARTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);
        f35.setVisible(false);


        final var f36 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_BASICFINISHDATE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_BASICFINISHDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);
        f36.setVisible(false);


        final var f37 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_INTERNALARRIVALDATE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_INTERNALARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);
        f37.setVisible(false);


        final var f38 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_INTERNALSHIPMENTDATE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_INTERNALSHIPMENTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);
        f38.setVisible(false);


        final var f39 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_EPIDEMICFAILURE,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_EPIDEMICFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);
        f39.setVisible(false);


        final var f40 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_ANALYSISTEXT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ANALYSISTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f40.setVisible(false);


        final var f41 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_DELIVERYNOTENUMBER,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_DELIVERYNOTENUMBER), SearchFieldDataTypeEnum.STRING, 100);
        f41.setVisible(false);


        final var f42 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_SERVICEMESSAGEID,
                bundle.getString(COL_SERVICEMESSAGESTANDARDVIEW_SERVICEMESSAGEID), SearchFieldDataTypeEnum.STRING, 100);
        f42.setVisible(false);


        final var f43 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_CAUSETEXT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_CAUSETEXT), SearchFieldDataTypeEnum.STRING, 250);
        f43.setVisible(false);


        final var f44 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_REPAIRDESCRIPTION,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_REPAIRDESCRIPTION), SearchFieldDataTypeEnum.STRING, 250);
        f44.setVisible(false);


        final var f45 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        f45.setVisible(false);


        final var f46 = new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageStandardSearchDTO.SELECT_LASTUPDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_LASTUPDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        f46.setVisible(false);


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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER))
            return;


        formTitle = bundle.getString(FORM_SERVICEMESSAGESTANDARDVIEW_TITLE);

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
            serviceMessagesList = serviceMessageService.searchAllServiceMessagesStandard(searchObj);

            if (searchObj.isCount())
                countResult = serviceMessageService.countAllServiceMessagesStandard(searchObj);

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
            countResult = serviceMessageService.countAllServiceMessagesStandard(searchObj);

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
    public List<String> onCompleteRepairStateName(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<RepairStateListDTO> items = repairStateService.findRepairStates(query + "%");

            for (final RepairStateListDTO item : items)
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
    public List<String> onCompleteRepairErrorCodeName(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<RepairErrorCodeListDTO> items = repairErrorCodeService.findRepairErrorCodes(query + "%");

            for (final RepairErrorCodeListDTO item : items)
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
    public List<String> onCompleteRepairServiceName(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<RepairServiceListDTO> items = repairServiceService.findRepairServices(query + "%");

            for (final RepairServiceListDTO item : items)
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