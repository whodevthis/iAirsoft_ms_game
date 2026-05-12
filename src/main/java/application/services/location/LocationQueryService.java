package application.services.location;

import application.dtos.location.LocationDetailsDto;
import application.dtos.location.LocationDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.LocationMapper;
import application.ports.in.location.query.GetAllLocationUseCase;
import application.ports.in.location.query.GetLocationByIdUseCase;
import application.ports.in.location.query.SearchLocationUseCase;
import application.ports.out.LocationRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationQueryService implements GetAllLocationUseCase, GetLocationByIdUseCase, SearchLocationUseCase {

    private final LocationMapper locationMapper;
    private final LocationRepositoryPort locationRepositoryPort;

    @Override
    public List<LocationDto> getAll() {
        return locationRepositoryPort.findAll().stream().map(locationMapper::toDTO).toList();
    }

    @Override
    public LocationDetailsDto getById(UUID id) {
        return locationMapper.toDetailsDTO(locationRepositoryPort.findById(id).orElseThrow(()-> new EntityNotFoundException("Location not found")));
    }

    @Override
    public List<LocationDto> search(String data) {
        List<Location> locations = locationRepositoryPort.search(GenericUtils.search(data, Location.class));

        if (locations.isEmpty()) throw new EntityNotFoundException("No locations found for: " + data);

        return locations.stream().map(locationMapper::toDTO).toList();
    }
}
