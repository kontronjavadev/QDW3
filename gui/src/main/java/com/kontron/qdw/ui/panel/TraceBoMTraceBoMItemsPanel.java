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

@Named("traceBoMTraceBoMItemsPanel")
@ViewScoped
public class TraceBoMTraceBoMItemsPanel implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<TraceBoMTraceBoMItemsDTO> traceBoMItemsList = new ArrayList<>();
    @Generated
    private TraceBoMTraceBoMItemsDTO selItemOfTraceBoMItems;
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
    private final transient TraceBoMBoundaryService traceBoMService;
    @Generated
    private final transient TraceBoMItemBoundaryService traceBoMItemService;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMTraceBoMItemsPanel() {
        this.userSession = null;
        this.traceBoMService = null;
        this.traceBoMItemService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param traceBoMService
     * @param traceBoMItemService
     */
    @Inject
    @Generated
    public TraceBoMTraceBoMItemsPanel(UserSession userSession, TraceBoMBoundaryService traceBoMService,
            TraceBoMItemBoundaryService traceBoMItemService) {
        this.userSession = userSession;
        this.traceBoMService = traceBoMService;
        this.traceBoMItemService = traceBoMItemService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<TraceBoMTraceBoMItemsDTO> getTraceBoMItemsList() {
        return traceBoMItemsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public TraceBoMTraceBoMItemsDTO getSelItemOfTraceBoMItems() {
        return selItemOfTraceBoMItems;
    }

    /**
     * @param selItemOfTraceBoMItems
     */
    @Generated
    public void setSelItemOfTraceBoMItems(TraceBoMTraceBoMItemsDTO selItemOfTraceBoMItems) {
        this.selItemOfTraceBoMItems = selItemOfTraceBoMItems;
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
    public void onTraceBoMItemsGridDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewTraceBoMItemDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteTraceBoMItem() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfTraceBoMItems.getId());

            traceBoMItemService.deleteTraceBoMItem(selItemOfTraceBoMItems.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchTraceBoMItems();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfTraceBoMItems.getId());

            traceBoMItemService.copy(selItemOfTraceBoMItems.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchTraceBoMItems();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewTraceBoMItemDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
            url = ViewTraceBoMItemDialog.PAGE_INIT_URL + selItemOfTraceBoMItems.getId();

        return url;
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchTraceBoMItems();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchTraceBoMItems() {
        // Get data from server
        try {
            traceBoMItemsList = new ArrayList<>(traceBoMService.getTraceBoMItemsOfTraceBoM(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}