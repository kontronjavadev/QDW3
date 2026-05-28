package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("serviceOrderServiceMessagesPanel")
@ViewScoped
public class ServiceOrderServiceMessagesPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<ServiceOrderServiceMessagesDTO> serviceMessagesList = new ArrayList<>();
    @Generated
    private ServiceOrderServiceMessagesDTO selItemOfServiceMessages;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private String selectedObjectId;
    @Generated
    private String currentPageURL;
    @Generated
    private boolean readOnly;
    @Generated
    private final transient ServiceOrderBoundaryService serviceOrderService;
    @Generated
    private final transient ServiceMessageBoundaryService serviceMessageService;

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrderServiceMessagesPanel() {
        this.userSession = null;
        this.serviceOrderService = null;
        this.serviceMessageService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serviceOrderService
     * @param serviceMessageService
     */
    @Inject
    @Generated
    public ServiceOrderServiceMessagesPanel(UserSession userSession, ServiceOrderBoundaryService serviceOrderService,
            ServiceMessageBoundaryService serviceMessageService) {
        this.userSession = userSession;
        this.serviceOrderService = serviceOrderService;
        this.serviceMessageService = serviceMessageService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<ServiceOrderServiceMessagesDTO> getServiceMessagesList() {
        return serviceMessagesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public ServiceOrderServiceMessagesDTO getSelItemOfServiceMessages() {
        return selItemOfServiceMessages;
    }

    /**
     * @param selItemOfServiceMessages
     */
    @Generated
    public void setSelItemOfServiceMessages(ServiceOrderServiceMessagesDTO selItemOfServiceMessages) {
        this.selItemOfServiceMessages = selItemOfServiceMessages;
    }

    /**
     * @return the URL of the page this grid panel is included in
     */
    @Generated
    public String getCurrentPageURL() {
        return currentPageURL;
    }

    /**
     * @param currentPageURL
     */
    @Generated
    public void setCurrentPageURL(String currentPageURL) {
        this.currentPageURL = currentPageURL;
    }

    /**
     * @return the ID of the selected parent object
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
     * @return true if the panel is in read-only mode
     */
    @Generated
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly
     */
    @Generated
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onServiceMessagesGridDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewServiceMessageDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteServiceMessage() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfServiceMessages.getId());

            serviceMessageService.deleteServiceMessage(selItemOfServiceMessages.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchServiceMessages();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfServiceMessages.getId());

            serviceMessageService.copy(selItemOfServiceMessages.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchServiceMessages();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewServiceMessageDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            url = ViewServiceMessageDialog.PAGE_INIT_URL + selItemOfServiceMessages.getId();
        }

        return url;
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchServiceMessages();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchServiceMessages() {
        // Get data from server
        try {
            serviceMessagesList = new ArrayList<>(serviceOrderService.getServiceMessagesOfServiceOrder(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}
