package Application.dtos.team;

import Domain.valueObjects.PlayerObjective;
import Domain.valueObjects.PlayerRole;
import Domain.valueObjects.Respawn;
import Domain.valueObjects.TeamRole;

import java.util.List;
import java.util.UUID;

public record TeamDetailsDto(UUID id, String name, String description, String imagePath, List<PlayerRole> players,
                             List<TeamRole> roles, List<PlayerObjective> playerObjectives, List<UUID> objectives,
                             UUID cammoId, Respawn respawn) {
}
