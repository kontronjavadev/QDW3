package com.kontron.qdw.ui.util;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.DataExporters;

import jakarta.faces.event.*;

public class CustomExporterRegistrationListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    private volatile boolean registered = false;

    @Override
    public void beforePhase(PhaseEvent event) {
        if (!registered) {
            synchronized (this) {
                if (!registered) {
                    DataExporters.register(DataTable.class, CustomExcelXStreamExporter.class, "xlsxstream");
                    registered = true;
                }
            }
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        // nichts zu tun
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
