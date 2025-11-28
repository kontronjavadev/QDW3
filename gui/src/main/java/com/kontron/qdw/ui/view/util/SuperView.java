package com.kontron.qdw.ui.view.util;

import static com.kontron.qdw.ui.TranslationKeys.OPERATION_QUERYRENAME_NOT_UNIQUE;
import static com.kontron.qdw.ui.TranslationKeys.OPERATION_RENAME_FAIL;
import static com.kontron.qdw.ui.TranslationKeys.OPERATION_RENAME_SUCCESS;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.kontron.qdw.boundary.base.UserPropertyBoundaryService;
import com.kontron.qdw.dto.base.UserPropertyDTO;
import com.kontron.qdw.service.SavedQueryService;
import com.kontron.qdw.ui.UserSession;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import net.sourceforge.jbizmo.commons.search.dto.SearchFieldDTO;
import net.sourceforge.jbizmo.commons.search.dto.SortDirectionEnum;
import net.sourceforge.jbizmo.commons.webclient.primefaces.search.JSFSearchFieldDTO;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

public abstract class SuperView extends CopyClipboard {

    private static final String KEY_NUMBER_ROWS = "NUMBER_ROWS";
    private static final String DEFAULT_NUMBER_ROWS = "50";
    private static final int PAGINATOR_INVISIBLE_MAX_ROWS = 150;

    @Inject
    private UserSession userSession;
    @Inject
    private UserPropertyBoundaryService userPropertyService;
    @Inject
    private SavedQueryService queryManager;

    private UserPropertyDTO userPropNumberRows;

    private String newQueryName;



    public void clearSearchObject() {
        for (SearchFieldDTO field : searchObj.getSearchFields()) {
            JSFSearchFieldDTO jsfField = (JSFSearchFieldDTO) field;
            jsfField.setOperator(null);
            jsfField.setBetween(false);
            jsfField.setSortOrder(SortDirectionEnum.NONE);
            jsfField.setBigDecimalCriterion(null);
            jsfField.setBigDecimalBetweenCriterion(null);
            jsfField.setDateCriterion(null);
            jsfField.setDateBetweenCriterion(null);
            jsfField.setDoubleCriterion(null);
            jsfField.setDoubleBetweenCriterion(null);
            jsfField.setIntegerCriterion(null);
            jsfField.setIntegerBetweenCriterion(null);
            jsfField.setStringCriterion(null);
            jsfField.setStringBetweenCriterion(null);
        }
    }

    /**
     * initSearchObject();
     * fetchXxxxx();
     */
    public abstract void resetSearchObject();

    protected void setCountFilterDependent() {
        searchObj.setCount(searchObj.getSearchFields().stream()
                .map(JSFSearchFieldDTO.class::cast)
                .anyMatch(dto -> dto.getDateCriterion() != null
                        || dto.getDoubleCriterion() != null
                        || dto.getIntegerCriterion() != null
                        || StringUtils.isNotEmpty(dto.getStringCriterion())
                        || dto.getBigDecimalCriterion() != null));
    }



    public void initProperties() {
        userPropNumberRows = userPropertyService.findUserProperty(userSession.getPrincipal().getId(), KEY_NUMBER_ROWS, getViewName());
        if (userPropNumberRows == null) {
            // dieses Property ist neu, also mit Default befüllen
            userPropNumberRows = new UserPropertyDTO();
            userPropNumberRows.setUserId(userSession.getPrincipal().getId());
            userPropNumberRows.setPropKey(KEY_NUMBER_ROWS);
            userPropNumberRows.setViewName(getViewName());
            userPropNumberRows.setValue(DEFAULT_NUMBER_ROWS);
            userPropNumberRows = userPropertyService.saveUserProperty(userPropNumberRows);
        }
    }

    protected abstract String getViewName();



    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareAfterLoad() {
        // Die visFields (in target) nach colOrder sortieren, damit die Reihenfolge erhalten bleibt,
        // die availableFields in der Reihenfolge, wie ursprünglich definiert.
        super.prepareAfterLoad();
        sortSearchFields(visibleFields.getSource(), SearchFieldDTO::getOriginalColumnIndex);
        sortSearchFields(visibleFields.getTarget(), SearchFieldDTO::getColOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preSearch() {
        super.preSearch();

        // Map mit Spalten nach Name aufbauen.
        Map<String, SearchFieldDTO> cols = searchObj.getSearchFields().stream()
                .collect(Collectors.toMap(SearchFieldDTO::getColLabel, v -> v));

        // Reihenfolge für die sichtbaren Spalten gemäß Eintragungsreihenfolge in getTarget() setzen.
        int visibleColOrder = 0;
        for (SearchFieldDTO visibleCol : visibleFields.getTarget()) {
            cols.get(visibleCol.getColLabel()).setColOrder(visibleColOrder++);
        }

        // Reihenfolge für die nicht sichtbaren Spalten mit Start > letztem Index;
        // tatsächliche Reihenfolge ist irrelevant, da die Spalten nicht angezeigt werden, aber um etwaige
        // Konflikte zu vermeiden, sollten sie nicht mit den Indices der angezeigten Spalten kollidieren.
        int invisibleColOrder = visibleFields.getTarget().size();
        for (final SearchFieldDTO col : searchObj.getSearchFields()) {
            if (!col.isVisible()) {
                col.setColOrder(invisibleColOrder++);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postSearch() {
        // Die ursprüngliche Implementierug in der Superklasse ist falsch, wenn die colOrder gesetzt wird, aber xhtml-seitig
        // per Index auf die Felder zugegriffen wird. Damit würden dieselben Spalten, aber mit falschen Spalteninformationen
        // verwendet werden.
        // Jedoch werden die Spalten bei der Suche in JPAQueryStatementGenerator#createStatement(SearchDTO) nach sortIndex sortiert,
        // was, zumindest hier, nie gesetzt wird. Die Spalten müssen daher wieder nach dem originalColumnIndex sortiert werden.
        // Worin der Unterschied zwischen colOrder und columnIndex besteht, erschließt sich mir nicht. Wir verwenden hier colOrder,
        // da mit Aufbau des SearchDTO in der #initSearchObject() dem ctor eine fortlaufende Zahl übergeben wird, die in colOrder
        // und originalColumnIndex gespeichert wird.

        sortSearchFields(searchObj.getSearchFields(), SearchFieldDTO::getOriginalColumnIndex);
    }

    private void sortSearchFields(List<SearchFieldDTO> fields, ToIntFunction<? super SearchFieldDTO> searchCriterion) {
        fields.sort(Comparator.comparingInt(searchCriterion));
    }



    public LocalDate getDatePart(LocalDateTime localDate) {
        return localDate == null ? null : localDate.toLocalDate();
    }

    public LocalTime getTimePart(LocalDateTime localDate) {
        return localDate == null ? null : localDate.toLocalTime();
    }



    /**
     * Sucht das SearchField zum angegebenen Namen.
     * <p/>
     * <em>ACHTUNG!</em> Es wird zum Optional&lt;&gt; ein get ausgeführt. Das Feld muss es somit zwingend geben!
     */
    public JSFSearchFieldDTO findField(String colName) {
        return (JSFSearchFieldDTO) searchObj.getSearchFields().stream()
                .filter(field -> field.getColName().equals(colName))
                .findFirst()
                .get();
    }



    public String getNumberRows() {
        return userPropNumberRows.getValue();
    }

    public void setNumberRows(String numberRows) {
        userPropNumberRows.setValue(numberRows);
        userPropertyService.saveUserProperty(userPropNumberRows);
    }

    public String getPaginatorAlwaysVisible() {
        return Integer.parseInt(getNumberRows()) > PAGINATOR_INVISIBLE_MAX_ROWS ? "true" : "false";
    }



    public abstract String getSelectedSavedQuery();

    public String getNewQueryName() {
        return newQueryName;
    }

    public void setNewQueryName(String newQueryName) {
        this.newQueryName = newQueryName;
    }

    public void openRenameDialog() {
        if (getSelectedSavedQuery() == null) {
            return;
        }
        PrimeFaces.current().executeScript("PF('dlgQeryRename').show();");
    }

    public void renameQuery() {
        if (StringUtils.isEmpty(getSelectedSavedQuery()) || StringUtils.isEmpty(newQueryName)) {
            return;
        }

        ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        boolean nameExists = queryManager.getSavedQueries(userSession.getPrincipal().getId(), getViewName()).stream()
                .anyMatch(q -> q.equals(newQueryName));
        if (nameExists) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_RENAME_FAIL,
                    bundle.getString(OPERATION_QUERYRENAME_NOT_UNIQUE).formatted(newQueryName));
            return;
        }

        queryManager.renameQuery(userSession.getPrincipal().getId(), getViewName(), getSelectedSavedQuery(), newQueryName);
        MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, OPERATION_RENAME_SUCCESS);
    }

}
