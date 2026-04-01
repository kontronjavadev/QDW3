package com.kontron.qdw.ui.view;

import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import com.kontron.qdw.domain.mv.*;
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
import com.kontron.qdw.service.*;
import com.kontron.qdw.dto.material.*;
import jakarta.faces.model.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("materializedServiceMessageView")
@ViewScoped
public class MaterializedServiceMessageView extends AbstractSearchableView implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<MaterializedServiceMessageSearchDTO> materializedServiceMessagesList = new ArrayList<>();
    @Generated
    private MaterializedServiceMessageSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterializedServiceMessageBoundaryService materializedServiceMessageService;
    @Generated
    public static final String PAGE_URL = "/view/MaterializedServiceMessageView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";
    @Generated
    private long countResult;
    @Generated
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.MaterializedServiceMessageView";
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
    public MaterializedServiceMessageView() {
        this.userSession = null;
        this.materializedServiceMessageService = null;
        this.queryManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param materializedServiceMessageService
     * @param queryManager
     */
    @Inject
    @Generated
    public MaterializedServiceMessageView(UserSession userSession, MaterializedServiceMessageBoundaryService materializedServiceMessageService,
            SavedQueryService queryManager) {
        this.userSession = userSession;
        this.materializedServiceMessageService = materializedServiceMessageService;
        this.queryManager = queryManager;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<MaterializedServiceMessageSearchDTO> getMaterializedServiceMessagesList() {
        return materializedServiceMessagesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public MaterializedServiceMessageSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(MaterializedServiceMessageSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Perform translation of given enumeration literal
     * @param item
     * @return the translation based on the user's locale
     */
    @Generated
    public String translateServiceOrderType(ServiceOrderType item) {
        return bundle.getString("serviceordertype_" + item.name().toLowerCase());
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
    public void deleteMaterializedServiceMessage() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            materializedServiceMessageService.deleteMaterializedServiceMessage(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchMaterializedServiceMessages();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            materializedServiceMessageService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchMaterializedServiceMessages();
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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ID,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_ID), SearchFieldDataTypeEnum.LONG, 120);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PLANT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_PLANT), SearchFieldDataTypeEnum.STRING, 80);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SERVICEORDER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_SERVICEORDER), SearchFieldDataTypeEnum.STRING, 120);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SERVICEORDERTYPE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_SERVICEORDERTYPE), SearchFieldDataTypeEnum.ENUM, 80);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_COUNTRYNAME,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_COUNTRYNAME), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERNAME,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_CUSTOMERNAME), SearchFieldDataTypeEnum.STRING, 200);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_EXTERNALSUPPLIERNAME,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_EXTERNALSUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 150);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERNAME,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_SUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 200);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_SERIALNUMBER), SearchFieldDataTypeEnum.STRING, 120);
        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PARENTSERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_PARENTSERIALNUMBER), SearchFieldDataTypeEnum.STRING, 120);

        final var f11 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_MATERIALNUMBER,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_MATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        f11.setVisible(false);


        final var f12 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SAPNUMBER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_SAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        f12.setVisible(false);


        final var f13 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_MATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_MATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);
        f13.setVisible(false);


        final var f14 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_MATERIALSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_MATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f14.setVisible(false);


        final var f15 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_MATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);
        f15.setVisible(false);


        final var f16 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_REVISIONNUMBER,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_REVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 100);
        f16.setVisible(false);


        final var f17 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_OWNERLOCATION,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_OWNERLOCATION), SearchFieldDataTypeEnum.STRING, 100);
        f17.setVisible(false);


        final var f18 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_PARENTMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        f18.setVisible(false);


        final var f19 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PARENTSAPNUMBER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_PARENTSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);
        f19.setVisible(false);


        final var f20 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_PARENTMATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);
        f20.setVisible(false);


        final var f21 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDENTITIY_PARENTMATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f21.setVisible(false);


        final var f22 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALHIERARCHY,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_PARENTMATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);
        f22.setVisible(false);


        final var f23 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_PARENTREVISIONNUMBER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_PARENTREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 100);
        f23.setVisible(false);


        final var f24 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_RMATYPE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_RMATYPE), SearchFieldDataTypeEnum.STRING, 80);
        f24.setVisible(false);


        final var f25 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_FAULTANALYSISCODE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_FAULTANALYSISCODE), SearchFieldDataTypeEnum.STRING, 120);
        f25.setVisible(false);


        final var f26 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SYMPTOMSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_SYMPTOMSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f26.setVisible(false);


        final var f27 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SERVICENAME,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_SERVICENAME), SearchFieldDataTypeEnum.STRING, 100);
        f27.setVisible(false);


        final var f28 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ERRORCODEGROUP,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_ERRORCODEGROUP), SearchFieldDataTypeEnum.STRING, 150);
        f28.setVisible(false);


        final var f29 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ERRORCODENAME,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_ERRORCODENAME), SearchFieldDataTypeEnum.STRING, 150);
        f29.setVisible(false);


        final var f30 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ERRORSHORTTEXT,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_ERRORSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f30.setVisible(false);


        final var f31 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_TASKNAME,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_TASKNAME), SearchFieldDataTypeEnum.STRING, 120);
        f31.setVisible(false);


        final var f32 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_REPAIRTASKSHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_REPAIRTASKSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f32.setVisible(false);


        final var f33 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_STATENAME,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_STATENAME), SearchFieldDataTypeEnum.STRING, 120);
        f33.setVisible(false);


        final var f34 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_INTERNALARRIVALDATE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_INTERNALARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);
        f34.setVisible(false);


        final var f35 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_INTERNALSHIPMENTDATE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_INTERNALSHIPMENTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);
        f35.setVisible(false);


        final var f36 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_BASICSTARTDATE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_BASICSTARTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);
        f36.setVisible(false);


        final var f37 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_BASICENDDATE,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_BASICENDDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);
        f37.setVisible(false);


        final var f38 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_DESIGNATOR,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_DESIGNATOR), SearchFieldDataTypeEnum.STRING, 100);
        f38.setVisible(false);


        final var f39 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_DEFECTCOMPONENT,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_DEFECTCOMPONENT), SearchFieldDataTypeEnum.STRING, 120);
        f39.setVisible(false);


        final var f40 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ANALYSISTEXT,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_ANALYSISTEXT), SearchFieldDataTypeEnum.STRING, 250);
        f40.setVisible(false);


        final var f41 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_INTERNALREPORT,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_INTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f41.setVisible(false);


        final var f42 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_EXTERNALREPORT,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_EXTERNALREPORT), SearchFieldDataTypeEnum.STRING, 250);
        f42.setVisible(false);


        final var f43 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERREPORT,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_CUSTOMERREPORT), SearchFieldDataTypeEnum.STRING, 150);
        f43.setVisible(false);


        final var f44 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_EPIDEMICFAILURE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_EPIDEMICFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);
        f44.setVisible(false);


        final var f45 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ERRORID,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_ERRORID), SearchFieldDataTypeEnum.STRING, 100);
        f45.setVisible(false);


        final var f46 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ORIGIN,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_ORIGIN), SearchFieldDataTypeEnum.STRING, 100);
        f46.setVisible(false);


        final var f47 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERFAILURE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_CUSTOMERFAILURE), SearchFieldDataTypeEnum.BOOLEAN, 80);
        f47.setVisible(false);


        final var f48 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERGROUP,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_CUSTOMERGROUP), SearchFieldDataTypeEnum.STRING, 120);
        f48.setVisible(false);


        final var f49 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERARRIVALDATE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_SUPPLIERARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 100, false);
        f49.setVisible(false);


        final var f50 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERSHIPMENTDATE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_CUSTOMERSHIPMENTDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 110, false);
        f50.setVisible(false);


        final var f51 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_DELIVERYNOTENUMBER,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_DELIVERYNOTENUMBER), SearchFieldDataTypeEnum.STRING, 100);
        f51.setVisible(false);


        final var f52 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_REPAIRDESCRIPTION,
                bundle.getString(LBL_ATTR_MATERIALIZEDSERVICEMESSAGE_REPAIRDESCRIPTION), SearchFieldDataTypeEnum.STRING, 150);
        f52.setVisible(false);


        final var f53 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ASSEMBLYDATE,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_ASSEMBLYDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);
        f53.setVisible(false);


        final var f54 = new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedServiceMessageSearchDTO.SELECT_ASSEMBLYPO,
                bundle.getString(COL_MATERIALIZEDSERVICEMESSAGEVIEW_ASSEMBLYPO), SearchFieldDataTypeEnum.STRING, 100);
        f54.setVisible(false);


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


        formTitle = bundle.getString(FORM_MATERIALIZEDSERVICEMESSAGEVIEW_TITLE);

        // Check if previous search exists!
        final SearchDTO lastSearch = queryManager.getLastQuery(userSession.getPrincipal().getId(), VIEW_ID);

        if (lastSearch != null) {
            searchObj = lastSearch;

            prepareAfterLoad();
        }
        else
            initSearchObject();

        fetchMaterializedServiceMessages();

        logger.debug("View initialization finished");
    }

    /**
     * Perform data fetch operation
     */
    @Generated
    public void fetchMaterializedServiceMessages() {
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
            materializedServiceMessagesList = materializedServiceMessageService.searchAllMaterializedServiceMessages(searchObj);

            if (searchObj.isCount())
                countResult = materializedServiceMessageService.countAllMaterializedServiceMessages(searchObj);

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
            countResult = materializedServiceMessageService.countAllMaterializedServiceMessages(searchObj);

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
        fetchMaterializedServiceMessages();
    }

}