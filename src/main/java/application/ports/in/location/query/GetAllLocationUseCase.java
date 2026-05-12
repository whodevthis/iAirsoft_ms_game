package application.ports.in.location.query;

import application.dtos.location.LocationDetailsDto;

import java.util.List;

public interface GetAllLocationUseCase {
    List<LocationDetailsDto> getAll();
}
