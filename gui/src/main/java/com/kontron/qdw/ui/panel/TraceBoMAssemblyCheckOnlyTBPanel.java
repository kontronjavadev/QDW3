package com.kontron.qdw.ui.panel;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.ui.UserSession;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("traceBoMAssemblyCheckOnlyTBPanel")
@ViewScoped
public class TraceBoMAssemblyCheckOnlyTBPanel extends TraceBoMAssemblyCheckPanel {

    private static final long serialVersionUID = -6453497621565918047L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());



    public TraceBoMAssemblyCheckOnlyTBPanel() {
        super();
    }

    @Inject
    public TraceBoMAssemblyCheckOnlyTBPanel(UserSession userSession) {
        super(userSession);
    }



    @Override
    public boolean isTBVisible() {
        return true;
    }

    @Override
    public boolean isRBVisible() {
        return false;
    }

    @Override
    public String defaultExcelExportFileName() {
        return "OnlyInTraceBoMList";
    }

    @Override
    public void onBomItemsGridDoubleClick() {
        logger.debug("Handle double-click event");

        getUserSession().redirectTo(getCurrentPageURL(), openViewTraceBoMItemDialog());
    }

}
