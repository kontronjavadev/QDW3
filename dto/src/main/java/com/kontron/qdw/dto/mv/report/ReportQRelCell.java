package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportQRelCell extends FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String TEXTSTYLE_Q_REL = "color: var(--reportmarkgreen);";


    public ReportQRelCell(String label) {
        super(label, DATA_CELL_WIDTH, TEXTSTYLE_Q_REL + TEXTSTYLE_ALIGN_RIGHT);
    }

}
