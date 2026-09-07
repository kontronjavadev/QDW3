package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.FORM_TRACEBOMIMPORT_TITLE;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;
import static com.kontron.qdw.ui.UserSession.ROLE_ADMINISTRATOR;
import static com.kontron.qdw.ui.UserSession.ROLE_MAINTAINER;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.tracebomimport.TraceBoMImportServiceBean;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Named("traceBoMImportView")
@SessionScoped
public class TraceBoMImportView extends CopyClipboard implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;

    protected static final String ITEM_LABEL_SEPARATOR = ": ";
    public static final String PAGE_URL = "/view/traceBoMImportView.jsf?faces-redirect=true";
    private String formTitle;

    private final UserSession userSession;
    private transient ResourceBundle bundle;
    protected DecimalFormat decimalFormat = new DecimalFormat();
    protected transient DateTimeFormatter dateTimeFormat;
    protected transient DateTimeFormatter dateFormat;

    private final transient TraceBoMImportServiceBean importServiceBean;

    private List<String> folders = new ArrayList<>();
    private List<String> selectedFolders = new ArrayList<>();


    @Generated
    public TraceBoMImportView() {
        userSession = null;
        importServiceBean = null;
    }

    @Inject
    @Generated
    public TraceBoMImportView(UserSession userSession, TraceBoMImportServiceBean importServiceBean) {
        this.userSession = userSession;
        this.importServiceBean = importServiceBean;
    }

    public void initView() {
        logger.debug("Initialize trace BoM import view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_MAINTAINER)) {
            return;
        }


        formTitle = bundle.getString(FORM_TRACEBOMIMPORT_TITLE);

        // Initialize formatters for items that represent number or date values
        dateFormat = DateTimeFormatter.ofPattern(userSession.getDateFormat()).withZone(TimeZone.getTimeZone(userSession.getTimeZone()).toZoneId());
        dateTimeFormat = DateTimeFormatter.ofPattern(userSession.getDateTimeFormat())
                .withZone(TimeZone.getTimeZone(userSession.getTimeZone()).toZoneId());
        decimalFormat.applyPattern(userSession.getNumberFormat());


        // Code für Abfrage über FTP. Unnötig langsam, also erst Mal fest kodiert hier rein schreiben
        // und ggf. mit anderen Mechanismen in der Datenbank persistieren.
        // if (folders.isEmpty()) {
        // try {
        // folders.addAll(importServiceBean.getRootFolders());
        // if (Constants.IS_PROD_ENVIRONMENT) {
        // // Ein Test-Ordner für die Testumgebung
        // folders.removeIf(folder -> folder.equalsIgnoreCase("test"));
        // }
        // }
        // catch (Exception e) {
        // folders.clear();
        // MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_FETCH_FAIL, e.getMessage());
        // }
        // }

        if (folders.isEmpty()) {
            folders.addAll(List.of("BMK", "ENNOCON", "ETL", "ISKRATEL", "KAT", "Kontron Electronics"));
            if (!Constants.IS_PROD_ENVIRONMENT) {
                // Ein Test-Ordner für die Testumgebung
                folders.add("x TEST");
            }
        }


        logger.debug("Trace BoM import view initialization finished");
    }



    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getCurrentPageURL() {
        return PAGE_URL;
    }



    public void runImport() {
        importServiceBean.runImport(selectedFolders.size() == folders.size()
                ? null
                : selectedFolders);
    }

    public void runDownload() {
        importServiceBean.runDownload(selectedFolders.size() == folders.size()
                ? null
                : selectedFolders);
    }

    public void runProcess() {
        importServiceBean.runProcess(selectedFolders.size() == folders.size()
                ? null
                : selectedFolders);
    }



    public List<String> getFolders() {
        return folders;
    }

    public void setFolders(List<String> folders) {
        this.folders = folders;
    }

    public List<String> getSelectedFolders() {
        return selectedFolders;
    }

    public void setSelectedFolders(List<String> selectedFolders) {
        this.selectedFolders = selectedFolders;
    }

}
