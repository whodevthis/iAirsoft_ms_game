package application.ports.in.cammo.command;

import application.dtos.cammo.CammoDetailsDto;

import java.util.UUID;

public interface UpdateCammoUseCase {
    UUID update(CammoDetailsDto cammoDetailsDto);
}