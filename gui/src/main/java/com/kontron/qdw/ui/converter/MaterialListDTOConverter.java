package com.kontron.qdw.ui.converter;

import org.slf4j.*;
import jakarta.faces.context.*;
import jakarta.enterprise.context.*;
import com.kontron.qdw.boundary.material.*;
import java.lang.invoke.*;
import jakarta.faces.component.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;

@Named("materialListDTOConverter")
@RequestScoped
public class MaterialListDTOConverter implements Converter<MaterialListDTO> {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final String NON_BREAKING_SPACE = "&nbsp;";
    @Generated
    private final MaterialBoundaryService materialService;

    /**
     * Default constructor
     */
    @Generated
    public MaterialListDTOConverter() {
        this.materialService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param materialService
     */
    @Inject
    @Generated
    public MaterialListDTOConverter(MaterialBoundaryService materialService) {
        this.materialService = materialService;
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public MaterialListDTO getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty() || submittedValue.equals(NON_BREAKING_SPACE))
            return null;

        try {
            return materialService.findMaterials(submittedValue).stream().findFirst().orElse(null);
        }
        catch (final Exception e) {
            logger.error("Error while performing object conversion by using the provided input '{}'!", submittedValue, e);
        }

        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    @Generated
    public String getAsString(FacesContext facesContext, UIComponent component, MaterialListDTO value) {
        if (value == null)
            return null;

        return value.getMaterialNumber();
    }

}