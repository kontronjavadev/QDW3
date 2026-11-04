package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportSumRatioCell extends FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 5070757370094291239L;

    public ReportSumRatioCell(String label) {
        super(label, DATA_CELL_WIDTH, TEXTSTYLE_DEFAULT + TEXTSTYLE_ALIGN_RIGHT);
    }

}
