package infrastructure.persistence.adapter;

import application.ports.out.GeofenceRepositoryPort;
import domain.aggregates.Geofence;
import infrastructure.persistence.mapper.GeofenceEntityMapper;
import infrastructure.persistence.repository.GeofenceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GeofenceRepositoryAdapter implements GeofenceRepositoryPort {

    private final GeofenceJpaRepository geofenceJpaRepository;
    private final GeofenceEntityMapper geofenceEntityMapper;

    @Override
    public Geofence save(Geofence geofence) {
        return geofenceEntityMapper.toDomain(geofenceJpaRepository.save(geofenceEntityMapper.toEntity(geofence)));
    }

    @Override
    public Optional<Geofence> findById(UUID id) {
        return geofenceJpaRepository.findById(id).map(geofenceEntityMapper::toDomain);
    }

    @Override
    public List<Geofence> findAll() {
        return geofenceJpaRepository.findAll().stream().map(geofenceEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        geofenceJpaRepository.deleteById(id);
    }

    @Override
    public List<Geofence> search(Specification<Geofence> spec) {
        return geofenceJpaRepository.findAll((Sort) spec).stream().map(geofenceEntityMapper::toDomain).toList();
    }
}