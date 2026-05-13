package org.project.msgame.application.ports.in.player.command;

import org.project.msgame.application.dtos.player.PlayerDetailsDto;

import java.util.UUID;

public interface UpdatePlayerUseCase {
    UUID update(PlayerDetailsDto playerDetailsDto);
}