package com.kontron.qdw.ui.dialog;

import org.slf4j.*;
import jakarta.faces.context.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import jakarta.servlet.http.*;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.panel.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("viewMaterialDialog")
@ViewScoped
public class ViewMaterialDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewMaterialDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private MaterialDTO material;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    private long selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final MaterialRevisionsPanel panRevisions;

    /**
     * Default constructor
     */
    @Generated
    public ViewMaterialDialog() {
        this.materialService = null;
        this.userSession = null;
        this.panRevisions = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param materialService
     * @param userSession
     * @param panRevisions
     */
    @Inject
    @Generated
    public ViewMaterialDialog(MaterialBoundaryService materialService, UserSession userSession, MaterialRevisionsPanel panRevisions) {
        this.materialService = materialService;
        this.userSession = userSession;
        this.panRevisions = panRevisions;
    }

    /**
     * @return the model object
     */
    @Generated
    public MaterialDTO getMaterial() {
        return material;
    }

    /**
     * @param material
     */
    @Generated
    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }

    /**
     * @return the form title
     */
    @Generated
    public String getFormTitle() {
        return formTitle;
    }

    /**
     * @param formTitle
     */
    @Generated
    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    /**
     * @return the ID of the selected object
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
     * Initialize dialog
     */
    @Generated
    public void initView() {
        logger.debug("Initialize dialog");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            material = materialService.findMaterialById(selectedObjectId);

            panRevisions.setSelectedObjectId(selectedObjectId);
            panRevisions.setCurrentPageURL(ViewMaterialDialog.PAGE_INIT_URL + selectedObjectId);
            panRevisions.setReadOnly(true);
            panRevisions.initView();


            formTitle = bundle.getString(FORM_VIEWMATERIALDIALOG_TITLE) + " '" + selectedObjectId + "'";

            logger.debug("Dialog initialization finished");
        }
        catch (final Exception e) {
            logger.error("Dialog initialization failed!", e);

            final FacesContext facesContext = FacesContext.getCurrentInstance();

            try {
                final String errorMessage = bundle.getString(DIALOG_INIT_FAIL);

                facesContext.getExternalContext().responseSendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
            }
            catch (final Exception ex) {
                logger.error("Failed to send error code!", ex);
            }

            facesContext.responseComplete();
        }
    }

    /**
     * @return the URL of the current page
     */
    @Generated
    public String getCurrentPageURL() {
        return ViewMaterialDialog.PAGE_INIT_URL + selectedObjectId;
    }

}