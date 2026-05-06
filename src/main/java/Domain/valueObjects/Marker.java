package Domain.valueObjects;

import org.springframework.util.Assert;

public record Marker(double lat, double lon) {
    public Marker {
        Assert.isTrue(lat >= -90 && lat <= 90, "lat must be between -90 and 90");
        Assert.isTrue(lon >= -180 && lon <= 180, "lon must be between -180 and 180");
    }
}