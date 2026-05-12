package application.ports.in.team;

import application.dtos.team.TeamDetailsDto;

import java.util.UUID;

public interface GetTeamByIdUseCase {
    TeamDetailsDto getById(UUID id);
}
