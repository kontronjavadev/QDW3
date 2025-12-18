package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.*;
import static com.kontron.qdw.ui.UserSession.*;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.SupplierBoundaryService;
import com.kontron.qdw.boundary.material.MaterialBoundaryService;
import com.kontron.qdw.boundary.mv.MaterializedArrivalBoundaryService;
import com.kontron.qdw.dto.base.SupplierListDTO;
import com.kontron.qdw.dto.material.MaterialListDTO;
import com.kontron.qdw.dto.mv.MaterializedArrivalSearchDTO;
import com.kontron.qdw.service.SavedQueryService;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.dialog.ViewArrivalDialog;
import com.kontron.qdw.ui.view.util.SuperView;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import net.sourceforge.jbizmo.commons.search.dto.SearchFieldDTO;
import net.sourceforge.jbizmo.commons.search.dto.SearchFieldDataTypeEnum;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.JSFSearchFieldDTO;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.SearchInputFieldValidationException;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("materializedArrivalView")
@ViewScoped
public class MaterializedArrivalView extends SuperView implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.MaterializedArrivalView";
    public static final String PAGE_URL = "/view/MaterializedArrivalView.jsf?faces-redirect=true";
    private static final long serialVersionUID = 1L;

    private transient ResourceBundle bundle;
    private final UserSession userSession;
    private final transient MaterializedArrivalBoundaryService arrivalService;
    private final transient SupplierBoundaryService supplierService;
    private final transient MaterialBoundaryService materialService;
    private final transient SavedQueryService queryManager;

    private String savedQueryName;
    private String selectedSavedQuery;

    private String formTitle = "";

    private List<MaterializedArrivalSearchDTO> arrivalsList = new ArrayList<>();
    private MaterializedArrivalSearchDTO selectedObject;
    private long countResult;



    public MaterializedArrivalView() {
        this.userSession = null;
        this.arrivalService = null;
        this.supplierService = null;
        this.materialService = null;
        this.queryManager = null;
    }

    @Inject
    public MaterializedArrivalView(UserSession userSession, MaterializedArrivalBoundaryService arrivalService,
            SupplierBoundaryService supplierService,
            MaterialBoundaryService materialService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.arrivalService = arrivalService;
        this.supplierService = supplierService;
        this.materialService = materialService;
        this.queryManager = queryManager;
    }



    public void initView() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return;
        }


        formTitle = bundle.getString(FORM_MATERIALIZEDARRIVALVIEW_TITLE);

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
        fetchArrivals();

        logger.debug("View initialization finished");
    }

    public void initSearchObject() {
        searchObj = new SearchDTO();
        int colOrderId = -1;

        // Initialize search object
        searchObj.setMaxResult(1000);
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setCount(false);

        refreshFormatSettings();

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_ID,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_ID), SearchFieldDataTypeEnum.LONG, 120);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PLANT,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PLANT), SearchFieldDataTypeEnum.STRING, 80);



        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_SERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_SERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PARENTSERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PARENTERIALNUMBER), SearchFieldDataTypeEnum.STRING, 100);



        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_ORDERNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_ORDERNUMBER), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_ARRIVALDATE,
                bundle.getString(LBL_ATTR_ARRIVAL_ARRIVALDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_ASSEMBLYDATE,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_ASSEMBLYDATE), SearchFieldDataTypeEnum.LOCAL_DATE, 80, false);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_ASSEMBLYPO,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_ASSEMBLYPO), SearchFieldDataTypeEnum.STRING, 100);



        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_SUPPLIERNAME,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_SUPPLIERNAME), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_COUNTRYNAME,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_COUNTRYNAME), SearchFieldDataTypeEnum.STRING, 100);



        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_REVISIONNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_REVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PARENTREVISIONNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PARENTREVISIONNUMBER), SearchFieldDataTypeEnum.STRING, 150);



        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_MATERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_MATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_SAPNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_SAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_MATERIALSHORTTEXT,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_MATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_MATERIALHIERARCHY,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_MATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_MATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);



        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PARENTMATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PARENTSAPNUMBER,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PARENTSAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALSHORTTEXT,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PARENTMATERIALSHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALHIERARCHY,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PARENTMATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALTYPE,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_PARENTMATERIALTYPE), SearchFieldDataTypeEnum.STRING, 80);



        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterializedArrivalSearchDTO.SELECT_MOVEMENTTYPE,
                bundle.getString(COL_MATERIALIZEDARRIVALVIEW_MOVEMENTTYPE), SearchFieldDataTypeEnum.STRING, 80);


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



    public void fetchArrivals() {
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
            arrivalsList = arrivalService.searchAllArrivals(searchObj);

            if (searchObj.isCount()) {
                countResult = arrivalService.countAllArrivals(searchObj);
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
            countResult = arrivalService.countAllArrivals(searchObj);

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

    public void deleteArrival() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getId());

            arrivalService.delete(selectedObject.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchArrivals();
    }

    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getId());

            arrivalService.copy(selectedObject.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchArrivals();
        return "";
    }



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

    public void deleteSavedQuery() {
        if (selectedSavedQuery == null) {
            return;
        }

        logger.debug("Delete saved query");

        queryManager.deleteSavedQuery(userSession.getPrincipal().getId(), VIEW_ID, selectedSavedQuery);

        MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, SAVED_QUERY_DELETE_SUCCESS, "", selectedSavedQuery);
        selectedSavedQuery = null;
    }

    public void runSavedQuery() {
        if (selectedSavedQuery == null) {
            return;
        }

        logger.debug("Run saved query");

        searchObj = queryManager.getSavedQuery(userSession.getPrincipal().getId(), VIEW_ID, selectedSavedQuery);

        prepareAfterLoad();
        fetchArrivals();
    }



    @Override
    public void resetSearchObject() {
        initSearchObject();
        fetchArrivals();
    }

    public void refreshFormatSettings() {
        searchObj.setDateFormat(userSession.getDateFormat());
        searchObj.setDateTimeFormat(userSession.getDateTimeFormat());
        searchObj.setNumberFormat(userSession.getNumberFormat());
        searchObj.setDecimalSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getDecimalSeparator());
        searchObj.setGroupingSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getGroupingSeparator());
    }



    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewArrivalDialog());
    }

    public String openViewArrivalDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            url = ViewArrivalDialog.PAGE_INIT_URL + selectedObject.getId();
        }

        return url;
    }



    public List<String> onCompleteSupplierName(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<SupplierListDTO> items = supplierService.findSuppliers(query + "%");

            for (final SupplierListDTO item : items) {
                results.add(item.getName());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", query, e);
        }

        return results;
    }

    public List<String> onCompleteMaterialMaterialNumber(String query) {
        final var results = new ArrayList<String>();

        try {
            final Collection<MaterialListDTO> items = materialService.findMaterials(query + "%");

            for (final MaterialListDTO item : items) {
                results.add(item.getMaterialNumber());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", query, e);
        }

        return results;
    }



    @Override
    protected String getViewName() {
        return VIEW_ID;
    }

    public String getCurrentPageURL() {
        return PAGE_URL;
    }

    public Collection<MaterializedArrivalSearchDTO> getArrivalsList() {
        return arrivalsList;
    }

    public MaterializedArrivalSearchDTO getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(MaterializedArrivalSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public long getCountResult() {
        return countResult;
    }

    public String getSavedQueryName() {
        return savedQueryName;
    }

    public void setSavedQueryName(String savedQueryName) {
        this.savedQueryName = savedQueryName;
    }

    @Override
    public String getSelectedSavedQuery() {
        return selectedSavedQuery;
    }

    public void setSelectedSavedQuery(String selectedSavedQuery) {
        this.selectedSavedQuery = selectedSavedQuery;
    }

}
