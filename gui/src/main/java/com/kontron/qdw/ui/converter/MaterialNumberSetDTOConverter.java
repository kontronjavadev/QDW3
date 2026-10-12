package com.kontron.qdw.ui.converter;

import com.kontron.qdw.boundary.material.MaterialNumberSetBoundaryService;
import com.kontron.qdw.dto.material.MaterialNumberSetDTO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("materialNumberSetDTOConverter")
@RequestScoped
public class MaterialNumberSetDTOConverter implements Converter<MaterialNumberSetDTO> {

    @Inject
    private MaterialNumberSetBoundaryService service;

    @Override
    public MaterialNumberSetDTO getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            long id = Long.parseLong(value);
            // Lade das vollständige DTO anhand der ID, damit das Label nicht null ist
            return service.findById(id);
        }
        catch (NumberFormatException e) {
            return null; // Im Fehlerfall null oder eine ConverterException werfen
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, MaterialNumberSetDTO value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value.getId());
    }

}
