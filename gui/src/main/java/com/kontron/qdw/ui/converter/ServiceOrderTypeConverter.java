package com.kontron.qdw.ui.converter;

import jakarta.faces.context.*;
import java.util.*;
import jakarta.enterprise.context.*;
import com.kontron.qdw.domain.mv.*;
import jakarta.faces.component.*;
import java.util.stream.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;

@Named("serviceOrderTypeConverter")
@RequestScoped
public class ServiceOrderTypeConverter implements Converter<ServiceOrderType> {
    @Generated
    private final UserSession userSession;

    /**
     * Default constructor
     */
    @Generated
    public ServiceOrderTypeConverter() {
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     */
    @Inject
    @Generated
    public ServiceOrderTypeConverter(UserSession userSession) {
        this.userSession = userSession;
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public ServiceOrderType getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        final ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        return Stream.of(ServiceOrderType.values())
                .filter(value -> bundle.getString("serviceordertype_" + value.name().toLowerCase()).equals(submittedValue)).findFirst().orElse(null);
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    @Generated
    public String getAsString(FacesContext facesContext, UIComponent component, ServiceOrderType value) {
        final ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        return bundle.getString("serviceordertype_" + value.name().toLowerCase());
    }

}