package org.project.msgame.application.ports.out;

import org.project.msgame.domain.aggregates.Game;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepositoryPort {
    Game save(Game game);
    void deleteById(UUID id);

    Optional <Game> findById(UUID id);
    List<Game> findAll();
    List<Game> search(Specification<Game> search);
}