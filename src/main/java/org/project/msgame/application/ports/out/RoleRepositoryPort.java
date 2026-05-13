package org.project.msgame.application.ports.out;

import org.project.msgame.domain.aggregates.Role;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepositoryPort {
    Role save(Role role);
    void deleteById(UUID id);
    Optional <Role> findById(UUID id);
    List<Role> findAll();
    List<Role> search(Specification<Role> search);
}