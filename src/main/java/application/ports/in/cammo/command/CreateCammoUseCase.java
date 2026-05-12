package application.ports.in.cammo.command;

import application.dtos.cammo.InputCammoDto;

import java.util.UUID;

public interface CreateCammoUseCase {
    UUID create(InputCammoDto input);

}
