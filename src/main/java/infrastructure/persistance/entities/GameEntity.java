package infrastructure.persistance.entities;


import domain.states.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    private UUID id;
    private String name;
    private String description;
    private String imagePath;
    private ZonedDateTime initGame;
    private ZonedDateTime endGame;
    private int maxPlayers;
    private List<TeamEntity> teams;
    private int bleedingTimeSeconds;
    private int healingTimeSeconds;
    private ZonedDateTime recruitingTimeEnd;
    private LocationEntity location;
    private GameStatus status;
}
