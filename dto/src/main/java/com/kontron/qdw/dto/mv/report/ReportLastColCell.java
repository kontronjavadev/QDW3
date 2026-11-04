package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportLastColCell extends FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 4982793839970882495L;

    public ReportLastColCell(String label) {
        super(label, DATA_CELL_WIDTH, TEXTSTYLE_LAST_COLUMN + TEXTSTYLE_ALIGN_RIGHT);
    }

}
