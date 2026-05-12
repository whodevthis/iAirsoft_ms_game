package domain.aggregates;

import domain.valueObjects.Respawn;
import domain.valueObjects.PlayerObjective;
import domain.valueObjects.PlayerRole;
import domain.valueObjects.TeamRole;
import lombok.AllArgsConstructor;
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

    public Team(UUID cammoId, String description, UUID id, String imagePath, String name,
                List<UUID> objectives, List<PlayerObjective> playerObjectives,
                List<PlayerRole> players, Respawn respawn, List<TeamRole> roles) {
        this.cammoId = cammoId;
        this.description = description;
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.objectives = objectives != null ? objectives : new ArrayList<>();
        this.playerObjectives = playerObjectives != null ? playerObjectives : new ArrayList<>();
        this.players = players != null ? players : new ArrayList<>();
        this.respawn = respawn;
        this.roles = roles != null ? roles : new ArrayList<>();
    }
}