package org.project.msgame.application.ports.in.game.command;

import java.util.UUID;

public interface RemoveTeamFromGameUseCase {
    void removeTeam(UUID gameId, UUID teamId);
}