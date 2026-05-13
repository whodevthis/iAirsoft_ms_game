package org.project.msgame.domain.aggregates;

import org.project.msgame.domain.valueObjects.Respawn;
import org.project.msgame.domain.valueObjects.PlayerObjective;
import org.project.msgame.domain.valueObjects.PlayerRole;
import org.project.msgame.domain.valueObjects.TeamRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor

public class Team {
    private UUID id;
    private String name;
    private String description;
    private String imagePath;
    private List<PlayerRole> players = new ArrayList<>();
    private List<TeamRole> roles = new ArrayList<>();
    private List<PlayerObjective> playerObjectives = new ArrayList<>();
    private List<UUID> objectives = new ArrayList<>();
    private UUID cammoId;
    private Respawn respawn;

    public Team(UUID id, String name, String description, String imagePath,
                UUID cammoId, Respawn respawn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.cammoId = cammoId;
        this.respawn = respawn;
    }

    public Team(UUID id, String name, String description, String imagePath, List<PlayerRole> players, List<TeamRole> roles, List<PlayerObjective> playerObjectives, List<UUID> objectives, UUID cammoId, Respawn respawn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.players = players;
        this.roles = roles;
        this.playerObjectives = playerObjectives;
        this.objectives = objectives;
        this.cammoId = cammoId;
        this.respawn = respawn;
    }
}