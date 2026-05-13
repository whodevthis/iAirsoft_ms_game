package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.team.TeamDetailsDto;
import org.project.msgame.application.dtos.team.TeamDto;
import org.project.msgame.domain.aggregates.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {


    TeamDto toDTO(Team team);
    TeamDetailsDto toDetailsDTO(Team team);
}