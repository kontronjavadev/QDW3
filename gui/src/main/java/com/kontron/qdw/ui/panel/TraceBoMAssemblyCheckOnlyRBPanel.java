package com.kontron.qdw.ui.panel;

import com.kontron.qdw.ui.UserSession;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("traceBoMAssemblyCheckOnlyRBPanel")
@ViewScoped
public class TraceBoMAssemblyCheckOnlyRBPanel extends TraceBoMAssemblyCheckPanel {

    private static final long serialVersionUID = 6965523918096803187L;



    public TraceBoMAssemblyCheckOnlyRBPanel() {
        super();
    }

    @Inject
    public TraceBoMAssemblyCheckOnlyRBPanel(UserSession userSession) {
        super(userSession);
    }



    @Override
    public boolean isTBVisible() {
        return false;
    }

    @Override
    public boolean isRBVisible() {
        return true;
    }

}
