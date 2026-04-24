package com.kontron.qdw.ui.dialog;

import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import jakarta.servlet.http.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("viewRepairServiceDialog")
@ViewScoped
public class ViewRepairServiceDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewRepairServiceDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private RepairServiceDTO repairService;
    @Generated
    private final transient RepairServiceBoundaryService repairServiceService;
    @Generated
    private String selectedObjectId;
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
    public ViewRepairServiceDialog() {
        this.repairServiceService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repairServiceService
     * @param userSession
     */
    @Inject
    @Generated
    public ViewRepairServiceDialog(RepairServiceBoundaryService repairServiceService, UserSession userSession) {
        this.repairServiceService = repairServiceService;
        this.userSession = userSession;
    }

    /**
     * @return the model object
     */
    @Generated
    public RepairServiceDTO getRepairService() {
        return repairService;
    }

    /**
     * @param repairService
     */
    @Generated
    public void setRepairService(RepairServiceDTO repairService) {
        this.repairService = repairService;
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

            repairService = repairServiceService.findRepairServiceById(selectedObjectId);

            formTitle = bundle.getString(FORM_VIEWREPAIRSERVICEDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
        return ViewRepairServiceDialog.PAGE_INIT_URL + java.net.URLEncoder.encode(selectedObjectId, java.nio.charset.StandardCharsets.UTF_8);
    }

}