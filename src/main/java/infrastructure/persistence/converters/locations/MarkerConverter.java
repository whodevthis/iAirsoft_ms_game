package infrastructure.persistence.converters.locations;


import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.persistence.mirrorClass.locations.MarkerJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MarkerConverter implements AttributeConverter<MarkerJson, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(MarkerJson marker) {
        try {
            return mapper.writeValueAsString(marker);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing Marker", e);
        }
    }

    @Override
    public MarkerJson convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, MarkerJson.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Marker", e);
        }
    }
}