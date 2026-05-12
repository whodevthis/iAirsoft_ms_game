package infrastructure.persistence.mapper;

import domain.aggregates.Game;
import infrastructure.persistence.entities.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameEntityMapper {
    @Mapping(target = "teamIds", ignore = true)
    GameEntity toEntity(Game game);
    Game toDomain(GameEntity entity);
}