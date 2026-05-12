package infrastructure.persistence.converters.team;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.persistence.mirrorClass.team.TeamRoleJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class TeamRoleListConverter implements AttributeConverter<List<TeamRoleJson>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final TypeReference<List<TeamRoleJson>> TYPE = new TypeReference<>() {};

    @Override
    public String convertToDatabaseColumn(List<TeamRoleJson> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing TeamRole list", e);
        }
    }

    @Override
    public List<TeamRoleJson> convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, TYPE);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing TeamRole list", e);
        }
    }
}