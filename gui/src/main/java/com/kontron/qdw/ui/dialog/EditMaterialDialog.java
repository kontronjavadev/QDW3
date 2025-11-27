package com.kontron.qdw.ui.dialog;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.servlet.http.*;
import com.kontron.qdw.dto.base.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.panel.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("editMaterialDialog")
@ViewScoped
public class EditMaterialDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/EditMaterialDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private MaterialUpdateDTO material;
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
    private final transient MaterialClassBoundaryService materialClassService;
    @Generated
    private final transient MaterialTypeBoundaryService materialTypeService;
    @Generated
    private final transient LocationBoundaryService locationService;
    @Generated
    private final MaterialRevisionsPanel panRevisions;
    @Generated
    private transient Collection<MaterialClassListDTO> materialClassList;
    @Generated
    private transient Collection<MaterialTypeListDTO> materialTypeList;
    @Generated
    private transient Collection<LocationListDTO> ownerLocationList;

    /**
     * Default constructor
     */
    @Generated
    public EditMaterialDialog() {
        this.materialService = null;
        this.userSession = null;
        this.materialClassService = null;
        this.materialTypeService = null;
        this.locationService = null;
        this.panRevisions = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param materialService
     * @param userSession
     * @param materialClassService
     * @param materialTypeService
     * @param locationService
     * @param panRevisions
     */
    @Inject
    @Generated
    public EditMaterialDialog(MaterialBoundaryService materialService, UserSession userSession, MaterialClassBoundaryService materialClassService,
            MaterialTypeBoundaryService materialTypeService, LocationBoundaryService locationService, MaterialRevisionsPanel panRevisions) {
        this.materialService = materialService;
        this.userSession = userSession;
        this.materialClassService = materialClassService;
        this.materialTypeService = materialTypeService;
        this.locationService = locationService;
        this.panRevisions = panRevisions;
    }

    /**
     * @return the model object
     */
    @Generated
    public MaterialUpdateDTO getMaterial() {
        return material;
    }

    /**
     * @param material
     */
    @Generated
    public void setMaterial(MaterialUpdateDTO material) {
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
     * @return the material class list
     */
    @Generated
    public Collection<MaterialClassListDTO> getMaterialClassList() {
        return materialClassList;
    }

    /**
     * @return the material type list
     */
    @Generated
    public Collection<MaterialTypeListDTO> getMaterialTypeList() {
        return materialTypeList;
    }

    /**
     * @return the location list
     */
    @Generated
    public Collection<LocationListDTO> getOwnerLocationList() {
        return ownerLocationList;
    }

    /**
     * Initialize dialog
     */
    @Generated
    public void initView() {
        logger.debug("Initialize dialog");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_SUPERUSER))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            material = materialService.getMaterialForUpdate(selectedObjectId);
            materialClassList = materialClassService.findMaterialClasses(null);

            if (material.getMaterialClass() != null && !materialClassList.contains(material.getMaterialClass()))
                materialClassList.add(material.getMaterialClass());

            materialTypeList = materialTypeService.findMaterialTypes(null);

            if (material.getMaterialType() != null && !materialTypeList.contains(material.getMaterialType()))
                materialTypeList.add(material.getMaterialType());

            ownerLocationList = locationService.findLocations(null);

            if (material.getOwnerLocation() != null && !ownerLocationList.contains(material.getOwnerLocation()))
                ownerLocationList.add(material.getOwnerLocation());


            panRevisions.setSelectedObjectId(selectedObjectId);
            panRevisions.setCurrentPageURL(EditMaterialDialog.PAGE_INIT_URL + selectedObjectId);
            panRevisions.setReadOnly(false);
            panRevisions.initView();


            formTitle = bundle.getString(FORM_EDITMATERIALDIALOG_TITLE) + " '" + selectedObjectId + "'";

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

            materialService.updateMaterial(material);

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
        return EditMaterialDialog.PAGE_INIT_URL + selectedObjectId;
    }

}