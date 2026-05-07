package application.dtos.location;

import domain.valueObjects.Address;

import java.util.UUID;

public record LocationDto(UUID id , Address address) {
}
