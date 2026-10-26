package com.kontron.qdw.ui.converter;

import com.kontron.qdw.boundary.base.CustomerBoundaryService;
import com.kontron.qdw.dto.base.CustomerDTO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("customerDTOConverter")
@RequestScoped
public class CustomerDTOConverter implements Converter<CustomerDTO> {

    @Inject
    private CustomerBoundaryService customerBoundary;

    /** html -> java */
    @Override
    public CustomerDTO getAsObject(FacesContext context, UIComponent component, String htmlValue) {
        if (htmlValue == null || htmlValue.isBlank()) {
            return null;
        }

        try {
            // Holt das vollständige Objekt anhand des Codes aus der Datenbank
            return customerBoundary.findCustomerByIdForSearch(htmlValue);
        }
        catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Fehler bei der Customer-Konvertierung."), e);
        }
    }

    /** java -> html */
    @Override
    public String getAsString(FacesContext context, UIComponent component, CustomerDTO dto) {
        if (dto == null || dto.getCode() == null) {
            return "";
        }
        return dto.getCode();
    }

}
