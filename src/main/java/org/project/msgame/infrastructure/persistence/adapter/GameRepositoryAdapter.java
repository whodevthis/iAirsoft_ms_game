package org.project.msgame.infrastructure.persistence.adapter;

import org.project.msgame.application.ports.out.GameRepositoryPort;
import org.project.msgame.domain.aggregates.Game;
import org.project.msgame.infrastructure.persistence.assemblers.GameAssembler;
import org.project.msgame.infrastructure.persistence.entities.GameEntity;
import org.project.msgame.infrastructure.persistence.mapper.GameEntityMapper;
import org.project.msgame.infrastructure.persistence.repository.GameJpaRepository;
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
    private final GameAssembler gameAssembler;

    @Override
    public Game save(Game game) {
        GameEntity entity = gameEntityMapper.toEntity(game);
        GameEntity gameAssembled = gameAssembler.assembler(entity, game);
        return gameEntityMapper.toDomain(gameJpaRepository.saveAndFlush(gameAssembled));
    }

    @Override
    public Optional<Game> findById(UUID id) {
        return gameJpaRepository.findById(id).map(gameEntityMapper::toDomain);
    }

    @Override
    public List<Game> findAll() {
        return gameJpaRepository.findAll().stream().map(gameEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        gameJpaRepository.deleteById(id);
    }

    @Override
    public List<Game> search(Specification<Game> spec) {
        return gameJpaRepository.findAll((Sort) spec).stream().map(gameEntityMapper::toDomain).toList();
    }
}