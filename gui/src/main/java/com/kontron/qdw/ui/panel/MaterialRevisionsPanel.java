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

@Named("materialRevisionsPanel")
@ViewScoped
public class MaterialRevisionsPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<MaterialRevisionsDTO> revisionsList = new ArrayList<>();
    @Generated
    private MaterialRevisionsDTO selItemOfRevisions;
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
    private final transient MaterialBoundaryService materialService;
    @Generated
    private final transient MaterialRevisionBoundaryService materialRevisionService;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionsPanel() {
        this.userSession = null;
        this.materialService = null;
        this.materialRevisionService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param materialService
     * @param materialRevisionService
     */
    @Inject
    @Generated
    public MaterialRevisionsPanel(UserSession userSession, MaterialBoundaryService materialService,
            MaterialRevisionBoundaryService materialRevisionService) {
        this.userSession = userSession;
        this.materialService = materialService;
        this.materialRevisionService = materialRevisionService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<MaterialRevisionsDTO> getRevisionsList() {
        return revisionsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public MaterialRevisionsDTO getSelItemOfRevisions() {
        return selItemOfRevisions;
    }

    /**
     * @param selItemOfRevisions
     */
    @Generated
    public void setSelItemOfRevisions(MaterialRevisionsDTO selItemOfRevisions) {
        this.selItemOfRevisions = selItemOfRevisions;
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
    public void onRevisionsGridDoubleClick() {
        logger.debug("Handle double-click event");

        if (!readOnly)
            if (userSession.redirectTo(getCurrentPageURL(), openEditMaterialRevisionDialog()))
                return;

        userSession.redirectTo(getCurrentPageURL(), openViewMaterialRevisionDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteMaterialRevision() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfRevisions.getId());

            materialRevisionService.deleteMaterialRevision(selItemOfRevisions.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchRevisions();
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
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfRevisions.getId());

            newId = materialRevisionService.copy(selItemOfRevisions.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditMaterialRevisionDialog.PAGE_INIT_URL + newId;

        userSession.setLastPage(getCurrentPageURL());
        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openEditMaterialRevisionDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditMaterialRevisionDialog.PAGE_INIT_URL + selItemOfRevisions.getId();

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialRevisionDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER))
            url = ViewMaterialRevisionDialog.PAGE_INIT_URL + selItemOfRevisions.getId();

        return url;
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchRevisions();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchRevisions() {
        // Get data from server
        try {
            revisionsList = new ArrayList<>(materialService.getRevisionsOfMaterial(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}