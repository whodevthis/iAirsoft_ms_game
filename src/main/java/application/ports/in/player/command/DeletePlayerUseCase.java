package application.ports.in.player.command;

import java.util.UUID;

public interface DeletePlayerUseCase {
    void delete (UUID id);
}
