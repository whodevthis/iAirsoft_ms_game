package org.project.msgame.infrastructure.persistence.adapter;

import org.project.msgame.application.ports.out.CammoRepositoryPort;
import org.project.msgame.domain.aggregates.Cammo;
import org.project.msgame.infrastructure.persistence.mapper.CammoEntityMapper;
import org.project.msgame.infrastructure.persistence.repository.CammoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CammoRepositoryAdapter implements CammoRepositoryPort {

    private final CammoJpaRepository cammoJpaRepository;
    private final CammoEntityMapper cammoEntityMapper;

    @Override
    public Cammo save(Cammo cammo) {
        return cammoEntityMapper.toDomain(cammoJpaRepository.save(cammoEntityMapper.toEntity(cammo)));
    }

    @Override
    public Optional<Cammo> findById(UUID id) {
        return cammoJpaRepository.findById(id).map(cammoEntityMapper::toDomain);
    }

    @Override
    public List<Cammo> findAll() {
        return cammoJpaRepository.findAll().stream().map(cammoEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        cammoJpaRepository.deleteById(id);
    }

    @Override
    public List<Cammo> search(Specification<Cammo> spec) {
        return cammoJpaRepository.findAll((Sort) spec).stream().map(cammoEntityMapper::toDomain).toList();
    }
}