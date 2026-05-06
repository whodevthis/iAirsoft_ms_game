package Domain.aggregates;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Team {

    private UUID id;
    private UUID gameId;
    private String name;
    private FlagTeam flagTeam;
    private UUID cammoId;
    private List<TeamRole> roles;
    private List<Objective> objectives;
    private Respawn respawn;

    public Team(UUID cammoId, FlagTeam flagTeam, UUID gameId, UUID id, String name, List<Objective> objectives, Respawn respawn, List<TeamRole> roles) {

        Assert.notNull(cammoId, "cammoId is null");
        Assert.notNull(flagTeam, "flagTeam is null");
        Assert.notNull(gameId, "gameId is null");
        Assert.notNull(name, "name is null");
        Assert.notNull(respawn, "respawn is null");
        Assert.notEmpty(objectives, "objectives is empty");
        Assert.notEmpty(roles, "roles is empty");

        this.cammoId = cammoId;
        this.flagTeam = flagTeam;
        this.gameId = gameId;
        this.id = id;
        this.name = name;
        this.respawn = respawn;
        this.objectives = List.copyOf(objectives);
        this.roles = List.copyOf(roles);
    }


    public void addTeamRole(TeamRole teamRole) {
        Assert.notNull(teamRole, "teamRole is null");
        // FIX: usaba 'objective' en lugar de 'teamRole'
        Assert.isTrue(roles.stream().noneMatch(teamRole::equals), "teamRole already belongs to this team");

        List<TeamRole> updated = new ArrayList<>(this.roles);
        updated.add(teamRole);
        this.roles = List.copyOf(updated); // reemplaza con nueva lista inmutable
    }

    public void removeTeamRole(TeamRole teamRole) {
        Assert.notNull(teamRole, "teamRole is null");

        Assert.isTrue(roles.stream().anyMatch(teamRole::equals), "teamRole does not belong to this team");
        Assert.isTrue(roles.size() > 1, "Team must keep at least one role");

        List<TeamRole> updated = new ArrayList<>(this.roles);
        updated.removeIf(teamRole::equals);
        this.roles = List.copyOf(updated);
    }

    public void addObjective(Objective objective) {
        Assert.notNull(objective, "objective is null");
        Assert.isTrue(objectives.stream().noneMatch(objective::equals), "objective already belongs to this team");

        List<Objective> updated = new ArrayList<>(this.objectives);
        updated.add(objective);
        this.objectives = List.copyOf(updated);
    }

    public void removeObjective(Objective objective) {
        Assert.notNull(objective, "objective is null");
        Assert.isTrue(objectives.stream().anyMatch(objective::equals), "objective does not belong to this team");
        Assert.isTrue(objectives.size() > 1, "Team must keep at least one objective");

        List<Objective> updated = new ArrayList<>(this.objectives);
        updated.removeIf(objective::equals);
        this.objectives = List.copyOf(updated);
    }
}