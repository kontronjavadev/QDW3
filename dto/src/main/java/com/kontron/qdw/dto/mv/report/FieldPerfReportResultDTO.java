package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String TEXTSTYLE_DEFAULT = "color: var(--text);";
    protected static final String TEXTSTYLE_HEADER = "color: var(--reportmarkblue);";
    protected static final String TEXTSTYLE_SUMMARY = "color: var(--reportmarkred);";

    protected static final String TEXTSTYLE_BOLD = "font-weight:bold;";
    protected static final String TEXTSTYLE_ALIGN_RIGHT = "float:right;";



    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_PRE = "box-shadow: ";
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_POST = " !important;";

    // ein harter Rahmen plus ein weiches Glühen
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_T = "inset 0 2px 0 0 var(--active), inset 0 5px 4px -3px var(--active)";
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_B = "inset 0 -2px 0 0 var(--active), inset 0 -5px 4px -3px var(--active)";
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_L = "inset 2px 0 0 0 var(--active), inset 5px 0 4px -3px var(--active)";
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_R = "inset -2px 0 0 0 var(--active), inset -5px 0 4px -3px var(--active)";

    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_TRANSITION = "transition: box-shadow 0.3s ease-in-out !important;";



    protected static int DATA_CELL_WIDTH = 60;
    protected static int WIDE_CELL_WIDTH = 95;

    private final String label;
    private final int width;
    private final String textStyle;

    private boolean highlightT = false;
    private boolean highlightB = false;
    private boolean highlightL = false;
    private boolean highlightR = false;


    public FieldPerfReportResultDTO(String label, int width, String textStyle) {
        this.label = label;
        this.width = width;
        this.textStyle = textStyle;
    }



    @Override
    public String toString() {
        return label;
    }

    public void clearHighlights() {
        highlightT = false;
        highlightB = false;
        highlightL = false;
        highlightR = false;
    }



    public void clearHighlightT() {
        highlightT = false;
    }

    public void clearHighlightB() {
        highlightB = false;
    }

    public void clearHighlightL() {
        highlightL = false;
    }

    public void clearHighlightR() {
        highlightR = false;
    }



    public void setHighlightT() {
        highlightT = true;
    }

    public void setHighlightB() {
        highlightB = true;
    }

    public void setHighlightTB() {
        highlightT = true;
        highlightB = true;
    }

    public void setHighlightL() {
        highlightL = true;
    }

    public void setHighlightR() {
        highlightR = true;
    }

    public void setHighlightLR() {
        highlightL = true;
        highlightR = true;
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
        if (!highlightT && !highlightB && !highlightL && !highlightR) {
            return "";
        }

        List<String> boxShadows = new ArrayList<>();
        if (highlightT) {
            boxShadows.add(TEXTSTYLE_CELL_HIGHLIGHT_T);
        }
        if (highlightB) {
            boxShadows.add(TEXTSTYLE_CELL_HIGHLIGHT_B);
        }
        if (highlightL) {
            boxShadows.add(TEXTSTYLE_CELL_HIGHLIGHT_L);
        }
        if (highlightR) {
            boxShadows.add(TEXTSTYLE_CELL_HIGHLIGHT_R);
        }
        return TEXTSTYLE_CELL_HIGHLIGHT_PRE + String.join(",", boxShadows) + TEXTSTYLE_CELL_HIGHLIGHT_POST;
    }

}
