package org.project.msgame.infrastructure.persistence.mapper;

import org.project.msgame.domain.aggregates.Game;
import org.project.msgame.infrastructure.persistence.entities.GameEntity;
import org.project.msgame.infrastructure.persistence.entities.TeamEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface GameEntityMapper {

    GameEntity toEntity(Game game);

    default Game toDomain(GameEntity entity) {
        if (entity == null) return null;
        return new Game(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImagePath(),
                entity.getInitGame(),
                entity.getEndGame(),
                entity.getMaxPlayers(),
                entity.getTeams() != null
                        ? entity.getTeams().stream().map(TeamEntity::getId).toList()
                        : new ArrayList<>(),
                entity.getBleedingTimeSeconds(),
                entity.getHealingTimeSeconds(),
                entity.getRecruitingTimeEnd(),
                entity.getStatus(),
                entity.getLocation() != null ? entity.getLocation().getId() : null
        );
    }
}