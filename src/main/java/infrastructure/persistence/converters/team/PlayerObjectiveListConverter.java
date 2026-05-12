package infrastructure.persistence.converters.team;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.persistence.mirrorClass.team.PlayerObjectiveJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class PlayerObjectiveListConverter implements AttributeConverter<List<PlayerObjectiveJson>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final TypeReference<List<PlayerObjectiveJson>> TYPE = new TypeReference<>() {};

    @Override
    public String convertToDatabaseColumn(List<PlayerObjectiveJson> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing PlayerObjective list", e);
        }
    }

    @Override
    public List<PlayerObjectiveJson> convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, TYPE);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing PlayerObjective list", e);
        }
    }
}