package application.dtos.location;

import domain.valueObjects.Address;
import domain.valueObjects.Marker;

import java.util.UUID;

public record LocationDetailsDto(UUID id, UUID geofenceId, Address address, Marker marker) {
}
