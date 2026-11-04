package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String TEXTSTYLE_DEFAULT = "color: var(--text);";
    protected static final String TEXTSTYLE_HEADER = "color: var(--reportmarkblue);";
    protected static final String TEXTSTYLE_SUMMARY = "color: var(--reportmarkred);";

    protected static final String TEXTSTYLE_BOLD = "font-weight:bold;";
    protected static final String TEXTSTYLE_ALIGN_RIGHT = "float:right;";

    protected static int DATA_CELL_WIDTH = 60;
    protected static int WIDE_CELL_WIDTH = 95;

    private final String label;
    private final int width;
    private final String textStyle;


    public FieldPerfReportResultDTO(String label, int width, String textStyle) {
        this.label = label;
        this.width = width;
        this.textStyle = textStyle;
    }



    @Override
    public String toString() {
        return label;
    }



    public String getLabel() {
        return label;
    }

    public int getWidth() {
        return width;
    }

    public String getTextStyle() {
        return textStyle;
    }

}
