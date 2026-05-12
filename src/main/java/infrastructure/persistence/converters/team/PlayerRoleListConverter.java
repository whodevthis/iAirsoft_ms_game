package infrastructure.persistence.converters.team;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.persistence.mirrorClass.team.PlayerRoleJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class PlayerRoleListConverter implements AttributeConverter<List<PlayerRoleJson>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final TypeReference<List<PlayerRoleJson>> TYPE = new TypeReference<>() {};

    @Override
    public String convertToDatabaseColumn(List<PlayerRoleJson> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing PlayerRole list", e);
        }
    }

    @Override
    public List<PlayerRoleJson> convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, TYPE);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing PlayerRole list", e);
        }
    }
}