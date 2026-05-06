package Domain.aggregates;

import Domain.valueObjects.Respawn;
import Domain.valueObjects.FlagTeam;
import Domain.valueObjects.PlayerObjective;
import Domain.valueObjects.PlayerRole;
import Domain.valueObjects.TeamRole;
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
    private FlagTeam flagTeam;

    private List<PlayerRole> players;
    private List<TeamRole> roles;

    private List<PlayerObjective> playerObjectives;
    private List<Objective> objectives;

    private UUID cammoId;

    private Respawn respawn;


}