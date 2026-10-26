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

    /** html -> java */
    @Override
    public MaterialNumberSetDTO getAsObject(FacesContext context, UIComponent component, String htmlValue) {
        if (htmlValue == null || htmlValue.isBlank()) {
            return null;
        }

        try {
            long id = Long.parseLong(htmlValue);
            // Lade das vollständige DTO anhand der ID, damit das Label nicht null ist
            return service.findById(id);
        }
        catch (NumberFormatException e) {
            return null; // Im Fehlerfall null oder eine ConverterException werfen
        }
    }

    /** java -> html */
    @Override
    public String getAsString(FacesContext context, UIComponent component, MaterialNumberSetDTO dto) {
        if (dto == null) {
            return "";
        }
        return String.valueOf(dto.getId());
    }

}
