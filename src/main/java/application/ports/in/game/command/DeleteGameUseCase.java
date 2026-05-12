package application.ports.in.game.command;

import java.util.UUID;

public interface DeleteGameUseCase {
    void delete(UUID id);
}
