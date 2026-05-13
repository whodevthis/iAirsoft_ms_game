package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.objective.ObjectiveDetailsDto;
import org.project.msgame.application.dtos.objective.ObjectiveDto;
import org.project.msgame.domain.aggregates.Objective;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectiveMapper {



    ObjectiveDto toDTO(Objective objective);
    ObjectiveDetailsDto toDetailsDTO(Objective objective);
}