package application.ports.in.game;

import application.dtos.game.GameDto;

import java.util.List;
import java.util.UUID;

public interface SearchGameUseCase {

     List<GameDto> searchGame(String data);

}
