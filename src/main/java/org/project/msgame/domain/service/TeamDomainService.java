package org.project.msgame.domain.service;

import org.project.msgame.domain.aggregates.Team;
import org.project.msgame.domain.valueObjects.PlayerObjective;
import org.project.msgame.domain.valueObjects.PlayerRole;
import org.project.msgame.domain.valueObjects.TeamRole;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamDomainService {

    public void addTeamRole(Team team, UUID roleId, int quantity) {
        if (team == null)    throw new IllegalArgumentException("team must not be null");
        if (roleId == null)  throw new IllegalArgumentException("roleId must not be null");
        if (quantity <= 0)   throw new IllegalArgumentException("quantity must be > 0");
        team.getRoles().add(new TeamRole(roleId, quantity));
    }

    public void removeTeamRole(Team team, UUID roleId) {
        if (team == null)   throw new IllegalArgumentException("team must not be null");
        if (roleId == null) throw new IllegalArgumentException("roleId must not be null");
        boolean removed = team.getRoles().removeIf(role -> role.role().equals(roleId));
        if (!removed) throw new IllegalArgumentException("Role not found in team");
    }

    public void addPlayerOnTeam(Team team, UUID playerId, UUID roleId) {
        if (team == null)     throw new IllegalArgumentException("team must not be null");
        if (playerId == null) throw new IllegalArgumentException("playerId must not be null");
        if (roleId == null)   throw new IllegalArgumentException("roleId must not be null");
        if (!fitTeam(team))   throw new IllegalArgumentException("El equipo está lleno");
        if (!fitPlayer(team, roleId)) throw new IllegalArgumentException("No hay hueco para ese rol");
        team.getPlayers().add(new PlayerRole(playerId, roleId));
    }

    public void removePlayerOnTeam(Team team, UUID playerId) {
        if (team == null)     throw new IllegalArgumentException("team must not be null");
        if (playerId == null) throw new IllegalArgumentException("playerId must not be null");
        boolean removed = team.getPlayers().removeIf(p -> p.playerId().equals(playerId));
        if (!removed) throw new IllegalArgumentException("Player not found in team");
    }

    public void addObjective(Team team, UUID objectiveId) {
        if (team == null)        throw new IllegalArgumentException("team must not be null");
        if (objectiveId == null) throw new IllegalArgumentException("objectiveId must not be null");
        if (team.getObjectives().contains(objectiveId))
            throw new IllegalArgumentException("Objective already assigned to team");
        team.getObjectives().add(objectiveId);
    }

    public void removeObjective(Team team, UUID objectiveId) {
        if (team == null)        throw new IllegalArgumentException("team must not be null");
        if (objectiveId == null) throw new IllegalArgumentException("objectiveId must not be null");
        boolean removed = team.getObjectives().remove(objectiveId);
        if (!removed) throw new IllegalArgumentException("Objective not found in team");
    }

    public void addPlayerObjective(Team team, UUID roleId, UUID objectiveId) {
        if (team == null)        throw new IllegalArgumentException("team must not be null");
        if (roleId == null)      throw new IllegalArgumentException("roleId must not be null");
        if (objectiveId == null) throw new IllegalArgumentException("objectiveId must not be null");
        if (!team.getObjectives().contains(objectiveId))
            throw new IllegalArgumentException("Objective not found in team");
        boolean roleExists = team.getRoles().stream()
                .anyMatch(r -> r.role().equals(roleId));
        if (!roleExists)
            throw new IllegalArgumentException("Role not found in team");
        PlayerObjective playerObjective = new PlayerObjective(new PlayerRole(null, roleId), objectiveId);
        if (team.getPlayerObjectives().contains(playerObjective))
            throw new IllegalArgumentException("Objective already assigned to player");
        team.getPlayerObjectives().add(playerObjective);
    }

    public void removePlayerObjective(Team team, UUID roleId, UUID objectiveId) {
        if (team == null)        throw new IllegalArgumentException("team must not be null");
        if (roleId == null)      throw new IllegalArgumentException("roleId must not be null");
        if (objectiveId == null) throw new IllegalArgumentException("objectiveId must not be null");
        boolean removed = team.getPlayerObjectives().removeIf(po ->
                po.playerRole().roleId().equals(roleId) &&
                        po.objectiveId().equals(objectiveId)
        );
        if (!removed) throw new IllegalArgumentException("Objective not found for that player in team");
    }

    private boolean fitPlayer(Team team, UUID roleId) {
        long assignedCount = team.getPlayers().stream()
                .filter(p -> p.roleId().equals(roleId))
                .count();
        int maxForRole = team.getRoles().stream()
                .filter(r -> r.role().equals(roleId))
                .map(TeamRole::quantity)
                .findFirst()
                .orElse(0);
        return assignedCount < maxForRole;
    }

    private boolean fitTeam(Team team) {
        int maxPlayers = team.getRoles().stream()
                .mapToInt(TeamRole::quantity)
                .sum();
        return team.getPlayers().size() < maxPlayers;
    }
}