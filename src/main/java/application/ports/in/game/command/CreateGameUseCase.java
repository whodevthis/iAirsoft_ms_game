package application.ports.in.game.command;

import application.dtos.game.InputGameDto;

import java.util.UUID;

public interface CreateGameUseCase {
    UUID create(InputGameDto input);

}
