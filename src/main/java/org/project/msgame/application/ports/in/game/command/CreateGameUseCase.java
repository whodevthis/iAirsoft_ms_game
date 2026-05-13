package org.project.msgame.application.ports.in.game.command;

import org.project.msgame.application.dtos.game.InputGameDto;

import java.util.UUID;

public interface CreateGameUseCase {
    UUID create(InputGameDto input);

}
