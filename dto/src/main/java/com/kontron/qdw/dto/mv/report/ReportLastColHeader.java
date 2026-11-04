package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportLastColHeader extends ReportHeader implements Serializable {

    private static final long serialVersionUID = 3842399789448828611L;

    public ReportLastColHeader(String label) {
        super(label, WIDE_CELL_WIDTH, TEXTSTYLE_LAST_COLUMN + TEXTSTYLE_BOLD);
    }

}
