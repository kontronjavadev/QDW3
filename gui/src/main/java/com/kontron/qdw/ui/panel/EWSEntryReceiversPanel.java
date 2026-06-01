package com.kontron.qdw.ui.panel;

import org.slf4j.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import com.kontron.qdw.dto.base.*;
import jakarta.faces.application.FacesMessage;
import java.util.*;
import jakarta.faces.view.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;

@Named("eWSEntryReceiversPanel")
@ViewScoped
public class EWSEntryReceiversPanel extends CopyClipboard implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    private List<EWSEntryReceiversDTO> receiversList = new ArrayList<>();
    @Generated
    private EWSEntryReceiversDTO selItemOfReceivers;
    @Generated
    private final UserSession userSession;
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private long selectedObjectId;
    @Generated
    private String currentPageURL;
    @Generated
    private final transient EWSEntryBoundaryService eWSEntryService;

    /**
     * Default constructor
     */
    @Generated
    public EWSEntryReceiversPanel() {
        this.userSession = null;
        this.eWSEntryService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     * @param eWSEntryService
     */
    @Inject
    @Generated
    public EWSEntryReceiversPanel(UserSession userSession, EWSEntryBoundaryService eWSEntryService) {
        this.userSession = userSession;
        this.eWSEntryService = eWSEntryService;
    }

    /**
     * @return the list of elements
     */
    @Generated
    public Collection<EWSEntryReceiversDTO> getReceiversList() {
        return receiversList;
    }

    /**
     * @return the selected item
     */
    @Generated
    public EWSEntryReceiversDTO getSelItemOfReceivers() {
        return selItemOfReceivers;
    }

    /**
     * @param selItemOfReceivers
     */
    @Generated
    public void setSelItemOfReceivers(EWSEntryReceiversDTO selItemOfReceivers) {
        this.selItemOfReceivers = selItemOfReceivers;
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
     * Event that will be fired if user performs a double-click on a grid row
     */
    @Generated
    public void onReceiversGridDoubleClick() {
        // No appropriate form found!
    }

    /**
     * Initialize view
     */
    @Generated
    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        fetchReceivers();

        logger.debug("Grid panel initialization finished");
    }

    /**
     * Fetch data for grid panel
     */
    @Generated
    public void fetchReceivers() {
        // Get data from server
        try {
            receiversList = new ArrayList<>(eWSEntryService.getReceiversOfEWSEntry(selectedObjectId));
        }
        catch (final Exception e) {
            logger.error("Error while fetching grid panel data for object with id '{}'!", selectedObjectId, e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, DIALOG_INIT_FAIL, e);
        }
    }

}
