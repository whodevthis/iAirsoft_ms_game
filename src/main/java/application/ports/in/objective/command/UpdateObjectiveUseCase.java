package application.ports.in.objective.command;

import application.dtos.objective.ObjectiveDetailsDto;

import java.util.UUID;

public interface UpdateObjectiveUseCase {
    UUID update(ObjectiveDetailsDto objectiveDetailsDto);
}