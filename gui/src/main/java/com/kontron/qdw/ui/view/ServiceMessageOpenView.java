package com.kontron.qdw.ui.view;

import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
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
import com.kontron.qdw.ui.view.util.OnCompleteHelper;
import com.kontron.qdw.ui.view.util.SuperView;

import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.service.*;

import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Named("serviceMessageOpenView")
@ViewScoped
public class ServiceMessageOpenView extends SuperView implements Serializable {
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
    private final transient RepairStateBoundaryService repairStateService;
    @Generated
    private final transient RepairErrorCodeBoundaryService repairErrorCodeService;
    @Generated
    private final transient RepairServiceBoundaryService repairServiceService;
    @Generated
    private final transient SupplierBoundaryService supplierService;
    @Generated
    private final transient FaultAnalysisBoundaryService faultAnalysisService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.ServiceMessageOpenView";
    @Generated
    private final transient SavedQueryService queryManager;
    @Generated
    private String savedQueryName;
    @Generated
    private String selectedSavedQuery;



    @Generated
    public ServiceMessageOpenView() {
        userSession = null;
        serviceMessageService = null;
        customerService = null;
        countryService = null;
        materialService = null;
        repairStateService = null;
        repairErrorCodeService = null;
        repairServiceService = null;
        supplierService = null;
        queryManager = null;
        faultAnalysisService = null;
    }

    @Inject
    @Generated
    public ServiceMessageOpenView(UserSession userSession, ServiceMessageBoundaryService serviceMessageService,
            CustomerBoundaryService customerService, CountryBoundaryService countryService, MaterialBoundaryService materialService,
            RepairStateBoundaryService repairStateService, RepairErrorCodeBoundaryService repairErrorCodeService,
            RepairServiceBoundaryService repairServiceService, SupplierBoundaryService supplierService,
            FaultAnalysisBoundaryService faultAnalysisService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.serviceMessageService = serviceMessageService;
        this.customerService = customerService;
        this.countryService = countryService;
        this.materialService = materialService;
        this.repairStateService = repairStateService;
        this.repairErrorCodeService = repairErrorCodeService;
        this.repairServiceService = repairServiceService;
        this.supplierService = supplierService;
        this.faultAnalysisService = faultAnalysisService;
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


        formTitle = bundle.getString(FORM_SERVICEMESSAGEOPENVIEW_TITLE);

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
        fetchServiceMessages();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ID,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_ID), SearchFieldDataTypeEnum.LONG, 120);

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATMATERIALTYPECODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATMATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATREVMATMATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_MATERIALREVISIONREVISIONNUMBER,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_MATERIALREVISIONREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRSTATENAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRSTATENAME), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODEGROUPNAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRERRORCODEGROUPNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODENAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRERRORCODENAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRERRORCODESHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRERRORCODESHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRLOCATIONCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_RMATYPECODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_RMATYPECODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRSERVICENAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRSERVICENAME), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_FAULTANALYSISCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_FAULTANALYSISCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_FAULTANALYSISSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_FAULTANALYSISSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRTASKCODE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRTASKCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRTASKSHORTTEXT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_REPAIRTASKSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_EXTERNALSUPPLIERNAME,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_EXTERNALSUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 200);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ERRORID,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ERRORID), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ORIGIN,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ORIGIN), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_DESIGNATOR,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_DESIGNATOR), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_DEFECTCOMPONENT,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_DEFECTCOMPONENT), SearchFieldDataTypeEnum.STRING, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_INTERNALREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_INTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_EXTERNALREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_EXTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CUSTOMERREPORT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_CUSTOMERREPORT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CUSTOMERFAILURE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_CUSTOMERFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_BASICSTARTDATE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_BASICSTARTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_BASICFINISHDATE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_BASICFINISHDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_INTERNALARRIVALDATE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_INTERNALARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_EPIDEMICFAILURE,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_EPIDEMICFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_ANALYSISTEXT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_ANALYSISTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_DELIVERYNOTENUMBER,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_DELIVERYNOTENUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_SERVICEMESSAGEID,
                bundle.getString(COL_SERVICEMESSAGEOPENVIEW_SERVICEMESSAGEID), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CAUSETEXT,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_CAUSETEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_REPAIRDESCRIPTION,
                bundle.getString(LBL_ATTR_SERVICEMESSAGE_REPAIRDESCRIPTION), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, ServiceMessageOpenSearchDTO.SELECT_LASTUPDATE,
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
        setCountFilterDependent(2);

        try {
            serviceMessagesList = serviceMessageService.searchAllServiceMessagesOpen(searchObj);

            if (searchObj.isCount()) {
                if (serviceMessagesList.size() == searchObj.getMaxResult()) {
                    countResult = serviceMessageService.countAllServiceMessagesOpen(searchObj);
                }
                else {
                    countResult = serviceMessagesList.size();
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
        fetchServiceMessages();
    }



    @Customized
    public List<String> onCompleteMaterialMaterialNumber(String searchText) {
        return OnCompleteHelper.onCompleteMaterialNumber(materialService, searchText);
    }

    public List<String> onCompleteSapNumber(String searchText) {
        return OnCompleteHelper.onCompleteSapNumber(materialService, searchText);
    }

    public List<String> onCompleteSymptomCode(String searchText) {
        return OnCompleteHelper.onCompleteSymptomCode(faultAnalysisService, searchText);
    }

    @Customized
    public List<String> onCompleteSupplierName(String searchText) {
        return OnCompleteHelper.onCompleteSupplierName(supplierService, searchText);
    }

    @Customized
    public List<String> onCompleteCustomerName(String searchText) {
        return OnCompleteHelper.onCompleteCustomerName(customerService, searchText);
    }

    @Customized
    public List<String> onCompleteCountryName(String searchText) {
        return OnCompleteHelper.onCompleteCountryName(countryService, searchText);
    }

    @Customized
    public List<String> onCompleteRepairStateName(String searchText) {
        return OnCompleteHelper.onCompleteRepairStateName(repairStateService, searchText);
    }

    @Customized
    public List<String> onCompleteRepairErrorCodeName(String searchText) {
        return OnCompleteHelper.onCompleteRepairErrorCodeName(repairErrorCodeService, searchText);
    }

    @Generated
    public List<String> onCompleteRepairServiceName(String searchText) {
        final var results = new ArrayList<String>();

        try {
            final Collection<RepairServiceListDTO> items = repairServiceService.findRepairServices(searchText + "%");

            for (final RepairServiceListDTO item : items) {
                results.add(item.getName());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", searchText, e);
        }

        return results;
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

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            url = ViewServiceMessageDialog.PAGE_INIT_URL + selectedObject.getId();
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
        fetchServiceMessages();
    }

}
