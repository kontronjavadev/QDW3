package com.kontron.qdw.ui.converter;

import com.kontron.qdw.boundary.base.SupplierBoundaryService;
import com.kontron.qdw.dto.base.SupplierDTO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("supplierDTOConverter")
@RequestScoped
public class SupplierDTOConverter implements Converter<SupplierDTO> {

    @Inject
    private SupplierBoundaryService supplierBoundary;

    /** html -> java */
    @Override
    public SupplierDTO getAsObject(FacesContext context, UIComponent component, String htmlValue) {
        if (htmlValue == null || htmlValue.isBlank()) {
            return null;
        }

        try {
            // Holt das vollständige Objekt anhand des Codes aus der Datenbank
            return supplierBoundary.findSupplierByIdForSearch(htmlValue);
        }
        catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Fehler bei der Supplier-Konvertierung."), e);
        }
    }

    /** java -> html */
    @Override
    public String getAsString(FacesContext context, UIComponent component, SupplierDTO dto) {
        if (dto == null || dto.getCode() == null) {
            return "";
        }
        return dto.getCode();
    }

}
