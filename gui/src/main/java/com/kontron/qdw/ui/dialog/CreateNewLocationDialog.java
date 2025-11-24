package com.kontron.qdw.ui.dialog;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
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

@Named("createNewLocationDialog")
@ViewScoped
public class CreateNewLocationDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/CreateNewLocationDialog.jsf?faces-redirect=true";
    @Generated
    private LocationCreateDTO location;
    @Generated
    private final transient LocationBoundaryService locationService;
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
    public CreateNewLocationDialog() {
        this.locationService = null;
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param locationService
     * @param userSession
     */
    @Inject
    @Generated
    public CreateNewLocationDialog(LocationBoundaryService locationService, UserSession userSession) {
        this.locationService = locationService;
        this.userSession = userSession;
    }

    /**
     * @return the model object
     */
    @Generated
    public LocationCreateDTO getLocation() {
        return location;
    }

    /**
     * @param location
     */
    @Generated
    public void setLocation(LocationCreateDTO location) {
        this.location = location;
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
            location = new LocationCreateDTO();
            location.setActive(true);

            formTitle = bundle.getString(FORM_CREATENEWLOCATIONDIALOG_TITLE);

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

            locationService.createLocation(location);

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
        return CreateNewLocationDialog.PAGE_INIT_URL;
    }

}