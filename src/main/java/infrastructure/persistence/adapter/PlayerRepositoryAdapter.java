package infrastructure.persistence.adapter;


import application.ports.out.PlayerRepositoryPort;
import domain.aggregates.Player;
import infrastructure.persistence.mapper.PlayerEntityMapper;
import infrastructure.persistence.repository.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlayerRepositoryAdapter implements PlayerRepositoryPort {

    private final PlayerJpaRepository playerJpaRepository;
    private final PlayerEntityMapper playerEntityMapper;

    @Override
    public Player save(Player player) {
        return playerEntityMapper.toDomain(playerJpaRepository.save(playerEntityMapper.toEntity(player)));
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return playerJpaRepository.findById(id).map(playerEntityMapper::toDomain);
    }

    @Override
    public List<Player> findAll() {
        return playerJpaRepository.findAll().stream().map(playerEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        playerJpaRepository.deleteById(id);
    }

    @Override
    public List<Player> search(Specification<Player> spec) {
        return playerJpaRepository.findAll((Sort) spec).stream().map(playerEntityMapper::toDomain).toList();
    }
}
