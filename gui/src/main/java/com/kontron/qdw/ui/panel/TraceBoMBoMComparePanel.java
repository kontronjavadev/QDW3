package com.kontron.qdw.ui.panel;

import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;
import static com.kontron.qdw.ui.UserSession.ROLE_ADMINISTRATOR;
import static com.kontron.qdw.ui.UserSession.ROLE_READONLY;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.serial.TraceBoMBoundaryService;
import com.kontron.qdw.dto.serial.SerialObjectCompareDTO;
import com.kontron.qdw.dto.serial.TraceBoMTraceBoMItemsDTO;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.dialog.ViewBoMItemDialog;
import com.kontron.qdw.ui.dialog.ViewMaterialDialog;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("traceBoMBoMComparePanel")
@ViewScoped
public class TraceBoMBoMComparePanel extends CopyClipboard implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;

    private final UserSession userSession;
    @SuppressWarnings("unused")
    private transient ResourceBundle bundle;
    private final transient TraceBoMBoundaryService tbService;

    private SerialObjectCompareDTO serObjFst;
    private SerialObjectCompareDTO serObjSnd;
    private Map<Boolean, List<TraceBoMTraceBoMItemsDTO>> diffBoMItems;
    private TraceBoMTraceBoMItemsDTO selItemOfBomItemsFst;
    private TraceBoMTraceBoMItemsDTO selItemOfBomItemsSnd;

    private String currentPageURL;



    public TraceBoMBoMComparePanel() {
        userSession = null;
        tbService = null;
    }

    @Inject
    public TraceBoMBoMComparePanel(UserSession userSession, TraceBoMBoundaryService tbService) {
        this.userSession = userSession;
        this.tbService = tbService;
    }



    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        diffBoMItems = new HashMap<>();
        diffBoMItems.put(Boolean.TRUE, Collections.emptyList());
        diffBoMItems.put(Boolean.FALSE, Collections.emptyList());

        logger.debug("Grid panel initialization finished");
    }



    public void onBomItemsGridDoubleClickFst() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewBoMItemDialogFst());
    }

    public void onBomItemsGridDoubleClickSnd() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewBoMItemDialogSnd());
    }


    public String openViewBoMItemDialogFst() {
        return openViewBoMItemDialog(selItemOfBomItemsFst);
    }

    public String openViewBoMItemDialogSnd() {
        return openViewBoMItemDialog(selItemOfBomItemsSnd);
    }

    private String openViewBoMItemDialog(TraceBoMTraceBoMItemsDTO selItemOfBomItems) {
        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return ViewBoMItemDialog.PAGE_INIT_URL + selItemOfBomItems.getId();
        }

        return "";
    }



    public String openViewMaterialDialogFst() {
        return openViewMaterialDialog(selItemOfBomItemsFst);
    }

    public String openViewMaterialDialogSnd() {
        return openViewMaterialDialog(selItemOfBomItemsSnd);
    }

    private String openViewMaterialDialog(TraceBoMTraceBoMItemsDTO selItemOfBomItems) {
        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return ViewMaterialDialog.PAGE_INIT_URL + selItemOfBomItems.getMaterialId();
        }

        return "";
    }



    public void setSerObjsToCompare(SerialObjectCompareDTO serObjFst, SerialObjectCompareDTO serObjSnd) {
        this.serObjFst = serObjFst;
        this.serObjSnd = serObjSnd;
        refresh();
    }

    public SerialObjectCompareDTO getSerObjFst() {
        return serObjFst;
    }

    public SerialObjectCompareDTO getSerObjSnd() {
        return serObjSnd;
    }

    public List<TraceBoMTraceBoMItemsDTO> getBomItemsFst() {
        return diffBoMItems.get(Boolean.TRUE);
    }

    public List<TraceBoMTraceBoMItemsDTO> getBomItemsSnd() {
        return diffBoMItems.get(Boolean.FALSE);
    }

    public void refresh() {
        diffBoMItems = tbService.compareBoMs(serObjFst.getTraceBomId(), serObjSnd.getTraceBomId());
    }



    public TraceBoMTraceBoMItemsDTO getSelItemOfBomItemsFst() {
        return selItemOfBomItemsFst;
    }

    public void setSelItemOfBomItemsFst(TraceBoMTraceBoMItemsDTO selItemOfBomItemsFst) {
        this.selItemOfBomItemsFst = selItemOfBomItemsFst;
    }

    public TraceBoMTraceBoMItemsDTO getSelItemOfBomItemsSnd() {
        return selItemOfBomItemsSnd;
    }

    public void setSelItemOfBomItemsSnd(TraceBoMTraceBoMItemsDTO selItemOfBomItemsSnd) {
        this.selItemOfBomItemsSnd = selItemOfBomItemsSnd;
    }



    public String getCurrentPageURL() {
        return currentPageURL;
    }

    public void setCurrentPageURL(String currentPageURL) {
        this.currentPageURL = currentPageURL;
    }

}
