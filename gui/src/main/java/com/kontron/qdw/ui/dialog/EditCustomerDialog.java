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

@Named("editCustomerDialog")
@ViewScoped
public class EditCustomerDialog implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String PAGE_INIT_URL = "/dialog/EditCustomerDialog.jsf?faces-redirect=true&selectedObjectId=";
    @Generated
    private CustomerUpdateDTO customer;
    @Generated
    private final transient CustomerBoundaryService customerService;
    @Generated
    private String selectedObjectId;
    @Generated
    private final UserSession userSession;
    @Generated
    private String formTitle = "";
    @Generated
    private transient ResourceBundle bundle;
    @Generated
    private final transient VerticalSectorBoundaryService verticalSectorService;
    @Generated
    private final transient CountryBoundaryService countryService;
    @Generated
    private transient Collection<VerticalSectorListDTO> verticalSectorList;

    /**
     * Default constructor
     */
    @Generated
    public EditCustomerDialog() {
        this.customerService = null;
        this.userSession = null;
        this.verticalSectorService = null;
        this.countryService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param customerService
     * @param userSession
     * @param verticalSectorService
     * @param countryService
     */
    @Inject
    @Generated
    public EditCustomerDialog(CustomerBoundaryService customerService, UserSession userSession, VerticalSectorBoundaryService verticalSectorService,
            CountryBoundaryService countryService) {
        this.customerService = customerService;
        this.userSession = userSession;
        this.verticalSectorService = verticalSectorService;
        this.countryService = countryService;
    }

    /**
     * @return the model object
     */
    @Generated
    public CustomerUpdateDTO getCustomer() {
        return customer;
    }

    /**
     * @param customer
     */
    @Generated
    public void setCustomer(CustomerUpdateDTO customer) {
        this.customer = customer;
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
    public String getSelectedObjectId() {
        return selectedObjectId;
    }

    /**
     * @param selectedObjectId
     */
    @Generated
    public void setSelectedObjectId(String selectedObjectId) {
        this.selectedObjectId = selectedObjectId;
    }

    /**
     * @return the vertical sector list
     */
    @Generated
    public Collection<VerticalSectorListDTO> getVerticalSectorList() {
        return verticalSectorList;
    }

    /**
     * Callback method for auto-complete field 'cboCountry'
     * @param filter the filter criterion inserted the by the user
     * @return the proposal list
     */
    @Generated
    public List<CountryListDTO> onCompleteCountry(String filter) {
        try {
            return countryService.findCountries(filter);
        }
        catch (final Exception e) {
            logger.error("Error while fetching data for proposal text field 'cboCountry'!", e);

            return Collections.emptyList();
        }
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
            logger.debug("Fetch data for object with id '{}'", selectedObjectId);

            customer = customerService.getCustomerForUpdate(selectedObjectId);
            verticalSectorList = verticalSectorService.findVerticalSectors(null);

            if (customer.getVerticalSector() != null && !verticalSectorList.contains(customer.getVerticalSector()))
                verticalSectorList.add(customer.getVerticalSector());


            formTitle = bundle.getString(FORM_EDITCUSTOMERDIALOG_TITLE) + " '" + selectedObjectId + "'";

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

            customerService.updateCustomer(customer);

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
        return EditCustomerDialog.PAGE_INIT_URL + java.net.URLEncoder.encode(selectedObjectId, java.nio.charset.StandardCharsets.UTF_8);
    }

}