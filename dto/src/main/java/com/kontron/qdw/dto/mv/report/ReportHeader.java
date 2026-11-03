package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportHeader extends FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReportHeader(String label) {
        super(label, 60);
    }

    protected ReportHeader(String label, int width) {
        super(label, width);
    }

}
