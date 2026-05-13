package org.project.msgame.infrastructure.persistence.adapter;

import org.project.msgame.application.ports.out.LocationRepositoryPort;
import org.project.msgame.domain.aggregates.Location;
import org.project.msgame.infrastructure.persistence.mapper.LocationEntityMapper;
import org.project.msgame.infrastructure.persistence.repository.LocationJpaRepository;
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