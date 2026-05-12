package application.ports.in.cammo.query;

import application.dtos.cammo.CammoDetailsDto;

import java.util.UUID;

public interface GetCammoByIdUseCase {
    CammoDetailsDto getById(UUID id);
}
