package com.kontron.qdw.ui.panel;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.ui.UserSession;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("traceBoMAssemblyCheckDiffQtyPanel")
@ViewScoped
public class TraceBoMAssemblyCheckDiffQtyPanel extends TraceBoMAssemblyCheckPanel {

    private static final long serialVersionUID = -2009923688934537282L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());



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

    @Override
    public String defaultExcelExportFileName() {
        return "DifferentQuantityBoMList";
    }

    @Override
    public void onBomItemsGridDoubleClick() {
        logger.debug("Handle double-click event");

        getUserSession().redirectTo(getCurrentPageURL(), openViewMaterialDialog());
    }

}
