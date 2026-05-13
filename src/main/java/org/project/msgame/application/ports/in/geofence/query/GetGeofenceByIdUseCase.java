package org.project.msgame.application.ports.in.geofence.query;

import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;

import java.util.UUID;

public interface GetGeofenceByIdUseCase {
    GeofenceDetailsDto getById(UUID id);
}
