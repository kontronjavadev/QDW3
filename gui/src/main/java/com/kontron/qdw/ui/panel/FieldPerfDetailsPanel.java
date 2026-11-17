package com.kontron.qdw.ui.panel;

import static com.kontron.qdw.ui.TranslationKeys.*;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.mv.FieldPerfReportService;
import com.kontron.qdw.domain.mv.ServiceOrderType;
import com.kontron.qdw.dto.mv.MaterializedServiceMessageSearchDTO;
import com.kontron.qdw.dto.mv.report.FieldPerfReportResultDTO;
import com.kontron.qdw.dto.mv.report.FieldPerfReportSearchObject;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("fieldPerfDetailsPanel")
@ViewScoped
public class FieldPerfDetailsPanel extends CopyClipboard implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final UserSession userSession;
    private transient ResourceBundle bundle;
    private final transient FieldPerfReportService reportService;

    private String currentPageURL;
    // private long selectedObjectId;
    private Supplier<FieldPerfReportSearchObject> searchObjSupp;
    private Supplier<FieldPerfReportResultDTO[]> reportHeaderSupp;
    private Supplier<List<FieldPerfReportResultDTO[]>> reportDataSupp;

    private List<MaterializedServiceMessageSearchDTO> details = new ArrayList<>();
    private MaterializedServiceMessageSearchDTO selectedDetail;



    public FieldPerfDetailsPanel() {
        userSession = null;
        reportService = null;
    }

    @Inject
    public FieldPerfDetailsPanel(UserSession userSession, FieldPerfReportService reportService) {
        this.userSession = userSession;
        this.reportService = reportService;
    }



    public void initView(Supplier<FieldPerfReportSearchObject> searchObjSupp,
            Supplier<FieldPerfReportResultDTO[]> reportHeaderSupp,
            Supplier<List<FieldPerfReportResultDTO[]>> reportDataSupp) {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        this.searchObjSupp = searchObjSupp;
        this.reportHeaderSupp = reportHeaderSupp;
        this.reportDataSupp = reportDataSupp;
        // fetchRevisions();

        logger.debug("Grid panel initialization finished");
    }



    public void showDetails(int rowIndex, int colIndex) {
        if (!isDetailPossible(rowIndex, colIndex)) {
            return;
        }
        logger.info("showDetails() zu (x, y): {}, {}", rowIndex, colIndex);
        details = executeDetail(rowIndex, colIndex);
    }

    public void appendDetails(int rowIndex, int colIndex) {
        if (!isDetailPossible(rowIndex, colIndex)) {
            return;
        }
        logger.info("appendDetails() zu (x, y): {}, {}", rowIndex, colIndex);
        details = new ArrayList<>(details);
        details.addAll(executeDetail(rowIndex, colIndex));
    }



    private List<MaterializedServiceMessageSearchDTO> executeDetail(int rowIndex, int colIndex) {
        if (!isDetailPossible(rowIndex, colIndex)) {
            return Collections.emptyList();
        }
        String shipValue = reportHeaderSupp.get()[colIndex].getLabel(); // rowHeader: supplier arrival (shipped)
        String returnValue = reportDataSupp.get().get(rowIndex)[0].getLabel(); // colHeader: repair arrival (returned)

        logger.info("Perform details execution for (x) supplier arrival (shipped) {} and (y) repair arrival (returned) {}",
                shipValue, returnValue);

        boolean qrelCol = shipValue.equals(bundle.getString(FIELDPERFREPORTVIEW_COL_QREL_PERIOD));
        boolean sumRow = returnValue.equals(bundle.getString(FIELDPERFREPORTVIEW_ROW_SUM));
        LocalDate shipDate = null;
        LocalDate returnDate = null;
        try {
            if (!qrelCol) {
                String[] s = shipValue.split("-");
                shipDate = LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), 1);
            }
            if (!sumRow) {
                String[] s = returnValue.split("-");
                returnDate = LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), 1);
            }
        }
        catch (NumberFormatException | DateTimeException e) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_EXEC_REPORT_FAIL, e);
            return Collections.emptyList();
        }

        logger.debug("returnDate: {}, shipDate: {}", returnDate, shipDate);

        searchObjSupp.get().setShipDate(shipDate);
        searchObjSupp.get().setReturnDate(returnDate);


        if (qrelCol && sumRow) {
            return reportService.getAllDetailData(searchObjSupp.get());
        }
        else {
            return reportService.getFieldPerformanceReportDetailData(searchObjSupp.get());
        }
    }

    public boolean isDetailPossible(int rowIndex, int colIndex) {
        if (rowIndex <= 0 || colIndex <= 0) {
            // keine Auswahl oder Zeilenüberschrift oder Shipments-Zeile gewählt (Spaltenüberschrift ist nicht anwählbar)
            return false;
        }
        if (rowIndex > reportDataSupp.get().size() - 1 - 3) {
            // eine der letzten drei Zeilen gewählt. Details ist nur bis Zeile "sum" verfügbar.
            return false;
        }
        FieldPerfReportResultDTO clickedCell = reportDataSupp.get().get(rowIndex)[colIndex];
        if (StringUtils.isEmpty(clickedCell.getLabel())) {
            // in der Zelle steht nichts. Zelle ist also außerhalb des analysierbaren Bereichs. Details nicht möglich.
            return false;
        }
        return true;
    }



    // /**
    // * Event that will be fired if user performs a double-click on a grid row
    // */
    // public void onRevisionsGridDoubleClick() {
    // logger.debug("Handle double-click event");
    // userSession.redirectTo(getCurrentPageURL(), openViewMaterialRevisionDialog());
    // }
    //
    // /**
    // * Open dialog
    // * @return the navigation target
    // */
    // public String openViewMaterialRevisionDialog() {
    // var url = "";
    //
    // if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER)) {
    // url = ViewMaterialRevisionDialog.PAGE_INIT_URL + selItemOfRevisions.getId();
    // }
    //
    // return url;
    // }



    public List<MaterializedServiceMessageSearchDTO> getDetails() {
        return details;
    }

    public void setDetails(List<MaterializedServiceMessageSearchDTO> details) {
        this.details = details;
    }

    public MaterializedServiceMessageSearchDTO getSelectedDetail() {
        return selectedDetail;
    }

    public void setSelectedDetail(MaterializedServiceMessageSearchDTO selectedDetail) {
        this.selectedDetail = selectedDetail;
    }



    /**
     * Perform translation of given enumeration literal
     * @param item
     * @return the translation based on the user's locale
     */
    public String translateServiceOrderType(ServiceOrderType item) {
        return bundle.getString("serviceordertype_" + item.name().toLowerCase());
    }

    public String getCurrentPageURL() {
        return currentPageURL;
    }

    public void setCurrentPageURL(String currentPageURL) {
        this.currentPageURL = currentPageURL;
    }

}
