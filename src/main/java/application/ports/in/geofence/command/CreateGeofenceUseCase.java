package application.ports.in.geofence.command;

import application.dtos.geofence.InputGeofenceDto;

import java.util.UUID;

public interface CreateGeofenceUseCase {
    UUID create(InputGeofenceDto input);

}
