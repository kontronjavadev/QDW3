package com.kontron.qdw.dto.mv;

import java.io.Serializable;
import java.util.Objects;

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

    // Identifizierung
    private final Type type;
    private final Object id;

    private String label;
    private Integer totalQuantity;
    private Integer openQuantity;
    private Double possibleRevenue;
    private Double noneRevenue;
    private Double approvedRevenue;
    private Double bookValue;
    private Double billingAmount;


    public FieldPerfReportResultDTO(Type type, Object id) {
        this(type, id, true);
    }

    private FieldPerfReportResultDTO(Type type, Object id, boolean initWithNull) {
        this.type = type;
        this.id = id;
        if (initWithNull) {
            totalQuantity = 0;
            openQuantity = 0;
            possibleRevenue = 0.0;
            noneRevenue = 0.0;
            approvedRevenue = 0.0;
            bookValue = 0.0;
            billingAmount = 0.0;
        }
    }


    public static FieldPerfReportResultDTO createPlantHeader() {
        return new FieldPerfReportResultDTO(Type.HEADER_LINE, InfoLineId.PLANT_ITEM_HEADER.id, false);
    }

    public static FieldPerfReportResultDTO createBUHeader() {
        return new FieldPerfReportResultDTO(Type.HEADER_LINE, InfoLineId.BU_ITEM_HEADER.id, false);
    }

    public static FieldPerfReportResultDTO createSpacerSum() {
        return new FieldPerfReportResultDTO(Type.EMPTY, InfoLineId.SPACER_SUM.id, false);
    }

    public static FieldPerfReportResultDTO createTotalSum() {
        return new FieldPerfReportResultDTO(Type.SUM_LINE, InfoLineId.TOTAL_SUM.id, true);
    }


    @Override
    public int hashCode() {
        return Objects.hash(type, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        FieldPerfReportResultDTO other = (FieldPerfReportResultDTO) obj;
        return type == other.type && id == other.id;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FieldPerfReportResultDTO [").append(type);

        if (type != Type.EMPTY) {
            sb.append(": ").append(label);
        }
        sb.append("]");

        return sb.toString();
    }


    public Type getType() {
        return type;
    }

    public boolean isPlantItem() {
        return type == Type.PLANT_ITEM;
    }

    public boolean isBUItem() {
        return type == Type.BU_ITEM;
    }

    public boolean isHeaderLine() {
        return type == Type.HEADER_LINE;
    }

    public boolean isSumLine() {
        return type == Type.SUM_LINE;
    }

    public Object getId() {
        return id;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FieldPerfReportResultDTO withLabel(String label) {
        this.label = label;
        return this;
    }


    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void addTotalQuantity(Integer totalQuantity) {
        this.totalQuantity += totalQuantity;
    }

    public FieldPerfReportResultDTO withTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }


    public Integer getOpenQuantity() {
        return openQuantity;
    }

    public void setOpenQuantity(Integer openQuantity) {
        this.openQuantity = openQuantity;
    }

    public void addOpenQuantity(Integer openQuantity) {
        this.openQuantity += openQuantity;
    }

    public FieldPerfReportResultDTO withOpenQuantity(Integer openQuantity) {
        this.openQuantity = openQuantity;
        return this;
    }


    public Double getPossibleRevenue() {
        return possibleRevenue;
    }

    public void setPossibleRevenue(Double possibleRevenue) {
        this.possibleRevenue = possibleRevenue;
    }

    public void addPossibleRevenue(Double possibleRevenue) {
        this.possibleRevenue += possibleRevenue;
    }

    public FieldPerfReportResultDTO withPossibleRevenue(Double possibleRevenue) {
        this.possibleRevenue = possibleRevenue;
        return this;
    }


    public Double getNoneRevenue() {
        return noneRevenue;
    }

    public void setNoneRevenue(Double noneRevenue) {
        this.noneRevenue = noneRevenue;
    }

    public void addNoneRevenue(Double noneRevenue) {
        this.noneRevenue += noneRevenue;
    }

    public FieldPerfReportResultDTO withNoneRevenue(Double noneRevenue) {
        this.noneRevenue = noneRevenue;
        return this;
    }


    public Double getApprovedRevenue() {
        return approvedRevenue;
    }

    public void setApprovedRevenue(Double approvedRevenue) {
        this.approvedRevenue = approvedRevenue;
    }

    public void addApprovedRevenue(Double approvedRevenue) {
        this.approvedRevenue += approvedRevenue;
    }

    public FieldPerfReportResultDTO withApprovedRevenue(Double approvedRevenue) {
        this.approvedRevenue = approvedRevenue;
        return this;
    }


    public Double getBookValue() {
        return bookValue;
    }

    public void setBookValue(Double bookValue) {
        this.bookValue = bookValue;
    }

    public void addBookValue(Double bookValue) {
        this.bookValue += bookValue;
    }

    public FieldPerfReportResultDTO withBookValue(Double bookValue) {
        this.bookValue = bookValue;
        return this;
    }


    public Double getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(Double billingAmount) {
        this.billingAmount = billingAmount;
    }

    public void addBillingAmount(Double billingAmount) {
        this.billingAmount += billingAmount;
    }

    public FieldPerfReportResultDTO withBillingAmount(Double billingAmount) {
        this.billingAmount = billingAmount;
        return this;
    }

    public String getTextStyleColLabel() {
        switch (type){
        case HEADER_LINE:
            return TEXTSTYLE_DEFAULT_BOLD;
        case SUM_LINE:
            return TEXTSTYLE_RED_BOLD;
        case PLANT_ITEM, BU_ITEM:
        default:
            return TEXTSTYLE_DEFAULT;
        }
    }

    public String getTextStyleOtherCols() {
        switch (type){
        case SUM_LINE:
            return TEXTSTYLE_RED_BOLD + TEXTSTYLE_ALIGN_RIGHT;
        case HEADER_LINE, PLANT_ITEM, BU_ITEM:
        default:
            return TEXTSTYLE_DEFAULT + TEXTSTYLE_ALIGN_RIGHT;
        }
    }

}
