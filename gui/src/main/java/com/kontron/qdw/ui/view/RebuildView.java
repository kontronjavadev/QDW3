package com.kontron.qdw.ui.view;

import static com.kontron.qdw.ui.TranslationKeys.FORM_REBUILD_TITLE;
import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;
import static com.kontron.qdw.ui.UserSession.ROLE_ADMINISTRATOR;
import static com.kontron.qdw.ui.UserSession.ROLE_MAINTAINER;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.rebuild.RebuildServiceBean;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.view.util.CopyClipboard;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Named("rebuildView")
@SessionScoped
public class RebuildView extends CopyClipboard implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;

    protected static final String ITEM_LABEL_SEPARATOR = ": ";
    public static final String PAGE_URL = "/view/rebuildView.jsf?faces-redirect=true";
    private String formTitle;

    private final UserSession userSession;
    private transient ResourceBundle bundle;
    protected DecimalFormat decimalFormat = new DecimalFormat();
    protected transient DateTimeFormatter dateTimeFormat;
    protected transient DateTimeFormatter dateFormat;

    private final transient RebuildServiceBean rebuildServiceBean;



    @Generated
    public RebuildView() {
        userSession = null;
        rebuildServiceBean = null;
    }

    @Inject
    @Generated
    public RebuildView(UserSession userSession, RebuildServiceBean importServiceBean) {
        this.userSession = userSession;
        this.rebuildServiceBean = importServiceBean;
    }

    public void initView() {
        logger.debug("Initialize xml import view");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        // Check if user is allowed to open this page!
        if (!userSession.checkAuthorization(true, ROLE_ADMINISTRATOR, ROLE_MAINTAINER)) {
            return;
        }


        formTitle = bundle.getString(FORM_REBUILD_TITLE);

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



    public void runRebuildDelta() {
        rebuildServiceBean.runRebuildDelta();
    }

    public void runArrivalRebuildMaterializedDelta() {
        rebuildServiceBean.runArrivalRebuildMaterializedDelta();
    }

    public void runShptArrvRebuildMaterializedDelta() {
        rebuildServiceBean.runShptArrvRebuildMaterializedDelta();
    }

    public void runSvcMsgRebuildMaterializedDelta() {
        rebuildServiceBean.runSvcMsgRebuildMaterializedDelta();
    }



    public void runRebuildFull() {
        rebuildServiceBean.runRebuildFull();
    }

    public void runArrivalRebuildMaterializedFull() {
        rebuildServiceBean.runArrivalRebuildMaterializedFull();
    }

    public void runShptArrvRebuildMaterializedFull() {
        rebuildServiceBean.runShptArrvRebuildMaterializedFull();
    }

    public void runSvcMsgRebuildMaterializedFull() {
        rebuildServiceBean.runSvcMsgRebuildMaterializedFull();
    }



    public void runArrivalRebuildAggregated() {
        rebuildServiceBean.runArrivalRebuildAggregated();
    }

    public void runShptRebuildAggregated() {
        rebuildServiceBean.runShptRebuildAggregated();
    }

    public void runShptArrvRebuildAggregated() {
        rebuildServiceBean.runShptArrvRebuildAggregated();
    }

}
