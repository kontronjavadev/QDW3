package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.boundary.serial.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("serialObjectSerialObjectsPanel")
@ViewScoped
public class SerialObjectSerialObjectsPanel implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<SerialObjectSerialObjectsDTO> serialObjectsList = new ArrayList<>();
    @Generated
    private SerialObjectSerialObjectsDTO selItemOfSerialObjects;
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
    private final transient SerialObjectBoundaryService serialObjectService;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectSerialObjectsPanel() {
        this.userSession = null;
        this.serialObjectService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serialObjectService
     */
    @Inject
    @Generated
    public SerialObjectSerialObjectsPanel(UserSession userSession, SerialObjectBoundaryService serialObjectService) {
        this.userSession = userSession;
        this.serialObjectService = serialObjectService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<SerialObjectSerialObjectsDTO> getSerialObjectsList() {
        return serialObjectsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public SerialObjectSerialObjectsDTO getSelItemOfSerialObjects() {
        return selItemOfSerialObjects;
    }

    /**
     * @param selItemOfSerialObjects
     */
    @Generated
    public void setSelItemOfSerialObjects(SerialObjectSerialObjectsDTO selItemOfSerialObjects) {
        this.selItemOfSerialObjects = selItemOfSerialObjects;
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
    public void onSerialObjectsGridDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewSerialObjectDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteSerialObject() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfSerialObjects.getId());

            serialObjectService.deleteSerialObject(selItemOfSerialObjects.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchSerialObjects();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfSerialObjects.getId());

            serialObjectService.copy(selItemOfSerialObjects.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchSerialObjects();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewSerialObjectDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
            url = ViewSerialObjectDialog.PAGE_INIT_URL + selItemOfSerialObjects.getId();

        return url;
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchSerialObjects();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchSerialObjects() {
        // Get data from server
        try {
            serialObjectsList = new ArrayList<>(serialObjectService.getSerialObjectsOfSerialObject(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}