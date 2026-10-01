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

@Named("assemblyCheckDialog")
@ViewScoped
public class AssemblyCheckDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/AssemblyCheckDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private SerialObjectAssemblyCheckDTO serialObject;
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
    private final TraceBoMAssemblyCheckPanel panAssyCheck;

    /**
     * Default constructor
     */
    @Generated
    public AssemblyCheckDialog() {
        this.serialObjectService = null;
        this.userSession = null;
        this.panAssyCheck = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param serialObjectService
     * @param userSession
     * @param panServiceMessages
     */
    @Inject
    @Generated
    public AssemblyCheckDialog(SerialObjectBoundaryService serialObjectService, UserSession userSession,
            TraceBoMAssemblyCheckPanel panAssyCheck) {
        this.serialObjectService = serialObjectService;
        this.userSession = userSession;
        this.panAssyCheck = panAssyCheck;
    }

    /**
     * @return the model object
     */
    @Generated
    public SerialObjectAssemblyCheckDTO getSerialObject() {
        return serialObject;
    }

    /**
     * @param serialObject
     */
    @Generated
    public void setSerialObject(SerialObjectAssemblyCheckDTO serialObject) {
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return;
        }


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            serialObject = serialObjectService.findSerialObjectAssemblyCheck(selectedObjectId);

            panAssyCheck.setSelectedSerialObjectId(selectedObjectId);
            panAssyCheck.setCurrentPageURL(AssemblyCheckDialog.PAGE_INIT_URL + selectedObjectId);
            panAssyCheck.initView();


            formTitle = bundle.getString(FORM_ASSEMBLYCHECKDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
        return AssemblyCheckDialog.PAGE_INIT_URL + selectedObjectId;
    }

}
