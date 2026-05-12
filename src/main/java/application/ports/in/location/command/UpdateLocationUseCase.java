package application.ports.in.location.command;

import application.dtos.location.LocationDetailsDto;

import java.util.UUID;

public interface UpdateLocationUseCase {
    UUID update(LocationDetailsDto locationDetailsDto);
}