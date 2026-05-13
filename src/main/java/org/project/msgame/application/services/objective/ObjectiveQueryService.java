package org.project.msgame.application.services.objective;

import org.project.msgame.application.dtos.objective.ObjectiveDetailsDto;
import org.project.msgame.application.dtos.objective.ObjectiveDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.mappers.ObjectiveMapper;
import org.project.msgame.application.ports.in.objective.query.GetAllObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.query.GetObjectiveByIdUseCase;
import org.project.msgame.application.ports.in.objective.query.SearchObjectiveUseCase;
import org.project.msgame.application.ports.out.ObjectiveRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Objective;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObjectiveQueryService implements GetAllObjectiveUseCase, GetObjectiveByIdUseCase, SearchObjectiveUseCase {

    private final ObjectiveMapper objectiveMapper;
    private final ObjectiveRepositoryPort objectiveRepositoryPort;

    @Override
    public List<ObjectiveDto> getAll() {
        return objectiveRepositoryPort.findAll().stream().map(objectiveMapper::toDTO).toList();
    }

    @Override
    public ObjectiveDetailsDto getById(UUID id) {
        return objectiveMapper.toDetailsDTO(objectiveRepositoryPort.findById(id).orElseThrow(()-> new EntityNotFoundException("Objective not found")));
    }

    @Override
    public List<ObjectiveDto> search(String data) {
        List<Objective> objectives = objectiveRepositoryPort.search(GenericUtils.search(data, Objective.class));

        if (objectives.isEmpty()) throw new EntityNotFoundException("No objectives found for: " + data);

        return objectives.stream().map(objectiveMapper::toDTO).toList();
    }
}
