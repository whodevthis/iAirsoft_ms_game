package domain.aggregates;

import domain.valueObjects.Respawn;
import domain.valueObjects.PlayerObjective;
import domain.valueObjects.PlayerRole;
import domain.valueObjects.TeamRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private UUID id;
    private String name;
    private String description;
    private String imagePath;

    private List<PlayerRole> players;
    private List<TeamRole> roles;

    private List<PlayerObjective> playerObjectives;
    private List<UUID> objectives;

    private UUID cammoId;

    private Respawn respawn;


}