package org.project.msgame.application.ports.out;

import org.project.msgame.domain.aggregates.Location;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationRepositoryPort {
    Location save(Location location);
    void deleteById(UUID id);
    Optional <Location> findById(UUID id);
    List<Location> findAll();
    List<Location> search(Specification<Location> search);
}