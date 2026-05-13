package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.player.PlayerDetailsDto;
import org.project.msgame.application.dtos.player.PlayerDto;
import org.project.msgame.domain.aggregates.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {


    PlayerDto toDTO(Player player);
    PlayerDetailsDto toDetailsDTO(Player player);
}
