package com.kontron.qdw.ui.panel;

import com.kontron.qdw.ui.UserSession;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("traceBoMAssemblyCheckDiffQtyPanel")
@ViewScoped
public class TraceBoMAssemblyCheckDiffQtyPanel extends TraceBoMAssemblyCheckPanel {

    private static final long serialVersionUID = 5302884267509194474L;



    public TraceBoMAssemblyCheckDiffQtyPanel() {
        super();
    }

    @Inject
    public TraceBoMAssemblyCheckDiffQtyPanel(UserSession userSession) {
        super(userSession);
    }



    @Override
    public boolean isTBVisible() {
        return true;
    }

    @Override
    public boolean isRBVisible() {
        return true;
    }

}
