package org.project.msgame.application.dtos.team;

import org.project.msgame.domain.valueObjects.PlayerObjective;
import org.project.msgame.domain.valueObjects.PlayerRole;
import org.project.msgame.domain.valueObjects.Respawn;
import org.project.msgame.domain.valueObjects.TeamRole;

import java.util.List;
import java.util.UUID;

public record TeamDetailsDto(UUID id, String name, String description, String imagePath, List<PlayerRole> players,
                             List<TeamRole> roles, List<PlayerObjective> playerObjectives, List<UUID> objectives,
                             UUID cammoId, Respawn respawn) {
}
