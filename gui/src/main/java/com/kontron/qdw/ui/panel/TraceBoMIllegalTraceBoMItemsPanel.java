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

@Named("traceBoMIllegalTraceBoMItemsPanel")
@ViewScoped
public class TraceBoMIllegalTraceBoMItemsPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<TraceBoMIllegalTraceBoMItemsDTO> illegalTraceBoMItemsList = new ArrayList<>();
    @Generated
    private TraceBoMIllegalTraceBoMItemsDTO selItemOfIllegalTraceBoMItems;
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
    private final transient IllegalTraceBoMItemBoundaryService illegalTraceBoMItemService;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMIllegalTraceBoMItemsPanel() {
        this.userSession = null;
        this.traceBoMService = null;
        this.illegalTraceBoMItemService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param traceBoMService
     * @param illegalTraceBoMItemService
     */
    @Inject
    @Generated
    public TraceBoMIllegalTraceBoMItemsPanel(UserSession userSession, TraceBoMBoundaryService traceBoMService,
            IllegalTraceBoMItemBoundaryService illegalTraceBoMItemService) {
        this.userSession = userSession;
        this.traceBoMService = traceBoMService;
        this.illegalTraceBoMItemService = illegalTraceBoMItemService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<TraceBoMIllegalTraceBoMItemsDTO> getIllegalTraceBoMItemsList() {
        return illegalTraceBoMItemsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public TraceBoMIllegalTraceBoMItemsDTO getSelItemOfIllegalTraceBoMItems() {
        return selItemOfIllegalTraceBoMItems;
    }

    /**
     * @param selItemOfIllegalTraceBoMItems
     */
    @Generated
    public void setSelItemOfIllegalTraceBoMItems(TraceBoMIllegalTraceBoMItemsDTO selItemOfIllegalTraceBoMItems) {
        this.selItemOfIllegalTraceBoMItems = selItemOfIllegalTraceBoMItems;
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
    public void onIllegalTraceBoMItemsGridDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openViewIllegalTraceBoMItemDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteIllegalTraceBoMItem() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfIllegalTraceBoMItems.getId());

            illegalTraceBoMItemService.deleteIllegalTraceBoMItem(selItemOfIllegalTraceBoMItems.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchIllegalTraceBoMItems();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfIllegalTraceBoMItems.getId());

            illegalTraceBoMItemService.copy(selItemOfIllegalTraceBoMItems.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchIllegalTraceBoMItems();
        return "";
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewIllegalTraceBoMItemDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
            url = ViewIllegalTraceBoMItemDialog.PAGE_INIT_URL + selItemOfIllegalTraceBoMItems.getId();

        return url;
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchIllegalTraceBoMItems();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchIllegalTraceBoMItems() {
        // Get data from server
        try {
            illegalTraceBoMItemsList = new ArrayList<>(traceBoMService.getIllegalTraceBoMItemsOfTraceBoM(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}