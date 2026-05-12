package application.services.location;

import application.dtos.location.InputLocationDto;
import application.dtos.location.LocationDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.ports.in.location.command.CreateLocationUseCase;
import application.ports.in.location.command.DeleteLocationUseCase;
import application.ports.in.location.command.UpdateLocationUseCase;
import application.ports.out.LocationRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Location;
import domain.valueObjects.Address;
import domain.valueObjects.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationCommandService implements CreateLocationUseCase, DeleteLocationUseCase, UpdateLocationUseCase {

    private final GenericUtils genericUtils;
    private final LocationRepositoryPort locationRepositoryPort;

    @Transactional
    @Override
    public UUID create(InputLocationDto inputLocationDto) {

        Address address= new Address(inputLocationDto.street(), inputLocationDto.city(), inputLocationDto.cp(),inputLocationDto.province(),inputLocationDto.country());

        Marker marker = new Marker(inputLocationDto.lat(),inputLocationDto.lon());

        Location location = new Location(null, address, marker);
        return locationRepositoryPort.save(location).getId();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        locationRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Location not found: " + id));
        locationRepositoryPort.deleteById(id);
    }

    @Transactional
    @Override
    public UUID update(LocationDetailsDto locationDetailsDto) {
        Location oldLocation = locationRepositoryPort.findById(locationDetailsDto.id()).orElseThrow(() -> new EntityNotFoundException("Location not found"));

        Location updatedLocation = new Location(oldLocation.getId(),
                genericUtils.applyIfChanged(oldLocation.getLocationType(),locationDetailsDto.locationType()),
                genericUtils.applyIfChanged(oldLocation.getDescription(),locationDetailsDto.description())
        );

        return locationRepositoryPort.save(updatedLocation).getId();
    }


}
