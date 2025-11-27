package com.kontron.qdw.ui.converter;

import jakarta.faces.context.*;
import jakarta.enterprise.context.*;
import jakarta.faces.component.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;

@Named("materialClassListDTOConverter")
@RequestScoped
public class MaterialClassListDTOConverter implements Converter<MaterialClassListDTO> {
    @Generated
    private static final String NON_BREAKING_SPACE = "&nbsp;";

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public MaterialClassListDTO getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty() || submittedValue.equals(NON_BREAKING_SPACE))
            return null;

        return new MaterialClassListDTO(submittedValue);
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    @Generated
    public String getAsString(FacesContext facesContext, UIComponent component, MaterialClassListDTO value) {
        if (value == null)
            return null;

        return value.getCode();
    }

}