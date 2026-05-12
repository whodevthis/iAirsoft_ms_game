package application.ports.in.team.command;


import application.dtos.team.InputTeamDto;

import java.util.UUID;

public interface CreateTeamUseCase {
    UUID createTeam(InputTeamDto input);

}
