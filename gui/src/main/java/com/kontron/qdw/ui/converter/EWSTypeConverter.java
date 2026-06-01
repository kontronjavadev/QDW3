package com.kontron.qdw.ui.converter;

import jakarta.faces.context.*;
import java.util.*;
import jakarta.enterprise.context.*;
import jakarta.faces.component.*;
import java.util.stream.*;
import com.kontron.qdw.domain.material.*;
import static com.kontron.qdw.ui.UserSession.*;
import com.kontron.qdw.ui.*;
import jakarta.inject.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.faces.convert.*;

@Named("eWSTypeConverter")
@RequestScoped
public class EWSTypeConverter implements Converter<EWSType> {
    @Generated
    private final UserSession userSession;

    /**
     * Default constructor
     */
    @Generated
    public EWSTypeConverter() {
        this.userSession = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userSession
     */
    @Inject
    @Generated
    public EWSTypeConverter(UserSession userSession) {
        this.userSession = userSession;
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @Generated
    public EWSType getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        final ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        return Stream.of(EWSType.values()).filter(value -> bundle.getString("ewstype_" + value.name().toLowerCase()).equals(submittedValue))
                .findFirst().orElse(null);
    }

    /* (non-Javadoc)
     * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    @Generated
    public String getAsString(FacesContext facesContext, UIComponent component, EWSType value) {
        final ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, userSession.getLocale());

        return bundle.getString("ewstype_" + value.name().toLowerCase());
    }

}