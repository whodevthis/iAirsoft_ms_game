package application.mappers;

import application.dtos.game.GameDto;
import application.dtos.game.InputGameDto;
import application.dtos.game.GameDetailsDto;
import domain.aggregates.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamIds", ignore = true)
    @Mapping(target = "status", constant = "DRAFT")
    Game toDomain(InputGameDto dto);

    @Mapping(target = "imagePath", ignore = true)
    GameDto toDTO(Game domain);

    @Mapping(target = "imagePath", ignore = true)
    GameDetailsDto toDetailsDTO(Game domain);
}