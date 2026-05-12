package infrastructure.persistence.adapter;

import application.ports.out.LocationRepositoryPort;
import domain.aggregates.Location;
import infrastructure.persistence.mapper.LocationEntityMapper;
import infrastructure.persistence.repository.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocationRepositoryAdapter implements LocationRepositoryPort {

    private final LocationJpaRepository locationJpaRepository;
    private final LocationEntityMapper locationEntityMapper;

    @Override
    public Location save(Location location) {
        return locationEntityMapper.toDomain(locationJpaRepository.save(locationEntityMapper.toEntity(location)));
    }

    @Override
    public Optional<Location> findById(UUID id) {
        return locationJpaRepository.findById(id).map(locationEntityMapper::toDomain);
    }

    @Override
    public List<Location> findAll() {
        return locationJpaRepository.findAll().stream().map(locationEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        locationJpaRepository.deleteById(id);
    }

    @Override
    public List<Location> search(Specification<Location> spec) {
        return locationJpaRepository.findAll((Sort) spec).stream().map(locationEntityMapper::toDomain).toList();
    }
}