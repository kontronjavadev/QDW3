package com.kontron.qdw.ui.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormatSymbols;
import java.time.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.apache.poi.ss.usermodel.*;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.export.DataTableExcelXStreamExporter;
import org.primefaces.component.export.ColumnValue;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.ValueHolder;
import jakarta.faces.component.html.HtmlCommandLink;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.*;


public class CustomExcelXStreamExporter extends DataTableExcelXStreamExporter {

    private static final String STRIP_CHARS = " \t\n\r\f\u00A0\u202F";

    private CellStyle dateStyle;
    private CellStyle dateTimeStyle;
    private CellStyle integerStyle;
    private CellStyle textStyle;
    private final Map<Integer, CellStyle> percentStyles = new HashMap<>();
    private DecimalFormatSymbols decimalSymbols;

    private record ExtractedValue(Object value, Converter<?> converter) {
    }



    @Override
    protected void preExport(FacesContext context) throws IOException {
        super.preExport(context);
        // Styles sind an das jeweilige Workbook gebunden -> pro Export neu anlegen
        dateStyle = null;
        dateTimeStyle = null;
        integerStyle = null;
        textStyle = null;
        percentStyles.clear();
        // decimalSymbols = DecimalFormatSymbols.getInstance(LocaleUtils.getCurrentLocale(context));
        decimalSymbols = DecimalFormatSymbols.getInstance(Locale.getDefault(Locale.Category.FORMAT));
    }


    @Override
    protected void exportCellValue(FacesContext context, DataTable table, UIColumn col, ColumnValue columnValue, int i) {
        ExtractedValue extracted = extractRawValue(col);

        // Optionales Opt-in pro <p:column> via <f:attribute name="exportAsText" value="true" />
        if (isForceText(col)) {
            Cell cell = row().createCell(i);
            cell.setCellValue(columnValue != null ? columnValue.toString() : "");
            cell.setCellStyle(getTextStyle());
            return;
        }

        // 1. Echte Datums- und Zeitstempeltypen direkt typisiert schreiben
        if (extracted != null && extracted.value() != null && tryWriteTemporal(extracted, i)) {
            return;
        }

        // 2. Große Ganzzahlen (12-15 Stellen ohne E+, >=16 Stellen als Text ohne Rundungsverlust)
        // sowie Ziffernfolgen mit führender Null (z.B. Materialnummern) vor dem ExcelStylesManager abfangen
        String text = columnValue != null ? columnValue.toString() : null;
        if (tryWriteLargeIntegerOrCode(extracted, text, i)) {
            return;
        }

        // 3. Prozent-Strings (z.B. "12,34%" oder "12,34 %") als echte numerische Prozentwerte schreiben
        if (tryWritePercentage(text, i)) {
            return;
        }

        // Sonst: Standard-Verhalten von PrimeFaces für alle übrigen Zellen
        super.exportCellValue(context, table, col, columnValue, i);
    }



    private boolean tryWriteLargeIntegerOrCode(ExtractedValue extracted, String formattedText, int colIndex) {
        Object raw = extracted != null ? extracted.value() : null;
        Converter<?> converter = extracted != null ? extracted.converter() : null;

        String s = null;
        boolean fromString = false;

        if ((raw instanceof Long || raw instanceof BigInteger)
                && (converter == null || converter instanceof LongConverter)) {
            s = raw.toString();
        }
        else if (raw instanceof String rawStr && converter == null) {
            s = StringUtils.strip(rawStr, STRIP_CHARS);
            fromString = true;
        }
        else if (formattedText != null) {
            // Bei Custom-Converter, exportFunction oder field="..." immer den formatierten Wert prüfen
            s = StringUtils.strip(formattedText, STRIP_CHARS);
            fromString = true;
        }

        if (StringUtils.isEmpty(s)) {
            return false;
        }

        String digits = Strings.CS.removeStart(s, "-");
        if (!StringUtils.isNumeric(digits)) {
            return false;
        }

        // Führende Null (nur bei Strings wie Materialnummern/PLZ) oder >= 16 Stellen -> zwingend Text
        if ((fromString && digits.length() > 1 && digits.charAt(0) == '0') || digits.length() >= 16) {
            Cell cell = row().createCell(colIndex);
            cell.setCellValue(s);
            cell.setCellStyle(getTextStyle());
            return true;
        }

        // 12 bis 15 Stellen -> exakt in IEEE-754 double darstellbar, Format "0" verhindert E+-Notation
        if (digits.length() >= 12) {
            Cell cell = row().createCell(colIndex);
            cell.setCellValue(Double.parseDouble(s));
            cell.setCellStyle(getIntegerStyle());
            return true;
        }

        return false;
    }

    private boolean tryWriteTemporal(ExtractedValue extracted, int colIndex) {
        Object raw = extracted.value();

        if (raw instanceof LocalDate ld) {
            Cell cell = row().createCell(colIndex);
            cell.setCellValue(ld);
            cell.setCellStyle(getDateStyle());
            return true;
        }

        if (raw instanceof java.sql.Date sqlDate) {
            // über toLocalDate() gehen, um Zeitzonen-Verschiebungen zu vermeiden
            Cell cell = row().createCell(colIndex);
            cell.setCellValue(sqlDate.toLocalDate());
            cell.setCellStyle(getDateStyle());
            return true;
        }

        if (raw instanceof java.sql.Timestamp ts) {
            writeLocalDateTime(ts.toLocalDateTime(), extracted.converter(), colIndex);
            return true;
        }

        if (raw instanceof LocalDateTime ldt) {
            writeLocalDateTime(ldt, extracted.converter(), colIndex);
            return true;
        }

        if (raw instanceof ZonedDateTime zdt) {
            ZoneId zone = resolveZoneId(extracted.converter());
            writeLocalDateTime(zdt.withZoneSameInstant(zone).toLocalDateTime(), extracted.converter(), colIndex);
            return true;
        }

        if (raw instanceof OffsetDateTime odt) {
            ZoneId zone = resolveZoneId(extracted.converter());
            writeLocalDateTime(odt.atZoneSameInstant(zone).toLocalDateTime(), extracted.converter(), colIndex);
            return true;
        }

        if (raw instanceof Instant instant) {
            ZoneId zone = resolveZoneId(extracted.converter());
            writeLocalDateTime(LocalDateTime.ofInstant(instant, zone), extracted.converter(), colIndex);
            return true;
        }

        if (raw instanceof Date date) {
            ZoneId zone = resolveZoneId(extracted.converter());
            LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), zone);
            boolean dateOnly = isDateOnly(extracted.converter(), ldt);
            Cell cell = row().createCell(colIndex);
            if (dateOnly) {
                cell.setCellValue(ldt.toLocalDate());
                cell.setCellStyle(getDateStyle());
            }
            else {
                cell.setCellValue(ldt);
                cell.setCellStyle(getDateTimeStyle());
            }
            return true;
        }

        return false;
    }

    private boolean tryWritePercentage(String formattedText, int colIndex) {
        String trimmed = StringUtils.strip(formattedText, STRIP_CHARS);
        if (!Strings.CS.endsWith(trimmed, "%")) {
            return false;
        }

        String numPart = StringUtils.strip(Strings.CS.removeEnd(trimmed, "%"), STRIP_CHARS);
        BigDecimal parsed = parseDecimal(numPart);
        if (parsed == null) {
            return false;
        }

        int decimals = Math.min(Math.max(parsed.scale(), 0), 6);
        Cell cell = row().createCell(colIndex);
        cell.setCellValue(parsed.movePointLeft(2).doubleValue());
        cell.setCellStyle(getPercentStyle(decimals));
        return true;
    }

    private void writeLocalDateTime(LocalDateTime ldt, Converter<?> converter, int colIndex) {
        Cell cell = row().createCell(colIndex);
        if (isConverterDateOnly(converter)) {
            cell.setCellValue(ldt.toLocalDate());
            cell.setCellStyle(getDateStyle());
        }
        else {
            cell.setCellValue(ldt);
            cell.setCellStyle(getDateTimeStyle());
        }
    }



    private ExtractedValue extractRawValue(UIColumn col) {
        if (col.getExportValue() != null) {
            return new ExtractedValue(col.getExportValue(), null);
        }
        if (col.getExportFunction() != null || StringUtils.isNotBlank(col.getField())) {
            return null;
        }

        ExtractedValue found = null;
        for (UIComponent child : col.getChildren()) {
            if (!child.isRendered()) {
                continue;
            }

            ExtractedValue current = extractFromComponent(child);
            if (current != null) {
                if (found != null) {
                    // Mehrere Wert-Komponenten in einer Spalte -> PrimeFaces-Join nutzen
                    return null;
                }
                found = current;
            }
            else {
                // Statischer Text neben der Komponente? Whitespace ignorieren, echten Text nicht überschreiben
                String plain = child.toString();
                if (plain != null && !plain.trim().isEmpty()) {
                    return null;
                }
            }
        }
        return found;
    }

    private ExtractedValue extractFromComponent(UIComponent component) {
        if (component instanceof ValueHolder vh) {
            return new ExtractedValue(vh.getValue(), vh.getConverter());
        }
        if (component instanceof HtmlCommandLink link) {
            if (link.getValue() != null) {
                return new ExtractedValue(link.getValue(), null);
            }
            for (UIComponent child : link.getChildren()) {
                if (child.isRendered() && child instanceof ValueHolder vh) {
                    return new ExtractedValue(vh.getValue(), vh.getConverter());
                }
            }
        }
        return null;
    }



    private ZoneId resolveZoneId(Converter<?> converter) {
        if (converter instanceof DateTimeConverter dtc) {
            TimeZone tz = dtc.getTimeZone();
            if (tz != null) {
                return tz.toZoneId();
            }
        }
        return ZoneId.systemDefault();
    }

    private boolean patternHasTime(String pattern) {
        boolean inQuote = false;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '\'') {
                inQuote = !inQuote;
            }
            else if (!inQuote && (c == 'H' || c == 'h' || c == 'k' || c == 'K' || c == 'm' || c == 's')) {
                return true;
            }
        }
        return false;
    }

    private BigDecimal parseDecimal(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }

        String sign = s.startsWith("-") ? "-" : "";
        String body = (s.startsWith("-") || s.startsWith("+")) ? s.substring(1) : s;
        if (body.isEmpty()) {
            return null;
        }

        char decSep = decimalSymbols.getDecimalSeparator();
        char grpSep = decimalSymbols.getGroupingSeparator();

        int decIdx = body.indexOf(decSep);
        String intPart;
        String fracPart = null;

        if (decIdx >= 0) {
            if (body.indexOf(decSep, decIdx + 1) >= 0 || decIdx == 0 || decIdx == body.length() - 1) {
                return null;
            }
            intPart = body.substring(0, decIdx);
            fracPart = body.substring(decIdx + 1);
            if (!StringUtils.isNumeric(fracPart)) {
                return null;
            }
        }
        else {
            intPart = body;
        }

        String normalizedInt = normalizeIntegerPart(intPart, grpSep);
        if (normalizedInt == null) {
            return null;
        }

        String normalized = fracPart != null ? sign + normalizedInt + "." + fracPart : sign + normalizedInt;
        return new BigDecimal(normalized);
    }

    private String normalizeIntegerPart(String intPart, char grpSep) {
        if (intPart.indexOf(grpSep) < 0) {
            return StringUtils.isNumeric(intPart) ? intPart : null;
        }

        String[] groups = StringUtils.splitPreserveAllTokens(intPart, grpSep);
        // Erste Gruppe: 1-3 Ziffern, keine führende Null (verhindert Fehlinterpretation von "0.123" in de_DE)
        if (groups.length < 2
                || groups[0].length() < 1 || groups[0].length() > 3
                || groups[0].startsWith("0")
                || !StringUtils.isNumeric(groups[0])) {
            return null;
        }

        for (int i = 1; i < groups.length; i++) {
            if (groups[i].length() != 3 || !StringUtils.isNumeric(groups[i])) {
                return null;
            }
        }

        return String.join("", groups);
    }



    private boolean isForceText(UIColumn col) {
        if (col instanceof UIComponent comp) {
            return Boolean.parseBoolean(String.valueOf(comp.getAttributes().get("exportAsText")));
        }
        return false;
    }

    private boolean isDateOnly(Converter<?> converter, LocalDateTime ldt) {
        if (converter instanceof DateTimeConverter dtc) {
            return isConverterDateOnly(dtc);
        }
        return LocalTime.MIDNIGHT.equals(ldt.toLocalTime());
    }

    private boolean isConverterDateOnly(Converter<?> converter) {
        if (!(converter instanceof DateTimeConverter dtc)) {
            return false;
        }
        String pattern = dtc.getPattern();
        if (StringUtils.isNotBlank(pattern)) {
            return !patternHasTime(pattern);
        }
        return "date".equalsIgnoreCase(dtc.getType());
    }



    private CellStyle getDateStyle() {
        if (dateStyle == null) {
            dateStyle = document.createCellStyle();
            DataFormat df = document.createDataFormat();
            dateStyle.setDataFormat(df.getFormat("dd.mm.yyyy"));
        }
        return dateStyle;
    }

    private CellStyle getDateTimeStyle() {
        if (dateTimeStyle == null) {
            dateTimeStyle = document.createCellStyle();
            DataFormat df = document.createDataFormat();
            dateTimeStyle.setDataFormat(df.getFormat("dd.mm.yyyy hh:mm:ss"));
        }
        return dateTimeStyle;
    }

    private CellStyle getIntegerStyle() {
        if (integerStyle == null) {
            integerStyle = document.createCellStyle();
            DataFormat df = document.createDataFormat();
            integerStyle.setDataFormat(df.getFormat("0"));
        }
        return integerStyle;
    }

    private CellStyle getTextStyle() {
        if (textStyle == null) {
            textStyle = document.createCellStyle();
            DataFormat df = document.createDataFormat();
            textStyle.setDataFormat(df.getFormat("@"));
        }
        return textStyle;
    }

    private CellStyle getPercentStyle(int decimals) {
        return percentStyles.computeIfAbsent(decimals, d -> {
            CellStyle style = document.createCellStyle();
            DataFormat df = document.createDataFormat();
            // Excel erwartet englische Format-Token und lokalisiert das Dezimalzeichen selbst
            String pattern = d == 0 ? "0%" : "0." + "0".repeat(d) + "%";
            style.setDataFormat(df.getFormat(pattern));
            return style;
        });
    }

}
