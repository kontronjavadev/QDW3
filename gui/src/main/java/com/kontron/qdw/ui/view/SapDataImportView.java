package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.FORM_SAPDATAIMPORT_TITLE;
import static com.kontron.qdw.ui.UserSession.*;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.sapimport.SapDataImportServiceBean;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Named("sapDataImportView")
@SessionScoped
public class SapDataImportView extends CopyClipboard implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;

    protected static final String ITEM_LABEL_SEPARATOR = ": ";
    public static final String PAGE_URL = "/view/sapDataImportView.jsf?faces-redirect=true";
    private String formTitle;

    private final UserSession userSession;
    private transient ResourceBundle bundle;
    protected DecimalFormat decimalFormat = new DecimalFormat();
    protected transient DateTimeFormatter dateTimeFormat;
    protected transient DateTimeFormatter dateFormat;

    private final transient SapDataImportServiceBean importServiceBean;



    @Generated
    public SapDataImportView() {
        userSession = null;
        importServiceBean = null;
    }

    @Inject
    @Generated
    public SapDataImportView(UserSession userSession, SapDataImportServiceBean importServiceBean) {
        this.userSession = userSession;
        this.importServiceBean = importServiceBean;
    }

    public void initView() {
        logger.debug("Initialize xml import view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_MAINTAINER)) {
            return;
        }


        formTitle = bundle.getString(FORM_SAPDATAIMPORT_TITLE);

        // Initialize formatters for items that represent number or date values
        dateFormat = DateTimeFormatter.ofPattern(userSession.getDateFormat()).withZone(TimeZone.getTimeZone(userSession.getTimeZone()).toZoneId());
        dateTimeFormat = DateTimeFormatter.ofPattern(userSession.getDateTimeFormat())
                .withZone(TimeZone.getTimeZone(userSession.getTimeZone()).toZoneId());
        decimalFormat.applyPattern(userSession.getNumberFormat());

        logger.debug("XML import view initialization finished");
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
        importServiceBean.runImportAsync();
    }



    public void runCustomerImport() {
        importServiceBean.runCustomerImportAsync();
    }

    public void runSupplierImport() {
        importServiceBean.runSupplierImportAsync();
    }

    public void runMaterialImport() {
        importServiceBean.runMaterialImportAsync();
    }

    public void runBoMImport() {
        importServiceBean.runBoMImportAsync();
    }



    public void runArrivalImport() {
        importServiceBean.runArrivalImportAsync();
    }

    public void runAnalyzeSerObjStructure() {
        importServiceBean.runAnalyzeSerObjStructureAsync();
    }

    public void runArrivalRebuildMaterialized() {
        importServiceBean.runArrivalRebuildMaterializedAsync();
    }

    public void runArrivalRebuildAggregated() {
        importServiceBean.runArrivalRebuildAggregatedAsync();
    }



    public void runShipmentImport() {
        importServiceBean.runShipmentImportAsync();
    }

    public void runShptArrvRebuildMaterialized() {
        importServiceBean.runShptArrvRebuildMaterializedAsync();
    }

    public void runShptRebuildAggregated() {
        importServiceBean.runShptRebuildAggregatedAsync();
    }

    public void runShptArrvRebuildAggregated() {
        importServiceBean.runShptArrvRebuildAggregatedAsync();
    }

}
