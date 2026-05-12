package application.ports.in.player.query;

import application.dtos.player.PlayerDetailsDto;

import java.util.UUID;

public interface GetPlayerByIdUseCase {
    PlayerDetailsDto getById(UUID id);
}
