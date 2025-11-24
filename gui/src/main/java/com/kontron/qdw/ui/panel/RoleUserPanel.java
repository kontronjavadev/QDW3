package com.kontron.qdw.ui.panel;

import static com.kontron.qdw.ui.TranslationKeys.DIALOG_INIT_FAIL;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;
import static com.kontron.qdw.ui.UserSession.ROLE_ADMINISTRATOR;
import static com.kontron.qdw.ui.UserSession.ROLE_USER_ADMINISTRATOR;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.UserBoundaryService;
import com.kontron.qdw.dto.base.UserOfRoleDTO;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.dialog.EditUserDialog;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("roleUserPanel")
@ViewScoped
public class RoleUserPanel extends CopyClipboard implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;
    private List<UserOfRoleDTO> usersList = new ArrayList<>();
    private UserOfRoleDTO selItemOfUsers;
    private final UserSession userSession;
    private transient ResourceBundle bundle;
    private long selectedObjectId;
    private String currentPageURL;
    private boolean readOnly;
    private final transient UserBoundaryService userService;

    /**
     * Default constructor
     */
    public RoleUserPanel() {
        this.userSession = null;
        this.userService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param userService
     */
    @Inject
    public RoleUserPanel(UserSession userSession, UserBoundaryService userService) {
        this.userSession = userSession;
        this.userService = userService;
    }

    public Collection<UserOfRoleDTO> getUsersList() {
        return usersList;
    }

    public UserOfRoleDTO getSelItemOfUsers() {
        return selItemOfUsers;
    }

    public void setSelItemOfUsers(UserOfRoleDTO selItemOfUsers) {
        this.selItemOfUsers = selItemOfUsers;
    }

    /**
     * @return the URL of the page this grid panel is included in
     */
    public String getCurrentPageURL() {
        return currentPageURL;
    }

    public void setCurrentPageURL(String currentPageURL) {
        this.currentPageURL = currentPageURL;
    }

    public long getSelectedObjectId() {
        return selectedObjectId;
    }

    public void setSelectedObjectId(long selectedObjectId) {
        this.selectedObjectId = selectedObjectId;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    public void onUsersGridDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openEditUserDialog());
    }

    public String openEditUserDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_USER_ADMINISTRATOR)) {
            url = EditUserDialog.PAGE_INIT_URL + selItemOfUsers.getId();
        }

        return url;
    }

    /**
     * Initialize view
     */
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchUsers();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    public void fetchUsers() {
        // Get data from server
        try {
            usersList = new ArrayList<>(userService.findUsersWithRole(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}
