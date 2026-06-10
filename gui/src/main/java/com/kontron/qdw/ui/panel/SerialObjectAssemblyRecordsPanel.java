package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.boundary.serial.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("serialObjectAssemblyRecordsPanel")
@ViewScoped
public class SerialObjectAssemblyRecordsPanel implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<SerialObjectAssemblyRecordsDTO> assemblyRecordsList = new ArrayList<>();
    @Generated
    private SerialObjectAssemblyRecordsDTO selItemOfAssemblyRecords;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private long selectedObjectId;
    @Generated
    private String currentPageURL;
    @Generated
    private boolean readOnly;
    @Generated
    private final transient SerialObjectBoundaryService serialObjectService;
    @Generated
    private final transient AssemblyRecordBoundaryService assemblyRecordService;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectAssemblyRecordsPanel() {
        this.userSession = null;
        this.serialObjectService = null;
        this.assemblyRecordService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param serialObjectService
     * @param assemblyRecordService
     */
    @Inject
    @Generated
    public SerialObjectAssemblyRecordsPanel(UserSession userSession, SerialObjectBoundaryService serialObjectService,
            AssemblyRecordBoundaryService assemblyRecordService) {
        this.userSession = userSession;
        this.serialObjectService = serialObjectService;
        this.assemblyRecordService = assemblyRecordService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<SerialObjectAssemblyRecordsDTO> getAssemblyRecordsList() {
        return assemblyRecordsList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public SerialObjectAssemblyRecordsDTO getSelItemOfAssemblyRecords() {
        return selItemOfAssemblyRecords;
    }

    /**
     * @param selItemOfAssemblyRecords
     */
    @Generated
    public void setSelItemOfAssemblyRecords(SerialObjectAssemblyRecordsDTO selItemOfAssemblyRecords) {
        this.selItemOfAssemblyRecords = selItemOfAssemblyRecords;
    }

    /**
     * @return the URL of the page this grid panel is included in
     */
    @Generated
    public String getCurrentPageURL() {
        return currentPageURL;
    }

    /**
     * @param currentPageURL
     */
    @Generated
    public void setCurrentPageURL(String currentPageURL) {
        this.currentPageURL = currentPageURL;
    }

    /**
     * @return the ID of the selected parent object
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
     * @return true if the panel is in read-only mode
     */
    @Generated
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly
     */
    @Generated
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onAssemblyRecordsGridDoubleClick() {
        // No appropriate form found!
    }

    /**
     * Delete selected element
     */
    @Generated
    public void deleteAssemblyRecord() {
        try {
            logger.debug("Delete selected object with id '{}'", selItemOfAssemblyRecords.getId());

            assemblyRecordService.deleteAssemblyRecord(selItemOfAssemblyRecords.getId());
        }
        catch (final Exception e) {
            logger.error("Error while deleting selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_DELETE_FAIL, e);
        }

        fetchAssemblyRecords();
    }

    /**
     * Create copy of selected element
     * @return the navigation target
     */
    @Generated
    public String copy() {
        try {
            logger.debug("Create a copy of the selected object with id '{}'", selItemOfAssemblyRecords.getId());

            assemblyRecordService.copy(selItemOfAssemblyRecords.getId(), userSession.getPrincipal().getId());
        }
        catch (final Exception e) {
            logger.error("Error while creating a copy of the selected object!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_COPY_FAIL, e);
            return "";
        }

        fetchAssemblyRecords();
        return "";
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchAssemblyRecords();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchAssemblyRecords() {
        // Get data from server
        try {
            assemblyRecordsList = new ArrayList<>(serialObjectService.getAssemblyRecordsOfSerialObject(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}