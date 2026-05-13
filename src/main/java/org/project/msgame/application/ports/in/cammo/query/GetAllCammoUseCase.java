package org.project.msgame.application.ports.in.cammo.query;

import org.project.msgame.application.dtos.cammo.CammoDetailsDto;

import java.util.List;

public interface GetAllCammoUseCase {
    List<CammoDetailsDto> getAll();
}
