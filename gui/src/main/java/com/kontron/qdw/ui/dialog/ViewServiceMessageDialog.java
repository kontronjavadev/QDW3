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

@Named("viewServiceMessageDialog")
@ViewScoped
public class ViewServiceMessageDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewServiceMessageDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private ServiceMessageDTO serviceMessage;
    @Generated
    private final transient ServiceMessageBoundaryService serviceMessageService;
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
    public ViewServiceMessageDialog() {
        this.serviceMessageService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param serviceMessageService
     * @param userSession
     */
    @Inject
    @Generated
    public ViewServiceMessageDialog(ServiceMessageBoundaryService serviceMessageService, UserSession userSession) {
        this.serviceMessageService = serviceMessageService;
        this.userSession = userSession;
    }

    /**
     * @return the model object
     */
    @Generated
    public ServiceMessageDTO getServiceMessage() {
        return serviceMessage;
    }

    /**
     * @param serviceMessage
     */
    @Generated
    public void setServiceMessage(ServiceMessageDTO serviceMessage) {
        this.serviceMessage = serviceMessage;
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

            serviceMessage = serviceMessageService.findServiceMessageById(selectedObjectId);

            formTitle = bundle.getString(FORM_VIEWSERVICEMESSAGEDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
        return ViewServiceMessageDialog.PAGE_INIT_URL + selectedObjectId;
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewServiceOrderDialogLink() {
        return ViewServiceOrderDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(serviceMessage.getServiceOrder().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewSerialObjectDialogLink() {
        return ViewSerialObjectDialog.PAGE_INIT_URL + serviceMessage.getSerialObject().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialDialogLink() {
        return ViewMaterialDialog.PAGE_INIT_URL + serviceMessage.getMaterialRevisionMaterial().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialRevisionDialogLink() {
        return ViewMaterialRevisionDialog.PAGE_INIT_URL + serviceMessage.getMaterialRevision().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewSupplierDialogLink() {
        return ViewSupplierDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(serviceMessage.getExternalSupplier().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

}