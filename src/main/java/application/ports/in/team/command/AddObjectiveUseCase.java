package application.ports.in.team.command;

import java.util.UUID;

public interface AddObjectiveUseCase {
   void addObjective(UUID teamId , UUID objectiveId);
}
