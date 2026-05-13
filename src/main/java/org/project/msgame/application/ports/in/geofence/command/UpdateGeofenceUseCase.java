package org.project.msgame.application.ports.in.geofence.command;

import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;

import java.util.UUID;

public interface UpdateGeofenceUseCase {
    UUID update(GeofenceDetailsDto geofenceDetailsDto);
}