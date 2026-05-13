package org.project.msgame.application.ports.in.cammo.command;

import org.project.msgame.application.dtos.cammo.InputCammoDto;

import java.util.UUID;

public interface CreateCammoUseCase {
    UUID create(InputCammoDto input);

}
