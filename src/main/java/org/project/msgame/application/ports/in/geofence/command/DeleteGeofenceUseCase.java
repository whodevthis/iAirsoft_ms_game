package org.project.msgame.application.ports.in.geofence.command;

import java.util.UUID;

public interface DeleteGeofenceUseCase {
    void delete (UUID id);
}
