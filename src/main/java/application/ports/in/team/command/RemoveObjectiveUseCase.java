package application.ports.in.team.command;

import java.util.UUID;

public interface RemoveObjectiveUseCase {
    void removeObjective(UUID teamId, UUID objectiveId);
}
