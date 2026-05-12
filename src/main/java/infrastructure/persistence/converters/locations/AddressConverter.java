package infrastructure.persistence.converters.locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.persistence.mirrorClass.locations.AddressJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AddressConverter implements AttributeConverter<AddressJson, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AddressJson address) {
        try {
            return mapper.writeValueAsString(address);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing Address", e);
        }
    }

    @Override
    public AddressJson convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, AddressJson.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Address", e);
        }
    }
}