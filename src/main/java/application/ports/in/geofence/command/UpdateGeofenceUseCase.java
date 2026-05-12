package application.ports.in.geofence.command;

import application.dtos.geofence.GeofenceDetailsDto;

import java.util.UUID;

public interface UpdateGeofenceUseCase {
    UUID update(GeofenceDetailsDto geofenceDetailsDto);
}