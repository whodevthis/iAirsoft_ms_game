package org.project.msgame.application.dtos.location;

import org.project.msgame.domain.valueObjects.Address;
import org.project.msgame.domain.valueObjects.Marker;

import java.util.UUID;

public record LocationDetailsDto(UUID id, UUID geofenceId, Address address, Marker marker) {
}
