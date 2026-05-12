package application.ports.in.player.command;

import application.dtos.player.InputPlayerDto;

import java.util.UUID;

public interface CreatePlayerUseCase {
    UUID create(InputPlayerDto input);

}
