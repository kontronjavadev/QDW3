package com.kontron.qdw.ui.dialog;

import org.slf4j.*;
import jakarta.faces.context.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.servlet.http.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("editBoMItemDialog")
@ViewScoped
public class EditBoMItemDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/EditBoMItemDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private BoMItemUpdateDTO boMItem;
    @Generated
    private final transient BoMItemBoundaryService boMItemService;
    @Generated
    private long selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterialRevisionBoundaryService materialRevisionService;
    @Generated
    private final transient MaterialBoundaryService materialService;

    /**
     * Default constructor
     */
    @Generated
    public EditBoMItemDialog() {
        this.boMItemService = null;
        this.userSession = null;
        this.materialRevisionService = null;
        this.materialService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param boMItemService
     * @param userSession
     * @param materialRevisionService
     * @param materialService
     */
    @Inject
    @Generated
    public EditBoMItemDialog(BoMItemBoundaryService boMItemService, UserSession userSession, MaterialRevisionBoundaryService materialRevisionService,
            MaterialBoundaryService materialService) {
        this.boMItemService = boMItemService;
        this.userSession = userSession;
        this.materialRevisionService = materialRevisionService;
        this.materialService = materialService;
    }

    /**
     * @return the model object
     */
    @Generated
    public BoMItemUpdateDTO getBoMItem() {
        return boMItem;
    }

    /**
     * @param boMItem
     */
    @Generated
    public void setBoMItem(BoMItemUpdateDTO boMItem) {
        this.boMItem = boMItem;
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
     * Callback method for auto-complete field 'cboMaterialRevision'
     * @param filter the filter criterion inserted the by the user
     * @return the proposal list
     */
    @Generated
    public List<MaterialRevisionListDTO> onCompleteMaterialRevision(String filter) {
        try {
            return materialRevisionService.findMaterialRevisions(filter);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for proposal text field 'cboMaterialRevision'!", e);

            return Collections.emptyList();
        }
    }

    /**
     * Callback method for auto-complete field 'cboMaterial'
     * @param filter the filter criterion inserted the by the user
     * @return the proposal list
     */
    @Generated
    public List<MaterialListDTO> onCompleteMaterial(String filter) {
        try {
            return materialService.findMaterials(filter);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for proposal text field 'cboMaterial'!", e);

            return Collections.emptyList();
        }
    }

    /**
     * Initialize dialog
     */
    @Generated
    public void initView() {
        logger.debug("Initialize dialog");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            boMItem = boMItemService.getBoMItemForUpdate(selectedObjectId);

            formTitle = bundle.getString(FORM_EDITBOMITEMDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
     * Save model object
     * @return the navigation target
     */
    @Generated
    public String save() {
        try {
            logger.debug("Perform save operation");

            boMItemService.updateBoMItem(boMItem);

            return userSession.getLastPage();
        }
        catch (final Exception e) {
            logger.error("Error while performing save operation!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_SAVE_FAIL, e);
            return "";
        }
    }

    /**
     * @return the URL of the current page
     */
    @Generated
    public String getCurrentPageURL() {
        return EditBoMItemDialog.PAGE_INIT_URL + selectedObjectId;
    }

}