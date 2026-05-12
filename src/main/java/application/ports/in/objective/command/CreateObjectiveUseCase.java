package application.ports.in.objective.command;

import application.dtos.objective.InputObjectiveDto;

import java.util.UUID;

public interface CreateObjectiveUseCase {
    UUID create(InputObjectiveDto input);

}
