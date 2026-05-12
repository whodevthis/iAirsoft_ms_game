package application.services.game;

import application.dtos.game.GameDetailsDto;
import application.dtos.game.GameDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.GameMapper;
import application.ports.in.game.query.GetAllGamesUseCase;
import application.ports.in.game.query.GetGameByIdUseCase;
import application.ports.in.game.query.SearchGameUseCase;
import application.ports.out.GameRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameQueryService implements GetAllGamesUseCase, GetGameByIdUseCase, SearchGameUseCase {

    private final GameRepositoryPort gameRepositoryPort;
    private final GameMapper gameMapper;

    public List<GameDto> getAll() {
        return gameRepositoryPort.findAll().stream().map(gameMapper::toDTO).toList();
    }

    public GameDetailsDto getById(UUID id) {
        return gameMapper.toDetailsDTO(
                gameRepositoryPort.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Game not found: " + id))
        );
    }

    public List<GameDto> search(String data) {
        List<Game> games = gameRepositoryPort.search(GenericUtils.search(data, Game.class));

        if (games.isEmpty()) throw new EntityNotFoundException("No games found for: " + data);

        return games.stream().map(gameMapper::toDTO).toList();
    }

}
