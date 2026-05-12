package application.ports.in.game;

import application.dtos.game.GameDetailsDto;

import java.util.UUID;

public interface UpdateGameUseCase {
    UUID updateGame(GameDetailsDto gameDetailsDto);
}