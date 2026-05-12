package application.ports.in.objective.command;

import java.util.UUID;

public interface DeleteObjectiveUseCase {
    void delete (UUID id);
}
