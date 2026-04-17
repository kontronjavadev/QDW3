package com.kontron.qdw.ui.converter;

import org.slf4j.*;
import jakarta.faces.context.*;
import jakarta.enterprise.context.*;
import java.lang.invoke.*;
import jakarta.faces.component.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;
import com.kontron.qdw.boundary.service.*;
import com.kontron.qdw.dto.service.*;

@Named("repairErrorCodeListDTOConverter")
@RequestScoped
public class RepairErrorCodeListDTOConverter implements Converter<RepairErrorCodeListDTO> {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final String NON_BREAKING_SPACE = "&nbsp;";
    @Generated
    private final RepairErrorCodeBoundaryService repairErrorCodeService;

    /**
     * Default constructor
     */
    @Generated
    public RepairErrorCodeListDTOConverter() {
        this.repairErrorCodeService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repairErrorCodeService
     */
    @Inject
    @Generated
    public RepairErrorCodeListDTOConverter(RepairErrorCodeBoundaryService repairErrorCodeService) {
        this.repairErrorCodeService = repairErrorCodeService;
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public RepairErrorCodeListDTO getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty() || submittedValue.equals(NON_BREAKING_SPACE))
            return null;

        try {
            return repairErrorCodeService.findRepairErrorCodes(submittedValue).stream().findFirst().orElse(null);
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
    public String getAsString(FacesContext facesContext, UIComponent component, RepairErrorCodeListDTO value) {
        if (value == null)
            return null;

        return value.getName();
    }

}