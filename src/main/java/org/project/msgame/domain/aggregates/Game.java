package org.project.msgame.domain.aggregates;

import org.project.msgame.domain.states.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private UUID id;
    private String name;
    private String description;
    private String imagePath;
    private ZonedDateTime initGame;
    private ZonedDateTime endGame;
    private int maxPlayers;
    private List<UUID> teamIds = new ArrayList<>();
    private int bleedingTimeSeconds;
    private int healingTimeSeconds;
    private ZonedDateTime recruitingTimeEnd;
    private GameStatus status;
    private UUID locationId;

}