package Application.dtos.game;

import Domain.states.GameStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public record GameDetailsDto(UUID id, String name, String description, String imagePath, ZonedDateTime initGame,
                             ZonedDateTime endGame, int maxPlayers, List<UUID> teamIds, int bleedingTimeSeconds,
                             int healingTimeSeconds, ZonedDateTime recruitingTimeEnd, UUID locationId,
                             GameStatus status) {
}
