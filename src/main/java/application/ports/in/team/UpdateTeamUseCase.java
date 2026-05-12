package application.ports.in.team;

import application.dtos.team.TeamDetailsDto;

import java.util.UUID;

public interface UpdateTeamUseCase {
    UUID updateTeam(TeamDetailsDto teamDetailsDto);
}