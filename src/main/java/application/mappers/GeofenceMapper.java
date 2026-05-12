package application.mappers;

import application.dtos.geofence.GeofenceDetailsDto;
import application.dtos.geofence.InputGeofenceDto;
import domain.aggregates.Geofence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GeofenceMapper {

    GeofenceMapper INSTANCE = Mappers.getMapper(GeofenceMapper.class);

    @Mapping(target = "id", ignore = true)
    Geofence toDomain(InputGeofenceDto dto);

    @Mapping(target = "imagePath", ignore = true)
    GeofenceDetailsDto toDetailsDTO(Geofence domain);
}