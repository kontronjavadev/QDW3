package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class ReportLastColHeader extends ReportHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReportLastColHeader(String label) {
        super(label, 120);
    }

}
