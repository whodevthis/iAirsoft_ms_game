package application.ports.in.team;


import application.dtos.team.InputTeamDto;

import java.util.UUID;

public interface CreateTeamUseCase {
    UUID createTeam(InputTeamDto input);

}
