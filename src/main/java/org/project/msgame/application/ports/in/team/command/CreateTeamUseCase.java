package org.project.msgame.application.ports.in.team.command;


import org.project.msgame.application.dtos.team.InputTeamDto;

import java.util.UUID;

public interface CreateTeamUseCase {
    UUID create(InputTeamDto input);

}
