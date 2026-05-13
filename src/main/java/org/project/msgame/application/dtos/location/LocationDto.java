package org.project.msgame.application.dtos.location;

import org.project.msgame.domain.valueObjects.Address;

import java.util.UUID;

public record LocationDto(UUID id , Address address) {
}
