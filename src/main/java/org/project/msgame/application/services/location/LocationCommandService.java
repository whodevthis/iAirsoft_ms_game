package org.project.msgame.application.services.location;

import org.project.msgame.application.dtos.location.InputLocationDto;
import org.project.msgame.application.dtos.location.LocationDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.ports.in.location.command.CreateLocationUseCase;
import org.project.msgame.application.ports.in.location.command.DeleteLocationUseCase;
import org.project.msgame.application.ports.in.location.command.UpdateLocationUseCase;
import org.project.msgame.application.ports.out.LocationRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Location;
import org.project.msgame.domain.valueObjects.Address;
import org.project.msgame.domain.valueObjects.Marker;
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
                genericUtils.applyIfChanged(oldLocation.getGeofenceId(),locationDetailsDto.geofenceId()),
                genericUtils.applyIfChanged(oldLocation.getAddress(),locationDetailsDto.address()),
                genericUtils.applyIfChanged(oldLocation.getMarker(),locationDetailsDto.marker())

        );

        return locationRepositoryPort.save(updatedLocation).getId();
    }


}
