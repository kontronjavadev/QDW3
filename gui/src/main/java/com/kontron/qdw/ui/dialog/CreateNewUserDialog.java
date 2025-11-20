package com.kontron.qdw.ui.dialog;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import org.primefaces.model.DualListModel;
import com.kontron.qdw.boundary.util.Constants;
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
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("createNewUserDialog")
@ViewScoped
public class CreateNewUserDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/CreateNewUserDialog.jsf?faces-redirect=true";
    @Generated
    private UserCreateDTO user;
    @Generated
    private final transient UserBoundaryService userService;
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
    public CreateNewUserDialog() {
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
    public CreateNewUserDialog(UserBoundaryService userService, UserSession userSession, RoleBoundaryService roleService) {
        this.userService = userService;
        this.userSession = userSession;
        this.roleService = roleService;
    }

    /**
     * Initialize dialog
     */
    @Customized
    public void initView() {
        logger.debug("Initialize dialog");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR)) {
            return;
        }

        try {
            user = new UserCreateDTO();
            user.setActive(true);

            final List<RoleListDTO> rolesSourceList = roleService.findRoles(null);
            final var rolesTargetList = new ArrayList<RoleListDTO>();

            rolesList = new DualListModel<>(rolesSourceList, rolesTargetList);
            RoleListDTO roRole = rolesList.getSource().stream()
                    .filter(dto -> dto.getName().equals(UserSession.ROLE_READONLY))
                    .findAny()
                    .get();
            rolesList.getSource().remove(roRole);
            rolesList.getTarget().add(roRole);


            formTitle = bundle.getString(FORM_CREATENEWUSERDIALOG_TITLE);

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
    @Customized
    public String save() {
        try {
            logger.debug("Perform save operation");

            // Neben dem ADMINISTRATOR darf auch der USER_ADMINISTRATOR User verwalten, jedoch darf er einige Dinge nicht:
            // * er darf einem Anwender keine Rolle ADMINISTRATOR nehmen oder geben
            // * er darf keine "Testaccounts" erstellen oder ändern; Problem: wie erkennt man das?
            boolean isAdmin = userSession.checkAuthorization(false, ROLE_ADMINISTRATOR);

            if (!isAdmin /*&& user.getSupplier() != null*/) {
                // ein USER_ADMINISTRATOR darf keine Testaccounts für Supplier ändern (etwa aktivieren)
                for (String forbiddenMailDomain : Constants.MAILDOMAIN_BLACK_LIST) {
                    if (user.getEmail().contains("@" + forbiddenMailDomain + ".")) {
                        logger.error("USER_ADMINISTRATOR trying to save supplier with test account");
                        MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, INVALID_MAIL_ADDRESS);
                        return "";
                    }
                }
            }

            if (!isAdmin) {
                // ein USER_ADMINISTRATOR darf keine Rolle ADMINISTRATOR nehmen oder geben
                Optional<RoleListDTO> adminRoleInTarget = rolesList.getTarget().stream()
                        .filter(target -> target.getName().equals(ROLE_ADMINISTRATOR))
                        .findFirst();

                if (adminRoleInTarget.isPresent()) {
                    rolesList.getTarget().remove(adminRoleInTarget.get());
                    rolesList.getSource().add(adminRoleInTarget.get());
                }
            }

            user.setRoles(rolesList.getTarget());
            userService.createUser(user);

            return userSession.getLastPage();
        }
        catch (final Exception e) {
            logger.error("Error while performing save operation!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_SAVE_FAIL, e);
            return "";
        }
    }

    /**
     * @return the model object
     */
    @Generated
    public UserCreateDTO getUser() {
        return user;
    }

    /**
     * @param user
     */
    @Generated
    public void setUser(UserCreateDTO user) {
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
     * @return the URL of the current page
     */
    @Generated
    public String getCurrentPageURL() {
        return CreateNewUserDialog.PAGE_INIT_URL;
    }

}
