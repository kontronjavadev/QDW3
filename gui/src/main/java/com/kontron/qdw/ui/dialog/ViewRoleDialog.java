package com.kontron.qdw.ui.dialog;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import com.kontron.qdw.dto.base.*;
import jakarta.servlet.http.*;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("viewRoleDialog")
@ViewScoped
public class ViewRoleDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewRoleDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private RoleDTO role;
    @Generated
    private final transient RoleBoundaryService roleService;
    @Generated
    private long selectedObjectId;
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
    public ViewRoleDialog() {
        this.roleService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param roleService
     * @param userSession
     */
    @Inject
    @Generated
    public ViewRoleDialog(RoleBoundaryService roleService, UserSession userSession) {
        this.roleService = roleService;
        this.userSession = userSession;
    }

    /**
     * @return the model object
     */
    @Generated
    public RoleDTO getRole() {
        return role;
    }

    /**
     * @param role
     */
    @Generated
    public void setRole(RoleDTO role) {
        this.role = role;
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            role = roleService.findRoleById(selectedObjectId);

            formTitle = bundle.getString(FORM_VIEWROLEDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
        return ViewRoleDialog.PAGE_INIT_URL + selectedObjectId;
    }

}