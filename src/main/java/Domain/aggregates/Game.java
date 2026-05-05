package Domain.aggregates;

import Domain.states.GameStatus;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class Game {

    private  UUID id;
    private String name;
    private ZonedDateTime initGame;
    private ZonedDateTime endGame;
    private String description;
    private List<UUID> teamIds;
    private UUID locationId;
    private int bleedingTimeSeconds;
    private int healingTimeSeconds;
    private GameStatus status;
    private int maxPlayers;
    private ZonedDateTime recruitingTimeEnd;

    public Game(int bleedingTimeSeconds, String description, ZonedDateTime endGame, int healingTimeSeconds, UUID id, ZonedDateTime initGame, UUID locationId, String name, List<UUID> teamIds, int maxPlayers, ZonedDateTime recruitingTimeEnd) {


        Assert.notNull(name, "Game name is null");
        Assert.hasText(name, "Game name is blank");

        Assert.notNull(initGame, "initGame is null");
        Assert.notNull(endGame, "endGame is null");
        Assert.isTrue(initGame.isBefore(endGame), "initGame must be before endGame");
        Assert.notNull(description, "Game description is null");
        Assert.hasText(description, "Game description is blank");
        Assert.notNull(locationId, "locationId is null");
        Assert.notEmpty(teamIds, "Game must have at least one team");
        Assert.notNull(recruitingTimeEnd, "recruitingTimeEnd is null");
        Assert.isTrue(recruitingTimeEnd.isBefore(endGame), "recruiting must end before game ends");
        Assert.isTrue(recruitingTimeEnd.isAfter(initGame), "recruiting must end after game starts");
        Assert.isTrue(maxPlayers > 0, "maxPlayers must be > 0");
        Assert.isTrue(bleedingTimeSeconds >= 0, "bleedingTimeSeconds must be >= 0");
        Assert.isTrue(healingTimeSeconds >= 0, "healingTimeSeconds must be >= 0");

        this.id = id;
        this.name = name;
        this.initGame = initGame;
        this.endGame = endGame;
        this.description = description;
        this.locationId = locationId;
        this.bleedingTimeSeconds = bleedingTimeSeconds;
        this.healingTimeSeconds = healingTimeSeconds;
        this.teamIds = new ArrayList<>(teamIds);
        this.maxPlayers = maxPlayers;
        this.recruitingTimeEnd = recruitingTimeEnd;
        this.status = GameStatus.CREATED;
    }


    public boolean isRecruitmentOpen() {
        return status == GameStatus.CREATED && ZonedDateTime.now().isBefore(recruitingTimeEnd);
    }

      public boolean hasCapacity(int currentPlayerCount) {
        Assert.isTrue(currentPlayerCount >= 0, "playerCount must be >= 0");
        return currentPlayerCount < maxPlayers;
    }

    public void addTeam(UUID teamId) {
        Assert.notNull(teamId, "teamId is null");
        Assert.state(status == GameStatus.CREATED, "Cannot add teams after game has started");

        Assert.isTrue(teamIds.stream().noneMatch(teamId::equals), "Team already belongs to this game");
        teamIds.add(teamId);
    }

    public void removeTeam(UUID teamId) {
        Assert.notNull(teamId, "teamId is null");
        Assert.state(status == GameStatus.CREATED, "Cannot remove teams after game has started");
        Assert.isTrue(teamIds.stream().anyMatch(teamId::equals), "Team does not belong to this game");
        Assert.isTrue(teamIds.size() > 1, "Game must keep at least one team");
        teamIds.removeIf(teamId::equals);
    }

    public boolean hasTeam(UUID teamId) {
        Assert.notNull(teamId, "teamId is null");
        return teamIds.stream().anyMatch(teamId::equals);
    }

    public void startGame() {
        Assert.state(status == GameStatus.CREATED, "Game can only start from CREATED state");
        Assert.state(!isRecruitmentOpen(), "Cannot start while recruitment is open");
        this.status = GameStatus.IN_PROGRESS;
    }

    public void finishGame() {
        Assert.state(status == GameStatus.IN_PROGRESS, "Game can only finish from IN_PROGRESS state");
        this.status = GameStatus.FINISHED;
    }

    public void cancelGame() {
        Assert.state(status != GameStatus.FINISHED, "Cannot cancel a finished game");
        this.status = GameStatus.CANCELLED;
    }

    public List<UUID> getTeamIds() {
        return Collections.unmodifiableList(teamIds);
    }
}