package org.project.msgame.application.ports.in.objective.query;

import org.project.msgame.application.dtos.objective.ObjectiveDetailsDto;

import java.util.UUID;

public interface GetObjectiveByIdUseCase {
    ObjectiveDetailsDto getById(UUID id);
}
