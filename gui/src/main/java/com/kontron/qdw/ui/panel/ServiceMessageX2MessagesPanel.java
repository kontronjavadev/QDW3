package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("serviceMessageX2MessagesPanel")
@ViewScoped
public class ServiceMessageX2MessagesPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<ServiceMessageX2MessagesDTO> x2MessagesList = new ArrayList<>();
    @Generated
    private ServiceMessageX2MessagesDTO selItemOfX2Messages;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private long selectedObjectId;
    @Generated
    private String currentPageURL;
    @Generated
    private boolean readOnly;
    @Generated
    private final transient ServiceMessageBoundaryService serviceMessageService;
    @Generated
    private final transient X2MessageBoundaryService x2MessageService;

    /**
     * Default constructor
     */
    @Generated
    public ServiceMessageX2MessagesPanel() {
        this.userSession = null;
        this.serviceMessageService = null;
        this.x2MessageService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serviceMessageService
     * @param x2MessageService
     */
    @Inject
    @Generated
    public ServiceMessageX2MessagesPanel(UserSession userSession, ServiceMessageBoundaryService serviceMessageService,
            X2MessageBoundaryService x2MessageService) {
        this.userSession = userSession;
        this.serviceMessageService = serviceMessageService;
        this.x2MessageService = x2MessageService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<ServiceMessageX2MessagesDTO> getX2MessagesList() {
        return x2MessagesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public ServiceMessageX2MessagesDTO getSelItemOfX2Messages() {
        return selItemOfX2Messages;
    }

    /**
     * @param selItemOfX2Messages
     */
    @Generated
    public void setSelItemOfX2Messages(ServiceMessageX2MessagesDTO selItemOfX2Messages) {
        this.selItemOfX2Messages = selItemOfX2Messages;
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
    public void onX2MessagesGridDoubleClick() {
        // No appropriate form found!
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteX2Message() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfX2Messages.getId());

            x2MessageService.deleteX2Message(selItemOfX2Messages.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchX2Messages();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfX2Messages.getId());

            x2MessageService.copy(selItemOfX2Messages.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchX2Messages();
        return "";
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchX2Messages();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchX2Messages() {
        // Get data from server
        try {
            x2MessagesList = new ArrayList<>(serviceMessageService.getX2MessagesOfServiceMessage(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}
