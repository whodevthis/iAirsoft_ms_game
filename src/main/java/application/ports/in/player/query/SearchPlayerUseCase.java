package application.ports.in.player.query;

import application.dtos.player.PlayerDetailsDto;

import java.util.List;

public interface SearchPlayerUseCase {

    List<PlayerDetailsDto> search(String data);

}
