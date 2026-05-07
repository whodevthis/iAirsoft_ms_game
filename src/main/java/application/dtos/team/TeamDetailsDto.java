package application.dtos.team;

import domain.valueObjects.PlayerObjective;
import domain.valueObjects.PlayerRole;
import domain.valueObjects.Respawn;
import domain.valueObjects.TeamRole;

import java.util.List;
import java.util.UUID;

public record TeamDetailsDto(UUID id, String name, String description, String imagePath, List<PlayerRole> players,
                             List<TeamRole> roles, List<PlayerObjective> playerObjectives, List<UUID> objectives,
                             UUID cammoId, Respawn respawn) {
}
