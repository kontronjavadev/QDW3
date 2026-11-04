package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportFirstColHeader extends ReportHeader implements Serializable {

    private static final long serialVersionUID = 7265576971106443117L;

    public ReportFirstColHeader(String label) {
        super(label, WIDE_CELL_WIDTH);
    }

}
