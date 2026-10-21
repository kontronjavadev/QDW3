package com.kontron.qdw.ui.converter;

import com.kontron.qdw.boundary.base.PlantBoundaryService;
import com.kontron.qdw.dto.base.PlantSearchDTO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("plantSearchDTOConverter")
@RequestScoped
public class PlantSearchDTOConverter implements Converter<PlantSearchDTO> {

    @Inject
    private PlantBoundaryService plantService;

    @Override
    public PlantSearchDTO getAsObject(FacesContext context, UIComponent component, String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }

        try {
            // Holt das vollständige Objekt anhand des Codes aus der Datenbank
            return plantService.findPlantByIdForSearch(code);
        }
        catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Fehler bei der Plant-Konvertierung."), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, PlantSearchDTO plant) {
        if (plant == null) {
            return "";
        }
        return plant.getCode();
    }

}
