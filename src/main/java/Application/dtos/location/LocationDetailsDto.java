package Application.dtos.location;

import Domain.valueObjects.Address;
import Domain.valueObjects.Marker;

import java.util.UUID;

public record LocationDetailsDto(UUID id, UUID geofenceId, Address address, Marker marker) {
}
