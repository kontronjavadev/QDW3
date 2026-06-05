package com.kontron.qdw.ui.util;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;

/**
 * Gibt die Dimensionen einer/eines View/Dialogs/Panels zurück, um diese Information an einer Stelle zu vereinen.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Named("sizeServiceBean")
@Stateless
public class SizeServiceBean {

    public String searchFilterHeight() {
        // -> #{sizeServiceBean.searchFilterHeight()}
        return "height:calc(100vh - 200px)";
    }

    public String searchQueriesHeight() {
        // -> #{sizeServiceBean.searchQueriesHeight()}
        return "height:calc(100vh - 265px)";
    }



    public String dialogHeight() {
        // -> #{sizeServiceBean.dialogHeight()}
        return "height: calc(100vh - 188px)";
    }



    public String panelWidth() {
        // -> #{sizeServiceBean.panelWidth()}
        // war errechnet als: "width: calc(100vw - 280px - 30px - 15px)"
        return "width: calc(100vw - 325px)";
    }


}
