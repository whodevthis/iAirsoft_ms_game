package application.ports.in.geofence.query;

import application.dtos.geofence.GeofenceDetailsDto;

import java.util.UUID;

public interface GetGeofenceByIdUseCase {
    GeofenceDetailsDto getById(UUID id);
}
