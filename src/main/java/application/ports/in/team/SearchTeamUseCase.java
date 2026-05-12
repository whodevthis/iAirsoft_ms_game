package application.ports.in.team;

import application.dtos.game.GameDto;
import application.dtos.team.TeamDto;

import java.util.List;

public interface SearchTeamUseCase {

     List<TeamDto> searchTeam(String data);

}
