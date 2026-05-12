package application.services.game;

import application.dtos.game.GameDetailsDto;
import application.dtos.game.InputGameDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.GameMapper;
import application.ports.in.game.AddTeamToGameUseCase;
import application.ports.in.game.CreateGameUseCase;
import application.ports.in.game.RemoveTeamFromGameUseCase;
import application.ports.out.GameRepositoryPort;
import domain.GameDomainService;
import domain.aggregates.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameCommandService implements CreateGameUseCase, AddTeamToGameUseCase, RemoveTeamFromGameUseCase {

    private final GameDomainService gameDomainService;
    private final GameMapper gameMapper;
    private final GameRepositoryPort gameRepositoryPort;


    @Override
    @Transactional
    public UUID createGame(InputGameDto inputGameDto) {
        Game game = gameMapper.toDomain(inputGameDto);
        return gameRepositoryPort.save(game).getId();
    }

    @Transactional
    public UUID updateGame(GameDetailsDto gameDetailsDto) {
        Game oldGame = gameRepositoryPort.findById(gameDetailsDto.id());
        if (oldGame == null) {
            throw new EntityNotFoundException("Game not found");
        }

        Game updatedGame = new Game(
                oldGame.getId(),
                gameDomainService.applyIfChanged(oldGame.getName(), gameDetailsDto.name()),
                gameDomainService.applyIfChanged(oldGame.getDescription(), gameDetailsDto.description()),
                gameDomainService.applyIfChanged(oldGame.getImagePath(), gameDetailsDto.imagePath()),
                gameDomainService.applyIfChanged(oldGame.getInitGame(), gameDetailsDto.initGame()),
                gameDomainService.applyIfChanged(oldGame.getEndGame(), gameDetailsDto.endGame()),
                gameDomainService.applyIfChanged(oldGame.getMaxPlayers(), gameDetailsDto.maxPlayers()),
                gameDomainService.applyIfChanged(oldGame.getTeamIds(), gameDetailsDto.teamIds()),
                gameDomainService.applyIfChanged(oldGame.getBleedingTimeSeconds(), gameDetailsDto.bleedingTimeSeconds()),
                gameDomainService.applyIfChanged(oldGame.getHealingTimeSeconds(), gameDetailsDto.healingTimeSeconds()),
                gameDomainService.applyIfChanged(oldGame.getRecruitingTimeEnd(), gameDetailsDto.recruitingTimeEnd()),
                gameDomainService.applyIfChanged(oldGame.getLocationId(), gameDetailsDto.locationId()),
                gameDomainService.applyIfChanged(oldGame.getStatus(), gameDetailsDto.status())
        );

        return gameRepositoryPort.save(updatedGame).getId();
    }

    @Transactional
    public void delete (UUID id){
        gameRepositoryPort.deleteById(id);
    }

    @Override
    @Transactional
    public void addTeam(UUID gameId, UUID teamId) {
        Game game = gameRepositoryPort.findById(gameId);
        gameDomainService.addTeam(game, teamId);
        gameRepositoryPort.save(game);
    }

    @Override
    @Transactional
    public void removeTeam(UUID gameId, UUID teamId) {
        Game game = gameRepositoryPort.findById(gameId);
        gameDomainService.removeTeam(game,teamId);
        gameRepositoryPort.save(game);
    }



}
