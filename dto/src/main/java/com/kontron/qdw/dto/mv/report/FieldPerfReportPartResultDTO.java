package com.kontron.qdw.dto.mv.report;

import java.io.Serializable;
import java.util.*;

public class FieldPerfReportPartResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String materialNumber;
    private int cnt = 0;
    private final Map<String, FieldPerfReportPartResultDTO> subItemsPerMatNr = new HashMap<>();



    public FieldPerfReportPartResultDTO(String materialNumber) {
        super();
        this.materialNumber = materialNumber;
    }



    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public int getCnt() {
        return cnt;
    }

    public void raiseCnt() {
        cnt++;
    }

    public Map<String, FieldPerfReportPartResultDTO> getSubItemsPerMatNr() {
        return subItemsPerMatNr;
    }

    public List<FieldPerfReportPartResultDTO> getSubItems() {
        ArrayList<FieldPerfReportPartResultDTO> returnedSubItems = new ArrayList<>(subItemsPerMatNr.values());
        returnedSubItems.sort(Comparator.comparing(FieldPerfReportPartResultDTO::getMaterialNumber, Comparator.nullsLast(Comparator.naturalOrder())));
        return returnedSubItems;
    }

}
