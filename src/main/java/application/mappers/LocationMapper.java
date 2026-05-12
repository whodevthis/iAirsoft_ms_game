package application.mappers;

import application.dtos.location.InputLocationDto;
import application.dtos.location.LocationDetailsDto;
import application.dtos.location.LocationDto;
import domain.aggregates.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "DRAFT")
    Location toDomain(InputLocationDto dto);

    @Mapping(target = "imagePath", ignore = true)
    LocationDto toDTO(Location domain);

    @Mapping(target = "imagePath", ignore = true)
    LocationDetailsDto toDetailsDTO(Location domain);
}