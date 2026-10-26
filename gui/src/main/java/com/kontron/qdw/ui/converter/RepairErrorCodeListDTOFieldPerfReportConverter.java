package com.kontron.qdw.ui.converter;

import com.kontron.qdw.dto.service.RepairErrorCodeListDTO;
import com.kontron.qdw.ui.view.FieldPerfReport;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Converter für RepairErrorCodeListDTO nach Name, speziell für die View FieldPerfReport, die die gelesenen Daten cached.
 * Nach jeder Filterung der picklist wird jeder einzelne Wert der source in der Datenbank abgefragt, was man sich mit dem Cache spart.
 */
@Named("repairErrorCodeListDTOFieldPerfReportConverter")
@RequestScoped
public class RepairErrorCodeListDTOFieldPerfReportConverter implements Converter<RepairErrorCodeListDTO> {

    private static final String NON_BREAKING_SPACE = "&nbsp;";
    private final FieldPerfReport fieldPerfReport;

    public RepairErrorCodeListDTOFieldPerfReportConverter() {
        fieldPerfReport = null;
    }

    @Inject
    public RepairErrorCodeListDTOFieldPerfReportConverter(FieldPerfReport fieldPerfReport) {
        this.fieldPerfReport = fieldPerfReport;
    }

    /** html -> java */
    @Override
    public RepairErrorCodeListDTO getAsObject(FacesContext context, UIComponent component, String htmlValue) {
        if (htmlValue == null || htmlValue.isEmpty() || htmlValue.equals(NON_BREAKING_SPACE)) {
            return null;
        }

        return fieldPerfReport.getErrDtoFromCache(htmlValue);
    }

    /** java -> html */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, RepairErrorCodeListDTO dto) {
        if (dto == null || dto.getCode() == null) {
            return "";
        }
        return String.valueOf(dto.getCode());
    }

}
