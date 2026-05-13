package org.project.msgame.application.dtos.geofence;

import org.project.msgame.domain.valueObjects.GeoJson;

import java.util.UUID;

public record GeofenceDetailsDto(UUID id, String name, GeoJson geoJson) {
}
