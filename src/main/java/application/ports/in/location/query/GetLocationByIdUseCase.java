package application.ports.in.location.query;

import application.dtos.location.LocationDetailsDto;

import java.util.UUID;

public interface GetLocationByIdUseCase {
    LocationDetailsDto getById(UUID id);
}
