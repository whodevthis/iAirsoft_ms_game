package org.project.msgame.application.ports.in.team.command;

import java.util.UUID;

public interface RemovePlayerObjectiveUseCase {
    void removePlayerObjective(UUID teamId, UUID roleId, UUID objectiveId);
}
