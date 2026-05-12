package application.ports.out;

import domain.aggregates.Role;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepositoryPort {
    Role save(Role role);
    void deleteById(UUID id);
    Optional <Role> findById(UUID id);
    List<Role> findAll();
    <T> List<Role> search(Specification<T> search);
}