package infrastructure.persistence.adapter;

import application.ports.out.ObjectiveRepositoryPort;
import domain.aggregates.Objective;
import infrastructure.persistence.mapper.ObjectiveEntityMapper;
import infrastructure.persistence.repository.ObjectiveJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ObjectiveRepositoryAdapter implements ObjectiveRepositoryPort {

    private final ObjectiveJpaRepository objectiveJpaRepository;
    private final ObjectiveEntityMapper objectiveEntityMapper;

    @Override
    public Objective save(Objective objective) {
        return objectiveEntityMapper.toDomain(objectiveJpaRepository.save(objectiveEntityMapper.toEntity(objective)));
    }

    @Override
    public Optional<Objective> findById(UUID id) {
        return objectiveJpaRepository.findById(id).map(objectiveEntityMapper::toDomain);
    }

    @Override
    public List<Objective> findAll() {
        return objectiveJpaRepository.findAll().stream().map(objectiveEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        objectiveJpaRepository.deleteById(id);
    }

    @Override
    public List<Objective> search(Specification<Objective> spec) {
        return objectiveJpaRepository.findAll((Sort) spec).stream().map(objectiveEntityMapper::toDomain).toList();
    }
}