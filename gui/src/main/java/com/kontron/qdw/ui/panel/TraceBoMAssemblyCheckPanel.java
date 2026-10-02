package com.kontron.qdw.ui.panel;

import static com.kontron.qdw.ui.UserSession.DEFAULT_BUNDLE_NAME;
import static com.kontron.qdw.ui.UserSession.ROLE_ADMINISTRATOR;
import static com.kontron.qdw.ui.UserSession.ROLE_READONLY;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.dto.serial.AssemblyCheckMaterialDTO;
import com.kontron.qdw.ui.UserSession;
import com.kontron.qdw.ui.dialog.ViewBoMItemDialog;
import com.kontron.qdw.ui.dialog.ViewMaterialDialog;
import com.kontron.qdw.ui.dialog.ViewTraceBoMItemDialog;
import com.kontron.qdw.ui.view.util.CopyClipboard;

public abstract class TraceBoMAssemblyCheckPanel extends CopyClipboard implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;

    private final UserSession userSession;
    @SuppressWarnings("unused")
    private transient ResourceBundle bundle;

    private List<AssemblyCheckMaterialDTO> assemblyCheckDtos;
    private AssemblyCheckMaterialDTO selectedAssemblyCheckDto;

    private String currentPageURL;



    TraceBoMAssemblyCheckPanel() {
        userSession = null;
    }

    TraceBoMAssemblyCheckPanel(UserSession userSession) {
        this.userSession = userSession;
    }



    public void initView() {
        logger.debug("Initialize grid panel");

        bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        logger.debug("Grid panel initialization finished");
    }


    public abstract boolean isTBVisible();

    public abstract boolean isRBVisible();

    public abstract String defaultExcelExportFileName();


    public void onBomItemsGridDoubleClick() {
        logger.debug("Handle double-click event");
        //
        // userSession.redirectTo(getCurrentPageURL(), openViewBoMItemDialog());
    }


    public String openViewTraceBoMItemDialog() {
        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return ViewTraceBoMItemDialog.PAGE_INIT_URL + selectedAssemblyCheckDto.getTraceBomItemId();
        }

        return "";
    }

    public String openViewRevisionBoMItemDialog() {
        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return ViewBoMItemDialog.PAGE_INIT_URL + selectedAssemblyCheckDto.getRevBomItemId();
        }

        return "";
    }

    public String openViewMaterialDialog() {
        if (userSession.checkAuthorization(false, ROLE_ADMINISTRATOR, ROLE_READONLY)) {
            return ViewMaterialDialog.PAGE_INIT_URL + selectedAssemblyCheckDto.getMaterialId();
        }

        return "";
    }



    public List<AssemblyCheckMaterialDTO> getAssemblyCheckDtos() {
        return assemblyCheckDtos;
    }

    public void setAssemblyCheckDtos(List<AssemblyCheckMaterialDTO> assemblyCheckDtos) {
        this.assemblyCheckDtos = assemblyCheckDtos;
    }

    public AssemblyCheckMaterialDTO getSelectedAssemblyCheckDto() {
        return selectedAssemblyCheckDto;
    }

    public void setSelectedAssemblyCheckDto(AssemblyCheckMaterialDTO selectedAssemblyCheckDto) {
        this.selectedAssemblyCheckDto = selectedAssemblyCheckDto;
    }

    public void refresh() {
        // diffBoMItems = tbService.compareBoMs(serObjFst.getTraceBomId(), serObjSnd.getTraceBomId());
    }



    public String getCurrentPageURL() {
        return currentPageURL;
    }

    public void setCurrentPageURL(String currentPageURL) {
        this.currentPageURL = currentPageURL;
    }

}
