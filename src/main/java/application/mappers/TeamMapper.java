package application.mappers;

import application.dtos.team.InputTeamDto;
import application.dtos.team.TeamDetailsDto;
import application.dtos.team.TeamDto;
import domain.aggregates.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "DRAFT")
    Team toDomain(InputTeamDto dto);

    @Mapping(target = "imagePath", ignore = true)
    TeamDto toDTO(Team domain);

    @Mapping(target = "imagePath", ignore = true)
    TeamDetailsDto toDetailsDTO(Team domain);
}