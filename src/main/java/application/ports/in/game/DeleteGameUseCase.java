package application.ports.in.game;

import java.util.UUID;

public interface DeleteGameUseCase {
    void deleteGame (UUID id);
}
