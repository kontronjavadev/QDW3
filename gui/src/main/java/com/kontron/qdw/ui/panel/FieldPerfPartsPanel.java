package com.kontron.qdw.ui.panel;

import static com.kontron.qdw.ui.TranslationKeys.*;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.mv.FieldPerfReportService;
import com.kontron.qdw.dto.mv.report.*;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("fieldPerfPartsPanel")
@SessionScoped
public class FieldPerfPartsPanel extends CopyClipboard implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final UserSession userSession;
    private transient ResourceBundle bundle;
    private final transient FieldPerfReportService reportService;

    private Supplier<FieldPerfReportSearchObject> searchObjSupp;
    private Supplier<FieldPerfReportResultDTO[]> reportHeaderSupp;
    private Supplier<List<FieldPerfReportResultDTO[]>> reportDataSupp;
    private BiFunction<Integer, Integer, Boolean> detailPossible;
    private IntConsumer tabIndexCons;

    private List<FieldPerfReportPartResultDTO> parts = new ArrayList<>();
    private FieldPerfReportPartResultDTO selectedPart;



    public FieldPerfPartsPanel() {
        userSession = null;
        reportService = null;
    }

    @Inject
    public FieldPerfPartsPanel(UserSession userSession, FieldPerfReportService reportService) {
        this.userSession = userSession;
        this.reportService = reportService;
    }



    public void initView(Supplier<FieldPerfReportSearchObject> searchObjSupp,
            Supplier<FieldPerfReportResultDTO[]> reportHeaderSupp,
            Supplier<List<FieldPerfReportResultDTO[]>> reportDataSupp,
            BiFunction<Integer, Integer, Boolean> detailPossible,
            IntConsumer tabIndexCons) {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        this.searchObjSupp = searchObjSupp;
        this.reportHeaderSupp = reportHeaderSupp;
        this.reportDataSupp = reportDataSupp;
        this.detailPossible = detailPossible;
        this.tabIndexCons = tabIndexCons;

        logger.debug("Grid panel initialization finished");
    }

    public void reset() {
        parts = new ArrayList<>();
        selectedPart = null;
    }



    public void showParts(int rowIndex, int colIndex) {
        if (!detailPossible.apply(rowIndex, colIndex)) {
            return;
        }
        logger.info("showParts() zu (x, y): {}, {}", rowIndex, colIndex);

        parts = executeParts(rowIndex, colIndex);

        // weist den Browser an, sofort auf den Parts-Tab (Index 3) zu wechseln
        PrimeFaces.current().executeScript("PF('tabview2Widget').select(3);");
        tabIndexCons.accept(3);
    }



    private List<FieldPerfReportPartResultDTO> executeParts(int rowIndex, int colIndex) {
        if (!detailPossible.apply(rowIndex, colIndex)) {
            return Collections.emptyList();
        }
        String shipValue = reportHeaderSupp.get()[colIndex].getLabel(); // colHeader, Spaltenüberschriften: supplier arrival (shipped)
        String returnValue = reportDataSupp.get().get(rowIndex)[0].getLabel(); // rowHeader, Zeilenbeschriftungen: repair arrival (returned)

        logger.info("Perform parts execution for (x) supplier arrival (shipped) {} and (y) repair arrival (returned) {}",
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

        // Rückgabewert Map<String, Long>, der dann in eine List<String[]> gewandelt wird.
        // besser vielleicht List<Record<String, Int>>?
        if (qrelCol && sumRow) {
            return reportService.getAllErrorPositions(searchObjSupp.get());
        }
        else {
            return reportService.getErrorPositionReport(searchObjSupp.get());
        }
    }



    public List<FieldPerfReportPartResultDTO> getParts() {
        return parts;
    }

    public void setParts(List<FieldPerfReportPartResultDTO> parts) {
        this.parts = parts;
    }

    public FieldPerfReportPartResultDTO getSelectedPart() {
        return selectedPart;
    }

    public void setSelectedPart(FieldPerfReportPartResultDTO selectedPart) {
        this.selectedPart = selectedPart;
    }

}
