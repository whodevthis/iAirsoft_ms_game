package Application.dtos.location;

import Domain.valueObjects.Address;

import java.util.UUID;

public record LocationDto(UUID id , Address address) {
}
