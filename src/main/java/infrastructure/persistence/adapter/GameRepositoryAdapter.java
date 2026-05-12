package infrastructure.persistence.adapter;

import application.ports.out.GameRepositoryPort;
import domain.aggregates.Game;
import infrastructure.persistence.entities.GameEntity;
import infrastructure.persistence.mapper.GameEntityMapper;
import infrastructure.persistence.repository.GameJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GameRepositoryAdapter implements GameRepositoryPort {

    private final GameJpaRepository gameJpaRepository;
    private final GameEntityMapper gameEntityMapper;

    @Override
    public Game save(Game game) {
        GameEntity entity = gameEntityMapper.toEntity(game);
        return gameEntityMapper.toDomain(gameJpaRepository.save(entity));
    }

    @Override
    public Optional<Game> findById(UUID id) {
        return gameJpaRepository.findById(id)
                .map(gameEntityMapper::toDomain);
    }

    @Override
    public List<Game> findAll() {
        return gameJpaRepository.findAll().stream()
                .map(gameEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        gameJpaRepository.deleteById(id);
    }

    @Override
    public List<Game> search(Specification<Game> spec) {
        return gameJpaRepository.findAll((Sort) spec)
                .stream()
                .map(gameEntityMapper::toDomain)
                .toList();
    }
}