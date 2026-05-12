package application.mappers;

import application.dtos.objective.InputObjectiveDto;
import application.dtos.objective.ObjectiveDetailsDto;
import application.dtos.objective.ObjectiveDto;
import domain.aggregates.Objective;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ObjectiveMapper {

    ObjectiveMapper INSTANCE = Mappers.getMapper(ObjectiveMapper.class);
    @Mapping(target = "id", ignore = true)
    Objective toDomain(InputObjectiveDto dto);

    @Mapping(target = "imagePath", ignore = true)
    ObjectiveDto toDTO(Objective domain);

    @Mapping(target = "imagePath", ignore = true)
    ObjectiveDetailsDto toDetailsDTO(Objective domain);
}