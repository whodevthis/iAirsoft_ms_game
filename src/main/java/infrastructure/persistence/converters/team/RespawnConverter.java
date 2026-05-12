package infrastructure.persistence.converters.team;


import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.persistence.mirrorClass.team.RespawnJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RespawnConverter implements AttributeConverter<RespawnJson, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(RespawnJson respawn) {
        try {
            return mapper.writeValueAsString(respawn);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing Respawn", e);
        }
    }

    @Override
    public RespawnJson convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, RespawnJson.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Respawn", e);
        }
    }
}