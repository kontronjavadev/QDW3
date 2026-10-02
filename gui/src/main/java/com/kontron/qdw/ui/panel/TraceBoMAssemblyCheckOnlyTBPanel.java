package com.kontron.qdw.ui.panel;

import com.kontron.qdw.ui.UserSession;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("traceBoMAssemblyCheckOnlyTBPanel")
@ViewScoped
public class TraceBoMAssemblyCheckOnlyTBPanel extends TraceBoMAssemblyCheckPanel {

    private static final long serialVersionUID = 6282253949123397385L;



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

}
