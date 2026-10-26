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

    /** html -> java */
    @Override
    public SerialNumberSetDTO getAsObject(FacesContext context, UIComponent component, String htmlValue) {
        if (htmlValue == null || htmlValue.isBlank()) {
            return null;
        }

        return new SerialNumberSetDTO(htmlValue);
    }

    /** java -> html */
    @Override
    public String getAsString(FacesContext context, UIComponent component, SerialNumberSetDTO dto) {
        if (dto == null || dto.getCode() == null) {
            return "";
        }
        return dto.getCode();
    }

}
