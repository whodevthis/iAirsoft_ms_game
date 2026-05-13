package org.project.msgame.domain.valueObjects;

public record Marker(double lat, double lon,  String iconUrl) {
    public Marker {
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("lat must be between -90 and 90");
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException("lon must be between -180 and 180");
        }
    }
    public Marker(double lat, double lon) {
        this(lat, lon, null);
    }
}