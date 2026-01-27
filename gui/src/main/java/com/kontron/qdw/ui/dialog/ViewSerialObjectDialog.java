package com.kontron.qdw.ui.dialog;

import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import jakarta.servlet.http.*;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.boundary.serial.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.panel.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("viewSerialObjectDialog")
@ViewScoped
public class ViewSerialObjectDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewSerialObjectDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private SerialObjectDTO serialObject;
    @Generated
    private final transient SerialObjectBoundaryService serialObjectService;
    @Generated
    private long selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final SerialObjectSerialObjectsPanel panSerialObjects;

    /**
     * Default constructor
     */
    @Generated
    public ViewSerialObjectDialog() {
        this.serialObjectService = null;
        this.userSession = null;
        this.panSerialObjects = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param serialObjectService
     * @param userSession
     * @param panSerialObjects
     */
    @Inject
    @Generated
    public ViewSerialObjectDialog(SerialObjectBoundaryService serialObjectService, UserSession userSession,
            SerialObjectSerialObjectsPanel panSerialObjects) {
        this.serialObjectService = serialObjectService;
        this.userSession = userSession;
        this.panSerialObjects = panSerialObjects;
    }

    /**
     * @return the model object
     */
    @Generated
    public SerialObjectDTO getSerialObject() {
        return serialObject;
    }

    /**
     * @param serialObject
     */
    @Generated
    public void setSerialObject(SerialObjectDTO serialObject) {
        this.serialObject = serialObject;
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            serialObject = serialObjectService.findSerialObjectById(selectedObjectId);

            panSerialObjects.setSelectedObjectId(selectedObjectId);
            panSerialObjects.setCurrentPageURL(ViewSerialObjectDialog.PAGE_INIT_URL + selectedObjectId);
            panSerialObjects.setReadOnly(true);
            panSerialObjects.initView();


            formTitle = bundle.getString(FORM_VIEWSERIALOBJECTDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
        return ViewSerialObjectDialog.PAGE_INIT_URL + selectedObjectId;
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewSerialObjectDialogLink() {
        return ViewSerialObjectDialog.PAGE_INIT_URL + serialObject.getParentObject().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewTraceBoMDialogLink() {
        return ViewTraceBoMDialog.PAGE_INIT_URL + serialObject.getTraceBom().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialDialogLink() {
        return ViewMaterialDialog.PAGE_INIT_URL + serialObject.getMaterial().getId();
    }

}