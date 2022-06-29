package pl.edu.pk.ztp.librarymonolith.converter;

import pl.edu.pk.ztp.librarymonolith.dto.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Converter
public class RolesListToStringConverter implements AttributeConverter<List<Role>, String> {

    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        return roles.stream().map(role -> role.name()).collect(Collectors.joining(","));
    }

    @Override
    public List<Role> convertToEntityAttribute(String s) {
        String[] concatenated = s.split(",");
        return Arrays.stream(concatenated).map(value -> Role.valueOf(value.toUpperCase())).collect(Collectors.toList());
    }
}
