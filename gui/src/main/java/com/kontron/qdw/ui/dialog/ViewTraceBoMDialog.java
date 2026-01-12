package com.kontron.qdw.ui.dialog;

import org.slf4j.*;
import jakarta.faces.context.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import jakarta.servlet.http.*;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.boundary.serial.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.panel.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("viewTraceBoMDialog")
@ViewScoped
public class ViewTraceBoMDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/ViewTraceBoMDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private TraceBoMDTO traceBoM;
    @Generated
    private final transient TraceBoMBoundaryService traceBoMService;
    @Generated
    private long selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final TraceBoMTraceBoMItemsPanel panTraceBoMItems;
    @Generated
    private final TraceBoMIllegalTraceBoMItemsPanel panIllegalTraceBoMItems;

    /**
     * Default constructor
     */
    @Generated
    public ViewTraceBoMDialog() {
        this.traceBoMService = null;
        this.userSession = null;
        this.panTraceBoMItems = null;
        this.panIllegalTraceBoMItems = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param traceBoMService
     * @param userSession
     * @param panTraceBoMItems
     * @param panIllegalTraceBoMItems
     */
    @Inject
    @Generated
    public ViewTraceBoMDialog(TraceBoMBoundaryService traceBoMService, UserSession userSession, TraceBoMTraceBoMItemsPanel panTraceBoMItems,
            TraceBoMIllegalTraceBoMItemsPanel panIllegalTraceBoMItems) {
        this.traceBoMService = traceBoMService;
        this.userSession = userSession;
        this.panTraceBoMItems = panTraceBoMItems;
        this.panIllegalTraceBoMItems = panIllegalTraceBoMItems;
    }

    /**
     * @return the model object
     */
    @Generated
    public TraceBoMDTO getTraceBoM() {
        return traceBoM;
    }

    /**
     * @param traceBoM
     */
    @Generated
    public void setTraceBoM(TraceBoMDTO traceBoM) {
        this.traceBoM = traceBoM;
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
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_READONLY))
            return;


        try {
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            traceBoM = traceBoMService.findTraceBoMById(selectedObjectId);

            panTraceBoMItems.setSelectedObjectId(selectedObjectId);
            panTraceBoMItems.setCurrentPageURL(ViewTraceBoMDialog.PAGE_INIT_URL + selectedObjectId);
            panTraceBoMItems.setReadOnly(true);
            panTraceBoMItems.initView();


            panIllegalTraceBoMItems.setSelectedObjectId(selectedObjectId);
            panIllegalTraceBoMItems.setCurrentPageURL(ViewTraceBoMDialog.PAGE_INIT_URL + selectedObjectId);
            panIllegalTraceBoMItems.setReadOnly(true);
            panIllegalTraceBoMItems.initView();


            formTitle = bundle.getString(FORM_VIEWTRACEBOMDIALOG_TITLE) + " '" + selectedObjectId + "'";

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
        return ViewTraceBoMDialog.PAGE_INIT_URL + selectedObjectId;
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialDialogLink() {
        return ViewMaterialDialog.PAGE_INIT_URL + traceBoM.getMaterialRevisionMaterial().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewMaterialRevisionDialogLink() {
        return ViewMaterialRevisionDialog.PAGE_INIT_URL + traceBoM.getMaterialRevision().getId();
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String openViewSupplierDialogLink() {
        return ViewSupplierDialog.PAGE_INIT_URL
                + java.net.URLEncoder.encode(traceBoM.getSupplier().getCode(), java.nio.charset.StandardCharsets.UTF_8);
    }

}