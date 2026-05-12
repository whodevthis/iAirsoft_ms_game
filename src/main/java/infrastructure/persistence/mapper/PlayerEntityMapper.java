package infrastructure.persistence.mapper;

import domain.aggregates.Player;
import infrastructure.persistence.entities.PlayerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerEntityMapper {
    PlayerEntity toEntity(Player player);
    Player toDomain(PlayerEntity entity);
}