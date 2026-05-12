package application.ports.out;

import domain.aggregates.Game;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface GameRepositoryPort {
    Game save(Game game);
    void deleteById(UUID id);

    Game findById(UUID id);
    List<Game> findAll();
    <T> List<Game> search(Specification<T> search);
}