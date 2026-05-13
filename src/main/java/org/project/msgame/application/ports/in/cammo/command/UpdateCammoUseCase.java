package org.project.msgame.application.ports.in.cammo.command;

import org.project.msgame.application.dtos.cammo.CammoDetailsDto;

import java.util.UUID;

public interface UpdateCammoUseCase {
    UUID update(CammoDetailsDto cammoDetailsDto);
}