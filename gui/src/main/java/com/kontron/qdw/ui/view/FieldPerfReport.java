package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.*;
import static com.kontron.qdw.ui.UserSession.*;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.material.MaterialBoundaryService;
import com.kontron.qdw.dto.material.MaterialListDTO;
import com.kontron.qdw.dto.material.MaterialSearchDTO;
import com.kontron.qdw.service.SavedQueryService;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.dialog.EditMaterialDialog;
import com.kontron.qdw.ui.dialog.ViewMaterialDialog;
import com.kontron.qdw.ui.view.util.SuperView;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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

@Named("fieldPerfReport")
@ViewScoped
public class FieldPerfReport extends SuperView implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final String PAGE_URL = "/view/FieldPerfReport.jsf?faces-redirect=true";
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.FieldPerfReport";

    private final transient MaterialBoundaryService materialService;
    private final transient SavedQueryService queryManager;

    private final UserSession userSession;
    private transient ResourceBundle bundle;
    private String formTitle = "";
    private long countResult;

    private String savedQueryName;
    private String selectedSavedQuery;

    private List<MaterialSearchDTO> materialsList = new ArrayList<>();
    private MaterialSearchDTO selectedObject;

    private List<String> matNrFilterList = new ArrayList<>();



    public FieldPerfReport() {
        this.userSession = null;
        this.materialService = null;
        this.queryManager = null;
    }

    @Inject
    public FieldPerfReport(UserSession userSession, MaterialBoundaryService materialService, SavedQueryService queryManager) {
        this.userSession = userSession;
        this.materialService = materialService;
        this.queryManager = queryManager;
    }

    public void initView() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_SUPERUSER)) {
            return;
        }


        formTitle = bundle.getString(FORM_MATERIALVIEW_TITLE);

        // Check if previous search exists!
        final SearchDTO lastSearch = queryManager.getLastQuery(userSession.getPrincipal().getId(), VIEW_ID);

        if (searchObj == null) {
            if (lastSearch != null) {
                searchObj = lastSearch;

                prepareAfterLoad();
            }
            else {
                initSearchObject();
            }
        }

        initProperties();
        fetchMaterials();

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

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_MATERIALNUMBER,
                bundle.getString(COL_MATERIALVIEW_MATERIALNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_SAPNUMBER,
                bundle.getString(COL_MATERIALVIEW_SAPNUMBER), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_MATERIALTYPECODE,
                bundle.getString(COL_MATERIALVIEW_MATERIALTYPECODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_MATERIALCLASSCODE,
                bundle.getString(COL_MATERIALVIEW_MATERIALCLASSCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_MATERIALHIERARCHY,
                bundle.getString(LBL_ATTR_MATERIAL_MATERIALHIERARCHY), SearchFieldDataTypeEnum.STRING, 150);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_SHORTTEXT,
                bundle.getString(LBL_ATTR_MATERIAL_SHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_OWNERLOCATIONCODE,
                bundle.getString(COL_MATERIALVIEW_OWNERLOCATIONCODE), SearchFieldDataTypeEnum.STRING, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_FITVALUE,
                bundle.getString(LBL_ATTR_MATERIAL_FITVALUE), SearchFieldDataTypeEnum.DOUBLE, 80);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_SEARCHSUBASSEMBLIES,
                bundle.getString(COL_MATERIALVIEW_SEARCHSUBASSEMBLIES), SearchFieldDataTypeEnum.BOOLEAN, 100);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_COMMENT,
                bundle.getString(LBL_ATTR_MATERIAL_COMMENT), SearchFieldDataTypeEnum.STRING, 250);

        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTENTITYWITHID_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);


        new JSFSearchFieldDTO(searchObj, ++colOrderId, MaterialSearchDTO.SELECT_LASTUPDATE,
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



    public void fetchMaterials() {
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
            materialsList = materialService.searchAllMaterials(searchObj);

            if (searchObj.isCount()) {
                if (materialsList.size() == searchObj.getMaxResult()) {
                    countResult = materialService.countAllMaterials(searchObj);
                }
                else {
                    countResult = materialsList.size();
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
    public void resetSearchObject() {
        initSearchObject();
        fetchMaterials();
    }



    public void onClick() {
    }

    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        if (userSession.redirectTo(getCurrentPageURL(), openEditMaterialDialog())) {
            return;
        }

        userSession.redirectTo(getCurrentPageURL(), openViewMaterialDialog());
    }

    public String openViewMaterialDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_SUPERUSER)) {
            url = ViewMaterialDialog.PAGE_INIT_URL + selectedObject.getId();
        }

        return url;
    }

    public String openEditMaterialDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_SUPERUSER)) {
            url = EditMaterialDialog.PAGE_INIT_URL + selectedObject.getId();
        }

        return url;
    }



    public void refreshFormatSettings() {
        searchObj.setDateFormat(userSession.getDateFormat());
        searchObj.setDateTimeFormat(userSession.getDateTimeFormat());
        searchObj.setNumberFormat(userSession.getNumberFormat());
        searchObj.setDecimalSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getDecimalSeparator());
        searchObj.setGroupingSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getGroupingSeparator());
    }



    /** Diese Methode wird vom JavaScript via p:remoteCommand aufgerufen */
    public void handlePastedMaterials() {
        final var requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String pastedTokensString = requestParams.get("pastedTokens");

        if (pastedTokensString != null && !pastedTokensString.isBlank()) {
            // Die aus dem JS übergebenen Tokens wieder aufsplitten
            final List<String> incomingTokens = Arrays.asList(pastedTokensString.split(";;"));

            // 1. Bulk-Validierung gegen die Boundary (vermeidet N+1 Selects)
            final List<String> existingNumbers = materialService.findExistingMaterialNumbers(incomingTokens);

            // 2. Gültige Nummern der UI-Liste hinzufügen (falls noch nicht vorhanden)
            for (final String validNum : existingNumbers) {
                if (!matNrFilterList.contains(validNum)) {
                    matNrFilterList.add(validNum);
                }
            }

            // 3. Ungültige Nummern ermitteln, um dem Anwender Feedback zu geben
            final List<String> invalidNumbers = new ArrayList<>(incomingTokens);
            invalidNumbers.removeAll(existingNumbers);

            if (!invalidNumbers.isEmpty()) {
                final FacesMessage msg = new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        bundle.getString("fieldperfreportview_matnr_notexist_title"),
                        bundle.getString("fieldperfreportview_matnr_notexist_msg") + ": " + String.join(", ", invalidNumbers));
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }


    public List<String> onCompleteMaterialNumber(String query) {
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
        fetchMaterials();
    }



    public MaterialSearchDTO getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(MaterialSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    public Collection<MaterialSearchDTO> getMaterialsList() {
        return materialsList;
    }

    public List<String> getMatNrFilterList() {
        return matNrFilterList;
    }

    public void setMatNrFilterList(List<String> matNrFilterList) {
        this.matNrFilterList = matNrFilterList;
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

    public String getCurrentPageURL() {
        return PAGE_URL;
    }

    @Override
    protected String getViewName() {
        return VIEW_ID;
    }

}
