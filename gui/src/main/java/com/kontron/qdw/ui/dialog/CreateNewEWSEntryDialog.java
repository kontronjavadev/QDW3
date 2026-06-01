package com.kontron.qdw.ui.dialog;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import com.kontron.qdw.domain.material.*;
import org.primefaces.model.DualListModel;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.servlet.http.*;
import com.kontron.qdw.dto.base.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.dto.material.*;
import jakarta.faces.model.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("createNewEWSEntryDialog")
@ViewScoped
public class CreateNewEWSEntryDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/CreateNewEWSEntryDialog.jsf?faces-redirect=true";
    @Generated
    private EWSEntryCreateDTO eWSEntry;
    @Generated
    private final transient EWSEntryBoundaryService eWSEntryService;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterialBoundaryService materialService;
    @Generated
    private final transient UserBoundaryService userService;
    @Generated
    private DualListModel<UserListDTO> receiversList;
    @Generated
    private String receiversFilterCriterion;

    /**
     * Default constructor
     */
    @Generated
    public CreateNewEWSEntryDialog() {
        this.eWSEntryService = null;
        this.userSession = null;
        this.materialService = null;
        this.userService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param eWSEntryService
     * @param userSession
     * @param materialService
     * @param userService
     */
    @Inject
    @Generated
    public CreateNewEWSEntryDialog(EWSEntryBoundaryService eWSEntryService, UserSession userSession, MaterialBoundaryService materialService,
            UserBoundaryService userService) {
        this.eWSEntryService = eWSEntryService;
        this.userSession = userSession;
        this.materialService = materialService;
        this.userService = userService;
    }

    /**
     * Perform filter operation
     */
    @Customized
    public void filterReceivers() {
        receiversList = new DualListModel<>(Collections.emptyList(), receiversList.getTarget());

        try {
            final List<UserListDTO> receiversSourceList = userService.findUsers(receiversFilterCriterion);
            receiversSourceList.removeAll(receiversList.getTarget());

            receiversList = new DualListModel<>(receiversSourceList, receiversList.getTarget());
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for list field 'listReceivers'!", e);
        }
    }

    /**
     * @return the model object
     */
    @Generated
    public EWSEntryCreateDTO geteWSEntry() {
        return eWSEntry;
    }

    /**
     * @param eWSEntry
     */
    @Generated
    public void seteWSEntry(EWSEntryCreateDTO eWSEntry) {
        this.eWSEntry = eWSEntry;
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
     * Callback method for auto-complete field 'cboMaterial'
     * @param filter the filter criterion inserted the by the user
     * @return the proposal list
     */
    @Generated
    public List<MaterialListDTO> onCompleteMaterial(String filter) {
        try {
            return materialService.findMaterials(filter);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for proposal text field 'cboMaterial'!", e);

            return Collections.emptyList();
        }
    }

    /**
     * @return an array of selectable items
     */
    @Generated
    public SelectItem[] getTypeList() {
        final var items = new SelectItem[EWSType.values().length];
        int i = 0;

        for (final EWSType item : EWSType.values()) {
            items[i++] = new SelectItem(item, bundle.getString("ewstype_" + item.name().toLowerCase()));
        }

        return items;
    }

    /**
     * @return the item list
     */
    @Generated
    public DualListModel<UserListDTO> getReceiversList() {
        return receiversList;
    }

    /**
     * @param receiversList
     */
    @Generated
    public void setReceiversList(DualListModel<UserListDTO> receiversList) {
        this.receiversList = receiversList;
    }

    /**
     * @return the filter criterion
     */
    @Generated
    public String getReceiversFilterCriterion() {
        return receiversFilterCriterion;
    }

    /**
     * @param receiversFilterCriterion
     */
    @Generated
    public void setReceiversFilterCriterion(String receiversFilterCriterion) {
        this.receiversFilterCriterion = receiversFilterCriterion;
    }

    /**
     * Initialize dialog
     */
    @Generated
    public void initView() {
        logger.debug("Initialize dialog");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR)) {
            return;
        }


        try {
            eWSEntry = new EWSEntryCreateDTO();
            eWSEntry.setThreshold(1.0);
            eWSEntry.setType(EWSType.values()[0]);
            eWSEntry.setBoardOrSystem(true);

            final var receiversSourceList = new ArrayList<UserListDTO>();
            final var receiversTargetList = new ArrayList<UserListDTO>();

            receiversList = new DualListModel<>(receiversSourceList, receiversTargetList);


            formTitle = bundle.getString(FORM_CREATENEWEWSENTRYDIALOG_TITLE);

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

            eWSEntry.setReceivers(receiversList.getTarget());
            eWSEntryService.createEWSEntry(eWSEntry);

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
        return CreateNewEWSEntryDialog.PAGE_INIT_URL;
    }

}
