package com.kontron.qdw.ui.view.util;

import org.primefaces.PrimeFaces;

import net.sourceforge.jbizmo.commons.webclient.primefaces.search.AbstractSearchableView;

public class CopyClipboard extends AbstractSearchableView {

    private String cellText;
    private String rowCsv;

    public void copyCellToClipboard() {
        copyToClipboard(cellText);
    }

    public void copyRowToClipboard() {
        copyToClipboard(rowCsv);
    }

    private void copyToClipboard(String text) {
        if (text == null) {
            return;
        }

        String escaped = text
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\n", "\\n")
                .replace("\r", "");

        String script = "navigator.clipboard.writeText('" + escaped + "');";
        PrimeFaces.current().executeScript(script);
    }

    public String getCellText() {
        return cellText;
    }

    public void setCellText(String cellText) {
        this.cellText = cellText;
    }

    public String getRowCsv() {
        return rowCsv;
    }

    public void setRowCsv(String rowCsv) {
        this.rowCsv = rowCsv;
    }

}
