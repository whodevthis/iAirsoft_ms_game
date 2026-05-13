package org.project.msgame.application.ports.in.geofence.command;

import org.project.msgame.application.dtos.geofence.InputGeofenceDto;

import java.util.UUID;

public interface CreateGeofenceUseCase {
    UUID create(InputGeofenceDto input);

}
