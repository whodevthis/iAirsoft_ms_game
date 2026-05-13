package org.project.msgame.application.services.geofence;

import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.mappers.GeofenceMapper;
import org.project.msgame.application.ports.in.geofence.query.GetAllGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.query.GetGeofenceByIdUseCase;
import org.project.msgame.application.ports.in.geofence.query.SearchGeofenceUseCase;
import org.project.msgame.application.ports.out.GeofenceRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Geofence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeofenceQueryService implements GetAllGeofenceUseCase, GetGeofenceByIdUseCase, SearchGeofenceUseCase {

    private final GeofenceMapper geofenceMapper;
    private final GeofenceRepositoryPort geofenceRepositoryPort;

    @Override
    public List<GeofenceDetailsDto> getAll() {
        return geofenceRepositoryPort.findAll().stream().map(geofenceMapper::toDetailsDTO).toList();
    }

    @Override
    public GeofenceDetailsDto getById(UUID id) {
        return geofenceMapper.toDetailsDTO(geofenceRepositoryPort.findById(id).orElseThrow(()-> new EntityNotFoundException("Geofence not found")));
    }

    @Override
    public List<GeofenceDetailsDto> search(String data) {
        List<Geofence> geofences = geofenceRepositoryPort.search(GenericUtils.search(data, Geofence.class));

        if (geofences.isEmpty()) throw new EntityNotFoundException("No geofences found for: " + data);

        return geofences.stream().map(geofenceMapper::toDetailsDTO).toList();
    }
}
