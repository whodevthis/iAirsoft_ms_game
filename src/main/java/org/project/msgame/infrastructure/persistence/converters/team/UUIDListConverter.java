package org.project.msgame.infrastructure.persistence.converters.team;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;
import java.util.UUID;

@Converter
public class UUIDListConverter implements AttributeConverter<List<UUID>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final TypeReference<List<UUID>> TYPE = new TypeReference<>() {};

    @Override
    public String convertToDatabaseColumn(List<UUID> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing UUID list", e);
        }
    }

    @Override
    public List<UUID> convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, TYPE);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing UUID list", e);
        }
    }
}