package com.kontron.qdw.ui.dialog;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import org.primefaces.model.DualListModel;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.dto.base.*;
import jakarta.servlet.http.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("editUserDialog")
@ViewScoped
public class EditUserDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/EditUserDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private UserUpdateDTO user;
    @Generated
    private final transient UserBoundaryService userService;
    @Generated
    private long selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient RoleBoundaryService roleService;
    @Generated
    private DualListModel<RoleListDTO> rolesList;

    /**
     * Default constructor
     */
    @Generated
    public EditUserDialog() {
        this.userService = null;
        this.userSession = null;
        this.roleService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userService
     * @param userSession
     * @param roleService
     */
    @Inject
    @Generated
    public EditUserDialog(UserBoundaryService userService, UserSession userSession, RoleBoundaryService roleService) {
        this.userService = userService;
        this.userSession = userSession;
        this.roleService = roleService;
    }

    /**
     * @return the model object
     */
    @Generated
    public UserUpdateDTO getUser() {
        return user;
    }

    /**
     * @param user
     */
    @Generated
    public void setUser(UserUpdateDTO user) {
        this.user = user;
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
     * @return the item list
     */
    @Generated
    public DualListModel<RoleListDTO> getRolesList() {
        return rolesList;
    }

    /**
     * @param rolesList
     */
    @Generated
    public void setRolesList(DualListModel<RoleListDTO> rolesList) {
        this.rolesList = rolesList;
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

            user = userService.getUserForUpdate(selectedObjectId);

            final List<RoleListDTO> rolesSourceList = roleService.findRoles(null);
            final ArrayList<RoleListDTO> rolesTargetList = new ArrayList<>(user.getRoles());

            rolesSourceList.removeAll(rolesTargetList);

            rolesList = new DualListModel<>(rolesSourceList, rolesTargetList);


            formTitle = bundle.getString(FORM_EDITUSERDIALOG_TITLE) + " '" + selectedObjectId + "'";

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

            user.setRoles(rolesList.getTarget());
            userService.updateUser(user);

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
        return EditUserDialog.PAGE_INIT_URL + selectedObjectId;
    }

}