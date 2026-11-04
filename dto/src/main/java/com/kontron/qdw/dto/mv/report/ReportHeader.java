package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportHeader extends FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    public ReportHeader(String label) {
        super(label, DATA_CELL_WIDTH, TEXTSTYLE_HEADER + TEXTSTYLE_BOLD);
    }

    protected ReportHeader(String label, int width) {
        super(label, width, TEXTSTYLE_HEADER + TEXTSTYLE_BOLD);
    }

    protected ReportHeader(String label, int width, String textStyle) {
        super(label, width, textStyle);
    }

}
