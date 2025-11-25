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

@Named("createNewVerticalSectorDialog")
@ViewScoped
public class CreateNewVerticalSectorDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/CreateNewVerticalSectorDialog.jsf?faces-redirect=true";
    @Generated
    private VerticalSectorCreateDTO verticalSector;
    @Generated
    private final transient VerticalSectorBoundaryService verticalSectorService;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient BusinessUnitBoundaryService businessUnitService;
    @Generated
    private transient Collection<BusinessUnitListDTO> businessUnitList;

    /**
     * Default constructor
     */
    @Generated
    public CreateNewVerticalSectorDialog() {
        this.verticalSectorService = null;
        this.userSession = null;
        this.businessUnitService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param verticalSectorService
     * @param userSession
     * @param businessUnitService
     */
    @Inject
    @Generated
    public CreateNewVerticalSectorDialog(VerticalSectorBoundaryService verticalSectorService, UserSession userSession,
            BusinessUnitBoundaryService businessUnitService) {
        this.verticalSectorService = verticalSectorService;
        this.userSession = userSession;
        this.businessUnitService = businessUnitService;
    }

    /**
     * @return the model object
     */
    @Generated
    public VerticalSectorCreateDTO getVerticalSector() {
        return verticalSector;
    }

    /**
     * @param verticalSector
     */
    @Generated
    public void setVerticalSector(VerticalSectorCreateDTO verticalSector) {
        this.verticalSector = verticalSector;
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
     * @return the business unit list
     */
    @Generated
    public Collection<BusinessUnitListDTO> getBusinessUnitList() {
        return businessUnitList;
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
            verticalSector = new VerticalSectorCreateDTO();
            verticalSector.setActive(true);
            businessUnitList = businessUnitService.findBusinessUnits(null);

            if (!businessUnitList.isEmpty())
                verticalSector.setBusinessUnit(businessUnitList.iterator().next());


            formTitle = bundle.getString(FORM_CREATENEWVERTICALSECTORDIALOG_TITLE);

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

            verticalSectorService.createVerticalSector(verticalSector);

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
        return CreateNewVerticalSectorDialog.PAGE_INIT_URL;
    }

}