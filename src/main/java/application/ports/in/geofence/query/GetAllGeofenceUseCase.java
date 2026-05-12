package application.ports.in.geofence.query;

import application.dtos.geofence.GeofenceDetailsDto;

import java.util.List;

public interface GetAllGeofenceUseCase {
    List<GeofenceDetailsDto> getAll();
}
