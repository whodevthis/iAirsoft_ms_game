package application.services.role;

import application.dtos.player.InputPlayerDto;
import application.dtos.player.PlayerDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.ports.in.player.command.CreatePlayerUseCase;
import application.ports.in.player.command.DeletePlayerUseCase;
import application.ports.in.player.command.UpdatePlayerUseCase;
import application.ports.out.PlayerRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerCommandService implements CreatePlayerUseCase, DeletePlayerUseCase, UpdatePlayerUseCase {

    private final GenericUtils genericUtils;
    private final PlayerRepositoryPort playerRepositoryPort;

    @Transactional
    @Override
    public UUID create(InputPlayerDto inputPlayerDto) {
        Player player = new Player(null, inputPlayerDto.userId(),inputPlayerDto.nickname(),inputPlayerDto.imagePath());
        return playerRepositoryPort.save(player).getId();
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
