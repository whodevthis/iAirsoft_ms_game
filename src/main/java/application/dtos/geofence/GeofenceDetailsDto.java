package application.dtos.geofence;

import java.util.UUID;

public record GeofenceDetailsDto(UUID id, String name, String geoJson) {
}
