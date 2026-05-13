// GameMapper.java
package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.game.GameDetailsDto;
import org.project.msgame.application.dtos.game.GameDto;
import org.project.msgame.application.dtos.game.InputGameDto;
import org.project.msgame.domain.aggregates.Game;
import org.project.msgame.domain.states.GameStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    default  Game toDomain(InputGameDto dto){
        if (dto == null) return null;
        return new Game(
               null,
                dto.name(),
                dto.description(),
                dto.imagePath(),
                dto.initGame(),
                dto.endGame(),
                dto.maxPlayers(),
               null,
                dto.bleedingTimeSeconds(),
                dto.healingTimeSeconds(),
                dto.recruitingTimeEnd(),
                GameStatus.CREATED,
                dto.locationId()
        );
    }



    default Game toDomain(GameDetailsDto dto) {
        if (dto == null) return null;
        return new Game(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.imagePath(),
                dto.initGame(),
                dto.endGame(),
                dto.maxPlayers(),
                dto.teamIds(),
                dto.bleedingTimeSeconds(),
                dto.healingTimeSeconds(),
                dto.recruitingTimeEnd(),
                dto.status(),
                dto.locationId()
        );
    }

    GameDto toDTO(Game game);
    GameDetailsDto toDetailsDTO(Game game);
}