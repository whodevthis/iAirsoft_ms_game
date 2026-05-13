package org.project.msgame.application.ports.in.game.query;

import org.project.msgame.application.dtos.game.GameDetailsDto;

import java.util.UUID;

public interface GetGameByIdUseCase {
    GameDetailsDto getById(UUID id);
}
