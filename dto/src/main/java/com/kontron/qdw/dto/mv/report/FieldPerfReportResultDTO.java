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
    protected static final String TEXTSTYLE_CELL_CORNERS_IMG_PRE = "background-image: ";
    protected static final String TEXTSTYLE_CELL_CORNERS_POS_PRE = "background-position: ";
    protected static final String TEXTSTYLE_CELL_CORNERS_SIZE_PRE = "background-size: ";

    protected static final String TEXTSTYLE_CELL_POST = " !important;";


    // ein harter Rahmen plus ein weiches Glühen
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_T = "inset 0 2px 0 0 var(--active), inset 0 5px 4px -3px var(--active)";
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_B = "inset 0 -2px 0 0 var(--active), inset 0 -5px 4px -3px var(--active)";
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_L = "inset 2px 0 0 0 var(--active), inset 5px 0 4px -3px var(--active)";
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_R = "inset -2px 0 0 0 var(--active), inset -5px 0 4px -3px var(--active)";


    protected static final String TEXTSTYLE_CELL_CORNERS_NOREPEAT = " background-repeat: no-repeat";
    protected static final String TEXTSTYLE_CELL_CORNER_SIZE = "100% 100%, 2px 2px, 2px 2px";

    // Top-Left (TL)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_TL = "radial-gradient(circle at 0 0, var(--active), transparent 5px), linear-gradient(var(--active), var(--active)), linear-gradient(var(--active), var(--active))";
    protected static final String TEXTSTYLE_CELL_CORNER_POS_TL = "top left, top left, top left";

    // Top-Right (TR)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_TR = "radial-gradient(circle at 100% 0, var(--active), transparent 5px), linear-gradient(var(--active), var(--active)), linear-gradient(var(--active), var(--active))";
    protected static final String TEXTSTYLE_CELL_CORNER_POS_TR = "top right, top right, top right";

    // Bottom-Left (BL)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_BL = "radial-gradient(circle at 0 100%, var(--active), transparent 5px), linear-gradient(var(--active), var(--active)), linear-gradient(var(--active), var(--active))";
    protected static final String TEXTSTYLE_CELL_CORNER_POS_BL = "bottom left, bottom left, bottom left";

    // Bottom-Right (BR)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_BR = "radial-gradient(circle at 100% 100%, var(--active), transparent 5px), linear-gradient(var(--active), var(--active)), linear-gradient(var(--active), var(--active))";
    protected static final String TEXTSTYLE_CELL_CORNER_POS_BR = "bottom right, bottom right, bottom right";



    protected static int DATA_CELL_WIDTH = 60;
    protected static int WIDE_CELL_WIDTH = 95;

    private final String label;
    private final int width;
    private final String textStyle;

    private boolean highlightT = false;
    private boolean highlightB = false;
    private boolean highlightL = false;
    private boolean highlightR = false;
    private boolean highlightSelected = false;


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
        highlightSelected = false;
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

    public void setHighlightSelected() {
        clearHighlights();
        highlightSelected = true;
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
        if (!highlightT && !highlightB && !highlightL && !highlightR && !highlightSelected) {
            return "";
        }

        StringBuilder style = new StringBuilder();

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

        if (!boxShadows.isEmpty()) {
            style.append(TEXTSTYLE_CELL_HIGHLIGHT_PRE)
                    .append(String.join(",", boxShadows))
                    .append(TEXTSTYLE_CELL_POST);
        }

        if (highlightSelected) {
            List<String> cornerImg = new ArrayList<>();
            List<String> cornerPos = new ArrayList<>();
            List<String> cornerSize = new ArrayList<>();

            // Oben Links
            if (!highlightT && !highlightL) {
                cornerImg.add(TEXTSTYLE_CELL_CORNER_IMG_TL);
                cornerPos.add(TEXTSTYLE_CELL_CORNER_POS_TL);
                cornerSize.add(TEXTSTYLE_CELL_CORNER_SIZE);
            }
            // Oben Rechts
            if (!highlightT && !highlightR) {
                cornerImg.add(TEXTSTYLE_CELL_CORNER_IMG_TR);
                cornerPos.add(TEXTSTYLE_CELL_CORNER_POS_TR);
                cornerSize.add(TEXTSTYLE_CELL_CORNER_SIZE);
            }
            // Unten Links
            if (!highlightB && !highlightL) {
                cornerImg.add(TEXTSTYLE_CELL_CORNER_IMG_BL);
                cornerPos.add(TEXTSTYLE_CELL_CORNER_POS_BL);
                cornerSize.add(TEXTSTYLE_CELL_CORNER_SIZE);
            }
            // Unten Rechts
            if (!highlightB && !highlightR) {
                cornerImg.add(TEXTSTYLE_CELL_CORNER_IMG_BR);
                cornerPos.add(TEXTSTYLE_CELL_CORNER_POS_BR);
                cornerSize.add(TEXTSTYLE_CELL_CORNER_SIZE);
            }

            if (!cornerImg.isEmpty()) {
                style.append(TEXTSTYLE_CELL_CORNERS_IMG_PRE)
                        .append(String.join(",", cornerImg))
                        .append(TEXTSTYLE_CELL_POST)

                        .append(" ")
                        .append(TEXTSTYLE_CELL_CORNERS_POS_PRE)
                        .append(String.join(",", cornerPos))
                        .append(TEXTSTYLE_CELL_POST)

                        .append(" ")
                        .append(TEXTSTYLE_CELL_CORNERS_SIZE_PRE)
                        .append(String.join(",", cornerSize))
                        .append(TEXTSTYLE_CELL_POST)

                        .append(" ")
                        .append(TEXTSTYLE_CELL_CORNERS_NOREPEAT)
                        .append(TEXTSTYLE_CELL_POST);
            }
        }

        return style.toString();
    }

}
