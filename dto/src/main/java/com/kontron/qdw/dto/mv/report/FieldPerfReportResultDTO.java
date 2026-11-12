package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
    protected static final String FRAME_COL = "var(--activemarked)";


    private static final String BRD_THK = "2px"; // border thinkness
    private static final String M_BRD_THK = "-" + BRD_THK;

    private static final String BLUR_OFFSET = "4px";
    private static final String M_BLUR_OFFSET = "-" + BLUR_OFFSET;

    private static final String BLUR = "4px"; // blur radius
    private static final String SPREAD = "-2px"; // spread radius

    private static final String CORNER_BLUR = "5px"; // blur radius

    private static final String HIGHLIGHT_PATTERN = "inset %s %s 0 0 " + FRAME_COL + ", " // Rahmen
            + "inset %s %s " + BLUR + " " + SPREAD + " " + FRAME_COL; // Glimmen

    private static final String CORNER_IMG_PATTERN = "radial-gradient(circle at %s %s, " + FRAME_COL + ", transparent " + CORNER_BLUR + "), "
            + "linear-gradient(" + FRAME_COL + ", " + FRAME_COL + "), "
            + "linear-gradient(" + FRAME_COL + ", " + FRAME_COL + ")";



    // ein harter Rahmen plus ein weiches Glühen
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_T = String.format(HIGHLIGHT_PATTERN, "0", BRD_THK, "0", BLUR_OFFSET);
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_B = String.format(HIGHLIGHT_PATTERN, "0", M_BRD_THK, "0", M_BLUR_OFFSET);
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_L = String.format(HIGHLIGHT_PATTERN, BRD_THK, "0", BLUR_OFFSET, "0");
    protected static final String TEXTSTYLE_CELL_HIGHLIGHT_R = String.format(HIGHLIGHT_PATTERN, M_BRD_THK, "0", M_BLUR_OFFSET, "0");


    protected static final String TEXTSTYLE_CELL_CORNERS_NOREPEAT = " background-repeat: no-repeat";
    protected static final String TEXTSTYLE_CELL_CORNER_SIZE = "100% 100%, " + BRD_THK + " " + BRD_THK + ", " + BRD_THK + " " + BRD_THK;


    // Top-Left (TL)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_TL = String.format(CORNER_IMG_PATTERN, "0", "0");
    protected static final String TEXTSTYLE_CELL_CORNER_POS_TL = "top left, top left, top left";

    // Top-Right (TR)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_TR = String.format(CORNER_IMG_PATTERN, "100%", "0");
    protected static final String TEXTSTYLE_CELL_CORNER_POS_TR = "top right, top right, top right";

    // Bottom-Left (BL)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_BL = String.format(CORNER_IMG_PATTERN, "0", "100%");
    protected static final String TEXTSTYLE_CELL_CORNER_POS_BL = "bottom left, bottom left, bottom left";

    // Bottom-Right (BR)
    protected static final String TEXTSTYLE_CELL_CORNER_IMG_BR = String.format(CORNER_IMG_PATTERN, "100%", "100%");
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

    public static String reportArrayToString(FieldPerfReportResultDTO[] reportRow) {
        return reportRow == null
                ? "null"
                : Arrays.stream(reportRow)
                        .map(FieldPerfReportResultDTO::getLabel)
                        .collect(Collectors.joining(","));
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
                        .append(String.join(", ", cornerImg))
                        .append(TEXTSTYLE_CELL_POST)

                        .append(" ")
                        .append(TEXTSTYLE_CELL_CORNERS_POS_PRE)
                        .append(String.join(", ", cornerPos))
                        .append(TEXTSTYLE_CELL_POST)

                        .append(" ")
                        .append(TEXTSTYLE_CELL_CORNERS_SIZE_PRE)
                        .append(String.join(", ", cornerSize))
                        .append(TEXTSTYLE_CELL_POST)

                        .append(" ")
                        .append(TEXTSTYLE_CELL_CORNERS_NOREPEAT)
                        .append(TEXTSTYLE_CELL_POST);
            }
        }

        return style.toString();
    }

}
