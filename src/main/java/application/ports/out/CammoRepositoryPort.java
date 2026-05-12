package application.ports.out;

import domain.aggregates.Cammo;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CammoRepositoryPort {
    Cammo save(Cammo cammo);
    void deleteById(UUID id);

    Optional <Cammo> findById(UUID id);
    List<Cammo> findAll();
    List<Cammo> search(Specification<Cammo> search);
}