package application.services.objective;

import application.dtos.objective.ObjectiveDetailsDto;
import application.dtos.objective.ObjectiveDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.ObjectiveMapper;
import application.ports.in.objective.query.GetAllObjectiveUseCase;
import application.ports.in.objective.query.GetObjectiveByIdUseCase;
import application.ports.in.objective.query.SearchObjectiveUseCase;
import application.ports.out.ObjectiveRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Objective;
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
