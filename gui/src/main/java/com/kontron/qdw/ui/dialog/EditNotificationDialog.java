package com.kontron.qdw.ui.dialog;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.dto.base.*;
import jakarta.servlet.http.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("editNotificationDialog")
@ViewScoped
public class EditNotificationDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/EditNotificationDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private NotificationUpdateDTO notification;
    @Generated
    private final transient NotificationBoundaryService notificationService;
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
    public EditNotificationDialog() {
        this.notificationService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param notificationService
     * @param userSession
     */
    @Inject
    @Generated
    public EditNotificationDialog(NotificationBoundaryService notificationService, UserSession userSession) {
        this.notificationService = notificationService;
        this.userSession = userSession;
    }

    public void onStartDateChange() {
        if (notification.getNotificationEnd() != null && notification.getNotificationStart() != null
                && notification.getNotificationEnd().isBefore(notification.getNotificationStart())) {
            notification.setNotificationEnd(notification.getNotificationStart());
        }
    }

    public void onEndDateChange() {
        if (notification.getNotificationEnd() != null && notification.getNotificationStart() != null
                && notification.getNotificationEnd().isBefore(notification.getNotificationStart())) {
            notification.setNotificationStart(notification.getNotificationEnd());
        }
    }
    
    /**
     * @return the model object
     */
    @Generated
    public NotificationUpdateDTO getNotification() {
        return notification;
    }

    /**
     * @param notification
     */
    @Generated
    public void setNotification(NotificationUpdateDTO notification) {
        this.notification = notification;
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_MAINTAINER))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            notification = notificationService.getNotificationForUpdate(selectedObjectId);

            formTitle = bundle.getString(FORM_EDITNOTIFICATIONDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
     * Save model object
     * @return the navigation target
     */
    @Generated
    public String save() {
        try {
            logger.debug("Perform save operation");

            notificationService.updateNotification(notification);

            return userSession.getLastPage();
        }
        catch (final Exception e) {
            logger.error("Error while performing save operation!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_SAVE_FAIL, e);
            return "";
        }
    }

    /**
     * @return the URL of the current page
     */
    @Generated
    public String getCurrentPageURL() {
        return EditNotificationDialog.PAGE_INIT_URL + selectedObjectId;
    }

}