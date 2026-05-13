package org.project.msgame.application.services.geofence;

import org.project.msgame.application.dtos.geofence.InputGeofenceDto;
import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.ports.in.geofence.command.CreateGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.command.DeleteGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.command.UpdateGeofenceUseCase;
import org.project.msgame.application.ports.out.GeofenceRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Geofence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeofenceCommandService implements CreateGeofenceUseCase, DeleteGeofenceUseCase, UpdateGeofenceUseCase {

    private final GenericUtils genericUtils;
    private final GeofenceRepositoryPort geofenceRepositoryPort;

    @Transactional
    @Override
    public UUID create(InputGeofenceDto inputGeofenceDto) {
        Geofence geofence = new Geofence(null, inputGeofenceDto.name(), inputGeofenceDto.geo());
        return geofenceRepositoryPort.save(geofence).getId();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        geofenceRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Geofence not found: " + id));
        geofenceRepositoryPort.deleteById(id);
    }

    @Transactional
    @Override
    public UUID update(GeofenceDetailsDto geofenceDetailsDto) {
        Geofence oldGeofence = geofenceRepositoryPort.findById(geofenceDetailsDto.id()).orElseThrow(() -> new EntityNotFoundException("Geofence not found"));

        Geofence updatedGeofence = new Geofence(oldGeofence.getId(),
                genericUtils.applyIfChanged(oldGeofence.getName(),geofenceDetailsDto.name()),
                genericUtils.applyIfChanged(oldGeofence.getGeoJson(),geofenceDetailsDto.geoJson())
        );

        return geofenceRepositoryPort.save(updatedGeofence).getId();
    }


}
