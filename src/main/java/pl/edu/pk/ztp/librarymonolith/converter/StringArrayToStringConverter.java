package pl.edu.pk.ztp.librarymonolith.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringArrayToStringConverter implements AttributeConverter<String[], String> {

    @Override
    public String convertToDatabaseColumn(String[] strings) {
        String value = null;
        if (strings.length > 0) {
            value = String.join(",", strings);
        }
        return value;
    }

    @Override
    public String[] convertToEntityAttribute(String s) {
        String[] value = null;
        if (s.length() > 0)
            value = s.split(",");
        return value;
    }
}
