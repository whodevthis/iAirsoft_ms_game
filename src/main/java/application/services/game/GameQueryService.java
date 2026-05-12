package application.services.game;

import application.dtos.game.GameDetailsDto;
import application.dtos.game.GameDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.GameMapper;
import application.ports.out.GameRepositoryPort;
import application.specifications.GenericSearch;
import domain.aggregates.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameQueryService {

    private final GameRepositoryPort gameRepositoryPort;
    private final GameMapper gameMapper;

    public List<GameDto> getAll() {
        return gameRepositoryPort.findAll().stream().map(gameMapper::toDTO).toList();
    }

    public GameDetailsDto getById(UUID id) {
        return gameMapper.toDetailsDTO(gameRepositoryPort.findById(id));
    }

    public List<GameDto> searchGame(String data) {
        List<Game> games = gameRepositoryPort.search(GenericSearch.search(data, Game.class));

        if (games.isEmpty()) throw new EntityNotFoundException("No games found for: " + data);

        return games.stream().map(gameMapper::toDTO).toList();
    }

}
