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

@Named("viewX2MessageDialog")
@ViewScoped
public class ViewX2MessageDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewX2MessageDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private X2MessageDTO x2Message;
    @Generated
    private final transient X2MessageBoundaryService x2MessageService;
    @Generated
    private long selectedObjectId;
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
    public ViewX2MessageDialog() {
        this.x2MessageService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param x2MessageService
     * @param userSession
     */
    @Inject
    @Generated
    public ViewX2MessageDialog(X2MessageBoundaryService x2MessageService, UserSession userSession) {
        this.x2MessageService = x2MessageService;
        this.userSession = userSession;
    }

    /**
     * @return the model object
     */
    @Generated
    public X2MessageDTO getX2Message() {
        return x2Message;
    }

    /**
     * @param x2Message
     */
    @Generated
    public void setX2Message(X2MessageDTO x2Message) {
        this.x2Message = x2Message;
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
    public long getSelectedObjectId() {
        return selectedObjectId;
    }

    /**
     * @param selectedObjectId
     */
    @Generated
    public void setSelectedObjectId(long selectedObjectId) {
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            x2Message = x2MessageService.findX2MessageById(selectedObjectId);

            formTitle = bundle.getString(FORM_VIEWX2MESSAGEDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
        return ViewX2MessageDialog.PAGE_INIT_URL + selectedObjectId;
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewServiceMessageDialogLink() {
        return ViewServiceMessageDialog.PAGE_INIT_URL + x2Message.getServiceMessage().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewSerialObjectDialogLink() {
        return ViewSerialObjectDialog.PAGE_INIT_URL + x2Message.getSerialObject().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialDialogLink() {
        return ViewMaterialDialog.PAGE_INIT_URL + x2Message.getMatrevMaterial().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialRevisionDialogLink() {
        return ViewMaterialRevisionDialog.PAGE_INIT_URL + x2Message.getMaterialRevision().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewRepairTaskDialogLink() {
        return ViewRepairTaskDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(x2Message.getRepairTask().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewRepairErrorCodeDialogLink() {
        return ViewRepairErrorCodeDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(x2Message.getRepairErrorCode().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewRepairStateDialogLink() {
        return ViewRepairStateDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(x2Message.getRepairState().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewFaultAnalysisDialogLink() {
        return ViewFaultAnalysisDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(x2Message.getFaultAnalysis().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

}