package application.ports.in.game;

import application.dtos.game.GameDto;

import java.util.List;

public interface GetAllGamesUseCase {
    List<GameDto> getAll();
}
