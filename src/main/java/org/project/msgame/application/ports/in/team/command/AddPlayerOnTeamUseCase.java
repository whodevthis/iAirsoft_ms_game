package org.project.msgame.application.ports.in.team.command;

import java.util.UUID;

public interface AddPlayerOnTeamUseCase {
   void addPlayerOnTeam(UUID teamId, UUID playerId, UUID roleId);
}

