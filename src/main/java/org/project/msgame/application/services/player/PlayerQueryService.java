package org.project.msgame.application.services.player;

import org.project.msgame.application.dtos.player.PlayerDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.mappers.PlayerMapper;
import org.project.msgame.application.ports.in.player.query.GetAllPlayerUseCase;
import org.project.msgame.application.ports.in.player.query.GetPlayerByIdUseCase;
import org.project.msgame.application.ports.in.player.query.GetPlayerByUserIdUseCase;
import org.project.msgame.application.ports.in.player.query.SearchPlayerUseCase;
import org.project.msgame.application.ports.out.PlayerRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerQueryService implements GetAllPlayerUseCase, GetPlayerByIdUseCase, SearchPlayerUseCase , GetPlayerByUserIdUseCase {

    private final PlayerMapper playerMapper;
    private final PlayerRepositoryPort playerRepositoryPort;

    @Override
    public List<PlayerDetailsDto> getAll() {
        return playerRepositoryPort.findAll().stream().map(playerMapper::toDetailsDTO).toList();
    }

    @Override
    public PlayerDetailsDto getById(UUID id) {
        return playerMapper.toDetailsDTO(playerRepositoryPort.findById(id).orElseThrow(()-> new EntityNotFoundException("Player not found")));
    }

    @Override
    public List<PlayerDetailsDto> search(String data) {
        List<Player> players = playerRepositoryPort.search(GenericUtils.search(data, Player.class));

        if (players.isEmpty()) throw new EntityNotFoundException("No players found for: " + data);

        return players.stream().map(playerMapper::toDetailsDTO).toList();
    }
    @Override
    public PlayerDetailsDto getByUserId(UUID userId) {
        return playerMapper.toDetailsDTO(
                playerRepositoryPort.findByUserId(userId)
                        .orElseThrow(() -> new EntityNotFoundException("Player not found for userId: " + userId))
        );
    }
}
