package com.kontron.qdw.ui.view;

import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import com.kontron.qdw.ui.dialog.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import java.text.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("materialTypeView")
@ViewScoped
public class MaterialTypeView extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<MaterialTypeSearchDTO> materialTypesList = new ArrayList<>();
    @Generated
    private MaterialTypeSearchDTO selectedObject;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient MaterialTypeBoundaryService materialTypeService;
    @Generated
    public static final String PAGE_URL = "/view/MaterialTypeView.jsf?faces-redirect=true";
    @Generated
    private String formTitle = "";

    /**
     * Default constructor
     */
    @Generated
    public MaterialTypeView() {
        this.userSession = null;
        this.materialTypeService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param materialTypeService
     */
    @Inject
    @Generated
    public MaterialTypeView(UserSession userSession, MaterialTypeBoundaryService materialTypeService) {
        this.userSession = userSession;
        this.materialTypeService = materialTypeService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<MaterialTypeSearchDTO> getMaterialTypesList() {
        return materialTypesList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public MaterialTypeSearchDTO getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     */
    @Generated
    public void setSelectedObject(MaterialTypeSearchDTO selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onDoubleClick() {
        logger.debug("Handle double-click event");

        userSession.redirectTo(getCurrentPageURL(), openEditMaterialTypeDialog());
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteMaterialType() {
        try {
            logger.debug("Delete selected object with id '{}'", selectedObject.getCode());

            materialTypeService.deleteMaterialType(selectedObject.getCode());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchMaterialTypes();
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

            newId = materialTypeService.copy(selectedObject.getCode(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditMaterialTypeDialog.PAGE_INIT_URL + java.net.URLEncoder.encode(newId, java.nio.charset.StandardCharsets.UTF_8);

        userSession.setLastPage(getCurrentPageURL());
        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openCreateNewMaterialTypeDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = CreateNewMaterialTypeDialog.PAGE_INIT_URL;

        return url;
    }

    /**
     * Open dialog
     * @return the navigation target
     */
    @Generated
    public String openEditMaterialTypeDialog() {
        var url = "";

        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR))
            url = EditMaterialTypeDialog.PAGE_INIT_URL
                    + java.net.URLEncoder.encode(selectedObject.getCode(), java.nio.charset.StandardCharsets.UTF_8);

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
        searchObj.setCaseSensitive(true);
        searchObj.setCount(false);
        searchObj.setDateFormat(userSession.getDateFormat());
        searchObj.setDateTimeFormat(userSession.getDateTimeFormat());
        searchObj.setNumberFormat(userSession.getNumberFormat());
        searchObj.setDecimalSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getDecimalSeparator());
        searchObj.setGroupingSeparator(DecimalFormatSymbols.getInstance(userSession.getLocale()).getGroupingSeparator());


        final var field1 = new SearchFieldDTO(++colOrderId, MaterialTypeSearchDTO.SELECT_CODE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_CODE), SearchFieldDataTypeEnum.STRING, 100);
        searchObj.getSearchFields().add(field1);


        final var field2 = new SearchFieldDTO(++colOrderId, MaterialTypeSearchDTO.SELECT_SHORTTEXT,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_SHORTTEXT), SearchFieldDataTypeEnum.STRING, 150);
        searchObj.getSearchFields().add(field2);


        final var field3 = new SearchFieldDTO(++colOrderId, MaterialTypeSearchDTO.SELECT_COMMENT,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_COMMENT), SearchFieldDataTypeEnum.STRING, 150);
        searchObj.getSearchFields().add(field3);


        final var field4 = new SearchFieldDTO(++colOrderId, MaterialTypeSearchDTO.SELECT_CREATIONDATE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_CREATIONDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        searchObj.getSearchFields().add(field4);


        final var field5 = new SearchFieldDTO(++colOrderId, MaterialTypeSearchDTO.SELECT_LASTUPDATE,
                bundle.getString(LBL_ATTR_ABSTRACTFUNTIONALENTITY_LASTUPDATE), SearchFieldDataTypeEnum.LOCAL_DATE_TIME, 120);
        searchObj.getSearchFields().add(field5);


        logger.debug("Perform data fetch operation");

        try {
            materialTypesList = materialTypeService.searchAllMaterialTypes(searchObj);
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
    public void fetchMaterialTypes() {
        logger.debug("Initialize view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR))
            return;


        formTitle = bundle.getString(FORM_MATERIALTYPEVIEW_TITLE);

        initSearchObject();

        logger.debug("View initialization finished");
    }

}