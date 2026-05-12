package application.ports.in.game.query;

import application.dtos.game.GameDto;

import java.util.List;

public interface SearchGameUseCase {

     List<GameDto> searchGame(String data);

}
