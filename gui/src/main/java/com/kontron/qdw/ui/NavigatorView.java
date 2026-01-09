package com.kontron.qdw.ui;

import org.primefaces.model.*;
import jakarta.enterprise.context.*;
import jakarta.faces.context.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.tree.*;
import org.primefaces.event.NodeCollapseEvent;
import com.kontron.qdw.boundary.util.Constants;
import static com.kontron.qdw.ui.TranslationKeys.*;
import jakarta.servlet.http.*;
import java.util.*;
import org.primefaces.event.NodeExpandEvent;
import static com.kontron.qdw.ui.UserSession.*;
import jakarta.annotation.PostConstruct;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;
import java.text.MessageFormat;

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

    public void nodeExpanded(NodeExpandEvent event) {
        // workaround, damit das Zuklappen der Listen gemerkt wird
    }

    public void nodeCollapsed(NodeCollapseEvent event) {
        // workaround, damit das Zuklappen der Listen gemerkt wird
    }

    public String getEnv() {
        if (Constants.IS_PROD_ENVIRONMENT) {
            return "";
        }

        return new MessageFormat(bundle.getString(APPLICATION_TITLE_ENVIRONMENT)).format(new Object[] { Constants.ENVIRONMENT });
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


        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_USER_ADMINISTRATOR, ROLE_MAINTAINER)) {
            // Form group: Administration
            final var itemGroup0001 = new DefaultTreeNode<>(FOLDER_TYPE, new TreeNavigatorItem(bundle.getString(FG_TOP_ADMINISTRATION)), root);
            itemGroup0001.setExpanded(false);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_USER_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_USERVIEW_TITLE), req.getContextPath() + "/view/UserView.jsf"), itemGroup0001);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_USER_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_ROLEVIEW_TITLE), req.getContextPath() + "/view/RoleView.jsf"), itemGroup0001);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_MAINTAINER))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_NOTIFICATIONVIEW_TITLE), req.getContextPath() + "/view/NotificationView.jsf"),
                        itemGroup0001);
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            // Form group: Master data
            final var itemGroup0002 = new DefaultTreeNode<>(FOLDER_TYPE, new TreeNavigatorItem(bundle.getString(FG_TOP_MASTER_DATA)), root);
            itemGroup0002.setExpanded(false);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_BUSINESSUNITVIEW_TITLE), req.getContextPath() + "/view/BusinessUnitView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_COUNTRYVIEW_TITLE), req.getContextPath() + "/view/CountryView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_CUSTOMERVIEW_TITLE), req.getContextPath() + "/view/CustomerView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_LOCATIONVIEW_TITLE), req.getContextPath() + "/view/LocationView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_MATERIALCLASSVIEW_TITLE), req.getContextPath() + "/view/MaterialClassView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_MATERIALTYPEVIEW_TITLE), req.getContextPath() + "/view/MaterialTypeView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_MOVEMENTTYPEVIEW_TITLE), req.getContextPath() + "/view/MovementTypeView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_PLANTVIEW_TITLE), req.getContextPath() + "/view/PlantView.jsf"), itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_SUPPLIERVIEW_TITLE), req.getContextPath() + "/view/SupplierView.jsf"),
                        itemGroup0002);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_VERTICALSECTORVIEW_TITLE), req.getContextPath() + "/view/VerticalSectorView.jsf"),
                        itemGroup0002);
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER)) {
            // Form group: Material
            final var itemGroup0003 = new DefaultTreeNode<>(FOLDER_TYPE, new TreeNavigatorItem(bundle.getString(FG_TOP_MATERIAL)), root);
            itemGroup0003.setExpanded(false);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER))
                new DefaultTreeNode<>(VIEW_TYPE, new TreeNavigatorItem(bundle.getString(FORM_MATERIALREVISIONVIEW_TITLE),
                        req.getContextPath() + "/view/MaterialRevisionView.jsf"), itemGroup0003);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY, ROLE_SUPERUSER))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_MATERIALVIEW_TITLE), req.getContextPath() + "/view/MaterialView.jsf"),
                        itemGroup0003);
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            // Form group: Serial object
            final var itemGroup0004 = new DefaultTreeNode<>(FOLDER_TYPE, new TreeNavigatorItem(bundle.getString(FG_TOP_SERIAL_OBJECT)), root);
            itemGroup0004.setExpanded(true);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE, new TreeNavigatorItem(bundle.getString(FORM_AGGREGATEDARRIVALVIEW_TITLE),
                        req.getContextPath() + "/view/AggregatedArrivalView.jsf"), itemGroup0004);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_ARRIVALLASTVIEW_TITLE), req.getContextPath() + "/view/ArrivalLastView.jsf"),
                        itemGroup0004);
            
            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE, new TreeNavigatorItem(bundle.getString(FORM_MATERIALIZEDARRIVALVIEW_TITLE),
                        req.getContextPath() + "/view/MaterializedArrivalView.jsf"), itemGroup0004);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE,
                        new TreeNavigatorItem(bundle.getString(FORM_ARRIVALVIEW_TITLE), req.getContextPath() + "/view/ArrivalView.jsf"),
                        itemGroup0004);



            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE, new TreeNavigatorItem(bundle.getString(FORM_AGGREGATEDSHIPMENTARRIVALVIEW_TITLE),
                        req.getContextPath() + "/view/AggregatedShipmentArrivalView.jsf"), itemGroup0004);



            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE, new TreeNavigatorItem(bundle.getString(FORM_AGGREGATEDSHIPMENTVIEW_TITLE),
                        req.getContextPath() + "/view/AggregatedShipmentView.jsf"), itemGroup0004);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE, new TreeNavigatorItem(bundle.getString(FORM_MATERIALIZEDARRIVALSHIPMENTVIEW_TITLE),
                        req.getContextPath() + "/view/MaterializedArrivalShipmentView.jsf"), itemGroup0004);

            if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY))
                new DefaultTreeNode<>(VIEW_TYPE, new TreeNavigatorItem(bundle.getString(FORM_MATERIALIZEDASSEMBLYSHIPMENTVIEW_TITLE),
                        req.getContextPath() + "/view/MaterializedAssemblyShipmentView.jsf"), itemGroup0004);
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