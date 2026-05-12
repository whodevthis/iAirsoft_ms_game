package application.ports.in.game;

import application.dtos.game.InputGameDto;

import java.util.UUID;

public interface CreateGameUseCase {
    UUID createGame(InputGameDto input);

}
