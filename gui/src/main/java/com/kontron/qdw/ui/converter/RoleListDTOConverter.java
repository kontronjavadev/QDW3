package com.kontron.qdw.ui.converter;

import com.kontron.qdw.boundary.base.*;
import org.slf4j.*;
import jakarta.faces.context.*;
import jakarta.enterprise.context.*;
import java.lang.invoke.*;
import jakarta.faces.component.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;
import com.kontron.qdw.dto.base.*;

@Named("roleListDTOConverter")
@RequestScoped
public class RoleListDTOConverter implements Converter<RoleListDTO> {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final String NON_BREAKING_SPACE = "&nbsp;";
    @Generated
    private final RoleBoundaryService roleService;

    /**
     * Default constructor
     */
    @Generated
    public RoleListDTOConverter() {
        this.roleService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param roleService
     */
    @Inject
    @Generated
    public RoleListDTOConverter(RoleBoundaryService roleService) {
        this.roleService = roleService;
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public RoleListDTO getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty() || submittedValue.equals(NON_BREAKING_SPACE))
            return null;

        try {
            return roleService.findRoles(submittedValue).stream().findFirst().orElse(null);
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
    public String getAsString(FacesContext facesContext, UIComponent component, RoleListDTO value) {
        if (value == null)
            return null;

        return value.getName();
    }

}