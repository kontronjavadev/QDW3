package com.kontron.qdw.ui.view;

import org.slf4j.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import java.text.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("repairErrorCodeView")
@ViewScoped
public class RepairErrorCodeView extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<RepairErrorCodeSearchDTO> repairErrorCodesList = new ArrayList<>();
    @Generated
    private RepairErrorCodeSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient RepairErrorCodeBoundaryService repairErrorCodeService;
    @Generated
    public static final String PAGE_URL = "/view/RepairErrorCodeView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";

    /**
     * Default constructor
     */
    @Generated
    public RepairErrorCodeView() {
        this.userSession = null;
        this.repairErrorCodeService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param repairErrorCodeService
     */
    @Inject
    @Generated
    public RepairErrorCodeView(UserSession userSession, RepairErrorCodeBoundaryService repairErrorCodeService) {
        this.userSession = userSession;
        this.repairErrorCodeService = repairErrorCodeService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<RepairErrorCodeSearchDTO> getRepairErrorCodesList() {
        return repairErrorCodesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public RepairErrorCodeSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(RepairErrorCodeSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openEditRepairErrorCodeDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteRepairErrorCode() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getCode());

            repairErrorCodeService.deleteRepairErrorCode(selectedObject.getCode());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchRepairErrorCodes();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        var url = "";
        String newId;

        try {
            logger.debug("Create a copy of the selected object with id '{}'", selectedObject.getCode());

            newId = repairErrorCodeService.copy(selectedObject.getCode(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            url = EditRepairErrorCodeDialog.PAGE_INIT_URL + java.net.URLEncoder.encode(newId, java.nio.charset.StandardCharsets.UTF_8);
        }

        userSession.setLastPage(getCurrentPageURL());
        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openCreateNewRepairErrorCodeDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            url = CreateNewRepairErrorCodeDialog.PAGE_INIT_URL;
        }

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openEditRepairErrorCodeDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            url = EditRepairErrorCodeDialog.PAGE_INIT_URL
                    + java.net.URLEncoder.encode(selectedObject.getCode(), java.nio.charset.StandardCharsets.UTF_8);
        }

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openViewRepairErrorCodeDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR)) {
            url = ViewRepairErrorCodeDialog.PAGE_INIT_URL
                    + java.net.URLEncoder.encode(selectedObject.getCode(), java.nio.charset.StandardCharsets.UTF_8);
        }

        return url;
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
     * @return the URL of the current page
     */
    @Generated
    public String getCurrentPageURL() {
        return PAGE_URL;
    }

    /**
     * Initialize search object
     */
    @Generated
    public void initSearchObject() {
        final var searchObj = new SearchDTO();
        int colOrderId = -1;

        // Initialize search object
        searchObj.setMaxResult(1000);
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(false);
        searchObj.setCount(false);
        searchObj.setDateFormat(userSession.getDateFormat());
        searchObj.setDateTimeFormat(userSession.getDateTimeFormat());
        searchObj.setNumberFormat(userSession.getNumberFormat());
        searchObj.setDecimalSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getDecimalSeparator());
        searchObj.setGroupingSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getGroupingSeparator());


        final var field1 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_CODE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNCTIONALACTIVEENTITY_CODE), SearchFieldDataTypeEnum.STRING, 100);
        searchObj.getSearchFields().add(field1);


        final var field2 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_NAME, bundle.getString(LBL_ATTR_REPAIRERRORCODE_NAME),
                SearchFieldDataTypeEnum.STRING, 250);
        searchObj.getSearchFields().add(field2);


        final var field3 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_SHORTTEXT,
                bundle.getString(LBL_ATTR_ABSTRACTFUNCTIONALACTIVEENTITY_SHORTTEXT), SearchFieldDataTypeEnum.STRING, 250);
        searchObj.getSearchFields().add(field3);


        final var field4 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_GROUPNAME,
                bundle.getString(LBL_ATTR_REPAIRERRORCODE_GROUPNAME), SearchFieldDataTypeEnum.STRING, 150);
        searchObj.getSearchFields().add(field4);


        final var field5 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_COMMENT,
                bundle.getString(LBL_ATTR_ABSTRACTFUNCTIONALACTIVEENTITY_COMMENT), SearchFieldDataTypeEnum.STRING, 150);
        searchObj.getSearchFields().add(field5);


        final var field6 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_ACTIVE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNCTIONALACTIVEENTITY_ACTIVE), SearchFieldDataTypeEnum.BOOLEAN, 70);
        searchObj.getSearchFields().add(field6);


        final var field7 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNCTIONALACTIVEENTITY_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        searchObj.getSearchFields().add(field7);


        final var field8 = new SearchFieldDTO(++colOrderId, RepairErrorCodeSearchDTO.SELECT_LASTUPDATE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNCTIONALACTIVEENTITY_LASTUPDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        searchObj.getSearchFields().add(field8);


        logger.debug("Perform data fetch operation");

        try {
            repairErrorCodesList = repairErrorCodeService.searchAllRepairErrorCodes(searchObj);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_FETCH_FAIL, e);
        }
    }

    /**
     * Initialize view
     */
    @Generated
    public void fetchRepairErrorCodes() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR)) {
            return;
        }


        formTitle = bundle.getString(FORM_REPAIRERRORCODEVIEW_TITLE);

        initSearchObject();

        logger.debug("View initialization finished");
    }

}
