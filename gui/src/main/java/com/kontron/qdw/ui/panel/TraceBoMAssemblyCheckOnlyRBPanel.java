package com.kontron.qdw.ui.panel;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.ui.UserSession;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("traceBoMAssemblyCheckOnlyRBPanel")
@ViewScoped
public class TraceBoMAssemblyCheckOnlyRBPanel extends TraceBoMAssemblyCheckPanel {

    private static final long serialVersionUID = 3578207517573932665L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());



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

    @Override
    public String defaultExcelExportFileName() {
        return "OnlyInRevisionBoMList";
    }

    @Override
    public void onBomItemsGridDoubleClick() {
        logger.debug("Handle double-click event");

        getUserSession().redirectTo(getCurrentPageURL(), openViewRevisionBoMItemDialog());
    }

}
