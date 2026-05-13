package org.project.msgame.application.services.game;

import org.project.msgame.application.dtos.game.GameDetailsDto;
import org.project.msgame.application.dtos.game.InputGameDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.mappers.GameMapper;
import org.project.msgame.application.ports.in.game.command.*;
import org.project.msgame.application.ports.in.game.command.*;
import org.project.msgame.application.ports.out.GameRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.service.GameDomainService;
import org.project.msgame.domain.aggregates.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameCommandService implements CreateGameUseCase, UpdateGameUseCase, DeleteGameUseCase, AddTeamToGameUseCase, RemoveTeamFromGameUseCase {

    private final GameDomainService gameDomainService;
    private final GenericUtils genericUtils;
    private final GameMapper gameMapper;
    private final GameRepositoryPort gameRepositoryPort;

    @Override
    @Transactional
    public UUID create(InputGameDto inputGameDto) {
        Game game = gameMapper.toDomain(inputGameDto);
        return gameRepositoryPort.save(game).getId();
    }

    @Transactional
    @Override
    public UUID update(GameDetailsDto gameDetailsDto) {
        Game oldGame = gameRepositoryPort.findById(gameDetailsDto.id())
                .orElseThrow(() -> new EntityNotFoundException("game not found"));
        Game updatedGame = new Game(
                oldGame.getId(),
                genericUtils.applyIfChanged(oldGame.getName(), gameDetailsDto.name()),
                genericUtils.applyIfChanged(oldGame.getDescription(), gameDetailsDto.description()),
                genericUtils.applyIfChanged(oldGame.getImagePath(), gameDetailsDto.imagePath()),
                genericUtils.applyIfChanged(oldGame.getInitGame(), gameDetailsDto.initGame()),
                genericUtils.applyIfChanged(oldGame.getEndGame(), gameDetailsDto.endGame()),
                genericUtils.applyIfChanged(oldGame.getMaxPlayers(), gameDetailsDto.maxPlayers()),
                genericUtils.applyIfChanged(oldGame.getTeamIds(), gameDetailsDto.teamIds()),
                genericUtils.applyIfChanged(oldGame.getBleedingTimeSeconds(), gameDetailsDto.bleedingTimeSeconds()),
                genericUtils.applyIfChanged(oldGame.getHealingTimeSeconds(), gameDetailsDto.healingTimeSeconds()),
                genericUtils.applyIfChanged(oldGame.getRecruitingTimeEnd(), gameDetailsDto.recruitingTimeEnd()),
                genericUtils.applyIfChanged(oldGame.getStatus(), gameDetailsDto.status()),
                genericUtils.applyIfChanged(oldGame.getLocationId(), gameDetailsDto.locationId())
        );
        return gameRepositoryPort.save(updatedGame).getId();
    }

    @Transactional
    @Override
    public void delete(UUID id){
        gameRepositoryPort.deleteById(id);
    }

    @Override
    @Transactional
    public void addTeam(UUID gameId, UUID teamId) {
        Game game = gameRepositoryPort.findById(gameId).orElseThrow(()-> new EntityNotFoundException("game to add team not found"));
        gameDomainService.addTeam(game, teamId);
        gameRepositoryPort.save(game);
    }

    @Override
    @Transactional
    public void removeTeam(UUID gameId, UUID teamId) {
        Game game = gameRepositoryPort.findById(gameId).orElseThrow(()-> new EntityNotFoundException("game to remove team not found"));;
        gameDomainService.removeTeam(game,teamId);
        gameRepositoryPort.save(game);
    }

}
