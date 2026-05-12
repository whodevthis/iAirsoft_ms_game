package domain.valueObjects;

public record GeoJson(String value) {
    public GeoJson {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("geoJson must not be blank");
    }
}