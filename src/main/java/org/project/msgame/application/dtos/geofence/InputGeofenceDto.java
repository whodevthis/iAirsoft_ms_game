package org.project.msgame.application.dtos.geofence;

import org.project.msgame.domain.valueObjects.GeoJson;

public record InputGeofenceDto(String name, GeoJson geo) {
}
