package application.ports.in.team;

import application.dtos.team.TeamDto;

import java.util.List;

public interface GetAllTeamUseCase {
    List<TeamDto> getAll();
}
