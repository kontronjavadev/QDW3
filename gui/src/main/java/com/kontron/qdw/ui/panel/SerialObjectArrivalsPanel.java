package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

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

@Named("serialObjectArrivalsPanel")
@ViewScoped
public class SerialObjectArrivalsPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<SerialObjectArrivalsDTO> arrivalsList = new ArrayList<>();
    @Generated
    private SerialObjectArrivalsDTO selItemOfArrivals;
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
    @Generated
    private final transient ArrivalBoundaryService arrivalService;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectArrivalsPanel() {
        this.userSession = null;
        this.serialObjectService = null;
        this.arrivalService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serialObjectService
     * @param arrivalService
     */
    @Inject
    @Generated
    public SerialObjectArrivalsPanel(UserSession userSession, SerialObjectBoundaryService serialObjectService,
            ArrivalBoundaryService arrivalService) {
        this.userSession = userSession;
        this.serialObjectService = serialObjectService;
        this.arrivalService = arrivalService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<SerialObjectArrivalsDTO> getArrivalsList() {
        return arrivalsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public SerialObjectArrivalsDTO getSelItemOfArrivals() {
        return selItemOfArrivals;
    }

    /**
     * @param selItemOfArrivals
     */
    @Generated
    public void setSelItemOfArrivals(SerialObjectArrivalsDTO selItemOfArrivals) {
        this.selItemOfArrivals = selItemOfArrivals;
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
    public void onArrivalsGridDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewArrivalDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteArrival() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfArrivals.getId());

            arrivalService.deleteArrival(selItemOfArrivals.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchArrivals();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfArrivals.getId());

            arrivalService.copy(selItemOfArrivals.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchArrivals();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewArrivalDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            url = ViewArrivalDialog.PAGE_INIT_URL + selItemOfArrivals.getId();
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

        fetchArrivals();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchArrivals() {
        // Get data from server
        try {
            arrivalsList = new ArrayList<>(serialObjectService.getArrivalsOfSerialObject(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}
