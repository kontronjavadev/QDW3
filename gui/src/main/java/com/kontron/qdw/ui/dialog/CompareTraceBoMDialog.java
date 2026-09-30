package com.kontron.qdw.ui.dialog;

import static com.kontron.qdw.ui.TranslationKeys.DIALOG_INIT_FAIL;
import static com.kontron.qdw.ui.TranslationKeys.FORM_COMPARETRACEBOMDIALOG_TITLE;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;
import static com.kontron.qdw.ui.UserSession.ROLE_ADMINISTRATOR;
import static com.kontron.qdw.ui.UserSession.ROLE_READONLY;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.serial.SerialObjectBoundaryService;
import com.kontron.qdw.dto.serial.SerialObjectCompareDTO;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.panel.TraceBoMBoMComparePanel;
import com.kontron.qdw.ui.view.util.ViewHelper;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;

@Named("compareTraceBoMDialog")
@ViewScoped
public class CompareTraceBoMDialog implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String SND_SER_OBJ_KEY = "SND_SER_OBJ_KEY";

    public static final String PAGE_INIT_URL = "/dialog/CompareTraceBoMDialog.jsf?faces-redirect=true&selectedObjectId=";
    private final UserSession userSession;
    private String formTitle = "";
    private transient ResourceBundle bundle;

    private final TraceBoMBoMComparePanel panBomItems;
    private final transient SerialObjectBoundaryService serObjService;

    private long selectedObjectId; // ist SerialObject-Id
    private SerialObjectCompareDTO fstSerObj;
    private SerialObjectCompareDTO sndSerObj;

    private ViewHelper<Long> viewHelper = new ViewHelper<>();


    public CompareTraceBoMDialog() {
        this.serObjService = null;
        this.userSession = null;
        panBomItems = null;
    }

    @Inject
    public CompareTraceBoMDialog(SerialObjectBoundaryService serObjService, UserSession userSession,
            TraceBoMBoMComparePanel panBomItems) {
        this.serObjService = serObjService;
        this.userSession = userSession;
        this.panBomItems = panBomItems;
    }



    public void sndSerObjSelected() {
        logger.info("sndSerObjSelected: " + (sndSerObj == null ? "null" : sndSerObj));
        if (sndSerObj == null) {
            return;
        }

        panBomItems.setSerObjsToCompare(fstSerObj, sndSerObj);
    }

    /**
     * Initialize dialog
     */
    public void initView() {
        logger.debug("Initialize dialog");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return;
        }


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            fstSerObj = serObjService.findCompareSerObj(selectedObjectId);

            panBomItems.setCurrentPageURL(CompareTraceBoMDialog.PAGE_INIT_URL + selectedObjectId);
            panBomItems.initView();


            formTitle = bundle.getString(FORM_COMPARETRACEBOMDIALOG_TITLE);

            Optional<SerialObjectCompareDTO> optSndSerObj = viewHelper.getStoredDataForSelectedObjectId(getClass(), SND_SER_OBJ_KEY,
                    selectedObjectId);
            if (optSndSerObj.isPresent()) {
                sndSerObj = optSndSerObj.get();
                sndSerObjSelected();
            }
            else {
                sndSerObj = new SerialObjectCompareDTO();
                sndSerObj.setSerialNumber(fstSerObj.getSerialNumber());
            }

            logger.debug("Dialog initialization finished");
        }
        catch (final Exception e) {
            logger.error("Dialog initialization failed!", e);

            final FacesContext facesContext = FacesContext.getCurrentInstance();

            try {
                final String errorMessage = bundle.getString(DIALOG_INIT_FAIL);

                facesContext.getExternalContext().responseSendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
            }
            catch (final Exception ex) {
                logger.error("Failed to send error code!", ex);
            }

            facesContext.responseComplete();
        }
    }

    public String openViewMaterialDialog() {
        return ViewMaterialDialog.PAGE_INIT_URL + fstSerObj.getMaterialId();
    }

    public String openViewTraceBoMDialog() {
        return ViewTraceBoMDialog.PAGE_INIT_URL + fstSerObj.getTraceBomId();
    }

    /**
     * Callback method for auto-complete field 'cboSndSerObj'
     * 
     * @param filter the filter criterion inserted the by the user
     * @return the proposal list
     */
    public List<SerialObjectCompareDTO> onCompleteSerObj(String filter) {
        try {
            return serObjService.findSerialObjectsForCompare(filter);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for proposal text field 'cboSndSerObj'!", e);
            return Collections.emptyList();
        }
    }



    public SerialObjectCompareDTO getFstSerObj() {
        return fstSerObj;
    }

    public void setFstSerObj(SerialObjectCompareDTO fstSerObj) {
        this.fstSerObj = fstSerObj;
    }

    public SerialObjectCompareDTO getSndSerObj() {
        return sndSerObj;
    }

    public void setSndSerObj(SerialObjectCompareDTO sndSerObj) {
        // Wichtig: vom Converter kommt lediglich ein leeres DTO mit Id!
        // Wird auf den link der ersten SerObj geklickt, noch bevor ein zweites SerObj ausgewählt wurde,
        // so wird der ursprüngliche Dummy mit der Materialnummer und Id = 0 übergeben. Dann Abbruch.
        if (sndSerObj == null || sndSerObj.getId() == 0) {
            this.sndSerObj = new SerialObjectCompareDTO();
            this.sndSerObj.setSerialNumber(fstSerObj.getSerialNumber());
        }
        else {
            // -> vollständig holen, da in allen Tabs für Anzeige MatNr/RevNr/Werk benötigt wird
            this.sndSerObj = serObjService.findCompareSerObj(sndSerObj.getId());
        }
        viewHelper.storeDataForSelectedObjectId(getClass(), SND_SER_OBJ_KEY, this.sndSerObj, selectedObjectId);
    }



    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    /**
     * @return the ID of the selected object (ServailObject id)
     */
    public long getSelectedObjectId() {
        return selectedObjectId;
    }

    /**
     * @param selectedObjectId (ServailObject id)
     */
    public void setSelectedObjectId(long selectedObjectId) {
        this.selectedObjectId = selectedObjectId;
    }

    public String getCurrentPageURL() {
        return CompareTraceBoMDialog.PAGE_INIT_URL + selectedObjectId;
    }

}
