package application.ports.out;

import domain.aggregates.Player;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepositoryPort {
    Player save(Player player);
    void deleteById(UUID id);

    Optional <Player> findById(UUID id);
    List<Player> findAll();
    <T> List<Player> search(Specification<T> search);
}