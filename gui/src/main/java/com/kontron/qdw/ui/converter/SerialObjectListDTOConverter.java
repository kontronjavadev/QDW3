package com.kontron.qdw.ui.converter;

import jakarta.faces.context.*;
import jakarta.enterprise.context.*;
import com.kontron.qdw.dto.serial.*;
import jakarta.faces.component.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;

@Named("serialObjectListDTOConverter")
@RequestScoped
public class SerialObjectListDTOConverter implements Converter<SerialObjectListDTO> {
    @Generated
    private static final String NON_BREAKING_SPACE = "&nbsp;";

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public SerialObjectListDTO getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty() || submittedValue.equals(NON_BREAKING_SPACE))
            return null;

        return new SerialObjectListDTO(Long.parseLong(submittedValue));
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    @Generated
    public String getAsString(FacesContext facesContext, UIComponent component, SerialObjectListDTO value) {
        if (value == null)
            return null;

        return Long.toString(value.getId());
    }

}