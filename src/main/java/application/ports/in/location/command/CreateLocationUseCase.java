package application.ports.in.location.command;

import application.dtos.location.InputLocationDto;

import java.util.UUID;

public interface CreateLocationUseCase {
    UUID create(InputLocationDto input);

}
