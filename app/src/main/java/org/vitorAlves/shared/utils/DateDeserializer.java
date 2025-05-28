package org.vitorAlves.shared.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateDeserializer extends JsonDeserializer<LocalDate> {
    private static final DateTimeFormatter[] FORMATTERS = new DateTimeFormatter[]{
        DateTimeFormatter.ofPattern("MMMM d, yyyy"),
        DateTimeFormatter.ofPattern("yyyy"),
        DateTimeFormatter.ofPattern("yyyy-MM")
    };

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText().trim();
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                if (formatter.equals(FORMATTERS[0])) return LocalDate.parse(text, formatter);
                else if (formatter.equals(FORMATTERS[1])) {
                    int year = Integer.parseInt(text);
                    return LocalDate.of(year, 1, 1);
                }
                else {
                    return LocalDate.parse(text + "-01");
                }
            }
            catch (DateTimeParseException | NumberFormatException ignored) {}
        }
        
        throw InvalidFormatException.from(p, "Unparseable date", text, LocalDate.class);
    }
}
