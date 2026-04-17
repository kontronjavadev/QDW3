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

@Named("repairServiceListDTOConverter")
@RequestScoped
public class RepairServiceListDTOConverter implements Converter<RepairServiceListDTO> {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final String NON_BREAKING_SPACE = "&nbsp;";
    @Generated
    private final RepairServiceBoundaryService repairServiceService;

    /**
     * Default constructor
     */
    @Generated
    public RepairServiceListDTOConverter() {
        this.repairServiceService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repairServiceService
     */
    @Inject
    @Generated
    public RepairServiceListDTOConverter(RepairServiceBoundaryService repairServiceService) {
        this.repairServiceService = repairServiceService;
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public RepairServiceListDTO getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty() || submittedValue.equals(NON_BREAKING_SPACE))
            return null;

        try {
            return repairServiceService.findRepairServices(submittedValue).stream().findFirst().orElse(null);
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
    public String getAsString(FacesContext facesContext, UIComponent component, RepairServiceListDTO value) {
        if (value == null)
            return null;

        return value.getName();
    }

}