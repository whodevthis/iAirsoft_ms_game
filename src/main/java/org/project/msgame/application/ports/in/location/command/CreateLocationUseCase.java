package org.project.msgame.application.ports.in.location.command;

import org.project.msgame.application.dtos.location.InputLocationDto;

import java.util.UUID;

public interface CreateLocationUseCase {
    UUID create(InputLocationDto input);

}
