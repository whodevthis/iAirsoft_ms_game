package application.mappers;

import application.dtos.player.PlayerDetailsDto;
import application.dtos.player.PlayerDto;
import application.dtos.player.InputPlayerDto;
import domain.aggregates.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamIds", ignore = true)
    @Mapping(target = "status", constant = "DRAFT")
    Player toDomain(InputPlayerDto dto);

    @Mapping(target = "imagePath", ignore = true)
    PlayerDto toDTO(Player domain);

    @Mapping(target = "imagePath", ignore = true)
    PlayerDetailsDto toDetailsDTO(Player domain);
}