package org.project.msgame.application.ports.in.player.command;

import org.project.msgame.application.dtos.player.InputPlayerDto;

import java.util.UUID;

public interface CreatePlayerUseCase {
    UUID create(InputPlayerDto input);

}
