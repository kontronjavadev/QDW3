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

@Named("addBoMItemDialog")
@ViewScoped
public class AddBoMItemDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/AddBoMItemDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private BoMItemCreateDTO boMItem;
    @Generated
    private final transient BoMItemBoundaryService boMItemService;
    @Generated
    private String selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterialBoundaryService materialService;

    /**
     * Default constructor
     */
    @Generated
    public AddBoMItemDialog() {
        this.boMItemService = null;
        this.userSession = null;
        this.materialService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param boMItemService
     * @param userSession
     * @param materialService
     */
    @Inject
    @Generated
    public AddBoMItemDialog(BoMItemBoundaryService boMItemService, UserSession userSession, MaterialBoundaryService materialService) {
        this.boMItemService = boMItemService;
        this.userSession = userSession;
        this.materialService = materialService;
    }

    /**
     * @return the model object
     */
    @Generated
    public BoMItemCreateDTO getBoMItem() {
        return boMItem;
    }

    /**
     * @param boMItem
     */
    @Generated
    public void setBoMItem(BoMItemCreateDTO boMItem) {
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
     * @return the ID of the parent object
     */
    @Generated
    public String getSelectedObjectId() {
        return selectedObjectId;
    }

    /**
     * @param selectedObjectId
     */
    @Generated
    public void setSelectedObjectId(String selectedObjectId) {
        this.selectedObjectId = selectedObjectId;
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
            boMItem = new BoMItemCreateDTO();
            boMItem.setMaterialRevision(new MaterialRevisionListDTO(Long.parseLong(selectedObjectId)));

            formTitle = bundle.getString(FORM_ADDBOMITEMDIALOG_TITLE);

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

            boMItemService.createBoMItem(boMItem);

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
        return AddBoMItemDialog.PAGE_INIT_URL + selectedObjectId;
    }

}