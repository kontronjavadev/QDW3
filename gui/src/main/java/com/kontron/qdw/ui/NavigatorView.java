package com.kontron.qdw.ui;

import org.primefaces.model.*;
import java.util.*;
import jakarta.enterprise.context.*;
import jakarta.faces.context.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.tree.*;
import static com.kontron.qdw.ui.UserSession.*;
import jakarta.annotation.PostConstruct;
import jakarta.inject.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;
import jakarta.servlet.http.*;

@Named("navigatorView")
@SessionScoped
public class NavigatorView implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private static final String FOLDER_TYPE = "folder_type";
    @Generated
    private transient TreeNode<String> root;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private static final String VIEW_TYPE = "view_type";

    /**
     * Default constructor
     */
    @Generated
    public NavigatorView() {
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     */
    @Inject
    @Generated
    public NavigatorView(UserSession userSession) {
        this.userSession = userSession;
    }

    /**
     * Initialize tree navigator
     */
    @Generated
    @PostConstruct
    public void init() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final var req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());
        root = new DefaultTreeNode<>("root", null);


        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            // Form group: Administration
            final var itemGroup0001 = new DefaultTreeNode<>(FOLDER_TYPE, new TreeNavigatorItem(bundle.getString(FG_TOP_ADMINISTRATION)), root);
            itemGroup0001.setExpanded(true);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_USERVIEW_TITLE), req.getContextPath() + "/view/UserView.jsf"), itemGroup0001);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_ROLEVIEW_TITLE), req.getContextPath() + "/view/RoleView.jsf"), itemGroup0001);
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            // Form group: Master data
            final var itemGroup0002 = new DefaultTreeNode<>(FOLDER_TYPE, new TreeNavigatorItem(bundle.getString(FG_TOP_MASTER_DATA)), root);
            itemGroup0002.setExpanded(true);
        }
    }

    /**
     * @return the root element of the tree
     */
    @Generated
    public TreeNode<String> getRoot() {
        return root;
    }

}