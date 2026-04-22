package com.kontron.qdw.ui.dialog;

import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import jakarta.servlet.http.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("viewFaultAnalysisDialog")
@ViewScoped
public class ViewFaultAnalysisDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewFaultAnalysisDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private FaultAnalysisDTO faultAnalysis;
    @Generated
    private final transient FaultAnalysisBoundaryService faultAnalysisService;
    @Generated
    private String selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;

    /**
     * Default constructor
     */
    @Generated
    public ViewFaultAnalysisDialog() {
        this.faultAnalysisService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param faultAnalysisService
     * @param userSession
     */
    @Inject
    @Generated
    public ViewFaultAnalysisDialog(FaultAnalysisBoundaryService faultAnalysisService, UserSession userSession) {
        this.faultAnalysisService = faultAnalysisService;
        this.userSession = userSession;
    }

    /**
     * @return the model object
     */
    @Generated
    public FaultAnalysisDTO getFaultAnalysis() {
        return faultAnalysis;
    }

    /**
     * @param faultAnalysis
     */
    @Generated
    public void setFaultAnalysis(FaultAnalysisDTO faultAnalysis) {
        this.faultAnalysis = faultAnalysis;
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
     * @return the ID of the selected object
     */
    @Generated
    public String getSelectedObjectId() {
        return selectedObjectId;
    }

    /**
     * @param selectedObjectId
     */
    @Generated
    public void setSelectedObjectId(String selectedObjectId) {
        this.selectedObjectId = selectedObjectId;
    }

    /**
     * Initialize dialog
     */
    @Generated
    public void initView() {
        logger.debug("Initialize dialog");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            faultAnalysis = faultAnalysisService.findFaultAnalysisById(selectedObjectId);

            formTitle = bundle.getString(FORM_VIEWFAULTANALYSISDIALOG_TITLE) + " '" + selectedObjectId + "'";

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

    /**
     * @return the URL of the current page
     */
    @Generated
    public String getCurrentPageURL() {
        return ViewFaultAnalysisDialog.PAGE_INIT_URL + java.net.URLEncoder.encode(selectedObjectId, java.nio.charset.StandardCharsets.UTF_8);
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewFaultAnalysisDialogLink() {
        return ViewFaultAnalysisDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(faultAnalysis.getMappedTo().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

}