package org.project.msgame.infrastructure.persistence.adapter;

import org.project.msgame.application.ports.out.RoleRepositoryPort;
import org.project.msgame.domain.aggregates.Role;
import org.project.msgame.infrastructure.persistence.mapper.RoleEntityMapper;
import org.project.msgame.infrastructure.persistence.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role save(Role role) {
        return roleEntityMapper.toDomain(roleJpaRepository.save(roleEntityMapper.toEntity(role)));
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleJpaRepository.findById(id).map(roleEntityMapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return roleJpaRepository.findAll().stream().map(roleEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        roleJpaRepository.deleteById(id);
    }

    @Override
    public List<Role> search(Specification<Role> spec) {
        return roleJpaRepository.findAll((Sort) spec).stream().map(roleEntityMapper::toDomain).toList();
    }
}