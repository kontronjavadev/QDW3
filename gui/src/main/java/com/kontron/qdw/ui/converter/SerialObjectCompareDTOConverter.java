package com.kontron.qdw.ui.converter;

import jakarta.faces.context.*;
import jakarta.enterprise.context.*;
import com.kontron.qdw.dto.serial.*;
import jakarta.faces.component.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;

@Named("serialObjectCompareDTOConverter")
@RequestScoped
public class SerialObjectCompareDTOConverter implements Converter<SerialObjectCompareDTO> {
    @Generated
    private static final String NON_BREAKING_SPACE = "&nbsp;";

    @Override
    @Generated
    public SerialObjectCompareDTO getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty() || submittedValue.equals(NON_BREAKING_SPACE)) {
            return null;
        }

        return new SerialObjectCompareDTO(Long.parseLong(submittedValue));
    }

    @Override
    @Generated
    public String getAsString(FacesContext facesContext, UIComponent component, SerialObjectCompareDTO value) {
        if (value == null) {
            return null;
        }

        return Long.toString(value.getId());
    }

}
