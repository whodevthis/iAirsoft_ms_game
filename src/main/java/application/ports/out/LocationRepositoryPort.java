package application.ports.out;

import domain.aggregates.Location;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationRepositoryPort {
    Location save(Location location);
    void deleteById(UUID id);
    Optional <Location> findById(UUID id);
    List<Location> findAll();
    <T> List<Location> search(Specification<T> search);
}