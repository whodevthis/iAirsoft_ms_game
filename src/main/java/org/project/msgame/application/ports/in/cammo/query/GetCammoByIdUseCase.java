package org.project.msgame.application.ports.in.cammo.query;

import org.project.msgame.application.dtos.cammo.CammoDetailsDto;

import java.util.UUID;

public interface GetCammoByIdUseCase {
    CammoDetailsDto getById(UUID id);
}
