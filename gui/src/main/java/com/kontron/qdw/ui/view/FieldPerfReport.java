package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.*;
import static com.kontron.qdw.ui.UserSession.*;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.text.StringEscapeUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.*;
import com.kontron.qdw.boundary.material.*;
import com.kontron.qdw.boundary.mv.FieldPerfReportService;
import com.kontron.qdw.boundary.mv.MaterializedServiceMessageBoundaryService;
import com.kontron.qdw.boundary.service.RMATypeBoundaryService;
import com.kontron.qdw.boundary.service.RepairErrorCodeBoundaryService;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.dto.material.*;
import com.kontron.qdw.dto.mv.*;
import com.kontron.qdw.dto.service.*;
import com.kontron.qdw.service.SavedQueryService;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.view.util.SuperView;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("fieldPerfReport")
@SessionScoped
public class FieldPerfReport extends SuperView implements Serializable {

    private static final long serialVersionUID = 6078905084559392861L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final Integer PRESET_MONTH_IN_INTERVAL = 36;
    private static final List<Integer> MONTH_INTERVALS = Arrays.asList(4, 6, 8, 12, 18, 24, PRESET_MONTH_IN_INTERVAL, 72);

    private static final List<Integer> QULT_REL_PERIODS = IntStream.rangeClosed(1, 36).boxed().toList();
    private static final Integer PRESET_QULT_REL_PERIOD = 12;

    public static final String PAGE_URL = "/view/FieldPerfReport.jsf?faces-redirect=true";
    public static final String VIEW_ID = "com.kontron.qdw.ui.view.FieldPerfReport";


    private final transient SavedQueryService queryManager;
    private final transient MaterialBoundaryService materialService;
    private final transient RMATypeBoundaryService rmaTypeService;
    private final transient MaterialNumberSetBoundaryService matNrSetService;
    private final transient SerialNumberSetBoundaryService serNrSetService;
    private final transient CustomerBoundaryService custService;
    private final transient SupplierBoundaryService suppService;
    private final transient MaterialTypeBoundaryService matTypeService;
    private final transient MaterialClassBoundaryService matClassService;
    private final transient PlantBoundaryService plantService;
    private final transient MaterializedServiceMessageBoundaryService servMsgService;
    private final transient RepairErrorCodeBoundaryService repErrorService;
    private final transient FieldPerfReportService reportService;

    private final UserSession userSession;
    private transient ResourceBundle bundle;
    private String formTitle = "";
    private long countResult;

    private String savedQueryName;
    private String selectedSavedQuery;

    private String currentCustFilter = "";
    private String currentSuppFilter = "";

    private FieldPerfReportSearchObject reportSearchObj;

    private DualListModel<RepairErrorCodeListDTO> errList;
    private String errFilterCriterion;
    private Map<String, RepairErrorCodeListDTO> errorCache = new HashMap<>();



    public FieldPerfReport() {
        userSession = null;
        queryManager = null;

        materialService = null;
        rmaTypeService = null;
        matNrSetService = null;
        serNrSetService = null;
        custService = null;
        suppService = null;
        matTypeService = null;
        matClassService = null;
        plantService = null;
        servMsgService = null;
        repErrorService = null;
        reportService = null;
    }

    @Inject
    public FieldPerfReport(UserSession userSession, SavedQueryService queryManager,
            MaterialBoundaryService materialService, RMATypeBoundaryService rmaTypeService, MaterialNumberSetBoundaryService matNrSetService,
            SerialNumberSetBoundaryService serNrSetService, CustomerBoundaryService custService, SupplierBoundaryService suppService,
            MaterialTypeBoundaryService matTypeService, MaterialClassBoundaryService matClassService, PlantBoundaryService plantService,
            MaterializedServiceMessageBoundaryService servMsgService, RepairErrorCodeBoundaryService repErrorService,
            FieldPerfReportService reportService) {
        this.userSession = userSession;
        this.queryManager = queryManager;

        this.materialService = materialService;
        this.rmaTypeService = rmaTypeService;
        this.matNrSetService = matNrSetService;
        this.serNrSetService = serNrSetService;
        this.custService = custService;
        this.suppService = suppService;
        this.matTypeService = matTypeService;
        this.matClassService = matClassService;
        this.plantService = plantService;
        this.servMsgService = servMsgService;
        this.repErrorService = repErrorService;
        this.reportService = reportService;
    }

    public void initView() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_SUPERUSER, ROLE_READONLY)) {
            return;
        }


        formTitle = bundle.getString(FORM_FIELDPERFREPORT_TITLE);

        // Check if previous search exists!
        final FieldPerfReportSearchObject lastSearch = queryManager.getLastQueryTyped(userSession.getPrincipal().getId(), VIEW_ID);

        if (reportSearchObj == null) {
            if (lastSearch != null) {
                reportSearchObj = lastSearch;
                initSearchObjectLists();
            }
            else {
                resetSearchObject();
            }
        }

        logger.debug("View initialization finished");
    }


    @Override
    public void resetSearchObject() {
        clearSearchObject();
    }

    @Override
    public void clearSearchObject() {
        reportSearchObj = new FieldPerfReportSearchObject();
        initSearchObjectLists();
        presetSearchObjectLists();
    }


    public void executeReport() {
        logger.debug("Perform report execution");

        if (!reportSearchObj.getMatNrs().isEmpty() && reportSearchObj.getSelectedMatNrSet() != null) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, FIELDPERFREPORTVIEW_REPORT_MATNR_FAIL);
            return;
        }

        if (!reportSearchObj.getRevNo().isEmpty() &&
                reportSearchObj.getMatNrs().isEmpty() && reportSearchObj.getSelectedMatNrSet() == null) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_WARN, FIELDPERFREPORTVIEW_REPORT_REVNR_WARN);
        }

        try {
            queryManager.saveQueryTyped(userSession.getPrincipal().getId(), VIEW_ID, null, reportSearchObj);

            // materialsList = materialService.searchAllMaterials(searchObj);
            HashMap<String, Long> shipPerMonth = reportService.getShipmentsPerMonth(reportSearchObj);
            System.out.println("hallo");
        }
        catch (final Exception e) {
            logger.error("Error while executing report!", e);
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_EXEC_REPORT_FAIL, e);
        }
    }



    /** Diese Methode wird vom JavaScript via p:remoteCommand aufgerufen */
    public void handlePastedMaterials() {
        final var requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String targetId = requestParams.get("targetFieldId");
        final String pastedTokensString = requestParams.get("pastedTokens");

        if (pastedTokensString != null && !pastedTokensString.isBlank()) {
            // Die aus dem JS übergebenen Tokens wieder aufsplitten
            final List<String> incomingTokens = Arrays.asList(pastedTokensString.split(";;"));

            // 1. Bulk-Validierung gegen die Boundary (vermeidet N+1 Selects)
            final List<String> existingNumbers = materialService.findExistingMaterialNumbers(incomingTokens);

            // 2. Gültige Nummern der UI-Liste hinzufügen (falls noch nicht vorhanden)
            if (targetId.contains("fi_matnr")) { // form:tabview1:fi_matnr_input
                for (final String validNum : existingNumbers) {
                    if (!reportSearchObj.getMatNrs().contains(validNum)) {
                        reportSearchObj.getMatNrs().add(validNum);
                    }
                }
            }
            else if (targetId.contains("fi_pmatnr")) { // form:tabview1:fi_pmatnr_input
                for (final String validNum : existingNumbers) {
                    if (!reportSearchObj.getpMatNrs().contains(validNum)) {
                        reportSearchObj.getpMatNrs().add(validNum);
                    }
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

    public List<String> onCompleteMaterialNumber(String filter) {
        final var results = new ArrayList<String>();
        try {
            final Collection<MaterialListDTO> items = materialService.findMaterials(filter + "%");
            for (final MaterialListDTO item : items) {
                results.add(item.getMaterialNumber());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", filter, e);
        }

        return results;
    }



    public List<CustomerDTO> onCompleteCustomer(String filter) {
        this.currentCustFilter = filter;
        try {
            return custService.findCustomersExtend(filter);
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", filter, e);
            return Collections.emptyList();
        }
    }

    public List<SupplierDTO> onCompleteSupplier(String filter) {
        this.currentSuppFilter = filter;
        try {
            return suppService.findSuppliersExtend(filter);
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", filter, e);
            return Collections.emptyList();
        }
    }



    public void onCustomerSelect(final SelectEvent<CustomerDTO> event) {
        if (event.getObject() == null) {
            return;
        }
        final CustomerDTO selected = event.getObject();
        addTooltip("fi_cust", selected.getCode(), selected.getName());
    }

    public void onSupplierSelect(final SelectEvent<SupplierDTO> event) {
        if (event.getObject() == null) {
            return;
        }
        final SupplierDTO selected = event.getObject();
        addTooltip("fi_supp", selected.getCode(), selected.getName());
    }

    private void addTooltip(String elemName, String code, String name) {
        // Maskiert einfache Anführungszeichen (z.B. bei "O'Connor")
        final String safeName = name.replace("'", "\\'");

        // Ruft die Funktion aus der extension.js auf.
        // Parameter: 1. ID des Feldes, 2. Code (Label des Tokens), 3. Tooltip-Text (Name)
        final String jsCall = String.format("addTooltipToToken('%s', '%s', '%s');", elemName, code, safeName);

        PrimeFaces.current().executeScript(jsCall);
    }



    public String getPreloadedCustomerTooltipsJson() {
        return getPreloadedTooltipsJson(reportSearchObj.getCustomers(), CustomerDTO::getCode, CustomerDTO::getName);
    }

    public String getPreloadedSupplierTooltipsJson() {
        return getPreloadedTooltipsJson(reportSearchObj.getSupplier(), SupplierDTO::getCode, SupplierDTO::getName);
    }

    private <DTO> String getPreloadedTooltipsJson(List<DTO> dtoFilterList,
            Function<DTO, String> codeMapper, Function<DTO, String> nameMapper) {
        if (dtoFilterList == null || dtoFilterList.isEmpty()) {
            return "{}";
        }

        final StringBuilder json = new StringBuilder("{");
        for (int i = 0; i < dtoFilterList.size(); i++) {
            final DTO dto = dtoFilterList.get(i);
            // Für reines JSON maskieren
            final String safeName = nameMapper.apply(dto).replace("\\", "\\\\").replace("\"", "\\\"");

            json.append("\"").append(codeMapper.apply(dto)).append("\": \"").append(safeName).append("\"");
            if (i < dtoFilterList.size() - 1) {
                json.append(", ");
            }
        }
        json.append("}");

        return json.toString();
    }



    /**
     * Sichert den Text gegen XSS ab und markiert den gefundenen Suchbegriff farblich.
     */
    public String highlightMatchCust(final String text) {
        return highlightMatch(text, currentCustFilter);
    }

    /**
     * Sichert den Text gegen XSS ab und markiert den gefundenen Suchbegriff farblich.
     */
    public String highlightMatchSupp(final String text) {
        return highlightMatch(text, currentSuppFilter);
    }

    private String highlightMatch(final String text, String currentFilter) {
        if (text == null) {
            return "";
        }

        // zwingend: XSS-Schutz vorab
        final String safeText = StringEscapeUtils.escapeHtml4(text);

        if (currentFilter == null || currentFilter.isBlank()) {
            return safeText;
        }

        // Führende und schließende % entfernen (auch mehrere wie "%%ab%")
        String cleanQuery = currentFilter.replaceAll("^%+", "").replaceAll("%+$", "").trim();

        if (cleanQuery.isBlank()) {
            return safeText;
        }


        // Den Suchbegriff für den Regex escapen (falls der User nach "+" oder "*" sucht)
        // und ebenfalls HTML-escapen, da wir ihn gegen den gesicherten 'safeText' matchen.
        cleanQuery = StringEscapeUtils.escapeHtml4(cleanQuery);

        // Suchstring an verbleibenden (inneren) % aufsplitten
        final String[] parts = cleanQuery.split("%+");
        final StringBuilder regexBuilder = new StringBuilder("(?i)(");

        for (int i = 0; i < parts.length; i++) {
            regexBuilder.append(Pattern.quote(parts[i]));
            // Zwischen den einzelnen Blöcken das Wildcard .*? (non-greedy) einfügen
            if (i < parts.length - 1) {
                regexBuilder.append(".*?");
            }
        }
        regexBuilder.append(")");

        // Regex-Replace mit der Capture-Group $1:
        // (?i) schaltet auf case-insensitive.
        // Die Klammern um quotedQuery bilden eine Capture-Group.
        // $1 referenziert den exakt gefundenen String im Zieltext (erhält den originalen Case).
        return safeText.replaceAll(regexBuilder.toString(),
                "<span style=\"background-color: var(--filtermark);color: var(--textonactivemarked);\">$1</span>");
    }



    public void filterErrs() {
        try {
            final List<RepairErrorCodeListDTO> errSourceList = repErrorService.findRepairErrorCodes(errFilterCriterion);

            errorCache.clear();
            errorCache.putAll(errSourceList.stream()
                    .collect(Collectors.toMap(RepairErrorCodeListDTO::getCode, Function.identity())));
            errorCache.putAll(reportSearchObj.getSelectedErr().stream()
                    .collect(Collectors.toMap(RepairErrorCodeListDTO::getCode, Function.identity())));

            errSourceList.removeAll(reportSearchObj.getSelectedErr());
            errList = new DualListModel<>(errSourceList, reportSearchObj.getSelectedErr());
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for list field 'errList'!", e);
            errList = new DualListModel<>(new ArrayList<>(), reportSearchObj.getSelectedErr());
        }
    }

    public void onPickListTransfer(TransferEvent event) {
        reportSearchObj.setSelectedErr(new ArrayList<>(errList.getTarget())); // neue ArrayList, um Referenz-Probleme zu vermeiden
    }

    /** getAsObject() des Converters ruft diese Methode auf, um nicht für jeden Eintrag eine DB-Abfrage machen zu müssen. */
    public RepairErrorCodeListDTO getErrDtoFromCache(String submittedValue) {
        return errorCache.get(submittedValue);
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

        queryManager.saveQueryTyped(userSession.getPrincipal().getId(), VIEW_ID, savedQueryName, reportSearchObj);
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

        logger.debug("Use saved query");

        reportSearchObj = queryManager.getSavedQueryTyped(userSession.getPrincipal().getId(), VIEW_ID, selectedSavedQuery);

        selectedSavedQuery = null;
    }

    @Override
    public void renameQuery() {
        super.renameQuery();
        selectedSavedQuery = null;
    }



    public FieldPerfReportSearchObject getReportSearchObj() {
        return reportSearchObj;
    }

    public void setReportSearchObj(FieldPerfReportSearchObject reportSearchObj) {
        this.reportSearchObj = reportSearchObj;
    }



    public DualListModel<RepairErrorCodeListDTO> getErrList() {
        return errList;
    }

    public void setErrList(DualListModel<RepairErrorCodeListDTO> errList) {
        this.errList = errList;
    }

    public String getErrFilterCriterion() {
        return errFilterCriterion;
    }

    public void setErrFilterCriterion(String errFilterCriterion) {
        this.errFilterCriterion = errFilterCriterion;
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



    private void initSearchObjectLists() {
        reportSearchObj.setRmaTypes(rmaTypeService.findAll().stream()
                .map(RMATypeListDTO::getCode)
                .collect(Collectors.toList()));

        reportSearchObj.setMatNrSets(matNrSetService.findAll());
        reportSearchObj.setSerNrSets(serNrSetService.findAll());

        reportSearchObj.setMatTypes(matTypeService.findAll().stream()
                .map(MaterialTypeListDTO::getCode)
                .collect(Collectors.toList()));

        reportSearchObj.setMatClasses(matClassService.findAll().stream()
                .map(MaterialClassListDTO::getCode)
                .collect(Collectors.toList()));

        reportSearchObj.setAnalIntervals(MONTH_INTERVALS);

        reportSearchObj.setReturnIntervals(MONTH_INTERVALS);

        reportSearchObj.setPlants(plantService.findAll());

        reportSearchObj.setOrigins(servMsgService.extractOrigins());

        reportSearchObj.setErrGrps(repErrorService.extractGroups());

        errList = new DualListModel<>(new ArrayList<>(), reportSearchObj.getSelectedErr());

        reportSearchObj.setQualityPeriods(QULT_REL_PERIODS);
    }

    private void presetSearchObjectLists() {
        // Voreinstellungen
        if (reportSearchObj.getSelectedAnalInterval() == null) {
            reportSearchObj.setSelectedAnalInterval(PRESET_MONTH_IN_INTERVAL);
        }
        if (reportSearchObj.getSelectedReturnInterval() == null) {
            reportSearchObj.setSelectedReturnInterval(PRESET_MONTH_IN_INTERVAL);
        }
        if (reportSearchObj.getStartDate() == null) {
            reportSearchObj.setStartDate(LocalDate.now().withDayOfMonth(1).withMonth(1).minusYears(2));
        }
        if (reportSearchObj.getEpidemic() == null) {
            reportSearchObj.setEpidemic(true);
        }
        if (reportSearchObj.getSelectedDistType() == null) {
            reportSearchObj.setSelectedDistType(DistributionType.CUSTOMER);
        }
        if (reportSearchObj.getSelectedQualityPeriod() == null) {
            reportSearchObj.setSelectedQualityPeriod(PRESET_QULT_REL_PERIOD);
        }
        if (reportSearchObj.getSelectedRmaCntType() == null) {
            reportSearchObj.setSelectedRmaCntType(RMACountingType.ALL_BY_ARRIVAL_DATE);
        }
    }



    // public void refreshFormatSettings() {
    // searchObj.setDateFormat(userSession.getDateFormat());
    // searchObj.setDateTimeFormat(userSession.getDateTimeFormat());
    // searchObj.setNumberFormat(userSession.getNumberFormat());
    // searchObj.setDecimalSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getDecimalSeparator());
    // searchObj.setGroupingSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getGroupingSeparator());
    // }



    // public void onClick() { }
    //
    // public void onDoubleClick() {
    // logger.debug("Handle double-click event");
    //
    // if (userSession.redirectTo(getCurrentPageURL(), openEditMaterialDialog())) {
    // return;
    // }
    //
    // userSession.redirectTo(getCurrentPageURL(), openViewMaterialDialog());
    // }
    //
    // public String openViewMaterialDialog() {
    // var url = "";
    //
    // if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_SUPERUSER)) {
    // url = ViewMaterialDialog.PAGE_INIT_URL + selectedObject.getId();
    // }
    //
    // return url;
    // }
    //
    // public String openEditMaterialDialog() {
    // var url = "";
    //
    // if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_SUPERUSER)) {
    // url = EditMaterialDialog.PAGE_INIT_URL + selectedObject.getId();
    // }
    //
    // return url;
    // }

}
