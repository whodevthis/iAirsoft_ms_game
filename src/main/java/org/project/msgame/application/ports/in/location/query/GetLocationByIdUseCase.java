package org.project.msgame.application.ports.in.location.query;

import org.project.msgame.application.dtos.location.LocationDetailsDto;

import java.util.UUID;

public interface GetLocationByIdUseCase {
    LocationDetailsDto getById(UUID id);
}
