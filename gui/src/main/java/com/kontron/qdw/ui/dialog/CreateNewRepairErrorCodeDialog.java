package com.kontron.qdw.ui.dialog;

import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.servlet.http.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("createNewRepairErrorCodeDialog")
@ViewScoped
public class CreateNewRepairErrorCodeDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/CreateNewRepairErrorCodeDialog.jsf?faces-redirect=true";
    @Generated
    private RepairErrorCodeCreateDTO repairErrorCode;
    @Generated
    private final transient RepairErrorCodeBoundaryService repairErrorCodeService;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;

    /**
     * Default constructor
     */
    @Generated
    public CreateNewRepairErrorCodeDialog() {
        this.repairErrorCodeService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repairErrorCodeService
     * @param userSession
     */
    @Inject
    @Generated
    public CreateNewRepairErrorCodeDialog(RepairErrorCodeBoundaryService repairErrorCodeService, UserSession userSession) {
        this.repairErrorCodeService = repairErrorCodeService;
        this.userSession = userSession;
    }

    /**
     * @return the model object
     */
    @Generated
    public RepairErrorCodeCreateDTO getRepairErrorCode() {
        return repairErrorCode;
    }

    /**
     * @param repairErrorCode
     */
    @Generated
    public void setRepairErrorCode(RepairErrorCodeCreateDTO repairErrorCode) {
        this.repairErrorCode = repairErrorCode;
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
     * Callback method for auto-complete field 'cboMappedTo'
     * @param filter the filter criterion inserted the by the user
     * @return the proposal list
     */
    @Generated
    public List<RepairErrorCodeListDTO> onCompleteMappedTo(String filter) {
        try {
            return repairErrorCodeService.findRepairErrorCodes(filter);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for proposal text field 'cboMappedTo'!", e);

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
            repairErrorCode = new RepairErrorCodeCreateDTO();
            repairErrorCode.setActive(true);

            formTitle = bundle.getString(FORM_CREATENEWREPAIRERRORCODEDIALOG_TITLE);

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

            repairErrorCodeService.createRepairErrorCode(repairErrorCode);

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
        return CreateNewRepairErrorCodeDialog.PAGE_INIT_URL;
    }

}