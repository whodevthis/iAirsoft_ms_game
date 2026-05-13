package org.project.msgame.application.services.player;

import lombok.extern.slf4j.Slf4j;
import org.project.msgame.application.dtos.player.InputPlayerDto;
import org.project.msgame.application.dtos.player.PlayerDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.ports.in.player.command.CreatePlayerUseCase;
import org.project.msgame.application.ports.in.player.command.DeletePlayerUseCase;
import org.project.msgame.application.ports.in.player.command.UpdatePlayerUseCase;
import org.project.msgame.application.ports.out.PlayerRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Player;
import lombok.RequiredArgsConstructor;
import org.project.msgame.infrastructure.persistence.entities.PlayerEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerCommandService implements CreatePlayerUseCase, DeletePlayerUseCase, UpdatePlayerUseCase {

    private final GenericUtils genericUtils;
    private final PlayerRepositoryPort playerRepositoryPort;

    @Transactional
    @Override
    public UUID create(InputPlayerDto inputPlayerDto) {

        if (playerRepositoryPort.existsByUserId(inputPlayerDto.userId())) {
            log.warn("Player ya existe para userId: {}, ignorando.", inputPlayerDto.userId());
            return playerRepositoryPort.findByUserId(inputPlayerDto.userId()).orElseThrow().getId();
        }

        Player player = new Player(
                null,
                inputPlayerDto.userId(),
                inputPlayerDto.nickname(),
                inputPlayerDto.imagePath()
        );

        UUID createdId = playerRepositoryPort.save(player).getId();
        log.info("Player creado - userId: {}, nickname: {}", inputPlayerDto.userId(), inputPlayerDto.nickname());
        return createdId;
    }


    @Transactional
    @Override
    public void delete(UUID id) {
        playerRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Player not found: " + id));
        playerRepositoryPort.deleteById(id);
    }

    @Transactional
    @Override
    public UUID update(PlayerDetailsDto playerDetailsDto) {
        Player oldPlayer = playerRepositoryPort.findById(playerDetailsDto.id()).orElseThrow(() -> new EntityNotFoundException("Player not found"));

        Player updatedPlayer = new Player(oldPlayer.getId(),
                genericUtils.applyIfChanged(oldPlayer.getUserId(),playerDetailsDto.userId()),
                genericUtils.applyIfChanged(oldPlayer.getNickname(),playerDetailsDto.nickname()),
                genericUtils.applyIfChanged(oldPlayer.getImagePath(),playerDetailsDto.imagePath())
        );

        return playerRepositoryPort.save(updatedPlayer).getId();
    }


}
