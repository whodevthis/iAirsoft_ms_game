package application.ports.in.team.query;

import application.dtos.team.TeamDetailsDto;

import java.util.UUID;

public interface GetTeamByIdUseCase {
    TeamDetailsDto getById(UUID id);
}
