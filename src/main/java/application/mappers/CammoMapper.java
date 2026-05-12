package application.mappers;

import application.dtos.cammo.InputCammoDto;
import application.dtos.cammo.CammoDetailsDto;

import domain.aggregates.Cammo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CammoMapper {

    CammoMapper INSTANCE = Mappers.getMapper(CammoMapper.class);

    @Mapping(target = "id", ignore = true)
    Cammo toDomain(InputCammoDto dto);

    @Mapping(target = "imagePath", ignore = true)
    CammoDetailsDto toDetailsDTO(Cammo domain);
}