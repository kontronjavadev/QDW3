package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.boundary.service.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("serviceMessageFailureMaterialsPanel")
@ViewScoped
public class ServiceMessageFailureMaterialsPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<ServiceMessageFailureMaterialsDTO> failureMaterialsList = new ArrayList<>();
    @Generated
    private ServiceMessageFailureMaterialsDTO selItemOfFailureMaterials;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private long selectedObjectId;
    @Generated
    private String currentPageURL;
    @Generated
    private final transient ServiceMessageBoundaryService serviceMessageService;

    /**
     * Default constructor
     */
    @Generated
    public ServiceMessageFailureMaterialsPanel() {
        this.userSession = null;
        this.serviceMessageService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serviceMessageService
     */
    @Inject
    @Generated
    public ServiceMessageFailureMaterialsPanel(UserSession userSession, ServiceMessageBoundaryService serviceMessageService) {
        this.userSession = userSession;
        this.serviceMessageService = serviceMessageService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<ServiceMessageFailureMaterialsDTO> getFailureMaterialsList() {
        return failureMaterialsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public ServiceMessageFailureMaterialsDTO getSelItemOfFailureMaterials() {
        return selItemOfFailureMaterials;
    }

    /**
     * @param selItemOfFailureMaterials
     */
    @Generated
    public void setSelItemOfFailureMaterials(ServiceMessageFailureMaterialsDTO selItemOfFailureMaterials) {
        this.selItemOfFailureMaterials = selItemOfFailureMaterials;
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
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onFailureMaterialsGridDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewMaterialDialog());
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER)) {
            url = ViewMaterialDialog.PAGE_INIT_URL + selItemOfFailureMaterials.getId();
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

        fetchFailureMaterials();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchFailureMaterials() {
        // Get data from server
        try {
            failureMaterialsList = new ArrayList<>(serviceMessageService.getFailureMaterialsOfServiceMessage(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}
