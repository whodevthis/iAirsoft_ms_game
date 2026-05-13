package org.project.msgame.application.dtos.team;

import java.util.UUID;

public record InputTeamDto(String name, String description, String imagePath,
                           double latRespawn, double lonRespawn, UUID cammoId
                           ) {
}
