package Domain.aggregates.domainService;

import Domain.aggregates.Game;
import Domain.aggregates.Team;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

public class GameDomainService {

    public int getTotalPlayers(Game game, List<Team> teams) {
        assertTeamsMatchGame(game, teams);
        return teams.stream()
                .mapToInt(t -> t.getPlayers().size())
                .sum();
    }


    public boolean canStart(Game game, List<Team> teams) {
        assertTeamsMatchGame(game, teams);
        int totalPlayers = getTotalPlayers(game, teams);
        return !game.isRecruitmentOpen()
                && totalPlayers > 0;
    }

    private void assertTeamsMatchGame(Game game, List<Team> teams) {
        Assert.notNull(game,  "game is null");
        Assert.notNull(teams, "teams is null");

        List<UUID> providedIds = teams.stream()
                .map(Team::getId)
                .toList();

        Assert.isTrue(
                providedIds.containsAll(game.getTeamIds()),
                "Provided teams do not match the teams registered in this game"
        );
    }
}