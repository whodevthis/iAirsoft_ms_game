package org.project.msgame.application.ports.in.team.command;

import org.project.msgame.application.dtos.team.TeamDetailsDto;

import java.util.UUID;

public interface UpdateTeamUseCase {
    UUID update(TeamDetailsDto teamDetailsDto);
}