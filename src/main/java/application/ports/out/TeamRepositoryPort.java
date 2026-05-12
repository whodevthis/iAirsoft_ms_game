package application.ports.out;

import domain.aggregates.Team;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepositoryPort {
    Team save(Team team);
    void deleteById(UUID id);

    Optional <Team> findById(UUID id);
    List<Team> findAll();
    <T> List<Team> search(Specification<T> search);
}