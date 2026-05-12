package application.services.role;

import application.dtos.player.PlayerDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.PlayerMapper;
import application.ports.in.player.query.GetAllPlayerUseCase;
import application.ports.in.player.query.GetPlayerByIdUseCase;
import application.ports.in.player.query.SearchPlayerUseCase;
import application.ports.out.PlayerRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerQueryService implements GetAllPlayerUseCase, GetPlayerByIdUseCase, SearchPlayerUseCase {

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
}
