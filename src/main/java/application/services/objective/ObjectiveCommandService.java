package application.services.objective;

import application.dtos.objective.InputObjectiveDto;
import application.dtos.objective.ObjectiveDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.ports.in.objective.command.CreateObjectiveUseCase;
import application.ports.in.objective.command.DeleteObjectiveUseCase;
import application.ports.in.objective.command.UpdateObjectiveUseCase;
import application.ports.out.ObjectiveRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Objective;
import domain.states.ObjectiveState;
import domain.valueObjects.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObjectiveCommandService implements CreateObjectiveUseCase, DeleteObjectiveUseCase, UpdateObjectiveUseCase {

    private final GenericUtils genericUtils;
    private final ObjectiveRepositoryPort objectiveRepositoryPort;

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
                handleObjectiveState(oldObjective.getState(),objectiveDetailsDto.state())

        );

        return objectiveRepositoryPort.save(updatedObjective).getId();
    }

    private ObjectiveState handleObjectiveState(ObjectiveState oldState, ObjectiveState newState) {
        switch (oldState) {
            case CREATED:
                if (newState == ObjectiveState.ON_COURSE)
                    return newState;
                throw new IllegalArgumentException("CREATED only can change to ON_COURSE");

            case ON_COURSE:
                if (newState == ObjectiveState.END || newState == ObjectiveState.CANCELLED)
                    return newState;
                throw new IllegalArgumentException("ON_COURSE only can change to END o CANCELLED");

            case END:
                throw new IllegalArgumentException("END is final state");

            case CANCELLED:
                throw new IllegalArgumentException("CANCELLED is final state");

            default:
                throw new IllegalArgumentException("Estado desconocido: " + oldState);
        }
    }

}
