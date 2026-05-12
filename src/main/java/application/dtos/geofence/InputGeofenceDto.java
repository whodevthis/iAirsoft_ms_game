package application.dtos.geofence;

import domain.valueObjects.GeoJson;

public record InputGeofenceDto(String name, GeoJson geo) {
}
