package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.location.LocationDetailsDto;
import org.project.msgame.application.dtos.location.LocationDto;
import org.project.msgame.domain.aggregates.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {


    LocationDto toDTO(Location location);
    LocationDetailsDto toDetailsDTO(Location location);
}