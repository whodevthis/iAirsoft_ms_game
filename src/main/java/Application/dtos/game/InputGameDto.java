package Application.dtos.game;

import java.time.ZonedDateTime;

public record InputGameDto(String name, String description, int maxPlayers, ZonedDateTime initGame,
                           ZonedDateTime endGame, int bleedingTimeSeconds, int healingTimeSeconds) {
}
