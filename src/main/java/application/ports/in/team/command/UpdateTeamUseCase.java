package application.ports.in.team.command;

import application.dtos.team.TeamDetailsDto;

import java.util.UUID;

public interface UpdateTeamUseCase {
    UUID updateTeam(TeamDetailsDto teamDetailsDto);
}