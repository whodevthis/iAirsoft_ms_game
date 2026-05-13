package org.project.msgame.application.ports.in.team.command;

import java.util.UUID;

public interface RemovePlayerOnTeamUseCase {
    void removePlayerOnTeam(UUID teamId, UUID playerId);
}
