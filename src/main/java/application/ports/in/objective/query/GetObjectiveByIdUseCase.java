package application.ports.in.objective.query;

import application.dtos.objective.ObjectiveDetailsDto;

import java.util.UUID;

public interface GetObjectiveByIdUseCase {
    ObjectiveDetailsDto getById(UUID id);
}
