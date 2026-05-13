package org.project.msgame.application.ports.in.geofence.query;

import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;

import java.util.List;

public interface SearchGeofenceUseCase {

     List<GeofenceDetailsDto> search(String data);

}
