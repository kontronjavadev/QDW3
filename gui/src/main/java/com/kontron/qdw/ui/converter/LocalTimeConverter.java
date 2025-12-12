package com.kontron.qdw.ui.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * JSF converter for fields of type {@link LocalTime}
 * In Anlehnung an JBizMos LocalDateConverter und LocalDateTimeConverter
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@FacesConverter(value = "com.kontron.gpe.intern.ui.converter.LocalTimeConverter", managed = true)
@RequestScoped
public class LocalTimeConverter implements Converter<LocalTime> {
    private static final String ATTRIBUTE_PATTERN = "pattern";
    private static final String ATTRIBUTE_TIME_ZONE = "timeZone";

    @Override
    public LocalTime getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        final var pattern = (String) component.getAttributes().get(ATTRIBUTE_PATTERN);
        final var timeZone = (String) component.getAttributes().get(ATTRIBUTE_TIME_ZONE);

        return LocalTime.parse(submittedValue,
                DateTimeFormatter.ofPattern(pattern).withZone(TimeZone.getTimeZone(timeZone).toZoneId()));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, LocalTime value) {
        if (value == null) {
            return "";
        }

        final var pattern = (String) component.getAttributes().get(ATTRIBUTE_PATTERN);
        final var timeZone = (String) component.getAttributes().get(ATTRIBUTE_TIME_ZONE);

        return DateTimeFormatter.ofPattern(pattern).withZone(TimeZone.getTimeZone(timeZone).toZoneId()).format(value);
    }

}
