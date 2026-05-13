package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;
import org.project.msgame.domain.aggregates.Geofence;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeofenceMapper {


    GeofenceDetailsDto toDetailsDTO(Geofence geofence);
}