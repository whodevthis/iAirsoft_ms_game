package org.project.msgame.application.ports.in.team.query;

import org.project.msgame.application.dtos.team.TeamDetailsDto;

import java.util.UUID;

public interface GetTeamByIdUseCase {
    TeamDetailsDto getById(UUID id);
}
