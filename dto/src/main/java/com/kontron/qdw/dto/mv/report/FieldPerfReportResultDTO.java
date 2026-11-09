package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String TEXTSTYLE_DEFAULT = "color: var(--text);";
    protected static final String TEXTSTYLE_HEADER = "color: var(--reportmarkblue);";
    protected static final String TEXTSTYLE_SUMMARY = "color: var(--reportmarkred);";

    protected static final String TEXTSTYLE_BOLD = "font-weight:bold;";
    protected static final String TEXTSTYLE_ALIGN_RIGHT = "float:right;";

    protected static final String TEXTSTYLE_CELL_HIGHLIGHT = "outline: 3px solid var(--active) !important;"
            + "outline-offset: -3px !important;";

    protected static final String TEXTSTYLE_ROWCOL_HIGHLIGHT = "background-color: color-mix(in srgb, var(--active) 50%, transparent) !important;";


    protected static int DATA_CELL_WIDTH = 60;
    protected static int WIDE_CELL_WIDTH = 95;

    private final String label;
    private final int width;
    private final String textStyle;

    private boolean rowcolHighlight = false;
    private boolean highlight = false;


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

    public String getCellStyle() {
        return rowcolHighlight
                ? (highlight
                        ? TEXTSTYLE_ROWCOL_HIGHLIGHT + TEXTSTYLE_CELL_HIGHLIGHT
                        : TEXTSTYLE_ROWCOL_HIGHLIGHT)
                : "";
    }

    public void setRowcolHighlight(boolean rowcolHighlight) {
        this.rowcolHighlight = rowcolHighlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

}
