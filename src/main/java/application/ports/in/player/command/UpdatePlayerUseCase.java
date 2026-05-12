package application.ports.in.player.command;

import application.dtos.player.PlayerDetailsDto;

import java.util.UUID;

public interface UpdatePlayerUseCase {
    UUID update(PlayerDetailsDto playerDetailsDto);
}