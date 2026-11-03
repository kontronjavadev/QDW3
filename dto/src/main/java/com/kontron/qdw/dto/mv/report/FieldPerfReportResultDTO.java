package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;

public class FieldPerfReportResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String TEXTSTYLE_ALIGN_RIGHT = "float:right;";
    private static final String TEXTSTYLE_BOLD = "font-weight:bold;";
    private static final String TEXTSTYLE_DEFAULT = "color: var(--text);";
    private static final String TEXTSTYLE_DEFAULT_BOLD = TEXTSTYLE_DEFAULT + TEXTSTYLE_BOLD;
    private static final String TEXTSTYLE_RED_BOLD = "color: var(--reportmarkred);" + TEXTSTYLE_BOLD;

    public enum Type {
        PLANT_ITEM, BU_ITEM, HEADER_LINE, SUM_LINE, EMPTY
    }

    private enum InfoLineId {
        PLANT_ITEM_HEADER(1L), //
        BU_ITEM_HEADER(2L), //
        SPACER_SUM(3L), //
        TOTAL_SUM(4L);

        final long id;

        InfoLineId(long id) {
            this.id = id;
        }
    }

    private final String label;
    private final int width;


    public FieldPerfReportResultDTO(String label, int width) {
        this.label = label;
        this.width = width;
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

    //
    // public String getTextStyleColLabel() {
    // switch (type){
    // case HEADER_LINE:
    // return TEXTSTYLE_DEFAULT_BOLD;
    // case SUM_LINE:
    // return TEXTSTYLE_RED_BOLD;
    // case PLANT_ITEM, BU_ITEM:
    // default:
    // return TEXTSTYLE_DEFAULT;
    // }
    // }
    //
    // public String getTextStyleOtherCols() {
    // switch (type){
    // case SUM_LINE:
    // return TEXTSTYLE_RED_BOLD + TEXTSTYLE_ALIGN_RIGHT;
    // case HEADER_LINE, PLANT_ITEM, BU_ITEM:
    // default:
    // return TEXTSTYLE_DEFAULT + TEXTSTYLE_ALIGN_RIGHT;
    // }
    // }

}
