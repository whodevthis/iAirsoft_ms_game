package org.project.msgame.application.ports.in.game.command;

import java.util.UUID;

public interface AddTeamToGameUseCase {
    void addTeam(UUID gameId, UUID teamId);
}