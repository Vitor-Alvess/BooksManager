package org.vitorAlves.shared.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * String to List converter and vice versa.
 * JPA does not support List of type String natively.
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String CHAR_DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute == null || attribute.isEmpty() ?
                "" :
                attribute.stream()
                        .map(String::trim)
                        .map(str -> str + CHAR_DELIMITER)
                        .collect(Collectors.joining());
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return dbData == null || dbData.isEmpty() ?
                Collections.emptyList() :
                Arrays.stream(dbData.split(CHAR_DELIMITER))
                        .map(String::trim)
                        .collect(Collectors.toList());
    }
}
