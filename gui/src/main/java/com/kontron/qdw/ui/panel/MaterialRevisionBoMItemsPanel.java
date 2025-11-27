package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("materialRevisionBoMItemsPanel")
@ViewScoped
public class MaterialRevisionBoMItemsPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<MaterialRevisionBoMItemsDTO> boMItemsList = new ArrayList<>();
    @Generated
    private MaterialRevisionBoMItemsDTO selItemOfBoMItems;
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
    private final transient MaterialRevisionBoundaryService materialRevisionService;
    @Generated
    private final transient BoMItemBoundaryService boMItemService;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionBoMItemsPanel() {
        this.userSession = null;
        this.materialRevisionService = null;
        this.boMItemService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param materialRevisionService
     * @param boMItemService
     */
    @Inject
    @Generated
    public MaterialRevisionBoMItemsPanel(UserSession userSession, MaterialRevisionBoundaryService materialRevisionService,
            BoMItemBoundaryService boMItemService) {
        this.userSession = userSession;
        this.materialRevisionService = materialRevisionService;
        this.boMItemService = boMItemService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<MaterialRevisionBoMItemsDTO> getBoMItemsList() {
        return boMItemsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public MaterialRevisionBoMItemsDTO getSelItemOfBoMItems() {
        return selItemOfBoMItems;
    }

    /**
     * @param selItemOfBoMItems
     */
    @Generated
    public void setSelItemOfBoMItems(MaterialRevisionBoMItemsDTO selItemOfBoMItems) {
        this.selItemOfBoMItems = selItemOfBoMItems;
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
    public void onBoMItemsGridDoubleClick() {
        logger.debug("Handle double-click event");

        if (!readOnly)
            if (userSession.redirectTo(getCurrentPageURL(), openEditBoMItemDialog()))
                return;

        userSession.redirectTo(getCurrentPageURL(), openViewBoMItemDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteBoMItem() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfBoMItems.getId());

            boMItemService.deleteBoMItem(selItemOfBoMItems.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchBoMItems();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        var url = "";
        long newId;

        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfBoMItems.getId());

            newId = boMItemService.copy(selItemOfBoMItems.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditBoMItemDialog.PAGE_INIT_URL + newId;

        userSession.setLastPage(getCurrentPageURL());
        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openEditBoMItemDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditBoMItemDialog.PAGE_INIT_URL + selItemOfBoMItems.getId();

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewBoMItemDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER))
            url = ViewBoMItemDialog.PAGE_INIT_URL + selItemOfBoMItems.getId();

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openAddBoMItemDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = AddBoMItemDialog.PAGE_INIT_URL + selectedObjectId;

        return url;
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchBoMItems();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchBoMItems() {
        // Get data from server
        try {
            boMItemsList = new ArrayList<>(materialRevisionService.getBoMItemsOfMaterialRevision(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}