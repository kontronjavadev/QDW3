package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportQRelCell extends FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReportQRelCell(String label) {
        super(label, 80);
    }

}
