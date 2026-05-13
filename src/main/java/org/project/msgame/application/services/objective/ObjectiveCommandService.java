package org.project.msgame.application.services.objective;

import org.project.msgame.application.dtos.objective.InputObjectiveDto;
import org.project.msgame.application.dtos.objective.ObjectiveDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.ports.in.objective.command.CreateObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.command.DeleteObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.command.UpdateObjectiveUseCase;
import org.project.msgame.application.ports.out.ObjectiveRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Objective;
import org.project.msgame.domain.service.ObjectiveDomainService;
import org.project.msgame.domain.states.ObjectiveState;
import org.project.msgame.domain.valueObjects.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObjectiveCommandService implements CreateObjectiveUseCase, DeleteObjectiveUseCase, UpdateObjectiveUseCase {

    private final GenericUtils genericUtils;
    private final ObjectiveRepositoryPort objectiveRepositoryPort;
    private final ObjectiveDomainService objectiveDomainService;

    @Transactional
    @Override
    public UUID create(InputObjectiveDto inputObjectiveDto) {
        Marker marker = new Marker(inputObjectiveDto.lat(), inputObjectiveDto.lon());
        Objective objective = new Objective(null, inputObjectiveDto.name(), inputObjectiveDto.description(), inputObjectiveDto.image(), inputObjectiveDto.code(), marker, false, null, ObjectiveState.CREATED);
        return objectiveRepositoryPort.save(objective).getId();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        objectiveRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Objective not found: " + id));
        objectiveRepositoryPort.deleteById(id);
    }

    @Transactional
    @Override
    public UUID update(ObjectiveDetailsDto objectiveDetailsDto) {
        Objective oldObjective = objectiveRepositoryPort.findById(objectiveDetailsDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Objective not found"));

        Objective updatedObjective = new Objective(
                oldObjective.getId(),
                genericUtils.applyIfChanged(oldObjective.getName(), objectiveDetailsDto.name()),
                genericUtils.applyIfChanged(oldObjective.getDescription(), objectiveDetailsDto.description()),
                genericUtils.applyIfChanged(oldObjective.getImage(), objectiveDetailsDto.image()),
                genericUtils.applyIfChanged(oldObjective.getCode(), objectiveDetailsDto.code()),
                genericUtils.applyIfChanged(oldObjective.getMarker(), objectiveDetailsDto.marker()),
                genericUtils.applyIfChanged(oldObjective.isCompleted(), objectiveDetailsDto.completed()),
                genericUtils.applyIfChanged(oldObjective.getCompletedBy(), objectiveDetailsDto.completedBy()),
                objectiveDomainService.handleStateTransition(oldObjective.getState(),objectiveDetailsDto.state())
        );
        return objectiveRepositoryPort.save(updatedObjective).getId();
    }



}
