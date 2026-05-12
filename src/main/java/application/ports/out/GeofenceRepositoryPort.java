package application.ports.out;

import domain.aggregates.Geofence;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeofenceRepositoryPort {
    Geofence save(Geofence geofence);
    void deleteById(UUID id);

    Optional <Geofence> findById(UUID id);
    List<Geofence> findAll();
    <T> List<Geofence> search(Specification<T> search);
}