package application.ports.out;

import domain.aggregates.Objective;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ObjectiveRepositoryPort {
    Objective save(Objective objective);
    void deleteById(UUID id);

    Optional <Objective> findById(UUID id);
    List<Objective> findAll();
     List<Objective> search(Specification<Objective> search);
}