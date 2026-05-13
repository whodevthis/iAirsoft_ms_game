package org.project.msgame.application.ports.in.game.command;

import org.project.msgame.application.dtos.game.GameDetailsDto;

import java.util.UUID;

public interface UpdateGameUseCase {
    UUID update(GameDetailsDto gameDetailsDto);
}
