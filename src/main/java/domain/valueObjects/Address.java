package domain.valueObjects;


import org.springframework.util.Assert;

public record Address(String street, String city, int cp, String province, String country) {
    public Address {
        Assert.hasText(street, "street must not be blank");
        Assert.hasText(city, "city must not be blank");
        Assert.isTrue(cp > 0, "cp must be > 0");
        Assert.hasText(province, "province must not be blank");
        Assert.hasText(country, "country must not be blank");
    }
}