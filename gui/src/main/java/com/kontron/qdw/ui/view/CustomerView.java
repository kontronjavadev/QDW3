package com.kontron.qdw.ui.view;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import java.lang.invoke.*;
import org.primefaces.model.DualListModel;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.SuperView;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.dto.base.*;
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

@Named("customerView")
@ViewScoped
public class CustomerView extends SuperView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<CustomerSearchDTO> customersList = new ArrayList<>();
    @Generated
    private CustomerSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient CustomerBoundaryService customerService;
    @Generated
    public static final String PAGE_URL = "/view/CustomerView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    private final transient CountryBoundaryService countryService;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.CustomerView";
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
    public CustomerView() {
        this.userSession = null;
        this.customerService = null;
        this.countryService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param customerService
     * @param countryService
     * @param queryManager
     */
    @Inject
    @Generated
    public CustomerView(UserSession userSession, CustomerBoundaryService customerService, CountryBoundaryService countryService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.customerService = customerService;
        this.countryService = countryService;
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR)) {
            return;
        }


        formTitle = bundle.getString(FORM_CUSTOMERVIEW_TITLE);

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
        fetchCustomers();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_CODE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_CODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_NAME,
                bundle.getString(LBL_ATTR_CUSTOMER_NAME), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_STREET,
                bundle.getString(LBL_ATTR_CUSTOMER_STREET), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_ZIPCODE,
                bundle.getString(LBL_ATTR_CUSTOMER_ZIPCODE), SearchFieldDataTypeEnum.STRING, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_CITY,
                bundle.getString(LBL_ATTR_CUSTOMER_CITY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_COUNTRYNAME,
                bundle.getString(COL_CUSTOMERVIEW_COUNTRYNAME), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_INTERNAL,
                bundle.getString(LBL_ATTR_CUSTOMER_INTERNAL), SearchFieldDataTypeEnum.BOOLEAN, 70);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_SHORTTEXT,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_SHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_COMMENT,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_COMMENT), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, CustomerSearchDTO.SELECT_LASTUPDATE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_LASTUPDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);

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
    public void fetchCustomers() {
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
            customersList = customerService.searchAllCustomers(searchObj);

            if (searchObj.isCount()) {
                if (customersList.size() == searchObj.getMaxResult()) {
                    countResult = customerService.countAllCustomers(searchObj);
                }
                else {
                    countResult = customersList.size();
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
        fetchCustomers();
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<CustomerSearchDTO> getCustomersList() {
        return customersList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public CustomerSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(CustomerSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openEditCustomerDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteCustomer() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getCode());

            customerService.deleteCustomer(selectedObject.getCode());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchCustomers();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        var url = "";
        String newId;

        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getCode());

            newId = customerService.copy(selectedObject.getCode(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditCustomerDialog.PAGE_INIT_URL + java.net.URLEncoder.encode(newId, java.nio.charset.StandardCharsets.UTF_8);

        userSession.setLastPage(getCurrentPageURL());
        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openCreateNewCustomerDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = CreateNewCustomerDialog.PAGE_INIT_URL;

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openEditCustomerDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditCustomerDialog.PAGE_INIT_URL + java.net.URLEncoder.encode(selectedObject.getCode(), java.nio.charset.StandardCharsets.UTF_8);

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
            countResult = customerService.countAllCustomers(searchObj);

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
        fetchCustomers();
    }

}