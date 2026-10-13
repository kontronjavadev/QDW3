package com.kontron.qdw.ui.converter;

import com.kontron.qdw.dto.material.SerialNumberSetDTO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Named;

@Named("serialNumberSetDTOConverter")
@RequestScoped
public class SerialNumberSetDTOConverter implements Converter<SerialNumberSetDTO> {

    @Override
    public SerialNumberSetDTO getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        return new SerialNumberSetDTO(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, SerialNumberSetDTO value) {
        if (value == null) {
            return null;
        }
        return value.getCode();
    }

}
