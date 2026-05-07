package domain.aggregates;

import domain.states.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
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
    private List<UUID> teamIds;
    private int bleedingTimeSeconds;
    private int healingTimeSeconds;
    private ZonedDateTime recruitingTimeEnd;
    private UUID locationId;
    private GameStatus status;
}