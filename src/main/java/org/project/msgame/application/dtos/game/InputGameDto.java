package org.project.msgame.application.dtos.game;

import java.time.ZonedDateTime;
import java.util.UUID;

public record InputGameDto(String name, String description, int maxPlayers, ZonedDateTime initGame,String imagePath,
                           ZonedDateTime endGame, int bleedingTimeSeconds, int healingTimeSeconds,   ZonedDateTime recruitingTimeEnd, UUID locationId) {
}
