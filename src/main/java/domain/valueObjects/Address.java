package domain.valueObjects;

public record Address(String street, String city, int cp, String province, String country) {
    public Address {
        if (street == null || street.isBlank())
            throw new IllegalArgumentException("street must not be blank");
        if (city == null || city.isBlank())
            throw new IllegalArgumentException("city must not be blank");
        if (province == null || province.isBlank())
            throw new IllegalArgumentException("province must not be blank");
        if (country == null || country.isBlank())
            throw new IllegalArgumentException("country must not be blank");
        if (cp <= 0)
            throw new IllegalArgumentException("cp must be > 0");
    }
}