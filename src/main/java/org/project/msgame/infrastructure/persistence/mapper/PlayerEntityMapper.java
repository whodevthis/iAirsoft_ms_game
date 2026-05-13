package org.project.msgame.infrastructure.persistence.mapper;

import org.project.msgame.domain.aggregates.Player;
import org.project.msgame.infrastructure.persistence.entities.PlayerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerEntityMapper {
    PlayerEntity toEntity(Player player);
    Player toDomain(PlayerEntity entity);
}