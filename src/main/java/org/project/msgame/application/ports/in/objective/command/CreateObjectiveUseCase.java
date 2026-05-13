package org.project.msgame.application.ports.in.objective.command;

import org.project.msgame.application.dtos.objective.InputObjectiveDto;

import java.util.UUID;

public interface CreateObjectiveUseCase {
    UUID create(InputObjectiveDto input);

}
