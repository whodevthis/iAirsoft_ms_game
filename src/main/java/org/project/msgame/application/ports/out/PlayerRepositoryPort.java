package org.project.msgame.application.ports.out;

import org.project.msgame.domain.aggregates.Player;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepositoryPort {
    Player save(Player player);
    void deleteById(UUID id);

    Optional <Player> findById(UUID id);
    List<Player> findAll();

    List<Player> search(Specification<Player> search);
}