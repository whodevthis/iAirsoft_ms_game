package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.cammo.CammoDetailsDto;
import org.project.msgame.application.dtos.cammo.InputCammoDto;

import org.project.msgame.domain.aggregates.Cammo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CammoMapper {

    CammoMapper INSTANCE = Mappers.getMapper(CammoMapper.class);

    @Mapping(target = "id", ignore = true)
    Cammo toDomain(InputCammoDto dto);


    CammoDetailsDto toDetailsDTO(Cammo domain);
}