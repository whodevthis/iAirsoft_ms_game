package Domain;


import lombok.Getter;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Game {
    UUID id;
    String name;
    ZonedDateTime initGame;
    ZonedDateTime endGame;
    String description;

    List<UUID> teamIds;
    List<UUID> playerIds;
    UUID locationId;

    int bleedingTimeSeconds;
    int healingTimeSeconds;

    public Game(int bleedingTimeSeconds, String description, ZonedDateTime endGame, int healingTimeSeconds, UUID id, ZonedDateTime initGame, UUID locationId, String name, List<UUID> playerIds, List<UUID> teamIds) {

        Assert.notNull(name, "Game name is null");
        Assert.notNull(initGame, "Game initGame is null");
        Assert.notNull(endGame, "Game endGame is null");
        Assert.notNull(description, "Game description is null");

        Assert.notEmpty(teamIds, "Game teamIds is empty");
        Assert.notEmpty(playerIds, "Game playerIds is empty");

        this.bleedingTimeSeconds = bleedingTimeSeconds;
        this.description = description;
        this.endGame = endGame;
        this.healingTimeSeconds = healingTimeSeconds;
        this.id = id;
        this.initGame = initGame;
        this.locationId = locationId;
        this.name = name;
        this.playerIds = playerIds;
        this.teamIds = teamIds;
    }


    public void addPlayer(UUID playerId) {
        Assert.notNull(playerId, "PlayerId is null");
        Assert.isTrue(!playerIds.contains(playerId), "Player already exists in game");
        playerIds.add(playerId);
    }

    public void removePlayer(UUID playerId) {
        Assert.notNull(playerId, "PlayerId is null");
        Assert.isTrue(playerIds.contains(playerId), "Player does not exist in game");
        Assert.isTrue(playerIds.size() > 1, "Game must have at least one player");
        playerIds.remove(playerId);
    }

    public void addTeam(UUID teamId) {
        Assert.notNull(teamId, "TeamId is null");
        Assert.isTrue(!teamIds.contains(teamId), "Team already exists in game");
        teamIds.add(teamId);
    }

    public void removeTeam(UUID teamId) {
        Assert.notNull(teamId, "TeamId is null");
        Assert.isTrue(teamIds.contains(teamId), "Team does not exist in game");
        Assert.isTrue(teamIds.size() > 1, "Game must have at least one team");
        teamIds.remove(teamId);
    }



}
