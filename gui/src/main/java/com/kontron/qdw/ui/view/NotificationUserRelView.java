package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.FORM_NOTIFICATIONUSERRELVIEW_TITLE;
import static com.kontron.qdw.ui.TranslationKeys.OPERATION_FETCH_FAIL;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.NotificationBoundaryService;
import com.kontron.qdw.dto.base.NotificationUserRelDTO;
import com.kontron.qdw.ui.UserSession;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("notificationUserRelView")
@ViewScoped
public class NotificationUserRelView implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;
    public static final String PAGE_URL = "/view/NotificationUserRelView.jsf?faces-redirect=true";

    private List<NotificationUserRelDTO> notificationsList = new ArrayList<>();
    private NotificationUserRelDTO selectedObject;
    private final UserSession userSession;
    private transient ResourceBundle bundle;
    private final transient NotificationBoundaryService notificationService;
    private String formTitle = "";


    public NotificationUserRelView() {
        this.userSession = null;
        this.notificationService = null;
    }

    @Inject
    public NotificationUserRelView(UserSession userSession, NotificationBoundaryService notificationService) {
        this.userSession = userSession;
        this.notificationService = notificationService;
    }



    public Collection<NotificationUserRelDTO> getNotificationsList() {
        return notificationsList;
    }

    public NotificationUserRelDTO getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(NotificationUserRelDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getCurrentPageURL() {
        return PAGE_URL;
    }

    public void initSearchObject() {
        logger.debug("Perform data fetch operation");

        try {
            notificationsList = notificationService.getActiveNotifications(userSession.getPrincipal().getId(), true);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_FETCH_FAIL, e);
        }
    }

    public void fetchNotifications() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        formTitle = bundle.getString(FORM_NOTIFICATIONUSERRELVIEW_TITLE);

        initSearchObject();

        logger.debug("View initialization finished");
    }

}
