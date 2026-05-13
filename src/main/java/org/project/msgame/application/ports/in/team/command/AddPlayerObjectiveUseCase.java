package org.project.msgame.application.ports.in.team.command;

import java.util.UUID;

public interface AddPlayerObjectiveUseCase {
    void addPlayerObjective(UUID teamId, UUID roleId, UUID objectiveId);
}
