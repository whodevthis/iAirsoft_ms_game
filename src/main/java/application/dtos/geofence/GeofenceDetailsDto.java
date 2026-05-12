package application.dtos.geofence;

import domain.valueObjects.GeoJson;

import java.util.UUID;

public record GeofenceDetailsDto(UUID id, String name, GeoJson geoJson) {
}
