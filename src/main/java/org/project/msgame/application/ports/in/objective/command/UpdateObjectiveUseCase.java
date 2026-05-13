package org.project.msgame.application.ports.in.objective.command;

import org.project.msgame.application.dtos.objective.ObjectiveDetailsDto;

import java.util.UUID;

public interface UpdateObjectiveUseCase {
    UUID update(ObjectiveDetailsDto objectiveDetailsDto);
}